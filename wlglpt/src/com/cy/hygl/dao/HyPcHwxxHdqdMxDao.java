package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for ����-�ɳ�-������Ϣ-�ص��嵥-��ϸ.
 * 
 * @author HJH
 */
public interface HyPcHwxxHdqdMxDao extends ExtendDao {

	/**
	 * ����qdDjxh��״̬Ϊ0
	 * @param hdDjxh
	 * @throws Exception
	 */
	public void updateDqbzByqdDjxh(String hdDjxh) throws Exception;
	/**
	 * ����key�ĵ�ǰ״̬Ϊ1
	 * @param hdDjxh
	 * @param hdqdDjxh
	 * @throws Exception
	 */
	public void updateDqbzBykey(String hdDjxh,String hdqdDjxh) throws Exception;
}
