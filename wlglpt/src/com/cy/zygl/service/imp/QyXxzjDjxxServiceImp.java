package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.zygl.dao.QyXxzjDjxxDao;
import com.cy.zygl.domain.QyXxzjDjxxDomain;
import com.cy.zygl.service.QyXxzjDjxxService;

/**
 * The SERVICE for 企业-信息中介-登记信息
 * 
 * @author yw
 * @since 2013-2-20 上午8:31:00
 * @version
 */
@Service
public class QyXxzjDjxxServiceImp extends BaseBusinessServiceImp implements QyXxzjDjxxService {
	@Autowired
	private QyXxzjDjxxDao dao;

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.queryList(domain);
		domain.setDataList(list);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		int count = dao.checkQyXxZjMc(domain);
		// 如果count>0，代表已经有重复的
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("121101");
			throw se;
		}
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	public void download(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.downloadList(domain);
		domain.setDataList(list);
	}
}
