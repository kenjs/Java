package com.cy.cwgl.action;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.cwgl.service.CwYsyfSrdjService;

import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * THE ACTION FOR 财务-收入登记
 * @author HCM
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "cwgl/cwysyfsrdj",results = {
		@Result(name = "init", location = "/work/cwgl/cw_ysyf_srdj.jsp"),		
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cw_ysyf_srdj_mx.jsp"),
		@Result(name = "checkZfFs", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "cancle", type = "json"),
		@Result(name = "queryXl", type = "json"),
		@Result(name = "plDj", type = "json"),
		@Result(name = "save", type = "json")		
})
public class CwYsyfSrdjAction extends ExtendAction {
	/**
	 * 取结算方名称下拉
	 * @return
	 * @throws Exception
	 */
	public String queryXl() throws Exception {
		((CwYsyfSrdjService)this.getService()).getMcList(domain);
		return "queryXl";
	}
	/**
	 * 下载功能
	 */
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();

		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("状态");		
		headList.add("结算方");
		headList.add("名称");
		headList.add("金额");
		headList.add("已收");
		headList.add("未收");
		headList.add("类别");
		headList.add("项目");
		headList.add("来源");
		headList.add("产生日期");
		headList.add("说明");
		headList.add("登记部门");
		headList.add("所属单位");
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwYsyfSrdjDomain element =  (CwYsyfSrdjDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getYsyfztMc());
			list.add(element.getYfjsfMc());
			list.add(element.getYfjsfDjmc());
			list.add(element.getYsfJe());
			list.add(element.getYisfJe());
			list.add(element.getWsfJe());
			list.add(element.getKmdlMc());
			list.add(element.getKmxlMc());
			list.add(element.getYsyflyMc());
			list.add(element.getCsrq());
			list.add(element.getSm());
			list.add(element.getSsJgmc());
			list.add(element.getDjJgmc());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String checkZfFs() throws Exception{
		CwYsyfSrdjDomain domain=(CwYsyfSrdjDomain)getDomain();
		CwYsyfSrdjService service=(CwYsyfSrdjService)getService();
		service.checkZfFs(domain);
		return "checkZfFs";
	}
	public String plDj() throws Exception{
		CwYsyfSrdjService service=(CwYsyfSrdjService)getService();
		service.plDj(domain,getUserDomain());
		return "plDj";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwYsyfSrdjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwYsyfSrdjDomain) domain;
	}

	@Resource(name = "cwYsyfSrdjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
	
	public void cancle() throws Exception{
		((CwYsyfSrdjService)getService()).doCancle(domain,getUserDomain());
	}
}
