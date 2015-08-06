package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.WlglptCommonService;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.QyRyGwDao;
import com.cy.xtgl.domain.QyRyGwDomain;
import com.cy.xtgl.service.QyRyGwService;

/**
 * The SERVICEIMP for 企业-人员-岗位.
 * 
 * @author HaoY
 */
@Service
public class QyRyGwServiceImp extends BaseBusinessServiceImp implements QyRyGwService {
	@Autowired
	private QyRyGwDao dao;
	@Autowired
	private WlglptCommonService commonService;
	
	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		// 根据人员的所属部门初始化人员的所属分公司
		domain.setGsbm(commonService.getSjJgbmByJgbm(domain.getSsJgbm()));
	}
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		if(dao.checkYhgw(baseBusinessDomain) > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110901");
			throw se;
		}
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRyGwDomain domain=(QyRyGwDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

}
