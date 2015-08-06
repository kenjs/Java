package com.cy.dcts.webUser.action;

import java.util.HashMap;
import java.util.Map;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class ValidateUserAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8998436616270279747L;
	private IQueryWebUserInfoService queryWebUserInfoService;

	@Override
	protected void execMethod() throws Exception {
		WebUserInfo obj = getSessionUser();
		if(obj != null) {
			response.sendRedirect(request.getContextPath());
		}
		String userId = request.getParameter("userId");
		String code = request.getParameter("code");
		Map<String,Object> map = new HashMap<String, Object>();
		WebUserInfo userInfo = queryWebUserInfoService.queryWebUserInfoById(userId);
		if(userInfo != null) {
			sendResponseToJson("0", "ok");
			map.put("id", userId);
			map.put("code", code);
			queryWebUserInfoService.updateUserFlag(map);
			userInfo = queryWebUserInfoService.queryWebUserInfoById(userId);
			this.putSessionUser(userInfo);
		} else {
			sendResponseToJson("1", "fail");
		}
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}
