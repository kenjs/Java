package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.XyjsPcHwxxDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;

/**
 * The DAO for 下游结算-收入对帐-清单.
 * 
 * @author HJH
 */
public interface XyjsSrdzQdDao extends ExtendDao {

	public List<XyjsPcHwxxDomain> queryJsxxMx(XyjsSrdzQdDomain domain, UserDomain user) throws Exception;
	
	public List<XyjsPcHwxxDomain> queryQdMx(XyjsSrdzQdDomain domain, UserDomain user) throws Exception;
	
	public String queryXymcByXh(String xyDjxh) throws Exception;
	
	public XyjsSrdzQdDomain queryHjjeCalculate(XyjsSrdzQdDomain domain, UserDomain userDomain) throws Exception;
	
	public void sendDzqd(XyjsSrdzQdDomain domain, UserDomain userDomain) throws Exception;
	
	public List<BaseBusinessDomain> querySydzqdConf(XyjsSrdzQdDomain domain,UserDomain userDomain) throws Exception;
	
	public void updateSydzqdQr(XyjsSrdzQdDomain domain) throws Exception;
	
	public List<BaseBusinessDomain> downloadQdQr(XyjsSrdzQdDomain domain,UserDomain userDomain) throws Exception;
	
	public void doCwInfo(XyjsSrdzQdDomain domain) throws Exception;
}
