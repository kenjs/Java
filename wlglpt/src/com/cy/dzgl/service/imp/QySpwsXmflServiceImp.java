package com.cy.dzgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.dzgl.dao.QySpwsXmflDao;
import com.cy.dzgl.domain.QySpwsXmflDomain;
import com.cy.dzgl.service.QySpwsXmflService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;

/**
 * The SERVICEIMP for 企业-审批文书-项目分类.
 * 
 * @data 2013-01-29
 * @author 闫伟
 */
@Service
public class QySpwsXmflServiceImp extends BaseBusinessServiceImp implements QySpwsXmflService {
	@Autowired
	private QySpwsXmflDao dao;

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.selectAll(domain, userDomain);
		domain.setDataList(list);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) baseBusinessDomain;
		int count = dao.checkQySpwsflMc(domain, userDomain);
		// 如果count>0，代表已經有重复的，抛异常为140201
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("140201");
			throw se;
		}
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.downloadLIst(domain, userDomain);
		domain.setDataList(list);
	}
}
