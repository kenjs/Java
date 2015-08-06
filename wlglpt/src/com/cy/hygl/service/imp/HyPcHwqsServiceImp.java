package com.cy.hygl.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyPcHwqsDao;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.dao.HyTydglDao;
import com.cy.hygl.domain.HyPcHwqsDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.service.HyPcHwqsService;
import com.cy.zyegl.dao.HyPcXydjDao;


@Service
/**
 * The SERVICEIMP for 货运-派车-货物签收.
 * 
 * @author HJH
 */
public class HyPcHwqsServiceImp extends BaseBusinessServiceImp implements HyPcHwqsService {
	@Autowired
	private HyPcHwqsDao dao;
	@Autowired
	private HyTydglDao tydglDao;
	@Autowired
	private WlglptCommonDao commonDao;
	@Autowired
	private HyPcXydjDao xyDao;
	@Autowired
	private HyPcxxglDao pcDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, -15);
		domain.setPcrqq(sim.format(cal.getTime()));
		
		//自动累加配送费 控制是否允许录入配送费 2013-09-29 by xiay 
		String xtcs20030 = commonDao.getFunXtXtcs("20030", userDomain.getGsbm(), "2");
		domain.setLjbz(xtcs20030);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		domain.setSsJgbm(userDomain.getGsbm());
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain, userDomain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList1(domain,userDomain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain)baseBusinessDomain;
		HyPcxxglDomain dom = new HyPcxxglDomain();
		dom.setPcDjxh(domain.getPcDjxh());
		pcDao.initDomainMx(dom);
		String xtcs20016 = commonDao.getFunXtXtcs("20016", dom.getSsJgbm(), "2");
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			if("Y".equals(xtcs20016)){
				xyDao.checkXydj(domain.getPcDjxh());
			}
		}
		dao.saveDomain(domain, userDomain);
		
		//送货方式为自提(代码为1)时，向货物自提中插入一条数据
		if("1".equals(domain.getShfsDm())){
			dao.insertHwZtInfo(domain, userDomain);
		}

		Long newDdDjxh = dao.callPPcHwqsHxcl(domain.getHwqsDjxh(), domain.getPcDjxh(), userDomain);
		tydglDao.callPHyglDdglTydglHxcl(newDdDjxh, null,"1");
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain=(HyPcHwqsDomain)baseBusinessDomain;
		HyPcHwqsDomain dom = (HyPcHwqsDomain)dao.getDomainByKey(domain);
		dao.callPcHwQsDel(domain.getHwqsDjxh(), domain.getPcDjxh(),domain.getWlssDjxh()); 
		tydglDao.callPHyglDdglTydglDelete(Long.parseLong(dom.getNewDdDjxh()), userDomain.getCzyDjxh(),"1");
	}
	
	public void initPsfMx(BaseBusinessDomain baseBusinessDomain) throws Exception {
		HyPcHwqsDomain domain=(HyPcHwqsDomain)baseBusinessDomain;
		dao.initPsfMx(domain);
	}

	public void savePsf(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain=(HyPcHwqsDomain)baseBusinessDomain;
		dao.savePsf(domain,userDomain);
	}
	
	public void checkWlssSfDj(BaseBusinessDomain baseBusinessDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain)baseBusinessDomain;
		HyPcHwqsDomain dom = dao.checkWlssSfDj(domain);
		if(dom != null){
			domain.setWlssHwSl(dom.getWlssHwSl());
		}else {
			domain.setWlssHwSl("0");
		}
	}
	
}
