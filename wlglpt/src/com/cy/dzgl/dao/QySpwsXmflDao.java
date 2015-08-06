package com.cy.dzgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE Dao FOR 企业-审批文书-项目分类
 * 
 * @author 闫伟
 * @date 2013.1.29
 */

public interface QySpwsXmflDao extends ExtendDao {
	/**
	 * 查询所有列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain busDomain, UserDomain user) throws Exception;

	/**
	 * 查询分类名称是否重复
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkQySpwsflMc(BaseBusinessDomain busDomain, UserDomain user) throws Exception;

	/**
	 * 查询所有列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadLIst(BaseBusinessDomain busDomain, UserDomain user) throws Exception;

}
