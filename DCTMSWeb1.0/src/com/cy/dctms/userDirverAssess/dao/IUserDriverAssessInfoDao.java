package com.cy.dctms.userDirverAssess.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;

public interface IUserDriverAssessInfoDao {

	/**
	 * 查询企业对司机评价信息列表
	 * @author:wjl
	 */
	public void queryUserDriverAssessInfoList(UserDriverAssessInfoDomain userDriverAssessInfoDomain);

	/**
	 * 导出企业对司机评价信息列表
	 * @author:wjl
	 */
	public void exportUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain);

	/**
	 * 查企业对司机评价信息明细根据ID
	 * @author:wjl
	 */
	public UserDriverAssessInfoDomain queryUserDriverAssessInfoById(String id);	

	/**
	 * 保存企业对司机评价信息
	 * @author:wjl
	 */
	public void saveUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain,String userId);

	/**
	 * 删除企业对司机评价信息
	 * @author:wjl
	 */
	public void deleteUserDriverAssessInfo(String id,String userId);
}
