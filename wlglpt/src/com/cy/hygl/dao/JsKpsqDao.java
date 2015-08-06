package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.bo.JsKpsqMx;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.JsKpsqDomain;

/**
 * The DAO for 开票申请.
 * 
 * @author HJH
 */
public interface JsKpsqDao extends ExtendDao {
	/**
	 * 获取清单信息list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryDzQdList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception;
	/**
	 * 检索当前维护的开票申请对帐清单列表list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryJsKpsqMxList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception;
	/**
	 * 检索当前维护的开票申请对帐清单列表list（临时表取数）
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryJsKpsqMxTempList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception;
	/**
	 * 删除结算-开票申请-对帐清单
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void deleteJsKpsqMxByKey(String kpsqmxDjxh) throws Exception;
	/**
	 * 删除结算-开票申请-对帐清单临时表
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void deleteJsKpsqMxTempByKey(String kpsqmxDjxh) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveJsKpsqMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxDomainByKey(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveJsKpsqMxTempDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxTempDomainByKey(BaseBusinessDomain baseDomain) throws Exception; 
	
	/**
	 * 更新申请开票金额合计
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 把临时表数据插入正式表结算-开票申请-对帐清单
	 * @param baseDomain
	 * @throws Exception
	 */
	public void insertJsKpsqMxByTemp(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 删除临时表结算-开票申请-对帐清单-临时表
	 * @param baseDomain
	 * @throws Exception
	 */
	public void deleteJsKpsqMxTemp(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 根据开票申请登记序号和清单登记序号查找是否已经存在对账清单记录（正式表）
	 * @param kpsqDjxh
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxDomainByXh(String kpsqDjxh,String qdDjxh) throws Exception;
	/**
	 * 根据开票申请登记序号和清单登记序号查找是否已经存在对账清单记录（临时表）
	 * @param kpsqDjxh
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxTempDomainByXh(String kpsqDjxh,String qdDjxh) throws Exception;
	
	public void callProKpsqHxcl(String kpsqDjxh, UserDomain userDomain) throws Exception;
	
	public List<JsKpsqDomain> querySrKpMx(JsKpsqDomain domain) throws Exception;
	
	public void savaSrKpMxTemp(JsKpsqMx bo) throws Exception;
	
	public List<JsKpsqDomain> querySrKpsqMxList(JsKpsqDomain domain,int i) throws Exception;
	
	public void deleteSqKpTemp(JsKpsqMx bo) throws Exception;
	
	public void savaSrKpMx(JsKpsqMx bo) throws Exception;
	
	public int checkSrSpMx(JsKpsqMx bo) throws Exception;
	
	public void deleteSrKpMx(JsKpsqMx bo) throws Exception;
	
	public void updateSrKpJeByDjxh(JsKpsqDomain domain,double zje) throws Exception;
	
	
}
