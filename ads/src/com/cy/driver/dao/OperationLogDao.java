package com.cy.driver.dao;

import com.cy.driver.bo.NoteLogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志dao
 * @author haoyong
 *
 */
@Repository("operationLogDao")
public interface OperationLogDao {

	/**
	 * 新增操作日志
	 */
	public int insertOperationLogBatch(List list);
	
	/**
	 *  短信发送记录新增
	 * @param entity
	 * @throws Exception
	 */
	public void insertNoteLogInfo(NoteLogInfo entity) throws Exception;
}
