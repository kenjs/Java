package com.cy.bggl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR �칫���� ������
 * 
 * @author ��ΰ
 * @date 2013.1.24
 */

public interface BgGzrDao extends ExtendDao {
	
	/**
	 * ����jgbm�������޸Ĺ�����
	 * @return
	 * @throws Exception
	 */
	public void updateGzrByJgbm(BaseBusinessDomain domain) throws Exception;
}
