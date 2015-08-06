package com.cy.driver.receipt.action;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.bo.TransactionReceiptPathBo;
import com.cy.common.util.FileUtil;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.receipt.service.TransactionReceiptPathService;

/**
 * 回单上传
 * @author haoyong
 *
 */
public class ReceiptUploadAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7450499862794627227L;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionReceiptPathService transactionReceiptPathService;
	private OperationLogService operationLogService;

	// 上传文件  
    private File file;// 拦截器会为你在缓冲区创建临时文件，这是临时文件对象  
    private String fileContentType;// 头域中的值  
    private String fileFileName;// 报文体中的name  
	private String receiptUploadPath;
	
	@Override
	protected void execMethod() throws Exception {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)) {
				log.info("用户未登陆");
				sendResponseToJson("-9", "用户未登陆");
				return;
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return;
			}
			log2Db(driverId);
			
			String transactionId = request.getParameter("transactionId");
			String type = request.getParameter("type");
			if(StringUtils.isBlank(transactionId)) {
				sendResponseToJson("-8", "订单ID不能为空.");
				return;
			}
			if(StringUtils.isBlank(type)) {
				sendResponseToJson("-8", "类型不能为空. ");
			}
			String filePath = receiptUploadPath + "APP/" + driverId + "/";
			TransactionReceiptPathBo bo = new TransactionReceiptPathBo();
			bo.setTransactionId(transactionId);
			bo.setReceiptPath(filePath + fileFileName);
			bo.setType(type);
			int resultI = transactionReceiptPathService.insertTransactionReceiptPath(bo);
			if(resultI != 0) {
				sendResponseToJson("1", "回单上传成功. ",resultI);
				FileUtil.uploadFile(file, filePath, fileFileName);
			} else {
				sendResponseToJson("-8", "回单上传失败.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			sendResponseToJson("-8", e.getMessage());
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("receiptUpload");
		bo.setOperationType(66);
		bo.setRemark("回单上传");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setTransactionReceiptPathService(
			TransactionReceiptPathService transactionReceiptPathService) {
		this.transactionReceiptPathService = transactionReceiptPathService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	public String getReceiptUploadPath() {
		return receiptUploadPath;
	}

	public void setReceiptUploadPath(String receiptUploadPath) {
		this.receiptUploadPath = receiptUploadPath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}