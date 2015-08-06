package com.cy.dcts.company.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;


/**
 * 根据公司名称查询判断公司是否存在
 * @author Administrator
 *
 */
public class QueryCompanyByNameAction extends BaseJsonAction {

	private static final long serialVersionUID = 2258268519275237656L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ICompanyService companyService;
	private IQueryWebUserInfoService queryWebUserInfoService;
	
	@Override
	protected void execMethod() throws Exception {
		String companyName = this.request.getParameter("companyName");
		String userType = this.request.getParameter("userType");
		try {
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyName(companyName);
			companyInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			if(StringUtils.isNotEmpty(userType)) {
				if (getSessionUser() == null) {
					getLogin();
				}
				companyInfo.setParentCompanyId(getSessionUser().getCompanyId());
				companyInfo.setCompanyType(userType);
			}else {
				companyInfo.setParentCompanyId("0");
			}
			List<CompanyInfo> list = new ArrayList<CompanyInfo>();
			if(StringUtils.isNotEmpty(userType)) {
				list = companyService.querySonCompanyInfo(companyInfo);
			}else {
				list = companyService.queryCompanyInfo(companyInfo);
			}
			if(list.size() > 0) {
					WebUserInfo webUserInfo = new WebUserInfo();
					webUserInfo.setCompanyId(list.get(0).getId());
					webUserInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
					List<WebUserInfo> userList = queryWebUserInfoService.queryWebUserInfo(webUserInfo);
					if(userList.size() > 0) {
						String result = this.sendResponseToJson("1","公司名称已存在!");
						logger.warn("query user code success. companyName=[{}], companyId=[{}], json=[{}]",new Object[] {companyName, companyInfo.getId(), result });
					}
			}
		}catch (Exception e) {
			logger.error("query user code error! companyName=[{}]",new Object[] { companyName });
			throw new RuntimeException();
		}
		
	}
	
	public String getLogin() {
		return LOGIN;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}
