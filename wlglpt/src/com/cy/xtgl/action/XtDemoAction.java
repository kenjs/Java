package com.cy.xtgl.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.XtDemoDomain;

/**
 * 
* @Descriptoin 公共部分应用事例
* @Note
* @author Administrator
* @since 2013-1-5 上午09:26:35 
* @version
 */

@Controller
@Scope("prototype")

@Action(value = "xtgl/demo", results = {
		@Result(name = "init", location = "/work/xtgl/demo.jsp"),
		@Result(name = "initMx", location = "/work/xtgl/demo_mx.jsp")
})
public class XtDemoAction extends ExtendAction {

	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XtDemoDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XtDemoDomain) domain;
	}

	@Resource(name = "xtDemoServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
	
}
