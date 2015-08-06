package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.HyPcHwxx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;

/**
 * The DAOIMP for 货运-派车-货物信息.
 * 
 * @author HJH
 */

@Repository
public class HyPcHwxxDaoImp implements HyPcHwxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		HyPcHwxx bo = new HyPcHwxx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyPcHwxxDomain dom = (HyPcHwxxDomain) this.getDomainByKey(domain);

		BeanUtils.copyProperties(domain, bo);
		if(dom != null){
			businessSqlMapClientTemplate.update("updateHyPcHwxxByKey", bo);
		}else{
			businessSqlMapClientTemplate.insert("insertHyPcHwxx", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());

		domain = (HyPcHwxxDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxByKey", map);
		return domain;
	}
	
	public BaseBusinessDomain getPcHwDomainFromLsbByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pchwLsxh", domain.getPchwLsxh());
		map.put("wfhDjxh", domain.getWfhDjxh());

		domain = (HyPcHwxxDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxFromLsbByKey", map);
		return domain;
	}
	
	public void updatePcHwxxTmp(HyPcHwxxDomain domain) throws Exception {
		HyPcHwxx bo = new HyPcHwxx();
		BeanUtils.copyProperties(domain, bo);
		businessSqlMapClientTemplate.update("updatePcHwxxTmp", bo);
	}
	
	public void updatePcHwxx(HyPcHwxxDomain domain) throws Exception {
		HyPcHwxx bo = new HyPcHwxx();
		BeanUtils.copyProperties(domain, bo);
		businessSqlMapClientTemplate.update("updateHyPcHwxxByKey", bo);
	}
	public HyPcHwxxDomain getPcHwxxDomainByKey(String pcDjxh, String wfhDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);

		return (HyPcHwxxDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxDomainByKey", map);
	}
	
	public void savePcHwxxToFormalTab(HyPcxxglDomain domain) throws Exception {
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pchwLsxh", domain.getPchwLsxh());
		map.put("pcDjxh", domain.getPcDjxh());
		/*map.put("zrbmDm", domain.getZrbmDm());
		map.put("zrbmDjxh", domain.getZrbmDjxh());
		map.put("zrbmXzqhDm", domain.getZrbmXzqhDm());
		map.put("zrbmDz", domain.getZrbmDz());
		map.put("zrbmLxr", domain.getZrbmLxr());
		map.put("zrbmLxdh", domain.getZrbmLxdh());*/
		
		businessSqlMapClientTemplate.insert("savePcHwxxToFormalTab", map);
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteHyPcHwxxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcHwxxDomain dom = (HyPcHwxxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom, domain);
			}
		}

	}
	public void updatePcHwxxWhenShfsbg(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh",domain.getWfhDjxh());
		map.put("shfsDm",domain.getShfsDm());
		map.put("zcHdf", domain.getZcHdf()+"");
		map.put("zcThf", domain.getZcThf()+"");
		businessSqlMapClientTemplate.update("updateHyPcHwxxWhenShfsbg", map);
	}
	public void updateWhenDeleteShfsbg(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxDomain domain = (HyPcHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh",domain.getWfhDjxh());
		map.put("shfsDm",domain.getShfsDm());
		businessSqlMapClientTemplate.update("updatePcWhenDeleteShfsbg", map);
	}
	
}
