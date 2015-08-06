package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.CwQtsr;
import com.cy.cwgl.dao.CwQtsrDao;
import com.cy.cwgl.domain.CwQtsrDomain;

/**
 * The DAOIMP for 财务-其他收入.
 * 
 * @author HJH
 */

@Repository
public class CwQtsrDaoImp implements CwQtsrDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwQtsrDomain domain = (CwQtsrDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = 0;
		int pagSize = 99999;

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rq", domain.getRq());

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwQtsrRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwQtsrPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwQtsrDomain domain = (CwQtsrDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwQtsrAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwQtsrDomain domain = (CwQtsrDomain) baseDomain;
		CwQtsr bo = new CwQtsr();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		CwQtsrDomain dom = (CwQtsrDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setSsJgbm(domain.getSsJgbm());
			bo.setRq(domain.getRq());
			bo.setXmmc(domain.getXmmc());
			bo.setJe(domain.getJe());
			bo.setZcflDm(domain.getZcflDm());
			bo.setYhCshDjxh(domain.getYhCshDjxh());
			bo.setBz(domain.getBz());
			bo.setFkf(domain.getFkf());

			
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateCwQtsrByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertCwQtsr", bo);
			
			domain.setCwDjxh(bo.getCwDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwQtsrDomain domain = (CwQtsrDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwQtsrDomain)businessSqlMapClientTemplate.queryForObject("selectCwQtsrByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwQtsrDomain domain = (CwQtsrDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("cwDjxh", domain.getCwDjxh());

		businessSqlMapClientTemplate.update("deleteCwQtsrByKey", map);
	}
	public void deleteByKey(String cwDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("cwDjxh", cwDjxh);

		businessSqlMapClientTemplate.update("deleteCwQtsrByKey", map);
	}
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwQtsrDomain domain = (CwQtsrDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			CwQtsrDomain dom = (CwQtsrDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public void callPCwglQtsrHxcl(String cwDjxh, String ssJgbm,String czyDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cwDjxh", cwDjxh);
		map.put("jgbm", ssJgbm);
		map.put("czyDjxh", czyDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPCwglQtsrHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
}
