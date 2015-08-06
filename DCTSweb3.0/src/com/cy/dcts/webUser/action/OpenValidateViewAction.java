package com.cy.dcts.webUser.action;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;

public class OpenValidateViewAction extends BasePageAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6443004574647233558L;
	private String userId;
	private String mobilephone = "";

	@Override
	protected String execMethod() throws Exception {		
		//userId = request.getParameter("userId");
		WebUserInfo obj = getSessionUser();
		if(obj != null) {
			response.sendRedirect(request.getContextPath());
		}
		String[] str = userId.split(";");
		userId = str[0];
		if(str.length > 1) {
			String[] strArr = str[1].split("=");
			if(strArr.length > 1) {
				mobilephone = strArr[1];
			}
		}
		return SUCCESS;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

}
