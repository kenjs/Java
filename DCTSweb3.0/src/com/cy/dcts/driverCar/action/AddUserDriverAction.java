package com.cy.dcts.driverCar.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.UserDriverInfo;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

public class AddUserDriverAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserCarInfoService driverUserCarInfoService;
	private DriverUserInfoDomain driverUserInfoDomain;
	@Override
	protected void execMethod() throws Exception {
		//判断是否登陆
				if (getSessionUser() == null) {
					this.sendResponseToJson("1", "请先登录");
					return ;
				}
				logger.debug("add user driver info begin. userId=[{}], companyId=[{}]",
						getSessionUser().getId(), getSessionUser().getCompanyId());
		if(driverUserInfoDomain==null||StringUtils.isEmpty(driverUserInfoDomain.getId())){
			this.sendResponseToJson("2", "添加失败，参数不能为空！");
			return ;
		}
		//判断该司机是否已被收藏
		List<UserDriverInfo> list=driverUserCarInfoService.queryUserDriverInfoByDriverID(driverUserInfoDomain.getId(),getSessionUser().getId());
		logger.debug("query User Driver Info ByDriverID ! list.size()=[{}]",list.size());
		if(list.size()>0){
			this.sendResponseToJson("3", "该司机已收藏");
			return ;
		}else{
		String id=driverUserCarInfoService.addUserDriver(driverUserInfoDomain.getId(), getSessionUser().getId());
		logger.debug("add user driver info success! addId=[{}]",id);
		this.sendResponseToJson("0", "success");
		}
		
	}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public DriverUserInfoDomain getDriverUserInfoDomain() {
		return driverUserInfoDomain;
	}
	public void setDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain) {
		this.driverUserInfoDomain = driverUserInfoDomain;
	}

}
