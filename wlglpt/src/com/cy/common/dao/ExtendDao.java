package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

public interface ExtendDao extends BaseDao {

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
	
	/**
	 * 根据主键获取domain
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)throws Exception;

	/**
	 * 根据主键在数据库中删除domain
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;

	/**
	 * 根据domain对象中的属性（主要是主键）从数据库中获取对象，对象信息置在domain中
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void initDomainMx(BaseBusinessDomain domain) throws Exception;
	
}
