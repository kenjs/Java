package com.cy.cwgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwHbzcLsjlDomain;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * THE ACTION FOR 财务-货币资产流水记录
 * @author HCM
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "cwgl/cwhbzclsjl",results = {
		@Result(name = "init", location = "/work/cwgl/cwhbzclsjl.jsp"),		
		@Result(name = "query", type = "json")})
public class CwHbzcLsjlAction extends ExtendAction {
	/**
	 * 下载功能
	 */
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("类别");		
		headList.add("银行名称");
		headList.add("户名");
		headList.add("账号");
		headList.add("金额");
		headList.add("经办人");
		headList.add("日期");
		headList.add("说明");
		
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwHbzcLsjlDomain element =  (CwHbzcLsjlDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getZcflMc());
			list.add(element.getYhmc());
			list.add(element.getHm());
			list.add(element.getZh());
			list.add(element.getBdje());
			list.add(element.getJbrCzyMc());
			list.add(element.getRq());
			list.add(element.getSm());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwHbzcLsjlDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwHbzcLsjlDomain) domain;
	}

	@Resource(name = "cwHbzcLsjlServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
