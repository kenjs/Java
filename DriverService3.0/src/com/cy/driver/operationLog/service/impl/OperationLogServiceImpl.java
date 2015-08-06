package com.cy.driver.operationLog.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.NoteLogInfo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.dao.OperationLogDao;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.dao.LoginUserInfoDao;
/**
 * 操作日志service imple
 * @author haoyong
 *
 */
public class OperationLogServiceImpl implements OperationLogService {

	private OperationLogDao operationLogDao;
	private LoginUserInfoDao loginUserInfoDao;
	
	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

	public void setLoginUserInfoDao(LoginUserInfoDao loginUserInfoDao) {
		this.loginUserInfoDao = loginUserInfoDao;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public int insertOperationLog(OperationLogInfoBo bo) {
		return operationLogDao.insertOperationLog(bo);
	}

	public int checkUser(String id) {		
		int freezeFlag = loginUserInfoDao.checkUser(id);
		if(freezeFlag == -1) {
			return 1;
		}
		if(freezeFlag == 1) {
			return 11;
		}
		return 0;
	}

	public void insertNoteLogInfo(NoteLogInfo entity) throws Exception {
		operationLogDao.insertNoteLogInfo(entity);
	}
	
}
