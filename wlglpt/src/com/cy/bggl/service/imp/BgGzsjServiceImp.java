package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgGzsjDao;
import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.service.BgGzsjService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

@Service
/**
 * The SERVICEIMP for 办公-工作时间.
 * 
 * @author HJH
 */
public class BgGzsjServiceImp extends BaseBusinessServiceImp implements BgGzsjService {
	@Autowired
	private BgGzsjDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain)baseBusinessDomain;
		domain.setYxqQ(SysDateUtil.getSqlDate().toString());
		domain.setYxqZ(SysDateUtil.getSqlDate().toString());
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setYxqQ(SysDateUtil.getSqlDate().toString());
		domain.setYxqZ(SysDateUtil.getSqlDate().toString());
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain=(BgGzsjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	public void checkSave(BaseBusinessDomain baseBusinessDomain) throws Exception {
		BgGzsjDomain domain=(BgGzsjDomain)baseBusinessDomain;
		domain.setCount(dao.queryBgGzsjCount(domain)+"");
	}
}
