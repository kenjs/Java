package com.cy.driver.assess.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverUserAssessInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.assess.dao.DriverUserAssessInfoDao;
import com.cy.driver.assess.domain.AssessDomain;
import com.cy.driver.assess.domain.UserDriverAssessInfoDomain;
/**
 * 货源评价dao impl
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class DriverUserAssessInfoDaoImpl extends BaseDao implements DriverUserAssessInfoDao{

	public int addNewDriverUserAssessInfo(DriverUserAssessInfoBo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertDriverUserAssessInfo", bo);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return i;
	}

	public int updateDriverUserAssessInfo(DriverUserAssessInfoBo bo) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateDriverUserAssessInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	
	public int selectAssessNum(DriverUserAssessInfoBo bo) {
		int key = 0;
		try {
			Object obj = queryForObject("iBatisSelectAssessNum", bo);
			if(obj != null) {
				key = (Integer) obj;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return key;
	}
	
	public int selectDriverUserAssess(String transactionId) {
		int count = 0;
		try {
			count = (Integer) queryForObject("iBatisSelectDriverUserAssessByTransactionId", transactionId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssessDomain> selectUserDriverAssessNum(String driverId) throws Exception {
		List<AssessDomain> list = null;
		try {
			list = (List<AssessDomain>) queryForList("iBatisSelectUserDirverAssessNum", driverId);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserDriverAssessInfoDomain> selectUserDriverAssessInfoList(Map<String,Object> map)
			throws Exception {
		List<UserDriverAssessInfoDomain> list = null;
		list = (List<UserDriverAssessInfoDomain>) queryForList("iBatisSelectUserDriverAssessInfo", map);
		return list;
	}
	
}
