package com.cy.jcgl.action;

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
import com.cy.jcgl.domain.JcTydglDomain;
import com.cy.jcgl.service.JcTydglService;

/**
 * THE ACTION FOR 决策-托运单管理
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/jcgl/jctydgl", results = {
		@Result(name = "init", location = "/work/jcgl/jc_tydgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/jcgl/jc_tydcx_mx.jsp"),
		@Result(name = "initJcTydPcxx", location = "/work/jcgl/jc_tyd_pcxx.jsp"),
		@Result(name = "initJcSjcxClgzxx", location = "/work/jcgl/jc_clgzxx.jsp"),
		@Result(name = "initJcyfzf", location = "/work/jcgl/jc_yfzfxx.jsp"),
		@Result(name = "initWlss", location = "/work/jcgl/jc_wlssxx.jsp"),
		@Result(name = "viewWlssMx", location = "/work/jcgl/jc_wlssxx_mx.jsp"),
		@Result(name = "queryJcTydPcxx", type = "json"),
		@Result(name = "queryJcSjcxClgzxx", type = "json"),
		@Result(name = "queryJcYfZfxx", type = "json"),
		@Result(name = "queryJcWlssxx", type = "json")
		})
@SuppressWarnings("unchecked")
public class JcTydglAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcTydglDomain domain = (JcTydglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序号");
		headList.add("订单编号");
		headList.add("发货单位");
		headList.add("下单日期");
		headList.add("货物名称");
		headList.add("发货人");
		headList.add("结算标志");
		
		headList.add("货物状态");
		headList.add("始发地");
		headList.add("目的地");
		
		
		headList.add("收入");
		
		headList.add("现付");
		headList.add("到付");
		headList.add("月结");
		headList.add("回扣");
		headList.add("运输费");
		headList.add("配送费");
		headList.add("保价费");

		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("包装");
		headList.add("收货单位");
		headList.add("收货人");
		headList.add("要求发货日期");
		headList.add("要求到达日期");
		headList.add("收货方式");
		headList.add("登记人");
		headList.add("登记部门");
		headList.add("所属机构");

		heads.add(headList);

		int i = 0;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			JcTydglDomain element = (JcTydglDomain) e;
			List list = new ArrayList();
			
			if(i==0){
				list.add("合计");
				i++;
			}else{
				list.add(i+++ "");
			}
			list.add(element.getDdbh());
			list.add(element.getFhrMc());
			list.add(element.getXdrq());
			list.add(element.getHwmc());
			list.add(element.getFhrLxr());
			list.add(element.getYjWjBz());
			
			list.add(element.getHwztMc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			
			
			list.add(element.getSrHj());
			list.add(element.getSrXf());
			list.add(element.getSrDf());
			list.add(element.getSrYj());
			list.add(element.getSrHk());
			list.add(element.getSrYsf());
			list.add(element.getSrPsf());
			list.add(element.getFyDshk());
			
			list.add(element.getHwSl());
			list.add(element.getHwZl());
			list.add(element.getHwTj());
			list.add(element.getHwbz());
			list.add(element.getShrMc());
			list.add(element.getShrLxr());
			list.add(element.getYqFhrq());
			list.add(element.getYqDdrq());
			list.add(element.getShfsMc());
			list.add(element.getDjrMc());
			list.add(element.getDjJgmc());
			list.add(element.getSsJgmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, request);

		return "download";
	}
	
	public String initJcTydPcxx() throws Exception {
		
		return "initJcTydPcxx";
	}
	
	public String queryJcTydPcxx() throws Exception {
		((JcTydglService)getService()).queryJcTydPcxx((JcTydglDomain)getDomain());
		return "queryJcTydPcxx";
	}
	
	public String initJcSjcxClgzxx() throws Exception {
		return "initJcSjcxClgzxx";
	}
	
	public String initJcyfzf() throws Exception {
		return "initJcyfzf";
	}
	
	public String initWlss() throws Exception {
		return "initWlss";
	}
	
	public String queryJcWlssxx() throws Exception {
		((JcTydglService)getService()).queryJcWlssxx((JcTydglDomain)getDomain());
		return "queryJcWlssxx";
	}
	
	public String queryJcSjcxClgzxx() throws Exception {
		((JcTydglService)getService()).queryJcSjcxClgzxx((JcTydglDomain)getDomain());
		return "queryJcSjcxClgzxx";
	}
	
	public String queryJcYfZfxx() throws Exception {
		((JcTydglService)getService()).queryJcYfZfxx((JcTydglDomain)getDomain());
		return "queryJcYfZfxx";
	}
	
	public String viewWlssMx() throws Exception {
		((JcTydglService)getService()).queryWlssMx(getDomain());
		return "viewWlssMx";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new JcTydglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (JcTydglDomain) domain;
	}

	@Resource(name = "jcTydglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
