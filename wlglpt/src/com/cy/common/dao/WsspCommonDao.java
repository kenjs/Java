package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.common.domain.WssplzDomain;
import com.cy.framework.dao.BaseDao;

/**
 * 
 * @author hel
 * DESC:文书审批的DAO对象
 *
 */
public interface WsspCommonDao extends BaseDao {
	/**
	 * 查询是否允许终审
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void queryWszsJudge(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 文书终审
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveJudge(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 文书退回
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveBack(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 文书审批流转查询
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public List<WssplzDomain> querySplzcx(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 发送列表
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public WsspCommonDomain queryFsSelect(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 发送
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveSend(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 获取审批人通过审批人机构
	 * @param spJgbm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> querySprListBySpJgbm(String spJgbm) throws Exception;
	/**
	 * 获取审批人通过岗位登记序号
	 * @param gwDjxh
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> querySprListByGwDjxh(String gwDjxh) throws Exception;
	/**
	 * 取审批件url
	 * @param wsspxh
	 * @return
	 * @throws Exception
	 */
	public String queryWsspjUrl(String wsspxh) throws Exception;
	
	/**
	 * 首次发送列表
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WsspCommonDomain queryScFsSelect(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 首次发送
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveScSend(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 获取文书 审批流程设置序号
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 获取审批人通过本单位
	 * @param bdwDm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryWssprByBdwList(String bdwDm) throws Exception;
	/**
	 * 获取审批人通过总公司
	 * @param zgsDm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryWssprByZgsList(String zgsDm) throws Exception;
	
}
