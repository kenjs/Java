package com.cy.dctms.sendMessagerLog.dao;

import java.util.List;

import com.cy.dctms.common.bo.SendMessagerLog;

public interface ISendMessagerLogDao {



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
