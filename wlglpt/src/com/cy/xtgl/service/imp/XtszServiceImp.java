package com.cy.xtgl.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.XtszDao;
import com.cy.xtgl.domain.XtszDomain;
import com.cy.xtgl.service.XtDemoService;

@Service
public class XtszServiceImp extends BaseBusinessServiceImp implements	XtDemoService {

	@Autowired
	private XtszDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XtszDomain demoDomain = (XtszDomain) baseBusinessDomain;
		dao.initDomainMx(demoDomain, userDomain);
		List<XtszDomain> xzxmList = dao.queryXzxmList();
		Map<String, List<XtszDomain>> xzxmMap = new HashMap<String, List<XtszDomain>>();
		for (Iterator iter = xzxmList.iterator(); iter.hasNext();) {
			XtszDomain element = (XtszDomain) iter.next();
			
			
			if(!xzxmMap.containsKey(element.getXzxmDm())){
				List<XtszDomain> xzxm = new ArrayList<XtszDomain>();
				xzxm.add(element);
				xzxmMap.put(element.getXzxmDm(), xzxm);
			}else{
				List<XtszDomain> xzxm = xzxmMap.get(element.getXzxmDm());
				xzxm.add(element);
				xzxmMap.put(element.getXzxmDm(), xzxm);
			}
		}
		for (Iterator iter = demoDomain.getDataList().iterator(); iter.hasNext();) {
			XtszDomain element = (XtszDomain) iter.next();
			if("23".equals(element.getSjxlbDm())){
				List<XtszDomain> xzxm = xzxmMap.get(element.getXzxmDm());
				element.setXzxmList(xzxm);
			}
			if("21".equals(element.getSjxlbDm())){
				List<XtszDomain> xzxm = xzxmMap.get(element.getXzxmDm());
				element.setXzxmList(xzxm);
			}
		}
		
	}
	
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XtszDomain domain = (XtszDomain) baseBusinessDomain;
		if(!"1".equals(domain.getFlag())){
			return;
		}
		boolean flag = dao.checkCzypwd(domain,userDomain);
		if (!flag) {
			userDomain.setIsLoginSuccess(false);
			ServiceException se = new ServiceException();
			se.setErrorCode("112001 ");
			throw se;
		}
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XtszDomain domain = (XtszDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}
	
	@Override
	public void initMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		this.doMyInit(baseBusinessDomain, userDomain);
	}
	
}
