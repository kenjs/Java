package com.cy.dcts.webUser.action;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;

/**
 * 用户退出
 * @author nxj
 *
 */
public class WebUserInfoLogoutAction extends BaseJsonAction {

	
	private static final long serialVersionUID = -8995375975457132138L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void execMethod() throws Exception {
		if(getSessionUser() == null){
			return;
		}
		logger.debug("web user info logout... userId=[{}], userCode=[{}], userName=[{}",
				new Object[]{getSessionUser().getId(), getSessionUser().getCode(), 
				getSessionUser().getName()});
		putSessionUser(null);
		return;
	}

}
