package com.cy.dcts.log.dao.impl;

import java.sql.SQLException;

import com.cy.dcts.common.bo.OperationLogInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.log.dao.OperationLogDao;
/**
 * 操作日志dao imple
 * @author haoyong
 *
 */
public class OperationLogDaoImpl extends BaseDao implements OperationLogDao {

	public int insertOperationLog(OperationLogInfo bo) {
		int key = 0;
		try {
			key = addObject("insert_operation_log_info", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

}
