package com.cy.dzgl.dao.imp;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.bo.QyWsSpZb;
import com.cy.dzgl.dao.QyWsSpZbDao;
import com.cy.dzgl.domain.QyWsSpZbDomain;

/**
 * The DAOIMP for 企业-文书-审批-子表.
 * 
 * @author HJH
 */

@Repository
public class QyWsSpZbDaoImp implements QyWsSpZbDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	

	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyWsSpZbDomain domain = (QyWsSpZbDomain) baseDomain;
		QyWsSpZb bo = new QyWsSpZb();

		QyWsSpZbDomain dom = (QyWsSpZbDomain) this.getDomainByKey(domain);
		if(null==dom)
			return;
		
		BeanUtils.copyProperties(bo, dom);
		
		//bo.setSprCzyDjxh(domain.getSprCzyDjxh());
		bo.setSprCzyDjxh(user.czyDjxh);
		//bo.setSprq(domain.getSprq());
		//bo.setSprq(SysDateUtil.getCurrentDate());
		bo.setSpjg(domain.getSpjg());
		bo.setSpyj(domain.getSpyj());
		
		businessSqlMapClientTemplate.update("updateQyWsSpZbByKey", bo);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyWsSpZbDomain domain = (QyWsSpZbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wsSpxh", domain.getWsSpxh());
		map.put("spxh", domain.getSpxh());

		domain = (QyWsSpZbDomain)businessSqlMapClientTemplate.queryForObject("selectQyWsSpZbByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyWsSpZbDomain domain = (QyWsSpZbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("wsSpxh", domain.getWsSpxh());
		map.put("spxh", domain.getSpxh());

		businessSqlMapClientTemplate.update("deleteQyWsSpZbByKey", map);
	}

}
