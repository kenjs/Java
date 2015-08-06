package com.cy.bggl.service.imp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgglJstxDao;
import com.cy.bggl.domain.BgglJstxDomain;
import com.cy.bggl.service.BgglJstxService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;

@Service
/**
 * The SERVICEIMP for 办公-即时通讯.
 * 
 * @author anq
 */
public class BgglJstxServiceImp extends BaseBusinessServiceImp implements BgglJstxService {
	@Autowired
	private BgglJstxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain)baseBusinessDomain;
		domain.setFsrCzyDjxh(userDomain.getCzyDjxh());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		sortJstxList(dataList);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain)baseBusinessDomain;
		domain.setFsrCzyDjxh(userDomain.getCzyDjxh());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain=(BgglJstxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	private void sortJstxList(List<BaseBusinessDomain> dataList) throws Exception {
		Collections.sort(dataList, new Comparator<BaseBusinessDomain>() {
			public int compare(BaseBusinessDomain o1, BaseBusinessDomain o2) {
				BgglJstxDomain t1 = (BgglJstxDomain) o1;
				BgglJstxDomain t2 = (BgglJstxDomain) o2;
				return t1.getJstxXh().toString().compareTo(t2.getJstxXh().toString());
			}
		});
	}
}
