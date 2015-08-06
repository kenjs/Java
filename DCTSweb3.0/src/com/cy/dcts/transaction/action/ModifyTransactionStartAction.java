package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;
import com.cy.dcts.orderCargoLast.service.IOrderCargoLastService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.transactionLast.service.ITransactionLastService;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;

/**
 * 修改交易状态及（货源已送达-订单完成）评价
 * 1.修改交易状态，添加交易历史记录
 * 2.若当前交易状态为：货源已送达 ——需添加货主对司机评价
 * @author zdy
 *
 */
public class ModifyTransactionStartAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionLastService transactionLastService;
	private ITransactionInfoService transactionInfoService;
	private IOrderCargoLastService orderCargoLastService;
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private IUserDriverAssessInfoService userDriverAssessInfoService;
	
    private UserDriverAssessInfo userDriverAssessInfo;
	private TransactionInfoDomain transactionInfoDomain;
	private TransactionInfo transactionInfo;
	@Override
	protected String execMethod() throws Exception {
		//验证是否登录
		if(getSessionUser()==null){
			return LOGIN;
		}
		String sessionUserId=getSessionUser().getId();
		logger.debug("modify transaction info trade start begin. userId=[{}], companyId=[{}]",
				sessionUserId, getSessionUser().getCompanyId());
		
		//参数验证
			if(transactionInfoDomain==null||StringUtils.isEmpty(transactionInfoDomain.getId())||
					StringUtils.isEmpty(transactionInfoDomain.getTradeStart())){
				logger.debug("modify transaction info trade flag parameter error!");
				//this.sendResponseToJson("2", "参数错误");
				return ERROR;
			}
			  //交易状态改为取消或订单完成时需修改货源状态
			if(Constants.TRADE_START_CLOSE_KEY.equals(transactionInfoDomain.getTradeStart())||Constants.TRADE_START_SUCCESS_KEY.equals(transactionInfoDomain.getTradeStart())){
				if(StringUtils.isEmpty(transactionInfoDomain.getCargoFlag())){
					//this.sendResponseToJson("4", "参数:货源状态为空");
					logger.debug("modify transaction info trade flag parameter error,cargoFlag null!");
					return ERROR;
				}
				
			}
			//给交易取消来源赋值(交易没有关闭)
			if(!Constants.TRADE_START_CLOSE_KEY.equals(transactionInfoDomain.getTradeStart())){
				transactionInfoDomain.setTradeCancelOrigin("");//交易没有关闭(sql中判断isNotEmpty 才修改)
			}

		//根据交易Id查询交易记录
		transactionInfo=transactionInfoService.queryTransactionInfoById(transactionInfoDomain.getId());
		if(transactionInfo==null){
			//this.sendResponseToJson("3", "交易记录不存在");
			logger.debug("modify transaction info trade flag transactionInfo Object no exists!");
			return ERROR;
		}else{
			//将页面传过来的交易状态值存入TransactionInfo对象
			transactionInfo.setTradeStart(transactionInfoDomain.getTradeStart());
			transactionInfo.setRemark(transactionInfoDomain.getRemark());
			
			//修改交易状态
			transactionInfoService.modifyTransactionInfoTradeStart(transactionInfoDomain);
			logger.debug("modify transaction info trade flag success! trandeStart=[{}]",transactionInfoDomain.getTradeStart());
			
			//添加交易历史记录
			String trandeLastId=transactionLastService.addTransactionLastInfo(transactionInfo);
			logger.debug("isnert transaction last info success ! trandeLastId=[{}]",trandeLastId);
			if(Constants.TRADE_START_SUCCESS_KEY.equals(transactionInfoDomain.getTradeStart())){//交易状态修改为订单完成
				//修改货源状态(并添加货源状态历史记录)
				saveOrderCargoAndCargoLastInfo(transactionInfo,transactionInfoDomain,sessionUserId);
				//添加货主对司机的评价
				//saveUserDriverAssessInfo(transactionInfo.getDriverId(),transactionInfo.getCargoId(),transactionInfo.getId());
				
				//若未发货确认(即发货时间为空)就直接收货确认时,则需将货源表中要求装货时间保存为交易表中的发货时间
				if(transactionInfo.getDeliveryTime()==null&&StringUtils.isNotEmpty(transactionInfoDomain.getDeliveryTime())){
					transactionInfoService.modifyDeliveryOrArrivalTime(transactionInfoDomain);
				}
			}
			
		}
		return SUCCESS;
	}
	
	//添加货主对司机的评价
	private String saveUserDriverAssessInfo(String driverId,String cargoId,String transactionId){
		logger.debug("save userDriverAssess info begin userId=[{}],companyCode=[{}]",getSessionUser().getId(),getSessionUser().getCompanyId());
		//参数验证
		if(userDriverAssessInfo==null||
				StringUtils.isEmpty(userDriverAssessInfo.getTradeEvaluateScore())||
				StringUtils.isEmpty(driverId)||
				StringUtils.isEmpty(cargoId)){
			logger.debug("save userDriverAssess info parameter error!");
			return ERROR;
		}
		userDriverAssessInfo.setUserId(getSessionUser().getId());
		userDriverAssessInfo.setCargoId(cargoId);
		userDriverAssessInfo.setDriverId(driverId);
		userDriverAssessInfo.setTransactionId(transactionId);
		//开始添加添加货主对司机的评价
		String id=userDriverAssessInfoService.addUserDriverAssessInfo(userDriverAssessInfo);
		logger.debug("save userDriverAssess success id=[{}],userId=[{}],driverId=[{}]",new Object[]{id,userDriverAssessInfo.getUserId(),userDriverAssessInfo.getDriverId()});
		return SUCCESS;
	}
	
	/*3种情况下交易状态改变需修改货源状态(并添加货源状态历史记录)：
	 * (1)交易开始（交易状态：等待司机确认）-修改货源状态：交易中1（在下面if中未写入）
	 * (2)交易取消(取消订单)——修改货源状态：待交易0（暂时不管发货时间是否过期）
	 * (3)订单完成——修改货源状态：交易成功2
	 * */
	private void saveOrderCargoAndCargoLastInfo(TransactionInfo transactionInfo,TransactionInfoDomain transactionInfoDomain,String sessionUserId){
		//修改货源状态
		logger.debug("-------------用户Id的值："+sessionUserId+"--------------------");
		saveOrderCargoInfoService.modifyOrderCargoFlag(transactionInfo.getCargoId(), sessionUserId, transactionInfoDomain.getCargoFlag());
		logger.debug("update order cargo flag success ! CargoFlag=[{}]",transactionInfoDomain.getCargoFlag());
		//添加货源历史记录
		String cargoLastId=orderCargoLastService.addOrderCargoLastInfo(transactionInfo, transactionInfoDomain.getCargoFlag());
		logger.debug("isnert order cargo last info success ! cargoLastId=[{}]",cargoLastId);
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
	public ITransactionLastService getTransactionLastService() {
		return transactionLastService;
	}
	public void setTransactionLastService(
			ITransactionLastService transactionLastService) {
		this.transactionLastService = transactionLastService;
	}

	public UserDriverAssessInfo getUserDriverAssessInfo() {
		return userDriverAssessInfo;
	}

	public void setUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo) {
		this.userDriverAssessInfo = userDriverAssessInfo;
	}

	public void setUserDriverAssessInfoService(
			IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}

	public void setOrderCargoLastService(
			IOrderCargoLastService orderCargoLastService) {
		this.orderCargoLastService = orderCargoLastService;
	}

	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}

	public TransactionInfo getTransactionInfo() {
		return transactionInfo;
	}

	public void setTransactionInfo(TransactionInfo transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

}
