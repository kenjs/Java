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
import com.cy.xtgl.domain.QyJsDomain;

/**
 * THE ACTION FOR 企业角色
 * 
 * @author ylp
 * @since 2013-1-10 下午14:12:51
 * @version
 */
@Controller
@Scope("prototype")
@Action(value = "/xtgl/qyjs", results = {
		@Result(name = "init", location = "/work/xtgl/qyjs.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/xtgl/qyjs_mx.jsp"),
		@Result(name = "saveDisable", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json") })
@SuppressWarnings("unchecked")
public class QyJsAction extends ExtendAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyJsDomain domain = (QyJsDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("序号");
		headList.add("角色名称");
		headList.add("角色简称");
		headList.add("备注说明");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain
				.getDataList()) {
			QyJsDomain element = (QyJsDomain) e;
			List list = new ArrayList();

			list.add(i++ + "");
			list.add(element.getJsMc());
			list.add(element.getJsJc());
			list.add(element.getBzsm());
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
			domain = new QyJsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyJsDomain) domain;
	}

	@Resource(name = "qyJsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
