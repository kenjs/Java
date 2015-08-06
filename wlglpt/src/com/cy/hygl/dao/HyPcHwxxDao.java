package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;

/**
 * The DAO for 货运-派车-货物信息.
 * 
 * @author HJH
 */
public interface HyPcHwxxDao extends ExtendDao {

	public HyPcHwxxDomain getPcHwxxDomainByKey(String pcDjxh, String wfhDjxh) throws Exception;
	
	public BaseBusinessDomain getPcHwDomainFromLsbByKey(BaseBusinessDomain baseDomain) throws Exception;
	
	public void updatePcHwxxTmp(HyPcHwxxDomain domain) throws Exception;
	
	public void updatePcHwxx(HyPcHwxxDomain domain) throws Exception;
	
	public void savePcHwxxToFormalTab(HyPcxxglDomain domain) throws Exception;
	/**
	 * 送货方式变更 update
	 * @param baseDomain
	 * @throws Exception
	 */
	public void updatePcHwxxWhenShfsbg(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 送货方式变更 删除
	 * @param baseDomain
	 * @throws Exception
	 */
	public void updateWhenDeleteShfsbg(BaseBusinessDomain baseDomain) throws Exception;

}
