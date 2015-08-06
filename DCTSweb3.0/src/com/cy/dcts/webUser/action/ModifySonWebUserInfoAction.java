package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;
/**
 * 子账号修改
 * @author Administrator
 *
 */
public class ModifySonWebUserInfoAction extends BasePageAction {

	
	private static final long serialVersionUID = -2196393030063602L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	private WebUserInfoDamain webUserInfoDamain;
	private ISaveWebUserInfoService saveWebUserInfoService;
	private ICompanyService companyService;
	@Override
	protected String execMethod() throws Exception {
		try {
			//判断是否登陆
			if (getSessionUser() == null) {
				return LOGIN;
			}
			//判断对象是否为空
			if(webUserInfoDamain == null) {
				webUserInfoDamain.setErrorMessage("-1");
				logger.error("add web user info begin error. json=[{}]");
				return ERROR;
			}
			
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setId(webUserInfoDamain.getCompanyId());
			companyInfo.setParentCompanyId(getSessionUser().getCompanyId());
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
			companyInfo.setCompanyAddress(webUserInfoDamain.getCompanyAddress());
			companyInfo.setContactName(webUserInfoDamain.getContactName());
			companyInfo.setContactTelephone(webUserInfoDamain.getContactTelephone());
			companyService.modifySonCompanyInfoById(companyInfo);
			
			WebUserInfo webUserInfo = new WebUserInfo();
			webUserInfo.setId(webUserInfoDamain.getId());
			webUserInfo.setParentId(getSessionUser().getId());
			webUserInfo.setDeliveryFlag(webUserInfoDamain.getDeliveryFlag());
			webUserInfo.setArrivalSure(webUserInfoDamain.getArrivalSure());
			webUserInfo.setReceiveSure(webUserInfoDamain.getReceiveSure());
			saveWebUserInfoService.modifySonWebUserById(webUserInfo);
			return SUCCESS;
		}catch (Exception e) {
			logger.debug("modify web user info begin error! code=[{}]",new Object[] { webUserInfoDamain.getCode() });
			throw new RuntimeException();
		}
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


}
