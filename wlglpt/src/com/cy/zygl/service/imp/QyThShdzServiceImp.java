package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyThShdzDao;
import com.cy.zygl.domain.QyThShdzDomain;
import com.cy.zygl.service.QyThShdzService;

@Service
/**
 * The SERVICEIMP for 企业-提货收货地址维护.
 * 
 * @author HJH
 */
public class QyThShdzServiceImp extends BaseBusinessServiceImp implements QyThShdzService {
	@Autowired
	private QyThShdzDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain=(QyThShdzDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
