package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyYlClxxDomain;
import com.cy.zygl.service.QyYlClxxShclService;

/**
 * THE ACTION FOR 企业-运力-车辆信息
 * @author Haoy
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qyylclxxshcl", results = {
		@Result(name = "init", location = "/work/zygl/qyylclxxshcl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyylclxxshcl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteSjxx",type = "json")})
@SuppressWarnings("unchecked")
public class QyYlClxxShclAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyYlClxxDomain domain = (QyYlClxxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("车辆号码");
		headList.add("车主姓名");
		headList.add("证件类型");
		headList.add("证件号码");
		headList.add("联系电话");
		headList.add("司机");
		headList.add("备注");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyYlClxxDomain element = (QyYlClxxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getClhm());
			list.add(element.getCzXm());
			list.add(element.getZjlxMc());
			list.add(element.getCzZjhm());
			list.add(element.getCzLxdh());
			list.add(element.getSjXm());
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
	
	public String deleteSjxx() throws Exception {
		((QyYlClxxShclService)getService()).deleteSjxx(getDomain());
		return "deleteSjxx";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyYlClxxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyYlClxxDomain) domain;
	}

	@Resource(name = "qyYlClxxShclServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}

