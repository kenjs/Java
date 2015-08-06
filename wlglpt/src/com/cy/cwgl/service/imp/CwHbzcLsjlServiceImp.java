package com.cy.cwgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwHbzcLsjlDao;
import com.cy.cwgl.domain.CwHbzcLsjlDomain;
import com.cy.cwgl.service.CwHbzcLsjlService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-�����ʲ���ˮ��¼
 * 
 * @author HCM
 */
@Service
public class CwHbzcLsjlServiceImp extends BaseBusinessServiceImp implements CwHbzcLsjlService{
	@Autowired
	private CwHbzcLsjlDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain)baseBusinessDomain;
		// �ڴ���ӳ�ʼ����Ӧ����
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
}
