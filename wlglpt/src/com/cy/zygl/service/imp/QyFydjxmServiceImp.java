package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyFydjxmDao;
import com.cy.zygl.domain.QyFydjxmDomain;
import com.cy.zygl.service.QyFydjxmService;

@Service
/**
 * The SERVICEIMP for 企业-费用登记项目维护.
 * 
 * @author HJH
 */
public class QyFydjxmServiceImp extends BaseBusinessServiceImp implements QyFydjxmService {
	@Autowired
	private QyFydjxmDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		domain.setSsJgbm(userDomain.getZgsbm());
		if(domain != null && !"".equals(domain.getFydjXmmc())){
			domain.setFydjXmmc(SysEncodeUtil.UTF82ISO(domain.getFydjXmmc()));
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		if(domain != null && !"".equals(domain.getFydjXmmc())){
			domain.setFydjXmmc(SysEncodeUtil.UTF82ISO(domain.getFydjXmmc()));
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain=(QyFydjxmDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
