package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.common.util.MD5Util;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

/**
 * 用户登录
 * @author nxj
 *
 */
public class LoginWebUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -7137641230538918208L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String userId;
	private String mobilephone;
	private WebUserInfoDamain webUserInfoDamains;
	private IQueryWebUserInfoService queryWebUserInfoService;
	private ISaveWebUserInfoService saveWebUserInfoService;
	
	@Override
	protected String execMethod() throws Exception {
		try {
			if(getSessionUser() == null) {
				if(webUserInfoDamains == null) {
					webUserInfoDamains = new WebUserInfoDamain();
					webUserInfoDamains.setErrorMessage("0");
					logger.error("web user login error object is null.");
					return ERROR;
				}
				webUserInfoDamains.setErrorMessage("");
				if(StringUtils.isEmpty(webUserInfoDamains.getCode())) {
					webUserInfoDamains.setErrorMessage("-1");
					logger.error("web user login error code is null");
					return ERROR;
				}
				if(StringUtils.isEmpty(webUserInfoDamains.getPassword())) {
					webUserInfoDamains.setErrorMessage("-2");
					logger.error("web user login error password is null");
					return ERROR;
				}
				WebUserInfo userInfo = null;
				userInfo = queryWebUserInfoService.queryWebUserInfoByCode(webUserInfoDamains.getCode());
				if(userInfo == null) {
					webUserInfoDamains.setErrorMessage("-3");
					logger.error("web user login error code is not exists");
					return ERROR;
				}
//				
//				if(ValidateUtil.validateTelePhone(webUserInfoDamains.getCode()) == false) {
//					userInfo = queryWebUserInfoService.queryWebUserInfoByCode(webUserInfoDamains.getCode());
//					if(userInfo == null) {
//						webUserInfoDamains.setErrorMessage("-3");
//						logger.error("web user login error code is not exists");
//						return ERROR;
//					}
//				}else {
//					userInfo = queryWebUserInfoService.queryWebUserInfoByMobilephone(webUserInfoDamains.getCode());
//					if(userInfo == null) {
//						webUserInfoDamains.setErrorMessage("-4");
//						logger.error("web user login error password is not exists");
//						return ERROR;
//					}
//				}
				if(!userInfo.getPassword().equals(new MD5Util().getMD5ofStr(webUserInfoDamains.getPassword()))) {
					webUserInfoDamains.setErrorMessage("-5");
					logger.error("web user login password error");
					return ERROR;
				}				
				String newOrOldFlag = userInfo.getNewoldUserType();
				if(StringUtils.isNotBlank(newOrOldFlag) && "1".equals(newOrOldFlag)) {
					userId = userInfo.getId();
					mobilephone = userInfo.getMobilephone();
					return "validateAgain";
				}
				
				//如果编码代码为空, 生成一个给此用户
				if("0".equals(userInfo.getParentId()) && StringUtils.isBlank(userInfo.getEncoded())) {
					String encode = queryWebUserInfoService.getNextEncode();
					userInfo.setEncoded(encode);
					saveWebUserInfoService.modifyWebUserInfoEncodedById(userInfo);
				}							
				
				this.putSessionUser(userInfo);
				logger.warn("web user login success. code=[{}]", new Object[] { webUserInfoDamains.getCode() });
				
				webUserInfoDamains = null;								
								
			}
			return SUCCESS;
		}catch (Exception e) {
			logger.debug("add web user info begin error! code=[{}]",new Object[] { webUserInfoDamains.getCode() });
			throw new RuntimeException();
		}
	}
	
	
	
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
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



	public WebUserInfoDamain getWebUserInfoDamains() {
		return webUserInfoDamains;
	}



	public void setWebUserInfoDamains(WebUserInfoDamain webUserInfoDamains) {
		this.webUserInfoDamains = webUserInfoDamains;
	}



	public void setSaveWebUserInfoService(
			ISaveWebUserInfoService saveWebUserInfoService) {
		this.saveWebUserInfoService = saveWebUserInfoService;
	}
	
}
