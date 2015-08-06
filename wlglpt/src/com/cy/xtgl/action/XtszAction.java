package com.cy.xtgl.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.XtszDomain;

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

@Action(value = "xtgl/xtsz", results = {
		@Result(name = "init", location = "/work/xtgl/xtgl_xtsz.jsp"),
		@Result(name = "initMxx", type = "json"),
		@Result(name = "save", type = "json")
})
public class XtszAction extends ExtendAction {

	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XtszDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XtszDomain) domain;
	}
	
	public String initMxx() throws Exception{
		XtszDomain xtdomain=(XtszDomain)getDomain();
		getService().initMx(xtdomain, getUserDomain());
		return "initMxx";
	}
	
	@Resource(name = "xtszServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
	
}
