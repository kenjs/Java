package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyMbTydHwmxDomain;

/**
 * The DAO for 货运-托运单-货物明细.
 * 
 * @author HJH
 */
public interface HyTydMbHwmxDao extends ExtendDao {

	public List<HyMbTydHwmxDomain> queryHwmxByTydXh(Long ddDjxh, String tempFlag) throws Exception;
	
	public void saveHwxxToFormal(HyMbTydDomain domain) throws Exception;
	
	public void deleteHyMbTydHwxxTempByDdDjxh(Long ddDjxh) throws Exception;
	
	public void deleteHwxxByXhs(String ddDjxh, List<String> hwXhs, String tempFlag) throws Exception;

}
