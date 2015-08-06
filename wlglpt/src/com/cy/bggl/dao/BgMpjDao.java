package com.cy.bggl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR �칫���� ��Ƭ��
 * 
 * @author ��ΰ
 * @date 2013.1.22
 */

public interface BgMpjDao extends ExtendDao {
	
	/**
	 * ��ѯ�����б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain busDomain,UserDomain user) throws Exception;	
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain,UserDomain user) throws Exception ;
}
