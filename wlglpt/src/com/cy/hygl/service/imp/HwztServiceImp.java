package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HwztDao;
import com.cy.hygl.domain.HwztDomain;
import com.cy.hygl.service.HwztService;
import com.cy.common.domain.UserDomain;

@Service
/**
 * The SERVICEIMP for 货物自提.
 * 
 * @author LYY
 */
public class HwztServiceImp extends BaseBusinessServiceImp implements HwztService {
	@Autowired
	private HwztDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HwztDomain domain = (HwztDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setThbz("2");
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HwztDomain domain = (HwztDomain)baseBusinessDomain;
		
		List<HwztDomain> dataList = dao.queryList(domain, userDomain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HwztDomain domain = (HwztDomain)baseBusinessDomain;
		List<HwztDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HwztDomain domain = (HwztDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	public void register(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HwztDomain domain = (HwztDomain)baseBusinessDomain;
		dao.register(domain, userDomain);
	}
	
	public void delete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HwztDomain domain = (HwztDomain)baseBusinessDomain;
		dao.delete(domain, userDomain);
	}

}
