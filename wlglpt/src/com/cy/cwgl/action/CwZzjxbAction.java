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
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwZzjxbDomain;

/**
 * THE ACTION FOR ����-��ת���²�
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwzzjxb", results = {
		@Result(name = "init", location = "/work/cwgl/cwzzjxb.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwzzjxb_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwZzjxbAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwZzjxbDomain domain = (CwZzjxbDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ʽ�����Ǽ����(SEQ_CW_DJXH)");
		headList.add("����");
		headList.add("���յ�λ");
		headList.add("���_�ϼ�");
		headList.add("���_�ֽ�");
		headList.add("���_�Ϳ�");
		headList.add("���_���");
		headList.add("֧��_�ϼ�");
		headList.add("֧��_Ԥ����");
		headList.add("֧��_���");
		headList.add("֧��_��������");
		headList.add("���ý�");
		headList.add("�ʽ�����");
		headList.add("�²���λ");
		headList.add("�²����");
		headList.add("��ע");
		headList.add("��Ч��־(Y/N)");
		headList.add("��Ҫ������־(Y/N)");
		headList.add("��������״̬����");
		headList.add("�����������");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwZzjxbDomain element = (CwZzjxbDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getZjdbDjxh());
			list.add(element.getRq());
			list.add(element.getJsDwMc());
			list.add(element.getJcHj());
			list.add(element.getJcXj());
			list.add(element.getJcYk());
			list.add(element.getJcCk());
			list.add(element.getZfHj());
			list.add(element.getZfYfk());
			list.add(element.getZfYk());
			list.add(element.getZfBxfy());
			list.add(element.getByj());
			list.add(element.getZjxq());
			list.add(element.getXbDwDjxh());
			list.add(element.getXbje());
			list.add(element.getBz());
			list.add(element.getYxbz());
			list.add(element.getSpbz());
			list.add(element.getWsspztDm());
			list.add(element.getWsSpxh());
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
			domain = new CwZzjxbDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwZzjxbDomain) domain;
	}

	@Resource(name = "cwZzjxbServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
