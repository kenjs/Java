package com.cy.hygl.dao.imp;

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

import com.cy.common.bo.JsDdHwxx;
import com.cy.common.bo.JsSrdz;
import com.cy.common.bo.JsSrdzDzcy;
import com.cy.common.bo.JsSrdzDzcyTemp;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.hygl.dao.HyJsglSrdzDao;
import com.cy.hygl.domain.HyJsglSrdzDomain;
import com.cy.hygl.domain.JsDdHwxxDomain;
import com.cy.hygl.domain.JsSrdzDomain;
import com.cy.hygl.domain.JsSrdzDzcyDomain;

/**
 * The DAOIMP for 收入对账
 * 
 * @author HJH
 */

@Repository
public class HyJsglSrdzDaoImp implements HyJsglSrdzDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		
		List<BaseBusinessDomain> dataList = new ArrayList<BaseBusinessDomain>();
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		// 分页相关
		PageDomain page = domain.getPage();
		
		if(SysToolsUtil.isNullOrEmpty(domain.getBmDm())){
			map.put("dwbm", domain.getDwDm());
			map.put("dwbmbz", "D");
		}else{
			map.put("dwbm", domain.getBmDm());//部门
			map.put("dwbmbz", "B");
		}
		
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
			map.put("khMc", domain.getKhMc());
		
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		
		if(StringUtils.isNotBlank(domain.getDdbh())){
			map.put("ddbh",domain.getDdbh().trim());
		}
		if(StringUtils.isNotBlank(domain.getDzztDm())){
			map.put("dzztDm", domain.getDzztDm());
		}
		if(StringUtils.isNotBlank(domain.getYjZtDm())){
			map.put("yjZtDm", domain.getYjZtDm());
		}
		
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		//按订单
		if("1".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzDdPage","dataList",map); 
		}
		//按订单货物
		if("2".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzDdHwPage","dataList",map); 		
		}
		//按派车
		if("3".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzPcPage","dataList",map); 
		}
		//按派车货物
		if("4".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzPcHwPage","dataList",map); 
		}
		//按回单
		if("5".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzHdPage","dataList",map); 
		}
		
		page.setTotalRecords((Integer)map.get("pageCount"));
		page.setReccount((Integer)map.get("reccount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");	
			
		if (dataList == null || dataList.size() == 0)
			dataList = new ArrayList<BaseBusinessDomain>();
		return dataList;
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseDomain;
		List<BaseBusinessDomain> dataList = new ArrayList<BaseBusinessDomain>();
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		// 分页相关
		PageDomain page = domain.getPage();
		
		if(SysToolsUtil.isNullOrEmpty(domain.getBmDm())){
			map.put("dwbm", domain.getDwDm());
			map.put("dwbmbz", "D");
		}else{
			map.put("dwbm", domain.getBmDm());//部门
			map.put("dwbmbz", "B");
		}
		
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		
		if(StringUtils.isNotBlank(domain.getDdbh())){
			map.put("ddbh",domain.getDdbh().trim());
		}
		if(StringUtils.isNotBlank(domain.getDzztDm())){
			map.put("dzztDm", domain.getDzztDm());
		}
		
		map.put("pageNum", 1);
		map.put("pageSize", 9999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		//按订单
		if("1".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzDdPage","dataList",map); 
		}
		//按订单货物
		if("2".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzDdHwPage","dataList",map); 		
		}
		//按派车
		if("3".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzPcPage","dataList",map); 
		}
		//按派车货物
		if("4".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzPcHwPage","dataList",map); 
		}
		//按回单
		if("5".equals(domain.getDzfsDm())){
			businessSqlMapClientTemplate.queryForObjectByCurr("queryJsglSrdzHdPage","dataList",map); 
		}
		
		page.setTotalRecords((Integer)map.get("pageCount"));
		page.setReccount((Integer)map.get("reccount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");	
			
		if (dataList == null || dataList.size() == 0)
			dataList = new ArrayList<BaseBusinessDomain>();

		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsSrdzDomain domain = (JsSrdzDomain) baseDomain;
		JsSrdz bo = new JsSrdz();

		BeanUtils.copyProperties(bo, domain);
		if(StringUtils.isNotBlank(domain.getDzDjxh())) {
			businessSqlMapClientTemplate.update("updateJsSrdzByKey", bo);
		}else{
			bo.setYxbz("Y");
			bo.setDzrCzyDjxh(user.getCzyDjxh());//对帐人
			bo.setDzrq(SysDateUtil.getCurrentDate());//对帐日期
			bo.setDzJgbm(user.bmbm);
			bo.setSpbz("N");

			businessSqlMapClientTemplate.insert("insertJsSrdz", bo);
			domain.setDzDjxh(bo.getDzDjxh());
		}
	}
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		JsSrdzDomain domain = (JsSrdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", domain.getDzDjxh());

		businessSqlMapClientTemplate.update("deleteJsSrdzByKey", map);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzDomain domain = (JsSrdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("dzDjxh", domain.getDzDjxh());

		domain = (JsSrdzDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzByKey", map);
		return domain;
	}
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzDomain domain = (JsSrdzDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getJsDjxh())){
			JsSrdzDomain dom = (JsSrdzDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	//初始化结算-收入对帐
	public BaseBusinessDomain getJsSrdzByJsDjxh(JsSrdzDomain domain) throws Exception {
		if(SysToolsUtil.isNullOrEmpty(domain.getJsDjxh()))
			return null;
		HyJsglSrdzDomain dom=null;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jsDjxh", domain.getJsDjxh());
		//按订单
		if("1".equals(domain.getDzfsDm())){
			dom=(HyJsglSrdzDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzByJsDjxh_1", map);
		}
		//按订单货物
		if("2".equals(domain.getDzfsDm())){
			dom=(HyJsglSrdzDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzByJsDjxh_2", map);
		}
		//按派车
		if("3".equals(domain.getDzfsDm())){
			
		}
		//按派车货物
		if("4".equals(domain.getDzfsDm())){
			
		}
		//按回单
		if("5".equals(domain.getDzfsDm())){
	
		}
		//domain=(HyJsglSrdzDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzByJsDjxh", map);
		return dom;
	}
	
	//结算-收入对帐-对帐差异-临时表List
	@SuppressWarnings("unchecked")
	public List<JsSrdzDzcyDomain> queryDzcyTempList(String dzDjxh) throws Exception {
		List<JsSrdzDzcyDomain> dataList=new ArrayList<JsSrdzDzcyDomain>();
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", dzDjxh);

		dataList=businessSqlMapClientTemplate.queryForList("queryDzcyTempList", map);
		
		return dataList;
	}

	//结算-收入对帐-对帐差异List
	@SuppressWarnings("unchecked")
	public List<JsSrdzDzcyDomain> queryDzcyList(String dzDjxh) throws Exception {
		List<JsSrdzDzcyDomain> dataList=new ArrayList<JsSrdzDzcyDomain>();
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", dzDjxh);

		dataList=businessSqlMapClientTemplate.queryForList("queryDzcyList", map);
		
		return dataList;
		
	}
	
	public void saveDzcyDomain(JsSrdzDzcyDomain domain, UserDomain user) throws Exception {
		JsSrdzDzcy bo = new JsSrdzDzcy();
		JsSrdzDzcyDomain dom = (JsSrdzDzcyDomain) this.getDomainDzcyByKey(domain);
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDzDjxh(domain.getDzDjxh());
			bo.setXh(domain.getXh());
			bo.setDzcyje(domain.getDzcyje());
			bo.setDzcyyyDm(domain.getDzcyyyDm());
			bo.setDzcyClfsDm(domain.getDzcyClfsDm());
			bo.setBz(domain.getBz());
			bo.setXcJsDjxh(domain.getXcJsDjxh());
			bo.setWlssDjxh(domain.getWlssDjxh());

			
			businessSqlMapClientTemplate.update("updateJsSrdzDzcyByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			businessSqlMapClientTemplate.insert("insertJsSrdzDzcy", bo);
		}
	}

	public BaseBusinessDomain getDomainDzcyByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzDzcyDomain domain = (JsSrdzDzcyDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("dzDjxh", domain.getDzDjxh());
		map.put("xh", domain.getXh());

		domain = (JsSrdzDzcyDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzDzcyByKey", map);
		return domain;
	}

	public void deleteDzcyByKey(String dzDjxh,String xh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", dzDjxh);
		map.put("xh", xh);

		businessSqlMapClientTemplate.update("deleteJsSrdzDzcyByKey", map);
	}
	
	public void deleteDzcyByDzDjxh(String dzDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", dzDjxh);

		businessSqlMapClientTemplate.update("deleteDzcyByDzDjxh", map);
	}

	public void initDomainDzcyMx(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzDzcyDomain domain = (JsSrdzDzcyDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getDzDjxh())){
			JsSrdzDzcyDomain dom = (JsSrdzDzcyDomain) this.getDomainDzcyByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}else{
				domain.setXh(this.queryDzcyNextXh(domain.getDzDjxh()).toString());
			}
		}

	}
	public void saveDzcyTempDomain(JsSrdzDzcyDomain domain, UserDomain user) throws Exception {
		JsSrdzDzcyTemp bo = new JsSrdzDzcyTemp();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		JsSrdzDzcyDomain dom = (JsSrdzDzcyDomain) this.getDomainDzcyTempByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDzcyje(domain.getDzcyje());
			bo.setDzcyyyDm(domain.getDzcyyyDm());
			bo.setDzcyClfsDm(domain.getDzcyClfsDm());
			bo.setBz(domain.getBz());
			bo.setXcJsDjxh(domain.getXcJsDjxh());
			bo.setWlssDjxh(domain.getWlssDjxh());
			bo.setRq(SysDateUtil.getCurrentDate());

			
			businessSqlMapClientTemplate.update("updateJsSrdzDzcyTempByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setRq(SysDateUtil.getCurrentDate());
			bo.setXh(this.queryDzcyTempNextXh(domain.getDzDjxh()).toString());
			businessSqlMapClientTemplate.insert("insertJsSrdzDzcyTemp", bo);
		}
	}

	public JsSrdzDzcyDomain getDomainDzcyTempByKey(JsSrdzDzcyDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("dzDjxh", domain.getDzDjxh());
		map.put("xh", domain.getXh());

		domain = (JsSrdzDzcyDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzDzcyTempByKey", map);
		return domain;
	}

	public void deleteDzcyTempByKey(String dzDjxh, String xh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", dzDjxh);
		map.put("xh", xh);

		businessSqlMapClientTemplate.update("deleteJsSrdzDzcyTempByKey", map);
	}
	
	public void deleteDzcyTempByDzDjxh(String dzDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", dzDjxh);

		businessSqlMapClientTemplate.update("deleteJsSrdzDzcyTempByDzDjxh", map);
	}

	public void initDomainDzcyTempMx(JsSrdzDzcyDomain domain) throws Exception {
		if(StringUtils.isNotBlank(domain.getDzDjxh())){
			JsSrdzDzcyDomain dom = (JsSrdzDzcyDomain) this.getDomainDzcyTempByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}else{
				domain.setXh(this.queryDzcyTempNextXh(domain.getDzDjxh()).toString());
			}
		}

	}
	
	public Integer queryDzcyTempNextXh(String dzDjxh) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dzDjxh", dzDjxh);
		Integer jdxh = (Integer)businessSqlMapClientTemplate.queryForObject("queryDzcyTempNextXh", map);
		return jdxh;
	}
	
	public Integer queryDzcyNextXh(String dzDjxh) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dzDjxh", dzDjxh);
		Integer jdxh = (Integer)businessSqlMapClientTemplate.queryForObject("queryDzcyNextXh", map);
		return jdxh;
	}
	public Integer getCountSrdz(String jsDjxh) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("jsDjxh", jsDjxh);
		Integer len = (Integer)businessSqlMapClientTemplate.queryForObject("getCountSrdz", map);
		return len;
	}
	public boolean checkSrdz(String jsDjxh) throws Exception{
		return this.getCountSrdz(jsDjxh)>0;
	}
	//货运管理-结算管理-收入对帐（订单）-后续处理（对帐保存后，再调本PROD）
	public void callHyglJsglSrdzDdHxcl(HyJsglSrdzDomain domain, UserDomain user) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dzDjxh", domain.getDzDjxh());
		map.put("jgbm", user.bmbm);
		map.put("czyDjxh", user.czyDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callHyglJsglSrdzDdHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	
	public void callHyglJsglSrdzDdhwHxcl(HyJsglSrdzDomain domain, UserDomain user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dzDjxh", domain.getDzDjxh());
		map.put("jgbm", user.bmbm);
		map.put("czyDjxh", user.czyDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callHyglJsglSrdzDdhwHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	public BaseBusinessDomain getJsDdHwxxDomain(String jsDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jsDjxh", jsDjxh);
		JsDdHwxxDomain domain = (JsDdHwxxDomain)businessSqlMapClientTemplate.queryForObject("selectJsDdHwxxByKey", map);
		return domain;
	}
	public void saveJsDdHwxxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsDdHwxxDomain domain = (JsDdHwxxDomain) baseDomain;
		JsDdHwxx bo = new JsDdHwxx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		JsDdHwxxDomain dom = (JsDdHwxxDomain) this.getJsDdHwxxDomain(domain.getJsDjxh());

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDdDjxh(domain.getDdDjxh());
			bo.setXh(domain.getXh());
			bo.setHwmc(domain.getHwmc());
			bo.setHwDjxh(domain.getHwDjxh());
			bo.setHwxhDjxh(domain.getHwxhDjxh());
			bo.setHwBzHldwDm(domain.getHwBzHldwDm());
			bo.setHwSl(domain.getHwSl());
			bo.setHwSlJldwDm(domain.getHwSlJldwDm());
			bo.setHwZl(domain.getHwZl());
			bo.setHwZlJldwDm(domain.getHwZlJldwDm());
			bo.setHwTj(domain.getHwTj());
			bo.setHwTjJldwDm(domain.getHwTjJldwDm());
			bo.setJsSl(domain.getJsSl());
			bo.setJsJldwDm(domain.getJsJldwDm());
			bo.setJsJldwFlDm(domain.getJsJldwFlDm());
			bo.setHdbh(domain.getHdbh());
			bo.setSfdXzqhDm(domain.getSfdXzqhDm());
			bo.setMddXzqhDm(domain.getMddXzqhDm());
			bo.setDzSr(domain.getDzSr());
			bo.setDzYj(domain.getDzYj());
			bo.setDzWj(domain.getDzWj());
			bo.setDcjsbz(domain.getDcjsbz());
			bo.setQcDzDjxh(domain.getQcDzDjxh());
			bo.setDzztDm(domain.getDzztDm());
			bo.setDzDjxh(domain.getDzDjxh());

			bo.setYxbz("Y");

			businessSqlMapClientTemplate.update("updateJsDdHwxxByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");

			businessSqlMapClientTemplate.insert("insertJsDdHwxx", bo);
			
			domain.setJsDjxh(bo.getJsDjxh());
		}
	}
	public void deleteJsDdHwxxDomainByKey(String jsDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("jsDjxh",jsDjxh);

		businessSqlMapClientTemplate.update("deleteJsDdHwxxByKey", map);
	}
	public List<HyJsglSrdzDomain> getWlssxl(String ddDjxh,String xh){
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ddDjxh", ddDjxh);
		map.put("xh", xh);
		map.put("str1", SysEncodeUtil.GBK2ISO(" 损失数量"));
		map.put("str2", SysEncodeUtil.GBK2ISO(" ,共"));
		map.put("str3", SysEncodeUtil.GBK2ISO("元"));
		List list = businessSqlMapClientTemplate.queryForList("getWlssxl", map);
		return list;
	}
	/**
	 * 根据结算登记序号获取对账方式信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JsSrdzDomain getDzInfo(String jsDjxh) throws Exception {
		JsSrdzDomain dom = null;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jsDjxh", jsDjxh);
		dom = (JsSrdzDomain) businessSqlMapClientTemplate.queryForObject("getDzfsDm", map);
		return  dom == null ? (JsSrdzDomain) businessSqlMapClientTemplate.queryForObject("getDzfsDm2", map) : dom;
	}
}
