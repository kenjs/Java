package com.cy.common.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.cy.dcts.common.constants.Constants;


/**
 * 所有AJAX基类action
 * @author hayden
 */
public abstract class BaseAjaxAction extends BaseAction {

	private static final long serialVersionUID = -1559876334712310043L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	protected abstract String execMethod() throws Exception;

//	@Override
//	public String exec() {
//		try {
//			return execMethod();
//		} catch (Exception e) {
//			logger.warn("error:", e);
//			try {
//				this.sendBusinessErrorResponse(null);
//			} catch (Exception e1) {
//			}
//			return null;
//		}
//	}
	
	/**
	 * Ajax请求返回业务处理错误
	 * */
//	public String sendBusinessErrorResponse(String errorMessage) throws IOException, Exception{
//		if(StringUtils.isEmpty(errorMessage)){
//			errorMessage = "操作失败，业务处理错误！";
//		}
//		return this.sendErrorResponse(Constants.EXCEPTION_CODE_BUSINESS_COMMON, errorMessage);	
//	}
	
	/**
	 * Ajax请求返回数据库错误
	 * */
//	public String sendDataBaseErrorResponse(String errorMessage) throws IOException, Exception{
//		if(StringUtils.isEmpty(errorMessage)){
//			errorMessage = "操作失败，数据库错误！";
//		}
//		return this.sendErrorResponse(Constants.EXCEPTION_CODE_DATABASE_COMMON, errorMessage);	
//	}
	
	/**
	 * Ajax请求返回SESSION不能为空错误
	 * */
//	public String sendSessionEmptyErrorResponse(String errorMessage) throws IOException, Exception{
//		if(StringUtils.isEmpty(errorMessage)){
//			errorMessage = "只有登录用户才能进行操作，请先登录！";
//		}
//		return this.sendErrorResponse(Constants.EXCEPTION_CODE_SESSION_EMPTY_COMMON, errorMessage);	
//	}
	
	/**
	 * Ajax请求返回输入参数错误
	 * */
//	public String sendParameterErrorResponse(String errorMessage) throws IOException, Exception{
//		if(StringUtils.isEmpty(errorMessage)){
//			errorMessage = "操作失败，输入参数错误!";
//		}
//		return this.sendErrorResponse(Constants.EXCEPTION_CODE_PARAMETER_COMMON, errorMessage);	
//	}
	
	/**
	 * Ajax请求返回自定义错误
	 * */
	public String sendErrorResponse(String errorCode, String errorMessage) throws IOException, Exception{
		if (isAjaxRequest()) {
			response.setContentType("text/plain;charset=GBK");
			response.setStatus(500);
			PrintWriter out = response.getWriter();
			out.write("错误代码：" + errorCode + "<br> 错误信息：" + errorMessage);
			out.flush();
			out.close();
			return "错误代码：" + errorCode + "<br> 错误信息：" + errorMessage;
		}
		logger.info("send response fail! no ajax request.");
		return "no ajax request.";
	}
	
	/**
	 * @Description:判断是否是ajax的请求
	 */
	private boolean isAjaxRequest() {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	
}
