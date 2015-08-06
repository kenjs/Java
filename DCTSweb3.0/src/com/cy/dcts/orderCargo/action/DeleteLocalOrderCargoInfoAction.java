package com.cy.dcts.orderCargo.action;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseAjaxAction;
import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;

/**
 * 删除货源action
 * @author zdy
 */
public class DeleteLocalOrderCargoInfoAction extends BaseJsonAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;

	
	@Override
	protected void execMethod() throws Exception {
		//是否登录
		if (getSessionUser() == null) {
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		//参数验证
		if(orderCargoInfoDomain == null || orderCargoInfoDomain.getId() == null){
			logger.warn("delete local order cargo info fail. driverId=[{}]", 
					new Object[] {orderCargoInfoDomain.getId()});
			this.sendResponseToJson("2", "删除失败，参数不能为空");
			return ;
		}
		logger.debug("delete local order cargo info begin. userId=[{}], companyId=[{}], cargoId=[{}]",
				new Object[]{getSessionUser().getId(), getSessionUser().getCompanyId(), orderCargoInfoDomain.getId()});
		
		//根据Id查询货源（当前用户Id与原有的用户Id比对 判断权限）
		OrderCargoInfo orderCargoInfo = queryOrderCargoInfoService.queryOrderCargoInfoById(orderCargoInfoDomain.getId());
		
		if(orderCargoInfo == null ||
				!orderCargoInfo.getDeployUserid().equals(getSessionUser().getId()) ||	//只能删除自己创建的货物
				!orderCargoInfo.getCompanyId().equals(getSessionUser().getCompanyId())){
			this.sendResponseToJson("3", "删除失败，权限不足！");
			logger.warn("delete local order cargo info fail.  userId=[{}], companyId=[{}], cargoId=[{}]",
				new Object[]{getSessionUser().getId(), getSessionUser().getCompanyId(), orderCargoInfoDomain.getId()});
			return ;
		}
		
		saveOrderCargoInfoService.modifyOrderDeleteFlag(orderCargoInfoDomain.getId(), getSessionUser().getId(), Constants.DELETED_FLAG_TRUE);
		
		logger.debug("delete local order cargo info Success. userId=[{}], companyId=[{}], cargoId=[{}]",
				new Object[]{getSessionUser().getId(), getSessionUser().getCompanyId(), orderCargoInfoDomain.getId()});
		this.sendResponseToJson("0", "success");
}
	
	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}

	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}

	public void setOrderCargoInfoDomain(
			OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}

	

	

}
