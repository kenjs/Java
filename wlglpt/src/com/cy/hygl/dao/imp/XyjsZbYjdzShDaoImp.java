package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.hygl.dao.XyjsZbYjdzShDao;
import com.cy.hygl.domain.XyjsZbYjdzShDomain;

/**
 * The DAOIMP for 财务-发票开票登记
 * 
 * @author LYY
 */

@Repository
public class XyjsZbYjdzShDaoImp implements XyjsZbYjdzShDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<XyjsZbYjdzShDomain> queryAllList(BaseBusinessDomain baseDomain,UserDomain user)  throws Exception {
		XyjsZbYjdzShDomain domain = (XyjsZbYjdzShDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		List<XyjsZbYjdzShDomain> dataList=null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("djxh", user.getCzyDjxh());
		map.put("shbz", domain.getShbz());
		map.put("rqq", domain.getRqq());
		map.put("rqz", domain.getRqz());
		map.put("dataList", dataList);
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryListZbYjdzSh", "dataList", map);
		dataList=(List<XyjsZbYjdzShDomain>)map.get("dataList");
		return dataList;
	}

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveDomain(BaseBusinessDomain domain, UserDomain user)
			throws Exception {
		// TODO Auto-generated method stub
		
	}



	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
