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

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysSqlInUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.HyPz;
import com.cy.hygl.dao.HyPzDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyPzDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.hygl.domain.PzQingdanDomain;
import com.sun.org.apache.xml.internal.serializer.Encodings;

/**
 * The DAOIMP for 货运-配载.
 * 
 * @author HJH
 */

@Repository
public class HyPzDaoImp implements HyPzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("ddbh", domain.getDdbh());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPzxxglPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("ddbh", domain.getDdbh());
		map.put("pageNum", 1);
		map.put("pageSize", 99999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPzxxglPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		HyPz bo = new HyPz();

		BeanUtils.copyProperties(bo, domain);
		
		if(StringUtils.isNotBlank(domain.getPzDjxh())){
			bo.setXgrCzyDjxh(user.czyDjxh);
			bo.setXgrq(SysDateUtil.getSqlDate());

			businessSqlMapClientTemplate.update("updateHyPzByKey", bo);
		}else{
			bo.setDjJgbm(user.bmbm);
			bo.setSsJgbm(user.gsbm);
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());

			businessSqlMapClientTemplate.insert("insertHyPz", bo);
			domain.setPzDjxh(bo.getPzDjxh());
		}
	}
	
	/**
	 * 将派车时选择的货物信息保存到临时表，每个派车单对应一个临时序号
	 */
	@SuppressWarnings("unchecked")
	public void saveWfhxx4Pz(HyPzDomain domain) throws Exception {
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		
		String wfhXhs = domain.getWfhXhs();
		String xhsIn = SysSqlInUtil.getParameterArray(wfhXhs.split(","), "C.WFH_DJXH");
		
		if (StringUtils.isBlank(domain.getPchwLsxh())) {
			String lsxh = this.selectPchwLsxh();
			domain.setPchwLsxh(lsxh);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pzDjxh", domain.getPzDjxh());
		map.put("xhsIn", xhsIn);
		map.put("lsxh", domain.getPchwLsxh());
		String pchwClfsDm = "22";
		map.put("pchwClfsDm", pchwClfsDm);
		
		businessSqlMapClientTemplate.insert("saveWfhxx4Pz", map);
	}
	
	public void deleteWfhxxTmp4Pz(HyPzDomain domain) throws Exception {
		List<String> wfhXhs = domain.getHwXh4PcDel();
		String[] xhs = new String[wfhXhs.size()];
		System.arraycopy(wfhXhs.toArray(), 0, xhs, 0, xhs.length);
		String xhsIn = SysSqlInUtil.getParameterArray(xhs, "TMP.WFH_DJXH ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xhsIn", xhsIn);
		map.put("lsxh", domain.getPchwLsxh());
		businessSqlMapClientTemplate.delete("deleteWfhxxTmp4Pc", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<HyTydWfhxxDomain> queryPzHwxxByPzXh(HyPzDomain domain) throws Exception {
		if (StringUtils.isBlank(domain.getPzDjxh()) && StringUtils.isBlank(domain.getPchwLsxh())) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<HyTydWfhxxDomain> dataList = null;
		
		map.put("lsXh", domain.getPchwLsxh());
		map.put("pzDjxh", domain.getPzDjxh());
		dataList = businessSqlMapClientTemplate.queryForList("queryPzHwxxByLsxhAndPzxh", map);
		
		return dataList;
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pzDjxh", domain.getPzDjxh());

		domain = (HyPzDomain)businessSqlMapClientTemplate.queryForObject("selectHyPzByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("pzDjxh", domain.getPzDjxh());

		businessSqlMapClientTemplate.update("deleteHyPzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPzDjxh())){
			HyPzDomain dom = (HyPzDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	public String selectPchwLsxh() throws Exception {
		return (String) businessSqlMapClientTemplate.queryForObject("selectPchwLsxh");
	}

	
	public List<BaseBusinessDomain> onQingdan(HyPzDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("pzDjxh", domain.getPzDjxh());
		map.put("zt", SysEncodeUtil.UTF82ISO("合计"));
		List<BaseBusinessDomain> list=businessSqlMapClientTemplate.queryForList("queryPzQingdanList",map);
		return list;
	}
	
	public String queryZgsMc(UserDomain user){
		Map<String, String> map=new HashMap<String, String>();
		map.put("zgs", user.getZgsbm());
		String mc=(String)businessSqlMapClientTemplate.queryForObject("queryZgsMc",map);
		return mc;
	}
	
	/**
	 * 配载预览货物
	 */
	@SuppressWarnings("unchecked")
	public List<HyTydWfhxxDomain> viewPz(HyPzDomain domain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("lsXh", domain.getPchwLsxh());
		map.put("pzDjxh", domain.getPzDjxh());
		
		return  businessSqlMapClientTemplate.queryForList("queryPzHwxxByLsxhAndPzxh4View", map);
	}
	
	/**
	 * 获取货站名称
	 * @param hzJgbm
	 * @return
	 * @throws Exception
	 */
	public String getHzmcByHzJgbm(String hzJgbm) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("hzJgbm", hzJgbm);
		
		return (String) businessSqlMapClientTemplate.queryForObject("getHzmcByHzJgbm", map);
	}
	
	/**
	 * 获取车辆型号
	 * @param clxhwhDjxh
	 * @return
	 * @throws Exception
	 */
	public String getClxhByClxhWhxh(String clxhwhDjxh) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("clxhwhDjxh", clxhwhDjxh);
		
		return (String) businessSqlMapClientTemplate.queryForObject("getClxhByClxhWhxh", map);
	}
}
