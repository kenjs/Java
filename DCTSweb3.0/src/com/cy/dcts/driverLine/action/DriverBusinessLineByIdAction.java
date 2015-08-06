package com.cy.dcts.driverLine.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.driverLine.service.IDriverCarLineService;

/**
 * 根据司机ID查询预约线路
 * @author Administrator
 *
 */
public class DriverBusinessLineByIdAction extends BaseJsonAction{

	
	private static final long serialVersionUID = 5842918017581770522L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverCarLineService driverCarLineService;
	
	@Override
	protected void execMethod() throws Exception {
		try{
			String driverId = this.request.getParameter("driverId");
			List<DriverBusinessLineInfoDomain> list = new ArrayList<DriverBusinessLineInfoDomain>();
			list = driverCarLineService.queryDriverBusinessLineInfoDomainByDriverId(driverId);
			String result = this.sendResponseToJson("1","根据司机ID查询预约线路成功!",list);
			logger.warn("query driver car business line success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query driver car business line error!");
			throw new RuntimeException();
		}
	}

	public void setDriverCarLineService(IDriverCarLineService driverCarLineService) {
		this.driverCarLineService = driverCarLineService;
	}
}
