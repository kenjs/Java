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
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.ipUrlStr.service.IIpUrlStrService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

public class RegisterSonWebUserInfoAction extends BasePageAction {

	/**
	 * 维护子账号
	 */
	private static final long serialVersionUID = 8954383951918057L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private WebUserInfoDamain webUserInfoDamain;
	private IQueryWebUserInfoService queryWebUserInfoService;
	private ISaveWebUserInfoService saveWebUserInfoService;
	private ICompanyService companyService;
	private IIpUrlStrService ipUrlStrService;
	
	@Override
	protected String execMethod() throws Exception {
		try {
			//判断是否登陆
			if (getSessionUser() == null) {
				return LOGIN;
			}
			//判断对象是否为空
			if(webUserInfoDamain == null) {
				
				return null;
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
			if(StringUtils.isEmpty(webUserInfoDamain.getDeliveryFlag())) {
				webUserInfoDamain.setDeliveryFlag("0");
			}
			if(StringUtils.isEmpty(webUserInfoDamain.getArrivalSure())) {
				webUserInfoDamain.setArrivalSure("0");
			}
			if(StringUtils.isEmpty(webUserInfoDamain.getReceiveSure())) {
				webUserInfoDamain.setReceiveSure("0");
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
			//判断公司是否存在
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyName(webUserInfoDamain.getCompanyName());
			companyInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			companyInfo.setUserOrigin(Constants.USER_ORIGIN_REGISTRATION);
			companyInfo.setParentCompanyId(getSessionUser().getCompanyId());
			companyInfo.setCompanyType(webUserInfoDamain.getUserType());
			List<CompanyInfo> companyNameList = companyService.querySonCompanyInfo(companyInfo);
			if(companyNameList.size() > 0) {
				WebUserInfo webUserInfo = new WebUserInfo();
				webUserInfo.setCompanyId(companyNameList.get(0).getId());
				webUserInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
				List<WebUserInfo> userList = queryWebUserInfoService.queryWebUserInfo(webUserInfo);
				if(userList.size() > 0) {
					webUserInfoDamain.setErrorMessage("-9");
					logger.error("add web company info begin error. companyId=[{}], companyName=[{}], json=[{}]",
							new Object[] {companyNameList.get(0).getId(), companyNameList.get(0).getCompanyName()});
					return ERROR;
				}
				webUserInfoDamain.setCompanyId(companyNameList.get(0).getId());
			}else {
				if(StringUtils.isNotEmpty(webUserInfoDamain.getCompanyPcc())) {
					String[] companyPcc = webUserInfoDamain.getCompanyPcc().split("-");
					for(int i = 0;i < companyPcc.length;i++) {
						if(i == 0) {
							companyInfo.setCompanyProvince(companyPcc[0]);
						}else if(i == 1) {
							companyInfo.setCompanyCity(companyPcc[1]);
						}else if(i == 2) {
							companyInfo.setCompanyCounty(companyPcc[2]);
						}
					}
				}
				companyInfo.setContactName(webUserInfoDamain.getContactName());
				companyInfo.setContactTelephone(webUserInfoDamain.getContactTelephone());
				companyInfo.setCompanyAddress(webUserInfoDamain.getCompanyAddress());
				String companyId = companyService.addSonCompanyInfo(companyInfo);
				logger.warn("add web company info begin success. companyId=[{}], companyName=[{}]",companyId, companyInfo.getCompanyName());
				webUserInfoDamain.setCompanyId(companyId);
			}
			//新增子级用户
			webUserInfoDamain.setParentId(getSessionUser().getId());
			String userId = saveWebUserInfoService.addSonWebUserInfo(ConvertWebUserInfoUtil.getWebUserInfoFromDomain(webUserInfoDamain));
			logger.warn("add web user info begin success. userId=[{}], code=[{}], mobilephone=[{}], companyName=[{}]",
					userId, webUserInfoDamain.getCode(), webUserInfoDamain.getMobilephone(), webUserInfoDamain.getCompanyName());
			WebUserInfo userInfo = queryWebUserInfoService.queryWebUserInfoById(userId);
			if(userInfo == null) {
				logger.warn("add web user info begin error. userId=[{}], code=[{}], mobilephone=[{}], companyName=[{}]",
						userId, webUserInfoDamain.getCode(), webUserInfoDamain.getMobilephone(), webUserInfoDamain.getCompanyName());
				return ERROR;
			}
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


	public WebUserInfoDamain getWebUserInfoDamain() {
		return webUserInfoDamain;
	}


	public void setWebUserInfoDamain(WebUserInfoDamain webUserInfoDamain) {
		this.webUserInfoDamain = webUserInfoDamain;
	}

	public void setIpUrlStrService(IIpUrlStrService ipUrlStrService) {
		this.ipUrlStrService = ipUrlStrService;
	}
	
	
}
