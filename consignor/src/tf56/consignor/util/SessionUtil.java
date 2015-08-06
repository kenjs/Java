package tf56.consignor.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tf56.site.domain.SessionBean;
import tf56.sofa.memcache.session.Constants;
import tf56.sofa.memcache.session.CookieUtil;
import tf56.sofa.memcache.session.MemSession;

public class SessionUtil
{
	private final static Logger log = Logger.getLogger(SessionUtil.class);
	/**
	 * @author:wusong
	 * @date:2013-10-09
	 * @function:判断memcached中是否存在session信息
	 */
	public static boolean sessionExists(HttpServletRequest request){
		//取sessionId
		Cookie cookie = CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie);
		if(cookie != null){
			String sid = cookie.getValue();//sessionId	
			log.debug("sessionExists中获得的sid="+sid);
			//sid是否存在
			boolean b = MemSession.sessionExists(sid);
			if(b){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @author:wusong
	 * @date:2013-10-09
	 * @function:验证浏览器cookie中session是否有效， 登录返回true;未登录返回false;
	 */
	public static boolean valideLogin(HttpServletRequest request){
		//cookie中是否存在
		Cookie cookie = CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie);
		if(cookie == null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * @author:wusong
	 * @date:2013-10-09
	 * @function:把信息存在memcached的sid中
	 */
	public static void setAttribute(String sid,String key,Object value){
		//把置放入缓存中
		MemSession session = MemSession.getSession(sid);
		session.setAttribute(key, value);
	}

	
	/**
	 * @author:wusong
	 * @date:2013-10-09
	 * @function:从memcached中取sid的key映射的值
	 */
	public static Object getAttribute(String sid,String key){
		//把置放入缓存中
		MemSession session = MemSession.getSession(sid);
		return session.getAttribute(key);		
	}
	
	
	
	/**
	 * @author:wusong
	 * @date:2013-10-09
	 * @function:通过cookie从memcached中删除session
	 */
	public static void removeAttribute(HttpServletRequest request,String key){
		Cookie cookie = CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie);
		if(cookie != null){
			//取出session的ID
			String sid = cookie.getValue();
			//把置放入缓存中
			MemSession session = MemSession.getSession(sid);
			session.removeAttribute(key);
		}
	}
	
	/**
	 * @author:wusong
	 * @date:2013-10-09
	 * @function:通过cookie使用memcached中的session失效
	 */
	public static void invalidateSession(HttpServletRequest request,HttpServletResponse response){
		Cookie cookie = CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie);
		if(cookie != null){
			//取出session的ID
			String sid = cookie.getValue();
			//把置放入缓存中
			MemSession session = MemSession.getSession(sid);
			session.invalidate();
			//清除cookie
			CookieUtil.setCookie(request, response, Constants.session_key_flag_of_cookie, "", 0);
		}
	}
	
	/**
	 * @author:wjj
	 * @date:2013-12-10
	 * @function:取memcached中的session
	 */
	public static SessionBean getSession(HttpServletRequest request){
		SessionBean sessionBean = null;
		
		Cookie cookie = CookieUtil.getCookie(request, Constants.session_key_flag_of_cookie);
		if(cookie != null){
			//取出session的ID
			String sid = cookie.getValue();
			//得到session中的Key
			String jobcard = sid.split("_")[2];
			//通过key查询value
			sessionBean = (SessionBean)SessionUtil.getAttribute(sid, jobcard);
		}
		return sessionBean;
	}
}


