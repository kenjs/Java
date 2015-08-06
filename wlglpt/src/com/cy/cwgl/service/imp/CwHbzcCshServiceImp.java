package com.cy.cwgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwHbzcCshDao;
import com.cy.cwgl.domain.CwHbzcCshDomain;
import com.cy.cwgl.service.CwHbzcCshService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyZzjgDomain;

@Service
/**
 * The SERVICEIMP for 财务-货币资产初始化.
 * 
 * @author HJH
 */
public class CwHbzcCshServiceImp extends BaseBusinessServiceImp implements CwHbzcCshService {
	@Autowired
	private CwHbzcCshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		System.out.println(dataList.size());
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain=(CwHbzcCshDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		dao.deleteHxcl(domain);
	}
	
	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseBusinessDomain;
		dao.startUser(domain);
	}

	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseBusinessDomain;
		dao.stopUser(domain);
	}
	


	public void checkYhMc(BaseBusinessDomain baseBusinessDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseBusinessDomain;
		dao.checkYhMc(domain);
		
	}
}
