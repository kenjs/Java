package com.cy.zyegl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;
import com.cy.zyegl.domain.HyPcXydjDomain;

/**
 * The DAO for ����-�ɳ�-Э��Ǽ�.
 * 
 * @author HJH
 */
public interface HyPcXydjDao extends ExtendDao {

	public HyPcXydjDomain initXydj(String pcDjxh) throws Exception;
	
	public void callProXydjHxcl(String pcDjxh, UserDomain userDomain) throws Exception;
	
	public void updatePcXydjSlbgbz(HyPcHwxxXydjDomain hyPcHwxxXydjDomain) throws Exception;
	
	public List<BaseBusinessDomain> queryHyPcXybdList(String pcDjxh)  throws Exception;
	/**
	 * �����ɳ��Ǽ���� �Ҷ�ӦЭ��Ǽ�
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkXydj(String pcDjxh)throws Exception;
}
