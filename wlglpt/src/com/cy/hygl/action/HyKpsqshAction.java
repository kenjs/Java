package com.cy.hygl.action;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyKpsqshDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;

/**
 * THE ACTION FOR ªı‘À-ø™∆±…Í«Î…Û∫À
 * @author LYY
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hykpsqsh", results = {
		@Result(name = "init", location = "/work/hygl/hykpsqsh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hykpsqsh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyKpsqshAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyKpsqshDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyKpsqshDomain) domain;
	}

	@Resource(name = "hyKpsqshServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
