package com.cy.cwgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwZzjxbDomain;

/**
 * THE ACTION FOR 财务-周转金下拨
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwzzjxb", results = {
		@Result(name = "init", location = "/work/cwgl/cwzzjxb.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwzzjxb_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwZzjxbAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwZzjxbDomain domain = (CwZzjxbDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("资金调拨登记序号(SEQ_CW_DJXH)");
		headList.add("日期");
		headList.add("接收单位");
		headList.add("结存_合计");
		headList.add("结存_现金");
		headList.add("结存_油卡");
		headList.add("结存_存款");
		headList.add("支付_合计");
		headList.add("支付_预付款");
		headList.add("支付_余款");
		headList.add("支付_报销费用");
		headList.add("备用金");
		headList.add("资金需求");
		headList.add("下拨单位");
		headList.add("下拨金额");
		headList.add("备注");
		headList.add("有效标志(Y/N)");
		headList.add("需要审批标志(Y/N)");
		headList.add("文书审批状态代码");
		headList.add("文书审批序号");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwZzjxbDomain element = (CwZzjxbDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getZjdbDjxh());
			list.add(element.getRq());
			list.add(element.getJsDwMc());
			list.add(element.getJcHj());
			list.add(element.getJcXj());
			list.add(element.getJcYk());
			list.add(element.getJcCk());
			list.add(element.getZfHj());
			list.add(element.getZfYfk());
			list.add(element.getZfYk());
			list.add(element.getZfBxfy());
			list.add(element.getByj());
			list.add(element.getZjxq());
			list.add(element.getXbDwDjxh());
			list.add(element.getXbje());
			list.add(element.getBz());
			list.add(element.getYxbz());
			list.add(element.getSpbz());
			list.add(element.getWsspztDm());
			list.add(element.getWsSpxh());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwZzjxbDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwZzjxbDomain) domain;
	}

	@Resource(name = "cwZzjxbServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
