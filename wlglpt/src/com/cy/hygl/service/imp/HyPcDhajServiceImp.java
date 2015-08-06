package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcDhajDao;
import com.cy.hygl.domain.HyPcDhajDomain;
import com.cy.hygl.service.HyPcDhajService;

@Service
/**
 * The SERVICEIMP for 货运-派车-电话安检.
 * 
 * @author HJH
 */
public class HyPcDhajServiceImp extends BaseBusinessServiceImp implements HyPcDhajService {
	@Autowired
	private HyPcDhajDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain)baseBusinessDomain;
		List<HyPcDhajDomain> ajdhList = dao.queryAjdhList(domain);
		List<HyPcDhajDomain> ajzpList = dao.queryAjzpList(domain);
		dao.initDomainMx(domain);
		domain.setAjdhList(ajdhList);
		domain.setAjzpList(ajzpList);
		domain.setPcJgmc(userDomain.getBmjc());
		domain.setPcrCzyMc(userDomain.getCzyMc());
		domain.setSsJgmc(userDomain.getGsjc());
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain)baseBusinessDomain;
		
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			domain.setFhrMc(SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
		}
		if(StringUtils.isNotBlank(domain.getSjxm4Query())){
			domain.setSjxm4Query(SysEncodeUtil.UTF82ISO(domain.getSjxm4Query()));
		}
		if(StringUtils.isNotBlank(domain.getClhm4Query())){
			domain.setClhm4Query(SysEncodeUtil.UTF82ISO(domain.getClhm4Query()));
		}
		if(StringUtils.isNotBlank(domain.getPcdh4Qyuery())){
			domain.setPcdh4Qyuery(SysEncodeUtil.UTF82ISO(domain.getPcdh4Qyuery()));
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList =dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcDhajDomain domain = (HyPcDhajDomain)baseBusinessDomain;
		domain.setPcrCzyDjxh(userDomain.getCzyDjxh());
		domain.setPcJgbm(userDomain.getBmbm());
		domain.setSsJgbm(userDomain.getGsbm());
		dao.saveDomain(domain, userDomain);
		doMyInitMx(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcDhajDomain domain=(HyPcDhajDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

}
