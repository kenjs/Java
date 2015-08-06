package com.cy.dzgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.dzgl.domain.QySpwsSplcszDomain;
import com.cy.dzgl.domain.QySpwsSplcszZbDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 企业-审批文书-审批流程设置.
 * 
 * @author anq
 */
public interface QySpwsSplcszDao extends ExtendDao {

	/**
	 * 
	* @Description: 初始化 企业-审批文书-审批流程设置-子表
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @throws Exception
	 */
	public void initSpjcDomainMx(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 
	* @Description: 保存 企业-审批文书-审批流程设置-子表
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @throws Exception
	 */
	public void saveSpjc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 根据主表主键取审批流程设置子表列表 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param splcSzxh 审批流程设置主表主键
	* @return
	* @throws Exception
	 */
	public List<QySpwsSplcszZbDomain> querySplcszZbList(Long splcSzxh) throws Exception;
	
	/**
	 * 
	* @Description: 删除 企业-审批文书-审批流程-子表 中审批级次最大的一条记录 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @param userDomain
	* @throws Exception
	 */
	public void deleteSplcZb(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void deleteSpjcBySplcSzxh(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 根据主键检索 企业-审批文书  中配置的项目分类标志
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @param userDomain
	* @return
	* @throws Exception
	 */
	public String queryQySpwsXmflbzByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 检索文书信息
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWsxx(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * 根据splcSzxh获取文书审批流程设置
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWssplcsz(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * 重新设置审批流程
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveCxszSplc(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * 取消设置审批流程
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteQxszSplc(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;

}
