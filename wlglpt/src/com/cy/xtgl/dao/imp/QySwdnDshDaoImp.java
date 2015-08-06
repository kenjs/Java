package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QySwdn;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.xtgl.dao.QySwdnDshDao;
import com.cy.xtgl.domain.QySwdnDshDomain;

/**
 * The DAOIMP for 企业-上网电脑-待审核.
 * 
 * @author HaoY
 */

@Repository
public class QySwdnDshDaoImp implements QySwdnDshDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQySwdnDshRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQySwdnDshPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQySwdnDshAll", map);
		return dataList;
	}

	public void dispass(QySwdnDshDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		//设置查询条件
		map.put("shjg", "2");
		map.put("ggDjxh", domain.getGgDjxh());
		businessSqlMapClientTemplate.update("updateQySwdnDshByKey", map);
	}

	public void pass(QySwdnDshDomain domain, UserDomain user) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		//设置查询条件
		map.put("shjg", "1");
		map.put("ggDjxh", domain.getGgDjxh());
		businessSqlMapClientTemplate.update("updateQySwdnDshByKey", map);
		insertQySwdn(domain,user);
	}
	
	/**
	 * 审核通过插入一条数据
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	private void insertQySwdn(QySwdnDshDomain domain, UserDomain user) throws Exception {
		QySwdnDshDomain qySwdnDshDomain = getQySwdnDshDomainByKey(domain);
		QySwdn bo = new QySwdn();
		
		BeanUtils.copyProperties(bo,qySwdnDshDomain);
		
		bo.setQybz("Y");
		bo.setYxbz("Y");
		bo.setCjrCzyDjxh(user.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getCurrentDate());
		bo.setXgrCzyDjxh(user.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getCurrentDate());
		
		businessSqlMapClientTemplate.insert("insertQySwdn",bo);
	}
	
	/**
	 * 根据主键查询QySwdnDshDomain对象
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	private QySwdnDshDomain getQySwdnDshDomainByKey(QySwdnDshDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		//设置查询条件
		map.put("ggDjxh", domain.getGgDjxh());
		return (QySwdnDshDomain)businessSqlMapClientTemplate.queryForObject("selectQySwdnDshByKey",map);
	}
	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain) throws Exception {
		return null;
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		
	}

	public void saveDomain(BaseBusinessDomain domain, UserDomain user) throws Exception {
		
	}
}
