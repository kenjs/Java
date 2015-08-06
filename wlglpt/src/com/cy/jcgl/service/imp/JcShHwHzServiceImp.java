package com.cy.jcgl.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.jcgl.dao.JcKhShMxDao;
import com.cy.jcgl.dao.JcPcxxglDao;
import com.cy.jcgl.dao.JcShHwHzDao;
import com.cy.jcgl.dao.JcShQkTjDao;
import com.cy.jcgl.domain.JcKhShMxDomain;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcShHwHzDomain;
import com.cy.jcgl.domain.JcShQkTjDomain;
import com.cy.jcgl.service.JcKhShMxService;
import com.cy.jcgl.service.JcPcxxglService;
import com.cy.jcgl.service.JcShHwHzService;
import com.cy.jcgl.service.JcShQkTjService;


@Service
/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-5-4
 * @author LYY
 */
public class JcShHwHzServiceImp extends BaseBusinessServiceImp implements JcShHwHzService {
	@Autowired
	private JcShHwHzDao dao;
	
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcShHwHzDomain domain=(JcShHwHzDomain)baseBusinessDomain;
		domain.setSjJgbm(userDomain.getGsbm());
		domain.setTjRq(SysDateUtil.getCurrentDate());
		
	}
	
	

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcShHwHzDomain domain=(JcShHwHzDomain)baseBusinessDomain;
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
