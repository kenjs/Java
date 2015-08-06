package com.cy.dcts.identityVerify.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.IdentityVerifyLogDomain;
import com.cy.dcts.identityVerify.service.IdentityVerifyService;
/**
 * @description 身份验证action
 * @author 		haoy
 *
 */
public class IdentityVerifyAction extends BasePageAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5762291736095700810L;
	private IdentityVerifyService identityVerifyService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private IdentityVerifyLogDomain domain;
	
	@Override
	protected String execMethod() throws Exception {
		
		WebUserInfo webUserInfo = getSessionUser();
		if(webUserInfo == null) {
			log.info("SESSION 已失效 或 用户为登陆. ");
			return LOGIN;
		}
		
		String IDNumber = request.getParameter("IDNumber");
		String name = request.getParameter("name");
		
		if(StringUtils.isBlank(IDNumber) || StringUtils.isBlank(name)) {
			log.info("要验证的身份证号码或姓名为空");
			domain.setAccountMsg("要验证的身份证号码或姓名为空");
			return SUCCESS;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("IDNumber", IDNumber);
		map.put("name", name);
		
		domain = identityVerifyService.identityVerify(map, webUserInfo.getId());
		return SUCCESS;
	}

	public IdentityVerifyLogDomain getDomain() {
		return domain;
	}

	public void setDomain(IdentityVerifyLogDomain domain) {
		this.domain = domain;
	}

	public void setIdentityVerifyService(IdentityVerifyService identityVerifyService) {
		this.identityVerifyService = identityVerifyService;
	}

}
