package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.zygl.dao.QyKhHwxxDao;
import com.cy.zygl.domain.QyKhHwxxDomain;
import com.cy.zygl.service.QyKhHwxxService;

@Service
/**
 * The SERVICEIMP for 企业-客户-货物信息.
 * 
 * @author HJH
 */
public class QyKhHwxxServiceImp extends BaseBusinessServiceImp implements QyKhHwxxService {
	@Autowired
	private QyKhHwxxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		QyKhHwxxDomain domain = (QyKhHwxxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain=(QyKhHwxxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseBusinessDomain;
		int count = dao.checkHwmc(domain);
		if (count>0) {
			userDomain.setIsLoginSuccess(false);
			ServiceException se = new ServiceException();
			se.setErrorCode("120201");
			throw se;
		}
	}
}
