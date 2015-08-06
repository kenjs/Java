package com.cy.zyegl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.zyegl.dao.HyPcHwxxXydjDao;
import com.cy.zyegl.dao.HyPcXydjDao;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;
import com.cy.zyegl.domain.HyPcXydjDomain;
import com.cy.zyegl.service.HyPcXydjService;

@Service
/**
 * The SERVICEIMP for 货运-派车-协议登记.
 * 
 * @author HJH
 */
public class HyPcXydjServiceImp extends BaseBusinessServiceImp implements HyPcXydjService {
	@Autowired
	private HyPcXydjDao dao;
	@Autowired
	private HyPcHwxxXydjDao hwxxDao;
	@Autowired
	private HyPcHwxxDao pcHwxxDao;
	@Autowired
	private HyPcxxglDao hyPcDao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		initXydjSfsp(domain, userDomain);
	}
	
	public void initXydjSfsp(HyPcXydjDomain domain, UserDomain userDomain) throws Exception {
		String xtcs20206 = commonDao.getFunXtXtcs("20206", userDomain.getGsbm(), "2");
		domain.setXtcs20206(xtcs20206);
		
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		domain.setXtcs20016(xtcs20016);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain)baseBusinessDomain;
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		if("N".equals(xtcs20016)){
			throw new DiyServiceException("本企业不需要协议登记！");
		}
		dao.saveDomain(domain, userDomain);
		dao.callProXydjHxcl(domain.getPcDjxh(), userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain=(HyPcXydjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		//hwxxDao.deleteHyPcHwxxXydjByPcDjxh(domain.getPcDjxh());
		dao.callProXydjHxcl(domain.getPcDjxh(), userDomain);
	}
	
	public void initXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		HyPcxxglDomain pcxxDomain = hyPcDao.queryHyPcYfxxByKey(domain.getPcDjxh());
		HyPcXydjDomain dom = (HyPcXydjDomain)dao.getDomainByKey(domain);
		
		if (dom == null) {
			domain.setYfHj(pcxxDomain.getYfHj());
			domain.setYfYfyf(pcxxDomain.getYfYfyf());
			domain.setYfYj(pcxxDomain.getYfYj());
			domain.setYfXxf(pcxxDomain.getYfXxf());
			domain.setYfSjs(pcxxDomain.getYfSjs());
			domain.setYfHdyf(pcxxDomain.getYfHdyf());
			domain.setYfHdf(pcxxDomain.getYfHdf());
			domain.setAddFlag("Y");
		}else {
			BeanUtils.copyProperties(domain, dom);
		}
		
		domain.setPcxxDomain(pcxxDomain);
		domain.setBmbm(userDomain.getBmbm());
		if ("Y".equals(domain.getSpbz()) && ("0".equals(domain.getWsspztDm()) || "2".equals(domain.getWsspztDm())) 
				&& ("Y".equals(domain.getYfbgbz()) || "Y".equals(domain.getSlbgbz()))) {
			domain.setKfsFlag("Y");
		}
	}
	
	public void initXybd(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		HyPcxxglDomain pcxxDomain = hyPcDao.queryHyPcYfxxByKey(domain.getPcDjxh());
		HyPcXydjDomain dom = (HyPcXydjDomain)dao.getDomainByKey(domain);
		
		if (dom == null) {
			domain.setYfHj(0.0);
			domain.setYfYfyf(0.0);
			domain.setYfYj(0.0);
			domain.setYfXxf(0.0);
			domain.setYfSjs(0.0);
			domain.setYfHdyf(0.0);
			domain.setYfHdf(0.0);
			domain.setAddFlag("Y");
		}else {
			BeanUtils.copyProperties(domain, dom);
		}
		Double yfyHj =  pcxxDomain.getYfHj()==null?0.0:pcxxDomain.getYfHj();
		Double yyfYfyf =  pcxxDomain.getYfYfyf()==null?0.0:pcxxDomain.getYfYfyf();
		Double yyfYj =  pcxxDomain.getYfYj()==null?0.0:pcxxDomain.getYfYj();
		Double yyfXxf =  pcxxDomain.getYfXxf()==null?0.0:pcxxDomain.getYfXxf();
		Double yyfSjs =  pcxxDomain.getYfSjs()==null?0.0:pcxxDomain.getYfSjs();
		Double yyfHdyf =  pcxxDomain.getYfHdyf()==null?0.0:pcxxDomain.getYfHdyf();
		Double yyfHdf =  pcxxDomain.getYfHdf()==null?0.0:pcxxDomain.getYfHdf();
		
		domain.setPcxxDomain(pcxxDomain);
		domain.setYxyfHj(yfyHj+"/"+domain.getYfHj());
		domain.setYxyfYfyf(yyfYfyf+"/"+domain.getYfYfyf());
		domain.setYxyfYj(yyfYj+"/"+domain.getYfYj());
		domain.setYxyfXxf(yyfXxf+"/"+domain.getYfXxf());
		domain.setYxyfSjs(yyfSjs+"/"+domain.getYfSjs());
		domain.setYxyfHdyf(yyfHdyf+"/"+domain.getYfHdyf());
		domain.setYxyfHdf(yyfHdf+"/"+domain.getYfHdf());
		
		
		List<BaseBusinessDomain> dataList = dao.queryHyPcXybdList(domain.getPcDjxh());
		domain.setDataList(dataList);
	}
	
	public void initHwxxXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		HyPcHwxxXydjDomain hwmxDomain = domain.getHwmxDomain();
		String editFlag = hwmxDomain.getEditFlag();
		HyPcHwxxXydjDomain dom = (HyPcHwxxXydjDomain)hwxxDao.getDomainByKey(domain.getHwmxDomain());
		
		HyPcHwxxDomain pcHwDomain = pcHwxxDao.getPcHwxxDomainByKey(hwmxDomain.getPcDjxh(), hwmxDomain.getWfhDjxh());
		
		if (dom == null) {
			hwmxDomain.setDdDjxh(pcHwDomain.getDdDjxh());
			hwmxDomain.setXh(pcHwDomain.getXh());
			hwmxDomain.setHdbh(pcHwDomain.getHdbh());
			if (StringUtils.isNotBlank(pcHwDomain.getYqDdrq())) {
				hwmxDomain.setYqDdrq(SysDateUtil.parse(pcHwDomain.getYqDdrq()));
			}
			hwmxDomain.setShfsDm(pcHwDomain.getShfsDm());
			hwmxDomain.setSzHwSl(pcHwDomain.getHwSl());
			hwmxDomain.setSzHwTj(pcHwDomain.getHwTj());
			hwmxDomain.setSzHwZl(pcHwDomain.getHwZl());
			hwmxDomain.setSzJsSl(pcHwDomain.getJsSl());
			hwmxDomain.setSzHwBzHldwDm(pcHwDomain.getHwBzHldwDm());
			hwmxDomain.setShrXzqhDm(pcHwDomain.getShrXzqhDm());
			hwmxDomain.setShrXzqhMc(pcHwDomain.getShrXzqhMc());
			hwmxDomain.setShrDjxh(pcHwDomain.getShrDjxh());
			hwmxDomain.setShrMc(pcHwDomain.getShrMc());
			hwmxDomain.setShrDz(pcHwDomain.getShrDz());
			hwmxDomain.setShrLxr(pcHwDomain.getShrLxr());
			hwmxDomain.setShrLxdh(pcHwDomain.getShrLxdh());
		}else {
			BeanUtils.copyProperties(hwmxDomain, dom);
		}
		
		hwmxDomain.setEditFlag(editFlag);
		domain.setHwmxDomain(hwmxDomain);
		domain.setPcHwDomain(pcHwDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveHwmxXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		if("N".equals(xtcs20016)){
			throw new DiyServiceException("本企业不需要协议登记！");
		}
		hwxxDao.saveDomain(domain.getHwmxDomain(), userDomain);
		dao.updatePcXydjSlbgbz(domain.getHwmxDomain());
		dao.callProXydjHxcl(domain.getHwmxDomain().getPcDjxh(), userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteHwmxXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		hwxxDao.deleteByKey(domain.getHwmxDomain(), userDomain);
		dao.callProXydjHxcl(domain.getHwmxDomain().getPcDjxh(), userDomain);
	}
}
