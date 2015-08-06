package com.cy.dcts.webUser.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class QuerySonWebUserInfoAction extends BasePageAction {

	/**
	 * 查询子账号列表
	 */
	private static final long serialVersionUID = -6599168460125220056L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IQueryWebUserInfoService queryWebUserInfoService;

	
	@Override
	protected String execMethod() throws Exception {
		try {
			if(getSessionUser() == null) {
				return LOGIN;
			}
			WebUserInfoDamain webUserInfoDamain = getWebUserInfoDamain();
			webUserInfoDamain.setParentId(String.valueOf(getSessionUser().getId()));
			List<WebUserInfoDamain> list = queryWebUserInfoService.querySonWebUserInfoPage(webUserInfoDamain);
			webUserInfoDamain.setList(list);
			this.request.setAttribute("webUserInfoDamain",webUserInfoDamain);
			return SUCCESS;
		}catch (Exception e) {
			logger.error("query son web user page info  error!");
			throw new RuntimeException();
		}
		
	}
	
	public WebUserInfoDamain getWebUserInfoDamain() {
		WebUserInfoDamain webUserInfoDamain = new WebUserInfoDamain();
		PageInfo pageInfo = new PageInfo();
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
		if(StringUtils.isNotEmpty(this.request.getParameter("pageSize"))) {
			pageInfo.setPageSize(Integer.parseInt(this.request.getParameter("pageSize")));
		}else {
			pageInfo.setPageSize(10);
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("curPage"))) {
			if("0".equals(this.request.getParameter("curPage"))) {
				pageInfo.setCurPage(0);
			}else {
				pageInfo.setCurPage(Integer.parseInt(this.request.getParameter("curPage"))-1);
				pageInfo.setCurPageNo(Integer.parseInt(this.request.getParameter("curPage")));
			}
		}else {
			pageInfo.setCurPage(0);
		}
		webUserInfoDamain.setPageInfo(pageInfo);
		return webUserInfoDamain;
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}


	
}
