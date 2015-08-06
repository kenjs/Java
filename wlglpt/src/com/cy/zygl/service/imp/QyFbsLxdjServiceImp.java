package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyFbsLxdjDao;
import com.cy.zygl.domain.QyFbsLxdjDomain;
import com.cy.zygl.service.QyFbsLxdjService;

@Service
/**
 * The SERVICEIMP for 企业-分包商-路线登记.
 * 
 * @author HJH
 */
public class QyFbsLxdjServiceImp extends BaseBusinessServiceImp implements QyFbsLxdjService {
	@Autowired
	private QyFbsLxdjDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain)baseBusinessDomain;
		//转换路线名称 为拼音名称
		String lxmc = SysEncodeUtil.UTF82GBK(domain.getLxmc());
		domain.setPyqc(CnToSpellUtil.getFullSpell(lxmc));
		domain.setPyjc(CnToSpellUtil.getFirstSpell(lxmc));
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain=(QyFbsLxdjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain=(QyFbsLxdjDomain)baseBusinessDomain;
		int lxMcCount = dao.checkLxmc(domain);
		if(lxMcCount > 0 ){
			userDomain.setIsLoginSuccess(false);
			ServiceException se = new ServiceException();
			se.setErrorCode("120701");
			throw se;
		}
	}
}
