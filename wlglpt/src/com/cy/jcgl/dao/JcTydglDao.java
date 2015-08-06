package com.cy.jcgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.hygl.domain.HyClgzDomain;
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcYfZfxxDomain;

/**
 * The DAO for 决策-托运单管理.
 * 
 * @author LYY
 */
public interface JcTydglDao extends ExtendDao {

	public List<JcPcxxglDomain> queryJcTydPcxx(String ddDjxh) throws Exception;
	
	public List<HyClgzDomain> queryJcSjcxClgzxx(Long ddDjxh, String pcDjxh) throws Exception;
	public List<JcYfZfxxDomain> queryJcYfZfxx(Long ddDjxh, String pcDjxh) throws Exception;
	
	public List<HyWlSsDjGlDomain> queryJcWlssxx(Long ddDjxh) throws Exception;
	
	public List<HyWlSsDjGlDomain> querySsmx(String wlssDjxh) throws Exception;
	
}
