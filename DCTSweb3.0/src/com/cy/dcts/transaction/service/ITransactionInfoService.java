package com.cy.dcts.transaction.service;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionReceiptPath;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.TransactionInfoDomain;

public interface ITransactionInfoService {
	/**
	 * 根据交易Id换车（修改driverId）及交易状态
	 * @param id
	 * @return
	 */
	boolean modifyTrandeDriverAndOrderStart(TransactionInfoDomain transactionInfoDomain);
	/**
	 * 货源订单记录(货主版) 
	 * @param list
	 * @throws Exception
	 */
	public List<String> batchAddTransactionInfos(List<TransactionInfo> tradeList,List<OrderCargoInfo> cargoList,WebUserInfo userInfo) throws Exception;
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
	 * 我的订单记录 - 分页
	 * @param queryMap 
	 * @return
	 */
	List<TransactionInfoDomain> queryTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain,String userId);
	
	public List<TransactionInfoDomain> queryTransactionInfoDomain2(TransactionInfoDomain transactionInfoDomain,String userId,String parentId);
	
	/**
	 * 交易记录（成功或失败的订单） - 分页
	 * @param queryMap 
	 * @return
	 */
	List<TransactionInfoDomain> queryInOrNotInSuccessCloseTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain,String userId);
	
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
	 * @param createTime
	 * @return
	 */
	Integer queryTransactionCount(String createTime);
	
	
	/**
	 * 添加订单记录
	 * @param addMap 
	 * @return Id
	 */
	String addTransactionInfo(TransactionInfo transactionInfo,WebUserInfo userInfo);
	
	
	/**
	 * 根据司机id查询司机订单完成订单列表
	 * @param transactionInfoDomain
	 * @return
	 */
	List<TransactionInfoDomain> queryDriverCarTransactionInfo(TransactionInfoDomain transactionInfoDomain);
	
	/**
	 * 自己货源不同状态下的订单数
	 * @param userId 登录者ID 
	 *         tradeStart 交易状态
	 * @return TransactionInfoDomain 
	 */
	TransactionInfoDomain queryAllStartCount(String userId,String tradeStart);
	/**
	 * 今日动态（先查当天达成的交易即司机确认拉货后的订单：运输中的，司机已卸货的，订单完成的；若没有交易再查今天注册的司机）
	 * @param transactionInfoDomain
	 * @return
	 */
	List<TransactionInfoDomain> queryTodayDynamicTimeAndStart();
		
	/**
	 * 根据交易Id查询回单上传表
	 * @param transactionReceiptPath
	 * @return
	 */
	List<TransactionReceiptPath> queryTransactionReceiptPathByTradeId(String transactionId,String type);
	
}
