package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhZhdzDomain;

/**
 * The DAO for 企业-客户-装货地址.
 * 
 * @author ylp
 * @since 2013-1-15 上午8:31:00
 * @version
 */
public interface QyKhZhdzDao extends ExtendDao {

	// 保存时,校验详细地址是否重复
	public int checkXxdzRe(QyKhZhdzDomain domain) throws Exception;

}
