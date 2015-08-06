package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.bggl.dao.BgGzlxDao;
import com.cy.bggl.domain.BgGzlxDomain;
import com.cy.bggl.service.BgGzlxService;

@Service
/**
 * The SERVICEIMP for 办公-工作联系.
 * 
 * @author HJH
 */
public class BgGzlxServiceImp extends BaseBusinessServiceImp implements BgGzlxService {
	@Autowired
	private BgGzlxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
	public void doMyDownloadForSjx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		List<BaseBusinessDomain> dataList = dao.downloadListForSjx(domain);
		domain.setDataList(dataList);
	}
	
	public void doMyDownloadForCgx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		List<BaseBusinessDomain> dataList = dao.downloadListForCgx(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		dao.initDomainMx(domain);
		domain.setSaveBz(domain.getBcbzDm());
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain=(BgGzlxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	public void queryFjDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		dao.getFjDomain(domain);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteFjDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		dao.deleteFjDomain(domain);
		
	}

	public void queryForCgxDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		List<BaseBusinessDomain> dataList = dao.queryListForCgx(domain);
		domain.setDataList(dataList);	
		
	}

	public void queryForSjxDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		List<BaseBusinessDomain> dataList = dao.queryListForSjx(domain);
		domain.setDataList(dataList);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateJsrDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain)baseBusinessDomain;
		domain.setCzyDjxh(userDomain.czyDjxh);
		dao.updateJsrDomain(domain);
	}
}
