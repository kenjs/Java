package com.cy.dctms.orderCargo.service;

import java.util.List;

import com.cy.dctms.common.domain.OrderCargoInfoDomain;

public interface IOrderCargoInfoService {
	
	/**
	 * ��ѯ������Ϣ��Ϣ�б�
	 * @author:wjl
	 */
	public void queryOrderCargoInfoList(OrderCargoInfoDomain orderCargoInfoDomain);
	
	/**
	 * ����������Ϣ��Ϣ�б�
	 * @author:wjl
	 */
	public void exportOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain);

	/**
	 * �������Ϣ��Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public OrderCargoInfoDomain queryOrderCargoInfoMxById(String id);

	/**
	 * ���������Ϣ��Ϣ
	 * @author:wjl
	 */
	public void saveOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain ,String userId);

	/**
	 * ɾ��������Ϣ��Ϣ
	 * @author:wjl
	 */
	public void deleteOrderCargoInfo(String id,String userId);
	
	
}
	
