package com.cy.xtgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR 企业-组织机构 角色权限维护
 * @author 闫伟
 * @date 2013.1.17
*/ 	


public interface JsqxwhService extends BaseBusinessService {
	
	/**
	 * 获取树拼接字符串
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param domain
	* @throws Exception
	 */
	public void getTreeStr(BaseBusinessDomain domain,UserDomain user)throws Exception;
	/**
	 * 根据角色代码获取功能模块代码
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param baseBusinessDomain
	* @throws Exception
	 */
	public void getGnmkDmsByJsDm(BaseBusinessDomain domain)throws Exception;
	/**
	 * 根据功能模块代码获取角色拼接字符串
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param baseBusinessDomain
	* @throws Exception
	 */
	public void getJsInnerHtmlByGnmkDm(BaseBusinessDomain baseBusinessDomain)throws Exception;
	
	
	
}
