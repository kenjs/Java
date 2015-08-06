package com.cy.test.uploadFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseAction;

public class DownloadImage extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4808716385349428701L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private String downFileName; 
	private InputStream inputStream;
    
	@Override
	public String exec() {
		String filePath = "E:\\apache-tomcat-6.0.37\\file\\5fdf8db1cb13495474d8fcef544e9258d0094a72.jpg";
		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		setDownFileName(fileName);
		try {
			inputStream = new FileInputStream(new File(filePath));		
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public InputStream getInputStream(){
		return inputStream;
	}
	
	public void setImgStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

}
