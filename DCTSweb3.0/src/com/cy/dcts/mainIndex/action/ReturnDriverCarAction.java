package com.cy.dcts.mainIndex.action;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.driverLine.service.IDriverCarLineService;

/**
 * 预约车源
 * @author nxj
 *
 */
public class ReturnDriverCarAction extends BaseJsonAction {

	private static final long serialVersionUID = 7508580801232850951L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverCarLineService driverCarLineService;
	
	
	@Override
	protected void execMethod() throws Exception {
		try {
			String province = this.request.getParameter("province");
			String city = this.request.getParameter("city");
			String curPage = this.request.getParameter("curPage");
			String pageSize = this.request.getParameter("pageSize");
			StringBuffer startPcc = new StringBuffer();
			if(StringUtils.isNotEmpty(province)) {
				startPcc.append(province);
				if(StringUtils.isNotEmpty(city)) {
					startPcc.append("-"+city);
				}
			}
			DriverBusinessLineInfoDomain driverBusinessLineInfoDomain = new DriverBusinessLineInfoDomain();
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(Integer.parseInt(curPage));
			pageInfo.setPageSize(Integer.parseInt(pageSize));
			driverBusinessLineInfoDomain.setPageInfo(pageInfo);
			driverBusinessLineInfoDomain.setStartProvince(province);
			driverBusinessLineInfoDomain.setStartCity(city);
			driverBusinessLineInfoDomain.setDeleteFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//司机表删除状态
			driverBusinessLineInfoDomain.setStart(String.valueOf(Constants.DELETED_FLAG_FALSE));//预约线路表删除状态
			driverBusinessLineInfoDomain.setStartPcc(startPcc.toString());
			List<DriverBusinessLineInfoDomain> list = driverCarLineService.queryReturnDriverCar(driverBusinessLineInfoDomain);
			driverBusinessLineInfoDomain.setList(list);
			String result = this.sendResponseToJson("1","查询预约车源成功!",driverBusinessLineInfoDomain);
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
