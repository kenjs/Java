package com.cy.dcts.orderCargo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;


public class DownloadOrderCargoFileAction  extends BasePageAction {

	/**
	 * 下载货源导入模板
	 */
	private static final long serialVersionUID = 2162653361902877247L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private InputStream fstream;

	private String filePath;

	private String fileName;
	
		
	@Override
	protected String execMethod() throws Exception {
		logger.info("download import order cargo template file begin...");
		try {
			logger.trace("content path=[{}]", this.getClass().getClassLoader().getResource("/").getPath());
			logger.trace("content path=[{}]", ServletActionContext.getServletContext().getRealPath(filePath+fileName));
			fstream = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(filePath+fileName)));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("download import order cargo template file fail, message:", e);
		}
		return null;
	}
	
	public InputStream getFstream() {
		return fstream;
	}

	public void setFstream(InputStream fstream) {
		this.fstream = fstream;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

