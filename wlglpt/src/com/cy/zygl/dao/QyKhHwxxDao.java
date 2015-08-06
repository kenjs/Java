package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhHwxxDomain;

/**
 * The DAO for 企业-客户-货物信息.
 * 
 * @author HJH
 */
public interface QyKhHwxxDao extends ExtendDao {
	/**
	 * 检查货物名称是否重复
	 * @return
	 * @throws Exception
	 */
	public int checkHwmc(QyKhHwxxDomain qyKhHwxxdomain) throws Exception;

}
