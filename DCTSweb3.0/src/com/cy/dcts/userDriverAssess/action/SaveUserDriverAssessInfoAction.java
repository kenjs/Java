package com.cy.dcts.userDriverAssess.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.transactionLast.service.ITransactionLastService;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;

/**
 * 添加货主对司机的评价
 * @author Administrator
 *
 */
public class SaveUserDriverAssessInfoAction extends BaseJsonAction{
	private static final long serialVersionUID = 2026125707557156173L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private IUserDriverAssessInfoService userDriverAssessInfoService;
    
    private UserDriverAssessInfo userDriverAssessInfo;
	@Override
	protected void execMethod() throws Exception {
		if(getSessionUser() == null){
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		logger.debug("save userDriverAssess info begin userId=[{}],companyCode=[{}]",getSessionUser().getId(),getSessionUser().getCompanyId());
		//参数验证
		if(userDriverAssessInfo==null||
				StringUtils.isEmpty(userDriverAssessInfo.getTransactionId())||
						StringUtils.isEmpty(userDriverAssessInfo.getDriverId())||
								StringUtils.isEmpty(userDriverAssessInfo.getCargoId())){
			logger.debug("save userDriverAssess info parameter error!");
			this.sendResponseToJson("2", "参数错误");
			return;
		}
		
		//根据交易Id查询货主对司机的评价
		UserDriverAssessInfoDomain userDriverAssessInfoDomain=userDriverAssessInfoService.queryUserDriverAssesByTradeId(userDriverAssessInfo.getTransactionId());
				if(userDriverAssessInfoDomain!=null){
					this.sendResponseToJson("3", "您已给司机评价过了，不能多次评价");
					return;
				}
		userDriverAssessInfo.setUserId(getSessionUser().getId());
		//开始添加添加货主对司机的评价
		String id=userDriverAssessInfoService.addUserDriverAssessInfo(userDriverAssessInfo);
		logger.debug("save userDriverAssess success id=[{}],userId=[{}],driverId=[{}]",new Object[]{id,userDriverAssessInfo.getUserId(),userDriverAssessInfo.getDriverId()});
		this.sendResponseToJson("0", "订车成功！");
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
