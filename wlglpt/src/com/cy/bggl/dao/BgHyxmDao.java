package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgHyxmDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;

/**
 * The DAO for 办公-行业新闻.
 * 
 * @author HJH
 */
public interface BgHyxmDao extends ExtendDao {

	/**
	 * 删除附件
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void deleteFj(BgHyxmDomain domain,UserDomain user)throws Exception;
	/**
	 * 查询附件列表
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<BgHyxmDomain> queryFj(BgHyxmDomain domain,UserDomain user)throws Exception;
	
	/**
	 * 查询附件
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void queryFjByKey(BgHyxmDomain domain,UserDomain user)throws Exception;
	

}
