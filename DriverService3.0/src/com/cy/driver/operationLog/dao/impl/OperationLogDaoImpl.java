package com.cy.driver.operationLog.dao.impl;

import java.sql.SQLException;

import com.cy.common.bo.NoteLogInfo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.operationLog.dao.OperationLogDao;
/**
 * 操作日志记录dao impl
 * @author haoyong
 *
 */
public class OperationLogDaoImpl extends BaseDao implements OperationLogDao {

	public int insertOperationLog(OperationLogInfoBo bo) {
		int key = 0;
		try {
			key = addObject("iBatisInsertOperationLogInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	public void insertNoteLogInfo(NoteLogInfo entity) throws Exception {
		addObject("iBatisInsertNoteLogInfo", entity);
	}

}
