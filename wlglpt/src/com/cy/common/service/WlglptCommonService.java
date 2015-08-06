package com.cy.common.service;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.service.BaseService;
import com.cy.xtgl.domain.QyJsDomain;

public interface WlglptCommonService extends BaseService {
	
	/**
	 * 用于获取级联业务的公共服务方法
	 * @param domain
	 * @throws Exception
	 */
	public void getCommonList(WlglptCommonDomain domain, UserDomain userDomain) throws Exception;

	/**
	 * 
	* @Description: 根据传入的公司代码检索其下一级公司
	* @Note
	* @author 
	* @since 2013-3-5
	* @param domain
	* @throws Exception
	 */
	public void getBmList(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 根据传入的公司代码检索其下一级岗位
	* @Note
	* @author 
	* @since 2013-3-5
	* @param domain
	* @throws Exception
	 */
	public void getGwList(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * 根据企业机构编码获取企业级别代码
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getQyJbdmByJgbm(String jgbm) throws Exception;
	
	/**
	 * 传入一个机构编码，返回它自身及它上级（也包括上上级，上上上级...）所有的角色
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public List<QyJsDomain> getJsListByJgbm(String jgbm) throws Exception;
	
	/**
	 * 传入一个机构编码，返回他的上级机构编码
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getSjJgbmByJgbm(String jgbm) throws Exception;
	
	/**
	 * 取当前操作员所在公司下维护的分包商
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryCurrentFbs(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 取部门下维护的项目维护
	 * @param baseDomain
	 * @throws Exception
	 */
	public void queryWs(BaseBusinessDomain baseDomain) throws Exception;
}
