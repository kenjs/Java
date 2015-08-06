package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyTydHwmxDomain;
import com.cy.hygl.domain.HyTydglDomain;

/**
 * The DAO for 货运-托运单-货物明细.
 * 
 * @author HJH
 */
public interface HyTydHwmxDao extends ExtendDao {

	public List<HyTydHwmxDomain> queryHwmxByTydXh(Long ddDjxh, String tempFlag) throws Exception;
	
	public void saveHwxxToFormal(HyTydglDomain domain) throws Exception;
	
	public void deleteHyTydHwxxTempByDdDjxh(Long ddDjxh) throws Exception;
	
	public void deleteHwxxByXhs(String ddDjxh, List<String> hwXhs, String tempFlag) throws Exception;
	
	public Long saveCopyOrMbHwxxToTemp(String ddDjxhCopy, Long ddDjxh, String mbCopyFlag) throws Exception;
	
	public HyTydHwmxDomain queryPrintInfo(HyTydHwmxDomain domain) throws Exception;
	/**
	 * 送货方式变更 update
	 * @param baseDomain
	 * @throws Exception
	 */
	public void updateHwxxWhenShfsbg(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 删除后，回复变更
	 * @param baseDomain
	 * @throws Exception
	 */
	public void updateWhenDeleteShfsbg(BaseBusinessDomain baseDomain) throws Exception;

}
