package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyPcDhajDao;
import com.cy.hygl.dao.HyZpajDao;
import com.cy.hygl.domain.HyPcDhajDomain;
import com.cy.hygl.domain.HyZpajDomain;
import com.cy.hygl.service.HyZpajService;

@Service
/**
 * The SERVICEIMP for 货运-货运照片安检 time 2013-3-5
 * 
 * @author yw
 */
public class HyZpajServiceImp extends BaseBusinessServiceImp implements HyZpajService {
	@Autowired
	private HyZpajDao dao;

	@Autowired
	private HyPcDhajDao dhDao;

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZpajDomain domain = (HyZpajDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZpajDomain domain = (HyZpajDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		HyPcDhajDomain dhDomain = new HyPcDhajDomain();
		dhDomain.setPcDjxh(domain.getPcDjxh());
		List<HyPcDhajDomain> ajdhList = dhDao.queryAjdhList(dhDomain);
		List<HyPcDhajDomain> ajzpList = dhDao.queryAjzpList(dhDomain);
		dao.initDomainMx(domain);
		domain.setTage("1");
		domain.setAjdhList(ajdhList);
		domain.setAjzpList(ajzpList);
	}

	
	
	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZpajDomain domain = (HyZpajDomain) baseBusinessDomain;
		HyPcDhajDomain dhDomain = new HyPcDhajDomain();
		dhDomain.setPcDjxh(domain.getPcDjxh());
		List<HyPcDhajDomain> ajdhList = dhDao.queryAjdhList(dhDomain);
		List<HyPcDhajDomain> ajzpList = dhDao.queryAjzpList(dhDomain);
		dao.initDomainMx(domain);
		domain.setAjdhList(ajdhList);
		domain.setAjzpList(ajzpList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZpajDomain domain = (HyZpajDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	

	

}
