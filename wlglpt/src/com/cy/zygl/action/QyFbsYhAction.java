package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyFbsYhDomain;

/**
 * THE ACTION FOR 分包商用户管理
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/fbsyhgl", results = {
		@Result(name = "init", location = "/work/zygl/fbsyhgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/fbsyhgl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "saveDisable", type = "json")
		})
@SuppressWarnings("unchecked")
public class QyFbsYhAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyFbsYhDomain domain = (QyFbsYhDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("名称");
		headList.add("账号");
		headList.add("登录验证方式");
		headList.add("创建日期");
		headList.add("创建人");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyFbsYhDomain element = (QyFbsYhDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getMc());
			list.add(element.getZh());
			list.add(element.getDlyzfsMc());
			list.add(element.getCjrq());
			list.add(element.getCjrMc());
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
			domain = new QyFbsYhDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyFbsYhDomain) domain;
	}

	@Resource(name = "qyFbsYhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
