package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.hygl.dao.HyHwxxShfsbgDao;
import com.cy.hygl.dao.HyHwxxShfsbgZbDao;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.dao.HyTydHwmxDao;
import com.cy.hygl.domain.HyHwxxShfsbgDomain;
import com.cy.hygl.domain.HyHwxxShfsbgZbDomain;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyTydHwmxDomain;
import com.cy.hygl.service.HyHwxxShfsbgService;

@Service
/**
 * The SERVICEIMP for 货运-货物信息-送货方式变更.
 * 
 * @author HJH
 */
public class HyHwxxShfsbgServiceImp extends BaseBusinessServiceImp implements HyHwxxShfsbgService {
	@Autowired
	private HyHwxxShfsbgDao dao;
	@Autowired
	private HyHwxxShfsbgZbDao zbDao;
	@Autowired
	private HyPcxxglDao pcDao;
	@Autowired
	private HyPcHwxxDao pcHwxxDao;
	@Autowired
	private HyTydHwmxDao tydhwmxDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain)baseBusinessDomain;
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain)baseBusinessDomain;
		HyPcHwxxDomain pcDom = domain.getPchwDomain();
		pcDom.setPcDjxh(domain.getPcDjxh());
		pcDom.setWfhDjxh(domain.getWfhDjxh());
		
		HyTydHwmxDomain ddDom = domain.getDdhwDomain();
		ddDom.setDdDjxh(domain.getDdDjxh());
		ddDom.setXh(domain.getXh());
		ddDom.setTempFlag("N");
		
		zbDao.initDomainMx(domain);
		pcHwxxDao.initDomainMx(pcDom);
		tydhwmxDao.initDomainMx(ddDom);
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain)baseBusinessDomain;
		HyPcxxglDomain dom = domain.getPcxxDomain();
		dom.setPage(domain.getPage());
		dom.setPcfsDm("2");
		List<BaseBusinessDomain> dataList = pcDao.queryList(dom);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain)baseBusinessDomain;
		int count = dao.checkShfs(domain.getPcDjxh());
		if(count>0){
			throw new DiyServiceException("该货物下游已接受，不可变更送货方式！");
		}
		dao.saveDomain(domain, userDomain);
		
		HyHwxxShfsbgZbDomain zbDom = new HyHwxxShfsbgZbDomain();
		if(StringUtils.isNotBlank(domain.getShbgDjxh())){
			zbDom.setShbgDjxh(domain.getShbgDjxh());
		}else{
			HyHwxxShfsbgDomain dom = new HyHwxxShfsbgDomain();
			BeanUtils.copyProperties(dom, domain);
			dao.initDomainMx(dom);
			zbDom.setShbgDjxh(dom.getShbgDjxh());
		}
		zbDom.setPcDjxh(domain.getPcDjxh());
		zbDom.setDdDjxh(domain.getDdDjxh());
		zbDom.setWfhDjxh(domain.getWfhDjxh());
		zbDom.setXh(domain.getXh());
		zbDom.setBspsf(domain.getBspsf());
		zbDom.setBz(domain.getBz());
		zbDom.setSsJgbm(userDomain.getGsbm());
		zbDao.saveDomain(zbDom, userDomain);
		
		HyPcHwxxDomain pcDom = domain.getPchwDomain();
		pcDom.setPcDjxh(domain.getPcDjxh());
		pcDom.setWfhDjxh(domain.getWfhDjxh());
		pcHwxxDao.updatePcHwxxWhenShfsbg(pcDom);
		
		HyTydHwmxDomain ddDom = domain.getDdhwDomain();
		ddDom.setDdDjxh(domain.getDdDjxh());
		ddDom.setXh(domain.getXh());
		tydhwmxDao.updateHwxxWhenShfsbg(ddDom);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain=(HyHwxxShfsbgDomain)baseBusinessDomain;
		int count = dao.checkShfs(domain.getPcDjxh());
		if(count>0){
			throw new DiyServiceException("该货物下游已接受，不可变更送货方式！");
		}
		
		HyPcHwxxDomain pcDom = domain.getPchwDomain();
		pcDom.setPcDjxh(domain.getPcDjxh());
		pcDom.setWfhDjxh(domain.getWfhDjxh());

				
		HyTydHwmxDomain ddDom = domain.getDdhwDomain();
		ddDom.setDdDjxh(domain.getDdDjxh());
		ddDom.setXh(domain.getXh());
		ddDom.setTempFlag("N");
		if("1".equals(domain.getShBz())){
			pcDom.setShfsDm("2");
			ddDom.setShfsDm("2");
		}else{
			pcDom.setShfsDm("1");
			ddDom.setShfsDm("1");
		}
		tydhwmxDao.updateWhenDeleteShfsbg(ddDom);
		pcHwxxDao.updateWhenDeleteShfsbg(pcDom);
		

		int zbCount = dao.checkShfsbgZb(domain.getDdDjxh(), domain.getXh());
		if(zbCount<=1){
			dao.deleteByKey(domain, userDomain);
		}
		zbDao.deleteByKey(domain, userDomain);
	}
	
	public void viewMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain)baseBusinessDomain;
		HyPcHwxxDomain pcDom = domain.getPchwDomain();
		pcDom.setPcDjxh(domain.getPcDjxh());
		pcDom.setWfhDjxh(domain.getWfhDjxh());
		
		HyTydHwmxDomain ddDom = domain.getDdhwDomain();
		ddDom.setDdDjxh(domain.getDdDjxh());
		ddDom.setXh(domain.getXh());
		ddDom.setTempFlag("N");
		
		zbDao.initDomainMx(domain);
		pcHwxxDao.initDomainMx(pcDom);
		tydhwmxDao.initDomainMx(ddDom);	
	}
}
