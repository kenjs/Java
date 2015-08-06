package com.cy.dctms.orderCargo.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.OrderCargoInfoDomain;

public interface IOrderCargoInfoDao {

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
	public OrderCargoInfoDomain queryOrderCargoInfoById(String id);	

	/**
	 * ���������Ϣ��Ϣ
	 * @author:wjl
	 */
	public void saveOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain,String userId);

	/**
	 * ɾ��������Ϣ��Ϣ
	 * @author:wjl
	 */
	public void deleteOrderCargoInfo(String id,String userId);
}
