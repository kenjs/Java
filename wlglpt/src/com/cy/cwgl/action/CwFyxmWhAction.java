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
import com.cy.cwgl.domain.CwFyxmWhDomain;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR ����-������Ŀ-ά��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/fyxmwh", results = {
		@Result(name = "init", location = "/work/cwgl/cwfyxm.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwfyxm_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwFyxmWhAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwFyxmWhDomain domain = (CwFyxmWhDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("��������");
		headList.add("������Ŀ����");
		headList.add("�������");
		headList.add("��Ŀ����");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwFyxmWhDomain element = (CwFyxmWhDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getJgMc());
			list.add(element.getFyxmMc());
			list.add(element.getLbMc());
			list.add(element.getXmFl());
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
			domain = new CwFyxmWhDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwFyxmWhDomain) domain;
	}

	@Resource(name = "cwFyxmWhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
