package com.cy.cwgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.service.CwFpKpdjService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-开票登记
 * 
 * @author LYY
 */
@Service
public class CwFpKpdjServiceImp extends BaseBusinessServiceImp implements CwFpKpdjService{
	@Autowired
	private CwFpKpdjDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwFpKpdjDomain domain = (CwFpKpdjDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setKprCzyDjxh(userDomain.getCzyDjxh());
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		
		dao.callPHyglCwKpdjZfHxcl(domain.getKpDjxh(),domain.getKpsqDjxh());
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFpKpdjDomain domain=(CwFpKpdjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

}
