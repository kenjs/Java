package com.cy.dzgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.dzgl.dao.QySpwsDao;
import com.cy.dzgl.domain.QySpwsDomain;
import com.cy.dzgl.service.QySpwsService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;

@Service
/**
 * The SERVICEIMP for 企业-审批文书.
 * 
 * @author HaoY
 */
public class QySpwsServiceImp extends BaseBusinessServiceImp implements QySpwsService {
	@Autowired
	private QySpwsDao dao;

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain)baseBusinessDomain;
		//设置查询条件
		domain.setJgbm(userDomain.getZgsjbdm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain)baseBusinessDomain;
		//设置查询条件
		domain.setJgbm(userDomain.getZgsjbdm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsDomain domain = (QySpwsDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		String[] strObjs = domain.getStrObj().split("\\|");
		if(strObjs.length > 0){
			for(int i = 0;i < strObjs.length;i ++){
				String[] obj = strObjs[i].split(",");
				if("".equals(obj[2]) && "N".equals(obj[0])){
					continue;
				}
				domain.setWsDm(obj[3]);
				domain.setWsspmsDm(obj[1]);
				domain.setJgbm(obj[2]);
				if("Y".equals(obj[0])){
					dao.saveDomain(domain, userDomain);
				}else{
					dao.deleteByKey(domain, userDomain);
				}
			}
		}
	}

}
