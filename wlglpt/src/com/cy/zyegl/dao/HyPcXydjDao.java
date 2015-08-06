package com.cy.zyegl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;
import com.cy.zyegl.domain.HyPcXydjDomain;

/**
 * The DAO for 货运-派车-协议登记.
 * 
 * @author HJH
 */
public interface HyPcXydjDao extends ExtendDao {

	public HyPcXydjDomain initXydj(String pcDjxh) throws Exception;
	
	public void callProXydjHxcl(String pcDjxh, UserDomain userDomain) throws Exception;
	
	public void updatePcXydjSlbgbz(HyPcHwxxXydjDomain hyPcHwxxXydjDomain) throws Exception;
	
	public List<BaseBusinessDomain> queryHyPcXybdList(String pcDjxh)  throws Exception;
	/**
	 * 根据派车登记序号 找对应协议登记
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkXydj(String pcDjxh)throws Exception;
}
