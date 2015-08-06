package com.cy.common.service;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.service.BaseService;
import com.cy.xtgl.domain.QyJsDomain;

public interface WlglptCommonService extends BaseService {
	
	/**
	 * ���ڻ�ȡ����ҵ��Ĺ������񷽷�
	 * @param domain
	 * @throws Exception
	 */
	public void getCommonList(WlglptCommonDomain domain, UserDomain userDomain) throws Exception;

	/**
	 * 
	* @Description: ���ݴ���Ĺ�˾�����������һ����˾
	* @Note
	* @author 
	* @since 2013-3-5
	* @param domain
	* @throws Exception
	 */
	public void getBmList(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: ���ݴ���Ĺ�˾�����������һ����λ
	* @Note
	* @author 
	* @since 2013-3-5
	* @param domain
	* @throws Exception
	 */
	public void getGwList(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * ������ҵ���������ȡ��ҵ�������
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getQyJbdmByJgbm(String jgbm) throws Exception;
	
	/**
	 * ����һ���������룬�������������ϼ���Ҳ�������ϼ��������ϼ�...�����еĽ�ɫ
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public List<QyJsDomain> getJsListByJgbm(String jgbm) throws Exception;
	
	/**
	 * ����һ���������룬���������ϼ���������
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getSjJgbmByJgbm(String jgbm) throws Exception;
	
	/**
	 * ȡ��ǰ����Ա���ڹ�˾��ά���ķְ���
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryCurrentFbs(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ȡ������ά������Ŀά��
	 * @param baseDomain
	 * @throws Exception
	 */
	public void queryWs(BaseBusinessDomain baseDomain) throws Exception;
}
