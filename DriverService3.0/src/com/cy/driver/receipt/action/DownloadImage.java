package com.cy.driver.receipt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.driver.receipt.domain.TransactionReceiptPathDomain;
import com.cy.driver.receipt.service.TransactionReceiptPathService;

public class DownloadImage extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5267765740040097741L;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String downFileName; 
	private InputStream inputStream;
	private TransactionReceiptPathService transactionReceiptPathService;

	@Override
	public String exec() {
		String filePath = "";
		String fileName = "";		
		try {
			String id = request.getParameter("id");
			if(StringUtils.isBlank(id)) {
				sendResponseToJson("-8", "未找到该图片的信息");
				return ERROR;
			}
			TransactionReceiptPathDomain domain = transactionReceiptPathService.selectReceiptById(id);
			if(domain != null) {
				filePath = domain.getReceiptPath();
				fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
				setDownFileName(fileName);
				inputStream = new FileInputStream(new File(filePath));
			} else {
				sendResponseToJson("0", "未找到该图片的信息");
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setTransactionReceiptPathService(
			TransactionReceiptPathService transactionReceiptPathService) {
		this.transactionReceiptPathService = transactionReceiptPathService;
	}

	@Override
	protected void execMethod() throws Exception {
		
	}

}
