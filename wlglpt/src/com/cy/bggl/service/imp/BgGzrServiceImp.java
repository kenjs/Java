package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgGzrDao;
import com.cy.bggl.domain.BgGzrDomain;
import com.cy.bggl.service.BgGzrService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * THE ACTION FOR 办公管理 工作日
 * 
 * @author 闫伟
 * @date 2013.1.24
 */

@Service
public class BgGzrServiceImp extends BaseBusinessServiceImp implements BgGzrService {
	@Autowired
	private BgGzrDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) baseBusinessDomain;
		System.out.println("---");
		domain.setNowDate(SysDateUtil.getCurrentDate());
	}
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.queryList(domain);
		domain.setDataList(list);
	}

	public void updateGzrByJgbm(BaseBusinessDomain baseBusinessDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) baseBusinessDomain;
		dao.updateGzrByJgbm(domain);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list=dao.downloadList(domain);
		domain.setDataList(list);
	}

}
