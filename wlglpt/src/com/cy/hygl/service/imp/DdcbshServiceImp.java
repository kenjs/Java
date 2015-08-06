package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.dao.DdcbshDao;
import com.cy.hygl.domain.DdcbshDomain;
import com.cy.hygl.service.DdcbshService;

@Service
/**
 * The SERVICEIMP for 调度成本审核.
 * 
 * @author HJH
 */
public class DdcbshServiceImp extends BaseBusinessServiceImp implements DdcbshService {
	@Autowired
	private DdcbshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setShbz("N");
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain)baseBusinessDomain;
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain)baseBusinessDomain;
		
		List<DdcbshDomain> dataList = dao.queryList(domain, userDomain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain)baseBusinessDomain;
		List<DdcbshDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		DdcbshDomain domain = (DdcbshDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

}
