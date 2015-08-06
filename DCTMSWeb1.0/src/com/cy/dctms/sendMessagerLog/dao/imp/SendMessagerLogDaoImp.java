package com.cy.dctms.sendMessagerLog.dao.imp;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.SendMessagerLog;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.sendMessagerLog.dao.ISendMessagerLogDao;


public class SendMessagerLogDaoImp extends BaseDao implements ISendMessagerLogDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void saveSendMessagerLog(SendMessagerLog sendMessagerLog) {
		try {
			addObject("add_sendMessagerLog_info", sendMessagerLog);
		} catch (Exception e) {
			logger.error("save_sendMessagerLog",e);
		}
	}

	@Override
	public void saveBunchMessagerLog(SendMessagerLog sendMessagerLog ,List<String> list) {
		try {
			List<SendMessagerLog> dataList = new ArrayList<SendMessagerLog>();
			SendMessagerLog bo = new SendMessagerLog();
			for (String s : list) {
				bo = new SendMessagerLog();
				bo.setChannelType(sendMessagerLog.getChannelType());
				bo.setContent(sendMessagerLog.getContent());
				bo.setManagerId(sendMessagerLog.getManagerId());
				bo.setOrigin(sendMessagerLog.getOrigin());
				bo.setResultValue(sendMessagerLog.getResultValue());
				bo.setSendNumber(s);
				bo.setSendState(sendMessagerLog.getSendState());
				bo.setSendType(sendMessagerLog.getSendType());
				bo.setTitle(sendMessagerLog.getTitle());
				dataList.add(bo);
			}
			addObject("add_bunch_messager_log_info", dataList);
		} catch (Exception e) {
			logger.error("save_bunch_MessagerLog",e);
		}
		
	}
	
}
