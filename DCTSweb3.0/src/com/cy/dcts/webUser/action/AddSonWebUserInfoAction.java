package com.cy.dcts.webUser.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.WebUserInfoDamain;

public class AddSonWebUserInfoAction extends BasePageAction {

	/**
	 * 新增我的客户页面
	 */
	private static final long serialVersionUID = -6655592130985359145L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected String execMethod() throws Exception {
		try {
			if(getSessionUser() == null) {
				return LOGIN;
			}
			WebUserInfoDamain webUserInfoDamain = getWebUserInfoDamain();
			this.request.setAttribute("webUserInfoDamain",webUserInfoDamain);
			return SUCCESS;
		}catch (Exception e) {
			logger.error("add son web user info  error!");
			throw new RuntimeException();
		}
	}
	
	public WebUserInfoDamain getWebUserInfoDamain() {
		WebUserInfoDamain webUserInfoDamain = new WebUserInfoDamain();
		if(StringUtils.isNotEmpty(this.request.getParameter("menuAId"))) {
			webUserInfoDamain.setMenuAId(this.request.getParameter("menuAId"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("userType"))) {
			webUserInfoDamain.setUserType(this.request.getParameter("userType"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("companyName"))) {
			webUserInfoDamain.setCompanyName(this.request.getParameter("companyName"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("createTimeStart"))) {
			webUserInfoDamain.setCreateTimeStart(this.request.getParameter("createTimeStart"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("createTimeEnd"))) {
			webUserInfoDamain.setCreateTimeEnd(this.request.getParameter("createTimeEnd"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("deletedFlag"))) {
			webUserInfoDamain.setDeletedFlag(this.request.getParameter("deletedFlag"));
		}else {
			webUserInfoDamain.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("companyPcc"))) {
			String[] companyPcc = this.request.getParameter("companyPcc").split("-");
			for(int i = 0;i < companyPcc.length;i++) {
				if(i == 0) {
					webUserInfoDamain.setCompanyProvince(companyPcc[0]);
				}else if(i == 1) {
					webUserInfoDamain.setCompanyCity(companyPcc[1]);
				}else if(i == 2) {
					webUserInfoDamain.setCompanyCounty(companyPcc[2]);
				}
			}
			webUserInfoDamain.setCompanyPcc(this.request.getParameter("companyPcc"));
		}
		return webUserInfoDamain;
	}

}
