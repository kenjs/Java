package com.cy.dcts.identityVerify.service;

import java.util.Map;

import com.cy.dcts.common.domain.IdentityVerifyLogDomain;


/**
 * @description 身份验证逻辑操作接口
 * @author 		haoy
 *
 */
public interface IdentityVerifyService {

	/**
	 * 身份验证
	 * @param map
	 * @param userId
	 * @return IdentityVerifyLogDomain
	 * @throws Exception
	 */
	public IdentityVerifyLogDomain identityVerify(Map<String, Object> map, String userId) throws Exception;
}
