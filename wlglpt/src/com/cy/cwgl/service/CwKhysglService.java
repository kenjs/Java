package com.cy.cwgl.service;



import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwKhysglDomain;

/**
 * The SERVICE for ����-�ͻ�Ԥ������.
 * 
 * @author HJH
 */

public interface CwKhysglService extends BaseBusinessService {
	public void onLook(CwKhysglDomain domain) throws Exception ;
	public void checkZcFl(CwKhysglDomain domain,UserDomain user) throws Exception ;
	
}