package com.cy.dcts.driverLine.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.DriverLineInfo;
import com.cy.dcts.driverLine.service.IDriverCarLineService;

/**
 * 根据司机ID查询运营线路
 * @author nxj
 *
 */
public class QueryDriverLineByIdAction extends BaseJsonAction {

	private static final long serialVersionUID = 3286932381361168445L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverCarLineService driverCarLineService;

	@Override
	protected void execMethod() throws Exception {
		try{
			String driverId = this.request.getParameter("driverId");
			List<DriverLineInfo> list = new ArrayList<DriverLineInfo>();
			list = driverCarLineService.queryDriverLineInfoByDriverId(driverId);
			String result = this.sendResponseToJson("1","根据司机ID查询运营线路成功!",list);
			logger.warn("query driver car line success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query driver car line error!");
			throw new RuntimeException();
		}
		
	}
	
	public void setDriverCarLineService(IDriverCarLineService driverCarLineService) {
		this.driverCarLineService = driverCarLineService;
	}

}
