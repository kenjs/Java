package com.cy.dcts.pactDriverInfo.action;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.PactDriverInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.PactDriverInfoDomain;
import com.cy.dcts.common.util.DateUtil;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.pactDriverInfo.service.IPactDriverInfoService;

public class AddPactDriverInfoAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IPactDriverInfoService pactDriverInfoService;
	private IDriverUserCarInfoService driverUserCarInfoService;
	private PactDriverInfoDomain pactDriverInfoDomainObj;
	
	@Override
	protected void execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		logger.debug("add pact driver info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//参数验证
		if(StringUtils.isEmpty(pactDriverInfoDomainObj.getCode())){
			this.sendResponseToJson("2", "请输入司机手机号");
			return ;
		}
		DriverUserInfo driverUserInfo=driverUserCarInfoService.queryDriverInfoByCode(pactDriverInfoDomainObj.getCode());
		if(driverUserInfo==null){
			this.sendResponseToJson("3", "该司机还未安装快到网APP,请提醒司机安装");
			return ;
		}else{
			pactDriverInfoDomainObj.setDriverId(driverUserInfo.getId());
		}
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		//对象赋值
		if(StringUtils.isEmpty(pactDriverInfoDomainObj.getPactStartTime())||
				StringUtils.isEmpty(pactDriverInfoDomainObj.getPactEndTime())||
				df.parse(pactDriverInfoDomainObj.getPactStartTime()).getTime()>df.parse(pactDriverInfoDomainObj.getPactEndTime()).getTime()){
			pactDriverInfoDomainObj.setPactValidDay("0");
		}else{
			long dayCount=(df.parse(pactDriverInfoDomainObj.getPactEndTime()).getTime()-df.parse(pactDriverInfoDomainObj.getPactStartTime()).getTime())/(24*3600*1000);
			pactDriverInfoDomainObj.setPactValidDay(String.valueOf(dayCount+1));
		}
		pactDriverInfoDomainObj.setUserId(getSessionUser().getId());
		pactDriverInfoDomainObj.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
		
		//开始新增合同司机信息
		String id=pactDriverInfoService.addPactDriverInfo(pactDriverInfoDomainObj);
		logger.debug("add pact driver info success. addId=[{}]",id);
		this.sendResponseToJson("0", "success",id);
		
	}
	public IPactDriverInfoService getPactDriverInfoService() {
		return pactDriverInfoService;
	}
	public void setPactDriverInfoService(
			IPactDriverInfoService pactDriverInfoService) {
		this.pactDriverInfoService = pactDriverInfoService;
	}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public PactDriverInfoDomain getPactDriverInfoDomainObj() {
		return pactDriverInfoDomainObj;
	}
	public void setPactDriverInfoDomainObj(
			PactDriverInfoDomain pactDriverInfoDomainObj) {
		this.pactDriverInfoDomainObj = pactDriverInfoDomainObj;
	}
	
   
}
