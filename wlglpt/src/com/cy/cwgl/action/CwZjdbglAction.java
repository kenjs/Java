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
import com.cy.cwgl.domain.CwZjdbglDomain;

/**
 * THE ACTION FOR 财务-资金调拨管理
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwzjdbgl", results = {
		@Result(name = "init", location = "/work/cwgl/cwzjdbgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwzjdbgl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwZjdbglAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwZjdbglDomain domain = (CwZjdbglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("转出单位");
		headList.add("转入单位");
		headList.add("调拨金额");
		headList.add("计划日期");
		headList.add("备注");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwZjdbglDomain element = (CwZjdbglDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getDcDwMc());
			list.add(element.getDrDwMc());
			list.add(element.getJe());
			list.add(element.getRq());
			list.add(element.getBz());
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
			domain = new CwZjdbglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwZjdbglDomain) domain;
	}

	@Resource(name = "cwZjdbglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
