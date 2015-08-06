package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for ����-����Ǽ�
 * 
 * @author HCM
 */
public interface CwYsyfSrdjDao  extends ExtendDao {
	/**
	 * Ӧ��Ӧ����Ϣ
	 * @param ysyfDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getYsyfSrdj(String ysyfDjxh) throws Exception;
    /**
     * ������Ϣlist
     * @param ysyfDjxh
     * @return
     * @throws Exception
     */
	public List<BaseBusinessDomain> getSrdjList(String ysyfDjxh) throws Exception;
	/**
	 * ɾ��������Ϣ
	 * @param ysyfDjxh
	 * @throws Exception
	 */
	public  void deleteSrdj(String srDjxh)throws Exception ;
	/**
	 * ��������
	 * @param srDjxh
	 * @param bz
	 * @throws Exception
	 */
	public void callPHyglCwglSrdjHxcl(String srDjxh, String bz) throws Exception;
	/**
	 * ���㷽���
	 * @return
	 * @throws Exception
	 */
	public List<CwYsyfSrdjDomain> getLbList() throws Exception;
	/**
	 * ���㷽����
	 * @return
	 * @throws Exception
	 */
	public List<CwYsyfSrdjDomain> getMcList(String ssJgbm,String yfjsfDm) throws Exception;
	/**
	 * ����
	 * @param domain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain domain)throws Exception;

	
	public void checkZfFs(CwYsyfSrdjDomain domain)throws Exception;
	
	public void initCwYsJe(CwYsyfSrdjDomain domain,UserDomain userDomain) throws Exception;

	
	public BaseBusinessDomain getYsyfSrdjByYwDjxh(String ywDjxh) throws Exception;
	
	public int checkSrdj(String yfjsfDm,String yfjsfDjxh) throws Exception;
	
	/**
	 * ɾ�������˶��˵Ĺ�˾�е���������ʧ��Ӧ��Ӧ��Ӧ����Ϣ
	 * @param ywDjxh
	 * @throws Exception
	 */
	public  void deleteYsyfXx(String ywDjxh)throws Exception;
	
	/**
	 * �ָ��������˱�ɾ����Ӧ��Ӧ����Ϣ
	 * @param ywDjxh
	 * @throws Exception
	 */
	public  void updateYsyfXx(String ywDjxh)throws Exception;
	/**
	 * ���ɾ��
	 * @param bz 0 ��Ӧ��������Ǽ� 1��Ӧ����Ӧ��Ӧ��
	 * @throws Exception
	 */
    public void checkDelete(String xh,int bz)throws Exception;

}
