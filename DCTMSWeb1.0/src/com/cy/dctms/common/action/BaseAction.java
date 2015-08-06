package com.cy.dctms.common.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.constants.Constants;
import com.cy.dctms.common.domain.ManagerInfoDomain;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有基类action
 * @author hayden
 */
public abstract class BaseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -1559876334712310043L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		response.setContentType("text/html; charset=GBK");
		logger.trace("set response content type =[{}}","text/html; charset=GBK");
	}

	@Override
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
	public ManagerInfoDomain getSessionUser() {
		if (request.getSession().getAttribute(Constants.SESSION_LOGIN_USER) != null) {
			return (ManagerInfoDomain) request.getSession().getAttribute(
					Constants.SESSION_LOGIN_USER);
		} else {
			return null;
		}
	}
	
	/**
	 * 设置当前登录用户的session信息,GBK编码
	 */
	public void putSessionUser(ManagerInfoDomain managerInfoDomain) {
		if (managerInfoDomain==null) {
			request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, null);
			return;
		}
		request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, managerInfoDomain);
		request.getSession().setAttribute("name", managerInfoDomain.getName());
		request.getSession().setAttribute("id", managerInfoDomain.getId());
	}
	
	/**
	 * 设置当前登录用户喜好信息,GBK编码
	 */
	public void putUserPerferenceInfo(String key, String value) {
		if(getSessionUser() == null){
			return;
		}
	}
	
	
	
}
