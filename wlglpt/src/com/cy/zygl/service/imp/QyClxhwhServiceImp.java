package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyClxhwhDao;
import com.cy.zygl.domain.QyClxhwhDomain;
import com.cy.zygl.service.QyClxhwhService;

@Service
/**
 * The SERVICEIMP for ��ҵ-�����ͺ�ά��.
 * 
 * @author HJH
 */
public class QyClxhwhServiceImp extends BaseBusinessServiceImp implements QyClxhwhService {
	@Autowired
	private QyClxhwhDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain)baseBusinessDomain;
		// �ڴ����ӳ�ʼ����Ӧ����
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain=(QyClxhwhDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	public void queryClxhByKey(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		this.doMyInitMx(baseBusinessDomain, user);
	}
}