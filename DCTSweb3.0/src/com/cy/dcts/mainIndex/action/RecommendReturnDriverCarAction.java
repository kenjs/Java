package com.cy.dcts.mainIndex.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.driverLine.service.IDriverCarLineService;

/**
 * 推荐预约车源信息
 * @author nxj
 *
 */
public class RecommendReturnDriverCarAction extends BaseJsonAction {

	private static final long serialVersionUID = 8638092816530216686L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverCarLineService driverCarLineService;

	@Override
	protected void execMethod() throws Exception {
		try {
			String province = this.request.getParameter("province");
			String city = this.request.getParameter("city");
			DriverBusinessLineInfoDomain driverBusinessLineInfoDomain = new DriverBusinessLineInfoDomain();
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(0);
			pageInfo.setPageSize(3);
			driverBusinessLineInfoDomain.setPageInfo(pageInfo);
			driverBusinessLineInfoDomain.setStartProvince(province);
			driverBusinessLineInfoDomain.setStartCity(city);
			driverBusinessLineInfoDomain.setAuditFlag("1");
			List<DriverBusinessLineInfoDomain> list = driverCarLineService.queryReturnDriverCar(driverBusinessLineInfoDomain);
			String result = this.sendResponseToJson("1","查询推荐预约车源成功!",list);
			logger.warn("query backhaul driver car success. json=[{}]",new Object[] { result });
		}catch (Exception e) {
			logger.error("query backhaul driver car error!");
			throw new RuntimeException();
		}
	}
	
	public void setDriverCarLineService(IDriverCarLineService driverCarLineService) {
		this.driverCarLineService = driverCarLineService;
	}

}
