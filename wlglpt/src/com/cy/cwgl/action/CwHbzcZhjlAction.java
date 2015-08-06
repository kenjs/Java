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
import com.cy.cwgl.domain.CwHbzcZhjlDomain;
import com.cy.cwgl.service.CwHbzcZhjlService;

/**
 * THE ACTION FOR 财务-货币资产-转换记录
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwhbzczhjl", results = {
		@Result(name = "init", location = "/work/cwgl/cwhbzczhjl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwhbzczhjl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "initNewYe", type = "json"),
		@Result(name = "initOldYe", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwHbzcZhjlAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("原类别");
		headList.add("原银行名称");
		headList.add("原户名");
		headList.add("原账号");
		headList.add("目标类别");
		headList.add("目标银行名称");
		headList.add("目标户名");
		headList.add("目标账号金额");
		headList.add("转换金额");
		headList.add("凭证号");
		headList.add("经办人");
		headList.add("日期");
		headList.add("说明");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwHbzcZhjlDomain element = (CwHbzcZhjlDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getOldZcflMc());
			list.add(element.getOldYhCshMc());
			list.add(element.getOldHm());
			list.add(element.getOldZh());
			list.add(element.getNewZcflMc());
			list.add(element.getNewYhCshMc());
			list.add(element.getNewHm());
			list.add(element.getNewYe());
			list.add(element.getZhje());
			list.add(element.getPzh());
			list.add(element.getJbrCzyMc());
			list.add(element.getDjrq());
			list.add(element.getSm());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String initOldYe() throws Exception {
		((CwHbzcZhjlService)getService()).initOldYe(getDomain());
		return "initOldYe";
	}
	
	public String initNewYe() throws Exception {
		((CwHbzcZhjlService)getService()).initNewYe(getDomain());
		return "initNewYe";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwHbzcZhjlDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwHbzcZhjlDomain) domain;
	}

	@Resource(name = "cwHbzcZhjlServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
