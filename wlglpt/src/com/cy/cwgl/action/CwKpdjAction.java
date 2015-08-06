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
import com.cy.cwgl.domain.CwKpdjDomain;

/**
 * THE ACTION FOR 财务-开票登记
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwkpdj", results = {
		@Result(name = "init", location = "/work/cwgl/cwkpdj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/cwgl/cwkpdj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwKpdjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwKpdjDomain domain = (CwKpdjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("客户名称");
		headList.add("发票代码");
		headList.add("发票号码");
		headList.add("开票人");
		headList.add("开票日期");
		headList.add("开票金额");
		headList.add("税率");
		headList.add("税额");
		headList.add("作废标志");
		headList.add("红字标志");
		headList.add("对应蓝字发票代码");
		headList.add("对应蓝字发票号码");
		headList.add("所属机构");
		headList.add("登记部门");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwKpdjDomain element = (CwKpdjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getKhmc());
			list.add(element.getFpdm());
			list.add(element.getFphm());
			list.add(element.getKprMc());
			list.add(element.getKprq());
			list.add(element.getKpje());
			list.add(element.getSl());
			list.add(element.getSe());
			if("Y".equals(element.getZfbz())){
				list.add("作废");
			}else{
				list.add(element.getZfStr());
			}
			
			if("Y".equals(element.getHzbz())){
				list.add("红字");
			}else{
				list.add(element.getHzStr());
			}
			list.add(element.getLzFpdm());
			list.add(element.getLzFphm());
			list.add(element.getSsJgmc());
			list.add(element.getDjJgmc());
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
			domain = new CwKpdjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwKpdjDomain) domain;
	}

	@Resource(name = "cwKpdjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
