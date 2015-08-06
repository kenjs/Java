package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyXxzjDjxxDomain;

/**
 * The DAO for 企业-信息中介-登记信息
 * 
 * @author yw
 * @since 2013-2-20 上午8:31:00
 * @version
 */
public interface QyXxzjDjxxDao extends ExtendDao  {
	
	//保存时进行验证是否重复
	public int checkQyXxZjMc(QyXxzjDjxxDomain domain) throws Exception;
}
