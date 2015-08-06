package com.cy.jcgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.jcgl.dao.JcFcQkMxDao;
import com.cy.jcgl.domain.JcFcQkMxDomain;
import com.cy.jcgl.service.JcFcQkMxService;


@Service
/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-5-4
 * @author LYY
 */
public class JcFcQkMxServiceImp extends BaseBusinessServiceImp implements JcFcQkMxService {
	@Autowired
	private JcFcQkMxDao dao;
	
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcFcQkMxDomain domain=(JcFcQkMxDomain)baseBusinessDomain;
		
	}
	
	

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcFcQkMxDomain domain=(JcFcQkMxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList=dao.queryList(domain,userDomain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcFcQkMxDomain domain = (JcFcQkMxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain,userDomain);
		domain.setDataList(dataList);
	}
	
}
