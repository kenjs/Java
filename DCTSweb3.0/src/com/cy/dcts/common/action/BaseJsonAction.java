package com.cy.dcts.common.action;

import java.io.IOException;


import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.constants.Constants;

/**
 * 所有JSON基类action
 * @author hayden 
 */
public abstract class BaseJsonAction extends BaseAction {

	private static final long serialVersionUID = -1559876334712310043L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	protected abstract void execMethod() throws Exception;

	@Override
	public String exec() {
		try {
			execMethod();
		} catch (Exception e) {
			logger.warn("error:", e);
			try {
				this.sendBusinessErrorResponseToJson(null);
			} catch (IOException e1) {
			}
		}
		return null;
	}

	/**
	 * 响应 JSON格式数据——ParameterError
	 * */
	public String sendParameterErrorResponseToJson(String message) throws IOException {
		if(StringUtils.isEmpty(message)){
			message = "参数错误，操作失败！";
		}
		return this.sendResponseToJson(Constants.JSON_EXCEPTION_CODE_PARAMETER_COMMON, message, null);
	}
	
	/**
	 * 响应 JSON格式数据——UserEmptyError
	 * */
	public String sendUserEmptyErrorResponseToJson(String message) throws IOException {
		if(StringUtils.isEmpty(message)){
			message = "用户不存在，操作失败！";
		}
		return this.sendResponseToJson(Constants.JSON_EXCEPTION_CODE_USER_EMPTY_COMMON, message, null);
	}
	
	/**
	 * 响应 JSON格式数据——BusinessError 
	 * */
	public String sendBusinessErrorResponseToJson(String message) throws IOException {
		if(StringUtils.isEmpty(message)){
			message = "出现错误，操作失败！";
		}
		return this.sendResponseToJson(Constants.JSON_EXCEPTION_CODE_BUSINESS_COMMON, message, null);
	}
	
	/**
	 * 响应 JSON格式数据 key-value
	 * */
	public String sendResponseToJson(String result, String message) throws IOException {
		return this.sendResponseToJson(result, message, null);
	}
	
	
	/**
	 * 响应 JSON格式数据 key-value
	 * */
	public String sendResponseToJson(String result, String message, Object content) throws IOException {
		JSONObject json = new JSONObject();
		if (result != null) {
			json.accumulate("result", result);
		}
		if (message != null) {
			json.accumulate("errorMessage", message);
		}
		if (content != null) {
			json.accumulate("content", content);
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json.toString());
		out.flush();
		out.close();
		return json.toString();
	}
	
	/**
	 * 响应 JSON格式数据 无KEY
	 * */
	public String sendResponseToJson(Object content) throws IOException {
		JSONArray datasJson = JSONArray.fromObject(content);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(datasJson.toString());
		out.flush();
		out.close();
		return datasJson.toString();
	}
	
}
