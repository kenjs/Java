package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcHwqsDomain;

/**
 * The DAO for 货运-派车-货物签收.
 * 
 * @author HJH
 */
public interface HyPcHwqsDao extends ExtendDao {

	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain, UserDomain userDomain)  throws Exception;
	
	public Long callPPcHwqsHxcl(String hwqsDjxh, String pcDjxh, UserDomain userDomain) throws Exception;
	
	public void initPsfMx(BaseBusinessDomain baseDomain) throws Exception;
	
	public void savePsf(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
	public List<BaseBusinessDomain> downloadList1(BaseBusinessDomain baseDomain,UserDomain userDomain) throws Exception;
	
	public void callPcHwQsDel(String hwqsDjxh,String pcDjxh, String wlssDjxh) throws Exception;
	
	public HyPcHwqsDomain checkWlssSfDj(HyPcHwqsDomain domain) throws Exception;
	
	public void insertHwZtInfo(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
}
