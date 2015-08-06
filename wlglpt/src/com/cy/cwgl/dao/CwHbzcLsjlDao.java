package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for 财务-货币资产流水记录
 * 
 * @author HCM
 */
public interface CwHbzcLsjlDao  extends ExtendDao {
	public List<BaseBusinessDomain> queryListForZjrb(BaseBusinessDomain baseDomain)  throws Exception;
}
