package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.dao.DdcbshDao;
import com.cy.hygl.dao.HyWlSsDjShDao;
import com.cy.hygl.domain.DdcbshDomain;
import com.cy.hygl.domain.HyWlSsDjShDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.DdcbshService;
import com.cy.hygl.service.HyWlSsDjService;
import com.cy.hygl.service.HyWlSsDjShService;

@Service
/**
 * The SERVICEIMP for 调度成本审核.
 * 
 * @author HJH
 */
public class HyWlSsDjShServiceImp extends BaseBusinessServiceImp implements HyWlSsDjShService {
	@Autowired
	private HyWlSsDjShDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyWlSsDjShDomain domain = (HyWlSsDjShDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setShbz("N");
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyWlSsDjShDomain domain = (HyWlSsDjShDomain)baseBusinessDomain;
		List<BaseBusinessDomain> list=dao.queryList(domain,userDomain);
		domain.setDataList(list);
		
	}

	

	


	

}
