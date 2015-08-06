package com.cy.dcts.transaction.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionReceiptPath;
import com.cy.dcts.common.domain.TransactionInfoDomain;
public interface ITransactionInfoDao {
	
	/**
	 * 根据交易Id换车（修改driverId）及交易状态
	 * @param id
	 * @return
	 */
	boolean modifyTrandeDriverAndOrderStart(TransactionInfoDomain transactionInfoDomain);
	
	/**
	 *  添加订单记录(包含了所有字段   货主版新增字段)
	 * @param transactionInfo
	 * @return
	 */
	String addTransactionInfos(TransactionInfo transactionInfo);
	/**
	 * 根据Id查询导入订单的信息 返回domain (货主版) 
	 * @param id 订单Id
	 * @return 
	 */
	TransactionInfoDomain queryImportTransactionInfoById(String id);
	
	/**
	 *  修改导入的订单（货主版）
	 * @param transactionInfoDomain
	 * @return
	 */
	boolean modifyImportTransactionInfo(TransactionInfoDomain transactionInfoDomain);
	
	/**
	 * 修改交易表中的发货时间或到货时间
	 * @param transactionInfoDomain
	 * @return
	 */
	boolean modifyDeliveryOrArrivalTime(TransactionInfoDomain transactionInfoDomain);
	
	/**
	 * 根据Id查询交易记录
	 * @param id
	 * @return
	 */
	TransactionInfo queryTransactionInfoById(String id);
	/**
	 * 我的订单 - 不分页
	 * @param queryMap 
	 * @return
	 */
	List<TransactionInfoDomain> queryTransactionInfoDomain(Map<String,Object> queryMap);
	
	/**
	 * 我的订单  - 分页
	 * @param queryMap 
	 * @return
	 */
	List<TransactionInfoDomain> queryTransactionInfoDomainByPage(Map<String,Object> queryMap);
	
	List<TransactionInfoDomain> queryTransactionInfoDomainByPage2(Map<String,Object> queryMap);
	
	/**
	 * 我的订单 总记录数
	 * @param queryMap
	 * @return 
	 */
	Integer queryTransactionInfoDomainCount(Map<String,Object> queryMap);
	
	Integer queryTransactionInfoDomainCount2(Map<String,Object> queryMap);
	
	/**
	 * 我的交易记录（成功或失败的订单）,待处理订单  - 不分页
	 * @param queryMap 
	 * @return
	 */
	List<TransactionInfoDomain> queryInOrNotInSuccessCloseTrandeDomain(Map<String,Object> queryMap);
	/**
	 * 我的交易记录（成功或失败的订单）,待处理订单  - 分页
	 * @param queryMap 
	 * @return
	 */
	List<TransactionInfoDomain> queryInOrNotInSuccessCloseTrandeDomainByPage(Map<String,Object> queryMap);
	
	/**
	 * 我的交易记录（成功或失败的订单）,待处理订单-总记录数
	 * @param queryMap
	 * @return 
	 */
	Integer queryInOrNotInSuccessCloseTrandeDomainCount(Map<String,Object> queryMap);
	
	/**
	 * 修改订单删除状态（订单是否有效）
	 * @return
	 */
	boolean modifyTransactionInfoOrderStart(TransactionInfoDomain transactionInfoDomain);
	

	/**
	 * 修改交易状态
	 * @return
	 */
	boolean modifyTransactionInfoTradeStart(TransactionInfoDomain transactionInfoDomain);
	
	/**
	 * 交易数量
	 * 每日新增
	 * 或者
	 * 总数量
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	Integer queryTransactionCount(Map<String,Object> queryMap);
	
	/**
	 * 根据CREATE_TIME=当前的日期（年月日）查询倒序 取第一条的Id 
	 * @return Id
	 */
	String queryTransactionIdByCreateTime();
	
	/**
	 * 添加订单记录
	 * @param addMap 
	 * @return Id
	 */
	String addTransactionInfo(Map<String,Object> addMap);
	
	/**
	 * 根据司机id查询司机订单完成订单列表分页
	 * @author nxj
	 * @param transactionInfoDomain
	 * @return
	 */
	List<TransactionInfoDomain> queryDriverCarTransactionInfoPage(Map<String,Object> queryMap);
	
	/**
	 * 根据司机id查询司机订单完成订单列表总数量
	 * @author nxj
	 * @return
	 */
	Integer queryDriverCarTransactionInfoPageCount(Map<String,Object> queryMap);
	
	/**
	 * 自己货源不同状态下的订单数
	 * @param userId 登录者ID
	 * @return TransactionInfoDomain 
	 */
	TransactionInfoDomain queryAllStartCount(String userId);
	
	/**
	 * 自己货源对应状态下的且未评价的订单数
	 * @param queryMap
	 * @return 
	 */
	String queryAllStartNoAssessCount(Map<String,Object> queryMap);
	
	/**
	 * 今日动态（当天达成的交易即司机确认拉货后的订单：运输中的，司机已卸货的，订单完成的）
	 * @param transactionInfoDomain
	 * @return
	 */
	List<TransactionInfoDomain> queryTodayDynamicTimeAndStart(Map<String,Object> queryMap);
	
	
	/**
	 * 根据交易Id查询回单上传表
	 * @param transactionReceiptPath
	 * @return
	 */
	List<TransactionReceiptPath> queryTransactionReceiptPathByTradeId(Map<String,Object> queryMap);
}
