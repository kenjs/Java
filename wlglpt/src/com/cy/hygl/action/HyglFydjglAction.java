package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyFydjglService;
/**
 * THE ACTION FOR 	货运管理-作业管理-费用登记
 * @author hy
 *
 */

@Controller
@Scope("prototype")
@Action(value = "/hygl/fydjgl", results = {
		@Result(name = "init", location = "/work/hygl/hygl_fydjgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hygl_fydjgl_mx.jsp"),
		@Result(name = "save", location = "/work/hygl/hygl_fydjgl_mx.jsp"),
		@Result(name = "viewMx", location = "/work/hygl/hygl_fydjgl_mx_readonly.jsp"),
		@Result(name = "queryKhHw", type = "json"),
		@Result(name = "deleteFydj", type = "json"),
		@Result(name = "delete", type = "json")})
public class HyglFydjglAction extends ExtendAction {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyZyglFydjDomain domain = (HyZyglFydjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("序号");
		headList.add("派车单号");
		headList.add("车辆号码");
		headList.add("挂车号码");
		headList.add("司机");
		headList.add("订单编号");
		headList.add("客户名称");
		headList.add("货物名称");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("转入部门");
		headList.add("结算数量");
		headList.add("发货地址");
		headList.add("收货地址");
		headList.add("派车人");
		headList.add("派车日期");
		headList.add("派车部门");
		headList.add("业务单位");
		
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyZyglFydjDomain element = (HyZyglFydjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getPcdh());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			list.add(element.getCyrSjxm());
			list.add(element.getDdbh());
			list.add(element.getKhmc());
			list.add(element.getHwmc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getZrbmMc());
			list.add(element.getJsSl());
			list.add(element.getFhrDz());
			list.add(element.getShrDz());
			list.add(element.getPcrMc());
			list.add(element.getPcrq());
			list.add(element.getPcJgmc());
			list.add(element.getSsJgmc());
			
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}

	public String queryKhHw() throws Exception {
		((HyFydjglService)getService()).getKhHwXx(getDomain());
		return "queryKhHw";
	}
	
	public String viewMx() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().initMx(getDomain(), userDomain);
		return "viewMx";
	}
	
	public String deleteFydj() throws Exception {
		UserDomain userDomain = getUserDomain();
		((HyFydjglService)getService()).deleteFydj(getDomain(),userDomain);
		return "deleteFydj";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyZyglFydjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyZyglFydjDomain) domain;
	}

	@Resource(name = "hyFydjglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
