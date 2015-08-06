package com.cy.cwgl.dao.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwFpKpdj;
import com.cy.common.bo.CwKpdj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 财务-发票开票登记
 * 
 * @author LYY
 */

@Repository
public class CwFpKpdjDaoImp implements CwFpKpdjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if(StringUtils.isNotBlank(domain.getKpdwJgbm())){
			map.put("kpdwJgbm", domain.getKpdwJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getKhmc())){
			map.put("khmc", SysEncodeUtil.GBK2ISO(domain.getKhmc()));
		}
		
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwFpKpdjRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectCwFpKpdjPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getKpdwJgbm())){
			map.put("kpdwJgbm", domain.getKpdwJgbm());
		}
		if(StringUtils.isNotBlank(domain.getFhrDjxh())){
			map.put("khDjxh", domain.getFhrDjxh());
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("khmc", SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwFpKpdjAll", map);
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> proMap = new HashMap<String, Object>();
		CwFpKpdjDomain domain = (CwFpKpdjDomain) baseDomain;
		CwKpdj kpdjBo = new CwKpdj();
		CwFpKpdj bo = new CwFpKpdj();
		bo.setKpsqDjxh(domain.getKpsqDjxh());
		bo.setBzsm(domain.getBzsm());
		bo.setSpbz("N");
		businessSqlMapClientTemplate.update("updateCwFpKpdjByKey", bo);
		kpdjBo.setKpsqDjxh(domain.getKpsqDjxh());
		kpdjBo.setKhDjxh(domain.getKhDjxh());
		
		//判断发票代码和发票号码不全一样
		map.put("fpdm", domain.getFpdm());
		map.put("fphm", domain.getFphm());
		int count = ((Integer)businessSqlMapClientTemplate.queryForObject("selectFpdmAndFphm", map)).intValue();
		if(count != 0){
			domain.setFpdmAndFphm("true");
			throw new DiyServiceException("发票号码已经存在，请重新录入！");
		}
		else{
			kpdjBo.setFpdm(domain.getFpdm());
			kpdjBo.setFphm(domain.getFphm());
			kpdjBo.setXgrCzyDjxh(user.getCzyDjxh());
			kpdjBo.setKprq(domain.getKprq());
			kpdjBo.setXgrq(date);
			kpdjBo.setKprCzyDjxh(domain.getKprCzyDjxh());
			Double kpje = 0.0;
			Double sl = 0.0;
			try{
				kpje = Double.valueOf(domain.getKpje());
				sl = Double.valueOf(domain.getSl());
			}catch(Exception e){
			}
			kpdjBo.setKpje(kpje);
			kpdjBo.setSl(sl);
			kpdjBo.setSe(kpje * sl);
			kpdjBo.setDjJgbm(domain.getDjJgbm());
			kpdjBo.setSsJgbm(domain.getSsJgbm());
			kpdjBo.setZfbz("N");
			kpdjBo.setHzbz("N");
			
			kpdjBo.setCjrCzyDjxh(user.getCzyDjxh());
			kpdjBo.setCjrq(date);
			businessSqlMapClientTemplate.insert("insertCwKpdj", kpdjBo);
			
			domain.setKpDjxh(kpdjBo.getKpDjxh()); 
		}
			
	}
	public void callPHyglCwKpdjZfHxcl(String kpdjxh,String kpsqDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kpDjxh", kpdjxh);
		map.put("kpsqDjxh", kpsqDjxh);
		map.put("rtnCode", 0);
		map.put("errMesge", "");
		
		businessSqlMapClientTemplate.queryForObject("cwKpdjZfhxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("errMesge");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());

		domain = (CwFpKpdjDomain)businessSqlMapClientTemplate.queryForObject("selectCwFpKpdjByKey", map);
		return domain;
	}


	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getKpsqDjxh())){
			CwFpKpdjDomain dom = (CwFpKpdjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public int queryKhmcCount(CwFpKpdjDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());
		map.put("kpdwJgbm", domain.getKpdwJgbm());
		map.put("khmc", SysEncodeUtil.UTF82ISO(domain.getKhmc()));
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("queryKhmcCount", map))).intValue();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<CwFpKpdjDomain> queryXzqhList(CwFpKpdjDomain domain) throws Exception{
		//Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		List<CwFpKpdjDomain>  data = businessSqlMapClientTemplate.queryForList("queryXzqhList", null);
		return data;
	}

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		
	}

}
