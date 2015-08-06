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
import com.cy.zygl.domain.QyFbsDjxxDomain;

/**
 * THE ACTION FOR ��ҵ-�ְ���-�Ǽ���Ϣ
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qyfbsdjxx", results = {
		@Result(name = "init", location = "/work/zygl/qyfbsdjxx.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyfbsdjxx_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyFbsDjxxAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("");
		headList.add("�ְ�������");
		headList.add("�ְ��̼��");
		headList.add("��������");
		headList.add("��ַ");
		headList.add("�绰");
		headList.add("�ʱ�");
		headList.add("������");
		headList.add("��ע");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyFbsDjxxDomain element = (QyFbsDjxxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getFbsDjxh());
			list.add(element.getFbsmc());
			list.add(element.getFbsjc());
			list.add(element.getXzqhDm());
			list.add(element.getDz());
			list.add(element.getDh());
			list.add(element.getYb());
			list.add(element.getFzr());
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
			domain = new QyFbsDjxxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyFbsDjxxDomain) domain;
	}

	@Resource(name = "qyFbsDjxxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
