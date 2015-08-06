package com.cy.hygl.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyJsglPsfqrDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.service.HyJsglPsfqrService;


@Service
/**
 *
 */
public class HyJsglPsfqrServiceImp extends BaseBusinessServiceImp implements HyJsglPsfqrService {
	@Autowired
	private HyJsglPsfqrDao dao;
	@Autowired
	private WlglptCommonDao commonDao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		String xtcs20030 = commonDao.getFunXtXtcs("20030", userDomain.getGsbm(), "2");
		domain.setXtcs20030(xtcs20030);
	}
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> domList = dao.queryList(domain,userDomain);
		List<BaseBusinessDomain> dataList = new ArrayList<BaseBusinessDomain>();
		for (BaseBusinessDomain baseBusinessDomain2 : domList) {
			HyPcxxglDomain e = (HyPcxxglDomain)baseBusinessDomain2;
			String qrBz = dao.getPsfQrBz(e);
			e.setSfQr(qrBz);
			dataList.add(e);
		}
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> domList = dao.downloadList(domain,userDomain);
		List<BaseBusinessDomain> dataList = new ArrayList<BaseBusinessDomain>();
		for (BaseBusinessDomain baseBusinessDomain2 : domList) {
			HyPcxxglDomain e = (HyPcxxglDomain)baseBusinessDomain2;
			String qrBz = dao.getPsfQrBz(e);
			e.setSfQr(qrBz);
			dataList.add(e);
		}
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	public void viewMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseDomain;
		dao.viewMx(domain);
	}

	public void plqr(BaseBusinessDomain baseDomain,UserDomain user) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		
		List<String> plqrXhs = domain.getPlqrXhs();
		if(plqrXhs == null || plqrXhs.isEmpty()){
			return;
		}
		
		int i=0;
		for (String e : plqrXhs) {
			HyPcxxglDomain dom = new HyPcxxglDomain();
			dom.setPsfDjxh(e);
			dom.setQrJg("Y");
			
			dao.saveDomain(dom, user);
			i++;
		}
		
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyDeleteBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		int checkRes = dao.checkPsfSfTh(domain.getPsfDjxh());
		if(checkRes > 0){
			throw new DiyServiceException("该条货物已经配送派车不能退回！");
		}
	}
	
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
}
