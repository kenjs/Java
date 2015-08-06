package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.service.BaseService;

public interface BaseBusinessService extends BaseService {

	/**
	 * ҳ���ʼ��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws ServiceException
	 */
	public void init(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ��ϸҳ���ʼ��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws ServiceException
	 */
	public void initMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ҳ���ѯ
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void query(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ��ϸҳ���ѯ
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ҳ�汣��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void save(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ��ϸҳ�汣��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ҳ��ɾ��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void delete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ��ϸҳ��ɾ��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ҳ���ӡ
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void print(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * ��ϸҳ���ӡ
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void printMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ҳ�浼��excel
	* @Description: 
	* @Note
	* @author yzs
	* @since 2011-6-27
	* @param wlfpDomain
	* @param userDomain
	* @throws Exception
	 */
	public void download(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ����
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	
	/**
	 * ͣ��
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	
}
