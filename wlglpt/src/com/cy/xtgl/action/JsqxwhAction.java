package com.cy.xtgl.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.XtJsGnmkDomain;
import com.cy.xtgl.service.JsqxwhService;



/**
 * THE ACTION FOR 企业-组织机构 角色权限维护
 * @author 闫伟
 * @date 2013.1.17
*/ 	

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "xtgl/jsqxwh", 
		results = {
		@Result(name = "initMx", location = "/work/xtgl/jsqxwh_mx.jsp"),
		@Result(name = "init", location = "/work/xtgl/jsqxwh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "queryTreeStr", type = "json"),
		@Result(name = "queryGnmkDmsByJsDm", type = "json"),
		@Result(name = "queryJsInnerHtmlByGnmkDm", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})


public class JsqxwhAction  extends ExtendAction {

	public String queryTreeStr() throws Exception {
		JsqxwhService service = (JsqxwhService) getService();
		service.getTreeStr(getDomain(),getUserDomain());
		return "queryTreeStr";
	}

	public String queryGnmkDmsByJsDm() throws Exception {
		JsqxwhService service = (JsqxwhService) getService();
		service.getGnmkDmsByJsDm(getDomain());
		return "queryGnmkDmsByJsDm";
	}

	public String queryJsInnerHtmlByGnmkDm() throws Exception {
		JsqxwhService service = (JsqxwhService) getService();
		service.getJsInnerHtmlByGnmkDm(getDomain());
		return "queryJsInnerHtmlByGnmkDm";
	}

	@Resource(name = "jsqxwhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new XtJsGnmkDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
