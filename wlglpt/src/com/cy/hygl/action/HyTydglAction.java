package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyTydglDomain;
import com.cy.hygl.service.HyTydglService;

/**
 * THE ACTION FOR 货运-托运单管理
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hytydgl", results = {
		@Result(name = "init", location = "/work/hygl/hy_tydgl.jsp"),
		@Result(name = "initCy", location = "/work/hygl/hy_tydgl_cy.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_tydgl_mx.jsp"),
		@Result(name = "initMxCy", location = "/work/hygl/hy_tydgl_mx_cy.jsp"),
		@Result(name = "initMxXh", location = "/work/hygl/hy_tydgl_mx_xh.jsp"),
		@Result(name = "viewMx", location = "/work/hygl/hy_tydgl_mx_readonly.jsp"),
		@Result(name = "printView", location = "/work/hygl/hy_tydgl_print.jsp"),
		@Result(name = "printAll", location = "/work/hygl/hy_tydgl_print.jsp"),
		@Result(name = "printView4TS", location = "/work/hygl/hy_tydgl_print_ts.jsp"),
		@Result(name = "printView4SJ", location = "/work/hygl/hy_tydgl_print_sj.jsp"),
		@Result(name = "printView4XH", location = "/work/hygl/hy_tydgl_print_xh.jsp"),
		@Result(name = "printAll4TS", location = "/work/hygl/hy_tydgl_printAll_ts.jsp"),
		@Result(name = "printAll4MZ", location = "/work/hygl/hy_tydgl_printAll_mz.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "refreshHwList", type = "json"),
		@Result(name = "initHwMx", type = "json"),
		@Result(name = "initCopyMx", location = "/work/hygl/hy_tydgl_copymx.jsp"),
		@Result(name = "queryCopy", type = "json"),
		@Result(name = "saveHwMx", type = "json"),
		@Result(name = "deleteHwMx", type = "json"),
		@Result(name = "initTydFromCopy", type = "json"),
		@Result(name = "initTydFromTemplate", type = "json"),
		@Result(name = "checkSfypc", type = "json"),
		@Result(name = "doCheckDdbh", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyTydglAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyTydglDomain domain = (HyTydglDomain) getDomain();
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
			HyTydglDomain element = (HyTydglDomain) e;
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
			list.add(element.getSrBjf());
			

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
	
	public String viewMx() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().initMx(getDomain(), userDomain);
		return "viewMx";
	}
	public String initCy() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().init(getDomain(), userDomain);
		return "initCy";
	}
	public String initMxCy() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().initMx(getDomain(), userDomain);
		return "initMxCy";
	}
	public String initMxXh() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().initMx(getDomain(), userDomain);
		return "initMxXh";
	}
	public String save() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().save(getDomain(), userDomain);
		//((HyTydglService)getService()).callPHyglDdglTydglHxcl(getDomain(), getUserDomain());
		return XtglConstants.RESULT_NAME_SAVE;
	}
	
	public String refreshHwList() throws Exception {
		((HyTydglService)getService()).refreshHwList(getDomain(), getUserDomain());
		
		return "refreshHwList";
	}
	
	public String initHwMx() throws Exception {
		((HyTydglService)getService()).initHwMx(getDomain(), getUserDomain());
		
		return "initHwMx";
	}
	
	public String saveHwMx() throws Exception {
		((HyTydglService)getService()).saveHwMx(getDomain(), getUserDomain());
		
		return "saveHwMx";
	}
	
	public String deleteHwMx() throws Exception {
		((HyTydglService)getService()).deleteHwMx(getDomain(), getUserDomain());
		
		return "deleteHwMx";
	}
	
	public String initCopyMx() throws Exception {
		((HyTydglService)getService()).initCopyMx(getDomain(), getUserDomain());
		
		return "initCopyMx";
	}
	public String queryCopy() throws Exception {
		((HyTydglService)getService()).queryCopy(getDomain(), getUserDomain());
		
		return "queryCopy";
	}
	
	public String initTydFromCopy() throws Exception {
		((HyTydglService)getService()).initTydFromCopy(getDomain(), getUserDomain());
		return "initTydFromCopy";
	}
	public String printView() throws Exception {
		String qybm = getUserDomain().qybm;
		((HyTydglService)getService()).printView(getDomain(), getUserDomain());
		String forward = "printView4" + qybm.toUpperCase();//找不到会默认跳printView
		if("CY".equals(qybm)){
			forward = "printView";
		}
		return forward;
	}
	
	public String printAll() throws Exception {
		String qybm = getUserDomain().qybm;
		((HyTydglService)getService()).printView(getDomain(), getUserDomain());
		String forward = "printAll4" + qybm.toUpperCase();//找不到会默认跳printAll
		if("CY".equals(qybm)||"SJ".equals(qybm)||"XH".equals(qybm)){
			forward = "printView";
		}
		return forward;
	}
	
	public String initTydFromTemplate() throws Exception {
		((HyTydglService)getService()).initTydFromTemplate(getDomain(), getUserDomain());
		return "initTydFromTemplate";
	}
	
	public String checkSfypc() throws Exception {
		((HyTydglService)getService()).queryYpchwNumByDdDjxh(getDomain(), getUserDomain());
		return "checkSfypc";
	}
	public String doCheckDdbh() throws Exception {
		((HyTydglService)getService()).checkDdbh(getDomain(), getUserDomain());
		
		return "doCheckDdbh";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyTydglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyTydglDomain) domain;
	}

	@Resource(name = "hyTydglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
