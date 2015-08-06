package com.cy.swp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.swp.bo.DriverUserInfo;
import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.DriverUserInfoDao;
import com.cy.swp.dao.OrderCargoInfoDao;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.service.DriverUserInfoService;
@Service("driverUserInfoService")
public class DriverUserInfoServiceImpl implements DriverUserInfoService {

	@Resource
	private OrderCargoInfoDao orderCargoInfoDao;
	@Resource
	private DriverUserInfoDao driverUserInfoDao;
	
	public List<DriverUserInfoDomain> selectDriverUserInfo(
			Map<String, Object> queryMap) {
		return driverUserInfoDao.selectDriverUserInfo(queryMap);
	}

	public List<DriverUserInfoDomain> queryCargoMateDriverDomainInfo(
			DriverUserInfoDomain driverUserInfoDomain,MarketingUserInfo marketingUserInfo) {
		List<DriverUserInfoDomain> driverDomain=new ArrayList<DriverUserInfoDomain>();
		 Map<String, Object> queryMap = new HashMap<String, Object>();
		 queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);//0 删除
		 
		if(driverUserInfoDomain!=null){
			//根据货源Id查询货源
			OrderCargoInfo orderCargoInfo=orderCargoInfoDao.queryOrderCargoInfoById(driverUserInfoDomain.getCargoId());
			if(orderCargoInfo!=null){
				queryMap.put("assistId", driverUserInfoDomain.getAssistId());
				queryMap.put("marketUserId", marketingUserInfo.getId());
				
				
				queryMap.put("startProvince", orderCargoInfo.getStartProvince());
	            queryMap.put("startCity", orderCargoInfo.getStartCity());
	            queryMap.put("endProvince", orderCargoInfo.getEndProvince());
	            queryMap.put("endCity", orderCargoInfo.getEndCity());
	            
//	            if (driverUserInfoDomain.getPageInfo() != null) {
//	                int pageSizes = driverUserInfoDomain.getPageInfo().getPageSize();
//	                int curPages = driverUserInfoDomain.getPageInfo().getCurPage();
//	                queryMap.put("beginNum", pageSizes * (curPages - 1));
//	                queryMap.put("endNum", pageSizes);
//	                driverUserInfoDomain.getPageInfo().setTotalRecords(driverUserInfoDao.queryCargoMateDriverDomainCount(queryMap));
//	                return  driverUserInfoDao.queryCargoMateDriverDomainByPage(queryMap);
//	            }
	            driverDomain=driverUserInfoDao.queryCargoMateDriverDomain(queryMap);
			}
		}
		return driverDomain;
	}

	public DriverUserInfo queryDriverUserInfoById(String id) {
		return driverUserInfoDao.queryDriverUserInfoById(id);
	}

	public List<DriverUserInfoDomain> queryDriverMapList(
			Map<String, Object> queryMap) {
		List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
		list = driverUserInfoDao.queryDriverMapPageSizeOne(queryMap);
		if(list.size() != 3) {
			queryMap.put("pageSize", 3-list.size());
			List<DriverUserInfoDomain> towList = driverUserInfoDao.queryDriverMapPageSizeTow(queryMap);
			for(int i = 0;i < towList.size();i++) {
				list.add(towList.get(i));
			}
			if(list.size() != 3) {
				queryMap.put("pageSize", 3-list.size());
				List<DriverUserInfoDomain> threeList = driverUserInfoDao.queryDriverMapPageSizeThree(queryMap);
				for(int i = 0;i < threeList.size();i++) {
					list.add(threeList.get(i));
				}
				if(list.size() != 3) {
					queryMap.put("pageSize", 3-list.size());
					List<DriverUserInfoDomain> fourList = driverUserInfoDao.queryDriverMapPageSizeFour(queryMap);
					for(int i = 0;i < fourList.size();i++) {
						list.add(fourList.get(i));
					}
				}
			}
		}
		return list;
	}

	public List<DriverUserInfoDomain> queryCarByAssisterId(String assistId) {
		return driverUserInfoDao.queryCarByAssisterId(assistId);
	}

	@Override
	public boolean updateDriverUserInfo(DriverUserInfo driverUserInfo) {
		return driverUserInfoDao.updateDriverUserInfoSetDriverInfo(driverUserInfo);
	}

	@Override
	public DriverUserInfo queryDriverUserInfoLocation(Integer driverId) {
		return driverUserInfoDao.queryDriverUserInfoLocation(driverId);
	}


}
