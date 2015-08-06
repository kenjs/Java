package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 *
 */

public interface HyJsglPsfqrDao extends ExtendDao {
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
	
	public String getPsfQrBz(BaseBusinessDomain baseDomain) throws Exception;
	
	public void viewMx(BaseBusinessDomain baseDomain) throws Exception;
	
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain,UserDomain userDomain) throws Exception;
	
	/**
	 * ��֤���ͷ��Ƿ�����˻�(����ֵ����0�������˻أ�����ֵ����0�������˻�)
	 * @param psfDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkPsfSfTh(String psfDjxh) throws Exception;
	
}
