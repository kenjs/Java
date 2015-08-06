package com.cy.dcts.location.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.LocationCollectLastInfo;
import com.cy.dcts.common.domain.LocationCollectInfoDomain;
import com.cy.dcts.common.domain.LocationCollectLastInfoDomain;
import com.cy.dcts.location.dao.ILocationInfoDao;
import com.cy.dcts.location.service.ILocationInfoService;

public class LocationInfoServiceImp implements ILocationInfoService{
    private ILocationInfoDao locationInfoDao;
	
	public List<LocationCollectInfoDomain> queryLocationCollectInfoByDriverId(
			LocationCollectInfoDomain locationCollectInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(locationCollectInfoDomain!=null){
			queryMap.put("driverId", locationCollectInfoDomain.getDriverId());
			queryMap.put("tradeCrateTime", locationCollectInfoDomain.getTradeCrateTime());
			queryMap.put("searchLocation", locationCollectInfoDomain.getSearchLocation());
			queryMap.put("afterLoadTradeModifyTime", locationCollectInfoDomain.getAfterLoadTradeModifyTime());
			queryMap.put("arrivedTradeModifyTime", locationCollectInfoDomain.getArrivedTradeModifyTime());
			queryMap.put("startTime", locationCollectInfoDomain.getStartTime());
			queryMap.put("endTime", locationCollectInfoDomain.getEndTime());
		  if(locationCollectInfoDomain.getPageInfo()!=null){
			 // int pageSizes=locationCollectInfoDomain.getPageInfo().getPageSize();
			  locationCollectInfoDomain.getPageInfo().setPageSize(8);//在PageInfo类中计算总页数
			  int pageSizes=8;//每页显示8条数据
			  int curPages=locationCollectInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes );
				locationCollectInfoDomain.getPageInfo().setTotalRecords(locationInfoDao.queryLocationCollectInfoByDriverIdCount(queryMap));
				return locationInfoDao.queryLocationCollectInfoByDriverIdByPage(queryMap);
		  }
		}
		return locationInfoDao.queryLocationCollectInfoByDriverId(queryMap);
	}

	public LocationCollectLastInfo queryLocationCollectLastByDriverId(
			String driverId) {
		return locationInfoDao.queryLocationCollectLastByDriverId(driverId);
	}


	public LocationCollectLastInfoDomain queryLocationCollectLastDomainByDriverId(
			String driverId) {
		return locationInfoDao.queryLocationCollectLastDomainByDriverId(driverId);
	}
	
	public ILocationInfoDao getLocationInfoDao() {
		return locationInfoDao;
	}

	public void setLocationInfoDao(ILocationInfoDao locationInfoDao) {
		this.locationInfoDao = locationInfoDao;
	}

    
}
