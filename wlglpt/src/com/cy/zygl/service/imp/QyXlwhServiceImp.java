package com.cy.zygl.service.imp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.dao.QyXlwhDao;
import com.cy.zygl.domain.QyXlwhDomain;
import com.cy.zygl.service.QyXlwhService;
import com.cy.common.domain.UserDomain;


@Service
/**
 * The SERVICEIMP for 企业-线路维护.
 * 
 * @author HJH
 */
public class QyXlwhServiceImp extends BaseBusinessServiceImp implements QyXlwhService {
	@Autowired
	private QyXlwhDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain=(QyXlwhDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
