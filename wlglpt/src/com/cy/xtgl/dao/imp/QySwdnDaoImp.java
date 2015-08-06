package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.xtgl.dao.QySwdnDao;
import com.cy.xtgl.domain.QySwdnDshDomain;
/**
 * The DAOIMP for 上网电脑管理.
 * 
 * @author HaoY
 */
@Repository
public class QySwdnDaoImp implements QySwdnDao {

	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
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
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getSwdnglRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectSwdnglPage", map,start,pagSize);
		
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectSwdnglAll", map);
		return dataList;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("ggDjxh", domain.getGgDjxh());
		businessSqlMapClientTemplate.update("updateSwdnglYXBZByKey",map);
	}
	
	public void startUse(BaseBusinessDomain baseDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("ggDjxh", domain.getGgDjxh());
		map.put("qybz", "Y");
		businessSqlMapClientTemplate.update("updateSwdnglQYBZByKey",map);
	}

	public void stopUse(BaseBusinessDomain baseDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		//设置查询条件
		map.put("ggDjxh", domain.getGgDjxh());
		map.put("qybz", "N");
		businessSqlMapClientTemplate.update("updateSwdnglQYBZByKey",map);
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain) throws Exception {
		return null;
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		
	}

	public void saveDomain(BaseBusinessDomain domain, UserDomain user) throws Exception {
	}
}
