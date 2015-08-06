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
import com.cy.hygl.domain.HyClgzDomain;
import com.cy.hygl.service.HyClgzService;

/**
 * THE ACTION FOR ��������
 * @author HCM
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hyclgz", results = {
		@Result(name = "init", location = "/work/hygl/hy_clgz.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_clgz_mx.jsp"),
		@Result(name = "showSm", location = "/work/hygl/hy_clgz_mx_sm.jsp"),
		@Result(name = "delete", type = "json"),
		@Result(name = "save", type = "json")})
@SuppressWarnings("unchecked")
public class HyClgzAction extends ExtendAction{
	 /** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	public String showSm() throws Exception{
		((HyClgzService)this.getService()).getGzxx(domain);
		return "showSm";
	}
	public String download() throws Exception {
		super.download();
		HyClgzDomain domain = (HyClgzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("���");
		headList.add("�ɳ�����");
		headList.add("��������");
		headList.add("�ҳ�����");
		headList.add("˾��");
		headList.add("�������");
		headList.add("�ͻ�����");
		headList.add("��������");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("ת�벿��");
		headList.add("��������");
		headList.add("������ַ");
		headList.add("�ջ���ַ");
		headList.add("�ɳ���");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("ҵ��λ");
		
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyClgzDomain element = (HyClgzDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getPcdh());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			list.add(element.getCyrSjxm());
			list.add(element.getDdbh());
			list.add(element.getKhmc());
			list.add(element.getHwmc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getZrbmMc());
			list.add(element.getJsSl());
			list.add(element.getFhrDz());
			list.add(element.getShrDz());
			list.add(element.getPcrMc());
			list.add(element.getPcrq());
			list.add(element.getPcJgmc());
			list.add(element.getSsJgmc());
			
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyClgzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyClgzDomain) domain;
	}

	@Resource(name = "hyClgzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
