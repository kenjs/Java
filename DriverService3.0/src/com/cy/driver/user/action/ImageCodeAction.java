package com.cy.driver.user.action;

import com.cy.common.action.BaseAction;
import com.cy.common.util.ImgCodeUtil;

public class ImageCodeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3853646818383505963L;

	@Override
	public String exec() throws Exception {
		ImgCodeUtil.drawImg(0, 0, response);
		return null;
	}

}
