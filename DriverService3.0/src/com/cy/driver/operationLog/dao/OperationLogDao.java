package com.cy.driver.operationLog.dao;

import com.cy.common.bo.NoteLogInfo;
import com.cy.common.bo.OperationLogInfoBo;

/**
 * 操作日志dao
 * @author haoyong
 *
 */
public interface OperationLogDao {

	/**
	 * 新增操作日志
	 */
	public int insertOperationLog(OperationLogInfoBo bo);
	
	/**
	 *  短信发送记录新增
	 * @param entity
	 * @throws Exception
	 */
	public void insertNoteLogInfo(NoteLogInfo entity) throws Exception;
}
