package com.cy.dctms.sendMessagerLog.service.imp;


import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dctms.common.bo.SendMessagerLog;
import com.cy.dctms.sendMessagerLog.dao.ISendMessagerLogDao;
import com.cy.dctms.sendMessagerLog.service.ISendMessagerLogService;

public class SendMessagerLogServiceImp implements ISendMessagerLogService {

	private ISendMessagerLogDao sendMessagerLogDao;

	@Override
	public void saveSendMessagerLog(SendMessagerLog sendMessagerLog) {		
		sendMessagerLogDao.saveSendMessagerLog(sendMessagerLog);
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void saveBunchMessagerLog(SendMessagerLog sendMessagerLog,List<String> list) {
		sendMessagerLogDao.saveBunchMessagerLog(sendMessagerLog ,list);
		
	}

	public void setSendMessagerLogDao(ISendMessagerLogDao sendMessagerLogDao) {
		this.sendMessagerLogDao = sendMessagerLogDao;
	}
}
