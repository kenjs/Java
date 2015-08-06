package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.xtgl.domain.QyJsDomain;

/**
 * The DAO for 企业角色.
 * 
 * @author ylp
 * @since 2013-1-10 上午9:00:00
 * @version
 * 
 */
public interface QyJsDao extends ExtendDao {

	/**
	 * 检查角色名称是否重复
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkJsMcRe(QyJsDomain qyjsdomain) throws Exception;

	/**
	 * 删除角色前，判断该角色是否已在使用
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkJsUsed(QyJsDomain qyjsdomain) throws Exception;

	/**
	 * 停用
	 * 
	 * @return
	 * @throws Exception
	 */
	public void stopJs(QyJsDomain domain) throws Exception;

	/**
	 * 启用
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startJs(QyJsDomain domain) throws Exception;

}
