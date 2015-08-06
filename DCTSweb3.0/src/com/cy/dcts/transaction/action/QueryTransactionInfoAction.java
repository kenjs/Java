package com.cy.dcts.transaction.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;
/*
 * 查询我的订单记录
 * zdy
 */
public class QueryTransactionInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private ITransactionInfoService transactionInfoService;
    private TransactionInfoDomain transactionInfoDomain;
    private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化字典表
    private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			return LOGIN;
		}
		logger.debug("query transaction info info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//初始化对象 
		if(transactionInfoDomain==null){
			transactionInfoDomain=new TransactionInfoDomain();
		}
		if(transactionInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			transactionInfoDomain.setPageInfo(new PageInfo());
		}
		//参数设置
		if("0".equals(transactionInfoDomain.getCargoType())) {
			transactionInfoDomain.setCargoType("");
		}
		if("请输入货物名称".equals(transactionInfoDomain.getCargoName())){
			transactionInfoDomain.setCargoName("");
		}
		List<TransactionInfoDomain> transactionList = null;
		
		if("0".equals(getSessionUser().getParentId())) {
			transactionList=transactionInfoService.queryTransactionInfoDomain(transactionInfoDomain,getSessionUser().getId());
		} else {
			transactionList=transactionInfoService.queryTransactionInfoDomain2(transactionInfoDomain,getSessionUser().getId(),getSessionUser().getParentId());
		}
		
		transactionInfoDomain.setList(transactionList);
		logger.debug("query transaction info info success! transactionList.size()=[{}] ",transactionList.size());
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
