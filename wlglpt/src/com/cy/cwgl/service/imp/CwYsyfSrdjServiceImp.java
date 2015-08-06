package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwHbzcCshDao;
import com.cy.cwgl.dao.CwYsyfSrdjDao;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.cwgl.service.CwYsyfSrdjService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The SERVICE for ����-����Ǽ�
 * 
 * @author HCM
 */
@Service
public class CwYsyfSrdjServiceImp extends BaseBusinessServiceImp implements CwYsyfSrdjService{
	@Autowired
	private CwYsyfSrdjDao dao;
	@Autowired
	private CwHbzcCshDao cshDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseBusinessDomain;
		// �ڴ���ӳ�ʼ����Ӧ����
		domain.setYfjsfDmList(dao.getLbList());
		domain.setSsJgbm(userDomain.getGsbm());
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		
		domain.setJbrCzyDjxh(userDomain.getCzyDjxh());
		
		domain.setSsJgbm(userDomain.getGsbm());
	}


	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		
		dao.callPHyglCwglSrdjHxcl(domain.getSrDjxh(), "0");
	}
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain=(CwYsyfSrdjDomain)baseBusinessDomain;
		List<String> xhs=domain.getSrDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		
			//ɾ������-��Ʊ����-�����嵥
	    for (String xh : xhs) {
	    	dao.checkDelete(xh, 0);
	    	dao.deleteSrdj(xh);			
			dao.callPHyglCwglSrdjHxcl(xh, "0");
		}
   }
	public void getMcList(BaseBusinessDomain baseBusinessDomain)throws Exception {
		CwYsyfSrdjDomain domain=(CwYsyfSrdjDomain)baseBusinessDomain;
		List<CwYsyfSrdjDomain> list;
		if(StringUtils.isNotBlank(domain.getYfjsfDm())){
		   list = dao.getMcList(domain.getSsJgbm(),domain.getYfjsfDm());
		}else{
		   list = dao.getMcList(domain.getSsJgbm(),"");
		}
		domain.setYfjsfMcList(list);
	}

	public void doCancle(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain=(CwYsyfSrdjDomain)baseBusinessDomain;
		//dao.doCancle(domain);
		dao.checkDelete(domain.getYsyfDjxh(), 1);
		dao.callPHyglCwglSrdjHxcl(domain.getYsyfDjxh(), "1");
		dao.initCwYsJe(domain,userDomain);
		
		
	}
	//@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void plDj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		CwYsyfSrdjDomain domain=(CwYsyfSrdjDomain)baseBusinessDomain;
		List<String> ysyfDjxhs=domain.getYsyfDjxhs();
		if(null==ysyfDjxhs|| ysyfDjxhs.isEmpty())
			return;
		
		//ɾ������-��Ʊ����-�����嵥
	    for (String xh : ysyfDjxhs) {
			
	    	CwYsyfSrdjDomain srdjDmain = (CwYsyfSrdjDomain) dao.getYsyfSrdj(xh);
			CwYsyfSrdjDomain srdjDom = new CwYsyfSrdjDomain();
			
			srdjDom.setYsyfDjxh(srdjDmain.getYsyfDjxh());
			srdjDom.setYfjsfDm(srdjDmain.getYfjsfDm());
			srdjDom.setYfjsfDjxh(srdjDmain.getYfjsfDjxh());
			if(StringUtils.isNotEmpty(srdjDmain.getYfjsfDjmc())){
				srdjDom.setFkfmc(srdjDmain.getYfjsfDjmc());
			}else {
				srdjDom.setFkfmc(srdjDmain.getYfjsfMc());
			}
//			String yhCshDjxh = cshDao.getYhCshDjxhWhenXj(userDomain.getGsbm());
//			if(StringUtils.isBlank(yhCshDjxh)){
//				throw new DiyServiceException("�����ڻ����ʲ���ʼ����ά���ֽ��ʲ���");
//			}
			srdjDom.setYhCshDjxh(domain.getYhCshDjxh());
			srdjDom.setRq(domain.getRq());
			srdjDom.setZcflDm(domain.getZcflDm());
			if("12".equals(domain.getZcflDm())){
				srdjDom.setZffsDm("2");
			}else if("11".equals(domain.getZcflDm())){
				srdjDom.setZffsDm("1");
			}else{
				srdjDom.setZffsDm("3");
			}		
			srdjDom.setJe(srdjDmain.getWsfJe());
			srdjDom.setJbrCzyDjxh(userDomain.getCzyDjxh());
			srdjDom.setDjJgbm(userDomain.getBmbm());
			srdjDom.setSsJgbm(userDomain.getGsbm());
			srdjDom.setBz("�����Ǽǲ�����");
			dao.saveDomain(srdjDom, userDomain);
			dao.callPHyglCwglSrdjHxcl(srdjDom.getSrDjxh(), "0");
		}

	}
	
	public void checkZfFs(CwYsyfSrdjDomain domain) throws Exception {
		dao.checkZfFs(domain);
		
	}
}
