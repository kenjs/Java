package com.cy.zyegl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;

/**
 * The DAO for ����-�ɳ�-������Ϣ-Э��Ǽ�.
 * 
 * @author HJH
 */
public interface HyPcHwxxXydjDao extends ExtendDao {

	public HyPcHwxxXydjDomain initHwxxXydj(HyPcHwxxXydjDomain domain) throws Exception;
	
	public void deleteHyPcHwxxXydjByPcDjxh(String pcDjxh) throws Exception;
	
}
