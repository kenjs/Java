package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwZzjxbDao;
import com.cy.cwgl.domain.CwZzjxbDomain;
import com.cy.cwgl.service.CwZzjxbService;

@Service
/**
 * The SERVICEIMP for 财务-周转金下拨.
 * 
 * @author HJH
 */
public class CwZzjxbServiceImp extends BaseBusinessServiceImp implements CwZzjxbService {
	@Autowired
	private CwZzjxbDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setRq(SysDateUtil.getSqlDate().toString());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain)baseBusinessDomain;
		if(StringUtils.isNotBlank(domain.getZjdbDjxh())){
			dao.initDomainMx(domain);
		}else{
			domain.setRq(SysDateUtil.getSqlDate().toString());
			dao.getCwZzjxbxxWhenAdd(domain);
			
			domain.setRq(SysDateUtil.getSqlDate().toString());
			domain.setXbDwDjxh(userDomain.getGsbm());
			domain.setXbDwMc(userDomain.getGsjc());
		}
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain = (CwZzjxbDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		
		dao.callPCwglZzjxbHxcl(domain.getZjdbDjxh(), userDomain.getGsbm(), userDomain.getCzyDjxh());
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZzjxbDomain domain=(CwZzjxbDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		
		dao.callPCwglZzjxbHxcl(domain.getZjdbDjxh(), userDomain.getGsbm(), userDomain.getCzyDjxh());
	}
}
