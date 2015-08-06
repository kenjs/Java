package com.cy.dcts.transaction.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.TransactionReceiptPath;
import com.cy.dcts.transaction.service.ITransactionInfoService;

/**
 * 查询回单和发货单上传的图片
 * @author zdy
 *
 */
public class QueryReceiptOrShipperOrderPathAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	private TransactionReceiptPath transactionReceiptPath;
	private List<TransactionReceiptPath> transactionReceiptPathList;
	
	private String clickImagePath;//当请点击的图片的路径
	private String errorMessage;
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser()==null){
			return LOGIN;
		}
		logger.debug("query transaction receipt path info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//参数验证
		if(transactionReceiptPath==null||
				StringUtils.isEmpty(transactionReceiptPath.getTransactionId())||
				StringUtils.isEmpty(transactionReceiptPath.getType())){
			this.setErrorMessage("参数错误图片显示失败");
			return ERROR;
		}
		 //根据交易Id和类型查询发货单or回单上传图片--20140825
		transactionReceiptPathList=transactionInfoService.queryTransactionReceiptPathByTradeId(transactionReceiptPath.getTransactionId(),transactionReceiptPath.getType());
		logger.debug("query transaction receipt path info  success! transactionReceiptPathList.size()=[{}] ",transactionReceiptPathList.size());
		return SUCCESS;
	}
	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public TransactionReceiptPath getTransactionReceiptPath() {
		return transactionReceiptPath;
	}
	public void setTransactionReceiptPath(
			TransactionReceiptPath transactionReceiptPath) {
		this.transactionReceiptPath = transactionReceiptPath;
	}
	public List<TransactionReceiptPath> getTransactionReceiptPathList() {
		return transactionReceiptPathList;
	}
	public void setTransactionReceiptPathList(
			List<TransactionReceiptPath> transactionReceiptPathList) {
		this.transactionReceiptPathList = transactionReceiptPathList;
	}
	public String getClickImagePath() {
		return clickImagePath;
	}
	public void setClickImagePath(String clickImagePath) {
		this.clickImagePath = clickImagePath;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
