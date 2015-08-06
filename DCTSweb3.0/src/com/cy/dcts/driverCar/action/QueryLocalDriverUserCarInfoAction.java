package com.cy.dcts.driverCar.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

public class QueryLocalDriverUserCarInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private IDriverUserCarInfoService driverUserCarInfoService;
    private DriverUserInfoDomain driverUserInfoDomain;
    private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			return LOGIN;
		}
		
		logger.debug("query local driverCar info begin. userId=[{}], getCompanyId=[{}]", getSessionUser().getId(), getSessionUser().getCompanyId());
		//设置参数
		if(driverUserInfoDomain==null){
			driverUserInfoDomain=new DriverUserInfoDomain();
		}
		if(driverUserInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			driverUserInfoDomain.setPageInfo(new PageInfo());
		}
		List <DriverUserInfoDomain> list =driverUserCarInfoService.queryDriverUserInfoDomain(driverUserInfoDomain, getSessionUser().getId() );
		driverUserInfoDomain.setList(list);
		logger.debug("query_driver user info success ! list.size()=[{}]",list.size());
		return SUCCESS;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
