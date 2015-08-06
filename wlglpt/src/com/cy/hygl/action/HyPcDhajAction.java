package com.cy.hygl.action;

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
import com.cy.hygl.domain.HyPcDhajDomain;

/**
 * THE ACTION FOR ����-�ɳ�-�绰����
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hypcdhaj", results = {
		@Result(name = "init", location = "/work/hygl/hy_pcdhaj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_pcdhaj_mx.jsp"),
		@Result(name = "save", location = "/work/hygl/hy_pcdhaj_mx.jsp"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyPcDhajAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcDhajDomain domain = (HyPcDhajDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("״̬");
		headList.add("�������");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("��������");
		headList.add("��������");
		headList.add("�ҳ�����");
		headList.add("˾������");
		headList.add("�ֻ�����");
		headList.add("������ϵ�绰");
		headList.add("�ɳ���");
		headList.add("������λ");
		headList.add("��������");
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPcDhajDomain element = (HyPcDhajDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getZt());
			list.add(element.getAjcs());
			list.add(element.getPcdh());
			list.add(element.getPcrq());
			list.add(element.getCzXm());
			list.add(element.getClhm());
			list.add(element.getGchm());
			list.add(element.getSjXm());
			list.add(element.getSjSjHm());
			list.add(element.getQtLxDh());
			list.add(element.getPcrCzyMc());
			list.add(element.getSjMc());
			list.add(element.getBmMc());
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcDhajDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPcDhajDomain) domain;
	}

	@Resource(name = "hyPcDhajServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
