package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
/**
 * 验证手机号码是否已经存在
 * @author Administrator
 *
 */
public class CheckPhoneNumberAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8798833979928511936L;
	private IQueryWebUserInfoService queryWebUserInfoService;
	
	protected void execMethod() throws Exception {
		String phone = request.getParameter("mobilephone");
		if(StringUtils.isBlank(phone)){
			return;
		}
		WebUserInfo obj = queryWebUserInfoService.queryWebUserInfoByMobilephone(phone);
		if(obj != null) {
			sendResponseToJson("1", "");
		} else {
			sendResponseToJson("0", "");
		}
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}