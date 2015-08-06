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
* @Descriptoin ��¼����ACTION 
* @Note
* @author anq
* @since 2012-12-18 ����11:13:09 
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
	
	//�û���¼domain
	private UserDomain loginUserDomain;

	private QyRyGwDomain  qyRyGwDomain;
	/**
	 * 
	* @Description: ����û��˺�
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String checkUserinfo() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		LoginService service = (LoginService)getService();
		String ip = SysRequestUtil.getIpAddr(request);
		long startTime = System.currentTimeMillis();
		String macAddr = SysRequestUtil.getMacAddressByWlglpt(ip);	//�޸�ȡ�����ķ���
		//macAddr = SysRequestUtil.getMacAddress(ip);
		log.debug("login user, get mac end. time=[" + (System.currentTimeMillis()-startTime)+"]ms");
		
		getLoginUserDomain().setMacAddr(macAddr);
		//��¼ǰ�˺ż��
		service.checkUserinfo(getLoginUserDomain());
		return "checkUserinfo";	
	}
	
	//�������Դ�����
	public String saveSwdnSh() throws Exception{
		try {
			LoginService service = (LoginService)getService();
			service.saveSwdnSh(getLoginUserDomain());
		}catch (Exception e) {
			log.error("������ҵ-��������-�����ʧ�ܣ�", e);
		}
		return "saveSwdnSh";
	}
	
	/**
	 * 
	* @Description: �˺ż��ɹ���������ҳ��
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String loginIn()throws Exception {		
		//ת��Ϊ�Լ���SERVICE
		LoginService service = (LoginService)getService();
		//�˺ż��ɹ�����ȡ�û������Ϣ
		UserDomain resultDomian =  service.getUserInfo(getLoginUserDomain());		
		if(resultDomian != null){//���û���Ϣ��ȡ�쳣ʱ����ת������ҳ�棬����ʾ���µ�¼		
			//���û���Ϣ����SESSION
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
	* @Description: ��λ�л�
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
		//ת��Ϊ�Լ���SERVICE
		LoginService service = (LoginService)getService();
		//�˺ż��ɹ�����ȡ�û������Ϣ
		UserDomain resultDomian =  service.getUserInfo(getLoginUserDomain());
		
		if(resultDomian != null){//���û���Ϣ��ȡ�쳣ʱ����ת������ҳ�棬����ʾ���µ�¼		
			//���û���Ϣ����SESSION
			super.setUserDomain(resultDomian);
		}else{
			ServiceException se=new ServiceException();
			se.setErrorCode("111005");				
			throw se;	
		}		
		return  "gwqh";
	}
	
	/**
	 * @Descriptoin ���µ�¼ϵͳ
	 * @author 
	 * @since 2011-6-16
	 * @return pageView
	 * @throws Exception
	 */
	public String logout()throws Exception {
		//ϵͳ�˳�����session��ϢʧЧ
		session.remove(WebConstants.SES_USER_INFO);		
		//super.getRequest().getSession().getId();
		request.getSession().invalidate();
		return "logout";
	}
	
	/**
	 * 
	* @Description: ����session
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String clearSession()throws Exception {
		//session��ϢʧЧ
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
	* @Description: ��̬������
	* @Note
	* @author 
	* @since 2012-12-18
	* @return
	* @throws Exception
	 */
	public String treeView()throws Exception {
		//ת��Ϊ�Լ���SERVICE
		LoginService service = (LoginService)getService();	
		//��ȡ��̬���ص�����JSON
		String trssJson = service.getUserMenu(getLoginUserDomain(), getUserDomain());
		//���ҳ��ĸ�ʽת��
		/*response.setHeader("Cache-Control", "no-cache");  
		response.setContentType("text/json;charset=UTF-8");   
		PrintWriter writer = response.getWriter();
		writer.flush();		
		writer.write(trssJson);
		writer.close();	*/	
		return "treeView";
	}
	/**
	 * ��λ�л�
	 * @return
	 * @throws Exception
	 */
	public String initGwqh()throws Exception {
		LoginService service = (LoginService)getService();
		service.initGwqh(getQyRyGwDomain(),getUserDomain());
		return "initGwqh";
	}
	/**
	 * ����service
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
