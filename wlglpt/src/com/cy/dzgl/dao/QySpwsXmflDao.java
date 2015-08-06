package com.cy.dzgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE Dao FOR ��ҵ-��������-��Ŀ����
 * 
 * @author ��ΰ
 * @date 2013.1.29
 */

public interface QySpwsXmflDao extends ExtendDao {
	/**
	 * ��ѯ�����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain busDomain, UserDomain user) throws Exception;

	/**
	 * ��ѯ���������Ƿ��ظ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkQySpwsflMc(BaseBusinessDomain busDomain, UserDomain user) throws Exception;

	/**
	 * ��ѯ�����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadLIst(BaseBusinessDomain busDomain, UserDomain user) throws Exception;

}
