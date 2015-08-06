package com.cy.dcts.orderCargo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.util.ConvertCharactersUtil;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 更多货源信息分页
 * @author nxj
 *
 */
public class QueryOrderCargoListAction extends BasePageAction {

	private static final long serialVersionUID = 1899102231123459686L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;

	@Override
	protected String execMethod() throws Exception {
		OrderCargoInfoDomain orderCargoInfoDomain = getOrderCargoInfoDomain();
		if ("GET".equalsIgnoreCase(this.request.getMethod())) {
			if (this.request.getHeader("User-Agent").toLowerCase().indexOf("MSIE".toLowerCase()) != -1) {
				ConvertCharactersUtil.convertISO2GBK(orderCargoInfoDomain);
			} else {
				ConvertCharactersUtil.convertISO2UTF(orderCargoInfoDomain);
			}
		}
		List<OrderCargoInfoDomain> list = queryOrderCargoInfoService.queryStartDeployOrderCargoInfoByPage(orderCargoInfoDomain);
		orderCargoInfoDomain.setList(list);
		this.request.setAttribute("orderCargoInfoDomain",orderCargoInfoDomain);
		return SUCCESS;
	}

	private OrderCargoInfoDomain getOrderCargoInfoDomain() {
		OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
		PageInfo pageInfo = new PageInfo();
		orderCargoInfoDomain.setCargoFlag(Constants.CARGO_FLAG_PENDING_TRADE_KEY);
		orderCargoInfoDomain.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
		if(StringUtils.isNotEmpty(this.request.getParameter("pageSize"))) {
			pageInfo.setPageSize(Integer.parseInt(this.request.getParameter("pageSize")));
		}else {
			pageInfo.setPageSize(22);
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("curPage"))) {
			if("0".equals(this.request.getParameter("curPage"))) {
				pageInfo.setCurPage(0);
			}else {
				pageInfo.setCurPage(Integer.parseInt(this.request.getParameter("curPage"))-1);
				pageInfo.setCurPageNo(Integer.parseInt(this.request.getParameter("curPage")));
			}
		}else {
			pageInfo.setCurPage(0);
		}
		orderCargoInfoDomain.setPageInfo(pageInfo);
		return orderCargoInfoDomain;
	}
	
	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
}
