package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyDzcyyyDomain;

/**
 * THE ACTION FOR 企业-对账差异原因
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qydzcyyy", results = {
		@Result(name = "init", location = "/work/zygl/qydzcyyy.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qydzcyyy_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyDzcyyyAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyDzcyyyDomain domain = (QyDzcyyyDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("对账差异原因项目");
		headList.add("说明");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyDzcyyyDomain element = (QyDzcyyyDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getDzcyyy());
			list.add(element.getSm());
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
			domain = new QyDzcyyyDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyDzcyyyDomain) domain;
	}

	@Resource(name = "qyDzcyyyServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
