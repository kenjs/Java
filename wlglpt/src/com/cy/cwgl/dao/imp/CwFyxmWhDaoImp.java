package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwFyxmWh;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwFyxmWhDao;
import com.cy.cwgl.domain.CwFyxmWhDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 财务-费用项目-维护.
 * 
 * @author HJH
 */

@Repository
public class CwFyxmWhDaoImp implements CwFyxmWhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryListById(BaseBusinessDomain baseDomain,UserDomain user)
			throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getFyxmMc())) {
			String lb = SysEncodeUtil.UTF82ISO(domain.getFyxmMc());
			map.put("xmmc", "%" + lb + "%");
		}
		map.put("zgs", user.getZgsbm());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer) (businessSqlMapClientTemplate
				.queryForObject("getCwFyxmWhRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectCwFyxmWhPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getFyxmMc())) {
			String lb = SysEncodeUtil.UTF82ISO(domain.getFyxmMc());
			map.put("xmmc", "%" + lb + "%");
		}
		map.put("zgs", domain.getSsJgbm());
		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectCwFyxmWhAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain) baseDomain;
		CwFyxmWh bo = new CwFyxmWh();
		CwFyxmWhDomain dom = null;
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		if (StringUtils.isNotBlank(domain.getCwDjxh())) {
			dom = (CwFyxmWhDomain) this.getDomainByKey(domain);
		}

		if (dom != null) {
			BeanUtils.copyProperties(bo, dom);
			bo.setFylbCwDjxh(domain.getFylbCwDjxh());
			bo.setFyxmMc(domain.getFyxmMc());
			bo.setSplcXmflDjxh(domain.getSplcXmflDjxh());
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateCwFyxmWhByKey", bo);
		} else {
			BeanUtils.copyProperties(bo, domain);
			bo.setSsJgbm(user.getZgsbm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.insert("insertCwFyxmWh", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwFyxmWhDomain) businessSqlMapClientTemplate.queryForObject(
				"selectCwFyxmWhByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("cwDjxh", domain.getCwDjxh());

		businessSqlMapClientTemplate.update("deleteCwFyxmWhByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getCwDjxh())) {
			CwFyxmWhDomain dom = (CwFyxmWhDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
