package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HwztDomain;

/**
 * The DAO for 货物自提.
 * 
 * @author LYY
 */
public interface HwztDao extends BaseDao {

	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public List<HwztDomain> queryList(HwztDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 下载
	 * @return
	 * @throws Exception
	 */
	public List<HwztDomain> downloadList(HwztDomain domain) throws Exception;

	/**
	 * 保存或修改
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;

	/**
	 * 登记
	 * @param domain
	 * @param userDomain
	 */
	public void register(BaseBusinessDomain domain,UserDomain user) throws Exception;
	
	/**
	 * 撤销
	 * @param domain
	 * @param userDomain
	 */
	public void delete(BaseBusinessDomain domain,UserDomain user) throws Exception;

}
