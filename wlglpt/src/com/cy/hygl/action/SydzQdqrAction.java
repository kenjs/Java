package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;
import com.cy.hygl.service.XyjsSrdzQdService;

/**
 * THE ACTION FOR ���ν���-�������-�嵥
 * @author HJH 
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/sydzqdqr")
@SuppressWarnings("unchecked")
public class SydzQdqrAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�嵥����");
		headList.add("���ε�λ");
		headList.add("�ϼƽ��");
		headList.add("���˽��");
		headList.add("������");
		
		headList.add("״̬");
		headList.add("���");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			XyjsSrdzQdDomain element = (XyjsSrdzQdDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getQdmc());
			list.add(element.getSsJgmc());
			list.add(element.getHeJe());
			list.add(element.getDzJe());
			list.add(element.getDzcyJe());
			
			list.add(element.getZtStr());
			list.add(element.getBz());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XyjsSrdzQdDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XyjsSrdzQdDomain) domain;
	}

	@Resource(name = "sydzQdqrServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
