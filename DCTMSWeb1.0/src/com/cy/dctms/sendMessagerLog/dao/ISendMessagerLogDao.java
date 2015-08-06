package com.cy.dctms.sendMessagerLog.dao;

import java.util.List;

import com.cy.dctms.common.bo.SendMessagerLog;

public interface ISendMessagerLogDao {



	/**
	 * 保存发送消息日志信息
	 * @author:wjl
	 */
	public void saveSendMessagerLog(SendMessagerLog sendMessagerLog);
	
	/**
	 * 批量保存发送消息日志信息
	 * @author:wjl
	 */
	public void saveBunchMessagerLog(SendMessagerLog sendMessagerLog ,List<String> list);

}
