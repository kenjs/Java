package com.cy.common.exception;

import java.util.List;

/**
 * Service层完全自定义的Exception.
 * 错误信息完全自定义，在捕获异常时显示到界面。
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 */
public class DiyServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	// 错误代码
	private String errorCode;
	// 错误信息 
	private String errorMess;
	// 错误处理方式
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
