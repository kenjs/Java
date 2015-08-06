package com.cy.dctms.function.service;

import java.util.List;

import com.cy.dctms.common.domain.FunctionInfoDomain;

public interface IFunctionInfoService {
	
	/**
	 * ��ѯ�û�ӵ�еĹ��ܲ˵�Ȩ��
	 */
	public FunctionInfoDomain queryFunciontInfo(FunctionInfoDomain functionInfoDomain,String userId);
	/**
	 * ��ѯϵͳ������Ϣ�б�
	 * @author:wjl
	 */
	public List<FunctionInfoDomain> queryFunctionInfoList(FunctionInfoDomain functionInfoDomain);
	/**
	 * ��ϵͳ������Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public FunctionInfoDomain queryFunctionInfoMxById(String id);
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
	
