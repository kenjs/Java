package com.cy.dcts.driverCar.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.util.SysToolsUtil;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 司机报价
 * @author zdy
 *
 */
public class QueryDriverQuoteInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private IQueryOrderCargoInfoService queryOrderCargoInfoService;
    private IDriverUserCarInfoService driverUserCarInfoService;
    private OrderCargoInfo orderCargoInfo;
    private DriverUserInfoDomain driverUserInfoDomain;
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			return LOGIN;
		}
		
		logger.debug("add local order cargo info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		
		if(driverUserInfoDomain==null){
			driverUserInfoDomain=new DriverUserInfoDomain();
		}
		if(driverUserInfoDomain.getPageInfo()==null){
			driverUserInfoDomain.setPageInfo(new PageInfo());
		}
		//参数验证
		if(driverUserInfoDomain!=null&&SysToolsUtil.isNullOrEmpty(driverUserInfoDomain.getOrderId())){
			logger.warn("货源ID不能为空");
			return ERROR;
		}
		
		orderCargoInfo=queryOrderCargoInfoService.queryOrderCargoInfoById(driverUserInfoDomain.getOrderId());
		List<DriverUserInfoDomain> driverQuoteList=driverUserCarInfoService.queryDriverQuoteInfoByCargoId(driverUserInfoDomain);
		driverUserInfoDomain.setList(driverQuoteList);
		logger.warn("query driver quote info success! driverQuoteList.size()=[{}]",driverQuoteList.size());
		return SUCCESS;
	}
	public IQueryOrderCargoInfoService getQueryOrderCargoInfoService() {
		return queryOrderCargoInfoService;
	}
	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public OrderCargoInfo getOrderCargoInfo() {
		return orderCargoInfo;
	}
	public void setOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		this.orderCargoInfo = orderCargoInfo;
	}
	public DriverUserInfoDomain getDriverUserInfoDomain() {
		return driverUserInfoDomain;
	}
	public void setDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain) {
		this.driverUserInfoDomain = driverUserInfoDomain;
	}
   
}
