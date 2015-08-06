package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhShdzDomain;

/**
 * The DAO for 企业-客户-收货地址.
 * 
 * @author ylp
 * @since 2013-1-16 上午10:31:00
 * @version
 */
public interface QyKhShdzDao extends ExtendDao {

	// 保存时,校验详细地址是否重复
	public int checkShXxdzRe(QyKhShdzDomain domain) throws Exception;
}
