package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.xtgl.domain.QyJsDomain;

public interface WlglptCommonDao extends BaseDao {
	
	/**
	 * 用于获取级联业务的公共DAO方法
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryCommonList(WlglptCommonDomain domain, UserDomain userDomain) throws Exception;
	
	public List<DmbGgDomain> queryBmList(WlglptCommonDomain domain) throws Exception;
	
	public List<DmbGgDomain> queryGwList(WlglptCommonDomain domain) throws Exception;
	
	public List<DmbGgDomain> queryRyList(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * 根据机构编码取级别代码
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getQyJbdmByJgbm(String jgbm) throws Exception;
	
	/**
	 * 根据机构编码取可使用的角色列表
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
	 * 调用数据库函数，取单据顺序号
	 * @param sxhflbm
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getFunXtSxh(String sxhflbm, String jgbm) throws Exception;
	
	/**
	 * 取系统参数
	 * @param csxh
	 * @param jgbm
	 * @param cslb
	 * @return
	 * @throws Exception
	 */
	public String getFunXtXtcs(String csxh, String jgbm, String cslb) throws Exception;
	
	/**
	 * 取公司下维护的分包商
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryFbsByJgbm(WlglptCommonDomain domain, String ssJgbm) throws Exception;
	
	/**
	 * 取部门下维护的项目维护
	 * @param baseDomain
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryWs(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * 取sequence
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public String selectSequence(String seqName) throws Exception;
	
	/**
	 * 派车方式代码表(主要用于单选按钮，不含代码)
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryPcfsRadioList() throws Exception;
}
