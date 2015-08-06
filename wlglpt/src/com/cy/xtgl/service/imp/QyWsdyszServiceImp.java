package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.xtgl.dao.QyWsdyszDao;
import com.cy.xtgl.domain.QyWsdyszDomain;
import com.cy.xtgl.service.QyWsdyszService;

@Service
/**
 * The SERVICEIMP for 企业-文书打印设置.
 * 
 * @author HJH
 */
public class QyWsdyszServiceImp extends BaseBusinessServiceImp implements QyWsdyszService {
	@Autowired
	private QyWsdyszDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getGsbm());
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain=(QyWsdyszDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	public void selectBj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain=(QyWsdyszDomain)baseBusinessDomain;
		
		domain.setSsJgbm(userDomain.getGsbm());
		
		QyWsdyszDomain dom = (QyWsdyszDomain)dao.getDomainByWsdm(domain);
		
		if (dom != null) {
			domain.setWhXh(dom.getWhXh());
			domain.setWsDm(dom.getWsDm());
			domain.setLeftMargin(dom.getLeftMargin());
			domain.setTopMargin(dom.getTopMargin());
		}
		
	}
}
