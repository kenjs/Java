package com.cy.zygl.action;



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
import com.cy.zygl.domain.QyXxzjDjxxDomain;


/**
 * The SERVICE for ��ҵ-��Ϣ�н�-�Ǽ���Ϣ
 * 
 * @author yw
 * @since 2013-2-20 ����8:31:00
 * @version
 */

@Controller
@Scope("prototype")
@Action(value = "zygl/qyxxzjwh", 
		results = {
		@Result(name = "init", location = "/work/zygl/qyxxzjdjxx.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyxxzjdjxx_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyXxzjDjxxAction extends ExtendAction {
	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("���");
		headList.add("��������");
		headList.add("����");
		headList.add("���");
		headList.add("��ַ");
		headList.add("������");
		headList.add("�绰");
		headList.add("�ʱ�");
		headList.add("��ע");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyXxzjDjxxDomain element = (QyXxzjDjxxDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getQhStr());
			list.add(element.getXxzjmc());
			list.add(element.getXxzjjc());
			list.add(element.getDz());
			list.add(element.getFzr());
			list.add(element.getDh());
			list.add(element.getYb());
			list.add(element.getBz());
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
			domain = new QyXxzjDjxxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyXxzjDjxxDomain) domain;
	}

	@Resource(name = "qyXxzjDjxxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
