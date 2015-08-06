package com.cy.bggl.action;

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
import com.cy.framework.util.SysDateUtil;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.bggl.domain.BgglJstxDomain;

/**
 * THE ACTION FOR �칫-��ʱͨѶ
 * @author anq
 */
 @Controller
@Scope("prototype")
@Action(value = "bggl/jstx", results = {
		@Result(name = "init", location = "/work/bggl/jstx.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/jstx_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class BgglJstxAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgglJstxDomain domain = (BgglJstxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("����");
		headList.add("������");
		headList.add("��������");
		headList.add("������");
		headList.add("���ձ�־");
		headList.add("��������");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgglJstxDomain element = (BgglJstxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getNr());
			list.add(element.getFsrCzyMc());
			list.add(SysDateUtil.format(element.getFsrq(), SysDateUtil.TIME_DATETIME_FORMAT));
			list.add(element.getCzyMc());
			list.add("Y".equals(element.getJsbz()) ? "�ѽ���" : "δ����");
			list.add(SysDateUtil.format(element.getJsrq(), SysDateUtil.TIME_DATETIME_FORMAT));

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new BgglJstxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgglJstxDomain) domain;
	}

	@Resource(name = "bgglJstxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
