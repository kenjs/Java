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
import com.cy.cwgl.dao.CwZfdjDao;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.cwgl.domain.CwZfdjDomain;
import com.cy.cwgl.service.CwZfdjService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The SERVICE for ����-֧���Ǽ�
 * 
 * @author LYY
 */
@Service
public class CwZfdjServiceImp extends BaseBusinessServiceImp implements CwZfdjService{
	@Autowired
	private CwZfdjDao dao;
	@Autowired
	private CwYsyfSrdjDao ysyfDao;
	@Autowired
	private CwHbzcCshDao cshDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain)baseBusinessDomain;
		domain.setYfjsfDmList(dao.getLbList());
		domain.setSsJgbm(userDomain.getGsbm());
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		
		domain.setJbrCzyDjxh(userDomain.getCzyDjxh());
		domain.setSsJgbm(userDomain.getGsbm());
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZfdjDomain domain = (CwZfdjDomain)baseBusinessDomain;
		if("12".equals(domain.getYfjsfDm())||"22".equals(domain.getYfjsfDm())){
			if(StringUtils.isNotBlank(domain.getYfjsfDjxh())){
				int srjl = ysyfDao.checkSrdj(domain.getYfjsfDm(), domain.getYfjsfDjxh());
				if(srjl>0){
					throw new DiyServiceException("��˾������˾����Ƿ�ѣ���������Ǽǣ���ȡ����ã�");
				}
			}
		}
		dao.saveDomain(domain, userDomain);
		dao.callPHyglCwglZfdjHxcl(domain.getZfDjxh(), "0");
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZfdjDomain domain=(CwZfdjDomain)baseBusinessDomain;
		List<String> xhs=domain.getZfDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
			//ɾ������-��Ʊ����-�����嵥
	    for (String xh : xhs) {
	    	dao.deleteCwZfdjByKey(xh);
			dao.callPHyglCwglZfdjHxcl(xh, "0");
		}
	}

	public void getMcList(BaseBusinessDomain baseBusinessDomain)throws Exception {
		CwZfdjDomain domain=(CwZfdjDomain)baseBusinessDomain;
		List<CwZfdjDomain> list;
		if(StringUtils.isNotBlank(domain.getYfjsfDm())){
		   list = dao.getMcList(domain.getSsJgbm(),domain.getYfjsfDm());
		}else{
		   list = dao.getMcList(domain.getSsJgbm(),"");
		}
		domain.setYfjsfMcList(list);
	}
	
	public void doGetYh(BaseBusinessDomain baseBusinessDomain)throws Exception {
		CwZfdjDomain domain=(CwZfdjDomain)baseBusinessDomain;
		
		dao.doGetYh(domain);
	}

	public void doCancle(BaseBusinessDomain baseBusinessDomain) throws Exception {
		CwZfdjDomain domain=(CwZfdjDomain)baseBusinessDomain;
		//dao.doCancle(domain);
		dao.callPHyglCwglZfdjHxcl(domain.getYsyfDjxh(), "1");
	}
	//@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void plDj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		CwZfdjDomain domain=(CwZfdjDomain)baseBusinessDomain;
		List<String> ysyfDjxhs=domain.getYsyfDjxhs();
		if(null==ysyfDjxhs|| ysyfDjxhs.isEmpty())
			return;
		
		//ɾ������-��Ʊ����-�����嵥
	    for (String xh : ysyfDjxhs) {
			
	    	CwYsyfSrdjDomain srdjDmain = (CwYsyfSrdjDomain) ysyfDao.getYsyfSrdj(xh);
	    	CwZfdjDomain zfdjDom = new CwZfdjDomain();
			
			zfdjDom.setYsyfDjxh(srdjDmain.getYsyfDjxh());
			zfdjDom.setYfjsfDm(srdjDmain.getYfjsfDm());
			zfdjDom.setYfjsfDjxh(srdjDmain.getYfjsfDjxh());
			if(StringUtils.isNotEmpty(srdjDmain.getYfjsfDjmc())){
				zfdjDom.setSkfmc(srdjDmain.getYfjsfDjmc());
			}else {
				zfdjDom.setSkfmc(srdjDmain.getYfjsfMc());
			}
//			String yhCshDjxh = cshDao.getYhCshDjxhWhenXj(userDomain.getGsbm());
//			if(StringUtils.isBlank(yhCshDjxh)){
//				throw new DiyServiceException("�����ڻ����ʲ���ʼ����ά���ֽ��ʲ���");
//			}
			zfdjDom.setYhCshDjxh(domain.getYhCshDjxh());
			zfdjDom.setRq(domain.getRq());
			zfdjDom.setZcflDm(domain.getZcflDm());
			if("12".equals(domain.getZcflDm())){
				zfdjDom.setZffsDm("2");
			}else if("11".equals(domain.getZcflDm())){
				zfdjDom.setZffsDm("1");
			}else{
				zfdjDom.setZffsDm("3");
			}
			
			zfdjDom.setJe(srdjDmain.getWsfJe());
			zfdjDom.setJbrCzyDjxh(userDomain.getCzyDjxh());
			zfdjDom.setDjJgbm(userDomain.getBmbm());
			zfdjDom.setSsJgbm(userDomain.getGsbm());
			zfdjDom.setBz("�����Ǽǲ�����");
			dao.saveDomain(zfdjDom, userDomain);
			dao.callPHyglCwglZfdjHxcl(zfdjDom.getZfDjxh(), "0");
		}

	}
}
