package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.common.util.MD5Util;
import com.cy.dcts.common.util.ValidateUtil;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

/**
 * 用户快速登录
 * @author nxj
 *
 */
public class LoginUserInfoAction extends BaseJsonAction {

	private static final long serialVersionUID = 568259102936885488L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private IQueryWebUserInfoService queryWebUserInfoService;
	private ISaveWebUserInfoService saveWebUserInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			WebUserInfoDamain webUserInfoDamain = getWebUserInfoDomain();
			if(webUserInfoDamain == null) {
				String result = this.sendResponseToJson("divPwdMpt","divCoent","请填写完整信息");
				logger.debug("web user login error object is null. json=[{}]",new Object[] { result });
				return;
			}
			if(StringUtils.isEmpty(webUserInfoDamain.getCode())) {
				String result = this.sendResponseToJson("divCodeMpt","divCodeNull","账号不能为空");
				logger.debug("web user login error code is null. json=[{}]",new Object[] { result });
				return;
			}
			if(StringUtils.isEmpty(webUserInfoDamain.getPassword())) {
				String result = this.sendResponseToJson("divPwdMpt","divPwdNull","密码不能为空");
				logger.debug("web user login error password is null. json=[{}]",new Object[] { result });
				return;
			}
			WebUserInfo userInfo = null;
			if(ValidateUtil.validateTelePhone(webUserInfoDamain.getCode()) == false) {
				userInfo = queryWebUserInfoService.queryWebUserInfoByCode(webUserInfoDamain.getCode());
				if(userInfo == null) {
					String result = this.sendResponseToJson("divCodeMpt","divCodeNot","账号不存在");
					logger.debug("web user login error code is not exists. code=[{}], json=[{}]",new Object[] {webUserInfoDamain.getCode(), result });
					return;
				}
			}else {
				userInfo = queryWebUserInfoService.queryWebUserInfoByMobilephone(webUserInfoDamain.getCode());
				if(userInfo == null) {
					String result = this.sendResponseToJson("divCodeMpt","divMobilephoneNot","手机号不存在");
					logger.debug("web user login error mobilephone is not exists. mobilephone=[{}], json=[{}]",new Object[] {webUserInfoDamain.getCode(), result });
					return;
				}
			}
			if(!userInfo.getPassword().equals(new MD5Util().getMD5ofStr(webUserInfoDamain.getPassword()))) {
				String result = this.sendResponseToJson("divPwdMpt","divPwdError","密码错误");
				logger.debug("web user login password error. password=[{}], json=[{}]",new Object[] {webUserInfoDamain.getPassword(), result });
				return;
			}
			
			String newOrOldFlag = userInfo.getNewoldUserType();
			if(StringUtils.isNotBlank(newOrOldFlag) && "1".equals(newOrOldFlag)) {
				String userId = userInfo.getId();
				sendResponseToJson("0","","{\"userId\":" + userId +",\"mobilephone\":" + userInfo.getMobilephone() + "}");				
				return;
			}
			
			//如果编码代码为空, 生成一个给此用户
			if("0".equals(userInfo.getParentId()) && StringUtils.isBlank(userInfo.getEncoded())) {
				String encode = queryWebUserInfoService.getNextEncode();
				userInfo.setEncoded(encode);
				saveWebUserInfoService.modifyWebUserInfoEncodedById(userInfo);
			}
			
			this.putSessionUser(userInfo);
			
			String result = this.sendResponseToJson("1","登录成功");
			logger.warn("web user login success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.debug("add web user info begin error! code=[{}]",new Object[] { });
			throw new RuntimeException();
		}
	}

	public WebUserInfoDamain getWebUserInfoDomain() {
		WebUserInfoDamain webUserInfoDamain = new WebUserInfoDamain();
		webUserInfoDamain.setCode(this.request.getParameter("code"));
		webUserInfoDamain.setPassword(this.request.getParameter("password"));
		return webUserInfoDamain;
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
