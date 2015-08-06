package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.domain.XtJsGnmkDomain;


/**
 * THE ACTION FOR ��ҵ-��֯���� ��ɫȨ��ά��
 * @author ��ΰ
 * @date 2013.1.17
*/ 	


public interface JsqxwhDao extends ExtendDao {
	
	/**
	 * ����ϵͳ�����ȡ ��ϵͳ�µ�Ŀ¼�͹���ģ������νṹ ��htmlƬ��
	* @Description: 
	* @Note
	* @author 
	* @since 2013-1-14
	* @param xtflDm
	* @param tabPageNum
	* @return
	* @throws Exception
	 */
	public String getTreeStr(String xtflDm, String tabPageNum,UserDomain user) throws Exception;
	/**
	 * ���ݽ�ɫ�����ȡ����ģ�����
	* @Description: 
	* @Note
	* @author 
	* @since 2013-1-14
	* @param jsDm
	* @return
	* @throws Exception
	 */
	public String getGnmkDmsByJsDm(String jsDm) throws Exception;
	/**
	 * ����ϵͳ-��ɫ-����ģ��
	* @Description: 
	* @Note
	* @author 
	* @since 2013-1-14
	* @param jsDm
	* @param gnmkDm
	* @throws Exception
	 */
	public void saveXtJsGnmk(String jsDm,String gnmkDm,String xtml)throws Exception;
	/**
	 * ��ɫ-Ȩ��ʱɾ��
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-27
	* @param domain
	* @throws Exception
	 */
	public void deleteXtJsGnmk(XtJsGnmkDomain domain)throws Exception;
	/**
	 * �����������ϵͳ-��ɫ-����ģ��
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-27
	* @param jsDm
	* @param gnmkDm
	* @return
	* @throws Exception
	 */
	public boolean checkXtJsGnmk(String jsDm,String gnmkDm,String xtml)throws Exception;
	/**
	 * ���ݹ���ģ������ȡ��ɫƴ���ַ���
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param gnmkDm
	* @return
	* @throws Exception
	 */
	public String getJsInnerHtmlByGnmkDm(BaseBusinessDomain domain) throws Exception;
	/**
	 * Ȩ��-��ɫʱɾ��
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-27
	* @param domain
	* @throws Exception
	 */
	public void deleteXtJsGnmkByGnmkDm(String gnmkDm,String jsDm)throws Exception;
	public List<QyJsDomain> queryJsByDjxh(BaseBusinessDomain domain)throws Exception;
	
	
}
