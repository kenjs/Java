package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.dao.QyFbsJsjgDao;
import com.cy.zygl.domain.QyFbsJsjgDomain;
import com.cy.zygl.service.QyFbsJsjgService;

@Service
/**
 * The SERVICEIMP for 企业-分包商-结算价格.
 * 
 * @author HJH
 */
public class QyFbsJsjgServiceImp extends BaseBusinessServiceImp implements QyFbsJsjgService {
	@Autowired
	private QyFbsJsjgDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsJsjgDomain domain=(QyFbsJsjgDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	public void checkSave(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyFbsJsjgDomain domain=(QyFbsJsjgDomain)baseBusinessDomain;
		domain.setCount(dao.queryQyFbsJsjgCount(domain)+"");
	}
}
