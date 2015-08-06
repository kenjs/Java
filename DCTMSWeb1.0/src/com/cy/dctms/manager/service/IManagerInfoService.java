package com.cy.dctms.manager.service;

import java.util.List;

import com.cy.dctms.common.domain.ManagerInfoDomain;

public interface IManagerInfoService {
	/**
	 * 管理员登陆
	 * @author:wjl
	 */
	public ManagerInfoDomain userLogin(ManagerInfoDomain managerInfoDomain);
	/**
	 * 管理员密码修改
	 * @author:wjl
	 */
	public void updateManagerPassword(ManagerInfoDomain managerInfoDomain ,String userId);
		/**
	 * 查询管理员信息列表
	 * @author:wjl
	 */
	public void queryManagerInfoList(ManagerInfoDomain managerInfoDomain);
	/**
	 * 查管理员信息明细根据ID
	 * @author:wjl
	 */
	public ManagerInfoDomain queryManagerInfoMxById(String id);
	/**
	 * 保存管理员信息
	 * @author:wjl
	 */
	public void saveManagerInfo(ManagerInfoDomain managerInfoDomain ,String userId);
	/**
	 * 删除管理员信息
	 * @author:wjl
	 */
	public void deleteManagerInfo(String id,String userId);
	/**
	 * 导出管理员信息列表
	 * @author:wjl
	 */
	public void exportManagerInfo(ManagerInfoDomain managerInfoDomain);
}
	
