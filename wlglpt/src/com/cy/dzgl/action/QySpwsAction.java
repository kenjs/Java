package com.cy.dzgl.action;

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
import com.cy.dzgl.domain.QySpwsDomain;

/**
 * THE ACTION FOR ��ҵ-��������
 * @author HaoY
 */
 @Controller
@Scope("prototype")
@Action(value = "/dzgl/qyspws", results = {
		@Result(name = "init", location = "/work/dzgl/qyspws.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "saveDisable", type = "json")})
@SuppressWarnings("unchecked")
public class QySpwsAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QySpwsDomain domain = (QySpwsDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�������");
		headList.add("��������");
		headList.add("������");
		headList.add("��������ģʽ");
		headList.add("ҵ�����");
		headList.add("ҵ�񻷽�");
		headList.add("��λ");
		headList.add("��Ŀ����");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");
		headList.add("˵��");
		
		
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QySpwsDomain element = (QySpwsDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getWsDm());
			list.add(element.getWsMc());
			list.add(element.getWsJc());
			list.add(element.getWsspmsMc());
			list.add(element.getYwflMc());
			list.add(element.getYwhjMc());
			list.add(element.getDwMc());
			list.add(element.getFlbz());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());
			list.add(element.getSm());
			
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QySpwsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QySpwsDomain) domain;
	}

	@Resource(name = "qySpwsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
