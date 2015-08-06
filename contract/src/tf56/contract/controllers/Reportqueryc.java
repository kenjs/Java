package tf56.contract.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.contract.services.ReportQueryService;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Reportqueryc extends RestController{
	/**
	 * @author wei.huang
	 * @date 2013-11-25
	 * @function 跳转到运单明细页面
	 */
	@GET
	public void waybillDetail(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-25
	 * @function 跳转到应付明细页面
	 */
	@GET
	public void payableDetail(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-25
	 * @function 跳转到应收明细页面
	 */
	@GET
	public void receivableDetail(){
		
	}
	/**
     * @author wei.huang
     * @date 2013-11-27
     * @function 查询符合报表要求的运单明细表(已包含应收应付金额)
     * @param map:一定包括partyid，可能含有参数(waybillnumber,status,frompartyid和topartyid:以","联接的字符串,startdate,enddate,consignorprovince,consignorcity,consignorregion,consigneeprovince,consigneecity,consigneeregion)
     * @return Json
     */
	@POST
    public String selectWaybillListForReport(){
    	Map params=SysUtil.removeFilter(this.getParams());
    	HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		params.put("partyid", sessionBean.getPartyid());
		ReportQueryService reportQueryService=(ReportQueryService)SofaSpringContext.getBean("reportQueryService");
		String msgJson=reportQueryService.selectWaybillDetailForReport(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
    }
	/**
     * @author wei.huang
     * @date 2013-11-27
     * @function 导出excel
     */
	@GET
	public void exportExcel(){
        HttpServletResponse response=this.getResponseHelper().getResponse();
        String title="运单详细报表";
        ReportQueryService reportQueryService=(ReportQueryService)SofaSpringContext.getBean("reportQueryService");
        Map params=SysUtil.removeFilter(this.getParams());
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		params.put("partyid", sessionBean.getPartyid());
        String[] headers = { "运单编号", "运单状态", "紧急程度", "托运日期", "发货方" ,"发货地", "收货地", "分包商", "数量", "重量（公斤）","体积（立方）", "应收费用（元）", "应付费用（元）", "毛利（元）", "毛利率"};
        try{
        	reportQueryService.exportExcel(title, headers, response,params);
        }catch(IOException ex){
        	ex.printStackTrace();
        }
    }
}
