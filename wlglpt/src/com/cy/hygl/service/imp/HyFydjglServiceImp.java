package com.cy.hygl.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyFydjglDao;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyFydjglService;
import com.cy.zyegl.dao.HyPcXydjDao;
/**
 * The SERVICEIMP for 费用登记管理.
 * 
 * @author hy
 */
@Service
public class HyFydjglServiceImp extends BaseBusinessServiceImp implements HyFydjglService {
	
	@Autowired
	private HyFydjglDao dao;
	@Autowired
	private WlglptCommonDao commonDao;
	@Autowired
	private HyPcXydjDao xyDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		if(userDomain.getCs_20052().equals("Y")){
			domain.setDjJgbm4Query(userDomain.getBmbm());
		}
		
		initFydjSfsp(domain, userDomain);
		
		domain.setPcJgbm4Query(userDomain.getGsbm());
	}
	
	public void initFydjSfsp(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception {
		String xtcsSfsp = commonDao.getFunXtXtcs("20204", userDomain.getGsbm(), "2");
		domain.setXtcsSfsp(xtcsSfsp);
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		domain.setXtcs20016(xtcs20016);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		if (StringUtils.isNotBlank(domain.getFyDjxh())) {
			HyZyglFydjDomain dom = (HyZyglFydjDomain)dao.getDomainByKey(domain);
			if (dom != null) {
				domain.setPcDjxh(dom.getPcDjxh());
				domain.setWfhDjxh(dom.getWfhDjxh());
				domain.setDdDjxh(dom.getDdDjxh());
				domain.setXh(dom.getHwmxxh());
			}
		}
		
		String selOrUpd = domain.getSelOrUpd();
		String fyDjxh = domain.getFyDjxh();
		String ddDjxh = domain.getDdDjxh();
		String xh = domain.getXh();
		String wfhDjxh = domain.getWfhDjxh();
		
		List<HyZyglFydjDomain> fyDjMxList = dao.queryHyFydjMxList(domain);
		dao.initDomainMx(domain);
		
		domain.setFyDjxh(fyDjxh);
		domain.setDdDjxh(ddDjxh);
		domain.setXh(xh);
		domain.setWfhDjxh(wfhDjxh);
		domain.setSelOrUpd(selOrUpd);
		domain.setZgsbm(userDomain.getZgsbm());
		//判断发送按钮
		if(("0".equals(domain.getWsspztDm()) || "2".equals(domain.getWsspztDm())) && "Y".equals(domain.getSpbz())){
			domain.setSendBz(true);
		}
		//收款方下拉
		List<HyZyglFydjDomain> listSel = new ArrayList<HyZyglFydjDomain>();
		List<HyZyglFydjDomain> skfList = dao.querySkfList(ddDjxh,xh,userDomain);
		//添加司机
		HyZyglFydjDomain dom = new HyZyglFydjDomain();
		dom.setDm(null);
		dom.setMc("---请选择---");
		listSel.add(dom);
		for (HyZyglFydjDomain e1 : skfList) {
			HyZyglFydjDomain dom5 = new HyZyglFydjDomain();
			if("sj".equals(e1.getLb())){
				dom5.setDm(e1.getDm());
				dom5.setMc(e1.getMc());
				listSel.add(dom5);
			}
		}
		//添加分公司
		for (HyZyglFydjDomain e2 : skfList) {
			HyZyglFydjDomain dom4 = new HyZyglFydjDomain();
			if("fgs".equals(e2.getLb())){
				dom4.setDm(e2.getDm());
				dom4.setMc(e2.getMc());
				listSel.add(dom4);
			}
		}
		//其他
		HyZyglFydjDomain dom3 = new HyZyglFydjDomain();
		//“1” 表示 其他收入
		dom3.setDm("1");
		dom3.setMc("其他");
		listSel.add(dom3);
		domain.setSkfList(listSel);
		
		domain.setFyDjMxList(fyDjMxList);
	}


	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			if("Y".equals(xtcs20016)){
				xyDao.checkXydj(domain.getPcDjxh());
			}
		}
		dao.saveDomain(domain, userDomain);
		dao.fydjHxcl(domain, userDomain);
		domain.setFyDjxh(null);
		doMyInitMx(domain, userDomain);
	}


	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		
		dao.fydjHxcl(domain, userDomain);	
		doMyInitMx(domain, userDomain);
	}


	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		doMyQuery(domain, userDomain);
	}

	public void getKhHwXx(BaseBusinessDomain baseBusinessDomain)
			throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		List<HyZyglFydjDomain> khHwList = dao.getKhHwxx(domain);
		domain.setKhHwList(khHwList);
	}

	public void deleteFydj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		dao.deleteFyDj(domain.getFyDjxh());
		dao.fydjHxcl(domain, userDomain);
	}
}
