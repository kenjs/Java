package com.cy.xtgl.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.BmwhDao;
import com.cy.xtgl.domain.QyZzjgDomain;
import com.cy.xtgl.service.BmwhService;
/**
 * The ServiceIMP for ��ҵ��֯��������ά��
 * 
 * @author Yu huan
 * @data 2013-01-8
 */
@Service
public class BmwhServiceImp extends BaseBusinessServiceImp implements
		BmwhService {
	@Autowired
	private BmwhDao dao;

	// ��������
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		List<BaseBusinessDomain> bmwhList = dao.queryList(domain);
		domain.setDataList(bmwhList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	// ��Ӳ���ά��
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	// ����ά����ɾ������
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	// ���ز���
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	// ����ά���в������ƽ����ظ���֤
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		int count = dao.checkQyzzBmwhMc(domain);
		// ���count>0�����ظ������쳣Ϊ110301
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110301");
			throw se;
		}
	}

	//	 ����ά���е�״̬���ò���
	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.startUse(domain);
	}

	// ����ά���е�״̬ͣ�ò���
	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.stopUse(domain);
	}

}
