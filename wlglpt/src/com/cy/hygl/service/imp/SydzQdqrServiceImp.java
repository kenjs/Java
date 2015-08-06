package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.XyjsSrdzQdDao;
import com.cy.hygl.domain.XyjsSrdzQdDomain;
import com.cy.hygl.service.SydzQdqrService;

@Service
/**
 * The SERVICEIMP for 下游结算-收入对帐-清单.
 * 
 * @author HJH
 */
public class SydzQdqrServiceImp extends BaseBusinessServiceImp implements SydzQdqrService {
	@Autowired
	private XyjsSrdzQdDao dao;

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadQdQr(domain,userDomain);
		domain.setDataList(dataList);
	}
}
