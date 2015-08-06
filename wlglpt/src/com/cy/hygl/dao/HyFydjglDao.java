package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
/**
 * The DAO for 费用登记管理.
 * 
 * @author hy
 */
public interface HyFydjglDao extends ExtendDao{

public BaseBusinessDomain getHyPcxxByPcDjxh(String pcDjxh) throws Exception;

	/**
	 * 保存货运-费用登记
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveHyFyDj(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	/**
	 * 保存货运-费用登记-明细
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveHyFyDjMx(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	/**
	 * 查询货运费用登记明细
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<HyZyglFydjDomain> queryHyFydjMxList(BaseBusinessDomain baseDomain) throws Exception;
	
	public String getMaxXh(String fyDjxh) throws Exception;
	
	public List<HyZyglFydjDomain> getKhHwxx(HyZyglFydjDomain domain) throws Exception;
	
	public void deleteFyDj(String fyDjxh) throws Exception;
	
	/**
	 * 费用登记后续处理
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void fydjHxcl(HyZyglFydjDomain domain,UserDomain user) throws Exception;
	
	public int checkFydjMcByFydjxh(String djxh) throws Exception;
	
	public HyPcxxglDomain getInsertInfor(String pcDjxh) throws Exception;
	
	public void insertCwYsYf(CwYsyfSrdjDomain domain) throws Exception;
	
	public List<HyZyglFydjDomain> querySkfList(String ddDjxh,String xh,UserDomain user) throws Exception;
}