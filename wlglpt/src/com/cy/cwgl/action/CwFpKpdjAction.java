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
import com.cy.cwgl.domain.CwFpKpdjDomain;

/**
 * THE ACTION FOR ����-��Ʊ�Ǽ�
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwfpkpdj", results = {
		@Result(name = "init", location = "/work/cwgl/cwfpkpdj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwfpkpdj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")
		})
@SuppressWarnings("unchecked")
public class CwFpKpdjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwFpKpdjDomain domain = (CwFpKpdjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("���");
//			headList.add("��Ʊ����Ǽ����");
//			headList.add("�ͻ��Ǽ����");
			headList.add("�ͻ�����");
			headList.add("���뿪Ʊ���ϼ�");
			headList.add("���뿪Ʊ����");
			headList.add("��Ʊ���߽��");
			headList.add("������");
			headList.add("���벿��");
			headList.add("���벿��");
			headList.add("����ʱ��");
			headList.add("��ע˵��");

		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			CwFpKpdjDomain element = (CwFpKpdjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
//			list.add(element.getKpsqDjxh());
//			list.add(element.getKhDjxh());
			list.add(element.getKhmc());
			list.add(element.getSqKpjeHj()==null?"":Double.parseDouble(element.getSqKpjeHj()));
			list.add(element.getSqKprq());
			list.add(element.getFpkjje()==null?"":Double.parseDouble(element.getFpkjje()));
			list.add(element.getSqrMc());
			list.add(element.getDwMc());
			list.add(element.getBmMc());
			list.add(element.getDjrq());
			list.add(element.getBzsm());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}

	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwFpKpdjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwFpKpdjDomain) domain;
	}

	@Resource(name = "cwFpKpdjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

}
