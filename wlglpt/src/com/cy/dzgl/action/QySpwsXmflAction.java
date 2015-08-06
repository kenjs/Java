package com.cy.dzgl.action;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.dzgl.domain.QySpwsXmflDomain;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 企业-审批文书-项目分类.
 * @Date 2013-01-29
 * @author 闫伟
 */

@Controller
@Scope("prototype")
@Action(value = "dzgl/qyspwsxmfl", 
		results = {
		@Result(name = "init", location = "/work/dzgl/qyspwsxmfl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/dzgl/qyspwsxmfl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QySpwsXmflAction extends ExtendAction {

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QySpwsXmflDomain domain = (QySpwsXmflDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序列");
		headList.add("文书代码");
		headList.add("文书名称");
		headList.add("项目分类名称");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			QySpwsXmflDomain element = (QySpwsXmflDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getWsDm());
			list.add(element.getWsMc());
			list.add(element.getXmflmc());
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

	@Resource(name = "qySpwsXmflServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new QySpwsXmflDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
