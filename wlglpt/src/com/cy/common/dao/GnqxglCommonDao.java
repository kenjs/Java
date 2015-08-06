package com.cy.common.dao;

import com.cy.framework.dao.BaseDao;

/**
 * 
 * @author lust
 * DESC:提供数据库服务的DAO对象
 *
 */
public interface GnqxglCommonDao extends BaseDao {
	
	/**
	 * 根据参数编码取得相应的参数值
	 */
	public String getCszByCsbm(String csbm, String nsrsbh, String czyDm, String dwNsrsbh, String nsrSwjgDm) throws Exception;
	
}
