package com.cy.dcts.orderCargo.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;
/**
 * 查询我的货源
 * @author zdy
 *
 */
public class QueryLocalOrderCargoInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;
	private DataDictInfoDomain dataDictInfoDomain=new DataDictInfoDomain();
	private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if(this.getSessionUser()==null){
			return LOGIN;
		}
		logger.debug("query orderCargoInfo domain begin . userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//参数验证
		if(orderCargoInfoDomain==null){
			orderCargoInfoDomain=new OrderCargoInfoDomain();
		}
		if(orderCargoInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			orderCargoInfoDomain.setPageInfo(new PageInfo());
		}
		   //货物类型0-"请选择"（全部）
		if("0".equals(orderCargoInfoDomain.getCargoType())){
			orderCargoInfoDomain.setCargoType("");
		} 
		   //货源类型-1-"货源类型"（全部）
		if("-1".equals(orderCargoInfoDomain.getCargoFlag())){
			orderCargoInfoDomain.setCargoFlag("");
		}
		if("请输入货物名称".equals(orderCargoInfoDomain.getCargoName())){
			orderCargoInfoDomain.setCargoName("");
		}
		orderCargoInfoDomain.setDeployUserid(this.getSessionUser().getId());
		List<OrderCargoInfoDomain> list=queryOrderCargoInfoService.queryOrderCargoInfoPage(orderCargoInfoDomain);
		orderCargoInfoDomain.setList(list);
		logger.debug("query orderCargoInfo domain success ! list.size()=[{}]",list.size());
		return SUCCESS;
		
		
	}
	public IQueryOrderCargoInfoService getQueryOrderCargoInfoService() {
		return queryOrderCargoInfoService;
	}
	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}
	public void setOrderCargoInfoDomain(OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}
	public DataDictInfoDomain getDataDictInfoDomain() {
		return dataDictInfoDomain;
	}
	public void setDataDictInfoDomain(DataDictInfoDomain dataDictInfoDomain) {
		this.dataDictInfoDomain = dataDictInfoDomain;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
