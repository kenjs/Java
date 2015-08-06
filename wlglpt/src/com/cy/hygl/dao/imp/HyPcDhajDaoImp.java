package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyPcDhaj;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcDhajDao;
import com.cy.hygl.domain.HyPcDhajDomain;

/**
 * The DAOIMP for 货运-派车-电话安检.
 * 
 * @author HJH
 */

@Repository
public class HyPcDhajDaoImp implements HyPcDhajDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;
		map.put("pcbm4Query", domain.getPcbm4Query());
		map.put("bz", domain.getBz());
		map.put("pcrCzyDjxh", domain.getPcrCzyDjxh());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("clhm4Query", domain.getClhm4Query());
		map.put("sjxm4Query", domain.getSjxm4Query());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("khmc", domain.getFhrMc());
		map.put("pcdh4Qyuery", domain.getPcdh4Qyuery());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount",0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcDhajPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@Transactional
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;
		String jgbm=domain.getSsJgbm();
		String bz="D";
		if(domain.getPcJgbm()!=null&&!domain.getPcJgbm().equals("")){
			jgbm=domain.getPcJgbm();
			bz="B";
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("khmc", SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
		}
		if(StringUtils.isNotBlank(domain.getSjXm())){
			map.put("sjxm4Query",SysEncodeUtil.UTF82ISO(domain.getSjXm()));
		}
		if(StringUtils.isNotBlank(domain.getClhm4Query())){
			map.put("clhm4Query", SysEncodeUtil.UTF82ISO(domain.getClhm4Query()));
		}
		if(StringUtils.isNotBlank(domain.getPcDh())){
			map.put("pcdh4Qyuery", SysEncodeUtil.UTF82ISO(domain.getPcDh()));
		}
		map.put("pcbm4Query", jgbm);
		map.put("bz", bz);
		map.put("pcrCzyDjxh", domain.getPcrCzyDjxh());
		map.put("pcrqQ", domain.getPcrqq());
		map.put("pcrqZ", domain.getPcrqz());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount",0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcDhajPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		HyPcDhaj bo = new HyPcDhaj();

		BeanUtils.copyProperties(bo, domain);
		
		bo.setYxbz("Y");
		bo.setCjrCzyDjxh(user.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getCurrentDate().toString());
		bo.setXgrCzyDjxh(user.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getCurrentDate().toString());

		businessSqlMapClientTemplate.insert("insertHyPcDhaj", bo);
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ajDjxh", domain.getAjDjxh());

		domain = (HyPcDhajDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcDhajByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ajDjxh", domain.getAjDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcDhajByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		String pcDjxh = domain.getPcDjxh();
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcDhajDomain dom = (HyPcDhajDomain) this.queryPcxx(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setPcDjxh(pcDjxh);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<HyPcDhajDomain> queryAjdhList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		//设置查询条件
		
		map.put("pcDjxh", domain.getPcDjxh());
		List<HyPcDhajDomain> ajdhList = businessSqlMapClientTemplate.queryForList("queryAllAjdh", map);
		return ajdhList;
	}

	@SuppressWarnings("unchecked")
	public List<HyPcDhajDomain> queryAjzpList(BaseBusinessDomain baseDomain)
			throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("pcDjxh", domain.getPcDjxh());
		List<HyPcDhajDomain> ajzpList = businessSqlMapClientTemplate.queryForList("queryAllAjzp", map);
		return ajzpList;
	}

	public BaseBusinessDomain queryPcxx(BaseBusinessDomain baseDomain)
			throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("pcDjxh", domain.getPcDjxh());
		return (HyPcDhajDomain) businessSqlMapClientTemplate.queryForObject("queryPcxx", map);
		
	}
}
