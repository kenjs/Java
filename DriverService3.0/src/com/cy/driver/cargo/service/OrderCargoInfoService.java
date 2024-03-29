package com.cy.driver.cargo.service;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverCargoAssessInfoBo;
import com.cy.common.bo.DriverCargoCollectInfoBo;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;

/**
 * 货源service
 * @date 2014-5-30
 * @author haoyong
 *
 */
public interface OrderCargoInfoService {
	/**
	 * 附近货源
	 * @param positioning
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectNearByCargoList(String driverId,String fromSize,String listSize);
	
	/**
	 * 符合预约的货源
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectCargoSuitOrderList(String code,String fromSize,String listSize);
	
	/**
	 * 查找货源
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectCargoList(Map<String,String> map);
	
	/**
	 * 根据司机线路查找符合条件的货物数量
	 * @param map
	 * @return
	 */
	public int selectCargoNumByDriverLine(String driverId);
	
	/**
	 * 根据司机线路查找符合条件的货物
	 * @param map
	 * @return
	 */
	public List<OrderCargoInfoDomain> selectCargoListByDriverLine(String driverId,String fromSize,String listSize);
	
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
	public int selectByDriverAndCargoId(String driverId,String cargoId);
	
	/**
	 * 修改评价 
	 * @return
	 */
	public int updateAssess(DriverCargoAssessInfoBo bo);
	
	/**
	 * 是否已关注
	 * @param driverId
	 * @param cargoId
	 * @return
	 */
	public boolean cargoIsAttention(String driverId,String cargoId);
	
	/**
	 * 修改货源
	 * @param map
	 */
	public void updateCargoInfo(Map<String,Object> map);
	
	/**
	 * 某条货源被多少次标注为货已走
	 * @param cargoId
	 * @return
	 */
	public int selectDriverCargoAssessNum(String cargoId);
	
}
