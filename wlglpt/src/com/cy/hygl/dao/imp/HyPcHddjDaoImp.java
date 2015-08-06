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

import com.cy.common.bo.HyPcHddj;
import com.cy.common.bo.HyWlssdj;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHddjDao;
import com.cy.hygl.dao.HyWlSsDjDao;
import com.cy.hygl.domain.HyPcHddjDomain;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyWlssdjDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The DAOIMP for 货运-派车-回单.
 * 
 * @author HJH
 */

@Repository
public class HyPcHddjDaoImp implements HyPcHddjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@Autowired
	private HyWlSsDjDao dao;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;
		if (StringUtils.isNotBlank(domain.getPcJgbm())) {
			map.put("pcJgbm", domain.getPcJgbm());
			map.put("dwbmBz", "B");
		}else {
			map.put("pcJgbm", domain.getSsJgbm());
			map.put("dwbmBz", "D");
		}
		map.put("pcrCzyDjxh", domain.getPcrCzyDjxh());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("zt", domain.getZt());
		map.put("hdbh", domain.getHdbh());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		map.put("sfXsFgs", domain.getSfXsFgs());
		map.put("hwmc", domain.getHwmc());
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcHddjPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHddjAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		HyPcHddj bo = new HyPcHddj();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyPcHddjDomain dom = (HyPcHddjDomain) this.getDomainByKey(domain);
		BeanUtils.copyProperties(bo, domain);
		if(dom != null){
			bo.setSsJgbm(user.getGsbm());
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateHyPcHddjByKey", bo);
		}else {
			bo.setSpbz("N");
			bo.setYxbz("Y");
			bo.setSsJgbm(user.getGsbm());
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.insert("insertHyPcHddj", bo);
		}
		domain.setHdDjxh(bo.getHdDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hdDjxh", domain.getHdDjxh());

		domain = (HyPcHddjDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHddjByKey", map);
		return domain;
		/* null as HWFL_MC,
         null as FHR_DZ,null as YQ_FHRQ,
         null as SHR_MC,null as SHR_DZ,null as YQ_DDRQ,
         null as DJR_CZY_DJXH,null as DJR_MC,
         null as XDRQ,
         null as DJ_JGBM,null as DJ_JGMC,
         null as SS_JGBM,null as SS_JGMC,
         null as DJRQ,
         null as SHFS_DM,null as SHFS_MC,
         
         
         CASE WHEN C.HW_SL IS NULL OR sum(C.HW_SL) = 0 THEN NULL ELSE 
         rtrim(TO_CHAR(sum(C.HW_SL),'fm99999999999990.99'),'.')  end SL,
         CASE WHEN C.HW_ZL IS NULL OR sum(C.HW_ZL) = 0 THEN NULL ELSE
         rtrim(TO_CHAR(sum(C.HW_ZL),'fm99999999999990.99'),'.')  end ZL,
         CASE WHEN C.HW_TJ IS NULL OR sum(C.HW_TJ) = 0 THEN NULL ELSE
         rtrim(TO_CHAR(sum(C.HW_TJ),'fm99999999999990.99'),'.')   end TJ,
         CASE WHEN C.JS_SL IS NULL OR sum(C.JS_SL) = 0 THEN NULL ELSE
         rtrim(TO_CHAR (sum(C.JS_SL),'fm99999999999990.99'),'.') end JS_SL,     
         
         
         null as WFH_DJXH,null as DD_DJXH,null as XH,
         null as DDBH, null as FHR_MC,
         null as HWZT_DM, null as HWZT_MC,
         null as FHR_XZQH_DM, null as FHR_XZQH_MC,
         null as SHR_XZQH_DM, null as SHR_XZQH_MC,
         null as HWMC,*/
	}
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hdDjxh", domain.getHdDjxh());
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		businessSqlMapClientTemplate.update("deleteHyPcHddjByKey", map);
		
		businessSqlMapClientTemplate.update("deleteWlssDjByDjxh",map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcHddjDomain dom = (HyPcHddjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	public void callProHddjHxcl(String hdDjxh, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hdDjxh", hdDjxh);
		map.put("bmbm", userDomain.getBmbm());
		map.put("czyDjxh", userDomain.getCzyDjxh());
		map.put("retCode", 0);
		map.put("retMsg", "");
		businessSqlMapClientTemplate.queryForObject("callProHddjHxcl", map);
		if (map.get("retCode") != null && (Integer)map.get("retCode") != 0) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("retMsg")));
		}
	}
	
	public HyPcHwxxDomain queryJsPcHwxxByPcWfhXh(String pcDjxh, String wfhDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);

		return (HyPcHwxxDomain)businessSqlMapClientTemplate.queryForObject("queryJsPcHwxxByPcWfhXh", map);
	}

	public void plSave(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain) baseDomain;
		HyPcHddj bo = new HyPcHddj();

		// 根据主键查询对象 如果对象存在那么修改
		HyPcHddjDomain dom = (HyPcHddjDomain) this.getDomainByKey(domain);
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			businessSqlMapClientTemplate.update("updateHyPcHddjByKey", bo);
		}
	}
	
	public HyPcHddjDomain getWlss(String pcDjxh, String wfhDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);

		HyPcHddjDomain dom = (HyPcHddjDomain)businessSqlMapClientTemplate.queryForObject("selectHyWlss", map);
		if(dom==null){
			return null;
		}
		return dom;
	}
	public HyPcHddjDomain getZHwSl(String pcDjxh, String wfhDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);

		HyPcHddjDomain dom = (HyPcHddjDomain)businessSqlMapClientTemplate.queryForObject("selectZHwSl", map);
		if(dom==null){
			return null;
		}
		return dom;
	}

	public String plJs(String pcDjxh, String wfhDjxh) throws Exception {
		HyPcHddj bo = new HyPcHddj();
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);

		HyPcHwxxDomain dom = (HyPcHwxxDomain)businessSqlMapClientTemplate.queryForObject("queryJsPcHwxxByPcWfhXh", map);
		if(dom != null){
			bo.setPcDjxh(dom.getPcDjxh());
			bo.setWfhDjxh(wfhDjxh);
			bo.setDdDjxh(dom.getDdDjxh());
			bo.setXh(dom.getXh());
			bo.setSzHwSl(dom.getHwSl());
			bo.setSzHwZl(dom.getHwZl());
			bo.setSzHwTj(dom.getHwTj());
			if(StringUtils.isNotBlank(dom.getYqDdrq())){
				bo.setYqDdrq(SysDateUtil.parse(dom.getYqDdrq()));
			}
			bo.setShfsDm(dom.getShfsDm());
			bo.setSzJsSl(dom.getJsSl());
			bo.setHdbh(dom.getHdbh());
			bo.setHdjsrq(SysDateUtil.getCurrentTime());
			bo.setSpbz("N");
			bo.setYxbz("Y");
			
			businessSqlMapClientTemplate.insert("insertHyPcHddj", bo);
		}
		return bo.getHdDjxh();
	}

	
	public void saveWlssDj(HyPcHddjDomain domain, UserDomain userDomain)
			throws Exception {
		HyZyglFydjDomain fydjDomain=new HyZyglFydjDomain();
		HyWlssdj bo = new HyWlssdj();
		fydjDomain.setPcDjxh(domain.getPcDjxh());
		fydjDomain.setWfhDjxh(domain.getWfhDjxh());
		HyZyglFydjDomain hwDom = (HyZyglFydjDomain) this.selectHyPcHwxxWhenWlss(fydjDomain);
		bo.setWlssyyWhXh("");
		bo.setHjSr(Double.valueOf(0));
		bo.setKhDjxh(hwDom.getFhrDjxh());
		bo.setPcDjxh(domain.getPcDjxh());
		bo.setWfhDjxh(domain.getWfhDjxh());
		bo.setZl(domain.getZhwZl());
		bo.setTj(domain.getZhwTj());
		bo.setSl(domain.getZhwSl());
		bo.setDdDjxh(domain.getDdDjxh());
		bo.setHwmxxh(hwDom.getXh());
		bo.setJsSl("");
		bo.setWlssLybz("1");
		bo.setBz(domain.getBz());
		bo.setPcygCzyDjxh("");
		bo.setSpbz("N");
		bo.setWsspztDm(String.valueOf(0));
		bo.setWsSpxh("");
		bo.setSsJgbm(userDomain.getGsbm());
		bo.setDjJgbm(userDomain.getBmbm());
		bo.setYxbz("Y");
		bo.setCjrCzyDjxh(userDomain.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getSqlDate().toString());
		bo.setXgrCzyDjxh(userDomain.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getSqlDate().toString());
		Map<String, String> map=new HashMap<String, String>();
		map.put("wfhDjxh", domain.getWfhDjxh());
		map.put("pcDjxh", domain.getPcDjxh());
		HyPcHddjDomain  hdDomain=(HyPcHddjDomain)businessSqlMapClientTemplate.queryForObject("selectWlssDjSlZlTj",map);
		if(hdDomain!=null){
			businessSqlMapClientTemplate.update("updateWlssDjByWfhDjxh",map);
			bo.setZl(String.valueOf(Integer.parseInt(domain.getZhwSl())+Integer.parseInt(hdDomain.getWlssHwSl())));
			bo.setTj(String.valueOf(Integer.parseInt(domain.getZhwZl())+Integer.parseInt(hdDomain.getWlssHwZl())));
			bo.setSl(String.valueOf(Integer.parseInt(domain.getZhwTj())+Integer.parseInt(hdDomain.getWlssHwTj())));
		}
		
		businessSqlMapClientTemplate.insert("insertHyWlssdj",bo);
		fydjDomain.setWlssDjxh(bo.getWlssDjxh());
		this.wlssDjHxcl(fydjDomain,userDomain);
	}
	
	public BaseBusinessDomain selectHyPcHwxxWhenWlss(BaseBusinessDomain baseDomain) throws Exception{
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		HyZyglFydjDomain dom = (HyZyglFydjDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxWhenWlss",map);
		return dom;
	}
	
	public void wlssDjHxcl(BaseBusinessDomain baseDomain, UserDomain user)
	throws Exception {
			HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("wlDjxh", domain.getWlssDjxh());
			map.put("bm", user.getBmbm());
			map.put("czyDjxh", user.getCzyDjxh());
			map.put("count", 0);
			map.put("error", "");
			businessSqlMapClientTemplate.queryForObject("checkSaveWlssdj",map);
			if(StringUtils.isNotBlank((String)map.get("error"))&&(Integer)map.get("count")!=0){
				throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("error")));
			}

	}
}
