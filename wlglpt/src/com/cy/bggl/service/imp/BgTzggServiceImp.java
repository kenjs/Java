package com.cy.bggl.service.imp;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.domain.UserDomain;
import com.cy.bggl.dao.BgTzggDao;
import com.cy.bggl.domain.BgTzggDomain;
import com.cy.bggl.service.BgTzggService;

@Service
/**
 * The SERVICEIMP for 办公-通知公告.
 * 
 * @author HJH
 */
public class BgTzggServiceImp extends BaseBusinessServiceImp implements BgTzggService {
	@Autowired
	private BgTzggDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setRqQ(SysDateUtil.getIntervalDate(SysDateUtil.getCurrentDate(), Calendar.MONTH, -1));
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setFjList(dao.queryFj(domain, userDomain));
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		domain.setCjrCzyDjxh(userDomain.getCzyDjxh());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
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
		BgTzggDomain domain=(BgTzggDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	public void queryFj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		dao.queryFjByKey(domain, userDomain);
	}

	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMyDeleteMx(baseBusinessDomain, userDomain);
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		dao.deleteFj(domain, userDomain);
	}

	@Override
	protected void doMyQueryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMyQueryMx(baseBusinessDomain, userDomain);
		BgTzggDomain domain = (BgTzggDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setFjList(dao.queryFj(domain, userDomain));
	}

	@Override
	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMySaveMx(baseBusinessDomain, userDomain);
		dao.saveMxDomain(baseBusinessDomain, userDomain);
	}
}
