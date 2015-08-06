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
import com.cy.zygl.domain.QyJcbmJldwDomain;

/**
 * THE ACTION FOR 企业-基础编码-计量单位
 * @author HaoY
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qyjcbmjldw", results = {
		@Result(name = "init", location = "/work/zygl/qyjcbmjldw.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyjcbmjldw_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyJcbmJldwAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("计量单位代码");
		headList.add("计量单位名称");
		headList.add("计量单位简称");
		headList.add("基本单位标志");
		headList.add("换算比例");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyJcbmJldwDomain element = (QyJcbmJldwDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getJldwDm());
			list.add(element.getJldwMc());
			list.add(element.getJldwJc());
			list.add(element.getJbdwStr());
			list.add(element.getHsbl());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyJcbmJldwDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyJcbmJldwDomain) domain;
	}

	@Resource(name = "qyJcbmJldwServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
