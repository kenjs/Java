package com.cy.xtgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.dao.QyGwDao;
import com.cy.xtgl.domain.QyGwDomain;
import com.cy.xtgl.service.QyGwService;

@Service
/**
 * The SERVICEIMP for 企业-岗位.
 * 
 * @author HaoY
 */
public class QyGwServiceImp extends BaseBusinessServiceImp implements QyGwService {
	
	@Autowired
	private QyGwDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		String bmMc = dao.getBmMc(domain.getSsJgbm());
		if(StringUtils.isNotBlank(bmMc)){
			domain.setBmMc(SysEncodeUtil.ISO2GBK(bmMc));
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyGwDomain domain=(QyGwDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	public void initXtgwMx(BaseBusinessDomain baseBusinessDomain)
			throws Exception {
		QyGwDomain domain=(QyGwDomain)baseBusinessDomain;
		List<BaseBusinessDomain> xtgwList = dao.queryXtGw(domain);
		domain.setXtgwList(xtgwList);
	}

	public void saveXtgw(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain)
			throws Exception {
		QyGwDomain domain=(QyGwDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		String[] strs = domain.getStr().split("\\|");
		if(strs.length > 0){
			for(int i=0;i<strs.length;i++){
				String[] objs = strs[i].split(",");
				if("Y".equals(objs[4])){
					domain.setBzsm(objs[0]);
					domain.setGwMc(objs[1]);
					domain.setGwJc(objs[2]);
					domain.setGwDm(objs[3]);
					if(dao.checkGw(domain) > 0){
						if("N".equals(dao.getYxbz(domain))){
							domain.setCjrCzyDjxh(userDomain.getCzyDjxh());
							domain.setCjrq(SysDateUtil.getCurrentDate());
							domain.setXgrCzyDjxh(userDomain.getCzyDjxh());
							domain.setXgrq(SysDateUtil.getCurrentDate());
							dao.updateXtgw(domain);
						}
						continue;
					}
					dao.saveXtGw(domain, userDomain);
				}
			}
		}
		
	}

	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyGwDomain domain=(QyGwDomain)baseBusinessDomain;
		dao.saveEnable(domain);
	}

	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyGwDomain domain=(QyGwDomain)baseBusinessDomain;
		dao.saveDisable(domain);
	}

}