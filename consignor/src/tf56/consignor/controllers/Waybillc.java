package tf56.consignor.controllers;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import tf56.consignor.services.LbsDeviceService;
import tf56.consignor.services.WaybillService;
import tf56.consignor.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.client.WebServiceException;



public class Waybillc extends RestController{
	public static Logger logger = Logger.getLogger(Waybillc.class); 
	
	/**
	 * 打开运单详情页面
	 * @author haoyong
	 * @date 2014-1-8
	 */
	@GET
	public void waybill_detail(){
		logger.debug("打开运单详情页面");
	}
	
	/**
	 * xiayao
	 * 首页根据运单号查询订单
	 * @return
	 */
	@POST
	public String selectWaybillNumber() {
		String msg="";
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		String url="contract/waybillcs/selectWaybillNumber";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, params).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg); // 返回 Json格式:
	}
	
	/**
	 * @author yao.xia
	 * @return 统计昨日发货数(票)
	 * @since 2014.1.08
	 */
	@POST
	public String selectYestodayCount() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map paramsMap = this.getParams();
		paramsMap = SysUtil.removeFilter(paramsMap);
		paramsMap.put("frompartyid",sessionBean.getPartyid());
		String url="contract/waybillcs/selectYestodayCount";
		ClientUtil cu=new ClientUtil(url);
		Map msgMap = new HashMap();
		try {
			String msg = cu.post(url, paramsMap).toString();
			msgMap.put("yestodayCount", msg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.map2json(msgMap)); // 返回 Json格式:
	}
	
	/**
	 * @author yao.xia
	 * @return 统计本月发货数(票)
	 * @since 2014.1.08
	 */
	@POST
	public String selectMouthCount() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map paramsMap = this.getParams();
		paramsMap = SysUtil.removeFilter(paramsMap);
		paramsMap.put("frompartyid",sessionBean.getPartyid());
		String url="contract/waybillcs/selectMouthCount";
		ClientUtil cu=new ClientUtil(url);
		Map msgMap = new HashMap();
		try {
			String msg = cu.post(url, paramsMap).toString();
			msgMap.put("mouthCount", msg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.map2json(msgMap)); // 返回 Json格式:
	}

	/**
	 * 查询运单
	 * @author hcm
	 * @date 2014-1-8
	 */

	@SuppressWarnings("unchecked")
	@POST
	public String selectWaybillList(){
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		formMap.put("frompartyid",sessionBean.getPartyid());//当前会员id
		formMap.put("sort", "sort");
		String url="contract/waybillcs/waybillListForFhf";
		ClientUtil cu=new ClientUtil(url);
		StringBuffer sb=new StringBuffer();
		String msg="";
		try {
			msg=cu.post(url, formMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}	
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg); //返回给 Json格式: 自定义
	}
	
	/**
	 * 运单详情
	 * @author haoyong
	 * @date 2014-1-8
	 * @return
	 */
	@POST
	public String waybill_detail_json(){
		Map map = SysUtil.removeFilter(getParams());
		HttpServletResponse response = this.getResponseHelper().getResponse();
		String url = "contract/waybillamountcs/selectWaybillById";
		ClientUtil cu = new ClientUtil(url);
		String msg = "";
		try {
			msg = cu.post(url, map).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return new JsonResponse().responseJson(response, msg);
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
    
    /**应付结算导出excel
     * @author yaoyan.lin
     * @date 2014-02-18
     */
	@GET
    public void exportExcel(){
		HttpServletResponse response=this.getResponseHelper().getResponse();
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = SysUtil.removeFilter(this.getParams());
		formMap.put("frompartyid",sessionBean.getPartyid());//当前会员id
		formMap.put("sort", "sort");
		String title="运单信息";
		String[] headers = { "客户单号", "运单号", "货物状态", "托运日期" ,"发货地", 
				"收货方", "收货地", "货物名称", "货物数量","重量", "体积"};
		WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");// 调用接口(实现类)
		try {
			waybillService.exportExcel(title, headers, response,formMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
    
}
