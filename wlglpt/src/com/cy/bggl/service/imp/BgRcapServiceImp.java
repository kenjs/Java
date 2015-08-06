package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgRcapDao;
import com.cy.bggl.domain.BgRcapDomain;
import com.cy.bggl.service.BgRcapService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;

@Service
/**
 * The SERVICEIMP for 办公-日程安排.
 * 
 * @author HJH
 */
public class BgRcapServiceImp extends BaseBusinessServiceImp implements BgRcapService {
	@Autowired
	private BgRcapDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain)baseBusinessDomain;
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain=(BgRcapDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	public void queryForDate(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain=(BgRcapDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		dao.queryForDate(domain);
	}
}
