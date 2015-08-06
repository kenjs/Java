package com.cy.hygl.dao;

import java.util.List;
import java.util.Map;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyJsglSrdzDomain;
import com.cy.hygl.domain.JsSrdzDomain;
import com.cy.hygl.domain.JsSrdzDzcyDomain;

/**
 * The DAO for 收入对账
 * 
 * @author HJH
 */
public interface HyJsglSrdzDao extends ExtendDao {
	
	/**
	 * 初始化结算-收入对帐
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsSrdzByJsDjxh(JsSrdzDomain domain) throws Exception;
	
	/**
	 * 结算-收入对帐-对帐差异-临时表List
	 * @param dzDjxh
	 * @return
	 * @throws Exception
	 */
	public List<JsSrdzDzcyDomain> queryDzcyTempList(String dzDjxh) throws Exception ;
	/**
	 * 结算-收入对帐-对帐差异list
	 * @param dzDjxh
	 * @return
	 * @throws Exception
	 */
	public List<JsSrdzDzcyDomain> queryDzcyList(String dzDjxh) throws Exception;
	/**
	 * 保存或修改结算-收入对帐-对帐差异
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzcyDomain(JsSrdzDzcyDomain domain, UserDomain user) throws Exception;
	/**
	 * 保存或修改结算-收入对帐-对帐差异tempo\
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzcyTempDomain(JsSrdzDzcyDomain domain, UserDomain user) throws Exception;
	
	/**
	 * 查看结算-收入对帐-对帐差异根据jsDjxh
	 * @param baseDomain
	 * @throws Exception
	 */
	public void initDomainDzcyMx(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 删除结算-收入对帐-对帐差异根据主键(正式表)
	 * @param dzDjxh
	 * @param xh
	 * @throws Exception
	 */
	public void deleteDzcyByKey(String dzDjxh,String xh) throws Exception;
	
	/**
	 *  根据对账登记序号删除对应的对账差异记录
	 * @param dzDjxh
	 * @throws Exception
	 */
	public void deleteDzcyByDzDjxh(String dzDjxh) throws Exception;
	
	/**
	 * 删除结算-收入对帐-对帐差异-临时表根据dzDjxh
	 * @param dzDjxh
	 * @throws Exception
	 */
	public void deleteDzcyTempByDzDjxh(String dzDjxh) throws Exception;
	/**
	 * 删除结算-收入对帐-对帐差异-临时表根据主键
	 * @param dzDjxh
	 * @param xh
	 * @throws Exception
	 */
	public void deleteDzcyTempByKey(String dzDjxh, String xh) throws Exception ;
	/**
	 * 货运管理-结算管理-收入对帐-后续处理（对帐保存后，再调本PROD）
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void callHyglJsglSrdzDdHxcl(HyJsglSrdzDomain domain, UserDomain user) throws Exception;
	
	/**
	 * 货运管理-结算管理-收入对帐(订单货物)-后续处理（对帐保存后，再调本PROD）
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void callHyglJsglSrdzDdhwHxcl(HyJsglSrdzDomain domain, UserDomain user) throws Exception;
	/**
	 * 查结算订单货物信息
	 * @param jsDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsDdHwxxDomain(String jsDjxh) throws Exception;
	/**
	 * 保存结算订单货物信息
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveJsDdHwxxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 取物流损失下拉
	 * @param ddDjxh
	 * @param xh
	 * @return
	 */
	public List<HyJsglSrdzDomain> getWlssxl(String ddDjxh,String xh);

	
	public Integer queryDzcyNextXh(String dzDjxh) throws Exception;
	
	public boolean checkSrdz(String jsDjxh) throws Exception;
	
	public void deleteJsDdHwxxDomainByKey(String jsDjxh) throws Exception;
	
	public BaseBusinessDomain getDomainDzcyByKey(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 根据结算登记序号获取对账方式信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JsSrdzDomain getDzInfo(String jsDjxh) throws Exception;

}
