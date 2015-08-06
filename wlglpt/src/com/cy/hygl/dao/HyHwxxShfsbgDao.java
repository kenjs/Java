package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for 货运-货物信息-送货方式变更.
 * 
 * @author HJH
 */
public interface HyHwxxShfsbgDao extends ExtendDao {

	/**
	 * 检查送货方式是否可变更   大于0不可变
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkShfs(String pcDjxh) throws Exception;
	/**
	 * 检查子表记录数
	 * @param ddDjxh
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public int checkShfsbgZb(String ddDjxh,String xh) throws Exception;
}
