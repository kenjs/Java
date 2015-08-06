package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The DAO for 调度成本审核.
 * 
 * @author HJH
 */
public interface HyWlSsDjGlDao extends ExtendDao {

	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * 下载
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception;

	/**
	 * 保存或修改
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;

	public List<HyZyglFydjDomain> getHw(HyZyglFydjDomain domain,UserDomain userDomain)throws Exception;

}
