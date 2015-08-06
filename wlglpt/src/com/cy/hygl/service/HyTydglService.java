package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-���˵�����.
 * 
 * @author HJH
 */

public interface HyTydglService extends BaseBusinessService {

	/**
	 * ˢ�»����б�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void refreshHwList(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * ��ʼ������ά��ҳ��
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * �����е������������洢���̣�����δά���ķ������ջ���ַ����Ϣ������δ������Ϣ�Ϳ�桢����ȱ�
	 * @param ddDjxh
	 * @param xh
	 * @throws Exception
	 */
	public void callPHyglDdglTydglHxcl(Long ddDjxh, String xh) throws Exception;
	
	/**
	 * ���������Ϣ
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * ɾ��������Ϣ
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * ��ʼ���е�����ҳ��
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initCopyMx(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	/**
	 * �����е�����ҳ��
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryCopy(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	/**
	 * ����ѡ����������Ƶ��е���ʼ����ǰ�е�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initTydFromCopy(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	/**
	 * ����ѡ����������Ƶ�ģ���ʼ����ǰ�е�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initTydFromTemplate(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	public void queryYpchwNumByDdDjxh(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * У�鶩������Ƿ��ظ�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void checkDdbh(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)throws Exception;
	
	public void printView(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}