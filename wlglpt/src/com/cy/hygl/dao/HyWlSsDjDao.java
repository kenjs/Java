package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.bo.HyWlssdjMx;
import com.cy.common.bo.HyWlssdjZp;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyWlssdjMxDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.zygl.domain.QyYlClxxDomain;

/**
 * The DAO for ���ȳɱ����.
 * 
 * @author HJH
 */
public interface HyWlSsDjDao extends ExtendDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception;

	/**
	 * ������޸�
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;
	/**
	 * 
	* @Description: ���ڱ����ϴ�ͼƬ��Ϣ��domain
	* @author FWC
	* @since 2013-10-12
	* @throws Exception
	 */
	public void saveFileDomain(HyZyglFydjDomain domain,UserDomain user)throws Exception;
	/**
	 * 
	* @Description: ����ajax����ʱ��ѯ��Ƭ���
	* @author FWC
	* @since 2013-10-14
	* @param wlssdjxh
	* @throws Exception
	 */
	public List<HyWlssdjZp> queryPhoto(String wlssdjxh)throws Exception;
	public String deletePhoto(String zpscxh,HyZyglFydjDomain domain,UserDomain user)throws Exception;
	public List<String> deletePhotoes(HyZyglFydjDomain domain, UserDomain userDomain)throws Exception;

	public List<HyZyglFydjDomain> getHw(HyZyglFydjDomain domain,UserDomain userDomain)throws Exception;

	public int checkWlDj(HyZyglFydjDomain domain)throws Exception;
	/**
	 * �ɳ���Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getHyPcAllByDjxh(String id) throws Exception;
	
	public BaseBusinessDomain selectHyPcHwxxWhenWlss(BaseBusinessDomain domain) throws Exception;
	
	public List<HyWlssdjMxDomain> getHyWlssdjMxList(String wlssDjxh) throws Exception;
	/**
	 * ������Ϣ
	 * @param bo
	 * @throws Exception
	 */
	public void saveHyWlssdjMx(HyWlssdjMx bo) throws Exception;
	public int getXhOfWlssdjMx(String wlssDjxh) throws Exception;
	public void deleteWlssdjMx(String wlssDjxh,String xh) throws Exception;
	public double getWlssdjJe(String wlssDjxh) throws Exception;
	public void updateWlssdjJe(String wlssDjxh,String je) throws Exception;
	public void wlssDjHxcl(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
	public List<HyZyglFydjDomain> selectSjList(String ddDjxh,String xh) throws Exception;
	
	/**
	 * ��ȡ��εǼǼ�¼
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> getSsList(HyZyglFydjDomain domain,UserDomain userDomain) throws Exception;
}
