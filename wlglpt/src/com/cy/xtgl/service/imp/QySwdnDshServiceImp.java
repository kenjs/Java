package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.xtgl.dao.QySwdnDshDao;
import com.cy.xtgl.domain.QySwdnDshDomain;
import com.cy.xtgl.service.QySwdnDshService;

@Service
/**
 * The SERVICEIMP for 企业-上网电脑-待审核.
 * 
 * @author HaoY
 */
public class QySwdnDshServiceImp extends BaseBusinessServiceImp implements QySwdnDshService {
	@Autowired
	private QySwdnDshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain=(QySwdnDshDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseBusinessDomain;
		dao.dispass(domain);
	}

	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain) baseBusinessDomain;
		dao.pass(domain, userDomain);
	}

}
