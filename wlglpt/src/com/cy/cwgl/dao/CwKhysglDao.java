package com.cy.cwgl.dao;



import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.cwgl.domain.CwKhysglDomain;
import com.cy.cwgl.domain.CwKhysglMxDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ����-�ͻ�Ԥ������.
 * 
 * @author HJH
 */
public interface CwKhysglDao extends ExtendDao {

	public List<CwKhysglMxDomain> getKhYsMx(BaseBusinessDomain domain) throws Exception;
	
	public void deleteMx(CwKhysglMxDomain domain)throws Exception;
	
	public String getInItJe(String djxh) throws Exception;
	
	public void checkZcFl(CwKhysglDomain domain)throws Exception;
	
	public int checkYsYfDj(CwKhysglMxDomain domain) throws Exception;
}
