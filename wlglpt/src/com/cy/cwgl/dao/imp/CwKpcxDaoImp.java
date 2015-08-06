package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwKpcxDao;
import com.cy.cwgl.domain.CwKpcxDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;

/**
 * The DAOIMP for 财务-发票开票登记
 * 
 * @author LYY
 */

@Repository
public class CwKpcxDaoImp implements CwKpcxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwKpcxDomain domain = (CwKpcxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		List<BaseBusinessDomain> dataList = null;
		
		//调用存储过程检索数据
		map.put("jgbm", domain.getSsJgbm());
		map.put("khDjxh", domain.getKhDjxh());
		map.put("fpdm", domain.getFpdm());
		map.put("fphmQ", domain.getFphmQ());
		map.put("fphmZ", domain.getFphmZ());
		map.put("kprqQ", domain.getRqQ());
		map.put("kprqZ", domain.getRqZ());
		map.put("start", page.getCurPageNo());
		map.put("pagSize", new Integer(pagSize));
		map.put("pageCount", new Integer(0));
		map.put("pageCurcount", new Integer(0));
		map.put("dataList", "");
		businessSqlMapClientTemplate.queryForObjectByCurr("cwKpcx", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwKpcxDomain domain = (CwKpcxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		List<BaseBusinessDomain> dataList = null;
		
		//调用存储过程检索数据
		map.put("jgbm", domain.getSsJgbm());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("fpdm", domain.getFpdm());
		map.put("fphmQ", domain.getFphmQ());
		map.put("fphmZ", domain.getFphmZ());
		map.put("kprqQ", domain.getRqQ());
		map.put("kprqZ", domain.getRqZ());
		map.put("start", 1);
		map.put("pagSize", 99999);
		map.put("pageCount", new Integer(0));
		map.put("pageCurcount", new Integer(0));
		map.put("dataList", "");
		businessSqlMapClientTemplate.queryForObjectByCurr("cwKpcx", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		return dataList;
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain) throws Exception {
		return null;
	}

	public void saveDomain(BaseBusinessDomain domain, UserDomain user) throws Exception {
		
	}

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		
	}

}
