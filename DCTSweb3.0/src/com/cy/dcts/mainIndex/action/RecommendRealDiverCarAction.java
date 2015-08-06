package com.cy.dcts.mainIndex.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

/**
 * 推荐当前车源信息
 * @author nxj
 *
 */
public class RecommendRealDiverCarAction extends BaseJsonAction {

	private static final long serialVersionUID = -8961007514683702153L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverUserCarInfoService driverUserCarInfoService;

	@Override
	protected void execMethod() throws Exception {
		try{
			String province = this.request.getParameter("province");
			String city = this.request.getParameter("city");
			String pageSize = this.request.getParameter("pageSize");
			List<DriverUserInfoDomain> list = new ArrayList<DriverUserInfoDomain>();
			DriverUserInfoDomain driverUserInfoDomain = new DriverUserInfoDomain();
			PageInfo pagerInfo = new PageInfo();
			pagerInfo.setCurPage(0);
			pagerInfo.setPageSize(Integer.parseInt(pageSize));
			driverUserInfoDomain.setPageInfo(pagerInfo);
			driverUserInfoDomain.setStartProvince(province);
			driverUserInfoDomain.setStartCity(city);
			//driverUserInfoDomain.setAuditFlag("1");
			list = driverUserCarInfoService.queryRealDriver(driverUserInfoDomain);
			String result = this.sendResponseToJson("1","查询当前车源推荐车源成功!",list);
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
