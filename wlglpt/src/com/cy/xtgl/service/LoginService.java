package com.cy.xtgl.service;


import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.xtgl.domain.QyRyGwDomain;

/**
 * 
* @Descriptoin 登录业务 
* @Note
* @author anq
* @since 2012-12-18 下午05:13:10 
* @version
 */
public interface LoginService  extends BaseBusinessService {
	
	/**
	 * 
	* @Description: 检测用户账号 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param userDomain
	* @throws Exception
	 */
	public void checkUserinfo(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 增加一条电脑审核记录 
	* @Note
	* @author 
	* @since 2012-12-20
	* @param userDomain
	* @throws Exception
	 */
	public void saveSwdnSh(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 账号检测成功，进入主页面,获取用户相关信息 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param userDomain
	* @return
	* @throws Exception
	 */
	public UserDomain getUserInfo(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 账号检测成功，进入主页面,获取用户所属菜单栏 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param userDomain
	* @return
	* @throws Exception
	 */
	public String getUserMenu(UserDomain domain, UserDomain userDomain)throws Exception;
	
	public void initTopMenu(UserDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * 岗位切换
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initGwqh(QyRyGwDomain domain, UserDomain userDomain) throws Exception;
	public void initCdDh(UserDomain domain, UserDomain userDomain) throws Exception;
	
	
}
