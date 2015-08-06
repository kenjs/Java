package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.HyMbTydHwmx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysSqlInUtil;
import com.cy.hygl.dao.HyTydMbHwmxDao;
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyMbTydHwmxDomain;
import com.cy.hygl.domain.HyTydHwmxDomain;

/**
 * The DAOIMP for 货运-托运单-货物明细.
 * 
 * @author HJH
 */

@Repository
public class HyTydMbHwmxDaoImp implements HyTydMbHwmxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	@Autowired
	private WlglptCommonDao commonDao;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer) (businessSqlMapClientTemplate.queryForObject("getHyTydHwmxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectHyTydHwmxPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectHyTydHwmxAll", map);
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<HyMbTydHwmxDomain> queryHwmxByTydXh(Long ddDjxh, String tempFlag) throws Exception {
		if (ddDjxh == null || ddDjxh.longValue() <= 0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mbDjxh", ddDjxh);
		if ("Y".equals(tempFlag)) {
			map.put("tableName", "HY_DD_HWMX_TEMP");
		}else {
			map.put("tableName", "HY_MB_DD_HWMX");
		}
		
		List<HyMbTydHwmxDomain> dataList = businessSqlMapClientTemplate.queryForList("queryHwmxByTydMbXh", map);
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyMbTydHwmxDomain domain = (HyMbTydHwmxDomain) baseDomain;
		HyMbTydHwmx bo = new HyMbTydHwmx();
		Map<String,Object> map = new HashMap<String, Object>();
		
		BeanUtils.copyProperties(domain, bo);
		bo.setYxbz("Y");
		
		if (StringUtils.isBlank(domain.getMbDjxh())) {
			String lsxh = commonDao.selectSequence("SEQ_MB_DJXH");
			domain.setMbDjxh(lsxh);
			bo.setMbDjxh(lsxh);
		}
		
		bo.setPsbz("N");
		
		if ("Y".equals(domain.getTempFlag())) {
			//保存数据到临时表
			if (StringUtils.isNotBlank(bo.getXh())) {
				businessSqlMapClientTemplate.update("updateHyMbTydHwmxTempByKey", bo);
			}else {
				businessSqlMapClientTemplate.insert("insertHyMbTydHwmxTemp", bo);
				domain.setXh(bo.getXh());
			}
		}else if ("N".equals(domain.getTempFlag())) {
			//直接保存到正式表中
			if(StringUtils.isNotBlank(bo.getXh())){
				businessSqlMapClientTemplate.update("updateHyMbTydHwmxByKey", bo);
			}else{
				businessSqlMapClientTemplate.insert("insertHyMbTydHwmx", bo);
				domain.setXh(bo.getXh());
			}
		}
	}
	
	public void saveHwxxToFormal(HyMbTydDomain domain) throws Exception {
		
		businessSqlMapClientTemplate.insert("saveMbHwxxToFormal", domain);
	}
	
	public void deleteHyMbTydHwxxTempByDdDjxh(Long ddDjxh) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键查询条件
		map.put("mbDjxh", ddDjxh);
		businessSqlMapClientTemplate.delete("deleteHyMbTydHwxxTempByDdDjxh", map);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyMbTydHwmxDomain domain = (HyMbTydHwmxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("mbDjxh", domain.getMbDjxh());
		map.put("xh", domain.getXh());
		if ("Y".equals(domain.getTempFlag())) {
			map.put("tableName", "HY_DD_HWMX_TEMP");
		}else {
			map.put("tableName", "HY_MB_DD_HWMX");
		}

		domain = (HyMbTydHwmxDomain)businessSqlMapClientTemplate.queryForObject("selectHyMbTydHwmxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyMbTydHwmxDomain domain = (HyMbTydHwmxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("mbDjxh", domain.getMbDjxh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteHyMbTydHwmxByKey", map);
	}

	public void deleteHwxxByXhs(String ddDjxh, List<String> hwXhs, String tempFlag) throws Exception {
		String[] xhs = new String[hwXhs.size()];
		System.arraycopy(hwXhs.toArray(), 0, xhs, 0, xhs.length);
		String xhsIn = SysSqlInUtil.getParameterArray(xhs, "XH");
		
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("mbDjxh", ddDjxh);
		map.put("xhsIn", xhsIn);
		if ("Y".equals(tempFlag)) {
			map.put("tableName", "HY_DD_HWMX_TEMP");
		}else {
			map.put("tableName", "HY_MB_DD_HWMX");
		}
		
		businessSqlMapClientTemplate.delete("deleteMbHwxxByXhs", map);
	}
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyMbTydHwmxDomain domain = (HyMbTydHwmxDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getMbDjxh())) {
			domain.setDdDjxh(Long.parseLong(domain.getMbDjxh()));
			HyMbTydHwmxDomain dom = (HyMbTydHwmxDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(dom, domain);
			}
		}

	}
}
