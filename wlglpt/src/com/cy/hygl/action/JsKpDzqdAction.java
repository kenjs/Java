package com.cy.hygl.action;

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
import com.cy.hygl.domain.JsKpDzqdDomain;
import com.cy.hygl.service.JsKpDzqdService;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;

/**
 * THE ACTION FOR 预开票对账清单核销
 * @author HCM
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/jskpdzqd", results = {
		@Result(name = "init", location = "/work/hygl/jskpdzqd.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/jskpdzqd_mx.jsp"),
		@Result(name = "initAddHxMx", location = "/work/hygl/jskpdzqd_addhxmx.jsp"),
		@Result(name = "initHxMx", location = "/work/hygl/jskpdzqd_hxmx.jsp"),
		@Result(name = "delete", type = "json"),
		@Result(name = "save", type = "json")})
@SuppressWarnings("unchecked")
public class JsKpDzqdAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	public String initAddHxMx() throws Exception{
		((JsKpDzqdService)getService()).doMyInitAddHxMx(domain);
		return "initAddHxMx";
	}
	public String initHxMx() throws Exception{
		((JsKpDzqdService)getService()).doMyInitHxMx(domain);
		return "initHxMx";
	}
	public String download() throws Exception {
		super.download();
		JsKpDzqdDomain domain = (JsKpDzqdDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
	
		headList.add("序号");
		headList.add("客户名称");
		headList.add("申请开票金额");
		headList.add("申请开票日期");
		headList.add("开票状态");
		
		headList.add("开票金额");
		headList.add("开票日期");
		headList.add("核销标志");
		headList.add("核销金额");
		headList.add("登记人");
		
		headList.add("登记日期");
		headList.add("部门");
		headList.add("单位");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			JsKpDzqdDomain element = (JsKpDzqdDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getKhmc());
			list.add(element.getSqKpjeHj());
			list.add(element.getSqKprq());
			list.add(element.getFpkjbz());
			
			list.add(element.getFpkjje());
			list.add(element.getFpkjrq());
			list.add(element.getYkpQdhx());
			list.add(element.getYhxje());	
			list.add(element.getDjrMc());
			
			list.add(element.getDjrq());
			list.add(element.getDjJgbm());
			list.add(element.getSsJgbm());


			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new JsKpDzqdDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (JsKpDzqdDomain) domain;
	}

	@Resource(name = "jsKpDzqdServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
