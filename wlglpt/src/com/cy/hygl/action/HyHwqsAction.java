package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.HyHwqsDomain;

/**
 * THE ACTION FOR ����-����ǩ��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hwqs", results = {
		@Result(name = "init", location = "/work/hygl/hy_hwqs.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_hwqs_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyHwqsAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyHwqsDomain domain = (HyHwqsDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("״̬");
		headList.add("�ص����");
		headList.add("�ͻ�����");
		headList.add("�������");
		headList.add("��������");
		headList.add("����");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("�ջ���");
		headList.add("�ջ���ַ");
		headList.add("ǩ����");
		headList.add("ǩ������");
		headList.add("�ɳ�����");
		headList.add("������");
		headList.add("�ɳ�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyHwqsDomain element = (HyHwqsDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			if(StringUtils.isNotBlank(element.getQsrmc())){
				list.add("�ѵǼ�");
			}
			else{
				list.add("δ�Ǽ�");
			}
			list.add(element.getHdbh());
			list.add(element.getFhrMc());
			list.add("");
			list.add(element.getHwMc());
			list.add(element.getHwSl());
			list.add(element.getSfd());
			list.add(element.getMdd());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getQsrmc());
			list.add(element.getQsrq());
			list.add(element.getPcdh());
			list.add(element.getSjXm());
			list.add(element.getPcrq());
			

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyHwqsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyHwqsDomain) domain;
	}

	@Resource(name = "hyHwqsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
