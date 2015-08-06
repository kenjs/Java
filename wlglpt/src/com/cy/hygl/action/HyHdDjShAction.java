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
import com.cy.hygl.domain.DdcbshDomain;
import com.cy.hygl.domain.HyHdDjShDomain;
import com.cy.hygl.domain.HyWlSsDjShDomain;

/**
 * THE ACTION FOR ���ȳɱ����
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hyhddjbdsh", results = {
		@Result(name = "init", location = "/work/hygl/hy_hddjsh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_wlssdjsh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyHdDjShAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ɳ��Ǽ����(SEQ_PC_DJXH)");
		headList.add("�ɳ�����");
		headList.add("������_��������");
		headList.add("������_��������");
		headList.add("���˷�");
		headList.add("Ԥ���˷�");
		headList.add("Ѻ��");
		headList.add("ת�벿�Ŵ���");
		headList.add("�ɳ���");
		headList.add("�ɳ�����");
		headList.add("��������");
		headList.add("�ɳ���ʽ");
		headList.add("��������״̬");
		headList.add("������");

		heads.add(headList);

		

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyHdDjShDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyHdDjShDomain) domain;
	}

	@Resource(name = "hyHdDjBdShServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
