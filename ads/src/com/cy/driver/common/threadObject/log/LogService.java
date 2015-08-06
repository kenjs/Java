package com.cy.driver.common.threadObject.log;

import com.cy.driver.bo.OperationLogInfoBo;

import java.sql.SQLException;
import java.util.List;


public interface LogService {

	public int insertOperationLog(List<OperationLogInfoBo> list) throws SQLException;
}
