package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.QyJsDao;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.service.QyJsService;

@Service
/**
 * The SERVICEIMP for 企业角色.
 * 
 * @author ylp
 * @since 2013-1-10 下午13:12:51
 * @version
 */
public class QyJsServiceImp extends BaseBusinessServiceImp implements
		QyJsService {
	@Autowired
	private QyJsDao dao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		int count = dao.checkJsMcRe(domain);
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110502");
			throw se;
		}
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	protected void doMyDeleteBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		int boo = dao.checkJsUsed(domain);
		if (boo > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110501");
			throw se;
		}

	}

	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		dao.startJs(domain);
	}

	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseBusinessDomain;
		dao.stopJs(domain);
	}
}
