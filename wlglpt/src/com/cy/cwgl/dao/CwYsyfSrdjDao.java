package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for 财务-收入登记
 * 
 * @author HCM
 */
public interface CwYsyfSrdjDao  extends ExtendDao {
	/**
	 * 应收应付信息
	 * @param ysyfDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getYsyfSrdj(String ysyfDjxh) throws Exception;
    /**
     * 收入信息list
     * @param ysyfDjxh
     * @return
     * @throws Exception
     */
	public List<BaseBusinessDomain> getSrdjList(String ysyfDjxh) throws Exception;
	/**
	 * 删除收入信息
	 * @param ysyfDjxh
	 * @throws Exception
	 */
	public  void deleteSrdj(String srDjxh)throws Exception ;
	/**
	 * 后续处理
	 * @param srDjxh
	 * @param bz
	 * @throws Exception
	 */
	public void callPHyglCwglSrdjHxcl(String srDjxh, String bz) throws Exception;
	/**
	 * 结算方类别
	 * @return
	 * @throws Exception
	 */
	public List<CwYsyfSrdjDomain> getLbList() throws Exception;
	/**
	 * 结算方名称
	 * @return
	 * @throws Exception
	 */
	public List<CwYsyfSrdjDomain> getMcList(String ssJgbm,String yfjsfDm) throws Exception;
	/**
	 * 撤销
	 * @param domain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain domain)throws Exception;

	
	public void checkZfFs(CwYsyfSrdjDomain domain)throws Exception;
	
	public void initCwYsJe(CwYsyfSrdjDomain domain,UserDomain userDomain) throws Exception;

	
	public BaseBusinessDomain getYsyfSrdjByYwDjxh(String ywDjxh) throws Exception;
	
	public int checkSrdj(String yfjsfDm,String yfjsfDjxh) throws Exception;
	
	/**
	 * 删被加入了对账的公司承担的物流损失对应的应收应付信息
	 * @param ywDjxh
	 * @throws Exception
	 */
	public  void deleteYsyfXx(String ywDjxh)throws Exception;
	
	/**
	 * 恢复因加入对账被删除的应收应付信息
	 * @param ywDjxh
	 * @throws Exception
	 */
	public  void updateYsyfXx(String ywDjxh)throws Exception;
	/**
	 * 检查删除
	 * @param bz 0 对应单挑收入登记 1对应整个应收应付
	 * @throws Exception
	 */
    public void checkDelete(String xh,int bz)throws Exception;

}
