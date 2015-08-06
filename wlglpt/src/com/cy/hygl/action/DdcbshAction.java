package com.cy.hygl.action;

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
import com.cy.hygl.domain.DdcbshDomain;

/**
 * THE ACTION FOR 调度成本审核
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/ddcbsh", results = {
		@Result(name = "init", location = "/work/hygl/ddcbsh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/ddcbsh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class DdcbshAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		DdcbshDomain domain = (DdcbshDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("派车登记序号(SEQ_PC_DJXH)");
		headList.add("派车单号");
		headList.add("承运人_车主姓名");
		headList.add("承运人_车辆号码");
		headList.add("总运费");
		headList.add("预付运费");
		headList.add("押金");
		headList.add("转入部门代码");
		headList.add("派车人");
		headList.add("派车日期");
		headList.add("所属机构");
		headList.add("派车方式");
		headList.add("文书审批状态");
		headList.add("总收入");

		heads.add(headList);

		int i = 1;
		for (DdcbshDomain element:(List<DdcbshDomain>)domain.getDataList()) {
			List list = new ArrayList();
			
			list.add(i+++ "");
			
			list.add(element.getZsr());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new DdcbshDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (DdcbshDomain) domain;
	}

	@Resource(name = "ddcbshServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
