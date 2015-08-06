package com.cy.dzgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QySpws;
import com.cy.common.domain.UserDomain;
import com.cy.dzgl.dao.QySpwsDao;
import com.cy.dzgl.domain.QySpwsDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DAOIMP for 企业-审批文书.
 * 
 * @author HaoY
 */

@Repository
public class QySpwsDaoImp implements QySpwsDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QySpwsDomain domain = (QySpwsDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		if(StringUtils.isNotBlank(domain.getYwflDm())){
			map.put("ywflDm", domain.getYwflDm());
		}
		if(StringUtils.isNotBlank(domain.getYwhjDm())){
			map.put("ywhjDm", domain.getYwhjDm());
		}
		
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQySpwsRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQySpwsPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		if(StringUtils.isNotBlank(domain.getYwflDm())){
			map.put("ywflDm", domain.getYwflDm());
		}
		if(StringUtils.isNotBlank(domain.getYwhjDm())){
			map.put("ywhjDm", domain.getYwhjDm());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQySpwsAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QySpwsDomain domain = (QySpwsDomain) baseDomain;
		domain.setJgbm(user.getZgsjbdm());
		QySpws bo = new QySpws();
		//根据主键查询对象
		QySpwsDomain dom = (QySpwsDomain) this.getDomainByKey(domain);
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setWsspmsDm(domain.getWsspmsDm());
			bo.setYxbz("Y");
			
			businessSqlMapClientTemplate.update("updateQySpwsByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setJgbm(user.getZgsjbdm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQySpws", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("wsDm", domain.getWsDm().trim());
		
		domain = (QySpwsDomain)businessSqlMapClientTemplate.queryForObject("selectQySpwsByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("wsDm", domain.getWsDm().trim());
		
		businessSqlMapClientTemplate.update("deleteQySpwsByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
	
	}

}
