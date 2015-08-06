package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.dao.HyJsglSrdzcyshDao;
import com.cy.hygl.domain.HyJsglSrdzcyshDomain;
import com.cy.hygl.service.HyJsglSrdzcyshService;

@Service
/**
 * The SERVICEIMP for收入对账差异审核
 * 
 * @author HJH
 */
public class HyJsglSrdzcyshServiceImp extends BaseBusinessServiceImp implements HyJsglSrdzcyshService {
	@Autowired
	private HyJsglSrdzcyshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		// 在此添加初始化相应代码
		HyJsglSrdzcyshDomain domain = (HyJsglSrdzcyshDomain)baseBusinessDomain;
		domain.setShbz("N");
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}
	
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyJsglSrdzcyshDomain domain = (HyJsglSrdzcyshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain,userDomain);
		domain.setDataList(dataList);
	}

}
