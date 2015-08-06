package com.cy.hygl.action;

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
import com.cy.hygl.domain.XyjsSrdzDomain;
import com.cy.hygl.service.XyjsSrdzService;

/**
 * THE ACTION FOR ���ν���-�������
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/xyjssrdz", results = {
		@Result(name = "init", location = "/work/hygl/xyjssrdz.jsp"),
		@Result(name = "queryDzqdList", type = "json"),
		@Result(name = "query", type = "json"),
		@Result(name = "savePldz", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/xyjssrdz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class XyjsSrdzAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		XyjsSrdzDomain domain = (XyjsSrdzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		if ("1".equals(domain.getFylbDm())) {
			headList.add("���ͷ�");
		}else if ("2".equals(domain.getFylbDm())) {
			headList.add("������");
		}else {
			headList.add("���ջ���");
		}
		
		headList.add("���˽��");
		headList.add("����");
		headList.add("���˱�ע");
		headList.add("�������");
		headList.add("�µ�����");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("��������");
		headList.add("����");
		headList.add("����");
		headList.add("���");
		headList.add("������");
		headList.add("�ջ���");
		headList.add("�ջ���ַ");
		headList.add("���ε�λ");
		headList.add("���˵�λ");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			XyjsSrdzDomain element = (XyjsSrdzDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getJsJe());
			list.add(element.getDzje());
			list.add(element.getDzcyje());
			list.add(element.getBz());
			list.add(element.getDdbh());
			list.add(element.getXdrq());
			list.add(element.getSfdXzqhMc());
			list.add(element.getMddXzqhMc());
			list.add(element.getHwmc());
			list.add(element.getHwSl());
			list.add(element.getHwZl());
			list.add(element.getHwTj());
			list.add(element.getFhrLxr());
			list.add(element.getShrLxr());
			list.add(element.getShrDz());
			list.add(element.getXyMc());
			list.add(element.getSsJgmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String queryDzqdList() throws Exception {
		((XyjsSrdzService)getService()).queryDzqdList(getDomain(), getUserDomain());
		return "queryDzqdList";
	}
	
	public String savePldz() throws Exception {
		((XyjsSrdzService)getService()).savePldz(getDomain(), getUserDomain());
		return "savePldz";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XyjsSrdzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XyjsSrdzDomain) domain;
	}

	@Resource(name = "xyjsSrdzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
