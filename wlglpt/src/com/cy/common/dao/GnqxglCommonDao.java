package com.cy.common.dao;

import com.cy.framework.dao.BaseDao;

/**
 * 
 * @author lust
 * DESC:�ṩ���ݿ�����DAO����
 *
 */
public interface GnqxglCommonDao extends BaseDao {
	
	/**
	 * ���ݲ�������ȡ����Ӧ�Ĳ���ֵ
	 */
	public String getCszByCsbm(String csbm, String nsrsbh, String czyDm, String dwNsrsbh, String nsrSwjgDm) throws Exception;
	
}
