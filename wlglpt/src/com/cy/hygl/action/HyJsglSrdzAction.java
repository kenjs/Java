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
import com.cy.hygl.domain.HyJsglSrdzDomain;
import com.cy.hygl.service.HyJsglSrdzService;

/**
 * THE ACTION FOR 收入对账(订单)
 * @author HJH
 */
@Controller		
@Scope("prototype")	
@Action(value = "/hygl/jsglsrdz", results = { 
		@Result(name = "init", location = "/work/hygl/hy_jsgl_srdz.jsp"),
		@Result(name = "initMx", location = "/work/hygl/hy_jsgl_srdz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveMx", location = "/work/hygl/hy_jsgl_srdz_mx.jsp"),
		@Result(name = "queryMx", location = "/work/hygl/hy_jsgl_srdz_dzcy.jsp"),
		@Result(name = "deleteMx", type = "json"),
		@Result(name = "plDz", type = "json"),
		@Result(name = "view", location = "/work/hygl/hy_jsgl_srdz_mx_view.jsp"),
		@Result(name = "query", type = "json")})
public class HyJsglSrdzAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public String view() throws Exception{
		this.getService().initMx(this.getDomain(), this.getUserDomain());
		return "view";
	}

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("序号");
		headList.add("审核");
		headList.add("状态");
		headList.add("客户名称");
		
		headList.add("未结");
		
		headList.add("订单编号");
		headList.add("下单日期");
		headList.add("货物名称");
		headList.add("结算数量");
		
		headList.add("回单编号");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("数量");
		headList.add("重量");
		
		headList.add("体积");
		headList.add("包装");
		headList.add("对帐人");
		headList.add("对帐日期");
		headList.add("对账部门");
		
		headList.add("业务单位");


		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain element:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyJsglSrdzDomain dom = (HyJsglSrdzDomain)element;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(dom.getWsSpztMc());
			list.add(dom.getDzztMc());
			list.add(dom.getKhMc());
			list.add(dom.getDzwj());
			list.add(dom.getDdbh());
			list.add(dom.getXdrq());
			list.add(dom.getHwmc());
			list.add(dom.getJssl());
			
			
			list.add(dom.getHdbh());
			list.add(dom.getSfd());
			list.add(dom.getMdd());
			list.add(dom.getSl());
			list.add(dom.getZl());
			
			list.add(dom.getTj());
			list.add(dom.getBz());
			list.add(dom.getDzrMc());
			list.add(dom.getDzrq());
			list.add(dom.getBmmc());
			
			list.add(dom.getDwmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String plDz() throws Exception {
		((HyJsglSrdzService)getService()).plDz(getDomain(),getUserDomain());
		return "plDz";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyJsglSrdzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyJsglSrdzDomain) domain;
	}

	@Resource(name = "hyJsglSrdzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
