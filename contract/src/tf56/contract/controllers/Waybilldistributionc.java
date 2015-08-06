package tf56.contract.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import tf56.contract.domain.Goods;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillLogDao;
import tf56.contract.services.WaybillService;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Waybilldistributionc extends RestController{
	private final Logger log = Logger.getLogger("Waybilldistributionc.java");
	
	/**
	 * 运单分派页面
	 * 
	 * @author haoyong
	 * @date 2013-11-12
	 */
	@GET
	public void waybilldistributionlist(){
		
	}

	/**
	 * @author haoyong
	 * @date 2013-11-18
	 * @function 查询待分派运单列表
	 * @return
	 */
	@POST
	public String waybillList(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		formMap.put("partyid",sessionBean.getPartyid());//当前总包会员id
		String consigneeaddress  = "";
		if(formMap.containsKey("consigneetown")){
			consigneeaddress = formMap.get("consigneetown").toString();
			if(consigneeaddress.contains("-")){
				consigneeaddress=consigneeaddress.replaceAll("-", "");
			}
			formMap.put("consigneetown", consigneeaddress);
		}
		if("waybillList".equals(formMap.get("fromQuery").toString())){
		    formMap.put("operatorid",sessionBean.getOperatorid());//当前总包会员id
		}
		//formMap.put("status", "待分单");
		String distristatus = "";
		boolean haStatus = formMap.containsKey("distristatus");
		if(haStatus){
			formMap.put("status","待分派");
			WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
			String frompartyid = waybillDao.selectFrompartyid(formMap);
			distristatus = formMap.get("distristatus").toString() == null ?"":formMap.get("distristatus").toString();
			if("待分派".equals(distristatus)&&!StringUtils.isNotBlank(formMap.get("frompartyid").toString())){
				formMap.put("frompartyid", frompartyid);
			}
		}
		WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");
		String msgJson = waybillService.selectWaybillList(formMap);	
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
	}
	/**
	 * 选择分包商页面
	 * 
	 * @author haoyong
	 * @date 2013-11-14
	 */
	@GET
	public void subcontractorlist(){
		
	}
	
	/**
	 * @author haoyong
	 * @date 2013-11-21
	 * @function 分包商列表
	 * @return
	 */
	@POST
	public String chooseSubcontractorListJson(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = SysUtil.removeFilter(this.getParams());
		String partyid = sessionBean.getPartyid();
		formMap.put("partyid", partyid);
		formMap.put("partytype", "linked");
		//WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");
		//String msgJson = waybillService.combiWaybillList(formMap);
		WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");
		String msgJson = waybillService.queyConsignorInfo(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 * 运单分派操作
	 * @author haoyong
	 * @date 2013-11-22
	 * @return
	 */
	@POST
	public String waybillDistributionSave(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = SysUtil.removeFilter(this.getParams());
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
		String currrentDate = GetFormatDate.getCurrentDate();
		String inputMan = sessionBean.getRealname();
		formMap.put("status", "待配载");
		formMap.put("trace", "运单已分派");
		formMap.put("inputDate", currrentDate);
		formMap.put("inputMan", inputMan);
		List list = new ArrayList();
		String listStr = (String) formMap.get("waybillid");
		String[] chars = listStr.split(",");
		for(int i=0;i<chars.length;i++){
			list.add(chars[i]);
			formMap.put("waybillid", chars[i]);
			waybillLogDao.insert(formMap);  //保存日志操作记录
		}
		formMap.put("waybillidList", list);
		formMap.remove("waybillid");
		WaybillDao waybillDao = (WaybillDao)  SofaSpringContext.getBean("waybillDao");//调用接口(实现类)
		String msgJson = waybillDao.waybillDistribution(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	@POST
	public String total_json()
	{
		HttpServletRequest request = this.getResponseHelper().getRequest();
		HttpServletResponse response=this.getResponseHelper().getResponse();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		formMap.put("partyid",sessionBean.getPartyid());//当前总包会员id
		WaybillDao waybillDao = (WaybillDao)  SofaSpringContext.getBean("waybillDao");//调用接口(实现类)
		Map goodgMap = waybillDao.selectGoodsCount(formMap);
		String msg = "";
		msg = JsonGenerateUtil.map2json(goodgMap);
		return new JsonResponse().responseJson(response, msg);
	}
	
}
