package com.cy.dcts.orderCargo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 打开修改保存 本地货源信息页面 action
 * @author zdy
 */
public class OpenModifyLocalOrderCargoInfoAction extends BasePageAction {

	
	private static final long serialVersionUID = -3562119547747650583L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;
	private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化数据字典

	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser() == null){
			return LOGIN;
		}
		logger.debug("select order cargo info begin. userId=[{}], companyCode=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		if(orderCargoInfoDomain == null || orderCargoInfoDomain.getId() == null) {
			logger.debug("select order cargo info. orderCargoInfoDomain , orderCargoInfoDomain.getId()",
					orderCargoInfoDomain,  orderCargoInfoDomain.getId());
			return ERROR;
		}
		orderCargoInfoDomain = queryOrderCargoInfoService.queryOrderCargoInfoDomainById(orderCargoInfoDomain.getId());
		if(orderCargoInfoDomain == null) {
			logger.debug("select order cargo info. orderCargoInfoDomain",
					orderCargoInfoDomain);
			return ERROR;
		}
		logger.debug("select order cargo info close");
		return SUCCESS;
	}
	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}


	public void setOrderCargoInfoDomain(OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}


	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
	
	public DataDictInfoDomain getDataDictInfoDomain() {
		return dataDictInfoDomain;
	}

}
