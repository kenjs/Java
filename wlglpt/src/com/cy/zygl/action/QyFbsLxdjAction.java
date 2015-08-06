package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyFbsLxdjDomain;

/**
 * THE ACTION FOR ��ҵ-�ְ���-·�ߵǼ�
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qyfbslxdj", results = {
		@Result(name = "init", location = "/work/zygl/qyfbslxdj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyfbslxdj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyFbsLxdjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("��·����");
		headList.add("��·���");
		headList.add("ʼ��վ");
		headList.add("Ŀ�ĵ�");
		headList.add("��ע");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyFbsLxdjDomain element = (QyFbsLxdjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getLxmc());
			list.add(element.getLxjc());
			list.add(element.getSfdXzqhMc());
			list.add(element.getMddXzqhMc());
			list.add(element.getBz());
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
			domain = new QyFbsLxdjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyFbsLxdjDomain) domain;
	}

	@Resource(name = "qyFbsLxdjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
