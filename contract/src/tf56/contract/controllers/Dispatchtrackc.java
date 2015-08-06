package tf56.contract.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.contract.domain.WaybillStowage;
import tf56.contract.services.LbsDeviceService;
import tf56.contract.services.WaybillService;
import tf56.contract.services.WaybillStowageDao;
import tf56.contract.services.WaybillStowageService;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Dispatchtrackc extends RestController{
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 打开调度跟踪页面
	 */
	@GET
	public void dispatchtrack_list(){}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 查询调度单信息
	 */
	@POST
	public String selectWaybillstowageList(){
		String str = "";
		Map map = SysUtil.removeFilter(getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		map.put("partyid", partyid);
		String fromdate = "";
		String todate = "";
		if(map.get("fromdate")!=null){
			fromdate = map.get("fromdate").toString();
		}
		if(map.get("todate")!=null){
			todate = map.get("todate").toString();
		}
		if(StringUtils.isNotBlank(fromdate)){
			map.put("fromdate", fromdate+" 00:00:00");
		}
		if(StringUtils.isNotBlank(todate)){
			map.put("todate", todate+" 23:59:59");
		}
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao)SofaSpringContext.getBean("waybillStowageDao");
		List list = waybillStowageDao.selectWaybillstowageList(map);
		String count = waybillStowageDao.selectCount(map);
		str = JsonGenerateUtil.getPageListJson(list, count);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 打开装车确认页面
	 */
	@GET
	public void carloadconfirm(){}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 装车确认
	 */
	@POST
	public String carloadconfirm_save(){
		String msg = "";
		Map formMap = SysUtil.removeFilter(getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		String sureman = sessionBean.getRealname();
		String date = GetFormatDate.getCurrentDate();
		WaybillStowageService waybillStowageService = (WaybillStowageService) SofaSpringContext.getBean("waybillStowageService");
		formMap.put("status", "已确认");
		formMap.put("sureman", sureman);
		formMap.put("suredate", formMap.get("carloaddate").toString());
		formMap.put("inputman", sureman);
		formMap.put("inputdate", date);
		msg = waybillStowageService.carloadconfirm_save(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 打开发车确认页面
	 */
	@GET
	public void carbeginconfirm(){}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 发车确认
	 */
	@POST
	public String carbeginconfirm_save(){
		String msg = "";
		Map formMap = SysUtil.removeFilter(getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		String sureman = sessionBean.getRealname();
		String date = GetFormatDate.getCurrentDate();
		WaybillStowageService waybillStowageService = (WaybillStowageService) SofaSpringContext.getBean("waybillStowageService");
		formMap.put("status", "已发车");
		formMap.put("inputman", sureman);
		formMap.put("inputdate", date);
		msg = waybillStowageService.carbeginconfirm_save(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 打开到车确认页面
	 */
	@GET
	public void cararriveconfirm(){}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 到车确认
	 */
	@POST
	public String cararriveconfirm_save(){
		String msg = "";
		Map formMap = SysUtil.removeFilter(getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		String sureman = sessionBean.getRealname();
		String date = GetFormatDate.getCurrentDate();
		WaybillStowageService waybillStowageService = (WaybillStowageService) SofaSpringContext.getBean("waybillStowageService");
		formMap.put("status", "已到车");
		formMap.put("inputman", sureman);
		formMap.put("inputdate", date);
		msg = waybillStowageService.cararriveconfirm_save(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 调度单删除
	 * @return
	 */
	@POST
	public String delete_waybillstowage(){
		String msg = "";
		Map formMap = SysUtil.removeFilter(getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		String sureman = sessionBean.getRealname();
		String date = GetFormatDate.getCurrentDate();
		formMap.put("inputman", sureman);
		formMap.put("inputdate", date);
		WaybillStowageService waybillStowageService = (WaybillStowageService) SofaSpringContext.getBean("waybillStowageService");
		msg = waybillStowageService.delete_waybillstowage(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 打开调度单详情页面
	 */
	@GET
	public void waybillstowage_detail(){}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 调度单详情
	 */
	@POST
	public String waybillstowage_detail_json(){
		String strJson = "";
		Map map = SysUtil.removeFilter(getParams());
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext.getBean("waybillStowageDao");
		WaybillStowage bean = waybillStowageDao.selectWaybillStowageById(map);
		if(bean!=null){
			strJson = JsonGenerateUtil.bean2json(bean);
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, strJson);
	}
	
	/**
	 * @author xianjing.ni
	 * @date 2013-12-18
	 * @function 调度单打印预览
	 */
	@GET
	public void waybillstowage_print() {}
	
	/**
	 * @author xianjing.ni
	 * @date 2013-12-19
	 * @function  根据调度单号查询运单信息
	 */
	@POST
	public String waybillstowage_print_json() {
		String strJson = "";
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		HttpServletRequest request = this.getResponseHelper().getRequest();
		WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");
		List list = waybillService.selectWaybillGoodsByStowageId(params);
		if(list.size()>0) {
			strJson = JsonGenerateUtil.getListJson(list);
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, strJson);
	}
	
	/**
	 * @author xianjing.ni
	 * @date 2013-12-19
	 * @function  查询当前操作人
	 */
	@POST
	public String waybillstowage_userName_json() {
		Map map = new HashMap();
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		String sureman = sessionBean.getRealname();
		map.put("username", sureman);
		String strJson = JsonGenerateUtil.map2json(map);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, strJson);
	}
	/**
     * @author wei.huang
     * @date 2014-1-21
     * @function 调度单跟踪
     */
    @POST
    public String track(){
    	Map formMap = this.getParams();
        formMap = SysUtil.removeFilter(formMap);
        HttpServletResponse response = this.getResponseHelper().getResponse();
        String msgJson="";
        try {
        	LbsDeviceService lbsDeviceService = (LbsDeviceService) SofaSpringContext.getBean("lbsDeviceService"); // 调用接口(实现类)
            msgJson=lbsDeviceService.track(formMap);
            if(msgJson.equals("")||msgJson==null){
            	msgJson=JsonGenerateUtil.getMsgJson("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResponse().responseJson(response, msgJson);
    }
    /**
     * @author wei.huang
     * @date 2014-1-21
     * @function 调度单回放
     */
    @POST
    public String playback(){
    	Map formMap = this.getParams();
        formMap = SysUtil.removeFilter(formMap);
        HttpServletResponse response = this.getResponseHelper().getResponse();
        String msgJson="";
        try {
        	LbsDeviceService lbsDeviceService = (LbsDeviceService) SofaSpringContext.getBean("lbsDeviceService");// 调用接口(实现类)
            msgJson=lbsDeviceService.playback(formMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResponse().responseJson(response, msgJson);
    }
}
