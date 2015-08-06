package com.cy.jcgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyTydHwmxDao;
import com.cy.hygl.dao.HyTydglDao;
import com.cy.hygl.domain.HyClgzDomain;
import com.cy.hygl.domain.HyTydHwmxDomain;
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.jcgl.dao.JcTydglDao;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcTydglDomain;
import com.cy.jcgl.domain.JcYfZfxxDomain;
import com.cy.jcgl.service.JcTydglService;

@Service
/**
 * The SERVICEIMP for 货运-托运单管理.
 * 
 * @author LYY
 */
public class JcTydglServiceImp extends BaseBusinessServiceImp implements JcTydglService {
	@Autowired
	private JcTydglDao dao;
	@Autowired
	private HyTydglDao tydDao;
	@Autowired
	private HyTydHwmxDao hwmxDao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcTydglDomain domain = (JcTydglDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	
		if(userDomain.getCs_20052().equals("Y")){
			domain.setDjJgbm4Query(userDomain.getBmbm());
		}
		domain.setSsJgbm4Query(userDomain.getGsbm());
		
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcTydglDomain domain = (JcTydglDomain)baseBusinessDomain;
		tydDao.initDomainMx(domain);
		
		List<HyTydHwmxDomain> hwList = hwmxDao.queryHwmxByTydXh(domain.getDdDjxh(), domain.getHwmxDomain().getTempFlag());
		domain.setHwList(hwList);
		
		String xtcs20201 = commonDao.getFunXtXtcs("20201", userDomain.getGsbm(), "2");
		domain.setXtcs20201(xtcs20201);
	}
	
	public void queryJcTydPcxx(JcTydglDomain domain) throws Exception {
		List<JcPcxxglDomain> tydPcxxList = dao.queryJcTydPcxx(domain.getDdDjxh().toString());
		domain.setTydPcxxList(tydPcxxList);
	}
	
	/**
	 * 
	 */
	public void queryJcSjcxClgzxx(JcTydglDomain domain) throws Exception {
		List<HyClgzDomain> clgzList = dao.queryJcSjcxClgzxx(domain.getDdDjxh(),domain.getPcDjxh());
		domain.setClgzList(clgzList);
	}
	
	/**
	 * 检索订单中的物流损失信息
	 */
	public void queryJcWlssxx(JcTydglDomain domain) throws Exception {
		List<HyWlSsDjGlDomain> wlssList = dao.queryJcWlssxx(domain.getDdDjxh());
		if(wlssList != null){
		//	SysEncodeUtil.conISO2GBK(wlssList);
		}
		domain.setWlssList(wlssList);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcTydglDomain domain = (JcTydglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcTydglDomain domain = (JcTydglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	public void refreshHwList(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		
	}

	
	public void queryJcYfZfxx(JcTydglDomain domain) throws Exception {
		List<JcYfZfxxDomain> yfZfxxList = dao.queryJcYfZfxx(domain.getDdDjxh(),domain.getPcDjxh());
		domain.setYfList(yfZfxxList);
		
	}

	/**
	 * 检索物流损失对应的明细
	 */
	public void queryWlssMx(BaseBusinessDomain baseBusinessDomain) throws Exception {
		JcTydglDomain domain = (JcTydglDomain)baseBusinessDomain;
		List<HyWlSsDjGlDomain> wlssMxList = dao.querySsmx(domain.getWlssDjxh());
		domain.setWlssMxList(wlssMxList);
	}
}
