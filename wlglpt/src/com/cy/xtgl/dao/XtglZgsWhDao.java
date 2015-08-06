package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAO for 企业-组织机构.
 * 
 * @Descriptoin 总公司维护dao 
* @Note
* @author ylp
* @since 2013-1-9 下午05:20:25
 */
public interface XtglZgsWhDao extends ExtendDao {
	
	/**
	 * 根据用户提供的企业组织序号获取ZgsWhdomain
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public QyZzjgDomain getDomainByQyzcxh(UserDomain userdomain)throws Exception;
	
	/**
	 * 检查修改后的domain名称是否有重复的
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public boolean checkMcre(QyZzjgDomain domain) throws Exception;
	
}
