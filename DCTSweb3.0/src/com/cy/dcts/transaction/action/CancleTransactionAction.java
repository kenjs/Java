package com.cy.dcts.transaction.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;
import com.cy.dcts.orderCargoLast.service.IOrderCargoLastService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.transactionLast.service.ITransactionLastService;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;
/**
 * 取消订单
 * 1.修改交易状态，添加交易历史记录
 * 2.修改货源状态，添加货源历史记录
 * 3.评价（货主对司机的评价）
 * @author zdy
 *
 */
public class CancleTransactionAction  extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionLastService transactionLastService;
	private ITransactionInfoService transactionInfoService;
	private IOrderCargoLastService orderCargoLastService;
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private IUserDriverAssessInfoService userDriverAssessInfoService;
	
	private TransactionInfoDomain transactionInfoDomain;
    private UserDriverAssessInfo userDriverAssessInfo;
	@Override
	protected void execMethod() throws Exception {
		//验证是否登录
		if(getSessionUser()==null){
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		logger.debug("modify transaction info trade start begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//参数验证
			if(transactionInfoDomain==null||StringUtils.isEmpty(transactionInfoDomain.getId())||
					StringUtils.isEmpty(transactionInfoDomain.getTradeStart())){
				logger.debug("modify transaction info trade flag parameter error!");
				this.sendResponseToJson("2", "参数错误");
				return;
			}
			
			if(Constants.TRADE_START_CLOSE_KEY.equals(transactionInfoDomain.getTradeStart())||Constants.TRADE_START_SUCCESS_KEY.equals(transactionInfoDomain.getTradeStart())){
				if(StringUtils.isEmpty(transactionInfoDomain.getCargoFlag())){
					this.sendResponseToJson("4", "参数:货源状态为空");
					return;
				}
				
			}
		
		//根据交易Id查询交易记录
		TransactionInfo transactionInfo=transactionInfoService.queryTransactionInfoById(transactionInfoDomain.getId());
				
		if(transactionInfo==null){
			this.sendResponseToJson("3", "交易记录不存在");
			return;
		}else{
			//给交易取消来源赋值(页面传过来的交易状态为取消)
			if(Constants.TRADE_START_CLOSE_KEY.equals(transactionInfoDomain.getTradeStart())){
				//(司机已先取消了，修改取消来源为：司机取消后，我方取消)
				if(Constants.TRADE_CANCEL_ORIGIN_OPPOSITE_KEY.equals(transactionInfo.getTradeCancelOrigin())){
					transactionInfoDomain.setTradeCancelOrigin(Constants.TRADE_CANCEL_ORIGIN_OUR_PASSIVE_KEY);//司机取消后，我方取消
				}else{//货主方主动取消
					transactionInfoDomain.setTradeCancelOrigin(Constants.TRADE_CANCEL_ORIGIN_OUR_KEY);//货主方关闭（我方取消）
				}
				
			}
			
			//将页面传过来的交易状态值存入TransactionInfo对象
			transactionInfo.setTradeStart(transactionInfoDomain.getTradeStart());
			transactionInfo.setRemark(transactionInfoDomain.getRemark());
			
			//修改交易状态
			transactionInfoService.modifyTransactionInfoTradeStart(transactionInfoDomain);
			logger.debug("modify transaction info trade flag success! trandeStart=[{}]",transactionInfoDomain.getTradeStart());
			
			//添加交易历史记录
			String trandeLastId=transactionLastService.addTransactionLastInfo(transactionInfo);
			logger.debug("isnert transaction last info success ! trandeLastId=[{}]",trandeLastId);
			/*3种情况下交易状态改变需修改货源状态(并添加货源状态历史记录)：
			 * (1)交易开始（交易状态：等待司机确认）-修改货源状态：交易中1（在下面if中未写入）
			 * (2)交易取消（取消订单)——修改货源状态：待交易0（暂时不管发货时间是否过期）
			 * (3)交易成功——修改货源状态：交易成功2
			 * */
			if(Constants.TRADE_START_CLOSE_KEY.equals(transactionInfoDomain.getTradeStart())||Constants.TRADE_START_SUCCESS_KEY.equals(transactionInfoDomain.getTradeStart())){
				
					saveOrderCargoInfoService.modifyOrderCargoFlag(transactionInfo.getCargoId(), this.getSessionUser().getId(), transactionInfoDomain.getCargoFlag());
					logger.debug("update order cargo flag success ! CargoFlag=[{}]",transactionInfoDomain.getCargoFlag());
					String cargoLastId=orderCargoLastService.addOrderCargoLastInfo(transactionInfo, transactionInfoDomain.getCargoFlag());
					logger.debug("isnert order cargo last info success ! cargoLastId=[{}]",cargoLastId);
				
			}
			//货主对司机还没有评价过，可评价
			if("0".equals(transactionInfoDomain.getUserDriverAssessCount())){
				//货主对司机进行评价
				String assessRetuVal=saveUserDriverAssessInfo(transactionInfo.getDriverId(),transactionInfo.getCargoId(),transactionInfo.getId());
				if("error".equals(assessRetuVal)){
					this.sendResponseToJson("5", "评价时参数错误");
					return;
				}
			}
			this.sendResponseToJson("0", "success");
		}
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
	public IOrderCargoLastService getOrderCargoLastService() {
		return orderCargoLastService;
	}
	public void setOrderCargoLastService(
			IOrderCargoLastService orderCargoLastService) {
		this.orderCargoLastService = orderCargoLastService;
	}
	public ISaveOrderCargoInfoService getSaveOrderCargoInfoService() {
		return saveOrderCargoInfoService;
	}
	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}

	public IUserDriverAssessInfoService getUserDriverAssessInfoService() {
		return userDriverAssessInfoService;
	}

	public void setUserDriverAssessInfoService(
			IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}

	public UserDriverAssessInfo getUserDriverAssessInfo() {
		return userDriverAssessInfo;
	}

	public void setUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo) {
		this.userDriverAssessInfo = userDriverAssessInfo;
	}
}
