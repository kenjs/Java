package com.cy.bggl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The SERVICE for 办公-工作联系.
 * 
 * @author HJH
 */

public interface BgGzlxService extends BaseBusinessService {
	/**
	 * 获取附件信息
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryFjDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 获取收件箱信息
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryForSjxDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 获取草稿箱信息
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryForCgxDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 删除附件信息
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteFjDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 修改接收人信息
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void updateJsrDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void doMyDownloadForSjx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void doMyDownloadForCgx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}