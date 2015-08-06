package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 企业-岗位.
 * 
 * @author HaoY
 */
public interface QyGwDao extends ExtendDao {
	
	/**
	 * 检索系统岗位
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryXtGw(BaseBusinessDomain baseDomain)  throws Exception;
	
	/**
	 * 保存岗位
	 * @param baseDomain
	 * @throws Exception
	 */
	public void saveXtGw(BaseBusinessDomain baseDomain,UserDomain userDomain) throws Exception;
	
	/**
	 * 检查岗位是否重复
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public int checkGw(BaseBusinessDomain baseDomain) throws Exception;
	
	public String getBmMc(String jgbm) throws Exception;
	
	public String getYxbz(BaseBusinessDomain baseDomain) throws Exception;
	
	public void updateXtgw(BaseBusinessDomain baseDomain) throws Exception;
	
	public void saveEnable(BaseBusinessDomain baseDomain) throws Exception;
	
	public void saveDisable(BaseBusinessDomain baseDomain) throws Exception;
}
