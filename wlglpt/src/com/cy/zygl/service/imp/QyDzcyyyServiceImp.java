package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyDzcyyyDao;
import com.cy.zygl.domain.QyDzcyyyDomain;
import com.cy.zygl.service.QyDzcyyyService;

@Service
/**
 * The SERVICEIMP for 企业-对账差异原因维护
 * 
 * @author HJH
 */
public class QyDzcyyyServiceImp extends BaseBusinessServiceImp implements QyDzcyyyService {
	@Autowired
	private QyDzcyyyDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		domain.setSsJgbm(userDomain.getZgsbm());
		if(domain != null && !"".equals(domain.getDzcyyy())){
			domain.setDzcyyy(SysEncodeUtil.UTF82ISO(domain.getDzcyyy()));
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		if(domain != null && !"".equals(domain.getDzcyyy())){
			domain.setDzcyyy(SysEncodeUtil.UTF82ISO(domain.getDzcyyy()));
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain = (QyDzcyyyDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyDzcyyyDomain domain=(QyDzcyyyDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
