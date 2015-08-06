package com.cy.dcts.pactDriverInfo.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.PactDriverInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.pactDriverInfo.service.IPactDriverInfoService;

/**
 * 查询合同司机信息列表
 * @author Administrator
 *
 */
public class QueryPactDriveInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IPactDriverInfoService pactDriverInfoService;
	private PactDriverInfoDomain pactDriverInfoDomain;
	private String menuAId;//左边菜单链接
	private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
	
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if(this.getSessionUser()==null){
			return LOGIN;
		}
		String userId=getSessionUser().getId();
		logger.debug("query pact driver info domain begin . userId=[{}], companyId=[{}]",
				userId, getSessionUser().getCompanyId());
		if(pactDriverInfoDomain==null){
			pactDriverInfoDomain=new PactDriverInfoDomain();
		}
		if(pactDriverInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			pactDriverInfoDomain.setPageInfo(new PageInfo());
		}
		pactDriverInfoDomain.setUserId(userId);
		//查询合同司机信息列表
		List<PactDriverInfoDomain> list=pactDriverInfoService.queryPactDriverInfo(pactDriverInfoDomain);
		pactDriverInfoDomain.setList(list);
		
		logger.debug("query pact driver info domain success! list.size()= [{}]",
				list.size());
		return SUCCESS;		
	}
	public IPactDriverInfoService getPactDriverInfoService() {
		return pactDriverInfoService;
	}
	public void setPactDriverInfoService(
			IPactDriverInfoService pactDriverInfoService) {
		this.pactDriverInfoService = pactDriverInfoService;
	}
	public PactDriverInfoDomain getPactDriverInfoDomain() {
		return pactDriverInfoDomain;
	}
	public void setPactDriverInfoDomain(PactDriverInfoDomain pactDriverInfoDomain) {
		this.pactDriverInfoDomain = pactDriverInfoDomain;
	}
	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
