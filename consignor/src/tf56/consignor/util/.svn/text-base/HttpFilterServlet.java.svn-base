package tf56.consignor.util;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tf56.site.domain.SessionBean;
import tf56.sofa.util.ReadPropertiesFile;

/**
 * 
 * @author: wusong
 * @function: 从缓存中读取session信息，判断用户请求是否合法
 * @date:2013-11-26
 */
public class HttpFilterServlet extends HttpServlet implements Filter {   
	
	 private FilterConfig filterConfig;   
	 private String[] freePages;   
	 private String toPage = null;   
	  
	 private static  Logger log=Logger.getLogger(HttpFilterServlet.class); 
	 /**  
	  * @author:wusong
	  * @function:初始化filter（这里重写父类的方法）  
	  * @param filterConfig FilterConfig filter配置对象  
	  * @throws ServletException  
	  */  
	 public void init(FilterConfig filterConfig) throws ServletException {   
		 int i = 0;   
		 String pages = null;   
		 StringTokenizer strTokenizer = null;   
	  
		 this.filterConfig = filterConfig;   
		 //以下从配置文件获取配置信息   
		 this.toPage = filterConfig.getInitParameter("toPage");   
		 pages = filterConfig.getInitParameter("freePages");   
		 if(toPage==null||pages==null||toPage.trim().length()==0||pages.trim().length() == 0) {
			 log.debug("初始化：web.xml中没有配置初始化参数\"toPage\"或\"freePages\"");
		 }
		 strTokenizer = new StringTokenizer(pages, ";");   
		 this.freePages = new String[strTokenizer.countTokens()];   
		 while(strTokenizer.hasMoreTokens()) {   
			 freePages[i++] = strTokenizer.nextToken();   
		 }   
		 if(!isFreePage(toPage)) {    
			 log.debug("初始化：web.xml中filter初始化参数\"toPage\"的值必须是\"freePage\"中的某个页面.");
		 }
	  }   
	 /**  
	  * @author wusong
	  * @function:判断一个请求URI是否是不过滤的页面  
	  * @param requestURI String 请求URI  
	  * @return boolean 返回true为不过滤页面  
	  */  
	 private boolean isFreePage(String requestURI) {   
		 boolean isFree = false;    
		 for(int i = 0; i < freePages.length; i++) {   
			 if(requestURI.endsWith(freePages[i])) {   
				 return true;   
			 }   
		 }   
		 return isFree;   
	 }   
	  
	 /**  
	  * @author:wusong
	  * @function:判断请求是否为有效Session  
	  * @param request ServletRequest 请求对象  
	  * @return boolean 返回true为有效Session  
	  */  
	 private boolean isValidSession(ServletRequest sRequest) {
		 String sid = null;
		 HttpServletRequest request = (HttpServletRequest)sRequest; 
		
		 /*
		 //得到cookie中sidh
		 if(CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie)==null){
			 return false;
		 }else{
			 sid = CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie).getValue();
		 }
		 log.debug("isValidSession取出cookie中的sid sss="+sid);
		 //得到session中的Key
		 String partyid = sid.split("_")[2];
		 //通过key查询value
		 SessionBean sessionBean = (SessionBean)SessionUtil.getAttribute(sid, partyid);
		 */
		 SessionBean sessionBean = SessionUtil.getSession(request);
		 if (sessionBean == null || sessionBean.getPartyname() == null) {
			 log.debug("用户session不存在");
			 return false;
		 }else{
			return true;
		 }  
	 }    
	 /** 
	  * @author:wusong 
	  * @function:过滤动作  
	  * @param request ServletRequest 请求对象  
	  * @param response ServletResponse 响应对象  
	  * @param filterChain FilterChain 过滤器链对象  
	  */  
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {   
		 String requestURI = null;   
		 HttpServletRequest httpRequest = (HttpServletRequest)request;   
		 HttpServletResponse httpResponse = (HttpServletResponse)response;   
		 
		 requestURI = httpRequest.getRequestURI();   
		 if(!isFreePage(requestURI)) { //如果是保护页面   
			 if(!isValidSession(request)) { //如果Session无效
				 log.debug("请求URI="+requestURI+" session非法");
				 String mainSiteUrl = ReadPropertiesFile.getString("/config/application", "sessionDemoip");
				 String toPageURL = null;   
				 try {   
//					 toPageURL = httpRequest.getContextPath() + toPage;
					 toPageURL = mainSiteUrl + toPage;
					 httpResponse.encodeRedirectURL(toPageURL);   
					 httpResponse.sendRedirect(toPageURL); //转发响应   
				 } catch(IOException ex) {  
					 ex.printStackTrace();
				 }   
			 }   
		 } 
		 if(!httpResponse.isCommitted()) { //如果响应未提交,交给过滤器链   
			 try {   
				 filterChain.doFilter(request, response);   
			 } catch(ServletException sx) {   
				 filterConfig.getServletContext().log(sx.getMessage());   
			 } catch(IOException iox) {   
				 filterConfig.getServletContext().log(iox.getMessage());   
			 }   
		 }   
	 }   
	 //父类的方法   
	 public void destroy() {   
	 }   
	    
	 public FilterConfig getFilterConfig() {   
		 return this.filterConfig;   
	 }   
	  
	 public void setFilterConfig(FilterConfig filterConfig) {   
		 this.filterConfig = filterConfig;   
	 }   
}   

