package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyRyJs;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.xtgl.dao.YhjswhDao;
import com.cy.xtgl.domain.YhjswhDomain;


/**
 * 用户角色维护
 * 
 * @author HaoY
 * @since 
 */
@Repository
public class YhjswhDaoImp implements YhjswhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		YhjswhDomain yhjswhDomain = (YhjswhDomain) domain;
		Map<String,String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("czyDjxh", yhjswhDomain.getCzyDjxh());
		businessSqlMapClientTemplate.delete("deleteQyRyJsByKey", map);
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		YhjswhDomain domain = (YhjswhDomain) baseDomain;
		QyRyJs bo = new QyRyJs();
		
		BeanUtils.copyProperties(bo, domain);
		
		businessSqlMapClientTemplate.insert("insertQyRyJs", bo);
	}

	@SuppressWarnings("unchecked")
	public List<YhjswhDomain> queryJsMcList(String qyZcxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("qyZcxh", qyZcxh);
		List<YhjswhDomain> jsMcList =  businessSqlMapClientTemplate.queryForList("getYhJsPageByCzyDjxh", map);
		return jsMcList;
	}

	@SuppressWarnings("unchecked")
	public List<YhjswhDomain> queryYhMcList(BaseBusinessDomain domain,PageDomain page) throws Exception {
		YhjswhDomain yhjswhDomain = (YhjswhDomain) domain;
		Map<String,String> map = new HashMap<String, String>();
		
		int start = page.getStart();
		int pagSize = page.getPageSize();
		
		//设置查询条件
		map.put("jgbm", yhjswhDomain.getGsbm());
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getYhJsRowCount", map))).intValue();	
		page.setTotalRecords(totalRecords);
		
		List<YhjswhDomain> dataList =  businessSqlMapClientTemplate.queryForList("getYhMcPage", map,start,pagSize);
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<YhjswhDomain> queryChoosedJs(BaseBusinessDomain domain) throws Exception {
		YhjswhDomain yhjswhDomain = (YhjswhDomain) domain;
		Map<String,String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("czyDjxh", yhjswhDomain.getCzyDjxh());
		List<YhjswhDomain> jsList =  businessSqlMapClientTemplate.queryForList("getYhChoosedJsByCzyDjxh", map);
		return jsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<YhjswhDomain> queryYhMcList(BaseBusinessDomain domain) throws Exception {
		YhjswhDomain yhjswhDomain = (YhjswhDomain) domain;
		Map<String,String> map = new HashMap<String, String>();
		
		//设置查询条件
		map.put("jbdm", yhjswhDomain.getJbdm());
		map.put("qyZcxh", yhjswhDomain.getQyZcxh());
		return businessSqlMapClientTemplate.queryForList("getYhMcPage", map);
	}
	
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception {
		
		return null;
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain) throws Exception {
		
		return null;
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		
		
	}

	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception {
		
		return null;
	}

}
