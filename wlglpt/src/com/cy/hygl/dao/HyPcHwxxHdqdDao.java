package com.cy.hygl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;

/**
 * The DAO for 货运-派车-货物信息-回单清单.
 * 
 * @author HJH
 */
public interface HyPcHwxxHdqdDao extends ExtendDao {
	public List<BaseBusinessDomain> queryQdList(BaseBusinessDomain baseDomain)  throws Exception;
	/**
	 * 接收
	 * @param hdqdDjxh
	 * @throws Exception
	 */
	public void updatejsztWhenJs(String hdqdDjxh) throws Exception;
	/**
	 * 检查能否退回
	 * @param hdqdDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkTh(String hdqdDjxh) throws Exception;
	/**
	 * 退回
	 * @param hdqdDjxh
	 * @throws Exception
	 */
	public void updatejsztWhenTh(String hdqdDjxh) throws Exception;
	/**
	 * 查删除 清单中有哪些回单
	 * @param hdqdDjxh
	 * @return
	 * @throws Exception
	 */
	public List<HyPcHwxxHdqdDomain> hdDjxhList(String hdqdDjxh) throws Exception;
	/**
	 * 查对应回单 接收的清单号
	 * @param hdDjxh
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public String selectQdDjxh(String hdDjxh,String ssJgbm) throws Exception;
	/**
	 * 查回单中清单
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryHdByQd(BaseBusinessDomain baseDomain)  throws Exception;
}
