package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.bo.QySwdnDsh;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.XtGnmkDomain;
import com.cy.common.domain.XtXtmlDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyRyGwDomain;

/**
 * 
* @Descriptoin ��¼dao 
* @Note
* @author anq
* @since 2012-12-18 ����05:20:25 
* @version
 */
public interface LoginDao extends ExtendDao {	
	
	/**
	 * 
	* @Description: ��¼ʱ����û���Ϣ 
	* @Note
	* @author 
	* @since 2012-12-19
	* @param domain
	* @throws Exception
	 */
	public void checkUserInfo(UserDomain domain) throws Exception;
	
	public UserDomain getUserInfo(UserDomain domain) throws Exception;
	
	public List<XtXtmlDomain> getTopMenu(UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: �˺ż��ɹ�����ȡ�û������˵��� 
	* @Note
	* @author 
	* @since 2012-12-18
	* @param domain
	* @return
	* @throws Exception
	 */
	public List<XtGnmkDomain> getUserMenu(UserDomain domain, UserDomain userDomain) throws Exception;
	
	public List<XtGnmkDomain> queryLatestOprMenu(UserDomain domain, UserDomain userDomain) throws Exception;
	
	public void saveSwdnDsh(QySwdnDsh qySwdnDsh) throws Exception;
	
	public String queryLatestOprXtml(String czyDjxh) throws Exception;
	/**
	 * ��λ�л�
	 * @param czyDjxh
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryGw(String czyDjxh) throws Exception;
}
