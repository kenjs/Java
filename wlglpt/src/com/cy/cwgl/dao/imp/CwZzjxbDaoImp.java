package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.CwZzjxb;
import com.cy.cwgl.dao.CwZzjxbDao;
import com.cy.cwgl.domain.CwZzjxbDomain;

/**
 * The DAOIMP for 财务-周转金下拨.
 * 
 * @author HJH
 */

@Repository
public class CwZzjxbDaoImp implements CwZzjxbDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		
		List<BaseBusinessDomain> dataList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("zgsbm", domain.getZgsbm());
		map.put("rq", domain.getRq());
		map.put("pageNum", 1);
		map.put("pageSize", 999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		

		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("selectCwZzjxbPage", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		
		List<BaseBusinessDomain> dataList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("zgsbm", domain.getZgsbm());
		map.put("rq", domain.getRq());
		map.put("pageNum", 1);
		map.put("pageSize", 999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		

		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("selectCwZzjxbPage", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		CwZzjxb bo = new CwZzjxb();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		CwZzjxbDomain dom = (CwZzjxbDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setXbje(domain.getXbje());
			bo.setBz(domain.getBz());
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateCwZzjxbByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSpbz("N");
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertCwZzjxb", bo);
			
			domain.setZjdbDjxh(bo.getZjdbDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("zjdbDjxh", domain.getZjdbDjxh());
		
		domain = (CwZzjxbDomain)businessSqlMapClientTemplate.queryForObject("selectCwZzjxbByKey", map);
		return domain;
	}
	public void getCwZzjxbxxWhenAdd(BaseBusinessDomain baseDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jgbm", domain.getJsDwDjxh());
		map.put("rq", domain.getRq());
		
		CwZzjxbDomain dom = (CwZzjxbDomain)businessSqlMapClientTemplate.queryForObject("getCwZzjxbxxWhenAdd", map);
		
		BeanUtils.copyProperties(domain, dom);
	}
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("zjdbDjxh", domain.getZjdbDjxh());

		businessSqlMapClientTemplate.update("deleteCwZzjxbByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getZjdbDjxh())){
			CwZzjxbDomain dom = (CwZzjxbDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}
	public void callPCwglZzjxbHxcl(String zjbdDjxh, String ssJgbm,String czyDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zjdbDjxh", zjbdDjxh);
		map.put("jgbm", ssJgbm);
		map.put("czyDjxh", czyDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPCwglZzjxbHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
}
