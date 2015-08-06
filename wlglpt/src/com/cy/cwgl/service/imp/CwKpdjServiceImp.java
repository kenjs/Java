package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.dao.CwKpdjDao;
import com.cy.cwgl.domain.CwKpdjDomain;
import com.cy.cwgl.service.CwKpdjService;

@Service
/**
 * The SERVICEIMP for 财务-开票登记.
 * 
 * @author HJH
 */
public class CwKpdjServiceImp extends BaseBusinessServiceImp implements CwKpdjService {
	@Autowired
	private CwKpdjDao dao;
	@Autowired
	private CwFpKpdjDao fpDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		if(StringUtils.isNotBlank(domain.getFpdm())){
			domain.setFpdm("%" + domain.getFpdm() + "%");
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		
		fpDao.callPHyglCwKpdjZfHxcl(domain.getKpDjxh(),domain.getKpsqDjxh());
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain=(CwKpdjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	protected void doMyDeleteAfter(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		CwKpdjDomain domain=(CwKpdjDomain)baseBusinessDomain;
		dao.cwKpdjZfhxcl(domain.getKpDjxh());
	}
}
