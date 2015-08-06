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
import com.cy.xtgl.domain.QyGwDomain;
import com.cy.xtgl.service.QyGwService;

/**
 * THE ACTION FOR 企业-岗位
 * @author HaoY
 */
@Controller
@Scope("prototype")
@Action(value = "/xtgl/gwwh", results = {
		@Result(name = "init", location = "/work/xtgl/qygw.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/xtgl/qygw_mx.jsp"),
		@Result(name = "initXtgwMx", location = "/work/xtgl/xtgw_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "saveDisable", type = "json"),
		@Result(name = "saveXtgw", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyGwAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyGwDomain domain = (QyGwDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("系统编号");
		headList.add("岗位名称");
		headList.add("岗位简称");
		headList.add("备注说明");
		headList.add("标志");
		headList.add("系统岗位代码");
		headList.add("状态");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");
		
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain element:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyGwDomain el = (QyGwDomain)element;
			
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(el.getGwDjxh());
			list.add(el.getGwMc());
			list.add(el.getGwJc());
			list.add(el.getBzsm());
			list.add(el.getLybzStr());
			list.add(el.getGwDm());
			list.add(el.getQybzStr());
			list.add(el.getCjrMc());
			list.add(el.getCjrq());
			list.add(el.getXgrMc());
			list.add(el.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String initXtgwMx() throws Exception {
		((QyGwService)getService()).initXtgwMx(getDomain());
		return "initXtgwMx";
	}
	
	public String saveXtgw() throws Exception {
		((QyGwService)getService()).saveXtgw(getDomain(),getUserDomain());
		return "saveXtgw";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyGwDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyGwDomain) domain;
	}

	@Resource(name = "qyGwServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
