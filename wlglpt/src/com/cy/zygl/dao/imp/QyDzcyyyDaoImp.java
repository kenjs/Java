package com.cy.zygl.dao.imp;

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
import com.cy.common.bo.QyDzcyyy;
import com.cy.zygl.dao.QyDzcyyyDao;
import com.cy.zygl.domain.QyDzcyyyDomain;

/**
 * The DAOIMP for 企业-对账差异原因维护.
 * 
 * @author HJH
 */

@Repository
public class QyDzcyyyDaoImp implements QyDzcyyyDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		if(StringUtils.isNotBlank(domain.getDzcyyy())){
			map.put("dzcyyy", "%"+domain.getDzcyyy()+"%");
		}

		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyDzcyyyRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyDzcyyyPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		if(StringUtils.isNotBlank(domain.getDzcyyy())){
			map.put("dzcyyy", "%"+domain.getDzcyyy()+"%");
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyDzcyyyAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain) baseDomain;
		domain.setSsJgbm(user.getZgsbm());
		QyDzcyyy bo = new QyDzcyyy();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyDzcyyyDomain dom = (QyDzcyyyDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDzcyyy(domain.getDzcyyy());

			bo.setSm(domain.getSm());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateQyDzcyyyByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyDzcyyy", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("whXh", domain.getWhXh());


		domain = (QyDzcyyyDomain)businessSqlMapClientTemplate.queryForObject("selectQyDzcyyyByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("whXh", domain.getWhXh());

		businessSqlMapClientTemplate.update("deleteQyDzcyyyByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWhXh())){
			QyDzcyyyDomain dom = (QyDzcyyyDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
