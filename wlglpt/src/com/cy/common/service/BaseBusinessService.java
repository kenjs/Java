package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.service.BaseService;

public interface BaseBusinessService extends BaseService {

	/**
	 * 页面初始化
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws ServiceException
	 */
	public void init(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 明细页面初始化
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws ServiceException
	 */
	public void initMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 页面查询
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void query(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 明细页面查询
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 页面保存
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void save(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 明细页面保存
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 页面删除
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void delete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 明细页面删除
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 页面打印
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void print(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	/**
	 * 明细页面打印
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void printMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 页面导出excel
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
	 * 启用
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	
	/**
	 * 停用
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

	
}
