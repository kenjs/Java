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
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.HyHwxxShfsbgDomain;
import com.cy.hygl.service.HyHwxxShfsbgService;

/**
 * THE ACTION FOR 货运-货物信息-送货方式变更
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hyhwxxshfsbg", results = {
		@Result(name = "init", location = "/work/hygl/hy_hwxx_shfsbg.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_hwxx_shfsbg_mx.jsp"),
		@Result(name = "viewMx", location = "/work/hygl/hy_hwxx_shfsbg_view_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyHwxxShfsbgAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("送货变更-登记序号");
		headList.add("订单登记序号");
		headList.add("序号(货物明细序号)");
		headList.add("收入_小计");
		headList.add("收入_月结");
		headList.add("收入_现付");
		headList.add("收入_货到付");
		headList.add("收入_提货付");
		headList.add("收入_回单付");
		headList.add("收入_回扣");
		headList.add("收入_保价费");
		headList.add("收入_配送费");
		headList.add("收入_运输费");
		headList.add("创建人");
		headList.add("创建日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyHwxxShfsbgDomain element = (HyHwxxShfsbgDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getShbgDjxh());
			list.add(element.getDdDjxh());
			list.add(element.getXh());
			list.add(element.getSrHj());
			list.add(element.getSrYj());
			list.add(element.getSrXf());
			list.add(element.getSrHdf());
			list.add(element.getSrThf());
			list.add(element.getSrHf());
			list.add(element.getSrHk());
			list.add(element.getSrBjf());
			list.add(element.getSrPsf());
			list.add(element.getSrYsf());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	public String viewMx() throws Exception {
		((HyHwxxShfsbgService)this.getService()).viewMx(getDomain(), getUserDomain());
		return "viewMx";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyHwxxShfsbgDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyHwxxShfsbgDomain) domain;
	}

	@Resource(name = "hyHwxxShfsbgServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
