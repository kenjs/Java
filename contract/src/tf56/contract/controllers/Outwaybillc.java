package tf56.contract.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.domain.OutWaybill;
import tf56.contract.services.OutWaybillDao;
import tf56.contract.services.OutWaybillService;
import tf56.contract.util.DateUtils;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Outwaybillc extends RestController {
	private final Logger log = Logger.getLogger(Outwaybillc.class);
	
	
	@GET
	public void orderManage(){
		
	}
	/**
	 * 保存订单
	 * hcm
	 * @throws IOException
	 */
	@POST
	public String saveOutWaybill() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
	
		OutWaybillService outWaybillService = (OutWaybillService) SofaSpringContext
				.getBean("outWaybillService"); // 调用接口(实现类)
		String msg = outWaybillService.transactionSaveOutWaybill(formMap); // 调用Dao同名方法
		String msgJson=JsonGenerateUtil.getMsgIdJson(msg, "");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回
	}
	@POST
	public String selectOutWaybillByPartyId(){
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		if("orderManage".equals(formMap.get("fromQuery").toString())){
			formMap.put("partyid",sessionBean.getPartyid());//当前总包会员id
//			formMap.put("partyid","176766");//当前总包会员id,测试用
		}
		OutWaybillDao  outWaybillDao=(OutWaybillDao) SofaSpringContext.getBean("outWaybillDao");
		List<Map> list=outWaybillDao.selectOutWaybillByPartyId(formMap);
		String count=outWaybillDao.selectOutWaybillByPartyIdCount(formMap);
		String msg=JsonGenerateUtil.getPageListJson(list, count);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg); // 返回
	}
	
	@GET
	public void order_detail() {
		log.debug("打开订单详情页面！");
	}
	
	@POST
	public String oreder_detail_json() {
		Map parsMap = SysUtil.removeFilter(getParams());
		HttpServletResponse response = this.getResponseHelper().getResponse();
		OutWaybillService outWaybillService = (OutWaybillService) SofaSpringContext
													.getBean("outWaybillService"); // 调用接口(实现类)
		String msgJson = outWaybillService.selectOutWaybillByWaybillId(parsMap);
		return new JsonResponse().responseJson(response, msgJson);
	}
	@GET
	public void order_waybill(){
		log.debug("订单转运单");
	}
	@POST
	public String save_order_waybill(){
		Map parsMap = SysUtil.removeFilter(getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		OutWaybillService outWaybillService = (OutWaybillService) SofaSpringContext
													.getBean("outWaybillService"); // 调用接口(实现类)
		OutWaybill waybill=new OutWaybill();
		ParseFormToBean parseFormToBean=new ParseFormToBean();
		waybill=(OutWaybill) parseFormToBean.parseToBean(parsMap, waybill);
		waybill.setStatus("已确认");
		waybill.setOperator(sessionBean.getOperator());
		waybill.setOperatetime(DateUtils.formatString(new Date()));
		String msgJson = outWaybillService.saveOrderWaybill(waybill);
		return new JsonResponse().responseJson(response, msgJson);
	}
}