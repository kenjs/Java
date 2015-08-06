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
import com.cy.zygl.domain.QyClxhwhDomain;
import com.cy.zygl.service.QyClxhwhService;

/**
 * THE ACTION FOR 企业-车辆型号维护
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qyclxhwh", results = {
		@Result(name = "init", location = "/work/zygl/qyclxhwh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyclxhwh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "queryClxhByKey", type = "json")
		})
@SuppressWarnings("unchecked")
public class QyClxhwhAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyClxhwhDomain domain = (QyClxhwhDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("车辆型号");
		headList.add("备注说明");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyClxhwhDomain element = (QyClxhwhDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getClxh());
			list.add(element.getBzsm());
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
	
	public String queryClxhByKey() throws Exception {
		((QyClxhwhService)getService()).queryClxhByKey(getDomain(),getUserDomain());
		return "queryClxhByKey";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyClxhwhDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyClxhwhDomain) domain;
	}

	@Resource(name = "qyClxhwhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
