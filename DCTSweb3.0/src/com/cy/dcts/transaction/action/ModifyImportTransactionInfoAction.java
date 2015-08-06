package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class ModifyImportTransactionInfoAction extends BasePageAction {
	private static final long serialVersionUID = -6851976278436983840L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private ITransactionInfoService transactionInfoService;
	private IDriverUserCarInfoService driverUserCarInfoService;
	private IQueryWebUserInfoService queryWebUserInfoService;
	private TransactionInfoDomain transactionInfoDomain;
	private OrderCargoInfo orderCargoInfo;
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
		return LOGIN;
	}
	logger.debug("modify import transaction info begin. userId=[{}], companyId=[{}]",
			getSessionUser().getId(), getSessionUser().getCompanyId());
	//对象及参数验证是否为空
	if(transactionInfoDomain==null||
			StringUtils.isEmpty(transactionInfoDomain.getId())||
			StringUtils.isEmpty(transactionInfoDomain.getCargoId())||
			StringUtils.isEmpty(transactionInfoDomain.getReceiverCode())){
		logger.warn("modify import transaction info  parameter error.");
		transactionInfoDomain.setErrorMessage("参数错误");
		return ERROR;
	}
	//对象及参数验证是否有效 String encoded,String userType
	//（1）根据收货方编码，用户类型和父级Id（即当请登录者的userId）判断修改的收货方是否有效（在web_user_info表中存在）
	Integer receiverId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),transactionInfoDomain.getReceiverCode(),Constants.USER_TYPE_RECEIVE_KEY);
	if(receiverId==null||"".equals(receiverId)){
		transactionInfoDomain.setErrorMessage("编码为"+transactionInfoDomain.getReceiverCode()+"的收货方不存在");
		logger.warn("modify import transaction info receiver not exists.");
		return ERROR;
	}else{
		transactionInfoDomain.setReceiverCodeId(receiverId.toString());
	}
    //（2）自己不修改自己的相关信息：a.货主（发货方）登录不修改发货方编码即发货单号；b.承运商登(物流企业)录不修改承运商编码
	//判断登录的用户类型
	if(Constants.USER_TYPE_ENTERPRISE_KEY.equals(getSessionUser().getUserType())){//登录用户是承运商
		//验证修改的货主（发货方）是否有效，表t_web_user_info根据父级userId，用户类型和发货方编码
		Integer shipperId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),transactionInfoDomain.getShipperCode(),Constants.USER_TYPE_SHIPPER_KEY);
		if(shipperId==null||"".equals(shipperId)){
			transactionInfoDomain.setErrorMessage("编码为"+transactionInfoDomain.getShipperCode()+"的发货方不存在");
			logger.warn("modify import transaction info shipper not exists.");
			return ERROR;
		}else{
			transactionInfoDomain.setShipperCodeId(shipperId.toString());
		}
		transactionInfoDomain.setShipperCompanyId(getSessionUser().getId());
	}else if(Constants.USER_TYPE_SHIPPER_KEY.equals(getSessionUser().getUserType())){//登录用户是发货方
		//验证修改的承运商（物流企业）是否有效，表t_web_user_info根据父级userId，用户类型和物流企业用户的编码
		if(StringUtils.isNotEmpty(transactionInfoDomain.getShipperCompanyCode())){
				Integer shipperComId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),transactionInfoDomain.getShipperCompanyCode(),Constants.USER_TYPE_ENTERPRISE_KEY);
				if(shipperComId==null||"".equals(shipperComId)){
					transactionInfoDomain.setErrorMessage("编码为"+transactionInfoDomain.getShipperCompanyCode()+"的承运商不存在");
					logger.warn("modify import transaction info shipper company not exists.");
					return ERROR;
				}else{
					transactionInfoDomain.setShipperCompanyId(shipperComId.toString());
				}
		}else{
			transactionInfoDomain.setShipperCompanyId(null);
		}
				transactionInfoDomain.setShipperCodeId(getSessionUser().getId());
	}
	
	
	//(3).输入的手机号不为空时去判断该司机是否在司机库中存在
	if(StringUtils.isNotEmpty(transactionInfoDomain.getCode())){
		DriverUserInfo driverUserInfo=driverUserCarInfoService.queryDriverInfoByCode(transactionInfoDomain.getCode());
		if(driverUserInfo!=null){
			transactionInfoDomain.setDriverId(driverUserInfo.getId());
			transactionInfoDomain.setTradeStart(Constants.TRADE_START_WAITING_DRIVER_CONFIRM_KEY);
		}else{
			transactionInfoDomain.setErrorMessage("该承运司机还未安装快到网App");
			logger.warn("modify import transaction info driver not exists.");
			return ERROR;
		}
	}else{
		//司机号码为空时交易状态未待交易（无效）
		transactionInfoDomain.setTradeStart(Constants.TRADE_START_INVALID_KEY);
	}
	//修改导入的订单货源
	orderCargoInfo.setId(transactionInfoDomain.getCargoId());
	orderCargoInfo.setCargoName(transactionInfoDomain.getCargoName());
	saveOrderCargoInfoService.modifyImportOrderCargoInfo(orderCargoInfo);
	//修改导入的订单信息
	transactionInfoService.modifyImportTransactionInfo(transactionInfoDomain);
	logger.debug("modify import transaction info success!");
		return SUCCESS;
	}
	public ISaveOrderCargoInfoService getSaveOrderCargoInfoService() {
		return saveOrderCargoInfoService;
	}
	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}
	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}
	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}
	public OrderCargoInfo getOrderCargoInfo() {
		return orderCargoInfo;
	}
	public void setOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		this.orderCargoInfo = orderCargoInfo;
	}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public IQueryWebUserInfoService getQueryWebUserInfoService() {
		return queryWebUserInfoService;
	}
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}
