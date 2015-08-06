package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.dao.XyjsZbYjdzDao;
import com.cy.hygl.dao.XyjsZbYjdzShDao;
import com.cy.hygl.domain.XyjsZbYjdzShDomain;
import com.cy.hygl.service.XyjsZbYjdzShService;

/**
 * The SERVICE for 财务-开票登记
 * 
 * @author LYY
 */
@Service
public class XyjsZbYjdzShServiceImp extends BaseBusinessServiceImp implements XyjsZbYjdzShService{
	@Autowired
	private XyjsZbYjdzShDao dao;
	
	
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzShDomain domain = (XyjsZbYjdzShDomain)baseBusinessDomain;
		domain.setShbz("N");
		domain.setRqq(SysDateUtil.getFirstDayMonth());
		domain.setRqz(SysDateUtil.getCurrentDate());
	}

	
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzShDomain domain = (XyjsZbYjdzShDomain)baseBusinessDomain;
		List<XyjsZbYjdzShDomain> dataList = dao.queryAllList(domain,userDomain);
		domain.setShList(dataList);
	}
	

}
