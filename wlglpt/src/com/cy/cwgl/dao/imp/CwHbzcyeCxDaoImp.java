package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwHbzcyeCxDao;
import com.cy.cwgl.domain.CwHbzcyeCxDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;

/**
 * The DAO for 财务-货币资产余额查询
 * 
 * @author HCM
 */
@Repository
public class CwHbzcyeCxDaoImp implements CwHbzcyeCxDao{
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("ssJgbm", domain.getSsJgbm());
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwHbzcyeCxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcyeCxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcyeCxAll", map);
		return dataList;
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwHbzcyeCxDomain)businessSqlMapClientTemplate.queryForObject("selectCwHbzcyeCxByKey", map);
		
		return domain;
	}
	
	@SuppressWarnings("unchecked")
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			CwHbzcyeCxDomain dom = (CwHbzcyeCxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}


	}

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void saveDomain(BaseBusinessDomain domain, UserDomain user)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
