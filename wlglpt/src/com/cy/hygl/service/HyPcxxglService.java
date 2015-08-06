package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

public interface HyPcxxglService extends BaseBusinessService {

	public void saveWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void queryWfhxx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void deleteWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void initWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void updateWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void queryPcHwxx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void modify(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * �������ɳ�����Ӧ�Ķ����л������ܽ�������ʼ��˾����
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryPcxxSjsInitVal(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void qingdan(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
}
