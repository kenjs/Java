package com.cy.driver.receipt.service;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.TransactionReceiptPathBo;
import com.cy.driver.receipt.domain.TransactionReceiptPathDomain;

public interface TransactionReceiptPathService {

	/**
	 * 回单保存
	 * @param bo
	 * @return
	 * @throws Exception
	 */
	public int insertTransactionReceiptPath(TransactionReceiptPathBo bo) throws Exception;
	
	/**
	 * 删除
	 * @param list 主键集合
	 * @return
	 * @throws Exception
	 */
	public int deleteTransactionReceiptPath(List<String> list) throws Exception;
	
	public List<String> selectReceiptPathById(List<String> list) throws Exception;
	
	/**
	 * 根据订单id查询
	 * @return
	 * @throws Exception
	 */
	public List<TransactionReceiptPathDomain> selectReceiptListByTransactionId(Map<String,String> map) throws Exception;
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TransactionReceiptPathDomain selectReceiptById(String id) throws Exception;
	
}
