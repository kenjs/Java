package com.cy.dctms.function.service;

import java.util.List;

import com.cy.dctms.common.domain.FunctionInfoDomain;

public interface IFunctionInfoService {
	
	/**
	 * 查询用户拥有的功能菜单权限
	 */
	public FunctionInfoDomain queryFunciontInfo(FunctionInfoDomain functionInfoDomain,String userId);
	/**
	 * 查询系统功能信息列表
	 * @author:wjl
	 */
	public List<FunctionInfoDomain> queryFunctionInfoList(FunctionInfoDomain functionInfoDomain);
	/**
	 * 查系统功能信息明细根据ID
	 * @author:wjl
	 */
	public FunctionInfoDomain queryFunctionInfoMxById(String id);
	/**
	 * 保存系统功能信息
	 * @author:wjl
	 */
	public void saveFunctionInfo(FunctionInfoDomain functionInfoDomain);
	/**
	 * 删除系统功能信息
	 * @author:wjl
	 */
	public void deleteFunctionInfo(String id);
}
	
