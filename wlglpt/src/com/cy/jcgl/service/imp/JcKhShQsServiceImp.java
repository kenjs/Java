package com.cy.jcgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.jcgl.dao.JcKhShQsDao;
import com.cy.jcgl.domain.JcKhShQsDomain;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.service.JcKhShQsService;


@Service
/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-5-4
 * @author LYY
 */
public class JcKhShQsServiceImp extends BaseBusinessServiceImp implements JcKhShQsService {
	@Autowired
	private JcKhShQsDao dao;
	@Autowired
	private WlglptCommonDao commonDao;
	
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcKhShQsDomain domain=(JcKhShQsDomain)baseBusinessDomain;
		
	}
	
	

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcKhShQsDomain domain=(JcKhShQsDomain)baseBusinessDomain;
		//取协议登记系统参数
		String xtcs_2016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		domain.setXydjbz("N");
		if("Y".equals(xtcs_2016)){
			domain.setXydjbz(xtcs_2016);
		}
		List<BaseBusinessDomain> dataList=dao.queryList(domain);
		
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcPcxxglDomain domain = (JcPcxxglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
}
