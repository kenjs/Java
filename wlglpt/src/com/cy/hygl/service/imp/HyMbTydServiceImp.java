package com.cy.hygl.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyMbTydDao;
import com.cy.hygl.dao.HyTydHwmxDao;
import com.cy.hygl.dao.HyTydMbHwmxDao;
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyMbTydHwmxDomain;
import com.cy.hygl.domain.HyTydHwmxDomain;
import com.cy.hygl.service.HyMbTydService;

@Service
/**
 * The SERVICEIMP for 货运-模版-托运单.
 * 
 * @author HJH
 */
public class HyMbTydServiceImp extends BaseBusinessServiceImp implements HyMbTydService {
	@Autowired
	private HyMbTydDao dao;
	@Autowired
	private HyTydMbHwmxDao hwmxDao; 
	@Autowired
	private HyTydHwmxDao tydHwmxDao;
	
	//余款结算方式
	private static String DM_YKJSFS_YJ = "1";
	private static String DM_YKJSFS_HF = "2";
	private static String DM_YKJSFS_YJHF = "3";
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		if (StringUtils.isNotBlank(domain.getMbDjxh())) {
			List<HyMbTydHwmxDomain> hwList = hwmxDao.queryHwmxByTydXh(Long.parseLong(domain.getMbDjxh()), domain.getHwmxDomain().getTempFlag());
			domain.setHwList(hwList);
		}
		
		domain.getHwmxDomain().setZlTjProportion(userDomain.getZlTjProportion());
		
		if (StringUtils.isBlank(domain.getMbDjxh())) {
			domain.setSsJgbm(userDomain.getGsbm());
			domain.setDjJgbm(userDomain.getBmbm());
			domain.setDjJgmc(userDomain.getBmjc());
			domain.getHwmxDomain().setTempFlag("Y");
		}else {
			domain.getHwmxDomain().setTempFlag("N");
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain)baseBusinessDomain;
		initTydDomainBeforeSave(domain);
		dao.saveDomain(domain, userDomain);
		saveHwxxToFormalTable(domain, userDomain);
	}
	
	private void initTydDomainBeforeSave(HyMbTydDomain domain) throws Exception {	
		domain.setYjjsfsDm("1");
		domain.setPsbz("N");
	}
	
	/**
	 * 保存货物临时表数据到正式表,并删除临时表数据
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	private void saveHwxxToFormalTable(HyMbTydDomain domain, UserDomain userDomain) throws Exception {
		//只有新增或有复制或模板的时候才需要处理临时表
		if ("N".equals(domain.getHwmxDomain().getTempFlag())) {
			return;
		}
		hwmxDao.saveHwxxToFormal(domain);
		hwmxDao.deleteHyMbTydHwxxTempByDdDjxh(Long.parseLong(domain.getMbDjxh()));
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	public void refreshHwList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		List<HyMbTydHwmxDomain> hwList = hwmxDao.queryHwmxByTydXh(Long.parseLong(domain.getMbDjxh()), domain.getHwmxDomain().getTempFlag());
		domain.setHwList(hwList);
	}
	
	private void autoSetHwJsJldwdm(HyMbTydHwmxDomain hwmxDomain) throws Exception {
		String jsJldwDm = "";
		Double jsSl = 0.0;
		if ("01".equals(hwmxDomain.getJsJldwFlDm())) {
			jsJldwDm = hwmxDomain.getHwSlJldwDm();
			jsSl = hwmxDomain.getHwSl();
		}else if ("02".equals(hwmxDomain.getJsJldwFlDm())) {
			jsJldwDm = hwmxDomain.getHwZlJldwDm();
			jsSl = hwmxDomain.getHwZl();
		}else if ("03".equals(hwmxDomain.getJsJldwFlDm())) {
			jsJldwDm = hwmxDomain.getHwTjJldwDm();
			jsSl = hwmxDomain.getHwTj();
		}
		hwmxDomain.setJsJldwDm(jsJldwDm);
		hwmxDomain.setJsSl(jsSl);
	}
	
	public void initHwMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		HyMbTydHwmxDomain hwmxDomain = domain.getHwmxDomain();
		
		String tempFlag = hwmxDomain.getTempFlag();
		hwmxDao.initDomainMx(hwmxDomain);
		hwmxDomain.setTempFlag(tempFlag);
		
		//hwmxDomain.setQyHwBzJldwDm(userDomain.getHwBzJldwDm());
		hwmxDomain.setQyHwSlJldwDm(userDomain.getHwSlJldwDm());
		hwmxDomain.setQyHwZlJldwDm(userDomain.getHwZlJldwDm());
		hwmxDomain.setQyHwTjJldwDm(userDomain.getHwTjJldwDm());
		hwmxDomain.setZlTjProportion(userDomain.getZlTjProportion());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveHwMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)
			throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		HyMbTydHwmxDomain hwmxDomain = domain.getHwmxDomain();
		if(StringUtils.isBlank(hwmxDomain.getJsJldwFlDm())){
			hwmxDomain.setJsJldwFlDm("01");
		}
		autoSetHwJsJldwdm(hwmxDomain);
		hwmxDomain.setYjjsfsDm("1");
		hwmxDomain.setPsbz("N");
		hwmxDao.saveDomain(hwmxDomain, userDomain);	
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteHwMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		if (StringUtils.isNotBlank(domain.getMbDjxh()) && domain.getHwXhs().size() > 0) {
			hwmxDao.deleteHwxxByXhs(domain.getMbDjxh(), domain.getHwXhs(), domain.getHwmxDomain().getTempFlag());
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAsTemplate(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		dao.saveAsTemplate(domain, userDomain);
	}
	
	public void checkTemplateName(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		dao.checkTemplateName(domain, userDomain);
	}
	
	public void initMb4Tydgl(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		String fhrMc = SysEncodeUtil.ISO2GBK(domain.getFhrMc());
		domain.setFhrMc(fhrMc);
	}
	
	public void queryMb4Tydgl(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		List<BaseBusinessDomain>  dataList = dao.queryMb4Tydgl(domain, userDomain);
		domain.setDataList(dataList);
	}
	
	public void initTydByMb(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain=(HyMbTydDomain)baseBusinessDomain;
		
		Long ddDjxh = domain.getDdDjxh();
		String ddbh = domain.getDdbh();
		
		ddDjxh = tydHwmxDao.saveCopyOrMbHwxxToTemp(domain.getMbDjxh(), ddDjxh, "MB");
		this.doMyInitMx(domain, userDomain);
		int yqfhrq = domain.getYqFhrq();
		int yqddrq = domain.getYqDdrq();
		
		domain.setYqFhrqDate(SysDateUtil.getIntervalDate(SysDateUtil.getSqlDate(), Calendar.DATE, yqfhrq));
		domain.setYqDdrqDate(SysDateUtil.getIntervalDate(SysDateUtil.getSqlDate(), Calendar.DATE, yqddrq));
		domain.setDdDjxh(ddDjxh);
		domain.setDdbh(ddbh);
		
		List<HyTydHwmxDomain> tydHwList = tydHwmxDao.queryHwmxByTydXh(domain.getDdDjxh(), "Y");
		List<HyMbTydHwmxDomain> hwList = new ArrayList<HyMbTydHwmxDomain>();
		if (tydHwList != null) {
			for (int i=0; i<tydHwList.size(); i++) {
				HyTydHwmxDomain hw = tydHwList.get(i);
				HyMbTydHwmxDomain mbHw = new HyMbTydHwmxDomain();
				BeanUtils.copyProperties(mbHw, hw);
				mbHw.setDdDjxh(ddDjxh);
				hwList.add(mbHw);
			}
		}
		domain.setHwList(hwList);
		domain.setXdrq(SysDateUtil.getSqlDate());
	}
	
}
