package com.cy.dcts.webUser.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.util.MD5Util;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

/**
 * 找回用户密码
 * @author nxj
 *
 */
public class RetrieveUserPwsAction  extends BaseJsonAction {

	private static final long serialVersionUID = 737300859439180502L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IQueryWebUserInfoService queryWebUserInfoService;
	private ISaveWebUserInfoService saveWebUserInfoService;
	
	
	@Override
	protected void execMethod() throws Exception {
		String mobilephone = this.request.getParameter("mobilephone");
		String password = this.request.getParameter("password");
		try{
			WebUserInfo webUserInfo = new WebUserInfo();
			webUserInfo.setMobilephone(mobilephone);
			webUserInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			List<WebUserInfo> list= queryWebUserInfoService.queryWebUserInfo(webUserInfo);
			if(list.size() == 1) {
				WebUserInfo webUserInfoPws = new WebUserInfo();
				webUserInfoPws = list.get(0);
				webUserInfoPws.setPassword(new MD5Util().getMD5ofStr(password));
				if(saveWebUserInfoService.modifyWebUserInfoPasswordById(webUserInfoPws) == true) {
					String result = this.sendResponseToJson("0","找回密码成功!");
					logger.warn("update user password success. mobilephone=[{}], password=[{}], userId=[{}], json=[{}]",new Object[] {mobilephone, password, list.get(0).getId(), result });
				}else {
					String result = this.sendResponseToJson("1","找回密码失败 !");
					logger.warn("update user password error. mobilephone=[{}], password=[{}], userId=[{}], json=[{}]",new Object[] {mobilephone, password, list.get(0).getId(), result });
				}
			}else {
				String result = this.sendResponseToJson("2","找回密码失败!用户不存在！");
				logger.warn("update user password error user not exist. mobilephone=[{}], password=[{}], listSize=[{}], json=[{}]",new Object[] {mobilephone, password, list.size(), result });
			}
		}catch(Exception e) {
			logger.error("update user password error! mobilephone=[{}] , password=[{}]",new Object[] {mobilephone , password});
			throw new RuntimeException();
		}
		
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

	public void setSaveWebUserInfoService(
			ISaveWebUserInfoService saveWebUserInfoService) {
		this.saveWebUserInfoService = saveWebUserInfoService;
	}

}
