package com.cy.bggl.dao.imp;

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
import com.cy.common.bo.BgDddl;
import com.cy.bggl.dao.BgDddlDao;
import com.cy.bggl.domain.BgDddlDomain;

/**
 * The DAOIMP for 办公-单点登录.
 * 
 * @author HaoY
 */

@Repository
public class BgDddlDaoImp implements BgDddlDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgDddlDomain domain = (BgDddlDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("mc", domain.getMc());
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgDddlRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgDddlPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("mc", domain.getMc());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgDddlAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgDddlDomain domain = (BgDddlDomain) baseDomain;
		BgDddl bo = new BgDddl();

		if(domain != null && !"".equals(domain.getDddlDjxh().trim())){
			//根据主键查询对象
			BgDddlDomain dom = (BgDddlDomain) this.getDomainByKey(domain);
			BeanUtils.copyProperties(bo, dom);
			
			bo.setJgbm(domain.getJgbm());
			bo.setMc(domain.getMc());
			bo.setUrl(domain.getUrl());
			bo.setDlfsDm(domain.getDlfsDm());
			bo.setXjgxbz(domain.getXjgxbz());

			businessSqlMapClientTemplate.update("updateBgDddlByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertBgDddl", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("dddlDjxh", domain.getDddlDjxh());

		domain = (BgDddlDomain)businessSqlMapClientTemplate.queryForObject("selectBgDddlByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dddlDjxh", domain.getDddlDjxh());

		businessSqlMapClientTemplate.update("deleteBgDddlByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getDddlDjxh())){
			BgDddlDomain dom = (BgDddlDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
