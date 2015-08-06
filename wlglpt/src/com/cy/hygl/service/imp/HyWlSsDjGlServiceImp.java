package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyWlSsDjDao;
import com.cy.hygl.dao.HyWlSsDjGlDao;
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjGlService;
import com.cy.hygl.service.HyWlSsDjService;

@Service
/**
 * The SERVICEIMP for 调度成本审核.
 * 
 * @author HJH
 */
public class HyWlSsDjGlServiceImp extends BaseBusinessServiceImp implements HyWlSsDjGlService {
	@Autowired
	private HyWlSsDjGlDao dao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain)baseBusinessDomain;		
		domain.setDwDm(userDomain.getGsbm());
		initFydjSfsp(domain, userDomain);
	}
	
	public void initFydjSfsp(HyWlSsDjGlDomain domain, UserDomain userDomain) throws Exception {
		String xtcsSfsp = commonDao.getFunXtXtcs("20205", userDomain.getGsbm(), "2");
		domain.setXtcsSfsp(xtcsSfsp);
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		domain.setXtcs20016(xtcs20016);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain)baseBusinessDomain;
		dao.initDomainMx(baseBusinessDomain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	public void getHw(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception {
		if("Y".equals(domain.getConBz())){
			HyZyglFydjDomain qbDomain = new HyZyglFydjDomain();
			qbDomain.setMc("--请选择--");
			domain.getDataList().add(qbDomain);
		}
		
		if (StringUtils.isNotBlank(domain.getPcDjxh())) {
			List<HyZyglFydjDomain> bmList = dao.getHw(domain, userDomain);
			domain.getDataList().addAll(bmList);
		}
	}

	

}
