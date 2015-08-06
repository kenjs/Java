package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.domain.XtJsGnmkDomain;


/**
 * THE ACTION FOR 企业-组织机构 角色权限维护
 * @author 闫伟
 * @date 2013.1.17
*/ 	


public interface JsqxwhDao extends ExtendDao {
	
	/**
	 * 根据系统分类获取 该系统下的目录和功能模块的树形结构 的html片段
	* @Description: 
	* @Note
	* @author 
	* @since 2013-1-14
	* @param xtflDm
	* @param tabPageNum
	* @return
	* @throws Exception
	 */
	public String getTreeStr(String xtflDm, String tabPageNum,UserDomain user) throws Exception;
	/**
	 * 根据角色代码获取功能模块代码
	* @Description: 
	* @Note
	* @author 
	* @since 2013-1-14
	* @param jsDm
	* @return
	* @throws Exception
	 */
	public String getGnmkDmsByJsDm(String jsDm) throws Exception;
	/**
	 * 保存系统-角色-功能模块
	* @Description: 
	* @Note
	* @author 
	* @since 2013-1-14
	* @param jsDm
	* @param gnmkDm
	* @throws Exception
	 */
	public void saveXtJsGnmk(String jsDm,String gnmkDm,String xtml)throws Exception;
	/**
	 * 角色-权限时删除
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-27
	* @param domain
	* @throws Exception
	 */
	public void deleteXtJsGnmk(XtJsGnmkDomain domain)throws Exception;
	/**
	 * 根据主键检查系统-角色-功能模块
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-27
	* @param jsDm
	* @param gnmkDm
	* @return
	* @throws Exception
	 */
	public boolean checkXtJsGnmk(String jsDm,String gnmkDm,String xtml)throws Exception;
	/**
	 * 根据功能模块代码获取角色拼接字符串
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param gnmkDm
	* @return
	* @throws Exception
	 */
	public String getJsInnerHtmlByGnmkDm(BaseBusinessDomain domain) throws Exception;
	/**
	 * 权限-角色时删除
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-27
	* @param domain
	* @throws Exception
	 */
	public void deleteXtJsGnmkByGnmkDm(String gnmkDm,String jsDm)throws Exception;
	public List<QyJsDomain> queryJsByDjxh(BaseBusinessDomain domain)throws Exception;
	
	
}
