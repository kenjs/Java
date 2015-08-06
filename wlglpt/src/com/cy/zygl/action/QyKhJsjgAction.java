package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyFbsDjxxDomain;
import com.cy.zygl.domain.QyKhJsjgDomain;
import com.cy.zygl.service.QyClxhwhService;
import com.cy.zygl.service.QyKhJsjgService;

/**
 * THE ACTION FOR ��ҵ-�ְ���-�Ǽ���Ϣ
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qykhjsjg", results = {
		@Result(name = "init", location = "/work/zygl/qykhjsjg.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "saveCheck", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qykhjsjg_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyKhJsjgAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyKhJsjgDomain domain = (QyKhJsjgDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("��������");
		headList.add("�ͻ�����");
		headList.add("�Ƿ�ʹ��ȫ������");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("��������");
		headList.add("�۸���㹫ʽ");
		headList.add("�۸����ϵͳ��ʽ");
		headList.add("��Ч����");
		headList.add("��Ч��ֹ");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);
		String str="";
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyKhJsjgDomain element = (QyKhJsjgDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getSjMc());
			list.add(element.getKhMc());
			if(element.getSyfw().equals("Y")){
				str="��";
			}
			else{
				str="��";
			}
			list.add(str);
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getHwMc());
			list.add(element.getJgjsgs());
			list.add(element.getXtgs());
			list.add(element.getYxqQ());
			list.add(element.getYxqZ());
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
	
	public String saveCheck() throws Exception{
		((QyKhJsjgService)getService()).saveCheck(getDomain());
		return "saveCheck";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyKhJsjgDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyKhJsjgDomain) domain;
	}

	@Resource(name = "qyKhJsjgServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
