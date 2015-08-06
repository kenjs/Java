package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhHwxhDomain;

/**
 * The DAO for 企业-客户-货物信息.
 * 
 * @author HJH
 */
public interface QyKhHwxhDao extends ExtendDao {
	/**
	 * 检查货物名称是否重复
	 * @return
	 * @throws Exception
	 */
	public int checkHwxhmc(QyKhHwxhDomain qyKhHwxhdomain) throws Exception;

}
