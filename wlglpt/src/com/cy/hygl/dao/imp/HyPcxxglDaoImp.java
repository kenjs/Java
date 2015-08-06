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

import bsh.StringUtil;

import com.cy.common.bo.HyPc;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysSqlInUtil;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyQingDanDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;


/**
 * The DAOIMP for 货运-派车信息管理
 * 
 * @date 2013.1.29
 * @author 闫伟
 */
@Repository
public class HyPcxxglDaoImp extends ExtendDaoImp implements HyPcxxglDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
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
		map.put("cyrClhm", domain.getCyrClhm());
		map.put("cyrSjxm", domain.getCyrSjxm());
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("pcfsDm", domain.getPcfsDm());
		map.put("dingdan", domain.getDingdan());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcxxglPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
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
		map.put("cyrClhm", domain.getCyrClhm());
		map.put("cyrSjxm", domain.getCyrSjxm());
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("pcfsDm", domain.getPcfsDm());
		map.put("dingdan", domain.getDingdan());
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcxxglPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		HyPc bo = new HyPc();

		if ("4".equals(domain.getPcfsDm())) {
			bo.setPcDjxh(domain.getPcDjxh());
			bo.setPcdh(domain.getPcdh());
			bo.setPcfsDm(domain.getPcfsDm());
			bo.setYsfsDm(domain.getYsfsDm());
			bo.setZcfsDm(domain.getZcfsDm());
		}else {
			BeanUtils.copyProperties(domain, bo);
		}
		
		//HyPcxxglDomain dom = (HyPcxxglDomain)this.getDomainByKey(domain);
		
		// 主键不为空那么为修改 ，否则为添加
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());

			businessSqlMapClientTemplate.update("updateHyPcByKey", bo);
		}else{
			bo.setYfjsfDm("12");
			bo.setYxbz("Y");
			bo.setPcrCzyDjxh(user.getCzyDjxh());
			//bo.setPcrq(new java.util.Date());
			bo.setPcJgbm(user.getBmbm());
			bo.setSsJgbm(user.getGsbm());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(new java.util.Date());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(new java.util.Date());
			bo.setSpbz("N");

			businessSqlMapClientTemplate.insert("insertHyPc", bo);
		}
		domain.setPcDjxh(bo.getPcDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		if (StringUtils.isBlank(domain.getPcDjxh())) {
			return null;
		}
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", domain.getPcDjxh());

		domain = (HyPcxxglDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcByKey", map);
		return domain;
	}
	
	public HyPcxxglDomain queryHyPcYfxxByKey(String pcDjxh) throws Exception {
		if (StringUtils.isBlank(pcDjxh)) {
			return null;
		}
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);

		return (HyPcxxglDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcByKey", map);
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("pcDjxh", domain.getPcDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcxxglDomain dom = (HyPcxxglDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom,domain);
			}
		}

	}
	
	public String selectPchwLsxh() throws Exception {
		return (String) businessSqlMapClientTemplate.queryForObject("selectPchwLsxh");
	}
	
	/**
	 * 将派车时选择的货物信息保存到临时表，每个派车单对应一个临时序号
	 */
	@SuppressWarnings("unchecked")
	public void saveWfhxx4Pc(HyPcxxglDomain domain) throws Exception {
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		SysEncodeUtil.decodeURL(domain.getPcHwxxDomain());
		SysEncodeUtil.conGBK2ISO(domain.getPcHwxxDomain());
		
		String wfhXhs = domain.getWfhXhs();
		String xhsIn = SysSqlInUtil.getParameterArray(wfhXhs.split(","), "C.WFH_DJXH");
		
		if (StringUtils.isBlank(domain.getPchwLsxh())) {
			String lsxh = this.selectPchwLsxh();
			domain.setPchwLsxh(lsxh);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("xhsIn", xhsIn);
		map.put("lsxh", domain.getPchwLsxh());
		String pchwClfsDm = "";// = domain.getPcfsDm().equals("1") ? "11" : domain.getPcfsDm().equals("2") ? "22" : "32";
		
		if ("42".equals(domain.getPcHwxxDomain().getPchwClfsDm())) {  // 转包
			pchwClfsDm = domain.getPcHwxxDomain().getPchwClfsDm();
		}else if ("1".equals(domain.getPcfsDm())) {
			if (StringUtils.isNotBlank(domain.getZrbmDjxh())) {
				pchwClfsDm = "12";     // 提货到下游
			}else {
				pchwClfsDm = "11";		// 提货到仓库
			}
		}else if ("2".equals(domain.getPcfsDm())) {
			if(StringUtils.isBlank(domain.getZrbmDjxh())){
				pchwClfsDm = "21";
			}else if (StringUtils.isNotBlank(domain.getZrbmDjxh()) && "2".equals(domain.getZrbmDm())) {
				pchwClfsDm = "22";     // 送货到站(默认都为送货到站，没有中转运输)
			}else if (StringUtils.isNotBlank(domain.getZrbmDjxh()) && "3".equals(domain.getZrbmDm())) {
				pchwClfsDm = "22";     // 送货到站 (默认都为送货到站，没有中转运输)
			}
			// 若为配送派车，并且货站为空，则派车货物处理方式为'31'配送到门
		}else if (domain.getPcfsDm().equals("3")) {
			if (StringUtils.isNotBlank(domain.getZrbmDjxh())) {
				pchwClfsDm = "32";		// 中转配送
			}else {
				pchwClfsDm = "31";      // 配送上门
			}			
		}
		map.put("pchwClfsDm", pchwClfsDm);
		
		map.put("zrbmDm", domain.getZrbmDm());
		map.put("zrbmDjxh", domain.getZrbmDjxh());
		map.put("zrbmXzqhDm", domain.getZrbmXzqhDm());
		map.put("zrbmDz", domain.getZrbmDz());
		map.put("zrbmLxr", domain.getZrbmLxr());
		map.put("zrbmLxdh", domain.getZrbmLxdh());
		map.put("zcHj", domain.getPcHwxxDomain().getZcHj());
		map.put("zcYj", domain.getPcHwxxDomain().getZcYj());
		map.put("zcXf", domain.getPcHwxxDomain().getZcXf());
		map.put("zcHf", domain.getPcHwxxDomain().getZcHf());
		map.put("zcHk", domain.getPcHwxxDomain().getZcHk());
		
		businessSqlMapClientTemplate.insert("saveWfhxx4Pc", map);
	}
	
	public void deleteWfhxxTmp4Pc(HyPcxxglDomain domain) throws Exception {
		List<String> wfhXhs = domain.getHwXh4PcDel();
		String[] xhs = new String[wfhXhs.size()];
		System.arraycopy(wfhXhs.toArray(), 0, xhs, 0, xhs.length);
		String xhsIn = SysSqlInUtil.getParameterArray(xhs, "TMP.WFH_DJXH ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xhsIn", xhsIn);
		map.put("lsxh", domain.getPchwLsxh());
		businessSqlMapClientTemplate.delete("deleteWfhxxTmp4Pc", map);
	}
	
	public void deleteHyPcHwxxByKey(String pcDjxh, String wfhDjxh, String bbh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);
		map.put("bbh", bbh);
		businessSqlMapClientTemplate.update("deleteHyPcHwxxByKey", map);
	}
	
	public void bakPcHwxx2Tmp(HyPcxxglDomain domain) throws Exception {
		String lsxh = this.selectPchwLsxh();
		domain.setPchwLsxh(lsxh);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("lsxh", lsxh);
		businessSqlMapClientTemplate.insert("bakPcHwxx2Tmp", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<HyTydWfhxxDomain> queryPcHwxxByPcXh(HyPcxxglDomain domain) throws Exception {
		if (StringUtils.isBlank(domain.getPcDjxh()) && StringUtils.isBlank(domain.getPchwLsxh())) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<HyTydWfhxxDomain> dataList = null;
		/*if (StringUtils.isNotBlank(domain.getPchwLsxh())) {
			map.put("lsXh", domain.getPchwLsxh());
			dataList = businessSqlMapClientTemplate.queryForList("queryPcHwxxByLsxh", map);
		} else if (StringUtils.isNotBlank(domain.getPcDjxh())) {
			map.put("pcDjxh", domain.getPcDjxh());
			dataList = businessSqlMapClientTemplate.queryForList("queryPcHwxxByPcXh", map);
		}*/
		
		map.put("lsXh", domain.getPchwLsxh());
		map.put("pcDjxh", domain.getPcDjxh());
		dataList = businessSqlMapClientTemplate.queryForList("queryPcHwxxByLsxhAndPcxh", map);
		
		return dataList;
	}
	
	public void updatePchwxxBbh(HyPcxxglDomain domain) throws Exception {
		if (StringUtils.isBlank(domain.getPcDjxh()) && StringUtils.isBlank(domain.getPchwLsxh())) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(domain.getPcDjxh())) {
			map.put("tableName", "HY_PC_HWXX");
			map.put("pcXh", domain.getPcDjxh());
			businessSqlMapClientTemplate.update("updatePchwxxBbh", map);
		}
		if (StringUtils.isNotBlank(domain.getPchwLsxh())) {
			map = new HashMap<String, Object>();
			map.put("tableName", "HY_PC_HWXX_TMP");
			map.put("pcXh", domain.getPchwLsxh());
			businessSqlMapClientTemplate.update("updatePchwxxBbh", map);
		}
		
	}
	
	public void callPHyglDdglPcxxHxcl(HyPcxxglDomain domain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPHyglDdglPcxxHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	
	public void callPHyglDdglPcxxDelete(HyPcxxglDomain domain, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("czyDjxh", userDomain.getCzyDjxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPHyglDdglPcxxDelete", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	
	public void deletePcHwxxTmpByLsXh(String lsXh) throws Exception {
		if (StringUtils.isBlank(lsXh)) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lsXh", lsXh);
		businessSqlMapClientTemplate.delete("deletePcHwxxTmpByLsXh", map);
	}
	
	public void deletePcHwxxByPcXh(HyPcxxglDomain domain) throws Exception {
		if (StringUtils.isBlank(domain.getPcDjxh())) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", domain.getPcDjxh());
		
		businessSqlMapClientTemplate.delete("deletePcHwxxByPcXh", map);
	}
	
	public Integer queryQyClxxCountBySsbmbm(String bmbm, String clsxDm) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("bmbm", bmbm);
		map.put("clsxDm", clsxDm);
		return (Integer)businessSqlMapClientTemplate.queryForObject("queryQyClxxCountBySsbmbm", map);
	}
	
	public void queryPcxxSjsInitVal(HyPcxxglDomain domain) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pchwLsxh", domain.getPchwLsxh());
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("yfSjs", 0.0D);
		map.put("bz", "");
		businessSqlMapClientTemplate.queryForObject("queryPcxxSjsInitVal", map);
		if (map.get("yfSjs") != null) {
			domain.setYfSjs((Double)map.get("yfSjs"));
		}
		if (map.get("bz") != null) {
			domain.setBz(SysEncodeUtil.ISO2GBK((String)map.get("bz")));
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
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<HyTydWfhxxDomain> queryWfhxx(HyPcxxglDomain domain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页相关
		PageDomain page = domain.getPage();
		List<HyTydWfhxxDomain> dataList = null;
		// 设置查询条件
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		map.put("dw4Query", domain.getDw4Query());
		map.put("fhrXzqhDm", domain.getFhrXzqhDm());
		map.put("shrXzqhDm", domain.getShrXzqhDm());
		map.put("ddbh4Query", domain.getDdbh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		map.put("khMc4Query", domain.getFhrMc());
		map.put("fhrqQ", domain.getFhrqQ());
		map.put("fhrqZ", domain.getFhrqZ());
		map.put("djJgbm4Query", domain.getDjJgbm4Query());
		map.put("lb4Query", domain.getLb4Query());
		map.put("hwztDm4Query", domain.getHwztDm4Query());
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pcfsDm", domain.getPcfsDm());
		map.put("bz", "1");
		map.put("pchwLsxh", domain.getPchwLsxh());
		map.put("pcDjxh", domain.getPcDjxh());
		
		map.put("pageCount",0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyTydwfhxxPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<HyTydWfhxxDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<HyQingDanDomain> qingdan(HyPcxxglDomain domain) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		PageDomain page = domain.getPage();
		List<HyQingDanDomain> dataList=null;
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyQingDan", "dataList", map);
		
		//page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<HyQingDanDomain>)map.get("dataList");
		for (HyQingDanDomain hyQingDanDomain : dataList) {
			String dz=hyQingDanDomain.getShrDz();
			String fhr=hyQingDanDomain.getFhr();
			if(dz!=null&&!dz.equals("")){
				if(dz.length()>5){
					hyQingDanDomain.setShrDz(dz.substring(0,5));
				}
			}
			if(fhr!=null&&!fhr.equals("")){
				if(fhr.length()>5){
					hyQingDanDomain.setFhr(fhr.substring(0,5));
				}
			}
		}
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	
	public String queryZgs(UserDomain userDomain) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("zgs", userDomain.getZgsbm());
		String mc=(String)businessSqlMapClientTemplate.queryForObject("queryZgsMc",map);
		return mc;
	}
	
	public Double getQrPsf(String wfhDjxh) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("wfhDjxh", wfhDjxh);
		Double psf=(Double)businessSqlMapClientTemplate.queryForObject("getQrPsf",map);
		return psf;
	}
	/*public Integer queryExistWfhxxCount(List<String> wfhDjxhs) throws Exception {
		String[] xhs = new String[wfhDjxhs.size()];
		System.arraycopy(wfhDjxhs.toArray(), 0, xhs, 0, xhs.length);
		String xhsIn = SysSqlInUtil.getParameterArray(xhs, "WFH_DJXH ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xhsIn", xhsIn);
		
		return (Integer)businessSqlMapClientTemplate.queryForObject("queryExistWfhxxCount", map);
	}*/
	
}
