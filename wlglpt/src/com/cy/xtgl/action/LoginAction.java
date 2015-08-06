package com.cy.xtgl.action;



import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.constants.WebConstants;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.SysRequestUtil;
import com.cy.xtgl.domain.QyRyGwDomain;
import com.cy.xtgl.service.LoginService;

/**
 * 
* @Descriptoin 登录操作ACTION 
* @Note
* @author anq
* @since 2012-12-18 上午11:13:09 
* @version
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "/xtgl/login", 
		results = {
		@Result(name = "checkUserinfo", type = "json"),
		@Result(name = "saveSwdnSh", type = "json"),
		@Result(name = "loginIn", location = "/work/xtgl/main.jsp"),		
		@Result(name = "logout", location = "/work/xtgl/login.jsp"),
		@Result(name = "clearSession", type = "json"),
		@Result(name = "treeView", type = "json"),
		@Result(name = "initCdDh", location = "/work/xtgl/xtcddh.jsp"),
		@Result(name = "initGwqh", location = "/work/xtgl/gwqh.jsp"),
		@Result(name = "gwqh", type = "json"),
		@Result(name = "initTopMenu", type = "json")
		})
public class LoginAction extends ExtendAction {
	
	private Logger log = Logger.getLogger(LoginAction.class);
	
	//用户登录domain
	private UserDomain loginUserDomain;

	private QyRyGwDomain  qyRyGwDomain;
	/**
	 * 
	* @Description: 检测用户账号
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String checkUserinfo() throws Exception {
		//转化为自己的SERVICE
		LoginService service = (LoginService)getService();
		String ip = SysRequestUtil.getIpAddr(request);
		long startTime = System.currentTimeMillis();
		String macAddr = SysRequestUtil.getMacAddressByWlglpt(ip);	//修改取网卡的方法
		//macAddr = SysRequestUtil.getMacAddress(ip);
		log.debug("login user, get mac end. time=[" + (System.currentTimeMillis()-startTime)+"]ms");
		
		getLoginUserDomain().setMacAddr(macAddr);
		//登录前账号检测
		service.checkUserinfo(getLoginUserDomain());
		return "checkUserinfo";	
	}
	
	//上网电脑待审批
	public String saveSwdnSh() throws Exception{
		try {
			LoginService service = (LoginService)getService();
			service.saveSwdnSh(getLoginUserDomain());
		}catch (Exception e) {
			log.error("保存企业-上网电脑-待审核失败：", e);
		}
		return "saveSwdnSh";
	}
	
	/**
	 * 
	* @Description: 账号检测成功，进入主页面
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String loginIn()throws Exception {		
		//转化为自己的SERVICE
		LoginService service = (LoginService)getService();
		//账号检测成功，获取用户相关信息
		UserDomain resultDomian =  service.getUserInfo(getLoginUserDomain());		
		if(resultDomian != null){//当用户信息获取异常时，跳转到错误页面，以提示重新登录		
			//把用户信息放入SESSION
			super.setUserDomain(resultDomian);
		}else{
			ServiceException se=new ServiceException();
			se.setErrorCode("111005");				
			throw se;	
		}		
		return  "loginIn";
	}
	/**
	 * 
	* @Description: 岗位切换
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String gwqh()throws Exception {
		getLoginUserDomain().setCzyDjxh(getUserDomain().getCzyDjxh());
		getLoginUserDomain().setDlzh(getUserDomain().getDlzh());
		getLoginUserDomain().setMacAddr(getUserDomain().getMacAddr());
		//转化为自己的SERVICE
		LoginService service = (LoginService)getService();
		//账号检测成功，获取用户相关信息
		UserDomain resultDomian =  service.getUserInfo(getLoginUserDomain());
		
		if(resultDomian != null){//当用户信息获取异常时，跳转到错误页面，以提示重新登录		
			//把用户信息放入SESSION
			super.setUserDomain(resultDomian);
		}else{
			ServiceException se=new ServiceException();
			se.setErrorCode("111005");				
			throw se;	
		}		
		return  "gwqh";
	}
	
	/**
	 * @Descriptoin 重新登录系统
	 * @author 
	 * @since 2011-6-16
	 * @return pageView
	 * @throws Exception
	 */
	public String logout()throws Exception {
		//系统退出所有session信息失效
		session.remove(WebConstants.SES_USER_INFO);		
		//super.getRequest().getSession().getId();
		request.getSession().invalidate();
		return "logout";
	}
	
	/**
	 * 
	* @Description: 清理session
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String clearSession()throws Exception {
		//session信息失效
		session.remove(WebConstants.SES_USER_INFO);		
		//super.getRequest().getSession().getId();
		request.getSession().invalidate();		
		getLoginUserDomain().setIsLoginSuccess(true);		
		return "clearSession";
	}
	
	public String initTopMenu() throws Exception {
		LoginService service = (LoginService)getService();
		service.initTopMenu(getLoginUserDomain(), getUserDomain());
		
		return "initTopMenu";
	}
	
	public String initCdDh() throws Exception{
		LoginService service = (LoginService)getService();
		service.initCdDh(getLoginUserDomain(), getUserDomain());
		return "initCdDh";
	}
	/**
	 * 
	* @Description: 动态加载树
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String treeView()throws Exception {
		//转化为自己的SERVICE
		LoginService service = (LoginService)getService();	
		//获取动态加载的树的JSON
		String trssJson = service.getUserMenu(getLoginUserDomain(), getUserDomain());
		//输出页面的格式转化
		/*response.setHeader("Cache-Control", "no-cache");  
		response.setContentType("text/json;charset=UTF-8");   
		PrintWriter writer = response.getWriter();
		writer.flush();		
		writer.write(trssJson);
		writer.close();	*/	
		return "treeView";
	}
	/**
	 * 岗位切换
	 * @return
	 * @throws Exception
	 */
	public String initGwqh()throws Exception {
		LoginService service = (LoginService)getService();
		service.initGwqh(getQyRyGwDomain(),getUserDomain());
		return "initGwqh";
	}
	/**
	 * 设置service
	 */
	@Resource(name = "loginServiceImp")	
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public UserDomain getLoginUserDomain() {
		if(loginUserDomain == null){
			loginUserDomain = new UserDomain();
		}
		return loginUserDomain;
	}

	public void setLoginUserDomain(UserDomain loginUserDomain) {
		this.loginUserDomain = loginUserDomain;
	}

	public QyRyGwDomain getQyRyGwDomain() {
		if(qyRyGwDomain == null){
			qyRyGwDomain = new QyRyGwDomain();
		}
		return qyRyGwDomain;
	}

	public void setQyRyGwDomain(QyRyGwDomain qyRyGwDomain) {
		this.qyRyGwDomain = qyRyGwDomain;
	}
	
}
