package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.cwgl.domain.CwZfdjDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for 财务-支付登记
 * 
 * @author LYY
 */
public interface CwZfdjDao  extends ExtendDao {

	/**
	 * 根据主键删除
	 * @param zfDjxh  支付登记序号
	 * @throws Exception
	 */
	public void deleteCwZfdjByKey(String zfDjxh)throws Exception;
	/**
	 * 后续处理
	 * @param zfDjxh
	 * @param bz
	 * @throws Exception
	 */
	public void callPHyglCwglZfdjHxcl(String zfDjxh, String bz) throws Exception;
	/**
	 * 结算方名称
	 * @return
	 * @throws Exception
	 */
	public List<CwZfdjDomain> getMcList(String ssJgbm,String yfjsfDm) throws Exception;
	/**
	 * 结算方类别
	 * @return
	 * @throws Exception
	 */
	public List<CwZfdjDomain> getLbList() throws Exception;
	/**
	 * 取银行
	 * @param domain
	 * @throws Exception
	 */
	public void doGetYh(BaseBusinessDomain domain)throws Exception;
	/**
	 * 撤销
	 * @param domain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain domain)throws Exception;
}
