package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyTyd;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyTydglDao;
import com.cy.hygl.domain.HyTydglDomain;

/**
 * The DAOIMP for 货运-托运单管理.
 * 
 * @author HJH
 */

@Repository
public class HyTydglDaoImp implements HyTydglDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	//货物来源代码-托运单
	private static String DM_DDFL_TYD = "13";
	//货物状态-未发
	private static String DM_HWZT_WF = "21";
	//货物状态-未提
	private static String DM_HWZT_WT = "11";

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyTydglDomain domain = (HyTydglDomain) baseDomain;
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
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyTydPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain) baseDomain;
		//SysEncodeUtil.decodeURL(domain);
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
		//map.put("fhrMc4", domain.getFhrMc4());
		//map.put("shrMc4", domain.getShrMc4());
		//map.put("hwMc4", domain.getHwMc4());
		map.put("fhrMc4", SysEncodeUtil.GBK2ISO(domain.getFhrMc4()));
		map.put("shrMc4", SysEncodeUtil.GBK2ISO(domain.getShrMc4()));
		map.put("hwMc4", SysEncodeUtil.GBK2ISO(domain.getHwMc4()));
		
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
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyTydPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyTydglDomain domain = (HyTydglDomain) baseDomain;
		HyTyd bo = new HyTyd();
		
		if (domain.getThflDm() != null && domain.getThflDm().longValue() == 1) {
			domain.setHwztDm(DM_HWZT_WT);
		}else {
			domain.setHwztDm(DM_HWZT_WF);
		}
		
		BeanUtils.copyProperties(domain, bo);
		//客户
		bo.setKhDjxh(domain.getFhrDjxh());
		bo.setKhmc(domain.getFhrMc());
		
		HyTydglDomain dom = (HyTydglDomain)this.getDomainByKey(baseDomain);
		
		if(dom != null){
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());

			businessSqlMapClientTemplate.update("updateHyTydByKey", bo);
			//托单修改的时候，更新对应的托单货物信息对应的 发货人、收货人等信息(已不用)
			//businessSqlMapClientTemplate.update("updateTydHwmxFhShxx", domain);
			domain.setDdDjxh(bo.getDdDjxh());
		}else{
			bo.setDdflDm(DM_DDFL_TYD);
			bo.setDzztDm("1");
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate());
			//bo.setDjJgbm(user.getBmbm());
			//bo.setSsJgbm(user.getGsbm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());

			businessSqlMapClientTemplate.insert("insertHyTyd", bo);
			domain.setDdbh(bo.getDdbh());
			domain.setDdDjxh(bo.getDdDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键查询条件
		map.put("ddDjxh", domain.getDdDjxh());

		domain = (HyTydglDomain)businessSqlMapClientTemplate.queryForObject("selectHyTydByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键条件
		map.put("ddDjxh", domain.getDdDjxh());

		businessSqlMapClientTemplate.update("deleteHyTydByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain) baseDomain;
		if(domain.getDdDjxh() != null && domain.getDdDjxh().longValue() > 0){
			HyTydglDomain dom = (HyTydglDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom, domain);
			}
		}else{
			Map<String,Object> map = new HashMap<String, Object>();
			// 设置主键查询条件
			map.put("ssJgbm", domain.getSsJgbm());
			HyTydglDomain dom0 = (HyTydglDomain)businessSqlMapClientTemplate.queryForObject("selectHyTydMrxx", map);
			domain.setFhrXzqhDm(dom0.getFhrXzqhDm());
			domain.setFhrXzqhMc(dom0.getFhrXzqhMc());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryCopy(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		
		PageDomain page=domain.getPage();
		/*int start=page.getStart();
		int pagSize=page.getPageSize();*/
		Map<String, String>map=new HashMap<String, String>();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		
		if (StringUtils.isNotBlank(domain.getFhrDjxh())) {
			map.put("fhrDjxh", domain.getFhrDjxh());
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("fhrMc", SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		}
		if(StringUtils.isNotBlank(domain.getShrMc())){
			map.put("shrMc", SysEncodeUtil.GBK2ISO(domain.getShrMc()));
		}
		if(StringUtils.isNotBlank(domain.getXdrqQ())){
			map.put("xdrqQ", domain.getXdrqQ());
		}
		if(StringUtils.isNotBlank(domain.getXdrqZ())){
			map.put("xdrqZ", domain.getXdrqZ());
		}
		map.put("gsbm", user.getGsbm());
		
		/*int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getHyTydCopyCount", map))).intValue();
		page.setTotalRecords(count);
		return businessSqlMapClientTemplate.queryForList("queryHyTyd4Copy",map, start, pagSize);*/
		
		return businessSqlMapClientTemplate.queryForList("queryHyTyd4Copy",map);
	}
	
	public void callPHyglDdglTydglHxcl(Long ddDjxh, String xh, String xzQsbz) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ddDjxh", ddDjxh);
		map.put("xh", xh);
		map.put("xzQsbz", xzQsbz);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPHyglDdglTydglHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	
	public void callPHyglDdglTydglDelete(Long ddDjxh, String czyDjxh, String xzQsbz) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ddDjxh", ddDjxh);
		map.put("czyDjxh", czyDjxh);
		map.put("xzQsbz", xzQsbz);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPHyglDdglTydglDelete", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	
	public Integer queryYpchwNumByDdDjxh(Long ddDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ddDjxh", ddDjxh);
		return (Integer)businessSqlMapClientTemplate.queryForObject("queryYpchwNumByDdDjxh", map);
	}
	
	public Integer selectXh(BaseBusinessDomain baseBusinessDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ddDjxh", domain.getDdDjxh());
		return (Integer)businessSqlMapClientTemplate.queryForObject("selectXh", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<HyTydglDomain> checkDdbh(String ddbh,String ssJgbm) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ssJgbm", ssJgbm);
		map.put("ddbh", ddbh);
		return (List<HyTydglDomain>)businessSqlMapClientTemplate.queryForList("checkDdbh", map);
	}
}
