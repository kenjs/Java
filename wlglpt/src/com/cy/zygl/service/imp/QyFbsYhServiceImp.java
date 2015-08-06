package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.zygl.dao.QyFbsYhDao;
import com.cy.zygl.domain.QyFbsYhDomain;
import com.cy.zygl.service.QyFbsYhService;

@Service
/**
 * The SERVICEIMP for 分包商用户管理.
 * 
 * @author HJH
 */
public class QyFbsYhServiceImp extends BaseBusinessServiceImp implements QyFbsYhService {
	@Autowired
	private QyFbsYhDao dao;

	@SuppressWarnings({ "unused"})
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain=(QyFbsYhDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseBusinessDomain;
		int count = dao.checkYhzh(domain, userDomain);
		if (count>0) {
			userDomain.setIsLoginSuccess(false);
			ServiceException se = new ServiceException();
			se.setErrorCode("120901");
			throw se;
		}
	}
	
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain=(QyFbsYhDomain)baseBusinessDomain;
		dao.saveEnable(domain);
	}
	
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain=(QyFbsYhDomain)baseBusinessDomain;
		dao.saveDisable(domain);
	}
}
