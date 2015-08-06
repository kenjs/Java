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
import com.cy.zygl.domain.QyXlwhDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;


/**
 * THE ACTION FOR ��ҵ-��·ά��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "zygl/qyxlwh", results = {
		@Result(name = "init", location = "/work/zygl/qyxlwh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyxlwh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyXlwhAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyXlwhDomain domain = (QyXlwhDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("��������");
		headList.add("ʼ����_������������");
		headList.add("Ŀ�ĵ�_������������");
		headList.add("�����");
		headList.add("�ﵽ����");
		headList.add("��Ч��־(Y/N)");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyXlwhDomain element = (QyXlwhDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getSsJgbm());
			list.add(element.getSfdXzqhDm());
			list.add(element.getMddXzqhDm());
			list.add(element.getLcs());
			list.add(element.getDdts());
			list.add(element.getYxbz());
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
			domain = new QyXlwhDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyXlwhDomain) domain;
	}

	@Resource(name = "qyXlwhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
