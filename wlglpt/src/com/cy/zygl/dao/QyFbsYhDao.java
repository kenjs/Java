package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.domain.QyFbsYhDomain;

/**
 * The DAO for 分包商用户管理.
 * 
 * @author HJH
 */
public interface QyFbsYhDao extends ExtendDao {

	/**
	 * 检查货物名称是否重复
	 * @return
	 * @throws Exception
	 */
	public int checkYhzh(QyFbsYhDomain domain, UserDomain user) throws Exception;
	
	/**
	 * 停用
	 * @return
	 * @throws Exception
	 */
	public void saveEnable(QyFbsYhDomain domain)throws Exception;
	/**
	 * 启用
	 * @return
	 * @throws Exception
	 */
	public void saveDisable(QyFbsYhDomain domain)throws Exception;
}
