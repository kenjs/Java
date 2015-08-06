package com.cy.dcts.webUser.action;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;

public class OpenUpdatePwdViewAction extends BasePageAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4508165877186103885L;
	private String code;

	protected String execMethod() throws Exception {
		Object obj = request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
		if(obj == null) {
			return LOGIN;
		}
		WebUserInfo webUserInfo = (WebUserInfo)obj;
		code = webUserInfo.getMobilephone();
		return SUCCESS;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
