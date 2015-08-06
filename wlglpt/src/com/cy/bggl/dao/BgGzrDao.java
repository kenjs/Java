package com.cy.bggl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公管理 工作日
 * 
 * @author 闫伟
 * @date 2013.1.24
 */

public interface BgGzrDao extends ExtendDao {
	
	/**
	 * 根据jgbm和日期修改工作日
	 * @return
	 * @throws Exception
	 */
	public void updateGzrByJgbm(BaseBusinessDomain domain) throws Exception;
}
