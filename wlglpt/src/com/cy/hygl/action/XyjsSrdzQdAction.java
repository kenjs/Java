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
import com.cy.hygl.domain.XyjsSrdzQdDomain;
import com.cy.hygl.service.XyjsSrdzQdService;

/**
 * THE ACTION FOR 下游结算-收入对帐-清单
 * @author HJH 
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/xyjssrdzqd", results = {
		@Result(name = "init", location = "/work/hygl/xyjssrdzqd.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "querySydzqdConf", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/xyjssrdzqd_mx.jsp"),
		@Result(name = "initSydzqdQrMx", location = "/work/hygl/sydzqdqr_mx.jsp"),
		@Result(name = "viewQrMx", location = "/work/hygl/xyjssrdzqd_mx_readonly.jsp"),
		@Result(name = "saveSydzqdqr", type = "json"),
		@Result(name = "initQueryJsxxMx", location = "/work/hygl/xyjssrdzqd_mx_queryjsxx.jsp"),
		@Result(name = "initconfirm", location = "/work/hygl/syjsdzqdqr.jsp"),
		@Result(name = "queryJsxxMx", type = "json"),
		@Result(name = "queryQdMx", type = "json"),
		@Result(name = "saveJsmx", type = "json"),
		@Result(name = "deleteQdmx", type = "json"),
		@Result(name = "queryHjjeCalculate", type = "json"),
		@Result(name = "sendDzqd", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class XyjsSrdzQdAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("清单名称");
		headList.add("下游名称");
		headList.add("合计金额");
		headList.add("对账金额");
		headList.add("差异金额");
		headList.add("登记人");
		headList.add("登记日期");
		headList.add("登记部门");
		headList.add("所属机构");
		headList.add("状态");
		headList.add("下游意见");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			XyjsSrdzQdDomain element = (XyjsSrdzQdDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getQdmc());
			list.add(element.getXyMc());
			list.add(element.getHeJe());
			list.add(element.getDzJe());
			list.add(element.getDzcyJe());
			list.add(element.getDjrMc());
			list.add(element.getDjrq());
			list.add(element.getDjJgmc());
			list.add(element.getSsJgmc());
			list.add(element.getZtStr());
			list.add(element.getBz());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String initQueryJsxxMx() throws Exception {
		//((XyjsSrdzQdService)getService()).initQueryJsxxMx(getDomain(), getUserDomain());
		return "initQueryJsxxMx";
	}
	
	public String queryJsxxMx() throws Exception {
		((XyjsSrdzQdService)getService()).queryJsxxMx(getDomain(), getUserDomain());
		return "queryJsxxMx";
	}
	
	public String saveJsmx() throws Exception {
		((XyjsSrdzQdService)getService()).saveJsmx(getDomain(), getUserDomain());
		return "saveJsmx";
	}
	
	public String queryQdMx() throws Exception {
		((XyjsSrdzQdService)getService()).queryQdMx(getDomain(), getUserDomain());
		return "queryQdMx";
	}
	
	public String deleteQdmx() throws Exception {
		((XyjsSrdzQdService)getService()).deleteQdmx(getDomain(), getUserDomain());
		return "deleteQdmx";
	}
	
	public String queryHjjeCalculate() throws Exception {
		((XyjsSrdzQdService)getService()).queryHjjeCalculate(getDomain(), getUserDomain());
		return "queryHjjeCalculate";
	}
	
	public String sendDzqd() throws Exception {
		((XyjsSrdzQdService)getService()).sendDzqd(getDomain(), getUserDomain());
		return "sendDzqd";
	}
	
	public String initconfirm() throws Exception {
		((XyjsSrdzQdService)getService()).initconfirm(getDomain(), getUserDomain());
		return "initconfirm";
	}
	
	public String querySydzqdConf() throws Exception {
		((XyjsSrdzQdService)getService()).querySydzqdConf(getDomain(), getUserDomain());
		return "querySydzqdConf";
	}
	
	public String initSydzqdQrMx() throws Exception {
		((XyjsSrdzQdService)getService()).initSydzqdQrMx(getDomain());
		return "initSydzqdQrMx";
	}
	
	public String saveSydzqdqr() throws Exception {
		((XyjsSrdzQdService)getService()).saveSydzqdqr(getDomain());
		return "saveSydzqdqr";
	}
	
	public String viewQrMx() throws Exception {
		((XyjsSrdzQdService)getService()).viewQrMx(getDomain());
		return "viewQrMx";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XyjsSrdzQdDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XyjsSrdzQdDomain) domain;
	}

	@Resource(name = "xyjsSrdzQdServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
