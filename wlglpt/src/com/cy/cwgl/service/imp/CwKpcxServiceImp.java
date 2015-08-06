package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwKpcxDao;
import com.cy.cwgl.domain.CwKpcxDomain;
import com.cy.cwgl.service.CwKpcxService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-开票登记
 * 
 * @author LYY
 */
@Service
public class CwKpcxServiceImp extends BaseBusinessServiceImp implements CwKpcxService{
	@Autowired
	private CwKpcxDao dao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwKpcxDomain domain = (CwKpcxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpcxDomain domain = (CwKpcxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKpcxDomain domain = (CwKpcxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		for(BaseBusinessDomain e:dataList){
			CwKpcxDomain dom = (CwKpcxDomain) e;
			if(StringUtils.isNotBlank(dom.getZfbz())){
				String str0 = dom.getZfbz().split(">")[1];
				dom.setZfbz(str0.split("<")[0]);
			}
			if(StringUtils.isNotBlank(dom.getHzbz())){
				String str0 = dom.getHzbz().split(">")[1];
				dom.setHzbz(str0.split("<")[0]);
			}
		}
		domain.setDataList(dataList);
	}

}
