package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.framework.service.BaseService;

/**
 * 
 * @author 
 * DESC: 公用service
 *
 */
public interface BaseCommonService extends BaseService {
	
	/**
	 * 根据参数编码取得相应的参数值
	 */
	public String getCszByCsbm(String csbm, UserDomain user) throws Exception;
	
}
