package com.cy.jcgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.jcgl.dao.JcJymlbDao;
import com.cy.jcgl.domain.JcYxsjbDomain;

/**
 * 
 */

@Repository
public class JcJymlbDaoImp implements JcJymlbDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		JcYxsjbDomain domain = (JcYxsjbDomain)baseDomain;
		//分页相关
		PageDomain page = domain.getPage();
		
		List<BaseBusinessDomain> dataList = null;
		//设置查询条件
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("fcrqS", domain.getFcrqS());
		map.put("fcrqZ", domain.getFcrqZ());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("khmc", SysEncodeUtil.UTF82ISO(domain.getFhrMc()));		
		map.put("hdbh", domain.getDdbh());
		
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryMlbPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		JcYxsjbDomain domain = (JcYxsjbDomain)baseDomain;
		//分页相关
		PageDomain page = domain.getPage();
		
		List<BaseBusinessDomain> dataList = null;
		//设置查询条件
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("fcrqS", domain.getFcrqS());
		map.put("fcrqZ", domain.getFcrqZ());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("khmc", SysEncodeUtil.GBK2ISO(domain.getFhrMc()));		
		map.put("hdbh", domain.getDdbh());
		
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryMlbPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain) throws Exception {
		return null;
	}

}
