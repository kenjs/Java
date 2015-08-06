package com.cy.dctms.manager.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.ManagerInfoDomain;

public interface IManagerInfoDao {
	
	public ManagerInfoDomain queryManagerInfoByCode(String code);	
	/**
	 * 查询管理员信息列表
	 * @author:wjl
	 */
	public void queryManagerInfoList(ManagerInfoDomain managerInfoDomain);
	/**
	 * 导出管理员信息列表
	 * @author:wjl
	 */
	public void exportManagerInfo(ManagerInfoDomain managerInfoDomain);
	/**
	 * 修改管理员密码
	 * @author:wjl
	 */
	public void updateManagerPassword(ManagerInfoDomain managerInfoDomain,String userId);
	/**
	 * 查管理员信息明细根据ID
	 * @author:wjl
	 */
	public ManagerInfoDomain queryManagerInfoById(String id);	
	/**
	 * 保存管理员信息
	 * @author:wjl
	 */
	public void saveManagerInfo(ManagerInfoDomain managerInfoDomain,String userId);
	/**
	 * 保存管理员物理地址
	 * @author:wjl
	 */
	public void saveManagerMacInfo(String macAddress ,String id);
	/**
	 * 删除管理员信息
	 * @author:wjl
	 */
	public void deleteManagerInfo(String id,String userId);
	/**
	 * 删除管理员物理地址
	 * @author:wjl
	 */
	public void deleteManagerMacInfo(String id,String userId);
}
