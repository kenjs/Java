package com.cy.dctms.orderCargoLast.service;

import java.util.List;

import com.cy.dctms.common.domain.OrderCargoLastInfoDomain;

public interface IOrderCargoLastInfoService {
	
	/**
	 * ��ѯ��Դ��ʷ״̬��Ϣ�б�
	 * @author:wjl
	 */
	public void queryOrderCargoLastInfoList(OrderCargoLastInfoDomain orderCargoLastInfoDomain);
	
	/**
	 * ������Դ��ʷ״̬��Ϣ�б�
	 * @author:wjl
	 */
	public void exportOrderCargoLastInfo(OrderCargoLastInfoDomain orderCargoLastInfoDomain);

	
}
	
