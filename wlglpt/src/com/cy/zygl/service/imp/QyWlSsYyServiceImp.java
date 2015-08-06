package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyClxhwhDao;
import com.cy.zygl.dao.QyWlSsYyDao;
import com.cy.zygl.domain.QyClxhwhDomain;
import com.cy.zygl.domain.WlSsYyDomain;
import com.cy.zygl.service.QyClxhwhService;
import com.cy.zygl.service.QyWlSsYyService;

@Service
/**
 * The SERVICEIMP for 企业-车辆型号维护.
 * 
 * @author HJH
 */
public class QyWlSsYyServiceImp extends BaseBusinessServiceImp implements QyWlSsYyService {
	@Autowired
	private QyWlSsYyDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain=(WlSsYyDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
