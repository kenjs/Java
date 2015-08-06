package com.cy.common.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.xtgl.domain.QyJsDomain;

@Repository
public class WlglptCommonDaoImp implements WlglptCommonDao {

	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryCommonList(WlglptCommonDomain domain, UserDomain userDomain) throws Exception {
		String ywid = domain.getYwid();
		// 对YWID进行处理，原始的YWID：gs-fbs，现在处理为 GSFBS
		ywid = ywid.replaceAll("-", "").toUpperCase();
		String sqlID = "get" + ywid + "DdlbList";

		Map<String, String> map = new HashMap<String, String>();
		map.put("sj", domain.getParamdm());
		map.put("jgbm", userDomain.qyZcxh);
		map.put("zgs", userDomain.getZgsbm());
		map.put("mcContainDmBz", domain.getMcContainDmBz());
		List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList(sqlID, map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryBmList(WlglptCommonDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sjJgbm", domain.getParamdm());
		map.put("mcContainDmBz", domain.getMcContainDmBz());
		List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList("getBmDdlbList", map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryGwList(WlglptCommonDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("bmdm", domain.getParamdm());
		map.put("mcContainDmBz", domain.getMcContainDmBz());
		List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList("getGwList", map);
		return dataList;
	}
	
	public List<DmbGgDomain> queryRyList(WlglptCommonDomain domain) throws Exception {
		@SuppressWarnings("unused")
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("", value)
		List<DmbGgDomain> dataList = null;
		return dataList;
	}
	
	public String getQyJbdmByJgbm(String jgbm) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jgbm", jgbm);
		Object obj = businessSqlMapClientTemplate.queryForObject("getQyJbdmByJgbm", map);
		if (obj != null)
			return obj.toString();
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public List<QyJsDomain> getJsListByJgbm(String jgbm) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jgbm", jgbm);
		List<QyJsDomain> dataList = businessSqlMapClientTemplate.queryForList("getJsListByJgbm", map);
		return dataList;
	}
	
	public String getSjJgbmByJgbm(String jgbm) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jgbm", jgbm);
		Object obj = businessSqlMapClientTemplate.queryForObject("getSjJgbmByJgbm", map);
		if (obj != null)
			return obj.toString();
		return "";
	}
	
	public String getFunXtSxh(String sxhflbm, String jgbm) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sxhflbm", sxhflbm);
		map.put("jgbm", jgbm);
		map.put("sxh", "");
		map.put("errMess", "");
		map.put("retCode", 0);
		
		businessSqlMapClientTemplate.queryForObject("getFunXtSxh", map);
		
		if (map.get("sxh") != null) {
			return (String)map.get("sxh");
		}
		
		return "";
	}
	
	public String getFunXtXtcs(String csxh, String jgbm, String cslb) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("csxh", csxh);
		map.put("jgbm", jgbm);
		map.put("cslb", cslb);
		map.put("csz", "");
		
		businessSqlMapClientTemplate.queryForObject("getFunXtXtcs", map);
		
		if (map.get("csz") != null) {
			return (String)map.get("csz");
		}
		
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryFbsByJgbm(WlglptCommonDomain domain, String ssJgbm) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ssJgbm", ssJgbm);
		map.put("mcContainDmBz", domain.getMcContainDmBz());
		
		List<DmbGgDomain> list = businessSqlMapClientTemplate.queryForList("queryFbsByJgbm", map);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryWs(WlglptCommonDomain domain) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("jgbm", domain.getParamdm());
		map.put("mcContainDmBz", domain.getMcContainDmBz());
		List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList("getSpwsDdlbList", map);
		return dataList;
	}
	
	public String selectSequence(String seqName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seqName", seqName);
		return (String) businessSqlMapClientTemplate.queryForObject("selectSequence", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryPcfsRadioList() throws Exception {
		List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList("queryPcfsRadioList");
		return dataList;
	}
	
}
