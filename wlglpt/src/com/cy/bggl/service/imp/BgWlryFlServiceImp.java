package com.cy.bggl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.bggl.dao.BgWlryFlDao;
import com.cy.bggl.domain.BgWlryFlDomain;
import com.cy.bggl.service.BgWlryFlService;

@Service
/**
 * The SERVICEIMP for 办公-外联人员-分类.
 * 
 * @author HaoY
 */
public class BgWlryFlServiceImp extends BaseBusinessServiceImp implements BgWlryFlService {
	@Autowired
	private BgWlryFlDao dao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain)baseBusinessDomain;
		//转码：utf-8 -> iso
		if(domain != null && !"".equals(domain.getFlmc())){
			String flmc = SysEncodeUtil.UTF82ISO((domain.getFlmc()));
			domain.setFlmc("%"+ flmc + "%");
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain)baseBusinessDomain;
		if(domain != null && !"".equals(domain.getFlmc())){
			String flmc = SysEncodeUtil.UTF82ISO((domain.getFlmc()));
			domain.setFlmc("%"+ flmc + "%");
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryFlDomain domain = (BgWlryFlDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgWlryFlDomain domain=(BgWlryFlDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
}
