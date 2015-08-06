package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.dao.QySwdnDao;
import com.cy.xtgl.domain.QySwdnDshDomain;
import com.cy.xtgl.service.QySwdnService;
/**
 * The SERVICEIMP for 上网电脑管理.
 * 
 * @author HaoY
 */
@Service
public class QySwdnServiceImp extends BaseBusinessServiceImp implements QySwdnService {
	@Autowired
	private QySwdnDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		dao.stopUse(domain);
	}

	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		dao.startUse(domain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySwdnDshDomain domain = (QySwdnDshDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

}
