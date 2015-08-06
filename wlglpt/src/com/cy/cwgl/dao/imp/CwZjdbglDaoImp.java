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
import com.cy.common.bo.CwZjdb;
import com.cy.cwgl.dao.CwZjdbglDao;
import com.cy.cwgl.domain.CwZjdbglDomain;

/**
 * The DAOIMP for 财务-资金调拨管理.
 * 
 * @author HJH
 */

@Repository
public class CwZjdbglDaoImp implements CwZjdbglDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("dcDwDjxh", domain.getDcDwDjxh());
		if(StringUtils.isNotBlank(domain.getDrDwDjxh())){
			map.put("drDwDjxh", domain.getDrDwDjxh());
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwZjdbglRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwZjdbglPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("dcDwDjxh", domain.getDcDwDjxh());
		if(StringUtils.isNotBlank(domain.getDrDwDjxh())){
			map.put("DrDwDjxh", domain.getDrDwDjxh());
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwZjdbglAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain) baseDomain;
		CwZjdb bo = new CwZjdb();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		CwZjdbglDomain dom = (CwZjdbglDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDrDwDjxh(domain.getDrDwDjxh());
			bo.setJe(domain.getJe());
			bo.setBz(domain.getBz());

			bo.setYxbz("Y");
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateCwZjdbglByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setDcDwDjxh(user.getGsbm());
			bo.setRq(SysDateUtil.getSqlDate().toString());
			bo.setYxbz("Y");
			bo.setSpbz("N");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertCwZjdbgl", bo);
			
			domain.setZjdbDjxh(bo.getZjdbDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("zjbdDjxh", domain.getZjdbDjxh());

		domain = (CwZjdbglDomain)businessSqlMapClientTemplate.queryForObject("selectCwZjdbglByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("zjdbDjxh", domain.getZjdbDjxh());

		businessSqlMapClientTemplate.update("deleteCwZjdbglByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getZjdbDjxh())){
			CwZjdbglDomain dom = (CwZjdbglDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public void callPCwglZjdbHxcl(String zjbdDjxh, String ssJgbm,String czyDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zjdbDjxh", zjbdDjxh);
		map.put("jgbm", ssJgbm);
		map.put("czyDjxh", czyDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPCwglZjdbHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
}
