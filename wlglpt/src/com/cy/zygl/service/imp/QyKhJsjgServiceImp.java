package com.cy.zygl.service.imp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.dao.QyKhJsjgDao;
import com.cy.zygl.domain.QyKhJsjgDomain;
import com.cy.zygl.service.QyKhJsjgService;
import com.cy.common.domain.UserDomain;


@Service
/**
 * The SERVICEIMP for 企业-客户-结算价格.
 * 
 * @author HJH
 */
public class QyKhJsjgServiceImp extends BaseBusinessServiceImp implements QyKhJsjgService {
	@Autowired
	private QyKhJsjgDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain=(QyKhJsjgDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	
	public void saveCheck(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyKhJsjgDomain domain=(QyKhJsjgDomain)baseBusinessDomain;
		int count=dao.saveCheck(domain);
		if(count>0){
			domain.setTager("1");
		}
		else {
			domain.setTager("2");
		}
		
	}
}
