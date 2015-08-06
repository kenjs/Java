package com.cy.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cy.common.domain.UserDomain;
import com.cy.framework.constants.WebConstants;


/**
 * 访问控制器，检查用户权限能否访问。
 * 
 * @since 2011-6-16
 * @author 
 * @version 1.00 2010-1-12
 */
public class FwkzFilter  implements Filter {
	
	/**
	 * 本过滤器的配置信息。
	 */
	protected FilterConfig filterConfig = null;

	/**
	 * 初始化过滤器。
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * 过滤系统请求，如果检查到过滤的页面没有权限则跳转到提示页面。
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{	
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			Object object = httpRequest.getSession().getAttribute(WebConstants.SES_USER_INFO);	
			UserDomain userInfo = null;				
			if(object != null){
				userInfo = (UserDomain)object;
			}
			String contextPath = WebConstants.GLOBAL_WEB_ROOT;
			String sUrl = String.valueOf(((HttpServletRequest) request).getRequestURI()).toLowerCase();

			// 去除contextPath。
			int iIndexOfContextPath = sUrl.indexOf(contextPath);
			if (iIndexOfContextPath >= 0) {
				sUrl = sUrl.substring(iIndexOfContextPath + contextPath.length());
			}
			if ((userInfo == null ||  StringUtils.isBlank(userInfo.getCzyDjxh()) || userInfo.getDlzh().equals(""))  && !WebConstants.FWKZ_IGNORE_SET.contains(sUrl)) {
				//跳转到指定页面
				skip(response, contextPath);
			    
				return;
			} else {
				chain.doFilter(request, response);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//页面跳到默认页面
	private void skip(ServletResponse response,String contextPath) throws IOException{
		java.io.PrintWriter out = response.getWriter();   
	    out.println("<html>");   
	    out.println("<script language=javascript>");   
	    out.println("window.open ('"+contextPath+"/work/xtgl/login.jsp','_top')");   
	    out.println("</script>");   
	    out.println("</html>");   
	}
	
	/**
	 * 过滤器销毁。
	 */
	public void destroy() {
		this.filterConfig = null;
	}

}
