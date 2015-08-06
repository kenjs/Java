package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;

/**
 * The DAO for 货运-托运单-未发货(提货)信息.
 * 
 * @author HJH
 */
public interface HyTydWfhxxDao extends ExtendDao {

	public List<DmbGgDomain> queryPchwClfsdmList(String pcfsDm) throws Exception;
	
	public void updateWfhhwZt(String wfhDjxh,String hwzt) throws Exception;
	
	public void saveXgRz(HyTydWfhxxDomain domain,UserDomain userDomain) throws Exception;
	
}
