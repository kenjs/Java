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
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.domain.JsSrdzQdDomain;
import com.cy.hygl.service.JsSrdzQdService;

/**
 * THE ACTION FOR 结算-收入对帐-清单
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/jssrdzqd", results = {
		@Result(name = "init", location = "/work/hygl/jssrdzqd.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/jssrdzqd_mx.jsp"),
		@Result(name = "viewMx", location = "/work/hygl/jssrdzqd_view.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "queryMxInit", location = "/work/hygl/jssrdzqd_mx_query.jsp"),
		@Result(name = "queryFydjInit", location = "/work/hygl/jssrdzqd_mx_queryFydj.jsp"),
		@Result(name = "queryMx", location = "/work/hygl/jssrdzqd_mx_query.jsp"),
		@Result(name = "queryFydjList", location = "/work/hygl/jssrdzqd_mx_queryFydj.jsp"),
		@Result(name = "saveMx", type = "json"),
		@Result(name = "saveFydjMx", type = "json"),
		@Result(name = "saveWlssMx", type = "json"),
		@Result(name = "deleteMx", type = "json"),
		@Result(name = "delete", type = "json")
		})
@SuppressWarnings("unchecked")
public class JsSrdzQdAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	public String queryMxInit() throws Exception{
		JsSrdzQdDomain domain = (JsSrdzQdDomain) getDomain();
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
		return "queryMxInit";
	}
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JsSrdzQdDomain domain = (JsSrdzQdDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("来源");
		headList.add("对账金额");
		headList.add("未结");
		headList.add("差异金额");
		headList.add("结果");
		headList.add("订单编号");
		headList.add("下单日期");
		headList.add("货物名称");
		headList.add("包装");
		headList.add("回单编号");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			JsSrdzQdDomain element = (JsSrdzQdDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			if("1".equals(element.getYwLydm())){
				list.add("收入对账");
			}else if("2".equals(element.getYwLydm())){
				list.add("费用登记");
			} else {
				list.add("物流损失");
			}
			list.add(element.getDzje() == null ? " " : Double.parseDouble(element.getDzje()));
			list.add(element.getJsWj());
			list.add(element.getDzcyje());
			if("Y".equals(element.getDzcybz())){
				list.add("有差异");
			}else {
				list.add("无差异");
			}
			list.add(element.getDdbh());
			list.add(element.getXdrq());
			list.add(element.getHwmc());
			list.add(element.getBz());
			list.add(element.getHdbh());
			list.add(element.getSfd());
			list.add(element.getMdd());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getTj());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, request);

		return "download";
	}
	
	public String queryFydjInit() throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) getDomain();
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
		return "queryFydjInit";
	}
	
	public String queryFydjList() throws Exception {
		((JsSrdzQdService)getService()).queryFydjList(getDomain(), getUserDomain());
		return "queryFydjList";
	}
	
	public String saveFydjMx() throws Exception {
		((JsSrdzQdService)getService()).saveFydjMx(getDomain(), getUserDomain());
		return "saveFydjMx";
	}
	
	public String saveWlssMx() throws Exception {
		((JsSrdzQdService)getService()).saveWlssMx(getDomain(), getUserDomain());
		return "saveWlssMx";
	}
	
	public String viewMx() throws Exception {
		((JsSrdzQdService)getService()).viewDzQdMx(getDomain(), getUserDomain());
		return "viewMx";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new JsSrdzQdDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (JsSrdzQdDomain) domain;
	}

	@Resource(name = "jsSrdzQdServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
