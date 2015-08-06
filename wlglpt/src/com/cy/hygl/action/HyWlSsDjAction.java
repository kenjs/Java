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
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjService;


/**
 * THE ACTION FOR ����-ģ��-������ʧ����ϸ����ѯ��������ʱ�鿴��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/wlssdj", results = {
		@Result(name = "init", location = "/work/hygl/hywlssck.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initSsMx", location = "/work/hygl/hy_wlssdj_ssmx.jsp"),
		@Result(name = "querySsMx", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_wlssdj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "getHw", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteWlssdjMx", type = "json"),
		@Result(name = "checkWlDj", type = "json"),
		@Result(name = "toLook", location = "/work/hygl/hy_wlssdjgl_mx.jsp"),
		@Result(name = "checkTemplateName", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyWlSsDjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyMbTydDomain domain = (HyMbTydDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ͻ�����");
		headList.add("ģ������");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("������ַ");
		headList.add("�ջ���");
		headList.add("�ջ���ַ");
		headList.add("�Ǽǲ���");
		headList.add("��������");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyMbTydDomain element = (HyMbTydDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getFhrMc());
			list.add(element.getMbmc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getFhrDz());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getDjJgmc());
			list.add(element.getSsJgmc());
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
	
	public String deleteWlssdjMx() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).deleteWlssdjMx(domain,getUserDomain());
		return "deleteWlssdjMx";
	}
	
	public String getHw() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).getHw(domain,getUserDomain());
		return "getHw";
	}
	
	public String initSsMx() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).initSsMx(domain,getUserDomain());
		return "initSsMx";
	}
	public String querySsMx() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).querySsMx(domain,getUserDomain());
		return "querySsMx";
	}
	public String checkWlDj() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).checkWlDj(domain,getUserDomain());
		return "checkWlDj";
	}
	
	public String toLook() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).toLook(domain,getUserDomain());
		return "toLook";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyZyglFydjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyMbTydDomain) domain;
	}

	@Resource(name = "hyWlSsDjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
