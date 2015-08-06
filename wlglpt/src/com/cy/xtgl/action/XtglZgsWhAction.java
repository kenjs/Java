package com.cy.xtgl.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * THE ACTION FOR ��ҵ-��֯����
 * 
 * @Descriptoin �ܹ�˾ά������ACTION 
* @Note
* @author ylp
* @since 2013-1-9 ����11:13:09 
 */
@Controller
@Scope("prototype")
@Action(value = "xtgl/zgswh", results = {
		@Result(name = "init", location = "/work/xtgl/zgswh_mx.jsp"),
		@Result(name = "save", type = "json") })
@SuppressWarnings("unchecked")
public class XtglZgsWhAction extends ExtendAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new QyZzjgDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}

	@Resource(name = "xtglZgsWhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

}
