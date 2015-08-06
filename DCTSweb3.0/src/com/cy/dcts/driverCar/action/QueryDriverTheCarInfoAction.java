package com.cy.dcts.driverCar.action;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 根据货源订车查询司机信息列表
 * @author nxj
 *
 */
public class QueryDriverTheCarInfoAction extends BaseJsonAction {

	private static final long serialVersionUID = 4660141135433872288L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserCarInfoService driverUserCarInfoService;
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	
	protected void execMethod() throws Exception {
		try{
			String orderCargoId = this.request.getParameter("orderCargoId");//货物Id
			String startProCityCounty = this.request.getParameter("startProCityCounty");//起始地
			String endProCityCounty = this.request.getParameter("endProCityCounty");//目的地
			String code = this.request.getParameter("code");//电话号码
			String carNumber = this.request.getParameter("carNumber");//车牌号
			String driverId = this.request.getParameter("driverId");//司机id
			String lastLocation = this.request.getParameter("lastLocation");//当前位置
			String carLength = this.request.getParameter("carLength");//长
			String carBarType = this.request.getParameter("carBarType");//板
			String carPlateType = this.request.getParameter("carPlateType");//栏
			DriverUserInfoDomain driverUserInfoDomain = new DriverUserInfoDomain();
			String linetypeset = "0";
			if("null".equals(carLength)) {
				carLength = "";
			}
			if("null".equals(carBarType)) {
				carBarType = "";
			}
			if("null".equals(carPlateType)) {
				carPlateType = "";
			}
			if(StringUtils.isEmpty(startProCityCounty) && StringUtils.isEmpty(endProCityCounty) && StringUtils.isEmpty(code) && 
					StringUtils.isEmpty(carNumber) && StringUtils.isEmpty(lastLocation) && StringUtils.isEmpty(carLength) && StringUtils.isEmpty(carBarType)  
					&& StringUtils.isEmpty(carPlateType)) {
				if(StringUtils.isNotEmpty(orderCargoId)) {
					OrderCargoInfoDomain OrderCargoInfoDomain = queryOrderCargoInfoService.queryOrderCargoInfoDomainById(orderCargoId);
					driverUserInfoDomain.setStartProvince(OrderCargoInfoDomain.getStartProvince());
					driverUserInfoDomain.setStartCity(OrderCargoInfoDomain.getStartCity());
					driverUserInfoDomain.setEndProvince(OrderCargoInfoDomain.getEndProvince());
					driverUserInfoDomain.setEndCity(OrderCargoInfoDomain.getEndCity());
					//driverUserInfoDomain.setCarLength(OrderCargoInfoDomain.getRequestCarLength());
					driverUserInfoDomain.setStartProCityCounty(OrderCargoInfoDomain.getStartProvince()+"-"+OrderCargoInfoDomain.getStartCity());
					driverUserInfoDomain.setEndProCityCounty(OrderCargoInfoDomain.getEndProvince()+"-"+OrderCargoInfoDomain.getEndCity());
					driverUserInfoDomain.setLastLocation(OrderCargoInfoDomain.getStartProvince()+"-"+OrderCargoInfoDomain.getStartCity());
					//linetypeset = "1";
				}
			}else {
				if(StringUtils.isNotEmpty(startProCityCounty)) {
					String[] startPcc = startProCityCounty.split("-");
					if(startPcc.length == 1) {
						driverUserInfoDomain.setStartProvince(startPcc[0]);
					}
					if(startPcc.length == 2) {
						driverUserInfoDomain.setStartProvince(startPcc[0]);
						driverUserInfoDomain.setStartCity(startPcc[1]);
					}
					if(startPcc.length == 3) {
						driverUserInfoDomain.setStartProvince(startPcc[0]);
						driverUserInfoDomain.setStartCity(startPcc[1]);
						driverUserInfoDomain.setStartCounty(startPcc[2]);
					}
				}
				if(StringUtils.isNotEmpty(endProCityCounty)) {
					String[] endPcc = endProCityCounty.split("-");
					if(endPcc.length == 1) {
						driverUserInfoDomain.setEndProvince(endPcc[0]);
					}
					if(endPcc.length > 1) {
						driverUserInfoDomain.setEndProvince(endPcc[0]);
						driverUserInfoDomain.setEndCity(endPcc[1]);
					}
					if(endPcc.length == 3) {
						driverUserInfoDomain.setEndProvince(endPcc[0]);
						driverUserInfoDomain.setEndCity(endPcc[1]);
						driverUserInfoDomain.setEndCounty(endPcc[2]);
					}
				}
				driverUserInfoDomain.setStartProCityCounty(startProCityCounty);
				driverUserInfoDomain.setEndProCityCounty(endProCityCounty);
				driverUserInfoDomain.setCode(code);
				driverUserInfoDomain.setCarNumber(carNumber);
				driverUserInfoDomain.setLastLocation(lastLocation);
				driverUserInfoDomain.setCarLength(carLength);
				driverUserInfoDomain.setCarBarType(carBarType);
				driverUserInfoDomain.setCarPlateType(carPlateType);
			}
			driverUserInfoDomain.setId(driverId);
			List<DriverUserInfoDomain> list = driverUserCarInfoService.queryDriverTheCarInfo(driverUserInfoDomain,linetypeset);
			driverUserInfoDomain.setList(list);
			String result = this.sendResponseToJson("1","订车查询司机信息成功!",driverUserInfoDomain);
			logger.warn("query the driver car success. json=[{}]",new Object[] { result });
		}catch (Exception e) {
			logger.error("query the driver car error!");
			throw new RuntimeException();
		}
	}
	
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}

	

}
