package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.domain.JsSrdzQdDomain;

/**
 * The DAO for 结算-收入对帐-清单.
 * 
 * @author HJH
 */
public interface JsSrdzQdDao extends ExtendDao {

	/**
	 * 查询已对账的list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryYdz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	/**
	 * 查询对账清单明细list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryDzQdMxTemp(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	/**
	 * 查询对账明细临时表list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryDzQdMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	/**
	 * 保存对账清单明细
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzQdMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception ;
	/**
	 * 保存对账清单明细临时表
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzQdMxTempDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 删除对账清单明细临时表
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteDzQdMxTemp(String qdDjxh,String ywDjxh, String ywMxXh, UserDomain userDomain) throws Exception;
	/**
	 * 删除对账清单明细
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteDzQdMx(String qdDjxh,String ywDjxh, String ywMxXh, UserDomain userDomain) throws Exception;
	/**
	 * 把清单明细临时表数据保存到正式表
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void saveQdMxDomainByTemp(String qdDjxh) throws Exception;
	/**
	 * 删除清单明细临时表
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void deleteDzQdMxTempByQdDjxh(String qdDjxh) throws Exception;
	/**
	 * 更新JS_SRDZ_DD结算-收入对帐-订单中的清单登记序号
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void updateJsSrDzDd(String qdDjxh) throws Exception;
	/**
	 *  删除清单明细时，把JS_SRDZ_DD结算-收入对帐-订单中的清单登记序号置空
	 * @param jsDjxh
	 * @throws Exception
	 */
	public void deleteDzQdMxUpdateSrDzDdOfIsNull(String ywDjxh) throws Exception;
	
	/**
	 * 更新合计金额
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	/**
	 * 检索费用登记列表
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public List<HyZyglFydjDomain> queryFydjList(JsSrdzQdDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 更新对账清单里的合计金额
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void updateQdHeJeByKey(String qdDjxh) throws Exception;
	
	public void updateJsSrdzQdxhToNullByQdDjxh(String qdDjxh) throws Exception;
	
	public Double calQdHjJe(String qdDjxh) throws Exception;

	/**
	 * 取物清单中对应流损失登记序号
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<JsSrdzQdDomain> selectSrdzQdMxWhenWlss(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 生成财务信息
	 * @throws Exception
	 */
	public void cwYsfSrdz(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 不开票申请时，生成开票信息
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveKp(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	/**
	 * 检查清单是否可以删除（如果已经进入开票申请，则不可以删）
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkQdDel(String qdDjxh) throws Exception;
	
	/**
	 * 清单不开票时，删除检验支付是否登记
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkQDCwInfo(String qdDjxh) throws Exception ;
	
	/**
	 * 删除财务支付信息
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void deleCwInfo(String qdDjxh) throws Exception ;
}
