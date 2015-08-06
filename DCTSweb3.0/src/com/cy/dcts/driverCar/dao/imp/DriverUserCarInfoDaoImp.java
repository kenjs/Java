package com.cy.dcts.driverCar.dao.imp;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.UserDriverInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.driverCar.dao.IDriverUserCarInfoDao;

public class DriverUserCarInfoDaoImp extends BaseDao implements IDriverUserCarInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public DriverUserInfo queryDriverInfoByCode(String code) {
		try {
			return (DriverUserInfo)this.queryForObject("query_driver_info_byCode", code);
		} catch (Exception e) {
			logger.error("query_driver_info_byCode error!",e);
			throw new RuntimeException();
		}
	}
	
	public List<DriverUserInfoDomain> queryTodayDynamicDriverCarByTime(
			String deleteFlag) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_today_dynamic_driver_car_by_time", deleteFlag);
		} catch (Exception e) {
			logger.error("query_today_dynamic_driver_car_by_time error!",e);
			throw new RuntimeException();
		}
	}
	
	public DriverUserInfoDomain queryDriverUserInfoDomainById(String id) {
		try {
			return (DriverUserInfoDomain)this.queryForObject("query_driver_user_info_domain_byId", id);
		} catch (Exception e) {
			logger.error("query_driver_user_info_domain_byId error!",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyDriverCarDeleteFlagByID(Map<String, Object> queryMap) {
		try {
			return this.saveObject("update_driver_car_deleteFlag_byId", queryMap)==1;
		} catch (Exception e) {
			logger.error("update_driver_car_deleteFlag_byId error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverUserInfoDomain> queryDriverUserInfoDomain(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_driver_user_info_domain", queryMap);
		} catch (Exception e) {
			logger.error("query_driver_user_info_domain error!",e);
			throw new RuntimeException();
		}
	}

	public Integer queryDriverUserInfoDomainCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_driver_user_info_domain_count", queryMap);
		} catch (Exception e) {
			logger.error("query_driver_user_info_domain_count error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverUserInfoDomain> queryDriverUserInfoDomainByPage(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_driver_user_info_domain_byPage", queryMap);
		} catch (Exception e) {
			logger.error("query_driver_user_info_domain_byPage error!",e);
			throw new RuntimeException();
		}
	}
	
	public List<DriverUserInfoDomain> queryDriverQuoteInfoByCargoId(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_driver_quote_byOid", queryMap);
		} catch (Exception e) {
			logger.warn("query_driver_quote_byOid error",e);
			throw new RuntimeException();
		}
	}

	public List<DriverUserInfoDomain> queryDriverQuoteInfoByCargoIdPage(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_driver_quote_byOid_page", queryMap);
		} catch (Exception e) {
			logger.warn("query_driver_quote_byOid_page error",e);
			throw new RuntimeException();
		}
	}

	public Integer queryDriverQuoteInfoByCargoIdCount(
			Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_driver_quote_byOid_count", queryMap);
		} catch (Exception e) {
			logger.error("query_driver_quote_byOid_count error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverUserInfoDomain> queryRealDriverCarByPage(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_real_driver_car_byPage", queryMap);
		} catch (Exception e) {
			logger.warn("query_real_driver_car_byPage error",e);
			throw new RuntimeException();
		}
	}

	public Integer queryRealDriverCarByPageCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_real_driver_car_byPage_count", queryMap);
		} catch (Exception e) {
			logger.error("query_real_driver_car_byPage_count error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverUserInfoDomain> queryRealDriverCarByPageList(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_real_driver_car_byPage_list", queryMap);
		} catch (Exception e) {
			logger.warn("query_real_driver_car_byPage_list error",e);
			throw new RuntimeException();
		}
	}

	public Integer queryDriverCarCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_driver_car_by_time_count", queryMap);
		} catch (Exception e) {
			logger.error("query_driver_car_by_time_count error!",e);
			throw new RuntimeException();
		}
	}

	public String addUserDriver(Map<String,Object> queryMap) {
		try {
			return this.addObjectKeyString("insert_user_driver_info", queryMap);
		} catch (Exception e) {
			logger.warn("insert_user_driver_info error", e);
			throw new RuntimeException();
		}
	}

	public List<UserDriverInfo> queryUserDriverInfoByDriverID(Map<String,Object> queryMap) {
		try {
			return (List<UserDriverInfo>)this.queryForList("query_user_driver_info_byDriverID", queryMap);
		} catch (Exception e) {
			logger.error("query_user_driver_info_byDriverID error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverUserInfoDomain> queryRealDriverCarByPageListcount(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_real_driver_car_byPage_list_count", queryMap);
		} catch (Exception e) {
			logger.warn("query_real_driver_car_byPage_list_count error",e);
			throw new RuntimeException();
		}
	}
	
	
	public List<DriverUserInfoDomain> queryRealDriverCarByPageListcountById(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_real_driver_car_byPage_list_count_by_id", queryMap);
		} catch (Exception e) {
			logger.warn("query_real_driver_car_byPage_list_count_by_id error",e);
			throw new RuntimeException();
		}
	}
	
	public List<DriverUserInfoDomain> queryRealDriverByPage(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverUserInfoDomain>)this.queryForList("query_real_driver_car_byPage_real", queryMap);
		} catch (Exception e) {
			logger.warn("query_real_driver_car_byPage_real error",e);
			throw new RuntimeException();
		}
	}

	
}
