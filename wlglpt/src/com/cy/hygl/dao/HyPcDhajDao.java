package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcDhajDomain;

/**
 * The DAO for 货运-派车-电话安检.
 * 
 * @author HJH
 */
public interface HyPcDhajDao extends ExtendDao {

	/**
	 * 检索所有的安检电话
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<HyPcDhajDomain> queryAjdhList(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 检索所有的安检照片
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<HyPcDhajDomain> queryAjzpList(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 检索派车信息
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain queryPcxx(BaseBusinessDomain baseDomain) throws Exception;
}
