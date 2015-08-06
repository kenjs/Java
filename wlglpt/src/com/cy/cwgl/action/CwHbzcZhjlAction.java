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
 * THE ACTION FOR ����-�����ʲ�-ת����¼
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
		
		headList.add("���");
		headList.add("ԭ���");
		headList.add("ԭ��������");
		headList.add("ԭ����");
		headList.add("ԭ�˺�");
		headList.add("Ŀ�����");
		headList.add("Ŀ����������");
		headList.add("Ŀ�껧��");
		headList.add("Ŀ���˺Ž��");
		headList.add("ת�����");
		headList.add("ƾ֤��");
		headList.add("������");
		headList.add("����");
		headList.add("˵��");

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
