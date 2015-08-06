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
import com.cy.hygl.domain.XyjsSrdzDomain;
import com.cy.hygl.service.XyjsSrdzService;

/**
 * THE ACTION FOR 下游结算-收入对帐
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/xyjssrdz", results = {
		@Result(name = "init", location = "/work/hygl/xyjssrdz.jsp"),
		@Result(name = "queryDzqdList", type = "json"),
		@Result(name = "query", type = "json"),
		@Result(name = "savePldz", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/xyjssrdz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class XyjsSrdzAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		XyjsSrdzDomain domain = (XyjsSrdzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		if ("1".equals(domain.getFylbDm())) {
			headList.add("配送费");
		}else if ("2".equals(domain.getFylbDm())) {
			headList.add("到付款");
		}else {
			headList.add("代收货款");
		}
		
		headList.add("对账金额");
		headList.add("差异");
		headList.add("对账备注");
		headList.add("订单编号");
		headList.add("下单日期");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("货物名称");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("发货人");
		headList.add("收货人");
		headList.add("收货地址");
		headList.add("下游单位");
		headList.add("对账单位");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			XyjsSrdzDomain element = (XyjsSrdzDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getJsJe());
			list.add(element.getDzje());
			list.add(element.getDzcyje());
			list.add(element.getBz());
			list.add(element.getDdbh());
			list.add(element.getXdrq());
			list.add(element.getSfdXzqhMc());
			list.add(element.getMddXzqhMc());
			list.add(element.getHwmc());
			list.add(element.getHwSl());
			list.add(element.getHwZl());
			list.add(element.getHwTj());
			list.add(element.getFhrLxr());
			list.add(element.getShrLxr());
			list.add(element.getShrDz());
			list.add(element.getXyMc());
			list.add(element.getSsJgmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String queryDzqdList() throws Exception {
		((XyjsSrdzService)getService()).queryDzqdList(getDomain(), getUserDomain());
		return "queryDzqdList";
	}
	
	public String savePldz() throws Exception {
		((XyjsSrdzService)getService()).savePldz(getDomain(), getUserDomain());
		return "savePldz";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XyjsSrdzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XyjsSrdzDomain) domain;
	}

	@Resource(name = "xyjsSrdzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
