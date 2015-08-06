package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.service.BaseService;

public interface WsspCommonService extends BaseService {
	/**
	 * �����˻�
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveBack(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ���鷢��
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void send(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��ѯ����������תlist
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWssplzList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * �鿴�������
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void querySpyj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ȡ��������ģʽdm
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWsspms(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��ʼ��������Ϣ
	 * @param userDomain
	 * @param domain
	 * @throws Exception
	 */
	public void initSendXX(UserDomain userDomain, WsspCommonDomain domain) throws Exception;
	/**
	 * �������ͣ�����ʱ���ж��Ƿ���������������������󣬷�����
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void plSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��ʼ���״η�����Ϣ
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void initScSendXx(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * �״η���
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void scSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��ȡ���� ���������������
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception;
	
}
