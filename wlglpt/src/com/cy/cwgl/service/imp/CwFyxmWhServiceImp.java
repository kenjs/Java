package com.cy.cwgl.service.imp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwFyxmWhDao;
import com.cy.cwgl.domain.CwFyxmWhDomain;
import com.cy.cwgl.service.CwFyxmWhService;


@Service
/**
 * The SERVICEIMP for 财务-费用项目-维护.
 * 
 * @author HJH
 */
public class CwFyxmWhServiceImp extends BaseBusinessServiceImp implements CwFyxmWhService {
	@Autowired
	private CwFyxmWhDao dao;

	
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}


	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryListById(domain,userDomain);
		domain.setDataList(dataList);
	}

	
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFyxmWhDomain domain = (CwFyxmWhDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFyxmWhDomain domain=(CwFyxmWhDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
