package com.cy.driver.dao;

import com.cy.driver.bo.PactDriverInfo;
import com.cy.driver.domain.PactDriverInfoDomain;
import com.cy.driver.domain.VipDriverLineInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @description 会员操作dao
 * @author 		haoy
 *
 */
@Repository("pactDriverInfoDao")
public interface PactDriverInfoDao {

	/**
	 * 查询会员列表
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<PactDriverInfoDomain> selectPactDriverInfoList(Map<String,String> map) throws Exception;

	/**
	 * 修改会员状态
	 * @param info
	 * @return	int
	 * @throws Exception
	 */
	public int updatePactDriverInfo(PactDriverInfo info) throws Exception;

	/**
	 * 查询VIP会员线路数
	 * @param pactDriverId
	 * @return	int
	 * @throws SQLException
	 */
	public int queryVipDriverLineNum(String pactDriverId) throws SQLException;

	/**
	 * vip会员线路
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<VipDriverLineInfoDomain> queryVipDriverLineList(Map<String,String> map) throws SQLException;

    /**
     * 修改VIP会员线路状态
     * @param  map
     * @return int
     * @throws SQLException
     */
    public int updateVipDriverLineInfo(Map<String, Object> map) throws SQLException;
}
