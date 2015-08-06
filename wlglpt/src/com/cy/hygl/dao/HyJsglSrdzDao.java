package com.cy.hygl.dao;

import java.util.List;
import java.util.Map;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyJsglSrdzDomain;
import com.cy.hygl.domain.JsSrdzDomain;
import com.cy.hygl.domain.JsSrdzDzcyDomain;

/**
 * The DAO for �������
 * 
 * @author HJH
 */
public interface HyJsglSrdzDao extends ExtendDao {
	
	/**
	 * ��ʼ������-�������
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsSrdzByJsDjxh(JsSrdzDomain domain) throws Exception;
	
	/**
	 * ����-�������-���ʲ���-��ʱ��List
	 * @param dzDjxh
	 * @return
	 * @throws Exception
	 */
	public List<JsSrdzDzcyDomain> queryDzcyTempList(String dzDjxh) throws Exception ;
	/**
	 * ����-�������-���ʲ���list
	 * @param dzDjxh
	 * @return
	 * @throws Exception
	 */
	public List<JsSrdzDzcyDomain> queryDzcyList(String dzDjxh) throws Exception;
	/**
	 * ������޸Ľ���-�������-���ʲ���
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzcyDomain(JsSrdzDzcyDomain domain, UserDomain user) throws Exception;
	/**
	 * ������޸Ľ���-�������-���ʲ���tempo\
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzcyTempDomain(JsSrdzDzcyDomain domain, UserDomain user) throws Exception;
	
	/**
	 * �鿴����-�������-���ʲ������jsDjxh
	 * @param baseDomain
	 * @throws Exception
	 */
	public void initDomainDzcyMx(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * ɾ������-�������-���ʲ����������(��ʽ��)
	 * @param dzDjxh
	 * @param xh
	 * @throws Exception
	 */
	public void deleteDzcyByKey(String dzDjxh,String xh) throws Exception;
	
	/**
	 *  ���ݶ��˵Ǽ����ɾ����Ӧ�Ķ��˲����¼
	 * @param dzDjxh
	 * @throws Exception
	 */
	public void deleteDzcyByDzDjxh(String dzDjxh) throws Exception;
	
	/**
	 * ɾ������-�������-���ʲ���-��ʱ�����dzDjxh
	 * @param dzDjxh
	 * @throws Exception
	 */
	public void deleteDzcyTempByDzDjxh(String dzDjxh) throws Exception;
	/**
	 * ɾ������-�������-���ʲ���-��ʱ���������
	 * @param dzDjxh
	 * @param xh
	 * @throws Exception
	 */
	public void deleteDzcyTempByKey(String dzDjxh, String xh) throws Exception ;
	/**
	 * ���˹���-�������-�������-�����������ʱ�����ٵ���PROD��
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void callHyglJsglSrdzDdHxcl(HyJsglSrdzDomain domain, UserDomain user) throws Exception;
	
	/**
	 * ���˹���-�������-�������(��������)-�����������ʱ�����ٵ���PROD��
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void callHyglJsglSrdzDdhwHxcl(HyJsglSrdzDomain domain, UserDomain user) throws Exception;
	/**
	 * ����㶩��������Ϣ
	 * @param jsDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsDdHwxxDomain(String jsDjxh) throws Exception;
	/**
	 * ������㶩��������Ϣ
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveJsDdHwxxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * ȡ������ʧ����
	 * @param ddDjxh
	 * @param xh
	 * @return
	 */
	public List<HyJsglSrdzDomain> getWlssxl(String ddDjxh,String xh);

	
	public Integer queryDzcyNextXh(String dzDjxh) throws Exception;
	
	public boolean checkSrdz(String jsDjxh) throws Exception;
	
	public void deleteJsDdHwxxDomainByKey(String jsDjxh) throws Exception;
	
	public BaseBusinessDomain getDomainDzcyByKey(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * ���ݽ���Ǽ���Ż�ȡ���˷�ʽ��Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JsSrdzDomain getDzInfo(String jsDjxh) throws Exception;

}
