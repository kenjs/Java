package com.cy.dcts.identityVerify.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
/**
 * @description 打开身份验证页面
 * @author 		haoy
 *
 */
public class OpenIdetityVerifyPage extends BasePageAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2742961617982437323L;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String menuAId;
	
	public String getMenuAId() {
		return menuAId;
	}

	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}

	public OpenIdetityVerifyPage() {
	}

	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser() == null) {
			log.info("SESSION 已失效 或 用户为登陆. ");
			return LOGIN;
		}
		menuAId = request.getParameter("menuAId");
		return SUCCESS;
	}

}
