package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.zygl.dao.QyKhZhdzDao;
import com.cy.zygl.domain.QyKhZhdzDomain;
import com.cy.zygl.service.QyKhZhdzService;

@Service
/**
 * The SERVICEIMP for 企业-客户-装货地址.
 * 
 * @author ylp
 * @since 2013-1-16 上午8:31:00
 * @version
 */
public class QyKhZhdzServiceImp extends BaseBusinessServiceImp implements
		QyKhZhdzService {
	@Autowired
	private QyKhZhdzDao dao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseBusinessDomain;
		int count = dao.checkXxdzRe(domain);
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("120401");
			throw se;
		}
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

}
