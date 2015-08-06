package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.xtgl.domain.QyRydjDomain;


/**
 * The DAO for 用户维护. 
 * @author yu huan
 * Date 2013-1-9
 */
public interface QyRydjDao extends ExtendDao {
	/**
	 * 启用
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startUse(QyRydjDomain domain) throws Exception;

	/**
	 * 停用
	 * 
	 * @return
	 * @throws Exception
	 */
	public void stopUse(QyRydjDomain domain) throws Exception;
	/**
	 * 检验登录帐号是否重复
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkQyzzYhwhMc(QyRydjDomain domain) throws Exception;




}
