package com.cy.cwgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwHbzcyeCxDao;
import com.cy.cwgl.domain.CwHbzcyeCxDomain;
import com.cy.cwgl.service.CwHbzcyeCxService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-货币资产余额查询
 * 
 * @author HCM
 */
@Service
public class CwHbzcyeCxServiceImp extends BaseBusinessServiceImp implements CwHbzcyeCxService {
	@Autowired
	private CwHbzcyeCxDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcyeCxDomain domain = (CwHbzcyeCxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
}
