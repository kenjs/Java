package com.cy.driver.dao;

import com.cy.driver.bo.TransactionLastInfoBo;
import com.cy.driver.domain.TransactionInfoDomain;
import com.cy.driver.domain.WebUserInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 订单dao
 * @date 2014-6-5
 * @author haoyong
 *
 */
@Repository("transactionInfoDao")
public interface TransactionInfoDao {

	/**
	 * 司机订单条数
	 * @param driverId
	 * @return
	 */
	public int selectDriverOrderNumber(String driverId);


	/**
	 * 我的订单条数
	 * @param driverId
	 * @return
	 */
	public int selectMyOrderNumber(String driverId);

	/**
	 * 司机订单列表
	 * @param map
	 * @return
	 */
	public List<?> selectDriverOrderList(Map<String,Object> map);

	/**
	 * 订单详情
	 * @param id
	 * @param id
	 * @return
	 */
	public TransactionInfoDomain selectDriverOrderDetail(String id);

	/**
	 * 更改订单状态
	 * @param map
	 * @return
	 */
	public int updateTransactionInfoById(Map<String,Object> map);

	/**
	 * 已经成交的货源
	 * @param map
	 * @return
	 */
	public List<TransactionInfoDomain> selectDoneCargoList(Map<String,Object> map);

	/**
	 * 新增交易状态历史
	 * @param bo
	 * @return
	 */
	public int addTransactionLastInfo(TransactionLastInfoBo bo);

	/**
	 * 根据id查找订单状态
	 * @param id
	 * @return
	 */
	public Map<String,Long> selectTransactionStartById(String id);

	/**
	 * 根据司机手机号码查找订单数量
	 * @param driverMobiphone
	 * @return
	 * @throws SQLException
	 */
	public List<TransactionInfoDomain> selectTransactionInfoByDriverMobiphone(String driverMobiphone) throws SQLException;

	/**
	 * 根据 ‘发货单号’查询订单信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<TransactionInfoDomain> selectTransactionByShipperOrderNo(Map<String,String> map)
			throws Exception;

	/**
	 * 查找物流公司id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public WebUserInfoDomain selectShipperCompanyId(Map<String,String> map) throws Exception;

	/**
	 * 查询待确认的货单数目
	 * @param driverId
	 * @return
	 * @throws SQLException
	 */
	public int queryWaitingConfirmReceiptNum(String driverId) throws SQLException;

	/**
	 * 查询导入的订单信息
	 * @param driverId
	 * @return
	 * @throws SQLException
	 */
	public List<TransactionInfoDomain> queryTransactionGroupByCompany(String driverId) throws SQLException;

	/**
	 * 查询分组订单详情
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<TransactionInfoDomain> queryGroupDetail(Map<String, String> map) throws SQLException;

	/**
	 * 批量修改订单状态
	 * @param map
	 * @throws SQLException
	 */
	public void batchUpdateTransactionStatue(Map<String, Object> map) throws SQLException;
}
