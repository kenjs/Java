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
import com.cy.zygl.domain.QyKhZhdzDomain;

/**
 * THE ACTION FOR ��ҵ-�ͻ�-װ����ַ
 * 
 * @author ylp
 * @since 2013-1-15 ����8:31:00
 * @version
 */
@Controller
@Scope("prototype")
@Action(value = "/zygl/qykhzhdz", results = {
		@Result(name = "init", location = "/work/zygl/qykhzhdz.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qykhzhdz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json") })
@SuppressWarnings("unchecked")
public class QyKhZhdzAction extends ExtendAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyKhZhdzDomain domain = (QyKhZhdzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("���");
		headList.add("�ͻ�����");
		headList.add("��������");
		headList.add("��ϸ��ַ");
		headList.add("�ʱ�");
		headList.add("��ϵ��");
		headList.add("�ƶ��绰");
		headList.add("�̶��绰");
		headList.add("������ϵ�绰");
		headList.add("��ע");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain
				.getDataList()) {
			QyKhZhdzDomain element = (QyKhZhdzDomain) e;
			List list = new ArrayList();

			list.add(i++ + "");
			list.add(element.getKhmc());
			list.add(element.getXzqhMc());
			list.add(element.getXxdz());
			list.add(element.getYb());
			list.add(element.getLxr());
			list.add(element.getLxrYddh());
			list.add(element.getLxrGddh());
			list.add(element.getQtlxdh());
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
		if (domain == null) {
			domain = new QyKhZhdzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyKhZhdzDomain) domain;
	}

	@Resource(name = "qyKhZhdzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
