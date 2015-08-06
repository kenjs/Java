package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.HyHwqsDomain;

/**
 * THE ACTION FOR 货运-货物签收
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hwqs", results = {
		@Result(name = "init", location = "/work/hygl/hy_hwqs.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_hwqs_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyHwqsAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyHwqsDomain domain = (HyHwqsDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("状态");
		headList.add("回单编号");
		headList.add("客户名称");
		headList.add("订单编号");
		headList.add("货物名称");
		headList.add("数量");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("收货人");
		headList.add("收货地址");
		headList.add("签收人");
		headList.add("签收日期");
		headList.add("派车单号");
		headList.add("承运商");
		headList.add("派车日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyHwqsDomain element = (HyHwqsDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			if(StringUtils.isNotBlank(element.getQsrmc())){
				list.add("已登记");
			}
			else{
				list.add("未登记");
			}
			list.add(element.getHdbh());
			list.add(element.getFhrMc());
			list.add("");
			list.add(element.getHwMc());
			list.add(element.getHwSl());
			list.add(element.getSfd());
			list.add(element.getMdd());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getQsrmc());
			list.add(element.getQsrq());
			list.add(element.getPcdh());
			list.add(element.getSjXm());
			list.add(element.getPcrq());
			

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyHwqsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyHwqsDomain) domain;
	}

	@Resource(name = "hyHwqsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
