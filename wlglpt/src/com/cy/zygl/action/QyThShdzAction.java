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
import com.cy.zygl.domain.QyThShdzDomain;

/**
 * THE ACTION FOR 企业-提货收货地址维护
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qythshdz", results = {
		@Result(name = "init", location = "/work/zygl/qythshdz.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qythshdz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyThShdzAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyThShdzDomain domain = (QyThShdzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("所属机构");
		headList.add("地址");
		headList.add("联系电话");
		headList.add("负责人");
		headList.add("行政区划");
		headList.add("创建人");
		headList.add("创建日期");	
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyThShdzDomain element = (QyThShdzDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getSsJgmc());
			list.add(element.getDz());
			list.add(element.getDh());
			list.add(element.getFzr());
			list.add(element.getFhrXzqhMc());
			list.add(element.getXgrMc());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			
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
			domain = new QyThShdzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyThShdzDomain) domain;
	}

	@Resource(name = "qyThShdzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
