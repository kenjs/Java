package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAO for 企业组织机构部门维护
 * 
 * @Author Yu huan
 * @Date 2013-01-8
 */

public interface BmwhDao extends ExtendDao {
	/**
	 * 启用
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startUse(QyZzjgDomain domain) throws Exception;

	/**
	 * 停用
	 * 
	 * @return
	 * @throws Exception
	 */
	public void stopUse(QyZzjgDomain domain) throws Exception;

	/**
	 * 进行部门名称验证是否重名
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkQyzzBmwhMc(QyZzjgDomain domain) throws Exception;

	/**
	 * 获取部门的最大级别代码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMaxJbdm(QyZzjgDomain domain) throws Exception;
}
