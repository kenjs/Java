package com.cy.bggl.action;

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
import com.cy.bggl.domain.BgWlryDomain;

/**
 * THE ACTION FOR 办公-外联人员
 * @author HaoY
 */
 @Controller
@Scope("prototype")
@Action(value = "/bggl/bgwlry", results = {
		@Result(name = "init", location = "/work/bggl/bgwlry.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/bgwlry_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class BgWlryAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgWlryDomain domain = (BgWlryDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("分类名称");
		headList.add("姓名");
		headList.add("地址");
		headList.add("电话");
		headList.add("传真");
		headList.add("手机");
		headList.add("E-MAIL");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgWlryDomain element = (BgWlryDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getFlmc());
			list.add(element.getXm());
			list.add(element.getDz());
			list.add(element.getDh());
			list.add(element.getCz());
			list.add(element.getSj());
			
			list.add(element.getDy());
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
			domain = new BgWlryDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgWlryDomain) domain;
	}

	@Resource(name = "bgWlryServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
