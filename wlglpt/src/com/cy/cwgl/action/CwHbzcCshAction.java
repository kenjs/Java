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
import com.cy.xtgl.service.JsqxwhService;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwHbzcCshDomain;
import com.cy.cwgl.service.CwHbzcCshService;
import com.cy.dzgl.domain.QySpwsXmflDomain;

/**
 * THE ACTION FOR 财务-货币资产初始化
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwhbcsh", results = {
		@Result(name = "init", location = "/work/cwgl/cwhbzccsh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwhbzccsh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveDisable", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "saveCheck", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwHbzcCshAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	
	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwHbzcCshDomain domain = (CwHbzcCshDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序号");
		//	headList.add("状态");
			headList.add("所属机构");
			headList.add("资产分类名称");
			headList.add("银行名称");
			headList.add("用户名");
			headList.add("账号");
			headList.add("初始金额");
			headList.add("创建人");
			headList.add("创建日期");
			headList.add("修改人");
			headList.add("修改日期");

		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			CwHbzcCshDomain element = (CwHbzcCshDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			//list.add(element.getQyStr());
			list.add(element.getSjMc());
			list.add(element.getFlMc());
			list.add(element.getYhmc());
			list.add(element.getHm());
			list.add(element.getZh());
			list.add(element.getCsje());
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

	
	public String checkSaveBefore() throws Exception{
		CwHbzcCshDomain domain = (CwHbzcCshDomain) getDomain();
		CwHbzcCshService service = (CwHbzcCshService) getService();
		service.checkYhMc(domain);
		return "saveCheck";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwHbzcCshDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwHbzcCshDomain) domain;
	}

	@Resource(name = "cwHbzcCshServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
