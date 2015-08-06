package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyFbsDjxxDomain;

/**
 * The DAO for 企业-分包商-登记信息.
 * 
 * @author HJH
 */
public interface QyFbsDjxxDao extends ExtendDao {
	
	/**
	 * 当前分包商的登记序号不存在时，查询同机构下是否已有同样的分包商名称
	 * 当前分包商的登记序号存在时，查询是否包含除本身的分包商名称
	 * @param domain
	 * @throws Exception
	 */
	public int queryFbsmcCount(QyFbsDjxxDomain domain) throws Exception;


}
