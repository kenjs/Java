package com.cy.driver.cargo.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverCargoAssessInfoBo;
import com.cy.common.bo.DriverCargoCollectInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.cargo.dao.OrderCargoInfoDao;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;
/**
 * 货源dao impl
 * @date 2014-5-30
 * @author haoyong
 *
 */
public class OrderCargoInfoDaoImpl extends BaseDao implements OrderCargoInfoDao{

	@SuppressWarnings("unchecked")
	public List<OrderCargoInfoDomain> selectNearByCargoList(Map<String,Object> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisSelectNearByCargoList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<OrderCargoInfoDomain> selectCargoSuitOrderList(
			Map<String, Object> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisSelectCargoSuitOrderList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<OrderCargoInfoDomain> selectCargoList(Map<String, String> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisSelectCargoList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int selectCargoNumByDriverLine(Map<String, Object> map) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectCargoNumByDriverLine", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public OrderCargoInfoDomain selectCargoDetailById(String id) {
		try {
			return (OrderCargoInfoDomain) queryForObject("iBatisSelectCargoById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int attentionCargoInfo(DriverCargoCollectInfoBo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertDriverCargoCollectInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int commentCargoInfo(DriverCargoAssessInfoBo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertDriverCargoAssessInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int selectByDriverAndCargoId(Map<String, Object> map) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectByDriverAndCargoId", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int updateAssess(DriverCargoAssessInfoBo bo) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateAssess", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<OrderCargoInfoDomain> selectCargoListByDriverLine(Map<String, Object> map) {
		List<OrderCargoInfoDomain> list = null;
		try {
			list = (List<OrderCargoInfoDomain>) queryForList("iBatisSelectCargoByDriverLineList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int cargoIsAttention(Map<String,Object> map) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisCheckIsAttention", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void updateCargoInfo(Map<String, Object> map) {
		try {
			saveObject("iBatsiUpdateCargoInfo", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectNearByCargoRemid(Map<String,Object> mapPar) {
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) queryForObject("iBatisSelectNearByCargoRemid", mapPar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBusinesslineCargoRemid(Map<String,Object> mapPar) {
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) queryForObject("iBatisSelecBusinesslineCargoRemid", mapPar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectNeededCargoRemid(Map<String,Object> mapPar) {
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) queryForObject("iBatisSelectNeededCargoRemid", mapPar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public int selectDriverCargoAssessNum(String cargoId) {
		int count = 0;
		try {
			count = (Integer) queryForObject("iBatisSelectNumByCargoId", cargoId);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return count;
	}
	
}
