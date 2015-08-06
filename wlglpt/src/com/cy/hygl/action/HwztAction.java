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
import com.cy.hygl.domain.HwztDomain;
import com.cy.hygl.service.HwztService;
import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;

/**
 * THE ACTION FOR 货物自提
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hwzt", results = {
		@Result(name = "init", location = "/work/hygl/hwzt.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hwzt_mx.jsp"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HwztAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HwztDomain domain = (HwztDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("货物自提登记序号");
		headList.add("状态");
		headList.add("收货人");
		headList.add("货物");
		headList.add("包装");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("结算数量");
		headList.add("经办人登记序号");
		headList.add("经办人");
		headList.add("提货日期");

		heads.add(headList);

		int i = 1;
		for (HwztDomain element:(List<HwztDomain>)domain.getDataList()) {
			List list = new ArrayList();
			
			list.add(i+++ "");
			
			list.add(element.getHwztDjxh());
			list.add(element.getZt());
			list.add(element.getShrMc());
			list.add(element.getHwmc());
			list.add(element.getBz());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getTj());
			list.add(element.getJsSl());
			list.add(element.getJbrCzyDjxh());
			list.add(element.getJbrmc());
			list.add(element.getThrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HwztDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HwztDomain) domain;
	}

	@Resource(name = "hwztServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
	
	public void register() throws Exception{
		UserDomain userDomain = getUserDomain();
		((HwztService)getService()).register(getDomain(), userDomain);
	}
}
