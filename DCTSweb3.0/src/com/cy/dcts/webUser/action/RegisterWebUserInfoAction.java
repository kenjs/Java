package com.cy.dcts.webUser.action;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.common.util.ConvertWebUserInfoUtil;
import com.cy.dcts.common.util.MD5Util;
import com.cy.dcts.common.util.ValidateUtil;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.ipUrlStr.service.IIpUrlStrService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

/**
 * 用户注册
 * @author nxj
 *
 */
public class RegisterWebUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -7480714217579639032L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private WebUserInfoDamain webUserInfoDamain;
	private IQueryWebUserInfoService queryWebUserInfoService;
	private ISaveWebUserInfoService saveWebUserInfoService;
	private ICompanyService companyService;
	private IIpUrlStrService ipUrlStrService;
                     
	@Override
	protected String execMethod() throws Exception {
		try {
			webUserInfoDamain.setErrorMessage("");
			//请填写注册信息
			if(webUserInfoDamain == null) {
				webUserInfoDamain.setErrorMessage("0");
				logger.error("add web user info begin error. json=[{}]");
				return ERROR;
			}
			//用户名不能为空
			if(StringUtils.isEmpty(webUserInfoDamain.getCode())) {
				webUserInfoDamain.setErrorMessage("-1");
				logger.error("add web user info begin error. json=[{}]");
				return ERROR;
			}
			//密码不能为空
			if(StringUtils.isEmpty(webUserInfoDamain.getPassword())) {
				webUserInfoDamain.setErrorMessage("-2");
				logger.error("add web user info begin error. json=[{}]");
				return ERROR;
			}
			//公司名不能为空
			if(StringUtils.isEmpty(webUserInfoDamain.getCompanyName())) {
				webUserInfoDamain.setErrorMessage("-3");
				logger.error("add web user info begin error. json=[{}]");
				return ERROR;
			}
			//手机号码不能为空
			if(StringUtils.isEmpty(webUserInfoDamain.getMobilephone())) {
				webUserInfoDamain.setErrorMessage("-4");
				logger.error("add web user info begin error.");
				return ERROR;
			}
			//手机号码格式错误
			if(ValidateUtil.validateTelePhone(webUserInfoDamain.getMobilephone()) == false) {
				webUserInfoDamain.setErrorMessage("-5");
				logger.error("add web user info begin error. mobilephone=[{}], json=[{}]",new Object[] {webUserInfoDamain.getMobilephone()});
				return ERROR;
			}
			//验证码不能为空
			if(StringUtils.isEmpty(webUserInfoDamain.getMobilephone())) {
				webUserInfoDamain.setErrorMessage("-6");
				logger.error("add web user info begin error. mobilephone=[{}]",new Object[] { webUserInfoDamain.getMobilephone()});
				return ERROR;
			}
			webUserInfoDamain.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			webUserInfoDamain.setPassword(new MD5Util().getMD5ofStr(webUserInfoDamain.getPassword()));
			webUserInfoDamain.setPanymentFlag(String.valueOf(Constants.PANYMENT_FLAG_NOT));
			webUserInfoDamain.setPersonageFlag(String.valueOf(Constants.PERSONAGE_FLAG_NOT));
			webUserInfoDamain.setEnterpriseFlag(String.valueOf(Constants.ENTERPRISE_FLAG_NOT));
			webUserInfoDamain.setLoginIp(ipUrlStrService.getIpAddr(request));
			//用户来源，自己注册的0（20140716）
			webUserInfoDamain.setUserOrigin(Constants.USER_ORIGIN_REGISTRATION);
			
			//判断用户名是否存在
			WebUserInfo webUserInfoCode = new WebUserInfo();
			webUserInfoCode.setCode(webUserInfoDamain.getCode());
			webUserInfoCode.setDeletedFlag(webUserInfoDamain.getDeletedFlag());
			List<WebUserInfo> codeList = queryWebUserInfoService.queryWebUserInfo(webUserInfoCode);
			if(codeList.size() > 0) {
				webUserInfoDamain.setErrorMessage("-7");
				logger.error("add web user info begin error. userId=[{}], code=[{}], json=[{}]",
						new Object[] {codeList.get(0).getId(), codeList.get(0).getCode()});
				return ERROR;
			}
			//判断手机号码是否已经被绑定
			WebUserInfo webUserInfoMobilephone = new WebUserInfo();
			webUserInfoMobilephone.setMobilephone(webUserInfoDamain.getMobilephone());
			webUserInfoMobilephone.setDeletedFlag(webUserInfoDamain.getDeletedFlag());
			List<WebUserInfo> mobilephoneList = queryWebUserInfoService.queryWebUserInfo(webUserInfoMobilephone);
			if(mobilephoneList.size() > 0) {
				webUserInfoDamain.setErrorMessage("-8");
				logger.error("add web user info begin error. userId=[{}], mobilephone=[{}], json=[{}]",
						new Object[] {mobilephoneList.get(0).getId(), mobilephoneList.get(0).getMobilephone()});
				return ERROR;
			}
			//判断公司是否存在
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyName(webUserInfoDamain.getCompanyName());
			companyInfo.setDeletedFlag(webUserInfoDamain.getDeletedFlag());
			companyInfo.setUserOrigin(Constants.USER_ORIGIN_REGISTRATION);
			companyInfo.setCompanyType(webUserInfoDamain.getUserType());
			List<CompanyInfo> companyNameList = companyService.queryCompanyInfo(companyInfo);
			if(companyNameList.size() > 0) {
				webUserInfoDamain.setErrorMessage("-9");
				logger.error("add web company info begin error. companyId=[{}], companyName=[{}], json=[{}]",
						new Object[] {companyNameList.get(0).getId(), companyNameList.get(0).getCompanyName()});
				return ERROR;
			}else {
				String companyId = companyService.addCompanyInfo(companyInfo);
				logger.warn("add web company info begin success. companyId=[{}], companyName=[{}]",companyId, companyInfo.getCompanyName());
				webUserInfoDamain.setCompanyId(companyId);
			}
			String userId = saveWebUserInfoService.addWebUserInfo(ConvertWebUserInfoUtil.getWebUserInfoFromDomain(webUserInfoDamain));
			logger.warn("add web user info begin success. userId=[{}], code=[{}], mobilephone=[{}], companyName=[{}]",
					userId, webUserInfoDamain.getCode(), webUserInfoDamain.getMobilephone(), webUserInfoDamain.getCompanyName());
			WebUserInfo userInfo = queryWebUserInfoService.queryWebUserInfoById(userId);
			if(userInfo == null) {
				logger.warn("add web user info begin error. userId=[{}], code=[{}], mobilephone=[{}], companyName=[{}]",
						userId, webUserInfoDamain.getCode(), webUserInfoDamain.getMobilephone(), webUserInfoDamain.getCompanyName());
				return ERROR;
			}
			this.putSessionUser(userInfo);
			return SUCCESS;
		}catch (Exception e) {
			logger.debug("add web user info begin error! code=[{}]",new Object[] { webUserInfoDamain.getCode() });
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


	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}


	public void setIpUrlStrService(IIpUrlStrService ipUrlStrService) {
		this.ipUrlStrService = ipUrlStrService;
	}


	public WebUserInfoDamain getWebUserInfoDamain() {
		return webUserInfoDamain;
	}


	public void setWebUserInfoDamain(WebUserInfoDamain webUserInfoDamain) {
		this.webUserInfoDamain = webUserInfoDamain;
	}

	
}
