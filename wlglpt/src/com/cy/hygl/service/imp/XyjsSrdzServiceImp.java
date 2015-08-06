package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.dao.XyjsSrdzDao;
import com.cy.hygl.domain.XyjsSrdzDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;
import com.cy.hygl.service.XyjsSrdzService;

@Service
/**
 * The SERVICEIMP for 下游结算-收入对帐.
 * 
 * @author HJH
 */
public class XyjsSrdzServiceImp extends BaseBusinessServiceImp implements XyjsSrdzService {
	@Autowired
	private XyjsSrdzDao dao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		if (StringUtils.isBlank(domain.getQdDjxh())) {
			domain.setPcrqQ(SysDateUtil.parse(SysDateUtil.getFirstDayMonth()));
			domain.setPcrqZ(SysDateUtil.getSqlDate());
			domain.setXdrqQ(SysDateUtil.parse(SysDateUtil.getFirstDayMonth()));
			domain.setXdrqZ(SysDateUtil.getSqlDate());
		}else {
			
		}
	}
	
	public void queryDzqdList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		List<XyjsSrdzQdDomain> dzqdList = dao.queryDzqdList(domain, userDomain);
		domain.setDzqdList(dzqdList);
	}
	
	public void savePldz(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		dao.savePldz(domain, userDomain);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		
		if (StringUtils.isBlank(domain.getDzDjxh())) {
			XyjsSrdzDomain dom = dao.initDzxxByJsxx(domain.getYwDjxh(), domain.getYwMxXh());
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}else {
			dao.initDomainMx(domain);
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain = (XyjsSrdzDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzDomain domain=(XyjsSrdzDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
