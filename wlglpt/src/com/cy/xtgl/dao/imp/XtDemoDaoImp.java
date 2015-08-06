package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.xtgl.dao.XtDemoDao;

@Repository
public class XtDemoDaoImp implements XtDemoDao {

	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	public List<DmbGgDomain> queryGsBmList(UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qxJgbm", userDomain.qxJgbm);
		map.put("mcContainDmBz", "N");
		List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList("getGsBmDdlbList", map);
		
		return dataList;
	}
	
}
