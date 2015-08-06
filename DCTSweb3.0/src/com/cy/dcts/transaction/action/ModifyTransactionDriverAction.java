package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;

public class ModifyTransactionDriverAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverUserCarInfoService driverUserCarInfoService;
	private ITransactionInfoService transactionInfoService;
	
	private TransactionInfoDomain transactionInfoDomain;
	@Override
	protected void execMethod() throws Exception {
		//验证是否登录
				if(getSessionUser()==null){
					this.sendResponseToJson("1", "请先登录");
					return ;
				}
				String sessionUserId=getSessionUser().getId();
				logger.debug("modify transaction driverId and orderStart begin. userId=[{}], companyId=[{}]",
						sessionUserId, getSessionUser().getCompanyId());
				if(transactionInfoDomain==null||
						StringUtils.isEmpty(transactionInfoDomain.getId())||
						StringUtils.isEmpty(transactionInfoDomain.getCode())){
					logger.debug("modify transaction driverId and orderStart parameter error!");
					this.sendResponseToJson("2", "参数错误");
					return;
				}
				
				//(3).输入的手机号不为空时去判断该司机是否在司机库中存在
					DriverUserInfo driverUserInfo=driverUserCarInfoService.queryDriverInfoByCode(transactionInfoDomain.getCode());
					if(driverUserInfo!=null){
						transactionInfoDomain.setDriverId(driverUserInfo.getId());
						transactionInfoDomain.setTradeStart(Constants.TRADE_START_WAITING_DRIVER_CONFIRM_KEY);
					}else{
						this.sendResponseToJson("3", "该承运司机还未安装快到网App");
						logger.warn("modify import transaction info driver not exists.");
						return ;
					}
					transactionInfoService.modifyTrandeDriverAndOrderStart(transactionInfoDomain);
				logger.debug("modify transaction driverId and orderStart success! userId=[{}]",
						sessionUserId);
				this.sendResponseToJson("0", "success");
		
}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}
	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}
	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	
}
