package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.XyjsSrdzQdMx;
import com.cy.hygl.dao.XyjsSrdzQdMxDao;
import com.cy.hygl.domain.XyjsSrdzQdMxDomain;

/**
 * The DAOIMP for 下游结算-收入对帐-清单-明细.
 * 
 * @author HJH
 */

@Repository
public class XyjsSrdzQdMxDaoImp implements XyjsSrdzQdMxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getXyjsSrdzQdMxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzQdMxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzQdMxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		XyjsSrdzQdMx bo = new XyjsSrdzQdMx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		//XyjsSrdzQdMxDomain dom = (XyjsSrdzQdMxDomain) this.getDomainByKey(domain);

		BeanUtils.copyProperties(bo, domain);
		
		businessSqlMapClientTemplate.insert("insertXyjsSrdzQdMx", bo);
	}
	
	public void saveDomainTemp(XyjsSrdzQdMxDomain domain, UserDomain user) throws Exception {
		XyjsSrdzQdMx bo = new XyjsSrdzQdMx();

		BeanUtils.copyProperties(bo, domain);
		businessSqlMapClientTemplate.insert("insertXyjsSrdzQdMxTemp", bo);
	}
	
	public void saveQdmxTempToFormal(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("qdDjxh", qdDjxh);
		
		businessSqlMapClientTemplate.insert("saveQdmxTempToFormal", map);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("ywMxXh", domain.getYwMxXh());

		domain = (XyjsSrdzQdMxDomain)businessSqlMapClientTemplate.queryForObject("selectXyjsSrdzQdMxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("ywMxXh", domain.getYwMxXh());

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdMxByKey", map);
	}
	
	public void deleteDomain(String qdDjxh, String ywDjxh, String ywMxXh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdMxByKey", map);
	}

	public void deleteDomainTemp(String qdDjxh, String ywDjxh, String ywMxXh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdMxTempByKey", map);
	}

	public void deleteQdmxTempByQddjxh(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		businessSqlMapClientTemplate.delete("deleteQdmxTempByQddjxh", map);
	}
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getQdDjxh())){
			XyjsSrdzQdMxDomain dom = (XyjsSrdzQdMxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
