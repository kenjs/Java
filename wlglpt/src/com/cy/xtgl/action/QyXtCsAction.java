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
import com.cy.xtgl.domain.QyXtCsDomain;
/**
 * THE ACTION FOR 企业-系统-参数信息
 * @author HCM
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "xtgl/qyxtcs",results = {
		@Result(name = "init", location = "/work/xtgl/qyxtcs.jsp"),
		@Result(name = "initMx", location = "/work/xtgl/qyxtcs_mx.jsp"),		
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
public class QyXtCsAction extends ExtendAction{
	/**
	 * 下载功能
	 */
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyXtCsDomain domain = (QyXtCsDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("设置单位");
		headList.add("参数序号");
		headList.add("参数名称");
		headList.add("使用说明");
		headList.add("参数值");
		
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyXtCsDomain element =  (QyXtCsDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getDwmc());
			list.add(element.getCsxh());
			list.add(element.getCsmc());
			list.add(element.getSysm());
			list.add(element.getCsz());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyXtCsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyXtCsDomain) domain;
	}

	@Resource(name = "qyXtCsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
