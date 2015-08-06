package com.cy.bggl.service.imp;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.domain.UserDomain;
import com.cy.bggl.dao.BgZlgxDao;
import com.cy.bggl.domain.BgZlgxDomain;
import com.cy.bggl.service.BgZlgxService;

@Service
/**
 * The SERVICEIMP for 办公-资料共享.
 * 
 * @author HJH
 */
public class BgZlgxServiceImp extends BaseBusinessServiceImp implements BgZlgxService {
	@Autowired
	private BgZlgxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setRqQ(SysDateUtil.getIntervalDate(SysDateUtil.getCurrentDate(), Calendar.MONTH, -1));
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setFjList(dao.queryFj(domain, userDomain));
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		domain.setCjrCzyDjxh(userDomain.getCzyDjxh());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		dao.initDomainMx(domain);
		//置保存标志1为暂存，2为发布
		if("1".equals(domain.getBcztDm()))
			domain.setSaveBz("1");
		else if("2".equals(domain.getBcztDm()))
			domain.setSaveBz("2");
		domain.setFjList(dao.queryFj(domain, userDomain));
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain=(BgZlgxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	public void queryFj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		dao.queryFjByKey(domain, userDomain);
	}

	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMyDeleteMx(baseBusinessDomain, userDomain);
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		dao.deleteFj(domain, userDomain);
	}

	@Override
	protected void doMyQueryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMyQueryMx(baseBusinessDomain, userDomain);
		BgZlgxDomain domain = (BgZlgxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setFjList(dao.queryFj(domain, userDomain));
	}
}
