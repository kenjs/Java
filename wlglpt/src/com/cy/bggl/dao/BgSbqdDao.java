package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR �칫ǩ��ǩ�� �ϰ�ǩ��
 * @author ��ΰ
 * @date 2013.1.22
*/ 

public interface  BgSbqdDao extends ExtendDao {
	
	/**
	 * ��ȡӦ���ϰ�ʱ��
	 * @return
	 * @throws Exception
	 */
	public  List<BgGzsjDomain> getSbSj(String bm)throws Exception;
	
	/**
	 * ��ѯ�����б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain baseBusinessDomain,UserDomain user)throws Exception;
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain,UserDomain user) throws Exception ;
	
	/**
	 * ��ȡӦ���ϰ�ʱ��
	 * @return
	 * @throws Exception
	 */
	public List<BgQdqtDomain> getSjQdByCzy(String czy) throws Exception ;
}
