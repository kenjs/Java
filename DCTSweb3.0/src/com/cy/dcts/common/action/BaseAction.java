package com.cy.dcts.common.action;



import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有基类action
 * @author hayden
 */
@Scope("prototype")
public abstract class BaseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -1559876334712310043L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		response.setContentType("text/html; charset=GBK");
		logger.trace("set response content type =[{}}","text/html; charset=GBK");
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	

	@Override
	public String execute() throws Exception {
		return exec();
	}
	
	/**
	 * action 访问主方法
	 * @author hayden
	 * */
	public abstract String exec();
	
	/**
	 * 获取当前登录用户的session信息
	 */
	public WebUserInfo getSessionUser() {
		if (request.getSession().getAttribute(Constants.SESSION_LOGIN_USER) != null) {
			return (WebUserInfo) request.getSession().getAttribute(
					Constants.SESSION_LOGIN_USER);
		} else {
			return null;
		}
	}
	
	/**
	 * 设置当前登录用户的session信息,GBK编码
	 */
	public void putSessionUser(WebUserInfo webUserInfo) {
		
		request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, webUserInfo);
	}
}
