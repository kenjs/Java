package com.cy.driver.service.impl;

import com.cy.driver.bo.NoteLogInfo;
import com.cy.driver.dao.DriverUserCargoInfoDao;
import com.cy.driver.dao.OperationLogDao;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.OperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 操作日志service imple
 * @author haoyong
 *
 */
@Service("OperationLogService")
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
	private OperationLogDao operationLogDao;

    @Resource
    private DriverUserCargoInfoDao driverUserCargoInfoDao;

	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

//	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
//	public int insertOperationLog(OperationLogInfoBo bo) {
//		return operationLogDao.insertOperationLog(bo);
//	}

	public DriverUserInfoDomain checkUser(String id) {
		return driverUserCargoInfoDao.selectUserBasicInfo(id);
	}

	public void insertNoteLogInfo(NoteLogInfo entity) throws Exception {
		operationLogDao.insertNoteLogInfo(entity);
	}

    public void setDriverUserCargoInfoDao(DriverUserCargoInfoDao driverUserCargoInfoDao) {
        this.driverUserCargoInfoDao = driverUserCargoInfoDao;
    }
}
