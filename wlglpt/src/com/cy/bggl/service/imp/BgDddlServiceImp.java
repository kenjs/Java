package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.bggl.dao.BgDddlDao;
import com.cy.bggl.domain.BgDddlDomain;
import com.cy.bggl.service.BgDddlService;

@Service
/**
 * The SERVICEIMP for 办公-单点登录.
 * 
 * @author HaoY
 */
public class BgDddlServiceImp extends BaseBusinessServiceImp implements BgDddlService {
	@Autowired
	private BgDddlDao dao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain)baseBusinessDomain;
		//转码：utf-8 -> iso
		if(domain != null && !"".equals(domain.getMc())){
			String mc = SysEncodeUtil.UTF82ISO(domain.getMc());
			domain.setMc("%" + mc + "%");
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain)baseBusinessDomain;
		if(domain != null && !"".equals(domain.getMc())){
			String mc = SysEncodeUtil.UTF82ISO(domain.getMc());
			domain.setMc("%" + mc + "%");
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgDddlDomain domain = (BgDddlDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgDddlDomain domain=(BgDddlDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
