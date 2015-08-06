package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.RyxzCommonDomain;
import com.cy.framework.dao.BaseDao;

/**
 * 
 * @author hel
 * DESC:人员选择的DAO对象
 *
 */
public interface RyxzCommonDao extends BaseDao {
	
	
	/**
	 * 组织机构
	 * @param qyzcXh 企业注册序号
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryQyjgList(String qyzcXh) throws Exception;
	/**
	 * 企业内部人员
	 * @param qyzcXh 企业注册序号
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryQyryList(String qyzcXh) throws Exception;
	
	/**
	 * 企业内部人员及组织机构
	 * @param qyzcXh 企业注册序号
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryQyryAndZzjgList(String qyzcXh) throws Exception;
	
	
	/**
	 * 分包商组织机构
	 * @param qyzcXh 企业注册序号
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryFbsZzjgList(String qyzcXh) throws Exception;
	
	/**
	 * 分包商的人员
	 * @param qyzcXh 企业注册序号
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryFbsryList(String qyzcXh) throws Exception;
}
