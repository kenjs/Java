package com.cy.bggl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR �칫���� ������
 * 
 * @author ��ΰ
 * @date 2013.1.24
 */
public interface BgGzrService extends BaseBusinessService {
	
	/**
	 * ����jgbm�������޸Ĺ�����
	 * @return
	 * @throws Exception
	 */
	public void updateGzrByJgbm(BaseBusinessDomain domain) throws Exception;
}
