package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.domain.CwFylbDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for ����-��Ʊ�Ǽ�
 * 
 * @author LYY
 */
public interface CwFyLbWhDao  extends ExtendDao {
	public List<BaseBusinessDomain> queryListById(CwFylbDomain CwFylbDomain,UserDomain user) throws Exception;
}
