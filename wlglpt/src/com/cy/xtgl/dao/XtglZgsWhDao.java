package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAO for ��ҵ-��֯����.
 * 
 * @Descriptoin �ܹ�˾ά��dao 
* @Note
* @author ylp
* @since 2013-1-9 ����05:20:25
 */
public interface XtglZgsWhDao extends ExtendDao {
	
	/**
	 * �����û��ṩ����ҵ��֯��Ż�ȡZgsWhdomain
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public QyZzjgDomain getDomainByQyzcxh(UserDomain userdomain)throws Exception;
	
	/**
	 * ����޸ĺ��domain�����Ƿ����ظ���
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public boolean checkMcre(QyZzjgDomain domain) throws Exception;
	
}
