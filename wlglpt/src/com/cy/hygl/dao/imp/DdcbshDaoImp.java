package com.cy.hygl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.hygl.dao.DdcbshDao;
import com.cy.hygl.domain.DdcbshDomain;

/**
 * The DAOIMP for 调度成本审核.
 * 
 * @author HJH
 */

@Repository
public class DdcbshDaoImp extends ExtendDaoImp implements DdcbshDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<DdcbshDomain> queryList(DdcbshDomain domain, UserDomain userDomain)  throws Exception {
		// 分页相关
		PageDomain page = domain.getPage();

		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("czyDjxh", userDomain.czyDjxh);
		map.put("shbz", domain.getShbz());
		
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		List<DdcbshDomain> dataList = new ArrayList<DdcbshDomain>();
		map.put("dataList", dataList);

		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryDdcbshList", "dataList", map);
		
		return (List<DdcbshDomain>)map.get("dataList");
	}
	
	@SuppressWarnings("unchecked")
	public List<DdcbshDomain> downloadList(DdcbshDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<DdcbshDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectDdcbshAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		DdcbshDomain domain = (DdcbshDomain) baseDomain;
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件

		domain = (DdcbshDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcByKey", map);
		return domain;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain) baseDomain;
		/*if(StringUtils.isNotBlank(domain.getPcDjxh())){
			DdcbshDomain dom = (DdcbshDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}*/

	}
}
