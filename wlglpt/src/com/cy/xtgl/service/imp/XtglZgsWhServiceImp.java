package com.cy.xtgl.service.imp;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.XtglZgsWhDao;
import com.cy.xtgl.domain.QyZzjgDomain;
import com.cy.xtgl.service.XtglZgsWhService;

@Service
/**
 * The SERVICEIMP for 企业-组织机构.
 * 
 * @Descriptoin 登总公司业务处理
* @Note
* @author ylp
* @since 2013-1-9 下午05:12:32 
* @version
 */
public class XtglZgsWhServiceImp extends BaseBusinessServiceImp implements XtglZgsWhService {

	@Autowired
	private XtglZgsWhDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) 
	         throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		if (userDomain.getQyZcxh() != null) {
			QyZzjgDomain dom = dao.getDomainByQyzcxh(userDomain);
			if (dom == null) {				
				ServiceException se = new ServiceException();
				se.setErrorCode("110102");
				throw se;			
			} else {			
				BeanUtils.copyProperties(domain, dom);	//可以传数据		
			}
		} else {
			ServiceException se = new ServiceException();
			se.setErrorCode("110101");
			throw se;
		}
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
			dao.saveDomain(domain, userDomain);
		
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		boolean boo = dao.checkMcre(domain);
		if (boo) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110103");
			throw se;
		} else {
			doMySave(domain, userDomain);
		}
	}
	
	

}
