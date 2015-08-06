package com.cy.cwgl.service;



import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-货币资产初始化.
 * 
 * @author HJH
 */

public interface CwHbzcCshService extends BaseBusinessService {
	public void checkYhMc(BaseBusinessDomain baseBusinessDomain) throws Exception;
}