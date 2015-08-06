package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptDropDownCommonDomain;
import com.cy.hygl.domain.QyZrbmThShdzDomain;
import com.cy.zygl.domain.DmXzqhDomain;
import com.cy.zygl.domain.QyYlClxxDomain;

public interface WlglptDropDownCommonDao {
	
	/**
	 * @description �����������������	
	 * @return
	 * @throws Exception
	 */
	public List<DmXzqhDomain> queryXzqhInputSelList() throws Exception;
	
	/**
	 * @description ȡ������������
	 * @return
	 * @throws Exception
	 */
	public List<DmXzqhDomain> queryXzdqList() throws Exception;

	/**
	 * ����������
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHykhListByBmbm(String bmbm) throws Exception;
	
	public List<WlglptDropDownCommonDomain> queryHykhListBySsjgbm(String ssJgbm) throws Exception;
	
	/**
	 * ������������
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyhwList(String khDjxh, String CS20001) throws Exception;
	
	/**
	 * ����������ַ
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyShdzList(String khDjxh) throws Exception;
	
	/**
	 * �����ջ���ַ
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyShrDzList(String khDjxh) throws Exception;
	
	/**
	 * �����ջ���λ
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyShdwList(String khDjxh)throws Exception;
	
	/**
	 * ������Ϣ
	 * @param bmbm
	 * @return
	 * @throws Exception
	 */
	public List<QyYlClxxDomain> queryQyClxxBySsbmbm(String bmbm, QyYlClxxDomain clxxDomain) throws Exception;
	
	public List<QyYlClxxDomain> queryQyGcxxBySsbmbm(String bmbm, QyYlClxxDomain clxxDomain) throws Exception;
	
	public List<QyZrbmThShdzDomain> queryZrbmThShdz(QyZrbmThShdzDomain domain, UserDomain userDomain) throws Exception;
}
