package com.cy.zygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhDjxxDomain;

/**
 * The DAO for 企业-客户-登记信息.
 * 
 * @author HJH
 */
public interface QyKhDjxxDao extends ExtendDao {
	
	/**
	 * 当前客户的登记序号不存在时，查询同机构下是否已有同样的客户名称
	 * 当前客户的登记序号存在时，查询是否包含除本身的客户名称
	 * @param domain
	 * @throws Exception
	 */
	public int queryKhmcCount(QyKhDjxxDomain domain) throws Exception;
	
	public List<QyKhDjxxDomain> queryXzqhList(QyKhDjxxDomain domain) throws Exception;
	


}
