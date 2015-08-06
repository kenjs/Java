package com.cy.driver.operationLog.service;

import com.cy.common.bo.NoteLogInfo;
import com.cy.common.bo.OperationLogInfoBo;

/**
 * 操作日志service
 * @author haoyong
 *
 */
public interface OperationLogService {

	/**
	 * 新增操作日志
	 * @param bo
	 * @return
	 */
	public int insertOperationLog(OperationLogInfoBo bo);
	
	/**
	 * 判断用户有效性
	 * @param id
	 * @return{0-->用户正常,1-->用户不存在或已删除,11-->用户被冻结}
	 */
	public int checkUser(String id);
	
	/**
	 *  短信发送记录新增
	 * @param entity
	 * @throws Exception
	 */
	public void insertNoteLogInfo(NoteLogInfo entity) throws Exception;
}
