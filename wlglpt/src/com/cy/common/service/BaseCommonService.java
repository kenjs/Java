package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.framework.service.BaseService;

/**
 * 
 * @author 
 * DESC: ����service
 *
 */
public interface BaseCommonService extends BaseService {
	
	/**
	 * ���ݲ�������ȡ����Ӧ�Ĳ���ֵ
	 */
	public String getCszByCsbm(String csbm, UserDomain user) throws Exception;
	
}
