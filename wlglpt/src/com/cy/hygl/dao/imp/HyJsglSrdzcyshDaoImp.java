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
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyJsglSrdzcyshDao;
import com.cy.hygl.domain.HyJsglSrdzcyshDomain;

/**
 * The DAOIMP for 收入对帐差异审核
 * 
 * @author HJH
 */

@Repository
public class HyJsglSrdzcyshDaoImp implements HyJsglSrdzcyshDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain userDomain)  throws Exception {
		HyJsglSrdzcyshDomain domain = (HyJsglSrdzcyshDomain) baseDomain;
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
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyJsglSrdzcyshList","dataList",map); 
		dataList = (List<BaseBusinessDomain>)map.get("dataList");	
		return dataList;
	}
}
