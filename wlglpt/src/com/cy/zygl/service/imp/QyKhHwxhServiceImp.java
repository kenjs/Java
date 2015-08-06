package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.zygl.dao.QyKhHwxhDao;
import com.cy.zygl.domain.QyKhHwxhDomain;
import com.cy.zygl.service.QyKhHwxhService;

@Service
/**
 * The SERVICEIMP for 企业-客户-货物信息.
 * 
 * @author HJH
 */
public class QyKhHwxhServiceImp extends BaseBusinessServiceImp implements QyKhHwxhService {
	@Autowired
	private QyKhHwxhDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		QyKhHwxhDomain domain = (QyKhHwxhDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain=(QyKhHwxhDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseBusinessDomain;
		int count = dao.checkHwxhmc(domain);
		if (count>0) {
			userDomain.setIsLoginSuccess(false);
			ServiceException se = new ServiceException();
			se.setErrorCode("120301");
			throw se;
		}
	}
}
