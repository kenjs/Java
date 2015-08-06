package com.cy.dcts.mainIndex.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

/**
 * 首页当前车源
 * @author nxj
 *
 */
public class RealDriverCarAction extends BaseJsonAction{

	private static final long serialVersionUID = -7824684033996381430L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverUserCarInfoService driverUserCarInfoService;
	
	@Override
	protected void execMethod() throws Exception {
		try{
			String province = this.request.getParameter("province");
			String city = this.request.getParameter("city");
			String curPage = this.request.getParameter("curPage");
			String pageSize = this.request.getParameter("pageSize");
			String auditFlag = this.request.getParameter("auditFlag");
			StringBuffer lastLocation = new StringBuffer();
			if(StringUtils.isNotEmpty(province)) {
				lastLocation.append(province);
				if(StringUtils.isNotEmpty(city)) {
					lastLocation.append("-"+city);
				}
			}
			List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
			DriverUserInfoDomain driverUserInfoDomain = new DriverUserInfoDomain();
			PageInfo pagerInfo = new PageInfo();
			pagerInfo.setCurPage(Integer.parseInt(curPage));
			pagerInfo.setPageSize(Integer.parseInt(pageSize));
			driverUserInfoDomain.setPageInfo(pagerInfo);
			driverUserInfoDomain.setStartProvince(province);
			driverUserInfoDomain.setStartCity(city);
			driverUserInfoDomain.setProvince(province);
			driverUserInfoDomain.setCity(city);
			driverUserInfoDomain.setAuditFlag(auditFlag);
			driverUserInfoDomain.setStartPcc(lastLocation.toString());
			driverUserInfoDomain.setLastLocation(lastLocation.toString());
			list = driverUserCarInfoService.queryRealDriverCarByPage(driverUserInfoDomain);
			driverUserInfoDomain.setList(list);
			String result = this.sendResponseToJson("1","查询当前车源成功!",driverUserInfoDomain);
			logger.warn("query real time driver car success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query real time driver car error!");
			throw new RuntimeException();
		}
		
	}

	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	
	
}
