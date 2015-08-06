package com.cy.driver.service;

import com.cy.driver.bo.PactDriverInfo;
import com.cy.driver.domain.PactDriverInfoDomain;
import com.cy.driver.domain.VipDriverLineInfoDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @description 会员操作service
 * @author 		haoy
 *
 */
public interface PactDriverInfoService {

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
