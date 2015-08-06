package com.cy.dcts.webUser.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.domain.DriverUserAssessInfoDomain;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;

public interface IUserDriverAssessInfoDao {
	/**
	 * 给他人的评价(货主对司机的评价) 不分页
	 * @param asses
	 * @param index
	 * @return
	 */
	public List<UserDriverAssessInfoDomain> queryUserDriverAssessInfo(Map<String,Object> map);
	
	/**
	 * 给他人的评价(货主对司机的评价) 分页
	 * @param asses
	 * @param index
	 * @return
	 */
	public List<UserDriverAssessInfoDomain> queryUserDriverAssessInfoByPage(Map<String,Object> map);
	
	/**
	 * 给他人的评价(货主对司机的评价)总记录数
	 * @param asses
	 * @param index
	 * @return
	 */
	public Integer queryUserDriverAssessInfoCount(Map<String,Object> map);
	
	/**
	 * 来自司机的评价(司机对货主的评价) 不分页
	 * @param asses
	 * @param index
	 * @return
	 */
	public List<DriverUserAssessInfoDomain> queryDriverUserAssessInfo(Map<String,Object> map);
	
	/**
	 * 来自司机的评价(司机对货主的评价) 分页
	 * @param asses
	 * @param index
	 * @return
	 */
	public List<DriverUserAssessInfoDomain> queryDriverUserAssessInfoBypage(Map<String,Object> map);
	
	/**
	 * 来自司机的评价(司机对货主的评价)总记录数
	 * @param asses
	 * @param index
	 * @return
	 */
	public Integer queryDriverUserAssessInfoCount(Map<String,Object> map);


	/**
	 * 根据货主Id（userId）查询司机对货主评价数(好评价，中评价，差评价)
	 * @param map
	 * @return
	 */
	public DriverUserAssessInfoDomain queryDriverUserAssessCountByAssessScore(String userId);
}	
