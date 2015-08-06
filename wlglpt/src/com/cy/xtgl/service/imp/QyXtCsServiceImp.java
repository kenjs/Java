package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.dao.QyXtCsDao;
import com.cy.xtgl.domain.QyXtCsDomain;
import com.cy.xtgl.service.QyXtCsService;
/**
 * The SERVICE for 企业-系统-参数信息.
 * 
 * @author HCM
 */
@Service
public class QyXtCsServiceImp extends BaseBusinessServiceImp implements QyXtCsService{
	@Autowired
	private QyXtCsDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		QyXtCsDomain domain = (QyXtCsDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}
	
	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXtCsDomain domain=(QyXtCsDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
