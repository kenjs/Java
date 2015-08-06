package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.dao.CwFyBxSqDao;
import com.cy.cwgl.dao.CwFyBxSqShDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.domain.CwFyBxSqMxDomain;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.cwgl.domain.CwFybxsqShDomain;
import com.cy.cwgl.service.CwFpKpdjService;
import com.cy.cwgl.service.CwFyBxSqService;
import com.cy.cwgl.service.CwFyBxSqShService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The SERVICE for 财务-开票登记
 * 
 * @author LYY
 */
@Service
public class CwFyBxSqShServiceImp extends BaseBusinessServiceImp implements CwFyBxSqShService{
	@Autowired
	private CwFyBxSqShDao dao;
	
	@Autowired
	private WlglptCommonDao commonDao;
	
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwFybxsqShDomain domain = (CwFybxsqShDomain)baseBusinessDomain;
		domain.setShbz("N");
		domain.setRqq(SysDateUtil.getFirstDayMonth());
		domain.setRqz(SysDateUtil.getCurrentDate());
	}

	
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqShDomain domain = (CwFybxsqShDomain)baseBusinessDomain;
		List<CwFybxsqShDomain> dataList = dao.queryAllList(domain,userDomain);
		domain.setDataList(dataList);
	}

	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain=(CwFybxsqDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
