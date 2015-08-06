package com.cy.cwgl.action;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.cwgl.domain.CwFyxmWhDomain;
import com.cy.cwgl.service.CwFyBxSqService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR ����-������Ŀ-ά��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwfybxsq", results = {
		@Result(name = "init", location = "/work/cwgl/cw_fybxsq.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "deleteMx", type = "json"),
		@Result(name = "checkXmfl", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cw_fybxsq_mx.jsp"),
		@Result(name = "onView", location = "/work/cwgl/cw_fybxsq_ck.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwFyBxSqAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwFybxsqDomain domain = (CwFybxsqDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("���");
		headList.add("���ñ����ܼ�");
		headList.add("���ü��˵�λ");
		headList.add("ֻ��֧����λ");
		headList.add("������");
		headList.add("��������");
		headList.add("���벿��");
		headList.add("���뵥λ");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwFybxsqDomain element = (CwFybxsqDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getSpztMc());
			list.add(element.getFybxje());
			list.add(element.getJzdw());
			list.add(element.getJfdw());
			list.add(element.getSqr());
			list.add(element.getSqrq());
			list.add(element.getSqbm());
			list.add(element.getSqdw());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String deleteMx() throws Exception
	{
		CwFybxsqDomain domain=(CwFybxsqDomain)getDomain();
		CwFyBxSqService service=(CwFyBxSqService)getService();
		service.deleteMx(domain,getUserDomain());
		return "deleteMx";
	}
	
	public String checkXmfl() throws Exception
	{
		CwFybxsqDomain domain=(CwFybxsqDomain)getDomain();
		CwFyBxSqService service=(CwFyBxSqService)getService();
		service.checkXmfl(domain);
		return "checkXmfl";
	}
	
	public String onView() throws Exception
	{
		CwFybxsqDomain domain=(CwFybxsqDomain)getDomain();
		CwFyBxSqService service=(CwFyBxSqService)getService();
		service.onView(domain,getUserDomain());
		return "onView";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwFybxsqDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwFybxsqDomain) domain;
	}

	@Resource(name = "cwFyBxSqServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
