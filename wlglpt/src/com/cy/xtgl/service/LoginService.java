package com.cy.xtgl.service;


import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.xtgl.domain.QyRyGwDomain;

/**
 * 
* @Descriptoin ��¼ҵ�� 
* @Note
* @author anq
* @since 2012-12-18 ����05:13:10 
* @version
 */
public interface LoginService  extends BaseBusinessService {
	
	/**
	 * 
	* @Description: ����û��˺� 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param userDomain
	* @throws Exception
	 */
	public void checkUserinfo(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: ����һ��������˼�¼ 
	* @Note
	* @author 
	* @since 2012-12-20
	* @param userDomain
	* @throws Exception
	 */
	public void saveSwdnSh(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: �˺ż��ɹ���������ҳ��,��ȡ�û������Ϣ 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param userDomain
	* @return
	* @throws Exception
	 */
	public UserDomain getUserInfo(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: �˺ż��ɹ���������ҳ��,��ȡ�û������˵��� 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param userDomain
	* @return
	* @throws Exception
	 */
	public String getUserMenu(UserDomain domain, UserDomain userDomain)throws Exception;
	
	public void initTopMenu(UserDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * ��λ�л�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initGwqh(QyRyGwDomain domain, UserDomain userDomain) throws Exception;
	public void initCdDh(UserDomain domain, UserDomain userDomain) throws Exception;
	
	
}
