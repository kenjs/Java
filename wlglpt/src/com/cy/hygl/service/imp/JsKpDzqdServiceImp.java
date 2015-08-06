package com.cy.hygl.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.JsKpDzqdDao;
import com.cy.hygl.domain.JsKpDzqdDomain;
import com.cy.hygl.service.JsKpDzqdService;
import com.cy.common.domain.UserDomain;


@Service
/**
 * The SERVICEIMP for 开票申请.
 * 
 * @author HCM
 */
public class JsKpDzqdServiceImp extends BaseBusinessServiceImp implements JsKpDzqdService {
	@Autowired
	private JsKpDzqdDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		JsKpDzqdDomain domain = (JsKpDzqdDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		
		
		dao.callPHyglJsglYkphx(domain.getKpsqmxDjxh());
	}

	public void doMyInitHxMx(BaseBusinessDomain baseBusinessDomain)throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseBusinessDomain;
		dao.initHxMx(domain);	
	}
	public void doMyInitAddHxMx(BaseBusinessDomain baseBusinessDomain)throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseBusinessDomain;
		dao.initAddHxMx(domain);	
	}
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpDzqdDomain domain=(JsKpDzqdDomain)baseBusinessDomain;
		List<String> xhs=domain.getKpsqmxDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		
		//删除结算-开票申请-对帐清单
		for (String xh : xhs) {
			dao.deleteJsKpsqMxByKey(xh);
			dao.callPHyglJsglYkphx(xh);
		}
	}
}
