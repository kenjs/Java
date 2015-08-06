package com.cy.dctms.function.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.FunctionInfoDomain;


public interface IFunctionInfoDao {
	
	
	/**
	 * ��ѯϵͳ������Ϣ�˵�
	 * @author:wjl
	 */
	public List<FunctionInfoDomain> queryFunctionInfo(String userId);	
	/**
	 * ��ѯϵͳ������Ϣ����
	 * @author:wjl
	 */
	public Integer queryFunctionInfoCount(Map<String,Object> map);
	/**
	 * ��ѯϵͳ������Ϣ�б�
	 * @author:wjl
	 */
	public List<FunctionInfoDomain> queryFunctionInfoByPage(Map<String,Object> map);
	/**
	 * ��ϵͳ������Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public FunctionInfoDomain queryFunctionInfoById(String id);
	/**
	 * ����ϵͳ������Ϣ
	 * @author:wjl
	 */
	public void saveFunctionInfo(FunctionInfoDomain functionInfoDomain);
	/**
	 * ɾ��ϵͳ������Ϣ
	 * @author:wjl
	 */
	public void deleteFunctionInfo(String id);
	
}
