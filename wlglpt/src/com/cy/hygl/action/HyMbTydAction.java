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
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.service.HyMbTydService;
import com.cy.hygl.service.HyTydglService;

/**
 * THE ACTION FOR 货运-模版-托运单
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hytydmbgl", results = {
		@Result(name = "init", location = "/work/hygl/hy_tydmbgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_tydmb_mx.jsp"),
		@Result(name = "initHwMx", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "initSaveAsTemplate", location = "/work/hygl/hy_tydgl_saveAsTemplate.jsp"),
		@Result(name = "saveAsTemplate", type = "json"),
		@Result(name = "checkTemplateName", type = "json"),
		@Result(name = "initMb4Tydgl", location = "/work/hygl/hy_tydgl_mbselect.jsp"),
		@Result(name = "queryMb4Tydgl", type = "json"),
		@Result(name = "initTydByMb", type = "json"),
		@Result(name = "deleteHwMx", type = "json"),
		@Result(name = "saveHwMx", type = "json"),
		@Result(name = "refreshHwList", type = "json"),
		@Result(name = "checkTemplateName", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyMbTydAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyMbTydDomain domain = (HyMbTydDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("客户名称");
		headList.add("模版名称");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("发货地址");
		headList.add("收货人");
		headList.add("收货地址");
		headList.add("登记部门");
		headList.add("所属机构");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyMbTydDomain element = (HyMbTydDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getFhrMc());
			list.add(element.getMbmc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getFhrDz());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getDjJgmc());
			list.add(element.getSsJgmc());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String refreshHwList() throws Exception {
		((HyMbTydService)getService()).refreshHwList(getDomain(), getUserDomain());
		
		return "refreshHwList";
	}
	
	public String initHwMx() throws Exception {
		((HyMbTydService)getService()).initHwMx(getDomain(), getUserDomain());
		
		return "initHwMx";
	}
	
	public String saveHwMx() throws Exception {
		((HyMbTydService)getService()).saveHwMx(getDomain(), getUserDomain());
		
		return "saveHwMx";
	}
	
	public String deleteHwMx() throws Exception {
		((HyMbTydService)getService()).deleteHwMx(getDomain(), getUserDomain());
		
		return "deleteHwMx";
	}
	
	public String initSaveAsTemplate() throws Exception {
		
		return "initSaveAsTemplate";
	}
	
	public String saveAsTemplate() throws Exception {
		((HyMbTydService)getService()).saveAsTemplate(getDomain(), getUserDomain());
		return "saveAsTemplate";
	}
	
	public String checkTemplateName() throws Exception {
		((HyMbTydService)getService()).checkTemplateName(getDomain(), getUserDomain());
		return "checkTemplateName";
	}
	
	public String initMb4Tydgl() throws Exception {
		((HyMbTydService)getService()).initMb4Tydgl(getDomain(), getUserDomain());
		return "initMb4Tydgl";
	}
	
	public String queryMb4Tydgl() throws Exception {
		((HyMbTydService)getService()).queryMb4Tydgl(getDomain(), getUserDomain());
		return "queryMb4Tydgl";
	}
	
	public String initTydByMb() throws Exception {
		((HyMbTydService)getService()).initTydByMb(getDomain(), getUserDomain());
		return "initTydByMb";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyMbTydDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyMbTydDomain) domain;
	}

	@Resource(name = "hyMbTydServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
