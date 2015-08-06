package com.cy.bggl.dao.imp;

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
import com.cy.framework.util.SysDateUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.BgWlryFl;
import com.cy.bggl.dao.BgWlryFlDao;
import com.cy.bggl.domain.BgWlryFlDomain;

/**
 * The DAOIMP for 办公-外联人员-分类.
 * 
 * @author HaoY
 */

@Repository
public class BgWlryFlDaoImp implements BgWlryFlDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("flmc", domain.getFlmc());
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgWlryFlRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgWlryFlPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("flmc", domain.getFlmc());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgWlryFlAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain) baseDomain;
		BgWlryFl bo = new BgWlryFl();
		//如果wlryFlxh为空则执行insert，否则执行update
		if(domain != null && !"".equals(domain.getWlryFlxh().trim())){
			//根据主键查询对象
			BgWlryFlDomain dom = (BgWlryFlDomain) this.getDomainByKey(domain);
			
			BeanUtils.copyProperties(bo, dom);
			
			bo.setJgbm(domain.getJgbm());
			bo.setFlmc(domain.getFlmc());
			bo.setXjgxbz(domain.getXjgxbz());

			businessSqlMapClientTemplate.update("updateBgWlryFlByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertBgWlryFl", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wlryFlxh", domain.getWlryFlxh());

		domain = (BgWlryFlDomain)businessSqlMapClientTemplate.queryForObject("selectBgWlryFlByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("wlryFlxh", domain.getWlryFlxh());

		businessSqlMapClientTemplate.update("deleteBgWlryFlByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWlryFlxh())){
			BgWlryFlDomain dom = (BgWlryFlDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
