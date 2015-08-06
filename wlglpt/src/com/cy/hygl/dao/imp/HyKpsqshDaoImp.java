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

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyKpsqshDao;
import com.cy.hygl.domain.HyKpsqshDomain;

/**
 * The DAOIMP for ����-��Ʊ�������.
 * 
 * @author LYY
 */

@Repository
public class HyKpsqshDaoImp implements HyKpsqshDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain userDomain)  throws Exception {
		HyKpsqshDomain domain = (HyKpsqshDomain) baseDomain;
		List<BaseBusinessDomain> dataList = new ArrayList<BaseBusinessDomain>();
		Map<String, Object> map = new HashMap<String, Object>();
		// ���ò�ѯ����
		
		map.put("czyDjxh", userDomain.czyDjxh);
		map.put("shbz", domain.getShbz());
		
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		
		// ��������
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyKpsqshList","dataList",map); 
		dataList = (List<BaseBusinessDomain>)map.get("dataList");	
		return dataList;
	}
	
}
