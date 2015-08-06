package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for 货运-派车-货物信息-回单清单-明细.
 * 
 * @author HJH
 */
public interface HyPcHwxxHdqdMxDao extends ExtendDao {

	/**
	 * 根据qdDjxh改状态为0
	 * @param hdDjxh
	 * @throws Exception
	 */
	public void updateDqbzByqdDjxh(String hdDjxh) throws Exception;
	/**
	 * 根据key改当前状态为1
	 * @param hdDjxh
	 * @param hdqdDjxh
	 * @throws Exception
	 */
	public void updateDqbzBykey(String hdDjxh,String hdqdDjxh) throws Exception;
}
