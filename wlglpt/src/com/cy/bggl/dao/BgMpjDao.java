package com.cy.bggl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公管理 名片夹
 * 
 * @author 闫伟
 * @date 2013.1.22
 */

public interface BgMpjDao extends ExtendDao {
	
	/**
	 * 查询所有列表
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain busDomain,UserDomain user) throws Exception;	
	
	/**
	 * 下载
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain,UserDomain user) throws Exception ;
}
