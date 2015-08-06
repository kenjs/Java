package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgGzlxDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 办公-工作联系.
 * 
 * @author HJH
 */
public interface BgGzlxDao extends ExtendDao {

	/**
	 * 获取附件
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void  getFjDomain(BgGzlxDomain domain) throws Exception;
	
	/**
	 * 删除附件
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void  deleteFjDomain(BgGzlxDomain domain) throws Exception;
	
	/**
	 * 修改接受人信息
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void  updateJsrDomain(BgGzlxDomain domain) throws Exception;
	
	/**
	 * 获取收件箱信息
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryListForSjx(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * 获取草稿箱信息
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryListForCgx(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * 下载收件箱
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadListForSjx(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 下载草稿箱
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadListForCgx(BaseBusinessDomain baseDomain) throws Exception;

}
