package com.cy.dctms.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cy.dctms.entity.WebUserInfo;

public class MerberInterceptor implements HandlerInterceptor {
	
	public static final String SESSION_USER = "user_session";

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        
        //登陆操作跳过
        String[] filterArray = {"/login","/dologin"};
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        for (String string : filterArray) {
			if(url.endsWith(string) || url.equals(contextPath + "/")) {
				return true;
			}
		}
  
		HttpSession session = request.getSession();
		WebUserInfo webUserInfo = (WebUserInfo) session.getAttribute(SESSION_USER);
		if(webUserInfo == null) {
			// 未登录  
            PrintWriter out = response.getWriter();  
            StringBuilder builder = new StringBuilder();  
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
            builder.append("alert(\"页面过期，请重新登录\");");  
            builder.append("window.top.location.href=\""); 
            builder.append(request.getContextPath());
            builder.append("/login\";</script>");  
            out.print(builder.toString());  
            out.close();  
            return false;
		}
		return true;
	}

}
