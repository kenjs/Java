package com.cy.driver.dao;

import com.cy.driver.bo.DriverCargoAssessInfoBo;
import com.cy.driver.bo.DriverCargoCollectInfoBo;
import com.cy.driver.domain.OrderCargoInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 货源dao
 * @date 2014-5-30
 * @author haoyong
 *
 */
@Repository("orderCargoInfoDao")
public interface OrderCargoInfoDao {

	/**
	 * 附近货源
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectNearByCargoList(Map<String,Object> map);
	
	/**
	 * 符合预约的货源
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectCargoSuitOrderList(Map<String,Object> map);
	
	/**
	 * 查找货源
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectCargoList(Map<String,Object> map);
	
	/**
	 * 根据司机线路查找符合条件的货物数量
	 * @param map
	 * @return
	 */
	public int selectCargoNumByDriverLine(Map<String,Object> map);
	
	/**
	 * 货源详情
	 * @param id
	 * @return
	 */
	public OrderCargoInfoDomain selectCargoDetailById(String id);
	
	/**
	 * 关注货源
	 * @return
	 */
	public int attentionCargoInfo(DriverCargoCollectInfoBo bo);
	
	/**
	 * 点评货源
	 * @return
	 */
	public int commentCargoInfo(DriverCargoAssessInfoBo bo);
	
	/**
	 * 校验是否已经评价
	 * @param map
	 * @return
	 */
	public int selectByDriverAndCargoId(Map<String,Object> map);
	
	/**
	 * 修改评价 
	 * @return
	 */
	public int updateAssess(DriverCargoAssessInfoBo bo);
	
	/**
	 * 根据司机线路查找符合条件的货物
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectCargoListByDriverLine(Map<String, Object> map);
	
	/**
	 * 是否已关注
	 * @param map
	 * @return
	 */
	public int cargoIsAttention(Map<String,Object> map);
	
	/**
	 * 修改货源
	 * @param map
	 */
	public void updateCargoInfo(Map<String,Object> map);
	
	/**
	 * 附近货源提醒
	 * @param map
	 * @return
	 */
	public Map<String,Object> selectNearByCargoRemid(Map<String,Object> map);
	
	/**
	 * 预约货源提醒
	 * @param map
	 * @return
	 */
	public Map<String,Object> selectBusinesslineCargoRemid(Map<String,Object> map);
	
	/**
	 * 需要货源提醒
	 * @param map
	 * @return
	 */
	public Map<String,Object> selectNeededCargoRemid(Map<String,Object> map);
	
	/**
	 * 某条货源被多少次标注为货已走
	 * @param cargoId
	 * @return
	 */
	public int selectDriverCargoAssessNum(String cargoId);

	/**
	 * 根据司机当前位置和运营线路找货
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<OrderCargoInfoDomain> queryCargoByLines(Map<String,Object> map) throws SQLException;

	/**
	 * 查询未被查到的附近货源
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<OrderCargoInfoDomain> queryCargoLocationAndNotExistIn(Map<String, Object> map) throws SQLException;

}
