package com.cy.dcts.driverCar.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.UserDriverInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.driverCar.dao.IDriverUserCarInfoDao;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

public class DriverUserCarInfoServiceImp implements IDriverUserCarInfoService{
    private IDriverUserCarInfoDao driverUserCarInfoDao;
    
    public DriverUserInfo queryDriverInfoByCode(String code) {
		return driverUserCarInfoDao.queryDriverInfoByCode(code);
	}
    
    public List<DriverUserInfoDomain> queryTodayDynamicDriverCarByTime() {
		return driverUserCarInfoDao.queryTodayDynamicDriverCarByTime(String.valueOf(Constants.DELETED_FLAG_FALSE));
	}
    
	public DriverUserInfoDomain queryDriverUserInfoDomainById(String id) {
		return driverUserCarInfoDao.queryDriverUserInfoDomainById(id);
	}

	public boolean modifyDriverCarDeleteFlagByID(String id) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("deleteFlag", Constants.DELETED_FLAG_TRUE);//-1 删除
		queryMap.put("id", id);
		return driverUserCarInfoDao.modifyDriverCarDeleteFlagByID(queryMap);
	}
	     
	public List<DriverUserInfoDomain> queryDriverUserInfoDomain(
			DriverUserInfoDomain driverUserInfoDomain,String userId) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		
		queryMap.put("userId",userId);
		queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);//0 删除
		if(driverUserInfoDomain!=null){
		queryMap.put("lastLocation", driverUserInfoDomain.getLastLocation());
		//queryMap.put("driverLine", driverUserInfoDomain.getDriverLine());
		queryMap.put("startProvince",driverUserInfoDomain.getStartProvince() );
		queryMap.put("startCity", driverUserInfoDomain.getStartCity());
		queryMap.put("endProvince",driverUserInfoDomain.getEndProvince());
		queryMap.put("endCity",driverUserInfoDomain.getEndCity() );
		queryMap.put("name", driverUserInfoDomain.getName());
		queryMap.put("code", driverUserInfoDomain.getCode());
		if(driverUserInfoDomain.getPageInfo()!=null){
			int pageSizes=driverUserInfoDomain.getPageInfo().getPageSize();
			int curPages=driverUserInfoDomain.getPageInfo().getCurPage();
			queryMap.put("beginNum",pageSizes*(curPages-1) );
			queryMap.put("endNum",pageSizes );
			driverUserInfoDomain.getPageInfo().setTotalRecords(driverUserCarInfoDao.queryDriverUserInfoDomainCount(queryMap));
			return driverUserCarInfoDao.queryDriverUserInfoDomainByPage(queryMap);
		}
		}
		return driverUserCarInfoDao.queryDriverUserInfoDomain(queryMap);
	}
	
	public List<DriverUserInfoDomain> queryDriverQuoteInfoByCargoId(
			DriverUserInfoDomain driverUserInfoDomain) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		if(driverUserInfoDomain!=null){
			queryMap.put("cargoId", driverUserInfoDomain.getOrderId());
			if(driverUserInfoDomain.getPageInfo()!=null){
				int pageSizes=driverUserInfoDomain.getPageInfo().getPageSize();
				int curPages=driverUserInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes );
				driverUserInfoDomain.getPageInfo().setTotalRecords(driverUserCarInfoDao.queryDriverQuoteInfoByCargoIdCount(queryMap));
				return driverUserCarInfoDao.queryDriverQuoteInfoByCargoIdPage(queryMap);
			}
		}
		
		return driverUserCarInfoDao.queryDriverQuoteInfoByCargoId(queryMap);
	}

	/*******
	 * 当前车源首页
	 */
	public List<DriverUserInfoDomain> queryRealDriverCarByPage(DriverUserInfoDomain driverUserInfoDomain) {
		List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
		if(driverUserInfoDomain != null) { 
			StringBuffer driverLineStart = new StringBuffer();//运营线路开始
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartProvince())) {
				queryMap.put("startProvince", driverUserInfoDomain.getStartProvince());
				driverLineStart.append(driverUserInfoDomain.getStartProvince());
				if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartCity())) {
					queryMap.put("startCity", driverUserInfoDomain.getStartCity());
					driverLineStart.append(driverUserInfoDomain.getStartCity());
				}
				queryMap.put("driverLinestart", driverLineStart.toString());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getAuditFlag())) {
				queryMap.put("auditFlag", driverUserInfoDomain.getAuditFlag());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getProvince())) {
				queryMap.put("province", driverUserInfoDomain.getProvince());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCity())) {
				queryMap.put("city", driverUserInfoDomain.getCity());
			}
			if(driverUserInfoDomain.getPageInfo() != null) {
				queryMap.put("curPage", driverUserInfoDomain.getPageInfo().getCurPage());
				queryMap.put("pageSize", driverUserInfoDomain.getPageInfo().getPageSize());
				List<DriverUserInfoDomain> list1 = driverUserCarInfoDao.queryRealDriverCarByPage(queryMap);
				if(list1.size() == driverUserInfoDomain.getPageInfo().getPageSize()) {
					list = list1;
				}else {
					if(list1.size() > 0) {
						for(int i = list1.size();i > 0;i--) {
							map.put(list1.get(i-1).getId(), list1.get(i-1));
						}
					}
					if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
						Map<String, Object> queryMapList = new HashMap<String, Object>();
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartProvince())) {
							queryMapList.put("startProvince", driverUserInfoDomain.getStartProvince());
							queryMapList.put("driverLinestart", driverUserInfoDomain.getStartProvince());
						}
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getAuditFlag())) {
							queryMapList.put("auditFlag", driverUserInfoDomain.getAuditFlag());
						}
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getProvince())) {
							queryMapList.put("province", driverUserInfoDomain.getProvince());
						}
						queryMapList.put("deleteFlag",Constants.DELETED_FLAG_FALSE);
						queryMapList.put("curPage", driverUserInfoDomain.getPageInfo().getCurPage());
						queryMapList.put("pageSize", (driverUserInfoDomain.getPageInfo().getPageSize()*2));
						List<DriverUserInfoDomain> list2 = driverUserCarInfoDao.queryRealDriverCarByPage(queryMapList);
						if(list2.size() > 0) {
							for(int i = list2.size(); i > 0;i--) {
								if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
									map.put(list2.get(i-1).getId(), list2.get(i-1));
								}else {
									break;
								}
							}
						}
					}
					if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
						Map<String, Object> queryMapList = new HashMap<String, Object>();
						queryMapList.put("deleteFlag",Constants.DELETED_FLAG_FALSE);
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getAuditFlag())) {
							queryMapList.put("auditFlag", driverUserInfoDomain.getAuditFlag());
						}
						queryMapList.put("curPage", driverUserInfoDomain.getPageInfo().getCurPage());
						queryMapList.put("pageSize", (driverUserInfoDomain.getPageInfo().getPageSize()*3));
						List<DriverUserInfoDomain> list2 = driverUserCarInfoDao.queryRealDriverCarByPage(queryMapList);
						if(list2.size() > 0) {
							for(int i = list2.size(); i > 0;i--) {
								if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
									map.put(list2.get(i-1).getId(), list2.get(i-1));
								}else {
									break;
								}
							}
						}
					}
					if(map.size()>0) {
						Iterator  itent = map.values().iterator();
						while(itent.hasNext()) {
							list.add((DriverUserInfoDomain)itent.next());
						}
					}
				}
			}else {
				list = driverUserCarInfoDao.queryRealDriverCarByPageList(queryMap);
			}
		}else {
			list = driverUserCarInfoDao.queryRealDriverCarByPageList(queryMap);
		}
		return list;
	}
	
	
	/***
	 * 当前车源更多
	 */
	public List<DriverUserInfoDomain> queryRealMoreDriverCarByPage(
			DriverUserInfoDomain driverUserInfoDomain) {
		List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
		StringBuffer driverLineStart = new StringBuffer();//运营线路开始
		StringBuffer driverLineEnd = new StringBuffer();//运营线路结束
		StringBuffer lastLocation = new StringBuffer();//当前位置
		if(driverUserInfoDomain != null) {
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getId())) {
				queryMap.put("id", driverUserInfoDomain.getId());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCode())) {
				queryMap.put("code", driverUserInfoDomain.getCode());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarNumber())) {
				queryMap.put("carNumber", driverUserInfoDomain.getCarNumber());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarPlateType())) {
				queryMap.put("carPlateType", driverUserInfoDomain.getCarPlateType());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarBarType())) {
				queryMap.put("carBarType", driverUserInfoDomain.getCarBarType());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarLength())) {
				queryMap.put("carLength", driverUserInfoDomain.getCarLength());
			}
			/**当前位置**/
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getProvince())) {
				lastLocation.append(driverUserInfoDomain.getProvince());
				if(StringUtils.isNotEmpty(driverUserInfoDomain.getCity())) {
					lastLocation.append(driverUserInfoDomain.getCity());
				}
				queryMap.put("lastLocation", lastLocation.toString());
			}
			/**运营线路并且双向线路**/
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartProvince())) {
				queryMap.put("startProvince", driverUserInfoDomain.getStartProvince());
				driverLineStart.append(driverUserInfoDomain.getStartProvince());
				if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartCity())) {
					queryMap.put("startCity", driverUserInfoDomain.getStartCity());
					driverLineStart.append(driverUserInfoDomain.getStartCity());
				}
				queryMap.put("driverLinestart", driverLineStart.toString());
				
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getEndProvince())) {
				queryMap.put("endProvince", driverUserInfoDomain.getEndProvince());
				driverLineEnd.append(driverUserInfoDomain.getEndProvince());
				if(StringUtils.isNotEmpty(driverUserInfoDomain.getEndCity())) {
					queryMap.put("endCity", driverUserInfoDomain.getEndCity());
					driverLineEnd.append(driverUserInfoDomain.getEndCity());
				}
				queryMap.put("driverLineEnd", driverLineEnd.toString());
			}
			if(driverUserInfoDomain.getPageInfo() != null) {
				queryMap.put("curPage",  driverUserInfoDomain.getPageInfo().getCurPage()*driverUserInfoDomain.getPageInfo().getPageSize());
				queryMap.put("pageSize", driverUserInfoDomain.getPageInfo().getPageSize());
				driverUserInfoDomain.getPageInfo().setTotalRecords(driverUserCarInfoDao.queryRealDriverCarByPageCount(queryMap));
				list = driverUserCarInfoDao.queryRealDriverCarByPage(queryMap);
			}else {
				list = driverUserCarInfoDao.queryRealDriverCarByPageList(queryMap);
			}
			
		}else {
			list = driverUserCarInfoDao.queryRealDriverCarByPageList(queryMap);
		}
		return list;
	} 
	
	public Integer queryDriverCarCount(String createTime) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(createTime)) {
			queryMap.put("createTime", createTime);
		}
		Integer count = driverUserCarInfoDao.queryDriverCarCount(queryMap);
		return count;
	}
	public String addUserDriver(String driverId,String userId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(driverId)) {
			queryMap.put("driverId", driverId);
		}
		if(StringUtils.isNotEmpty(userId)) {
			queryMap.put("userId", userId);
		}
		queryMap.put("start", Constants.DELETED_FLAG_FALSE);
		return driverUserCarInfoDao.addUserDriver(queryMap);
	}
	
	
	public List<DriverUserInfoDomain> queryDriverTheCarInfo(DriverUserInfoDomain driverUserInfoDomain,String linetypeset) {
		List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
		StringBuffer lastLocation = new StringBuffer();//当前位置
		if(driverUserInfoDomain != null) {
			/**当前位置**/
//			if("1".equals(linetypeset)) {
//				queryMap.put("lastLocationPage", driverUserInfoDomain.getStartProvince()+driverUserInfoDomain.getStartCity());
//			}else {
				if(StringUtils.isNotEmpty(driverUserInfoDomain.getLastLocation())) {
						String[] lastLocationSplit =  driverUserInfoDomain.getLastLocation().split("-");
						if(lastLocationSplit.length == 1) {
							lastLocation.append(lastLocationSplit[0]);
						}
						if(lastLocationSplit.length == 2) {
							lastLocation.append(lastLocationSplit[0]);
							lastLocation.append(lastLocationSplit[1]);
						}
						queryMap.put("lastLocation", lastLocation.toString());
				}
//			}
			queryMap.put("startProvince", driverUserInfoDomain.getStartProvince());
			queryMap.put("startCity", driverUserInfoDomain.getStartCity());
			queryMap.put("endProvince", driverUserInfoDomain.getEndProvince());
			queryMap.put("endCity", driverUserInfoDomain.getEndCity());
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCode())) {
				queryMap.put("code", driverUserInfoDomain.getCode());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarNumber())) {
				queryMap.put("carNumber", driverUserInfoDomain.getCarNumber());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarPlateType())) {
				queryMap.put("carPlateType", driverUserInfoDomain.getCarPlateType());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarBarType())) {
				queryMap.put("carBarType", driverUserInfoDomain.getCarBarType());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarLength())) {
				queryMap.put("carLength", driverUserInfoDomain.getCarLength());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarWeight())) {
				queryMap.put("carWeight", driverUserInfoDomain.getCarWeight());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCarCubage())) {
				queryMap.put("carCubage", driverUserInfoDomain.getCarCubage());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getId())) {
				queryMap.put("id", driverUserInfoDomain.getId());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getId())) {
				list = driverUserCarInfoDao.queryRealDriverCarByPageListcountById(queryMap);
			}else {
				list = driverUserCarInfoDao.queryRealDriverCarByPageListcount(queryMap);
			}
		}
		return list;
	}
	
	//推荐车源
	public List<DriverUserInfoDomain> queryRealDriver(
			DriverUserInfoDomain driverUserInfoDomain) {
		List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
		if(driverUserInfoDomain != null) { 
			StringBuffer driverLineStart = new StringBuffer();//运营线路开始
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartProvince())) {
				queryMap.put("startProvince", driverUserInfoDomain.getStartProvince());
				driverLineStart.append(driverUserInfoDomain.getStartProvince());
				if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartCity())) {
					queryMap.put("startCity", driverUserInfoDomain.getStartCity());
					driverLineStart.append(driverUserInfoDomain.getStartCity());
				}
				queryMap.put("driverLinestart", driverLineStart.toString());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getAuditFlag())) {
				queryMap.put("auditFlag", driverUserInfoDomain.getAuditFlag());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getProvince())) {
				queryMap.put("province", driverUserInfoDomain.getProvince());
			}
			if(StringUtils.isNotEmpty(driverUserInfoDomain.getCity())) {
				queryMap.put("city", driverUserInfoDomain.getCity());
			}
			if(driverUserInfoDomain.getPageInfo() != null) {
				queryMap.put("curPage", driverUserInfoDomain.getPageInfo().getCurPage());
				queryMap.put("pageSize", driverUserInfoDomain.getPageInfo().getPageSize());
				List<DriverUserInfoDomain> list1 = driverUserCarInfoDao.queryRealDriverByPage(queryMap);
				if(list1.size() == driverUserInfoDomain.getPageInfo().getPageSize()) {
					list = list1;
				}else {
					if(list1.size() > 0) {
						for(int i = 0;i < list1.size();i++) {
							map.put(list1.get(i).getId(), list1.get(i));
						}
					}
					if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
						Map<String, Object> queryMapList = new HashMap<String, Object>();
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getStartProvince())) {
							queryMapList.put("startProvince", driverUserInfoDomain.getStartProvince());
							queryMapList.put("driverLinestart", driverUserInfoDomain.getStartProvince());
						}
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getAuditFlag())) {
							queryMapList.put("auditFlag", driverUserInfoDomain.getAuditFlag());
						}
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getProvince())) {
							queryMapList.put("province", driverUserInfoDomain.getProvince());
						}
						queryMapList.put("deleteFlag",Constants.DELETED_FLAG_FALSE);
						queryMapList.put("curPage", driverUserInfoDomain.getPageInfo().getCurPage());
						queryMapList.put("pageSize", (driverUserInfoDomain.getPageInfo().getPageSize()*2));
						List<DriverUserInfoDomain> list2 = driverUserCarInfoDao.queryRealDriverByPage(queryMapList);
						if(list2.size() > 0) {
							for(int i = 0; i < list2.size();i++) {
								if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
									map.put(list2.get(i).getId(), list2.get(i));
								}else {
									break;
								}
							}
						}
					}
					if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
						Map<String, Object> queryMapList = new HashMap<String, Object>();
						queryMapList.put("deleteFlag",Constants.DELETED_FLAG_FALSE);
						if(StringUtils.isNotEmpty(driverUserInfoDomain.getAuditFlag())) {
							queryMapList.put("auditFlag", driverUserInfoDomain.getAuditFlag());
						}
						queryMapList.put("curPage", driverUserInfoDomain.getPageInfo().getCurPage());
						queryMapList.put("pageSize", (driverUserInfoDomain.getPageInfo().getPageSize()*3));
						List<DriverUserInfoDomain> list2 = driverUserCarInfoDao.queryRealDriverByPage(queryMapList);
						if(list2.size() > 0) {
							for(int i = 0; i < list2.size();i++) {
								if(map.size()<driverUserInfoDomain.getPageInfo().getPageSize()) {
									map.put(list2.get(i).getId(), list2.get(i));
								}else {
									break;
								}
							}
						}
					}
					if(map.size()>0) {
						Iterator  itent = map.values().iterator();
						while(itent.hasNext()) {
							list.add((DriverUserInfoDomain)itent.next());
						}
					}
				}
			}else {
				list = driverUserCarInfoDao.queryRealDriverCarByPageList(queryMap);
			}
		}else {
			list = driverUserCarInfoDao.queryRealDriverCarByPageList(queryMap);
		}
		return list;
	}
	
	public List<UserDriverInfo> queryUserDriverInfoByDriverID(String driverId,String userId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("driverId", driverId);
		queryMap.put("userId", userId);
		return driverUserCarInfoDao.queryUserDriverInfoByDriverID(queryMap);
	}
	
	public IDriverUserCarInfoDao getDriverUserCarInfoDao() {
		return driverUserCarInfoDao;
	}

	public void setDriverUserCarInfoDao(IDriverUserCarInfoDao driverUserCarInfoDao) {
		this.driverUserCarInfoDao = driverUserCarInfoDao;
	}

	

	


}
