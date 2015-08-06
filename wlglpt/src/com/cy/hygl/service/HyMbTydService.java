package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 货运-模版-托运单.
 * 
 * @author HJH
 */

public interface HyMbTydService extends BaseBusinessService {
	
	/**
	 * 刷新货物列表
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void refreshHwList(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;

	/**
	 * @description 从托运单保存为模板
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveAsTemplate(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 检查同一公司下模板名称是否重复
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void checkTemplateName(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 初始化托运单管理-模板选择页面
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initMb4Tydgl(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 检索托运单管理-模板选择
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryMb4Tydgl(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 根据模板初始化托运单
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initTydByMb(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void initHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	public void deleteHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	public void saveHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
}