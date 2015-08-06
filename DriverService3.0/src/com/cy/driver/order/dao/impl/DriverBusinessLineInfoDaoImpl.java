package com.cy.driver.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.bo.DriverBusinessLineInfo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.order.dao.DriverBusinessLineInfoDao;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
/**
 * 预约dao impl
 * 2014-5-27
 * @author haoyong
 *
 */
public class DriverBusinessLineInfoDaoImpl extends BaseDao implements DriverBusinessLineInfoDao{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public int insertDriverBusinessLineInfo(DriverBusinessLineInfo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertDriverBusinessLineInfo", bo);
			if(i != 0){
				log.info("新增预约成功！");
			} else {
				log.info("新增预约失败！");
			}
		} catch (SQLException e) {
			log.error("新增预约发生错误：",e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
		return i;
	}

	public int updateDriverBusinessLineInfo(DriverBusinessLineInfo bo) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateDriverBusinessLineInfo", bo);
			if(i == 1){
				log.info("修改预约成功！");
			} else {
				log.info("修改预约失败！");
			}
		} catch (SQLException e) {
			log.error("修改预约发生错误：",e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<DriverBusinessLineInfoDomain> selectDriverBusinessLineInfoList(String driverId) {
		List<DriverBusinessLineInfoDomain> list = null;
		try {
			list =  (List<DriverBusinessLineInfoDomain>) queryForList("iBatisSelectDriverBusinessLineInfo", driverId);
			if(list != null){
				log.info("查询预约成功. LIST=[{}]", new Object[]{list});
			}
		} catch (SQLException e) {
			log.error("查询预约发生错误：",e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	public int deleteDriverBusinessLineInfo(String driverId) {
		int i = 0;
		try {
			i = deleteObject("iBatisDeleteDriverBusinessLineInfo", driverId);
			if(i == 1){
				log.info("删除预约成功！");
			} else {
				log.info("删除预约失败！");
			}
		} catch (SQLException e) {
			log.error("删除预约发生错误：",e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
		return i;
	}

	public int selectDriverBusinessLineInfoCount(String driverId) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectBusinessLineInfoCount", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
