package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgMpjDao;
import com.cy.bggl.domain.BgMpjDomain;
import com.cy.bggl.service.BgMpjService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公管理 名片夹
 * 
 * @author 闫伟
 * @date 2013.1.22
 */

@Service
public class BgMpjServiceImp extends BaseBusinessServiceImp implements BgMpjService {
	@Autowired
	private BgMpjDao dao;

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.selectAll(domain, userDomain);
		domain.setDataList(list);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain, userDomain);
		domain.setDataList(dataList);
	}
}
