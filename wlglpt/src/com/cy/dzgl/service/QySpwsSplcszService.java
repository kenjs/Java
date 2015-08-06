package com.cy.dzgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 企业-审批文书-审批流程设置.
 * 
 * @author anq
 */

public interface QySpwsSplcszService extends BaseBusinessService {

	/**
	 * 
	* @Description: 企业-审批文书-审批流程节点设置 页面初始化 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void initSpjcMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description:  企业-审批文书-审批流程节点设置 保存
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void saveSpjc(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 删除 企业-审批文书-审批流程-子表 中审批级次最大的一条记录
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void deleteSplcZb(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 根据主键检索 企业-审批文书  中配置的项目分类标志 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void queryQySpwsXmflbzByKey(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception ;
	
}