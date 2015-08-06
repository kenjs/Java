package com.cy.dctms.function.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.FunctionInfoDomain;


public interface IFunctionInfoDao {
	
	
	/**
	 * 查询系统功能信息菜单
	 * @author:wjl
	 */
	public List<FunctionInfoDomain> queryFunctionInfo(String userId);	
	/**
	 * 查询系统功能信息数量
	 * @author:wjl
	 */
	public Integer queryFunctionInfoCount(Map<String,Object> map);
	/**
	 * 查询系统功能信息列表
	 * @author:wjl
	 */
	public List<FunctionInfoDomain> queryFunctionInfoByPage(Map<String,Object> map);
	/**
	 * 查系统功能信息明细根据ID
	 * @author:wjl
	 */
	public FunctionInfoDomain queryFunctionInfoById(String id);
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
