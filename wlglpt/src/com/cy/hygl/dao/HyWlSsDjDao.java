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
 * The DAO for 调度成本审核.
 * 
 * @author HJH
 */
public interface HyWlSsDjDao extends ExtendDao {

	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * 下载
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception;

	/**
	 * 保存或修改
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;
	/**
	 * 
	* @Description: 用于保存上传图片信息的domain
	* @author FWC
	* @since 2013-10-12
	* @throws Exception
	 */
	public void saveFileDomain(HyZyglFydjDomain domain,UserDomain user)throws Exception;
	/**
	 * 
	* @Description: 用于ajax调用时查询照片序号
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
	 * 派车信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getHyPcAllByDjxh(String id) throws Exception;
	
	public BaseBusinessDomain selectHyPcHwxxWhenWlss(BaseBusinessDomain domain) throws Exception;
	
	public List<HyWlssdjMxDomain> getHyWlssdjMxList(String wlssDjxh) throws Exception;
	/**
	 * 货物信息
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
	 * 获取多次登记记录
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> getSsList(HyZyglFydjDomain domain,UserDomain userDomain) throws Exception;
}
