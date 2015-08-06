package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyClgzDomain;
/**
 * The DAO for 车辆跟踪.
 * 
 * @author HCM
 */
public interface HyClgzDao extends ExtendDao {

/**
 * 派车信息
 * @param pcDjxh
 * @return
 * @throws Exception
 */
	public BaseBusinessDomain getHyClgzPcxx(String pcDjxh) throws Exception;
	/**
	 * 车辆跟踪信息列表
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> getHyClgzList(String pcDjxh) throws Exception;
	/**
	 * 根据序号删除
	 * @param clgzDjxh
	 * @throws Exception
	 */
	public void deleteClgzByKey(String clgzDjxh)throws Exception;
	/**
	 * 跟踪信息
	 * @param clgzDjxh
	 * @return
	 * @throws Exception
	 */
	public HyClgzDomain getClgzByKey(String clgzDjxh)throws Exception;
}
