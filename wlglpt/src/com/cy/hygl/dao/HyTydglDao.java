package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyTydglDomain;

/**
 * The DAO for ����-���˵�����.
 * 
 * @author HJH
 */
public interface HyTydglDao extends ExtendDao {

	public List<BaseBusinessDomain> queryCopy(BaseBusinessDomain domain,UserDomain user) throws Exception;
	
	public void callPHyglDdglTydglHxcl(Long ddDjxh, String xh,String xzQsbz) throws Exception;
	
	/**
	 * ���˵�ɾ��
	 * @param ddDjxh
	 * @param czyDjxh
	 * @throws Exception
	 */
	public void callPHyglDdglTydglDelete(Long ddDjxh, String czyDjxh,String xzQsbz) throws Exception;
	
	public Integer queryYpchwNumByDdDjxh(Long ddDjxh) throws Exception;
	
	/**
	 * ��list����У�鶩�����
	 * @param ddbh
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public List<HyTydglDomain> checkDdbh(String ddbh,String ssJgbm) throws Exception;
	
	public Integer selectXh(BaseBusinessDomain baseBusinessDomain) throws Exception;
}
