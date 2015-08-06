package com.cy.xtgl.dao.imp;

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
import com.cy.common.bo.QyWsdysz;
import com.cy.xtgl.dao.QyWsdyszDao;
import com.cy.xtgl.domain.QyWsdyszDomain;

/**
 * The DAOIMP for 企业-文书打印设置.
 * 
 * @author HJH
 */

@Repository
public class QyWsdyszDaoImp implements QyWsdyszDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyWsdyszRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyWsdyszPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyWsdyszAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		QyWsdysz bo = new QyWsdysz();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyWsdyszDomain dom = (QyWsdyszDomain) this.getDomainByWsdm(domain);
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setWsDm(domain.getWsDm());
			bo.setLeftMargin(domain.getLeftMargin());
			bo.setTopMargin(domain.getTopMargin());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyWsdyszByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSsJgbm(user.getGsbm());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyWsdysz", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("whXh", domain.getWhXh());

		domain = (QyWsdyszDomain)businessSqlMapClientTemplate.queryForObject("selectQyWsdyszByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("whXh", domain.getWhXh());

		businessSqlMapClientTemplate.update("deleteQyWsdyszByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWhXh())){
			QyWsdyszDomain dom = (QyWsdyszDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}
	public BaseBusinessDomain getDomainByWsdm(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wsDm", domain.getWsDm());
		map.put("ssJgbm", domain.getSsJgbm());
		domain = (QyWsdyszDomain)businessSqlMapClientTemplate.queryForObject("getDomainByWsdm", map);
		return domain;
	}
}
