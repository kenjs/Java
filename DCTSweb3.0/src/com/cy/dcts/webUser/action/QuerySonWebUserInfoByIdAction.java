package com.cy.dcts.webUser.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class QuerySonWebUserInfoByIdAction  extends BasePageAction {

	/**
	 * 根据id查询货源
	 */
	private static final long serialVersionUID = -1841579751302877494L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IQueryWebUserInfoService queryWebUserInfoService;
	
	@Override
	protected String execMethod() throws Exception {
		try {
			if(getSessionUser() == null) {
				return LOGIN;
			}
			String type = this.request.getParameter("type");
			WebUserInfoDamain webUserInfoDamain = getWebUserInfoDamain();
			webUserInfoDamain.setParentId(String.valueOf(getSessionUser().getId()));
			List<WebUserInfoDamain> list = queryWebUserInfoService.querySonWebUserInfoById(webUserInfoDamain);
			if(list.size()==1) {
				webUserInfoDamain.setId(list.get(0).getId());
				webUserInfoDamain.setCode(list.get(0).getCode());
				webUserInfoDamain.setCompanyName(list.get(0).getCompanyName());
				StringBuffer pcc = new StringBuffer();
				if(StringUtils.isNotEmpty(list.get(0).getCompanyProvince())) {
					pcc.append(list.get(0).getCompanyProvince());
				}
				if(StringUtils.isNotEmpty(list.get(0).getCompanyCity())) {
					pcc.append("-");
					pcc.append(list.get(0).getCompanyCity());
				}
				if(StringUtils.isNotEmpty(list.get(0).getCompanyCounty())) {
					pcc.append("-");
					pcc.append(list.get(0).getCompanyCounty());
				}
				webUserInfoDamain.setCompanyPcc(pcc.toString());
				webUserInfoDamain.setCompanyAddress(list.get(0).getCompanyAddress());
				webUserInfoDamain.setEncoded(list.get(0).getEncoded());
				webUserInfoDamain.setDeliveryFlag(list.get(0).getDeliveryFlag());
				webUserInfoDamain.setArrivalSure(list.get(0).getArrivalSure());
				webUserInfoDamain.setReceiveSure(list.get(0).getReceiveSure());
				webUserInfoDamain.setContactName(list.get(0).getContactName());
				webUserInfoDamain.setContactTelephone(list.get(0).getContactTelephone());
				webUserInfoDamain.setCreateTime(list.get(0).getCreateTime());
				webUserInfoDamain.setCompanyId(list.get(0).getCompanyId());
				webUserInfoDamain.setUserTypeVal(list.get(0).getUserTypeVal());
				this.request.setAttribute("webUserInfoDamain",webUserInfoDamain);
				if("select".equals(type)) {
					return "selectSuccess";
				}
				return SUCCESS;
			} 
			return ERROR;
		}catch (Exception e) {
			logger.error("query son web user page info  error!");
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
		if(StringUtils.isNotEmpty(this.request.getParameter("id"))) {
			webUserInfoDamain.setId(this.request.getParameter("id"));
		}
		return webUserInfoDamain;
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}
