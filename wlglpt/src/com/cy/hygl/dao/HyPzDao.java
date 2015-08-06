package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyPzDomain;
import com.cy.hygl.domain.HyPzHwxxDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;

/**
 * The DAO for 货运-配载.
 * 
 * @author HJH
 */
public interface HyPzDao extends ExtendDao {

	/**
	 * 
	* @Description: 将派车时选择的货物信息保存到临时表，每个派车单对应一个临时序号
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @return
	* @throws Exception
	 */
	public void saveWfhxx4Pz(HyPzDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 从临时表中删除对应的未发货信息 
	* @Note
	* @author 
	* @since 2013-3-12
	* @param domain
	* @throws Exception
	 */
	public void deleteWfhxxTmp4Pz(HyPzDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 检索该配载单所关联的配载——货物信息
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @return
	* @throws Exception
	 */
	public List<HyTydWfhxxDomain> queryPzHwxxByPzXh(HyPzDomain domain) throws Exception;
	
	public String selectPchwLsxh() throws Exception;
	
	public List<BaseBusinessDomain> onQingdan(HyPzDomain domain)throws Exception;
	
	public String queryZgsMc(UserDomain user) throws Exception;
	
	public List<HyTydWfhxxDomain> viewPz(HyPzDomain domain) throws Exception;
	
	public String getHzmcByHzJgbm(String hzJgbm) throws Exception;
	
	public String getClxhByClxhWhxh(String clxhwhDjxh) throws Exception;
}
