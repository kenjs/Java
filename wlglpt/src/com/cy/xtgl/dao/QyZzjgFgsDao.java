package com.cy.xtgl.dao;

import com.cy.common.bo.QyZzjg;
import com.cy.common.dao.ExtendDao;

import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * THE ACTION FOR 企业-组织机构 分公司
 * @author 闫伟
 * @date 2013.1.7
*/ 	

public interface QyZzjgFgsDao extends ExtendDao {
	
	/**
	 * 停用
	 * @return
	 * @throws Exception
	 */
	public void stopUser(QyZzjgDomain domain)throws Exception;
	/**
	 * 启用
	 * @return
	 * @throws Exception
	 */
	public void startUser(QyZzjgDomain domain)throws Exception;
	/**
	 * 获取最大的级别代码
	 * @return
	 * @throws Exception
	 */
	public String getMaxJbdm(QyZzjg bo)throws Exception;
	/**
	 * 进行重复验证是否重名
	 * @return
	 * @throws Exception
	 */
	public int checkQyzzFgsMc(QyZzjgDomain domain)throws Exception;
}
