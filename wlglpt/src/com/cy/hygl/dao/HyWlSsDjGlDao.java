package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The DAO for ���ȳɱ����.
 * 
 * @author HJH
 */
public interface HyWlSsDjGlDao extends ExtendDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception;

	/**
	 * ������޸�
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;

	public List<HyZyglFydjDomain> getHw(HyZyglFydjDomain domain,UserDomain userDomain)throws Exception;

}
