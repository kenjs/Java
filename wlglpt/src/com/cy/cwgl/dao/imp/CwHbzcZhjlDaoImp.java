package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwHbzcZhjl;
import com.cy.common.bo.CwHbzcye2;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwHbzcZhjlDao;
import com.cy.cwgl.domain.CwHbzcZhjlDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 财务-货币资产-转换记录.
 * 
 * @author HJH
 */

@Repository
public class CwHbzcZhjlDaoImp implements CwHbzcZhjlDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("oldZcflDm", domain.getLb());
		if(StringUtils.isNotBlank(domain.getYhzh())){
			map.put("yzh",SysEncodeUtil.UTF82ISO(domain.getYhzh()));
		}
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwHbzcZhjlRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcZhjlPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("oldZcflDm", domain.getLb());
		map.put("yzh", domain.getYhzh());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcZhjlAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) baseDomain;
		CwHbzcZhjl bo = new CwHbzcZhjl();

		bo.setSsJgbm(domain.getSsJgbm());
		bo.setOldZcflDm(domain.getOldZcflDm());
		bo.setOldYhCshDjxh(domain.getOldYhCshDjxh());
		bo.setNewZcflDm(domain.getNewZcflDm());
		bo.setNewYhCshDjxh(domain.getNewYhCshDjxh());
		bo.setZhje(new Double(domain.getZhje()));
		
		bo.setDjrCzyDjxh(user.getCzyDjxh());
		bo.setDjrq(domain.getRq());
		bo.setDjJgbm(user.getBmbm());
		bo.setYxbz("Y");

		businessSqlMapClientTemplate.insert("insertCwHbzcZhjl", bo);
		domain.setCwDjxh(bo.getCwDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwHbzcZhjlDomain)businessSqlMapClientTemplate.queryForObject("selectCwHbzcZhjlByKey", map);
		return domain;
	}
	public void deleteByYwxh(String ywxh) throws Exception {
		
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ywxh", ywxh);

		businessSqlMapClientTemplate.update("deleteCwHbzcBdjlByYwxh", map);
	}
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("cwDjxh", domain.getCwDjxh());

		businessSqlMapClientTemplate.update("deleteCwHbzcZhjlByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			CwHbzcZhjlDomain dom = (CwHbzcZhjlDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	public void saveZcBdjl(BaseBusinessDomain businessDomain, UserDomain user)
			throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) businessDomain;
		CwHbzcye2 bo = new CwHbzcye2();
		bo.setSsJgbm(domain.getSsJgbm());
		bo.setZcflDm(domain.getZcflDm());
		bo.setYhCshDjxh(domain.getYhDjxh());
		bo.setJe(new Double(domain.getJe()));
		bo.setDjJgbm(domain.getDjJgbm());
		bo.setSm(domain.getSm());
		bo.setJbrCzyDjxh(user.getCzyDjxh());
		bo.setRq(domain.getRq());
		bo.setBz(domain.getBz());
		bo.setYwxh(domain.getYwxh());
		bo.setYxbz("Y");
		businessSqlMapClientTemplate.insert("insertCwHbzcBdjl", bo);
	}

	public String getYe(BaseBusinessDomain businessDomain)
			throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) businessDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("yhCshDjxh", domain.getYhCshDjxh());
		String ye = (String) businessSqlMapClientTemplate.queryForObject("getYeForZh", map);
		return ye;
	}

	public void updateYe(BaseBusinessDomain businessDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) businessDomain;
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("yhCshDjxh", domain.getYhDjxh());
		map.put("je", domain.getBdJe());
		businessSqlMapClientTemplate.update("updateCwHbzcyeByKey",map);
	}

	public String getYhCshDjxh(BaseBusinessDomain businessDomain)
			throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain) businessDomain;
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("zcflDm", domain.getZcflDm());
		map.put("ssJgbm", domain.getSsJgbm());
		
		if("null".equals(domain.getYhCshDjxh())){
			map.put("yhCshDjxh", null);
		}else{
			map.put("yhCshDjxh", domain.getYhCshDjxh());
		}
		String yhDjxh =(String) businessSqlMapClientTemplate.queryForObject("getYhCshDjxh", map);
		return yhDjxh;
	}
}
