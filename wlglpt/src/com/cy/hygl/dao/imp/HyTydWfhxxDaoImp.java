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

import com.cy.common.bo.HyTydWfhxx;
import com.cy.common.bo.HyXgjlRz;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyTydWfhxxDao;
import com.cy.hygl.domain.HyTydWfhxxDomain;

/**
 * The DAOIMP for 货运-托运单-未发货(提货)信息.
 * 
 * @author HJH
 */

@Repository
public class HyTydWfhxxDaoImp implements HyTydWfhxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) baseDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页相关
		PageDomain page = domain.getPage();
		List<BaseBusinessDomain> dataList = null;
		// 设置查询条件
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		map.put("dw4Query", domain.getDw4Query());
		map.put("fhrXzqhDm", domain.getFhrXzqhDm());
		map.put("shrXzqhDm", domain.getShrXzqhDm());
		map.put("ddbh4Query", domain.getDdbh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		map.put("khMc4Query", domain.getKhMc4Query());
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		map.put("fhrqQ", domain.getFhrqQ());
		map.put("fhrqZ", domain.getFhrqZ());
		map.put("djrCzyDjxh4Query", domain.getDjrCzyDjxh4Query());
		map.put("djJgbm4Query", domain.getDjJgbm4Query());
		map.put("lb4Query", domain.getLb4Query());
		map.put("hwztDm4Query", domain.getHwztDm4Query());
		//若为派车打开，则不分页
		if ("Y".equals(domain.getPcOpenFlag())) {
			map.put("pageNum", 1);
			map.put("pageSize", 999999999);
			map.put("bz", "1");
		}else {
			map.put("pageNum", page.getCurPageNo());
			map.put("pageSize", page.getPageSize());
			map.put("bz", "2");
		}
		map.put("pchwLsxh", domain.getPchwLsxh());
		map.put("pcDjxh", domain.getPcDjxh());
		
		map.put("pageCount",0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyTydwfhxxPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) baseDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页相关
		PageDomain page = domain.getPage();
		List<BaseBusinessDomain> dataList = null;
		// 设置查询条件
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		map.put("dw4Query", domain.getDw4Query());
		map.put("fhrXzqhDm", domain.getFhrXzqhDm());
		map.put("shrXzqhDm", domain.getShrXzqhDm());
		map.put("ddbh4Query", domain.getDdbh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		map.put("khMc4Query", domain.getKhMc4Query());
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		map.put("fhrqQ", domain.getFhrqQ());
		map.put("fhrqZ", domain.getFhrqZ());
		map.put("djrCzyDjxh4Query", domain.getDjrCzyDjxh4Query());
		map.put("djJgbm4Query", domain.getDjJgbm4Query());
		map.put("lb4Query", domain.getLb4Query());
		map.put("hwztDm4Query", domain.getHwztDm4Query());
		map.put("bz", "2");
		
		map.put("pageNum", 1);
		map.put("pageSize", 99999999);
		map.put("pageCount",0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyTydwfhxxPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) baseDomain;
		HyTydWfhxx bo = new HyTydWfhxx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyTydWfhxxDomain dom = (HyTydWfhxxDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setHwztDm(domain.getHwztDm());
			bo.setDdDjxh(domain.getDdDjxh());
			bo.setXh(domain.getXh());
			bo.setHwSl(domain.getHwSl());
			bo.setHwZl(domain.getHwZl());
			bo.setHwTj(domain.getHwTj());
			bo.setFhrDjxh(domain.getFhrDjxh());
			bo.setFhrMc(domain.getFhrMc());
			bo.setFhrDz(domain.getFhrDz());
			bo.setFhrLxr(domain.getFhrLxr());
			bo.setFhrLxdh(domain.getFhrLxdh());
			bo.setFhrXzqhDm(domain.getFhrXzqhDm());
			bo.setPcbz(domain.getPcbz());
			bo.setPcDjxh(domain.getPcDjxh());

			bo.setYxbz("Y");

			businessSqlMapClientTemplate.update("updateHyTydWfhxxByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");

			businessSqlMapClientTemplate.insert("insertHyTydWfhxx", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wfhDjxh", domain.getWfhDjxh());

		domain = (HyTydWfhxxDomain)businessSqlMapClientTemplate.queryForObject("selectHyTydWfhxxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("wfhDjxh", domain.getWfhDjxh());

		businessSqlMapClientTemplate.update("deleteHyTydWfhxxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWfhDjxh())){
			HyTydWfhxxDomain dom = (HyTydWfhxxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryPchwClfsdmList(String pcfsDm) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pcfsDm", pcfsDm);
		map.put("mcContainsDm", "N");
		List<DmbGgDomain> list = businessSqlMapClientTemplate.queryForList("queryPchwClfsdmList", map);
		return list;
	}

	public void updateWfhhwZt(String wfhDjxh,String hwzt) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("hwztDm", hwzt);
		map.put("wfhDjxh", wfhDjxh);
		
		businessSqlMapClientTemplate.update("updateHwztByKey", map);
	}
	
	public void saveXgRz(HyTydWfhxxDomain domain,UserDomain userDomain) throws Exception {
		HyXgjlRz bo = new HyXgjlRz();
		BeanUtils.copyProperties(bo, domain);
		
		bo.setYwId(domain.getGnmkDm());
		bo.setCzrCzyDjxh(userDomain.getCzyDjxh());
		bo.setCzrq(SysDateUtil.getCurrentDate().toString());
		
		businessSqlMapClientTemplate.insert("insertHyXgjlRz", bo);
	}
}
