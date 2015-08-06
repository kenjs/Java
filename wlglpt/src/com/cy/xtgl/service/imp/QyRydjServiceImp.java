package com.cy.xtgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.WlglptCommonService;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.QyRydjDao;
import com.cy.xtgl.domain.QyRydjDomain;
import com.cy.xtgl.service.QyRydjService;

@Service
/**
 * The SERVICEIMP for �û�ά��. *
 * 
 * @author yu huan
 * @Date 2013-1-9
 */
public class QyRydjServiceImp extends BaseBusinessServiceImp implements QyRydjService {
	@Autowired
	private QyRydjDao dao;

	@Autowired
	private WlglptCommonService service;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
		// �����û�����ȡ���û�������˾
		if(StringUtils.isNotBlank(domain.getCzyDjxh())){
			domain.setGsbm(service.getSjJgbmByJgbm(domain.getSsJgbm()));
		}
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		String jgbm = domain.getJbdm();
		String jbdm = service.getQyJbdmByJgbm(jgbm);
		domain.setJbdm(jbdm);
		domain.setQyZcxh(userDomain.getQyZcxh());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		String jgbm = domain.getSsJgbm();
		String jbdm = service.getQyJbdmByJgbm(jgbm);
		domain.setSsJgbm(jbdm);
		domain.setQyZcxh(userDomain.getQyZcxh());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	// ��Աά���е�״̬���ò���
	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		dao.startUse(domain);
	}

	// ��Աά���е�״̬ͣ�ò���
	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		dao.stopUse(domain);
	}
	
	// ��Աά�����˺����ƽ����ظ���֤
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseBusinessDomain;
		domain.setQyZcxh(userDomain.getQyZcxh());
		int count = dao.checkQyzzYhwhMc(domain);
		// ���count>0�����ظ������쳣Ϊ110701
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110701");
			throw se;
		}
	}
}
