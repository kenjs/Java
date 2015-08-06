package com.cy.zyegl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;

/**
 * The DAO for 货运-派车-货物信息-协议登记.
 * 
 * @author HJH
 */
public interface HyPcHwxxXydjDao extends ExtendDao {

	public HyPcHwxxXydjDomain initHwxxXydj(HyPcHwxxXydjDomain domain) throws Exception;
	
	public void deleteHyPcHwxxXydjByPcDjxh(String pcDjxh) throws Exception;
	
}
