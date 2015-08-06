package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwHbzcZhjlDao;
import com.cy.cwgl.domain.CwHbzcZhjlDomain;
import com.cy.cwgl.service.CwHbzcZhjlService;

@Service
/**
 * The SERVICEIMP for 财务-货币资产-转换记录.
 * 
 * @author HJH
 */
public class CwHbzcZhjlServiceImp extends BaseBusinessServiceImp implements CwHbzcZhjlService {
	@Autowired
	private CwHbzcZhjlDao dao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcZhjlDomain domain = (CwHbzcZhjlDomain)baseBusinessDomain;
		double oldJe = Double.parseDouble(domain.getOldYe()) - Double.parseDouble(domain.getZhje());
		double newJe = Double.parseDouble(domain.getNewYe()) + Double.parseDouble(domain.getZhje());
		dao.saveDomain(domain, userDomain);
		
		CwHbzcZhjlDomain dom1 = new CwHbzcZhjlDomain();
		CwHbzcZhjlDomain dom2 = new CwHbzcZhjlDomain();
		
		dom1.setSsJgbm(domain.getSsJgbm());
		dom1.setZcflDm(domain.getOldZcflDm());
		dom1.setYhCshDjxh(domain.getOldYhCshDjxh());
		dom1.setYhDjxh(dao.getYhCshDjxh(dom1));
		dom1.setJe(domain.getZhje());
		dom1.setSm("货币资产调整");
		dom1.setJbrCzyDjxh(domain.getJbrCzyDjxh());
		dom1.setRq(domain.getRq());
		dom1.setDjJgbm(userDomain.getBmbm());
		dom1.setBdJe(oldJe + "");
		dom1.setBz("2");
		dom1.setYwxh(domain.getCwDjxh());
		
		dom2.setSsJgbm(domain.getSsJgbm());
		dom2.setZcflDm(domain.getNewZcflDm());
		dom2.setYhCshDjxh(domain.getNewYhCshDjxh());
		dom2.setYhDjxh(dao.getYhCshDjxh(dom2));
		dom2.setJe(domain.getZhje());
		dom2.setSm("货币资产调整");
		dom2.setJbrCzyDjxh(domain.getJbrCzyDjxh());
		dom2.setRq(domain.getRq());
		dom2.setDjJgbm(userDomain.getBmbm());
		dom2.setBdJe(newJe + "");
		dom2.setBz("1");
		dom2.setYwxh(domain.getCwDjxh());
		
		dao.saveZcBdjl(dom1, userDomain);
		dao.saveZcBdjl(dom2, userDomain);
		
		dao.updateYe(dom1);
		dao.updateYe(dom2);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwHbzcZhjlDomain domain=(CwHbzcZhjlDomain)baseBusinessDomain;
		CwHbzcZhjlDomain dom = (CwHbzcZhjlDomain)dao.getDomainByKey(domain);
		CwHbzcZhjlDomain dom1 = new CwHbzcZhjlDomain();
		CwHbzcZhjlDomain dom2 = new CwHbzcZhjlDomain();
		
		dao.deleteByYwxh(dom.getCwDjxh());//删对应变动记录
		
		Double zhje = Double.parseDouble(dom.getZhje());
		dom1.setYhDjxh(dom.getOldYhCshDjxh());
		dom1.setYhCshDjxh(dom.getOldYhCshDjxh());
		Double oldJe = Double.parseDouble((String)dao.getYe(dom1));
		oldJe = oldJe + zhje;
		dom1.setBdJe(oldJe+"");
		
		dom2.setYhDjxh(dom.getNewYhCshDjxh());
		dom2.setYhCshDjxh(dom.getNewYhCshDjxh());
		Double newJe = Double.parseDouble((String)dao.getYe(dom2));
		newJe = newJe - zhje;
		dom2.setBdJe(newJe+"");
		
		dao.updateYe(dom1);
		dao.updateYe(dom2);
		
		dao.deleteByKey(domain, userDomain);
	}

	public void initOldYe(BaseBusinessDomain baseBusinessDomain) throws Exception {
		CwHbzcZhjlDomain domain=(CwHbzcZhjlDomain)baseBusinessDomain;
		domain.setZcflDm(domain.getOldZcflDm());
		domain.setYhCshDjxh(domain.getOldYhCshDjxh());
		if(StringUtils.isBlank(domain.getOldYhCshDjxh())){
			domain.setYhCshDjxh(dao.getYhCshDjxh(domain));
		}
		String ye = (String) dao.getYe(domain);
		
		if("".equals(ye) || ye == null){
			ye = "0";
		}
		
		domain.setOldYe(ye);
	}
	
	public void initNewYe(BaseBusinessDomain baseBusinessDomain) throws Exception {
		CwHbzcZhjlDomain domain=(CwHbzcZhjlDomain)baseBusinessDomain;
		domain.setZcflDm(domain.getNewZcflDm());
		domain.setYhCshDjxh(domain.getNewYhCshDjxh());
		if(StringUtils.isBlank(domain.getNewYhCshDjxh())){
			domain.setYhCshDjxh(dao.getYhCshDjxh(domain));
		}
		String ye = (String) dao.getYe(domain);
		
		if("".equals(ye) || ye == null){
			ye = "0";
		}
		
		domain.setNewYe(ye);
	}
}
