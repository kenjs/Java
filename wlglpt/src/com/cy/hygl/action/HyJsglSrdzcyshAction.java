package com.cy.hygl.action;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyJsglSrdzcyshDomain;

/**
 * THE ACTION FOR  ’»Î∂‘’À≤Ó“Ï…Û∫À
 * @author HJH
 */
@Controller		
@Scope("prototype")	
@Action(value = "/hygl/srdzcysh", results = { 
		@Result(name = "init", location = "/work/hygl/hy_jsgl_srdzcysh.jsp"),
		@Result(name = "query", type = "json")})
public class HyJsglSrdzcyshAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyJsglSrdzcyshDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyJsglSrdzcyshDomain) domain;
	}

	@Resource(name = "hyJsglSrdzcyshServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
