package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.xtgl.domain.YhjswhDomain;

/**
 * �û���ɫά��
 * 
 * @author HaoY
 * @since
 */
public interface YhjswhDao extends ExtendDao {
	/**
	 * �����û�����(��ҳ���)
	 */
	public List<YhjswhDomain> queryYhMcList(BaseBusinessDomain domain,PageDomain page) throws Exception;
	
	/**
	 * �����û�����(�������)
	 */
	public List<YhjswhDomain> queryYhMcList(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * ������ɫ����
	 */
	public List<YhjswhDomain> queryJsMcList(String qyZcxh) throws Exception;
	
	/**
	 * �����û���ѡ���ɫ
	 */
	public List<YhjswhDomain> queryChoosedJs(BaseBusinessDomain domain) throws Exception;
}
