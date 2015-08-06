package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyJcbmJldw;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.zygl.dao.QyJcbmJldwDao;
import com.cy.zygl.domain.QyJcbmJldwDomain;

/**
 * The DAOIMP for 企业-基础编码-计量单位.
 * 
 * @author HaoY
 */

@Repository
public class QyJcbmJldwDaoImp implements QyJcbmJldwDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jldwFlDm", domain.getJldwFlDm());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyJcbmJldwRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyJcbmJldwPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jldwFlDm", domain.getJldwFlDm());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyJcbmJldwAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		QyJcbmJldw bo = new QyJcbmJldw();
		//根据主键查询对象
		QyJcbmJldwDomain dom = (QyJcbmJldwDomain) this.getDomainByKey(domain);
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
		
			bo.setQybz(domain.getQybz());
			
			bo.setJbdwbz(domain.getJbdwbz());
			bo.setHsbl(domain.getHsbl());

			businessSqlMapClientTemplate.update("updateQyJcbmJldwByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSsJgbm(user.getZgsbm());
			bo.setDjJgbm(user.getZgsbm());
			bo.setYxbz("Y");

			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyJcbmJldw", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jldwDm", domain.getJldwDm());

		domain = (QyJcbmJldwDomain)businessSqlMapClientTemplate.queryForObject("selectQyJcbmJldwByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jldwDm", domain.getJldwDm());

		businessSqlMapClientTemplate.update("deleteQyJcbmJldwByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			QyJcbmJldwDomain dom = (QyJcbmJldwDomain) this.getDomainByKey(domain);
			if(dom != null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<QyJcbmJldwDomain> queryAllJldw(BaseBusinessDomain baseDomain)
			throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jldwFlDm", domain.getJldwFlDm());
		// 检索数据
		List<QyJcbmJldwDomain>  jldwList = businessSqlMapClientTemplate.queryForList("selectQyJcbmJldwAll", map);
		return jldwList;
	}
}
