package com.cy.cwgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwZjdbglDao;
import com.cy.cwgl.domain.CwZjdbglDomain;
import com.cy.cwgl.service.CwZjdbglService;

@Service
/**
 * The SERVICEIMP for 财务-资金调拨管理.
 * 
 * @author HJH
 */
public class CwZjdbglServiceImp extends BaseBusinessServiceImp implements CwZjdbglService {
	@Autowired
	private CwZjdbglDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setDcDwMc(userDomain.getGsjc());

	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain)baseBusinessDomain;
		domain.setDcDwDjxh(userDomain.getGsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain)baseBusinessDomain;
		domain.setDcDwDjxh(userDomain.getGsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain = (CwZjdbglDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		
		dao.callPCwglZjdbHxcl(domain.getZjdbDjxh(), userDomain.getGsbm(), userDomain.getCzyDjxh());
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjdbglDomain domain=(CwZjdbglDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		
		dao.callPCwglZjdbHxcl(domain.getZjdbDjxh(), userDomain.getGsbm(), userDomain.getCzyDjxh());
	}
}
