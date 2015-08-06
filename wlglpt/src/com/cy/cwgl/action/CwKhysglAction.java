package com.cy.cwgl.action;



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
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.cwgl.domain.CwKhysglDomain;
import com.cy.cwgl.service.CwFyBxSqService;
import com.cy.cwgl.service.CwKhysglService;


/**
 * THE ACTION FOR 财务-客户预收收入
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "cwgl/khyslr", results = {
		@Result(name = "init", location = "/work/cwgl/cwkhysgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwkhysgl_mx.jsp"),
		@Result(name = "onLook", location = "/work/cwgl/cwkhysgl_ck.jsp"),
		@Result(name = "deleteKhMx", type = "json"),
		@Result(name = "checkZcFl", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwKhysglAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwKhysglDomain domain = (CwKhysglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("客户名称");
		headList.add("金额");
		headList.add("登记人");
		headList.add("登记日期");
		headList.add("登记部门");
		headList.add("所属机构");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwKhysglDomain element = (CwKhysglDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getKhMc());
			list.add(element.getJe()==null?"":Double.parseDouble(element.getJe()));
			list.add(element.getDjrMc());
			list.add(element.getDjrq());
			list.add(element.getDjBm());
			list.add(element.getSsJgmc());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String onLook() throws Exception
	{
		CwKhysglDomain domain=(CwKhysglDomain)getDomain();
		CwKhysglService service=(CwKhysglService)getService();
		service.onLook(domain);
		return "onLook";
	}
	public String deleteKhMx()throws Exception{
		CwKhysglDomain domain=(CwKhysglDomain)getDomain();
		CwKhysglService service=(CwKhysglService)getService();
		service.deleteMx(domain, getUserDomain());
		return "deleteKhMx";
	}
	
	public String checkZcFl()throws Exception{
		CwKhysglDomain domain=(CwKhysglDomain)getDomain();
		CwKhysglService service=(CwKhysglService)getService();
		service.checkZcFl(domain, getUserDomain());
		return "checkZcFl";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwKhysglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwKhysglDomain) domain;
	}

	@Resource(name = "cwKhysglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
