package com.cy.xtgl.action;

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
import com.cy.xtgl.domain.YhjswhDomain;

/**
 * 用户角色维护
 * 
 * @author HaoY
 * @since 
 */
@Controller
@Scope("prototype")
@Action(value = "/xtgl/yhjswh", results = { 
		@Result(name = "init", location = "/work/xtgl/yhjswh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "queryMx", location = "/work/xtgl/yhjswh_mx.jsp"), 
		@Result(name = "saveMx", type = "json") })
public class YhjswhAction extends ExtendAction {
	private static final long	serialVersionUID	= 1L;
	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		YhjswhDomain domain = (YhjswhDomain)getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("用户名称");
		headList.add("角色");
		headList.add("部门");
		headList.add("岗位");
		
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			YhjswhDomain element = (YhjswhDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getMc());
			list.add(element.getJsJc());
			list.add(element.getBmMc());
			list.add(element.getGwMc());
			
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}

	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new YhjswhDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (YhjswhDomain) domain;
	}

	@Resource(name = "yhjswhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
