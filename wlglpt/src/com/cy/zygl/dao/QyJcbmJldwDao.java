package com.cy.zygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.domain.QyJcbmJldwDomain;

/**
 * The DAO for 企业-基础编码-计量单位.
 * 
 * @author HaoY
 */
public interface QyJcbmJldwDao extends ExtendDao {

	/**
	 * 检索所有计量单位
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<QyJcbmJldwDomain> queryAllJldw(BaseBusinessDomain domain) throws Exception;

}
