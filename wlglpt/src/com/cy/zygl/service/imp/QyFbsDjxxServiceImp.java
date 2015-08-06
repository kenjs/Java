package com.cy.zygl.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyFbsDjxxDao;
import com.cy.zygl.domain.QyFbsDjxxDomain;
import com.cy.zygl.service.QyFbsDjxxService;

@Service
/**
 * The SERVICEIMP for ��ҵ-�ְ���-�Ǽ���Ϣ.
 * 
 * @author HJH
 */
public class QyFbsDjxxServiceImp extends BaseBusinessServiceImp implements QyFbsDjxxService {
	@Autowired
	private QyFbsDjxxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		//QyFbsDjxxDomain domain = (QyFbsDjxxDomain)baseBusinessDomain;
		// �ڴ���ӳ�ʼ����Ӧ����
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyFbsDjxxDomain domain=(QyFbsDjxxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	/**
	 * У��ְ��������Ƿ��ظ�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void check(QyFbsDjxxDomain domain, UserDomain userDomain) throws Exception{
		int num=dao.queryFbsmcCount(domain);
		if(num>0){
			ServiceException ex=new ServiceException();
			ex.setErrorCode("120601");
			throw ex;
		}
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMySaveBefore(baseBusinessDomain, userDomain);
		QyFbsDjxxDomain domain=(QyFbsDjxxDomain)baseBusinessDomain;
		this.check(domain, userDomain);
	}
}
