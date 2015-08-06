package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.HyTydHwmx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysSqlInUtil;
import com.cy.hygl.dao.HyTydHwmxDao;
import com.cy.hygl.domain.HyTydHwmxDomain;
import com.cy.hygl.domain.HyTydglDomain;

/**
 * The DAOIMP for 货运-托运单-货物明细.
 * 
 * @author HJH
 */

@Repository
public class HyTydHwmxDaoImp implements HyTydHwmxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	@Autowired
	private WlglptCommonDao commonDao;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
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
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyTydHwmxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyTydHwmxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyTydHwmxAll", map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<HyTydHwmxDomain> queryHwmxByTydXh(Long ddDjxh, String tempFlag) throws Exception {
		if (ddDjxh == null || ddDjxh.longValue() <= 0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ddDjxh", ddDjxh);
		if ("Y".equals(tempFlag)) {
			map.put("tableName", "HY_DD_HWMX_TEMP");
		}else {
			map.put("tableName", "HY_DD_HWMX");
		}
		
		List<HyTydHwmxDomain> dataList = businessSqlMapClientTemplate.queryForList("queryHwmxByTydXh", map);
		
		return dataList;
	}
	
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		HyTydHwmx bo = new HyTydHwmx();
		Map<String,Object> map = new HashMap<String, Object>();
		
		BeanUtils.copyProperties(domain, bo);
		bo.setYxbz("Y");
		
		if (StringUtils.isBlank(domain.getDdDjxh())) {
			String lsxh = commonDao.selectSequence("SEQ_DD_DJXH");
			domain.setDdDjxh(lsxh);
			bo.setDdDjxh(lsxh);
		}
		
		bo.setPsbz("N");
		
		if ("Y".equals(domain.getTempFlag())) {
			//保存数据到临时表
			if (StringUtils.isNotBlank(bo.getXh())) {
				businessSqlMapClientTemplate.update("updateHyTydHwmxTempByKey", bo);
			}else {
				businessSqlMapClientTemplate.insert("insertHyTydHwmxTemp", bo);
				domain.setXh(bo.getXh());
			}
		}else if ("N".equals(domain.getTempFlag())) {
			//直接保存到正式表中
			if(StringUtils.isNotBlank(bo.getXh())){
				businessSqlMapClientTemplate.update("updateHyTydHwmxByKey", bo);
			}else{
				businessSqlMapClientTemplate.insert("insertHyTydHwmx", bo);
				domain.setXh(bo.getXh());
			}
		}
	}
	
	public void saveHwxxToFormal(HyTydglDomain domain) throws Exception {
		
		businessSqlMapClientTemplate.insert("saveHwxxToFormal", domain);
	}
	
	public void deleteHyTydHwxxTempByDdDjxh(Long ddDjxh) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键查询条件
		map.put("ddDjxh", ddDjxh);
		businessSqlMapClientTemplate.delete("deleteHyTydHwxxTempByDdDjxh", map);
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		if ("Y".equals(domain.getTempFlag())) {
			map.put("tableName", "HY_DD_HWMX_TEMP");
		}else {
			map.put("tableName", "HY_DD_HWMX");
		}

		domain = (HyTydHwmxDomain)businessSqlMapClientTemplate.queryForObject("selectHyTydHwmxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteHyTydHwmxByKey", map);
		//businessSqlMapClientTemplate.delete("deleteHyTydWfhxxByHwmxKey", map);
	}
	
	public void deleteHwxxByXhs(String ddDjxh, List<String> hwXhs, String tempFlag) throws Exception {
		String[] xhs = new String[hwXhs.size()];
		System.arraycopy(hwXhs.toArray(), 0, xhs, 0, xhs.length);
		String xhsIn = SysSqlInUtil.getParameterArray(xhs, "XH");
		
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ddDjxh", ddDjxh);
		map.put("xhsIn", xhsIn);
		if ("Y".equals(tempFlag)) {
			map.put("tableName", "HY_DD_HWMX_TEMP");
		}else {
			map.put("tableName", "HY_DD_HWMX");
		}
		
		businessSqlMapClientTemplate.delete("deleteHwxxByXhs", map);
	}
	
	public Long saveCopyOrMbHwxxToTemp(String ddDjxhCopy, Long ddDjxh, String mbCopyFlag) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键条件
		map.put("ddDjxhCopy", ddDjxhCopy);
		if (ddDjxh == null || ddDjxh.longValue() <= 0) {
			ddDjxh = Long.parseLong(commonDao.selectSequence("SEQ_DD_DJXH"));
		}		
		map.put("ddDjxh", ddDjxh);
		
		if ("MB".equals(mbCopyFlag)) {
			map.put("tableName", "HY_MB_DD_HWMX");
		}else {
			map.put("tableName", "HY_DD_HWMX");
		}
		
		businessSqlMapClientTemplate.insert("saveCopyOrMbHwxxToTemp", map);
		
		return ddDjxh;
	}
	
	public HyTydHwmxDomain queryPrintInfo(HyTydHwmxDomain domain) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		
		HyTydHwmxDomain dom = (HyTydHwmxDomain)businessSqlMapClientTemplate.queryForObject("queryPrintInfo", map);
		return dom;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getDdDjxh())){
			HyTydHwmxDomain dom = (HyTydHwmxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom, domain);
			}
		}

	}
	public void updateHwxxWhenShfsbg(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		map.put("shfsDm", domain.getShfsDm());
		map.put("srHj", domain.getSrHj()+"");
		map.put("srPsf", domain.getSrPsf()+"");
		map.put("srHdf", domain.getSrHdf()+"");
		map.put("srThf", domain.getSrThf()+"");
		
		businessSqlMapClientTemplate.update("updateHyTydHwmxWhenShfsbg", map);
	}
	public void updateWhenDeleteShfsbg(BaseBusinessDomain baseDomain) throws Exception {
		HyTydHwmxDomain domain = (HyTydHwmxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		map.put("shfsDm", domain.getShfsDm());
		businessSqlMapClientTemplate.update("updateTydWhenDeleteShfsbg", map);
	}
}
