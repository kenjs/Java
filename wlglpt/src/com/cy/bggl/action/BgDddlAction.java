package com.cy.bggl.action;

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
import com.cy.bggl.domain.BgDddlDomain;

/**
 * THE ACTION FOR �칫-�����¼
 * @author HaoY
 */
 @Controller
@Scope("prototype")
@Action(value = "/bggl/bgdddl", results = {
		@Result(name = "init", location = "/work/bggl/bgdddl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/bgdddl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class BgDddlAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgDddlDomain domain = (BgDddlDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("����");
		headList.add("������ַ");
		headList.add("��¼��ʽ");
		headList.add("�¼�����");

		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgDddlDomain element = (BgDddlDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getMc());
			list.add(element.getUrl());
			list.add(element.getDlfsMc());
			list.add(element.getXjgxStr());
			
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
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new BgDddlDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgDddlDomain) domain;
	}

	@Resource(name = "bgDddlServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
