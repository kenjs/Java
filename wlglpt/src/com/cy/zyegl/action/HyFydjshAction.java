package com.cy.zyegl.action;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.zyegl.domain.HyFydjshDomain;

/**
 * THE ACTION FOR 货运-费用登记审核
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hyfydjsh", results = {
		@Result(name = "init", location = "/work/zygl/hyfydjsh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/hyfydjsh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyFydjshAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyFydjshDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyFydjshDomain) domain;
	}

	@Resource(name = "hyFydjshServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
