package com.cy.zygl.service;



import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.domain.QyKhJsjgDomain;

/**
 * The SERVICE for 企业-客户-结算价格.
 * 
 * @author HJH
 */

public interface QyKhJsjgService extends BaseBusinessService {

		public void saveCheck(BaseBusinessDomain doamin) throws Exception;
}