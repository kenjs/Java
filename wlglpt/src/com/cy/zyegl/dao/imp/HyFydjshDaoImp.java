package com.cy.zyegl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zyegl.dao.HyFydjshDao;
import com.cy.zyegl.domain.HyFydjshDomain;

/**
 * The DAOIMP for 货运-费用登记审核.
 * 
 * @author HJH
 */

@Repository
public class HyFydjshDaoImp implements HyFydjshDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain userDomain)  throws Exception {
		HyFydjshDomain domain = (HyFydjshDomain) baseDomain;
		List<BaseBusinessDomain> dataList = new ArrayList<BaseBusinessDomain>();
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
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyFydjshList","dataList",map); 
		dataList = (List<BaseBusinessDomain>)map.get("dataList");	
		return dataList;
	}
	
}
