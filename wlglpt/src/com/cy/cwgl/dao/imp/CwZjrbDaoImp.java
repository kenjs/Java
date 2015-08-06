package com.cy.cwgl.dao.imp;

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
import com.cy.common.bo.CwZjrb;
import com.cy.cwgl.dao.CwZjrbDao;
import com.cy.cwgl.domain.CwZjrbDomain;
import com.cy.cwgl.domain.CwZjrbSzmxDomain;

/**
 * The DAOIMP for 财务-资金日报.
 * 
 * @author HJH
 */

@Repository
public class CwZjrbDaoImp implements CwZjrbDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwZjrbPage", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<CwZjrbSzmxDomain> querySzmxList(BaseBusinessDomain baseDomain)  throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rq", domain.getRq());
		String bz = domain.getBz();
		List<CwZjrbSzmxDomain>  dataList=null;
		if(bz.equals("xfSrXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbXfSrXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbXfSrXjPage", map,start,pagSize);	
		}
		if(bz.equals("dfSrXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbDfSrXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbDfSrXjPage", map,start,pagSize);	
		}
		if(bz.equals("yjSrXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbYjSrXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbYjSrXjPage", map,start,pagSize);	
		}
		if(bz.equals("ysSrXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbYsSrXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbYsSrXjPage", map,start,pagSize);	
		}
		if(bz.equals("qtSrXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbQtSrXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbQtSrXjPage", map,start,pagSize);	
		}
		if(bz.equals("dshkSrXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbDshkSrXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbDshkSrXjPage", map,start,pagSize);	
		}
		if(bz.equals("zzcXj")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbZzcXjRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbZzcXjPage", map,start,pagSize);	
		}
		
	
		
		if(bz.equals("xfSrYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbXfSrYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbXfSrYhPage", map,start,pagSize);	
		}
		if(bz.equals("dfSrYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbDfSrYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbDfSrYhPage", map,start,pagSize);	
		}
		if(bz.equals("yjSrYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbYjSrYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbYjSrYhPage", map,start,pagSize);	
		}
		if(bz.equals("ysSrYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbYsSrYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbYsSrYhPage", map,start,pagSize);	
		}
		if(bz.equals("qtSrYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbQtSrYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbQtSrYhPage", map,start,pagSize);	
		}
		if(bz.equals("dshkSrYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbDshkSrYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbDshkSrYhPage", map,start,pagSize);	
		}
		if(bz.equals("zzcYh")){
			// 检索数据
			int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjrbZzcYhRowCount", map))).intValue();
			page.setTotalRecords(totalRecords);
			dataList = businessSqlMapClientTemplate.queryForList("selectZjrbZzcYhPage", map,start,pagSize);	
		}

	
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwZjrbAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		CwZjrb bo = new CwZjrb();

		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rq", domain.getRq());
		CwZjrbDomain dom = (CwZjrbDomain)businessSqlMapClientTemplate.queryForObject("selectCwZjrb", map);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			

			bo.setZrKcXj(domain.getZrKcXj());
			bo.setZrKcYh(domain.getZrKcYh());
			bo.setZrKcYk(domain.getZrKcYk());
			bo.setKcXj(domain.getKcXj());
			bo.setKcYh(domain.getKcYh());
			bo.setKcYk(domain.getKcYk());
			bo.setSrXj(domain.getSrXj());
			bo.setSrYh(domain.getSrYh());
			bo.setSrYk(domain.getSrYk());
			bo.setZcXj(domain.getZcXj());
			bo.setZcYh(domain.getZcYh());
			bo.setZcYk(domain.getZcYk());
			bo.setKcXjSrz(domain.getKcXjSrz());
			bo.setKcYhSrz(domain.getKcYhSrz());
			bo.setSrXjSrz(domain.getSrXjSrz());
			bo.setSrYhSrz(domain.getSrYhSrz());
			bo.setZcXjSrz(domain.getZcXjSrz());
			bo.setZcYhSrz(domain.getZcYhSrz());
			bo.setXfSrXj(domain.getXfSrXj());
			bo.setDfSrXj(domain.getDfSrXj());
			bo.setYjSrXj(domain.getYjSrXj());
			bo.setDshkSrXj(domain.getDshkSrXj());
			bo.setYsSrXj(domain.getYsSrXj());
			bo.setQtSrXj(domain.getQtSrXj());
			bo.setXfSrYh(domain.getXfSrYh());
			bo.setDfSrYh(domain.getDfSrYh());
			bo.setYjSrYh(domain.getYjSrYh());
			bo.setDshkSrYh(domain.getDshkSrYh());
			bo.setYsSrYh(domain.getYsSrYh());
			bo.setQtSrYh(domain.getQtSrYh());

			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateCwZjrbByKey", bo);		
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertCwZjrb", bo);
		}
		domain.setCwDjxh(bo.getCwDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwZjrbDomain)businessSqlMapClientTemplate.queryForObject("selectCwZjrbByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("cwDjxh", domain.getCwDjxh());

		businessSqlMapClientTemplate.update("deleteCwZjrbByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rq", domain.getRq());
		CwZjrbDomain dom = (CwZjrbDomain)businessSqlMapClientTemplate.queryForObject("selectCwZjrb", map);
		if(dom!=null){
			BeanUtils.copyProperties(domain, dom);
		}else{
			CwZjrbDomain dom1= (CwZjrbDomain)businessSqlMapClientTemplate.queryForObject("selectZjrbXx", map);

			domain.setKcXj(dom1.getKcXj());
			domain.setKcYh(dom1.getKcYh());
			domain.setSrXj(dom1.getSrXj());
			domain.setSrYh(dom1.getSrYh());
			domain.setZcXj(dom1.getZcXj());
			domain.setZcYh(dom1.getZcYh());
			domain.setZrKcXj(dom1.getZrKcXj());
			domain.setZrKcYh(dom1.getZrKcYh());
			
			domain.setXfSrXj(dom1.getXfSrXj());
			domain.setDfSrXj(dom1.getDfSrXj());
			domain.setYjSrXj(dom1.getYjSrXj());
			domain.setDshkSrXj(dom1.getDshkSrXj());
			domain.setYsSrXj(dom1.getYsSrXj());
			domain.setQtSrXj(dom1.getQtSrXj());
			
			domain.setXfSrYh(dom1.getXfSrYh());
			domain.setDfSrYh(dom1.getDfSrYh());
			domain.setYjSrYh(dom1.getYjSrYh());
			domain.setDshkSrYh(dom1.getDshkSrYh());
			domain.setYsSrYh(dom1.getYsSrYh());
			domain.setQtSrYh(dom1.getQtSrYh());
		}
	}
	public void cxTjMx(BaseBusinessDomain baseDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rq", domain.getRq());
		CwZjrbDomain dom1= (CwZjrbDomain)businessSqlMapClientTemplate.queryForObject("selectZjrbXx", map);


		domain.setSrXj(dom1.getSrXj());
		domain.setSrYh(dom1.getSrYh());
		domain.setZcXj(dom1.getZcXj());
		domain.setZcYh(dom1.getZcYh());

		
		domain.setXfSrXj(dom1.getXfSrXj());
		domain.setDfSrXj(dom1.getDfSrXj());
		domain.setYjSrXj(dom1.getYjSrXj());
		domain.setDshkSrXj(dom1.getDshkSrXj());
		domain.setYsSrXj(dom1.getYsSrXj());
		domain.setQtSrXj(dom1.getQtSrXj());
		domain.setXfSrYh(dom1.getXfSrYh());
		domain.setDfSrYh(dom1.getDfSrYh());
		domain.setYjSrYh(dom1.getYjSrYh());
		domain.setDshkSrYh(dom1.getDshkSrYh());
		domain.setYsSrYh(dom1.getYsSrYh());
		domain.setQtSrYh(dom1.getQtSrYh());
	}
}
