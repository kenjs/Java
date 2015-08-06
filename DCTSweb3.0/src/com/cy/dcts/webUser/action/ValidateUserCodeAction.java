package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class ValidateUserCodeAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1465902142648738851L;
	private IQueryWebUserInfoService queryWebUserInfoService;

	@Override
	protected void execMethod() throws Exception {
		String code = request.getParameter("code");		
		if(StringUtils.isBlank(code)) {
			sendResponseToJson("-1", "");
		}
		WebUserInfo info = queryWebUserInfoService.queryWebUserInfoByCode(code);
		if(info != null && !code.equals(info.getCode())){
			sendResponseToJson("0", "fail");
		} else {
			sendResponseToJson("1", "success");
		}
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}
