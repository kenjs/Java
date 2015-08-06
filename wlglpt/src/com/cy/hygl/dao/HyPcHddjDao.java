package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcHddjDomain;
import com.cy.hygl.domain.HyPcHwxxDomain;

/**
 * The DAO for 货运-派车-回单.
 * 
 * @author HJH
 */
public interface HyPcHddjDao extends ExtendDao {
	/**
	 * 回单批量登记
	 * @param domain
	 * @throws Exception
	 */
	public void plSave(BaseBusinessDomain domain) throws Exception ;
	
	public void callProHddjHxcl(String hdDjxh, UserDomain userDomain) throws Exception;
	
	public HyPcHwxxDomain queryJsPcHwxxByPcWfhXh(String pcDjxh, String wfhDjxh) throws Exception;
	/**
	 * 得到物流损失货物数量
	 * @param pcDjxh
	 * @param wfhDjxh
	 * @return
	 * @throws Exception
	 */
	public HyPcHddjDomain getWlss(String pcDjxh, String wfhDjxh) throws Exception;
	
	public HyPcHddjDomain getZHwSl(String pcDjxh, String wfhDjxh) throws Exception;
	
	public String plJs(String pcDjxh, String wfhDjxh) throws Exception;
	
	public void saveWlssDj(HyPcHddjDomain domain, UserDomain userDomain) throws Exception;
	
}
