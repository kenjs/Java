package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.xtgl.domain.YhjswhDomain;

/**
 * 用户角色维护
 * 
 * @author HaoY
 * @since
 */
public interface YhjswhDao extends ExtendDao {
	/**
	 * 检索用户名称(分页相关)
	 */
	public List<YhjswhDomain> queryYhMcList(BaseBusinessDomain domain,PageDomain page) throws Exception;
	
	/**
	 * 检索用户名称(下载相关)
	 */
	public List<YhjswhDomain> queryYhMcList(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * 检索角色名称
	 */
	public List<YhjswhDomain> queryJsMcList(String qyZcxh) throws Exception;
	
	/**
	 * 检索用户已选择角色
	 */
	public List<YhjswhDomain> queryChoosedJs(BaseBusinessDomain domain) throws Exception;
}
