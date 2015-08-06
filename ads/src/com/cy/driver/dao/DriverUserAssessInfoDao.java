package com.cy.driver.dao;

import com.cy.driver.bo.DriverUserAssessInfoBo;
import com.cy.driver.domain.AssessDomain;
import com.cy.driver.domain.UserDriverAssessInfoDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 货源评价dao
 * @date 2014-6-9
 * @author haoyong
 *
 */
@Repository("driverUserAssessInfoDao")
public interface DriverUserAssessInfoDao {

	/**
	 * 新增货源评价
	 * @param bo
	 * @return
	 */
	public int addNewDriverUserAssessInfo(DriverUserAssessInfoBo bo);
	
	/**
	 * 修改货源评价
	 * @param bo
	 * @return
	 */
	public int updateDriverUserAssessInfo(DriverUserAssessInfoBo bo);
	
	/**
	 * 判断是否已评价
	 * @param bo
	 * @return
	 */
	public Object selectAssessNum(DriverUserAssessInfoBo bo);
	
	/**
	 * 根据订单id查找评价
	 * @param driverId
	 * @return
	 */
	public int selectDriverUserAssess(String driverId);
	
	/**
	 * 查询用户对司机的评价信息
	 * @param driverId
	 * @return
	 */
	public List<AssessDomain> selectUserDriverAssessNum(String driverId) throws Exception;
	
	/**
	 * 查询web端用户对司机的评价信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<UserDriverAssessInfoDomain> selectUserDriverAssessInfoList(Map<String,Object> map) throws Exception;
}
