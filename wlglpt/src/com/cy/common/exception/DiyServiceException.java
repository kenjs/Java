package com.cy.common.exception;

import java.util.List;

/**
 * Service����ȫ�Զ����Exception.
 * ������Ϣ��ȫ�Զ��壬�ڲ����쳣ʱ��ʾ�����档
 * 
 * �̳���RuntimeException, ����Spring��������ĺ������׳�ʱ�ᴥ������ع�.
 * 
 */
public class DiyServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	// �������
	private String errorCode;
	// ������Ϣ 
	private String errorMess;
	// ������ʽ
	private String errorDo;

	public DiyServiceException() {
		super();
	}

	public DiyServiceException(String message) {
		super(message);
	}

	public DiyServiceException(Throwable cause) {
		super(cause);
	}

	public DiyServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DiyServiceException(String errorCode,List<String> paramList) {
		 super();
		 this.errorCode=errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMess() {
		return errorMess;
	}

	public void setErrorMess(String errorMess) {
		this.errorMess = errorMess;
	}

	public String getErrorDo() {
		return errorDo;
	}

	public void setErrorDo(String errorDo) {
		this.errorDo = errorDo;
	}

}
