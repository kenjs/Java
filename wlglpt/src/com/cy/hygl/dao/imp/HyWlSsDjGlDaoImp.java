package com.cy.hygl.dao.imp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyWlssdj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyWlSsDjGlDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The DAOIMP for 调度成本审核.
 * 
 * @author HJH
 */

@Repository
public class HyWlSsDjGlDaoImp implements HyWlSsDjGlDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		List<BaseBusinessDomain> dataList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getPcbm4Query())) {
			map.put("pcJgbm4Query", domain.getPcbm4Query());
			map.put("dwbmBz4Query", "B");
		}else {
			map.put("pcJgbm4Query", domain.getDwDm());
			map.put("dwbmBz4Query", "D");
		}
		map.put("zgsbm", domain.getZgsbm());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("fhrMc", SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("clhm4Query", SysEncodeUtil.UTF82ISO(domain.getClhm4Query()));
		map.put("pcdh4Query", SysEncodeUtil.UTF82ISO(domain.getPcdh4Query()));
		map.put("zt", domain.getZt());
	
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyWlSsDjGl", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		List<BaseBusinessDomain> dataList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getPcbm4Query())) {
			map.put("pcJgbm4Query", domain.getPcbm4Query());
			map.put("dwbmBz4Query", "B");
		}else {
			map.put("pcJgbm4Query", domain.getDwDm());
			map.put("dwbmBz4Query", "D");
		}
		map.put("zgsbm", domain.getZgsbm());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("fhrMc", SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("clhm4Query", SysEncodeUtil.UTF82ISO(domain.getClhm4Query()));
		map.put("pcdh4Query", SysEncodeUtil.UTF82ISO(domain.getPcdh4Query()));
		map.put("zt", domain.getZt());
	
		map.put("pageNum", 1);
		map.put("pageSize", 99999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyWlSsDjGl", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("reccount"));
		
		return dataList;
	}
	
	@Transactional
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		HyZyglFydjDomain hyDomain=null;
		HyWlssdj bo = new HyWlssdj();
		if(domain.getWlssDjxh()!=null&&!"".equals(domain.getWlssDjxh())){
			hyDomain=(HyZyglFydjDomain)this.getDomainByKey(domain);
		}
		if(hyDomain!=null){
//			bo.setWlssyyDm(domain.getWlssyyDm());
			bo.setWlssclfsDm(domain.getWlssclfsDm());
			bo.setHjSr(Double.valueOf(domain.getHjSr()));
			bo.setKhDjxh(domain.getKhDjxh());
			bo.setPcDjxh(domain.getPcDjxh());
			bo.setWfhDjxh(domain.getWfhDjxh());
			bo.setBz(domain.getBz());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateHyWlssdjByKey",bo);
		}
		else{
//		bo.setWlssyyDm(domain.getWlssyyDm());
		bo.setWlssclfsDm(domain.getWlssclfsDm());
		bo.setHjSr(Double.valueOf(domain.getHjSr()));
		bo.setKhDjxh(domain.getKhDjxh());
		bo.setPcDjxh(domain.getPcDjxh());
		bo.setWfhDjxh(domain.getWfhDjxh());
		bo.setDdDjxh("");
		bo.setHwmxxh("");
		bo.setBz(domain.getBz());
		bo.setPcygCzyDjxh("");
		bo.setSpbz("N");
		bo.setWsspztDm(0+"");
		bo.setWsSpxh("");
		bo.setSsJgbm("1000000002");
		bo.setDjJgbm("1000000002");
		bo.setYxbz("Y");
		bo.setWsspztDm("");
		bo.setCjrCzyDjxh(user.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getSqlDate().toString());
		bo.setXgrCzyDjxh(user.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getSqlDate().toString());
		businessSqlMapClientTemplate.insert("insertHyWlssdj",bo);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("wlDjxh", bo.getWlssDjxh());
		map.put("bm", user.getBmbm());
		map.put("czyDjxh", user.getCzyDjxh());
		map.put("count", 0);
		map.put("error", "");
		businessSqlMapClientTemplate.queryForObject("checkSaveWlssdj",map);
		if((Integer)map.get("count")==0){
		//	businessSqlMapClientTemplate.update("deleteCwHbzcCshByKey", map);
			//businessSqlMapClientTemplate.update("deleteCwHbzcyeByDjxh",map);
		}
		else{
			if(map.get("error")!=null){
			domain.setError(String.valueOf(map.get("error")));
		}}
		}
	}

	public BaseBusinessDomain getWlSsDjByKey(String djxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wlDjxh", djxh);

		HyZyglFydjDomain	domain = (HyZyglFydjDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcByKey", map);
		return domain;
	}
	
	public BaseBusinessDomain getHyPcAllByDjxh(String id) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", id);

		HyPcxxglDomain doMain = (HyPcxxglDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcAllByDjxh", map);
		return doMain;
	}
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcxxglDomain dom = (HyPcxxglDomain) this.getHyPcAllByDjxh(domain.getPcDjxh());
			if(dom!=null){
				domain.setPcdh(dom.getPcdh());
				domain.setClhm4Query(dom.getCyrClhm());
				domain.setCyrGchm(dom.getCyrGchm());
				domain.setCyrSjxm(dom.getCyrSjxm());
				domain.setPcrMc(dom.getPcrMc());	
				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");	
				domain.setPcrq(sim.format(dom.getPcrq()));	
				domain.setPcJgmc(dom.getPcJgbmMc());	
				domain.setSsJgmc(dom.getSsJgbmMc());
				if(StringUtils.isNotBlank(domain.getWlssDjxh())){
					HyZyglFydjDomain wlDomain=(HyZyglFydjDomain)this.getWlSsDjByKey(domain.getWlssDjxh());
				}
			}
		}
	}

	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public List<HyZyglFydjDomain> getHw(HyZyglFydjDomain domain,UserDomain userDomain) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			map.put("pcDjxh", domain.getPcDjxh());
		}
		if(String.valueOf(domain.getFhrDjxh())!=null&&!"null".equals(domain.getFhrDjxh())&&!"".equals(domain.getFhrDjxh())){
			map.put("fhDjxh", domain.getFhrDjxh());
		}
		map.put("mcContainDmBz", domain.getConDm());
		List<HyZyglFydjDomain> dataList =businessSqlMapClientTemplate.queryForList("getPcHwByDjxh",map);
		return dataList;
	}

	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
