package com.cy.dctms.sendMessagerLog.service;

import java.util.List;

import com.cy.dctms.common.bo.SendMessagerLog;

public interface ISendMessagerLogService {
	

	/**
	 * ���淢����Ϣ��־��Ϣ
	 * @author:wjl
	 */
	public void saveSendMessagerLog(SendMessagerLog sendMessagerLog);
	
	/**
	 * �������淢����Ϣ��־��Ϣ
	 * @author:wjl
	 */
	public void saveBunchMessagerLog(SendMessagerLog sendMessagerLog ,List<String> list);
	
	
}
	
