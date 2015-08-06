package com.cy.jcgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.domain.HyClgzDomain;
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.jcgl.dao.JcTydglDao;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcTydglDomain;
import com.cy.jcgl.domain.JcYfZfxxDomain;

/**
 * The DAOIMP for 决策-托运单管理.
 * 
 * @author LYY
 */

@Repository
public class JcTydglDaoImp implements JcTydglDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		JcTydglDomain domain = (JcTydglDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页相关
		PageDomain page = domain.getPage();
		
		List<BaseBusinessDomain> dataList = null;
		
		if (StringUtils.isNotBlank(domain.getDjJgbm4Query())) {
			map.put("djJgbm4Query", domain.getDjJgbm4Query());
			map.put("dwbmBz", "B");
		}else {
			map.put("djJgbm4Query", domain.getSsJgbm4Query());
			map.put("dwbmBz", "D");
		}
		
		map.put("djrCzyDjxh4Query", domain.getDjrCzyDjxh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh4Query());
		map.put("fhrMc4Query", SysEncodeUtil.GBK2ISO(domain.getFhrMc4Query()));
		map.put("shrDjxh4Query", domain.getShrDjxh4Query());
		map.put("shrMc4Query", SysEncodeUtil.GBK2ISO(domain.getShrMc4Query()));
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		
		map.put("ddbhQ", domain.getDdbhQ());
		map.put("ddbhZ", domain.getDdbhZ());
		map.put("fhrMc4", SysEncodeUtil.GBK2ISO(domain.getFhrMc4()));
		map.put("shrMc4", SysEncodeUtil.GBK2ISO(domain.getShrMc4()));
		map.put("hwMc4", SysEncodeUtil.GBK2ISO(domain.getHwMc4()));
		
		map.put("hwSl4", domain.getHwSl4());
		map.put("hwZl4", domain.getHwZl4());
		map.put("hwTj4", domain.getHwTj4());
		map.put("zsr4", domain.getZsr4());
		
		map.put("xjBz4", domain.getXjBz4());
		map.put("dfBz4", domain.getDfBz4());
		map.put("yjBz4", domain.getYjBz4());
		map.put("shfsDm4", domain.getShfsDm4());
		map.put("yjWjBz4", domain.getYjWjBz4());

		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		// 根据页面上的排序条件设置排序
		//map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJcTydPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		JcTydglDomain domain = (JcTydglDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页相关
		PageDomain page = domain.getPage();
		
		List<BaseBusinessDomain> dataList = null;
		
		if (StringUtils.isNotBlank(domain.getDjJgbm4Query())) {
			map.put("djJgbm4Query", domain.getDjJgbm4Query());
			map.put("dwbmBz", "B");
		}else {
			map.put("djJgbm4Query", domain.getSsJgbm4Query());
			map.put("dwbmBz", "D");
		}
		
		map.put("djrCzyDjxh4Query", domain.getDjrCzyDjxh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		map.put("fhrMc4Query", SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		map.put("shrDjxh4Query", domain.getShrDjxh());
		map.put("shrMc4Query", SysEncodeUtil.GBK2ISO(domain.getShrMc()));
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		
		map.put("ddbhQ", domain.getDdbhQ());
		map.put("ddbhZ", domain.getDdbhZ());
		map.put("fhrMc4", domain.getFhrMc4());
		map.put("shrMc4", domain.getShrMc4());
		map.put("hwMc4", domain.getHwMc4());
		
		map.put("hwSl4", domain.getHwSl4());
		map.put("hwZl4", domain.getHwZl4());
		map.put("hwTj4", domain.getHwTj4());
		map.put("zsr4", domain.getZsr4());
		
		if("true".equals(domain.getXjBz4())){
			map.put("xjBz4","1");
		}else{
			map.put("xjBz4","0");
		}
		if("true".equals(domain.getDfBz4())){
			map.put("dfBz4","1");
		}else{
			map.put("dfBz4","0");
		}
		if("true".equals(domain.getYjBz4())){
			map.put("yjBz4","1");
		}else{
			map.put("yjBz4","0");
		}
		map.put("shfsDm4", domain.getShfsDm4());
		
		
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		// 根据页面上的排序条件设置排序
		//map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJcTydPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<JcPcxxglDomain> queryJcTydPcxx(String ddDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<JcPcxxglDomain> dataList = null;
		
		map.put("ddDjxh", ddDjxh);
		map.put("pageNum", 1);
		map.put("pageSize", Integer.MAX_VALUE);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJcglTydcxPcxx", "dataList", map);
		dataList = (List<JcPcxxglDomain>)map.get("dataList");
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<HyClgzDomain> queryJcSjcxClgzxx(Long ddDjxh, String pcDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HyClgzDomain> dataList = null;
		
		map.put("ddDjxh", ddDjxh);
		map.put("pcDjxh", pcDjxh);
		map.put("pageNum", 1);
		map.put("pageSize", Integer.MAX_VALUE);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJcSjcxClgzxx", "dataList", map);
		dataList = (List<HyClgzDomain>)map.get("dataList");
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<HyWlSsDjGlDomain> queryJcWlssxx(Long ddDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();		
		
		map.put("ddDjxh", ddDjxh);
		//map.put("pcDjxh", pcDjxh);
		
		List<HyWlSsDjGlDomain> wlssList = businessSqlMapClientTemplate.queryForList("queryWlssXxByDdDjxh4Dd", map);
		// 检索数据
		
		return wlssList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<JcYfZfxxDomain> queryJcYfZfxx(Long ddDjxh, String pcDjxh)
			throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		List<JcYfZfxxDomain> dataList = null;
		map.put("ddDjxh", ddDjxh);
		map.put("pcDjxh", pcDjxh);
		map.put("pageNum", 1);
		map.put("pageSize", Integer.MAX_VALUE);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJcYfZfxx", "dataList", map);
		dataList = (List<JcYfZfxxDomain>)map.get("dataList");
		return dataList;
	}
	
	/**
	 * 检索物流损失对应的明细
	 */
	public List<HyWlSsDjGlDomain> querySsmx(String wlssDjxh) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("wlssDjxh", wlssDjxh);
		
		return businessSqlMapClientTemplate.queryForList("getWlssMxByWlssDjxh", map);
	}
}
