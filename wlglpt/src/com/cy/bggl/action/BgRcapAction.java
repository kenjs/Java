package com.cy.bggl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.bggl.domain.BgRcapDomain;
import com.cy.bggl.service.BgRcapService;
import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR �칫-�ճ̰���
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "bggl/bgrcap", results = {
		@Result(name = "init", location = "/work/bggl/bgrcap.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/bgrcap_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "queryByDate", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class BgRcapAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgRcapDomain domain = (BgRcapDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�칫�Ǽ����(SEQ_BG_DJXH)");
		headList.add("����Ա�Ǽ����");
		headList.add("����(YYYY-MM-DD)");
		headList.add("����");
		headList.add("���ŷ������");
		headList.add("�鿴��־(Y/N)");
		headList.add("��Ч��־(Y/N)");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgRcapDomain element = (BgRcapDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getBgDjxh());
			list.add(element.getCzyDjxh());
			list.add(element.getRq());
			list.add(element.getNr());
			list.add(element.getDxFsxh());
			list.add(element.getCkbz());
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
	
	public String queryByDate() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgRcapDomain domain = (BgRcapDomain)getDomain();
		BgRcapService service = (BgRcapService)getService();
		service.queryForDate(domain, userDomain);
		return "queryByDate";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new BgRcapDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgRcapDomain) domain;
	}

	@Resource(name = "bgRcapServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
