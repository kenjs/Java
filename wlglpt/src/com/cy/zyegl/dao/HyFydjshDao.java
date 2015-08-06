package com.cy.zyegl.dao;

import java.util.List;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 货运-费用登记审核.
 * 
 * @author HJH
 */
public interface HyFydjshDao extends BaseDao {

	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain,UserDomain userDomain) throws Exception;

}
