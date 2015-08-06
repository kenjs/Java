package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyMbTydDomain;

/**
 * The DAO for 货运-模版-托运单.
 * 
 * @author HJH
 */
public interface HyMbTydDao extends ExtendDao {

	/**
	 * @description 托运单保存为模板
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveAsTemplate(HyMbTydDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 检查同一公司下模板名称是否重复
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void checkTemplateName(HyMbTydDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 检索托运单管理-模板选择
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryMb4Tydgl(HyMbTydDomain domain, UserDomain userDomain) throws Exception;
	
}
