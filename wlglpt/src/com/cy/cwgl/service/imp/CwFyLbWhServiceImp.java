package com.cy.cwgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.dao.CwFyLbWhDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.domain.CwFylbDomain;
import com.cy.cwgl.service.CwFpKpdjService;
import com.cy.cwgl.service.CwFyLbWhService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-开票登记
 * 
 * @author LYY
 */
@Service
public class CwFyLbWhServiceImp extends BaseBusinessServiceImp implements CwFyLbWhService{
	@Autowired
	private CwFyLbWhDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwFylbDomain domain = (CwFylbDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryListById(domain,userDomain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain)baseBusinessDomain;
		
		dao.initDomainMx(domain);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFylbDomain domain=(CwFylbDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

}
