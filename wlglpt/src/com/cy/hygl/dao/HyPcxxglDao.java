package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyQingDanDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.zygl.domain.QyXxzjDjxxDomain;

/**
 * The SERVICEIMP for ����-�ɳ���Ϣ����
 * time  2013-3-4
 * @author yw
 */

public interface HyPcxxglDao extends ExtendDao {
	
	/**
	 * 
	* @Description: ���ɳ�ʱѡ��Ļ�����Ϣ���浽��ʱ��ÿ���ɳ�����Ӧһ����ʱ���
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @return
	* @throws Exception
	 */
	public void saveWfhxx4Pc(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: ����ʱ����ɾ����Ӧ��δ������Ϣ 
	* @Note
	* @author 
	* @since 2013-3-12
	* @param domain
	* @throws Exception
	 */
	public void deleteWfhxxTmp4Pc(HyPcxxglDomain domain) throws Exception;
	
	/**
	 *  ɾ���ɳ�������Ϣ���һ����¼����yxbz='N' �����°汾��
	 * @param pcDjxh
	 * @param wfhDjxh
	 * @param bbh
	 * @throws Exception
	 */
	public void deleteHyPcHwxxByKey(String pcDjxh, String wfhDjxh, String bbh) throws Exception;
	
	/**
	 * 
	* @Description: ���ɳ�����Ӧ�Ļ��� ��Ϣ���ݵ���ʱ����
	* @Note
	* @author 
	* @since 2013-3-12
	* @param domain 
	* @throws Exception
	 */
	public void bakPcHwxx2Tmp(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: �������ɳ������������ɳ�����������Ϣ
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @return
	* @throws Exception
	 */
	public List<HyTydWfhxxDomain> queryPcHwxxByPcXh(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: �����ɳ���Ϣʱ���ô洢���� P_HYGL_DDGL_PCXX_HXCL
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @throws Exception
	 */
	public void callPHyglDdglPcxxHxcl(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: ɾ���ɳ���Ϣʱ���ô洢���� P_HYGL_DDGL_PCXX_DELETE
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @throws Exception
	 */
	public void callPHyglDdglPcxxDelete(HyPcxxglDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: �����ɳ�������ʱ���ɾ����ʱ������
	* @Note
	* @author 
	* @since 2013-3-12
	* @param lsXh
	* @throws Exception
	 */
	public void deletePcHwxxTmpByLsXh(String lsXh) throws Exception;
	
	/**
	 * 
	* @Description: ɾ�����ɳ������������ɳ�����������Ϣ
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @throws Exception
	 */
	public void deletePcHwxxByPcXh(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * ȡ�ò�����ά���ĳ���������������������
	 * @param bmbm
	 * @param clsxDm
	 * @return
	 * @throws Exception
	 */
	public Integer queryQyClxxCountBySsbmbm(String bmbm, String clsxDm) throws Exception;
	
	/**
	 * �����������������ɳ����˷���Ϣ
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public HyPcxxglDomain queryHyPcYfxxByKey(String pcDjxh) throws Exception;
	
	public void queryPcxxSjsInitVal(HyPcxxglDomain domain) throws Exception;
	
	public List<DmbGgDomain> queryPchwClfsdmList(String pcfsDm) throws Exception;
	
	public List<HyTydWfhxxDomain> queryWfhxx(HyPcxxglDomain domain) throws Exception;
	
	public List<HyQingDanDomain> qingdan(HyPcxxglDomain domain) throws Exception;
	//public Integer queryExistWfhxxCount(List<String> wfhDjxhs) throws Exception;
	
	public void updatePchwxxBbh(HyPcxxglDomain domain) throws Exception;
	public String queryZgs(UserDomain userDomain) throws Exception;
	/**
	 * ����wfhDjxh ȡȷ�ϵ����ͷ�
	 * @param wfhDjxh
	 * @return
	 * @throws Exception
	 */
	public Double getQrPsf(String wfhDjxh) throws Exception;
}
