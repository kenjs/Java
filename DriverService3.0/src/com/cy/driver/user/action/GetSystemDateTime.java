package com.cy.driver.user.action;

import java.io.IOException;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.util.DateUtil;
/**
 * 获取系统当前时间 yyyy-MM-dd hh:mm:ss
 * @author Administrator
 *
 */
public class GetSystemDateTime extends BaseJsonAction{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8806145223592045865L;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String currTime = DateUtil.getCurrentDateTime();
			sendResponseToJson("1", "获取系统当前时间成功",currTime);
		} catch (IOException e) {
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
