package com.cy.jcgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.jcgl.dao.JcJymlbDao;
import com.cy.jcgl.domain.JcYxsjbDomain;
import com.cy.jcgl.service.JcJymlbService;

@Service
/**
 * 
 */
public class JcJymlbServiceImp extends BaseBusinessServiceImp implements JcJymlbService {
	@Autowired
	private JcJymlbDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcYxsjbDomain domain = (JcYxsjbDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcYxsjbDomain domain = (JcYxsjbDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

}
