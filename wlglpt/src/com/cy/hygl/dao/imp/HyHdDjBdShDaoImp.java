package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.hygl.dao.HyHdDjBdShDao;
import com.cy.hygl.domain.DdcbshDomain;
import com.cy.hygl.domain.HyHdDjShDomain;
import com.cy.hygl.domain.HyWlSsDjShDomain;

/**
 * The DAOIMP for ���ȳɱ����.
 * 
 * @author HJH
 */

@Repository
public class HyHdDjBdShDaoImp implements HyHdDjBdShDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain user)  throws Exception {
		HyHdDjShDomain domain = (HyHdDjShDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		List<BaseBusinessDomain> dataList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank(domain.getShbz()))
			map.put("shbz", domain.getShbz());
	
		if(StringUtils.isNotBlank(domain.getRqQ()))
			map.put("pcrqQ", domain.getRqQ());
		
		if(StringUtils.isNotBlank(domain.getRqZ()))
			map.put("pcrqZ", domain.getRqZ());
		
	
			map.put("pcrCzyDjxh4Query",user.getCzyDjxh());
		map.put("dataList", dataList);
		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyHdDjBdSh", "dataList", map);
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		System.out.println(dataList.size());
		return dataList;
	}

	
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void saveDomain(BaseBusinessDomain domain, UserDomain user)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	


}
