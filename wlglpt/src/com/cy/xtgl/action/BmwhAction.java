package com.cy.xtgl.action;

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
import com.cy.xtgl.domain.QyZzjgDomain;
/**
 * The Action for ��ҵ��֯��������ά��
 * 
 * @Author Yu huan
 * @Date 2013-01-8
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "xtgl/bmwh", 
		results = {
		@Result(name = "init", location = "/work/xtgl/bmwh.jsp"),
		@Result(name = "initMx", location = "/work/xtgl/bmwh_mx.jsp"),		
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveDisable", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "delete", type = "json")})

public class BmwhAction extends ExtendAction {
	@SuppressWarnings("unchecked")
	//���ع���
	public String download() throws Exception {
		super.download();
		QyZzjgDomain domain = (QyZzjgDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List bmwhdlList = new ArrayList();
		bmwhdlList.add("���");
		bmwhdlList.add("����");
		bmwhdlList.add("�绰");
		bmwhdlList.add("������");
		bmwhdlList.add("������");
		bmwhdlList.add("��������");
		bmwhdlList.add("�޸���");
		bmwhdlList.add("�޸�����");
		heads.add(bmwhdlList);
		int j = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain
				.getDataList()) {
			QyZzjgDomain element = (QyZzjgDomain) e;
			List list = new ArrayList();
			list.add(j++ + "");
			list.add(element.getMc());
			list.add(element.getDh());
			list.add(element.getFzr());
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

	@Resource(name = "bmwhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new QyZzjgDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}

