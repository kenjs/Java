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
import com.cy.common.bo.XyjsSrdz;
import com.cy.hygl.dao.XyjsSrdzDao;
import com.cy.hygl.domain.XyjsSrdzDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;

/**
 * The DAOIMP for 下游结算-收入对帐.
 * 
 * @author HJH
 */

@Repository
public class XyjsSrdzDaoImp implements XyjsSrdzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("xyDjxh", domain.getXyDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("ddbh", domain.getDdbh());
		map.put("hwSl", domain.getHwSl());
		map.put("hdbh", domain.getHdbh());
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		map.put("dzztDm", domain.getDzztDm());
		map.put("fylbDm", domain.getFylbDm());
		map.put("qdDjxh", domain.getQdDjxh());
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getXyjsSrdzRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain) baseDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("xyDjxh", domain.getXyDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("ddbh", domain.getDdbh());
		map.put("hwSl", domain.getHwSl());
		map.put("hdbh", domain.getHdbh());
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		map.put("dzztDm", domain.getDzztDm());
		map.put("fylbDm", domain.getFylbDm());
		map.put("qdDjxh", domain.getQdDjxh());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzAll", map);
		return dataList;
	}
	
	public void savePldz(XyjsSrdzDomain domain,UserDomain user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fylbDm", domain.getFylbDm());
		map.put("ssJgbm", user.gsbm);
		map.put("dzrCzyDjxh", user.czyDjxh);
		map.put("dzJgbm", user.bmbm);
		map.put("jsDjxhs", domain.getYwDjxhs());
		
		businessSqlMapClientTemplate.insert("savePldz", map);
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain) baseDomain;
		XyjsSrdz bo = new XyjsSrdz();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		//XyjsSrdzDomain dom = (XyjsSrdzDomain) this.getDomainByKey(domain);

		BeanUtils.copyProperties(bo, domain);
		if (bo.getDzje() != 0.0) {
			bo.setDzCybz("Y");
		}else {
			bo.setDzCybz("N");
		}
		if(StringUtils.isNotBlank(domain.getDzDjxh())){
			businessSqlMapClientTemplate.update("updateXyjsSrdzByKey", bo);
		}else{
			bo.setYxbz("Y");
			bo.setDzJgbm(user.bmbm);
			bo.setDzrCzyDjxh(user.czyDjxh);
			bo.setDzrq(SysDateUtil.getSqlDate());
			bo.setSpbz("N");
			bo.setSsJgbm(user.gsbm);

			businessSqlMapClientTemplate.insert("insertXyjsSrdz", bo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qdDjxh", bo.getQdDjxh());
		businessSqlMapClientTemplate.update("updateXyjsSrdzQdDzje", map);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("dzDjxh", domain.getDzDjxh());

		domain = (XyjsSrdzDomain)businessSqlMapClientTemplate.queryForObject("selectXyjsSrdzByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", domain.getDzDjxh());

		businessSqlMapClientTemplate.update("deleteXyjsSrdzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getDzDjxh())){
			XyjsSrdzDomain dom = (XyjsSrdzDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	public XyjsSrdzDomain initDzxxByJsxx(String ywDjxh, String ywMxXh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);
		
		XyjsSrdzDomain dom = (XyjsSrdzDomain)businessSqlMapClientTemplate.queryForObject("initDzxxByJsxx", map);
		return dom;
	}
	
	@SuppressWarnings("unchecked")
	public List<XyjsSrdzQdDomain> queryDzqdList(XyjsSrdzDomain domain,UserDomain user) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ssJgbm", user.gsbm);
		map.put("fylbDm", domain.getFylbDm());
		
		List<XyjsSrdzQdDomain> dzqdList = businessSqlMapClientTemplate.queryForList("queryDzqdList", map);
		return dzqdList;
	}
}
