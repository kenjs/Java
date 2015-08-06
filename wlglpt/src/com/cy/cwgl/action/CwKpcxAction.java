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
import com.cy.cwgl.domain.CwKpcxDomain;

/**
 * THE ACTION FOR ����-��Ʊ�Ǽ�
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwkpcx", results = {
		@Result(name = "init", location = "/work/cwgl/cwkpcx.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwkpcx_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")
		})
@SuppressWarnings("unchecked")
public class CwKpcxAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwKpcxDomain domain = (CwKpcxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ͻ�����");
		headList.add("��Ʊ����");
		headList.add("��Ʊ����");
		headList.add("��Ʊ��");
		headList.add("��Ʊ����");
		headList.add("��Ʊ���");
		headList.add("˰��");
		headList.add("˰��");
		headList.add("���ϱ�־");
		headList.add("����־");
		headList.add("���ַ�Ʊ����");
		headList.add("���ַ�Ʊ����");
		headList.add("��������");
		headList.add("�Ǽǲ���");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwKpcxDomain element = (CwKpcxDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getKhmc());
			list.add(element.getFpdm());
			list.add(element.getFphm());
			list.add(element.getKprMc());
			list.add(element.getKprq());
			list.add(element.getKpje());
			list.add(element.getSl());
			list.add(element.getSe());
			list.add(element.getZfbz());
			list.add(element.getHzbz());
			list.add(element.getLzFpdm());
			list.add(element.getLzFphm());
			list.add(element.getSsJgmc());
			list.add(element.getDjJgmc());			
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
			domain = new CwKpcxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwKpcxDomain) domain;
	}

	@Resource(name = "cwKpcxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

}
