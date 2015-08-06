package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.bo.QySwdnDsh;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.XtGnmkDomain;
import com.cy.common.domain.XtXtmlDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyRyGwDomain;

/**
 * 
* @Descriptoin 登录dao 
* @Note
* @author anq
* @since 2012-12-18 下午05:20:25 
* @version
 */
public interface LoginDao extends ExtendDao {	
	
	/**
	 * 
	* @Description: 登录时检测用户信息 
	* @Note
	* @author 
	* @since 2012-12-19
	* @param domain
	* @throws Exception
	 */
	public void checkUserInfo(UserDomain domain) throws Exception;
	
	public UserDomain getUserInfo(UserDomain domain) throws Exception;
	
	public List<XtXtmlDomain> getTopMenu(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 账号检测成功，获取用户所属菜单栏 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param domain
	* @return
	* @throws Exception
	 */
	public List<XtGnmkDomain> getUserMenu(UserDomain domain, UserDomain userDomain) throws Exception;
	
	public List<XtGnmkDomain> queryLatestOprMenu(UserDomain domain, UserDomain userDomain) throws Exception;
	
	public void saveSwdnDsh(QySwdnDsh qySwdnDsh) throws Exception;
	
	public String queryLatestOprXtml(String czyDjxh) throws Exception;
	/**
	 * 岗位切换
	 * @param czyDjxh
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryGw(String czyDjxh) throws Exception;
}
