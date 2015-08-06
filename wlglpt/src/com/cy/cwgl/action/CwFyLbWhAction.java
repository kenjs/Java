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

import com.cy.cwgl.domain.CwFylbDomain;

/**
 * THE ACTION FOR 财务-开票登记
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/fylbwh", results = {
		@Result(name = "init", location = "/work/cwgl/cwfylb.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwfylb_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")
		})
@SuppressWarnings("unchecked")
public class CwFyLbWhAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwFylbDomain domain = (CwFylbDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序号");
//			headList.add("开票申请登记序号");
//			headList.add("客户登记序号");
			headList.add("所属机构");
			headList.add("费用类别名称");
			headList.add("创建人");
			headList.add("创建日期");
			headList.add("修改人");
			headList.add("修改日期");

		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			CwFylbDomain element = (CwFylbDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getJgMc());
			list.add(element.getFylbMc());
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
			domain = new CwFylbDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwFylbDomain) domain;
	}

	@Resource(name = "cwFyLbWhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

}
