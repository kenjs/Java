package com.cy.cwgl.action;

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
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwZfdjDomain;
import com.cy.cwgl.service.CwYsyfSrdjService;
import com.cy.cwgl.service.CwZfdjService;

/**
 * THE ACTION FOR ����-��Ʊ�Ǽ�
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwzfdj", results = {
		@Result(name = "init", location = "/work/cwgl/cwzfdj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwzfdj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "doGetYh", type = "json"),
		@Result(name = "queryXl", type = "json"),
		@Result(name = "cancle", type = "json"),
		@Result(name = "plDj", type = "json"),
		@Result(name = "delete", type = "json")
		})
@SuppressWarnings("unchecked")
public class CwZfdjAction extends ExtendAction {
		/**
		 * ȡ���㷽��������
		 * @return
		 * @throws Exception
		 */
		public String queryXl() throws Exception {
			((CwZfdjService)this.getService()).getMcList(domain);
			return "queryXl";
		}
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	/**
	 * ȡ���г�ʼ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String doGetYh() throws Exception{
		((CwZfdjService)this.getService()).doGetYh(domain);
		return "doGetYh";
		
	}
	public String plDj() throws Exception{
		CwZfdjService service=(CwZfdjService)getService();
		service.plDj(domain,getUserDomain());
		return "plDj";
	}
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwZfdjDomain domain = (CwZfdjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("���");
		headList.add("״̬");		
		headList.add("���㷽");
		headList.add("����");
		headList.add("���");
		headList.add("����");
		headList.add("δ��");
		headList.add("���");
		headList.add("��Ŀ");
		headList.add("��Դ");
		headList.add("��������");
		headList.add("˵��");
		headList.add("�Ǽǲ���");
		headList.add("������λ");

		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			CwZfdjDomain element = (CwZfdjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getYsyfztMc());
			list.add(element.getYfjsfMc());
			list.add(element.getYfjsfDjmc());
			list.add(element.getYsfJe());
			list.add(element.getYisfJe());
			list.add(element.getWsfJe());
			list.add(element.getKmdlMc());
			list.add(element.getKmxlMc());
			list.add(element.getYsyflyMc());
			list.add(element.getCsrq());
			list.add(element.getSm());
			list.add(element.getSsJgmc());
			list.add(element.getDjJgmc());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}

	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwZfdjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwZfdjDomain) domain;
	}

	@Resource(name = "cwZfdjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
	
	public void cancle() throws Exception{
		UserDomain userDomain = getUserDomain();
		((CwZfdjService)getService()).doCancle(domain);
	}

}