package com.cy.dctms.orderCargoLast.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.OrderCargoLastInfoDomain;

public interface IOrderCargoLastInfoDao {

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
