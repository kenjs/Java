package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.bggl.dao.BgWlryDao;
import com.cy.bggl.domain.BgWlryDomain;
import com.cy.bggl.service.BgWlryService;

@Service
/**
 * The SERVICEIMP for 办公-外联人员.
 * 
 * @author HaoY
 */
public class BgWlryServiceImp extends BaseBusinessServiceImp implements BgWlryService {
	@Autowired
	private BgWlryDao dao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain)baseBusinessDomain;
		//转码：utf-8 -> iso
		if(domain != null && !"".equals(domain.getXm())) {
			String xm = SysEncodeUtil.UTF82ISO(domain.getXm());
			domain.setXm("%" + xm + "%");
		}
		if(domain != null && !"".equals(domain.getDz())) {
			String dz = SysEncodeUtil.UTF82ISO(domain.getDz());
			domain.setDz("%" + dz + "%");
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain)baseBusinessDomain;
		if(domain != null && !"".equals(domain.getXm())) {
			String xm = SysEncodeUtil.UTF82ISO(domain.getXm());
			domain.setXm("%" + xm + "%");
		}
		if(domain != null && !"".equals(domain.getDz())) {
			String dz = SysEncodeUtil.UTF82ISO(domain.getDz());
			domain.setDz("%" + dz + "%");
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryDomain domain=(BgWlryDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
