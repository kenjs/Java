package com.cy.dcts.transaction.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionReceiptPath;
import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.LocationCollectInfoDomain;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class QueryTransactionDetailAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private IQueryOrderCargoInfoService queryOrderCargoInfoService;
    private IDriverUserCarInfoService driverUserCarInfoService;
    private ITransactionInfoService transactionInfoService;
    private IUserDriverAssessInfoService userDriverAssessInfoService;
    private IQueryWebUserInfoService queryWebUserInfoService;

    private OrderCargoInfo orderCargoInfo;
    private DriverUserInfoDomain driverUserInfoDomain;
    private TransactionInfo transactionInfo;
    private LocationCollectInfoDomain locationCollectInfoDomain;
    private UserDriverAssessInfo userDriverAssessInfo;
    private String userDriverAssessCount;//对应某次交易货主对司机的评价次数<!-- 20140707 -->
    private WebUserInfoDamain webUserInfoDamain;
    private List<TransactionReceiptPath> transactionReceiptPathList;
    private List<TransactionReceiptPath> transactionShipperOrderPathList;
    
	@Override
	protected String execMethod() throws Exception {
		//判断是否登录
		if(this.getSessionUser()==null){
			return LOGIN;
		}
		logger.debug("query transaction detail begin userId=[{}], companyId=[{}]",getSessionUser().getId(), getSessionUser().getCompanyId());
    	
		//根据交易Id查询交易记录6
		transactionInfo=transactionInfoService.queryTransactionInfoById(transactionInfo.getId());
		//验证参数
		String transactionSteps="";
		if(driverUserInfoDomain!=null&&!StringUtils.isEmpty(driverUserInfoDomain.getTransactionStep())){
			transactionSteps=driverUserInfoDomain.getTransactionStep();
		}else{
			logger.warn("query transaction detail  parameter error");
			return ERROR;
		}
		if(StringUtils.isEmpty(transactionInfo.getCargoId())&&StringUtils.isEmpty(transactionInfo.getDriverId())){
			logger.warn("query transaction detail  parameter error");
			return ERROR;
		}
		userDriverAssessCount="0";
		//根据交易Id查询货主对司机的评价
		userDriverAssessInfo=userDriverAssessInfoService.queryUserDriverAssesInfoByTradeId(transactionInfo.getId());
		if(userDriverAssessInfo!=null){
			userDriverAssessCount="1";
			
		}
		//根据货源Id查询货源信息
		orderCargoInfo=queryOrderCargoInfoService.queryOrderCargoInfoById(transactionInfo.getCargoId());
		
		//根据司机Id查询司机信息
		driverUserInfoDomain=driverUserCarInfoService.queryDriverUserInfoDomainById(transactionInfo.getDriverId());
		if(driverUserInfoDomain!=null){
			driverUserInfoDomain.setTransactionStep(transactionSteps);
		}
		//查询承运商的信息
		webUserInfoDamain=queryWebUserInfoService.queryWebUserCompanyoyById(transactionInfo.getShipperCompanyId());
		
		//导入（收货方ID不为空）的订单需查询它的回单和上传的图片
		if(StringUtils.isNotEmpty(transactionInfo.getReceiverCodeId())){
        //根据交易Id和类型查询回单上传图片--20140825
		transactionReceiptPathList=transactionInfoService.queryTransactionReceiptPathByTradeId(transactionInfo.getId(),Constants.TYPE_RECEIPT_KEY);
		
		//根据交易Id和类型查询发货单上传图片--20140825
		transactionShipperOrderPathList = transactionInfoService.queryTransactionReceiptPathByTradeId(transactionInfo.getId(),Constants.TYPE_SHIPPER_ORDER_KEY);
		}
		//if(Constants.TRADE_START_ARRIVED_KEY.equals(transactionSteps)){
		//logger.debug("query transaction detail  success !");
		//return "successArrived";
		//}
		this.request.setAttribute("receiveSure",this.getSessionUser().getReceiveSure());
		logger.debug("query transaction detail success !");
		if(! "0".equals(getSessionUser().getParentId())) {
			return "transactionDetail";
		}
		return SUCCESS;
	}
	public IQueryOrderCargoInfoService getQueryOrderCargoInfoService() {
		return queryOrderCargoInfoService;
	}
	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
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
	public TransactionInfo getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(TransactionInfo transactionInfo) {
		this.transactionInfo = transactionInfo;
	}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public LocationCollectInfoDomain getLocationCollectInfoDomain() {
		return locationCollectInfoDomain;
	}
	public void setLocationCollectInfoDomain(
			LocationCollectInfoDomain locationCollectInfoDomain) {
		this.locationCollectInfoDomain = locationCollectInfoDomain;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public IUserDriverAssessInfoService getUserDriverAssessInfoService() {
		return userDriverAssessInfoService;
	}
	public void setUserDriverAssessInfoService(
			IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}
	public String getUserDriverAssessCount() {
		return userDriverAssessCount;
	}
	public void setUserDriverAssessCount(String userDriverAssessCount) {
		this.userDriverAssessCount = userDriverAssessCount;
	}
	public UserDriverAssessInfo getUserDriverAssessInfo() {
		return userDriverAssessInfo;
	}
	public void setUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo) {
		this.userDriverAssessInfo = userDriverAssessInfo;
	}
	public IQueryWebUserInfoService getQueryWebUserInfoService() {
		return queryWebUserInfoService;
	}
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}
	public WebUserInfoDamain getWebUserInfoDamain() {
		return webUserInfoDamain;
	}
	public void setWebUserInfoDamain(WebUserInfoDamain webUserInfoDamain) {
		this.webUserInfoDamain = webUserInfoDamain;
	}
	public List<TransactionReceiptPath> getTransactionReceiptPathList() {
		return transactionReceiptPathList;
	}
	public void setTransactionReceiptPathList(
			List<TransactionReceiptPath> transactionReceiptPathList) {
		this.transactionReceiptPathList = transactionReceiptPathList;
	}
	public List<TransactionReceiptPath> getTransactionShipperOrderPathList() {
		return transactionShipperOrderPathList;
	}
	public void setTransactionShipperOrderPathList(
			List<TransactionReceiptPath> transactionShipperOrderPathList) {
		this.transactionShipperOrderPathList = transactionShipperOrderPathList;
	}

}
