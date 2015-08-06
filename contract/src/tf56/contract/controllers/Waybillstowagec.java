package tf56.contract.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;
import org.json.JSONException;

import tf56.contract.domain.WaybillStowage;
import tf56.contract.domain.WaybillStowageLog;
import tf56.contract.services.PartyService;
import tf56.contract.services.WaybillLogDao;
import tf56.contract.services.WaybillService;
import tf56.contract.services.WaybillStowageDao;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtilNew;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Waybillstowagec extends RestController {
	private final Logger log = Logger.getLogger("Waybillstowagec.java");
	// 增 必须把获取的map转为bean对数据库进行操作
	@POST
	public String insert() throws IOException, JSONException {
	    
	    HttpServletRequest request = this .getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
	    
	    String currrentDate = GetFormatDate.getCurrentDate();
        String inputMan = sessionBean.getRealname();
        Map formMap = this.getParams();
        Map waybillStowageLogMap = new HashMap();
        formMap = SysUtil.removeFilter(formMap);
        formMap.put("status", "待确认");
        formMap.put("inputdate", currrentDate);
        formMap.put("inputman", inputMan);
        formMap.put("frompartyid", sessionBean.getPartyid());
        WaybillStowage waybillStowage = new WaybillStowage(); // 把map转成bean对象

        ParseFormToBean pftb = new ParseFormToBean();
        waybillStowage = (WaybillStowage) pftb.parseToBean(formMap,
                waybillStowage);// 将map转为bean对象
        // 系统调度单号获取
        WaybillService waybillService = (WaybillService) SofaSpringContext
                .getBean("waybillService");
        String systemdispatchnumber = waybillService.getSystemdispatchnumber();
        waybillStowage.setSystemdispatchnumber(systemdispatchnumber);
        //
        WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
                .getBean("waybillStowageDao"); // 调用接口(实现类)
        String msgJson = waybillStowageDao.insert(waybillStowage); // 调用Dao同名方法
        // 添加日志操作记录
        formMap.put("status", "已配载");
        formMap.put("trace", "运单已配载");
        formMap.put("inputDate", currrentDate);
        formMap.put("inputMan", inputMan);
        WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext
                .getBean("waybillLogDao"); // 调用接口(实现类)
        waybillLogDao.insert(formMap); // 保存日志操作记录
        //记录调度单操作日志
        Map ws = Json2ObjectUtilNew.fromJSONStr2Map(msgJson);
        waybillStowageLogMap.put("waybillstowageid", ws.get("id").toString());
        waybillStowageLogMap.put("systemdispatchnumber", systemdispatchnumber);
        waybillStowageLogMap.put("status", "待确认");
        waybillStowageLogMap.put("trace", "");
        waybillStowageLogMap.put("inputdate", currrentDate);
        waybillStowageLogMap.put("inputman", inputMan);
        WaybillStowageLog bean = new WaybillStowageLog();
        ParseFormToBean pft = new ParseFormToBean();
        bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap, bean);
        waybillStowageDao.insertWaybillStowageLog(bean);
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
                                                                    // Json格式:
                                                                    // msg:ok|错误信息,id:int值
    }
	
	/**
	 * @author yao.xia
	 * @date 2013-12-16
	 * @function 调度单信息查询
	 */
	@GET
	public String waybillStowageList() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		// 
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao"); // 调用接口(实现类)
		List list = waybillStowageDao.selectStowageList(formMap); // 调用Dao同名方法
		int count = Integer.parseInt(waybillStowageDao.selectCount(formMap));
		//
		String msgJson = JsonGenerateUtil.getPageListJson(list, count + "");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}
	
	/**
	 * @author yao.xia
	 * @throws IOException
	 * @date 2013-12-16
	 * @function 根据调度单号批量装车确认
	 */
	@POST
	public String loadSure() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		//
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao"); // 调用接口(实现类)
		String msgJson = waybillStowageDao.loadSureByStowageId(formMap); // 调用Dao同名方法
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}
	
	/**
	 * @author yao.xia
	 * @throws IOException
	 * @date 2013-12-16
	 * @function 根据调度单号批量确认发车
	 */
	@POST
	public String startSure() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		//
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao"); // 调用接口(实现类)
		String msgJson = waybillStowageDao.loadSureByStowageId(formMap); // 调用Dao同名方法
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}
	
	/**
	 * @author yao.xia
	 * @throws IOException
	 * @date 2013-12-16
	 * @function 根据调度单号批量确认到车
	 */
	@POST
	public String arriveSure() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		//
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao"); // 调用接口(实现类)
		String msgJson = waybillStowageDao.arriveSureByStowageId(formMap); // 调用Dao同名方法
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}
	
	/**
	 * @author yao.xia
	 * @throws IOException
	 * @date 2013-12-16
	 * @function 根据调度单号批量取消配载
	 */
	@POST
	public String stowageCancel() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		//
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao"); // 调用接口(实现类)
		String msgJson = waybillStowageDao.stowageCancelByStowageId(formMap); // 调用Dao同名方法
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}
	
	@POST
	public String waybillStowageCommit(){
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		PartyService partyService = (PartyService) SofaSpringContext.getBean("partyService");
		formMap = getXMLMap(formMap);
		String msgJson = partyService.waybillStowageCommit(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}

	private Map getXMLMap(Map formMap) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
				.append("<report_data>")
				.append("<request_sys>SCM</request_sys>")
				.append("<request_time>"+GetFormatDate.getCurrentDate()+"</request_time>")
				.append("<request_param>")
				.append("<driverName>"+formMap.get("drivername")+"</driverName>")
				.append("<carPlateNumber>"+formMap.get("carplatenumber")+"</carPlateNumber>")
				.append("<carrier>"+formMap.get("topartyname")+"</carrier>")
				.append("<systemDispatchNumber>"+formMap.get("paperdispatchnumber")+"</systemDispatchNumber>")
				.append("<carType>"+formMap.get("cartype")+"</carType>")
				.append("<driverMobile>"+formMap.get("drivermobile")+"</driverMobile>")
				.append("<dispatchDate>"+formMap.get("dispatchdate")+"</dispatchDate>")
				.append("<clientNumberList>")
		;
		String clientnumberStr=(String) formMap.get("clientnumberStr");
		String[] clientnumbers = clientnumberStr.split(",");
		for(String clientnumber:clientnumbers){
			buffer.append("<clientNumber>"+clientnumber+"</clientNumber>");
		}
		buffer.append("</clientNumberList>" +
				"</request_param>" +
				"</report_data>");
		formMap.put("buffer", buffer.toString());
		return formMap;
	}
	
}