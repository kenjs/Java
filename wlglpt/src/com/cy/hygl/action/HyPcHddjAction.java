package com.cy.hygl.action;

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
import com.cy.hygl.domain.HyPcHddjDomain;
import com.cy.hygl.service.HyPcHddjService;

/**
 * THE ACTION FOR 货运-派车-回单
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hypchddj", results = {
		@Result(name = "init", location = "/work/hygl/hy_pc_hddj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_pchddj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "plSave", type = "json"),
		@Result(name = "saveWlssDj", type = "json"),
		@Result(name = "plJs",type= "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "initViewMx", location = "/work/hygl/hy_pchddj_mx_readonly.jsp")
		})
@SuppressWarnings("unchecked")
public class HyPcHddjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcHddjDomain domain = (HyPcHddjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("状态");
		headList.add("回单编号");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("货物名称");
		headList.add("包装");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("发货人");
		headList.add("收货人");
		headList.add("派车单号");
		headList.add("派车日期");
		headList.add("车主姓名");
		headList.add("车辆号码");
		headList.add("挂车号码");
		headList.add("司机姓名");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPcHddjDomain element = (HyPcHddjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	public String plSave() throws Exception {
		((HyPcHddjService)getService()).plSave(getDomain());
		return "plSave";
	}
	
	public String saveWlssDj()throws Exception{
		((HyPcHddjService)getService()).saveWlssDj(getDomain(), getUserDomain());
		return "saveWlssDj";
	}
	
	public String initViewMx() throws Exception {
		((HyPcHddjService)getService()).initViewMx(getDomain(), getUserDomain());
		return "initViewMx";
	}
	
	public String plJs() throws Exception {
		((HyPcHddjService)getService()).plJs(getDomain(),getUserDomain());
		return "plJs";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcHddjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPcHddjDomain) domain;
	}

	@Resource(name = "hyPcHddjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
