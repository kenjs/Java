package com.cy.dcts.transaction.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
/**
 * 查询交易记录（成功和货主取消后的订单），个人中心-首页（待处理订单）
 * @author zdy
 *
 */
public class QuerySuccessCloseTransactionInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private ITransactionInfoService transactionInfoService;
    private IQueryWebUserInfoService queryWebUserInfoService;
    
    private TransactionInfoDomain transactionInfoDomain;
	private WebUserInfo webUserInfo;
	private String signType;//用于区分点击导航栏中的‘我的快到’还是'货物跟踪'进入的个人中心-首页 （对应给标题加底色）
	private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			return LOGIN;
		}
		try {
		String sessionUserId=getSessionUser().getId();
		logger.debug("query SuccessClose transaction info info begin. userId=[{}], companyId=[{}]",
				sessionUserId, getSessionUser().getCompanyId());
		//初始化对象 
		if(transactionInfoDomain==null){
			transactionInfoDomain=new TransactionInfoDomain();
		}else{
			//个人中心-首页的查询（清空查询条件）
			if(!Constants.MY_TRANSACTION.equals(transactionInfoDomain.getMenuAId())){
				transactionInfoDomain.setStartTime("");
				transactionInfoDomain.setEndTime("");
				//transactionInfoDomain.setTradeStart("");
			}
		}
		if(transactionInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			transactionInfoDomain.setPageInfo(new PageInfo());
		}
		String menuId=transactionInfoDomain.getMenuAId();
		
		//查询交易记录（in成功和失败订单），个人中心-首页（not in待处理订单）
		List<TransactionInfoDomain> transactionList=transactionInfoService.queryInOrNotInSuccessCloseTransactionInfoDomain(transactionInfoDomain, sessionUserId);
		transactionInfoDomain.setList(transactionList);
		
		logger.debug("query SuccessClose transaction info info success! transactionList.size()=[{}] ",transactionList.size());
		if(Constants.MY_TRANSACTION.equals(menuId)){
			return "successCloseTrande";//跳入‘交易记录’页面（成功或关闭的订单）
		}
		//查询个人信息（个人中心-首页 是否实名认证，是否企业认证）
		webUserInfo=queryWebUserInfoService.queryWebUserInfoById(sessionUserId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return SUCCESS;//个人中心-首页（待处理订单）
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
	public WebUserInfo getWebUserInfo() {
		return webUserInfo;
	}
	public void setWebUserInfo(WebUserInfo webUserInfo) {
		this.webUserInfo = webUserInfo;
	}
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
