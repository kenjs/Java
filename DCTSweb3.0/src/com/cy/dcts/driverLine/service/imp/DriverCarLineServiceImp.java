package com.cy.dcts.driverLine.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.bo.DriverLineInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.driverLine.dao.IDriverCarLineDao;
import com.cy.dcts.driverLine.service.IDriverCarLineService;

public class DriverCarLineServiceImp implements IDriverCarLineService {
	
	private IDriverCarLineDao driverCarLineDao;

	public List<DriverBusinessLineInfoDomain> queryReturnDriverCar(
			DriverBusinessLineInfoDomain driverBusinessLineInfoDomain) {
		List<DriverBusinessLineInfoDomain> list = new ArrayList<DriverBusinessLineInfoDomain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(driverBusinessLineInfoDomain != null) {
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStart())) {
				queryMap.put("start",driverBusinessLineInfoDomain.getStart());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getDeleteFlag())) {
				queryMap.put("deleteFlag",driverBusinessLineInfoDomain.getDeleteFlag());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getAuditFlag())) {
				queryMap.put("auditFlag",driverBusinessLineInfoDomain.getAuditFlag());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getFreezeFlag())) {
				queryMap.put("freezeFlag",driverBusinessLineInfoDomain.getFreezeFlag());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartProvince())) {
				queryMap.put("startProvince",driverBusinessLineInfoDomain.getStartProvince());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartCity())) {
				queryMap.put("startCity",driverBusinessLineInfoDomain.getStartCity());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartCounty())) {
				queryMap.put("startCounty",driverBusinessLineInfoDomain.getStartCounty());			
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getEndProvince())) {
				queryMap.put("endProvince",driverBusinessLineInfoDomain.getEndProvince());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getEndCity())) {
				queryMap.put("endCity",driverBusinessLineInfoDomain.getEndCity());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getEndCounty())) {
				queryMap.put("endCounty",driverBusinessLineInfoDomain.getEndCounty());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartTime())) {
				queryMap.put("startTime",driverBusinessLineInfoDomain.getStartTime());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarLength())) {
				queryMap.put("carLength",driverBusinessLineInfoDomain.getCarLength());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarPlateType())) {
				queryMap.put("carPlateType",driverBusinessLineInfoDomain.getCarPlateType());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarBarType())) {
				queryMap.put("carBarType",driverBusinessLineInfoDomain.getCarBarType() );
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarWeight())) {
				queryMap.put("carWeight",driverBusinessLineInfoDomain.getCarWeight());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarCubage())) {
				queryMap.put("carCubage",driverBusinessLineInfoDomain.getCarCubage());
			}
			
			if(driverBusinessLineInfoDomain.getPageInfo() != null) {
				queryMap.put("curPage",driverBusinessLineInfoDomain.getPageInfo().getCurPage());
				queryMap.put("pageSize",driverBusinessLineInfoDomain.getPageInfo().getPageSize());
				List<DriverBusinessLineInfoDomain> list1 = driverCarLineDao.queryDriverBusinessLineInfoDomainByPage(queryMap);
				if(list1.size() == driverBusinessLineInfoDomain.getPageInfo().getPageSize()) {
					list = list1;
				}else {
					if(list1.size()>0) {
						for(int i = 0;i < list1.size();i++) {
							map.put(list1.get(i).getId(), list1.get(i));
						}
					}
					if(map.size()<driverBusinessLineInfoDomain.getPageInfo().getPageSize()) {
						Map<String, Object> queryMapList = new HashMap<String, Object>();
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStart())) {
							queryMapList.put("start",driverBusinessLineInfoDomain.getStart());
						}
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getDeleteFlag())) {
							queryMapList.put("deleteFlag",driverBusinessLineInfoDomain.getDeleteFlag());
						}
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getFreezeFlag())) {
							queryMapList.put("freezeFlag",driverBusinessLineInfoDomain.getFreezeFlag());
						}
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getAuditFlag())) {
							queryMapList.put("auditFlag",driverBusinessLineInfoDomain.getAuditFlag());
						}
						queryMapList.put("startProvince",driverBusinessLineInfoDomain.getStartProvince());
						queryMapList.put("curPage",driverBusinessLineInfoDomain.getPageInfo().getCurPage() );
						queryMapList.put("pageSize",(driverBusinessLineInfoDomain.getPageInfo().getPageSize()*2));
						List<DriverBusinessLineInfoDomain> list2 = driverCarLineDao.queryDriverBusinessLineInfoDomainByPage(queryMapList);
						if(list2.size()>0) {
							for(int i = 0;i < list2.size();i++) {
								if(map.size()<driverBusinessLineInfoDomain.getPageInfo().getPageSize()) {
									map.put(list2.get(i).getId(), list2.get(i));
								}else {
									break;
								}
							}
						}
					}
					if(map.size()<driverBusinessLineInfoDomain.getPageInfo().getPageSize()) {
						Map<String, Object> queryMapList = new HashMap<String, Object>();
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStart())) {
							queryMapList.put("start",driverBusinessLineInfoDomain.getStart());
						}
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getDeleteFlag())) {
							queryMapList.put("deleteFlag",driverBusinessLineInfoDomain.getDeleteFlag());
						}
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getFreezeFlag())) {
							queryMapList.put("freezeFlag",driverBusinessLineInfoDomain.getFreezeFlag());
						}
						if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getAuditFlag())) {
							queryMapList.put("auditFlag",driverBusinessLineInfoDomain.getAuditFlag());
						}
						queryMapList.put("curPage",driverBusinessLineInfoDomain.getPageInfo().getCurPage() );
						queryMapList.put("pageSize",(driverBusinessLineInfoDomain.getPageInfo().getPageSize()*3));
						List<DriverBusinessLineInfoDomain> list3 = driverCarLineDao.queryDriverBusinessLineInfoDomainByPage(queryMapList);
						if(list3.size()>0) {
							for(int i = 0;i < list3.size();i++) {
								if(map.size()<driverBusinessLineInfoDomain.getPageInfo().getPageSize()) {
									map.put(list3.get(i).getId(), list3.get(i));
								}else {
									break;
								}
							}
						}
					}
					if(map.size()>0) {
						Iterator  itent = map.values().iterator();
						while(itent.hasNext()) {
							list.add((DriverBusinessLineInfoDomain)itent.next());
						}
					}
				}
			}else {
				list = driverCarLineDao.queryDriverBusinessLineInfoDomainList(queryMap);
			}
		}else {
			list = driverCarLineDao.queryDriverBusinessLineInfoDomainList(queryMap);
		}
		return list;
	}

	
	public List<DriverBusinessLineInfoDomain> queryDriverLineByPage(
			DriverBusinessLineInfoDomain driverBusinessLineInfoDomain) {
		List<DriverBusinessLineInfoDomain> list = new ArrayList<DriverBusinessLineInfoDomain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(driverBusinessLineInfoDomain != null) {
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStart())) {
				queryMap.put("start",driverBusinessLineInfoDomain.getStart());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCode())) {
				queryMap.put("code",driverBusinessLineInfoDomain.getCode());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarNumber())) {
				queryMap.put("carNumber",driverBusinessLineInfoDomain.getCarNumber());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getDeleteFlag())) {
				queryMap.put("deleteFlag",driverBusinessLineInfoDomain.getDeleteFlag());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getFreezeFlag())) {
				queryMap.put("freezeFlag",driverBusinessLineInfoDomain.getFreezeFlag());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getAuditFlag())) {
				queryMap.put("auditFlag",driverBusinessLineInfoDomain.getAuditFlag());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartProvince())) {
				queryMap.put("startProvince",driverBusinessLineInfoDomain.getStartProvince());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartCity())) {
				queryMap.put("startCity",driverBusinessLineInfoDomain.getStartCity());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartCounty())) {
				queryMap.put("startCounty",driverBusinessLineInfoDomain.getStartCounty());			
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getEndProvince())) {
				queryMap.put("endProvince",driverBusinessLineInfoDomain.getEndProvince());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getEndCity())) {
				queryMap.put("endCity",driverBusinessLineInfoDomain.getEndCity());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getEndCounty())) {
				queryMap.put("endCounty",driverBusinessLineInfoDomain.getEndCounty());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getStartTime())) {
				queryMap.put("startTime",driverBusinessLineInfoDomain.getStartTime());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarLength())) {
				queryMap.put("carLength",driverBusinessLineInfoDomain.getCarLength());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarPlateType())) {
				queryMap.put("carPlateType",driverBusinessLineInfoDomain.getCarPlateType());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarBarType())) {
				queryMap.put("carBarType",driverBusinessLineInfoDomain.getCarBarType() );
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarWeight())) {
				queryMap.put("carWeight",driverBusinessLineInfoDomain.getCarWeight());
			}
			if(StringUtils.isNotEmpty(driverBusinessLineInfoDomain.getCarCubage())) {
				queryMap.put("carCubage",driverBusinessLineInfoDomain.getCarCubage());
			}
			if(driverBusinessLineInfoDomain.getPageInfo() != null) {
				queryMap.put("curPage",driverBusinessLineInfoDomain.getPageInfo().getCurPage()*driverBusinessLineInfoDomain.getPageInfo().getPageSize());
				queryMap.put("pageSize",driverBusinessLineInfoDomain.getPageInfo().getPageSize());
				driverBusinessLineInfoDomain.getPageInfo().setTotalRecords(driverCarLineDao.queryDriverBusinessLineInfoDomainByPageCount(queryMap));
				list = driverCarLineDao.queryDriverBusinessLineInfoDomainByPage(queryMap);
			}else {
				list = driverCarLineDao.queryDriverBusinessLineInfoDomainList(queryMap);
			}
		}else {
			list = driverCarLineDao.queryDriverBusinessLineInfoDomainList(queryMap);
		}
		return list;
	}
	
	
	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainByDriverId(
			String driverId) {
		return driverCarLineDao.queryDriverBusinessLineInfoDomainByDriverId(driverId);
	}
	
	public List<DriverLineInfo> queryDriverLineInfoByDriverId(String driverId) {
		return driverCarLineDao.queryDriverLineInfoByDriverId(driverId);
	}
	
	public IDriverCarLineDao getDriverCarLineDao() {
		return driverCarLineDao;
	}

	public void setDriverCarLineDao(IDriverCarLineDao driverCarLineDao) {
		this.driverCarLineDao = driverCarLineDao;
	}


	




	
}
