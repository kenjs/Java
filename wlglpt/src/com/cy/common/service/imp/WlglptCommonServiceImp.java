package com.cy.common.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.common.service.WlglptCommonService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.domain.QyJsDomain;

@Service
public class WlglptCommonServiceImp extends BaseBusinessServiceImp implements WlglptCommonService {

	@Autowired
	private WlglptCommonDao dao;
	
	private static final String	QBSTR	= "---«Î—°‘Ò---";
	
	@SuppressWarnings("unchecked")
	public void getCommonList(WlglptCommonDomain domain, UserDomain userDomain) throws Exception {
		SysEncodeUtil.decodeURL(domain);
		
		if("Y".equals(domain.getContainQbBz())){
			DmbGgDomain qbDomain = new DmbGgDomain();
			qbDomain.setMc(QBSTR);
			domain.getDataList().add(qbDomain);
		}
		
		if (StringUtils.isNotBlank(domain.getParamdm())) {
			List<DmbGgDomain> bmList = dao.queryCommonList(domain, userDomain);
			domain.getDataList().addAll(bmList);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getBmList(WlglptCommonDomain domain) throws Exception {
		if("Y".equals(domain.getContainQbBz())){
			DmbGgDomain qbDomain = new DmbGgDomain();
			qbDomain.setMc(QBSTR);
			domain.getDataList().add(qbDomain);
		}
		
		if (StringUtils.isNotBlank(domain.getParamdm())) {
			List<DmbGgDomain> bmList = dao.queryBmList(domain);
			domain.getDataList().addAll(bmList);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getGwList(WlglptCommonDomain domain) throws Exception {
		if("Y".equals(domain.getContainQbBz())){
			DmbGgDomain qbDomain = new DmbGgDomain();
			qbDomain.setMc(QBSTR);
			domain.getDataList().add(qbDomain);
		}
		
		if (StringUtils.isNotBlank(domain.getParamdm())) {
			List<DmbGgDomain> gwList = dao.queryGwList(domain);
			domain.getDataList().addAll(gwList);
		}
	}
	
	public String getQyJbdmByJgbm(String jgbm) throws Exception{
		return dao.getQyJbdmByJgbm(jgbm);
	}
	
	public List<QyJsDomain> getJsListByJgbm(String jgbm) throws Exception{
		List<QyJsDomain> data = new ArrayList<QyJsDomain>();
		if (jgbm == null || "".equals(jgbm.trim()))
			return data;
		
		return dao.getJsListByJgbm(jgbm);
	}
	
	public String getSjJgbmByJgbm(String jgbm) throws Exception{
		return dao.getSjJgbmByJgbm(jgbm);
	}
	
	public void queryCurrentFbs(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		WlglptCommonDomain domain = (WlglptCommonDomain)baseDomain;
		if("Y".equals(domain.getContainQbBz())){
			DmbGgDomain qbDomain = new DmbGgDomain();
			qbDomain.setMc(QBSTR);
			domain.getDataList().add(qbDomain);
		}
		
		List<DmbGgDomain> fbsList = dao.queryFbsByJgbm(domain, userDomain.getGsbm());
		domain.getDataList().addAll(fbsList);
	}
	
	@SuppressWarnings("unchecked")
	public void queryWs(BaseBusinessDomain baseDomain) throws Exception{
		WlglptCommonDomain domain = (WlglptCommonDomain)baseDomain;
		if("Y".equals(domain.getContainQbBz())){
			DmbGgDomain qbDomain = new DmbGgDomain();
			qbDomain.setMc(QBSTR);
			domain.getDataList().add(qbDomain);
		}
		if (StringUtils.isNotBlank(domain.getParamdm())) {
			List<DmbGgDomain> wsList = dao.queryWs(domain);
			domain.getDataList().addAll(wsList);
		}
	}
}
