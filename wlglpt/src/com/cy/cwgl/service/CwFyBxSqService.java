package com.cy.cwgl.service;




import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwFybxsqDomain;

/**
 * The SERVICE for ����-��Ʊ�Ǽ�
 * 
 * @author LYY
 */

public interface CwFyBxSqService extends BaseBusinessService {
	
		public void checkXmfl(CwFybxsqDomain domain) throws Exception;
	
		public void onView(CwFybxsqDomain domain,UserDomain user) throws Exception;
}