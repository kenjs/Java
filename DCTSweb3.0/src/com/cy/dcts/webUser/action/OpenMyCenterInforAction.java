package com.cy.dcts.webUser.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
/**
 * 打开个人中心页面
 * @date 2014-5-21
 * @author haoyong
 *
 */
public class OpenMyCenterInforAction extends BasePageAction{

	private static final long serialVersionUID = -3405507275020830870L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected String execMethod() throws Exception {
		return SUCCESS;
	}
	
}
