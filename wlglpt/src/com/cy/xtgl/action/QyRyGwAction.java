package com.cy.xtgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyRyGwDomain;

/**
 * THE ACTION FOR 企业-人员岗位
 * @author HaoY
 */
@Controller
@Scope("prototype")
@Action(value = "/xtgl/qyrygw", results = {
		@Result(name = "init", location = "/work/xtgl/qyrygw.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/xtgl/qyrygw_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyRyGwAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyRyGwDomain domain = (QyRyGwDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("用户名称");
		headList.add("主/兼标志");
		headList.add("部门名称");
		headList.add("岗位名称");
		headList.add("权限机构");
		
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyRyGwDomain element = (QyRyGwDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getMc());
			list.add(element.getZjbzStr());
			list.add(element.getSsjgMc());
			list.add(element.getGwmc());
			list.add(element.getQxjgMc());
			
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyRyGwDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyRyGwDomain) domain;
	}

	@Resource(name = "qyRyGwServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
