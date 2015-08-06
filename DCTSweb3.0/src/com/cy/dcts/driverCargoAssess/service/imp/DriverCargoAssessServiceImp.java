package com.cy.dcts.driverCargoAssess.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dcts.common.domain.DriverCargoAssessInfoDomain;
import com.cy.dcts.driverCargoAssess.dao.IDriverCargoAssessDao;
import com.cy.dcts.driverCargoAssess.service.IDriverCargoAssessService;

public class DriverCargoAssessServiceImp implements IDriverCargoAssessService{
   private IDriverCargoAssessDao driverCargoAssessDao;
	public List<DriverCargoAssessInfoDomain> queryDriverCargoAssessByCargoId(
			DriverCargoAssessInfoDomain driverCargoAssessInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("cargoId", driverCargoAssessInfoDomain.getCargoId());
		if(driverCargoAssessInfoDomain.getPageInfo()!=null){
			int pageSizes=driverCargoAssessInfoDomain.getPageInfo().getPageSize();
			int curPages=driverCargoAssessInfoDomain.getPageInfo().getCurPage();
			queryMap.put("beginNum",pageSizes*(curPages-1) );
			queryMap.put("endNum",pageSizes);
			driverCargoAssessInfoDomain.getPageInfo().setTotalRecords(driverCargoAssessDao.queryDriverCargoAssessByCargoIdCount(queryMap));
			return driverCargoAssessDao.queryDriverCargoAssessByCargoIdPage(queryMap);
		}
		return driverCargoAssessDao.queryDriverCargoAssessByCargoId(queryMap);
	}
	public IDriverCargoAssessDao getDriverCargoAssessDao() {
		return driverCargoAssessDao;
	}
	public void setDriverCargoAssessDao(IDriverCargoAssessDao driverCargoAssessDao) {
		this.driverCargoAssessDao = driverCargoAssessDao;
	}

}
