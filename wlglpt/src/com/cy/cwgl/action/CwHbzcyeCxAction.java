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
import com.cy.cwgl.domain.CwHbzcyeCxDomain;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * THE ACTION FOR 财务-货币资产余额查询
 * @author HCM
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "cwgl/cwhbzcyecx",results = {
		@Result(name = "init", location = "/work/cwgl/cwhbzcyecx.jsp"),
		@Result(name = "initMx", location = "/work/cwgl/cwhbzcyecx_mx.jsp"),		
		@Result(name = "query", type = "json")})
public class CwHbzcyeCxAction extends ExtendAction {
	/**
	 * 下载功能
	 */
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("类别");
		headList.add("余额");
		headList.add("银行名称");
		headList.add("户名");
		headList.add("账号");
		
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwHbzcyeCxDomain element =  (CwHbzcyeCxDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getZcflMc());
			list.add(element.getJe());
			list.add(element.getYhmc());
			list.add(element.getHm());
			list.add(element.getZh());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwHbzcyeCxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwHbzcyeCxDomain) domain;
	}

	@Resource(name = "cwHbzcyeCxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
