package com.cy.cwgl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.CwZfdj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwZfdjDao;
import com.cy.cwgl.domain.CwFyBxSqMxDomain;
import com.cy.cwgl.domain.CwHbzcCshDomain;
import com.cy.cwgl.domain.CwZfdjDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 财务-支付登记
 * 
 * @author LYY
 */

@Repository
public class CwZfdjDaoImp implements CwZfdjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		List<BaseBusinessDomain> dataList = null;
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("zgsbm", domain.getZgsbm());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("yfjsfDm", domain.getYfjsfDm());
		map.put("yfjsfDjxh", domain.getYfjsfDjxh());
		if(StringUtils.isNotBlank(domain.getYfjsfDjmc())){
			map.put("yfjsfDjmc", SysEncodeUtil.GBK2ISO(domain.getYfjsfDjmc()));
		}
		map.put("csrqQ", domain.getCsrqQ());
		map.put("csrqZ", domain.getCsrqZ());
		map.put("zt", domain.getZt());
		map.put("ddbh", SysEncodeUtil.GBK2ISO(domain.getDdbh()));
		map.put("ysyflyDm", domain.getYsyflyDm());
		map.put("kmxlDm", domain.getKmxlDm());
		
		map.put("start", page.getCurPageNo());
		map.put("pagSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("pageCurcount", 0);
		map.put("dataList", "");
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("cwZfdj", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		for (BaseBusinessDomain baseBusinessDomain : dataList) {
			CwZfdjDomain zfDomain=(CwZfdjDomain)baseBusinessDomain;
			
			if(zfDomain.getYsyflyDm().equals("31")){
				String strBz="";
				map.put("cwDjxh", zfDomain.getYwDjxh());
				List<CwFyBxSqMxDomain> mxList=businessSqlMapClientTemplate.queryForList("selectCwFybxsqMxByKey",map);
				for (CwFyBxSqMxDomain cwFyBxSqMxDomain : mxList) {
					if(cwFyBxSqMxDomain.getBz()!=null&&!cwFyBxSqMxDomain.getBz().equals("null")){
						strBz+=cwFyBxSqMxDomain.getBz()+",";
					}
				}
				if(!strBz.equals("")){
					strBz=strBz.substring(0,strBz.length()-1);
				}
				zfDomain.setSm(strBz);
			}
			
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		Map<String, Object> map = new HashMap<String, Object>();
		List<BaseBusinessDomain> dataList = null;
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put("zgsbm", domain.getZgsbm());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("yfjsfDm", domain.getYfjsfDm());
		map.put("yfjsfDjxh", domain.getYfjsfDjxh());
		if(StringUtils.isNotBlank(domain.getYfjsfDjmc())){
			map.put("yfjsfDjmc", SysEncodeUtil.GBK2ISO(domain.getYfjsfDjmc()));
		}
		map.put("csrqQ", domain.getCsrqQ());
		map.put("csrqZ", domain.getCsrqZ());
		map.put("zt", domain.getZt());
		
		map.put("start", page.getCurPageNo());
		map.put("pagSize", 999999);
		map.put("pageCount", 0);
		map.put("pageCurcount", 0);
		map.put("dataList", "");
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("cwZfdj", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
//		Map<String, Object> proMap = new HashMap<String, Object>();
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		CwZfdj bo = new CwZfdj();
		bo.setYsyfDjxh(domain.getYsyfDjxh());
		bo.setYfjsfDm(domain.getYfjsfDm());
		bo.setYfjsfDjxh(domain.getYfjsfDjxh());
		bo.setSkfmc(domain.getSkfmc());
		bo.setJe(domain.getJe());
		bo.setRq(domain.getRq());
		bo.setZffsDm(domain.getZffsDm());
		bo.setZcflDm(domain.getZcflDm());
		bo.setYhCshDjxh(domain.getYhCshDjxh());
		bo.setLkr(domain.getLkr());
		bo.setLkrZjlxDm(domain.getLkrZjlxDm());
		bo.setLkrZjhm(domain.getLkrZjhm());
		bo.setLkrq(domain.getLkrq());
		bo.setYhmc(domain.getYhmc());
		bo.setHm(domain.getHm());
		bo.setZh(domain.getZh());
		bo.setZzrq(domain.getZzrq());
		bo.setJbrCzyDjxh(domain.getJbrCzyDjxh());
		bo.setShrCzyDjxh(domain.getShrCzyDjxh());
		bo.setShrq(domain.getShrq());
		bo.setBz(domain.getBz());
		bo.setDjJgbm(domain.getDjJgbm());
		bo.setSsJgbm(domain.getSsJgbm());
		
		bo.setYxbz("Y");
		bo.setDjrCzyDjxh(user.getCzyDjxh());
		bo.setDjrq(SysDateUtil.getSqlDate().toString());
		
		businessSqlMapClientTemplate.insert("saveCwZfdj", bo);
		domain.setZfDjxh(bo.getZfDjxh());
		
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ysyfDjxh", domain.getYsyfDjxh());

		domain = (CwZfdjDomain)businessSqlMapClientTemplate.queryForObject("getYsyfZfdj", map);
		return domain;
	}


	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(domain.getYsyfDjxh())){
			CwZfdjDomain dom = (CwZfdjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				if(dom.getYsyflyDm().equals("31")){
					String strBz="";
					map.put("cwDjxh", dom.getYwDjxh());
					List<CwFyBxSqMxDomain> mxList=businessSqlMapClientTemplate.queryForList("selectCwFybxsqMxByKey",map);
					for (CwFyBxSqMxDomain cwFyBxSqMxDomain : mxList) {
						if(cwFyBxSqMxDomain.getBz()!=null&&!cwFyBxSqMxDomain.getBz().equals("null")){
							strBz+=cwFyBxSqMxDomain.getBz()+",";
						}
					}
					if(!strBz.equals("")){
						strBz=strBz.substring(0,strBz.length()-1);
					}
					dom.setSm(strBz);
				}
				BeanUtils.copyProperties(domain, dom);
				
			}
			List<BaseBusinessDomain> list = this.getCwZfdjList(domain.getYsyfDjxh());
			domain.setDataList(list);
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> getCwZfdjList(String ysyfDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ysyfDjxh", ysyfDjxh);
		List<BaseBusinessDomain> list = businessSqlMapClientTemplate.queryForList("getCwZfdjList", map);
		return list;
	}
	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		
	}

	public void deleteCwZfdjByKey(String zfDjxh)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("zfDjxh", zfDjxh);
		businessSqlMapClientTemplate.update("deleteCwZfdjByKey", map);
	}

	public void callPHyglCwglZfdjHxcl(String zfDjxh, String bz) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zfDjxh", zfDjxh);
		map.put("bz", bz);
		map.put("rtnCode", new Integer(0));
		map.put("errMesge", "");
		businessSqlMapClientTemplate.queryForObject("callPHyglCwglZfdjHxcl", map);
		Integer rtnCode = (Integer) map.get("rtnCode");
		String errMesge = (String) map.get("errMesge");
		if(rtnCode != 0 && StringUtils.isNotBlank(errMesge)){
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(errMesge));
		}
	}
	@SuppressWarnings("unchecked")
	public List<CwZfdjDomain> getLbList() throws Exception {
		List<CwZfdjDomain> list = businessSqlMapClientTemplate.queryForList("getLbList");
		CwZfdjDomain dom = new CwZfdjDomain();
		dom.setYfjsfDm("");
		dom.setLbStr("-- 请选择 --");
		list.add(0, dom);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<CwZfdjDomain> getMcList(String ssJgbm,String yfjsfDm) throws Exception{
		List<CwZfdjDomain> list = new ArrayList<CwZfdjDomain>();
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ssJgbm", ssJgbm);
		
		if(StringUtils.isNotBlank(yfjsfDm)){
			int i  =Integer.parseInt(yfjsfDm);
			switch (i) {
			case 11:
				list= businessSqlMapClientTemplate.queryForList("getZjMcList",map);
				break;
			case 12:
				list= businessSqlMapClientTemplate.queryForList("getSjMcList",map);
				break;
			case 21:
				list= businessSqlMapClientTemplate.queryForList("getKhMcList",map);
				break;
			case 22:
				list= businessSqlMapClientTemplate.queryForList("getFgsMcList",map);
				break;
			case 23:
				list= businessSqlMapClientTemplate.queryForList("getFbsMcList",map);
				break;
			default:
				break;
			}
		}		
		CwZfdjDomain dom = new CwZfdjDomain();
		dom.setYfjsfDjxh("");
		dom.setMcStr("-- 请选择 --");
		list.add(0, dom);
		return list;
	}
	
	public void doGetYh(BaseBusinessDomain baseDomain)throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("cshDjxh", domain.getYhCshDjxh());
		CwHbzcCshDomain dom = (CwHbzcCshDomain)businessSqlMapClientTemplate.queryForObject("selectCwHbzcCshByKey", map);
		if(dom!=null){
		domain.setZh(dom.getZh());
		domain.setHm(dom.getHm());
		domain.setYhmc(dom.getYhmc());
		}
	}

	public void doCancle(BaseBusinessDomain baseDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain) baseDomain;
		businessSqlMapClientTemplate.delete("deleteCwZfdjByYsyfDjxh", domain);
	}
}
