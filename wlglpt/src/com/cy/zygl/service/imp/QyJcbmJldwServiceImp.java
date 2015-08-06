package com.cy.zygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyJcbmJldwDao;
import com.cy.zygl.domain.QyJcbmJldwDomain;
import com.cy.zygl.service.QyJcbmJldwService;

@Service
/**
 * The SERVICEIMP for 企业-基础编码-计量单位.
 * 
 * @author HaoY
 */
public class QyJcbmJldwServiceImp extends BaseBusinessServiceImp implements QyJcbmJldwService {
	@Autowired
	private QyJcbmJldwDao dao;
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getZgsbm());
		List<QyJcbmJldwDomain> jldwList = dao.queryAllJldw(domain);
		domain.setJldwList(jldwList);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getGsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyJcbmJldwDomain domain = (QyJcbmJldwDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		String[] strs = domain.getStrObj().split("\\|");
		if(strs.length > 0){
			for(int i=0;i<strs.length;i++){
				String[] objs = strs[i].split(",");
				for(int j=0;j<objs.length;j++){
					if("".equals(domain.getSsJgbm().trim() ) && "N".equals(domain.getQybz())){
						continue;
					}
					if(!"".equals(objs[0].trim())){
						domain.setHsbl(new Double(objs[0]));
					}else{
						domain.setHsbl(0.1);
					}
					domain.setQybz(objs[1]);
					domain.setJbdwbz(objs[2]);
					domain.setJldwDm(objs[3]);
					if("Y".equals(domain.getQybz())){
						dao.saveDomain(domain, userDomain);
					}else{
						dao.deleteByKey(domain, userDomain);
					}
				}
			}
		}
		
	}

}
