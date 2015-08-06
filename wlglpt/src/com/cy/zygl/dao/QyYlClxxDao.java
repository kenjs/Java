package com.cy.zygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.domain.QyYlClxxDomain;

/**
 * The DAO for 企业-运力-车辆信息.
 * 
 * @author HJH
 */
public interface QyYlClxxDao extends ExtendDao {
	/**
	 * 检索司机
	 * @return
	 * @throws Exception
	 */
	public List<QyYlClxxDomain> querySj(String clDjxh) throws Exception;
	
	/**
	 * 删除司机
	 * @param domain
	 * @throws Exception
	 */
	public void deleteSj(String xh,String clDjxh) throws Exception;
	
	/***
	 * 保存司机
	 * @param domain
	 * @throws Exception
	 */
	public void saveSj(QyYlClxxDomain domain) throws Exception;
	public int getXh(QyYlClxxDomain domain) throws Exception;
	/**
	 * 保存车辆信息
	 * @param domain
	 * @throws Exception
	 */
	public void saveCl(QyYlClxxDomain domain,UserDomain user) throws Exception;
	
	/**
	 * 查找司机信息
	 * @return
	 * @throws Exception
	 */
	public QyYlClxxDomain getSjxx(String clDjxh,String xh) throws Exception;
	
	/**
	 * 检查车辆号码是否重复
	 * @return
	 * @throws Exception
	 */
	public int checkClhm(QyYlClxxDomain domain) throws Exception;
	
	/**
	 * 保存后取当前xh
	 * @param domain
	 * @throws Exception
	 */
	public String getCurrentXh(QyYlClxxDomain domain) throws Exception;
}
