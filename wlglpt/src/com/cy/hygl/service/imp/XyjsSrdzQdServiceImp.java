package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.XyjsSrdzQdDao;
import com.cy.hygl.dao.XyjsSrdzQdMxDao;
import com.cy.hygl.domain.XyjsPcHwxxDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;
import com.cy.hygl.domain.XyjsSrdzQdMxDomain;
import com.cy.hygl.service.XyjsSrdzQdService;

@Service
/**
 * The SERVICEIMP for 下游结算-收入对帐-清单.
 * 
 * @author HJH
 */
public class XyjsSrdzQdServiceImp extends BaseBusinessServiceImp implements XyjsSrdzQdService {
	@Autowired
	private XyjsSrdzQdDao dao;
	@Autowired
	private XyjsSrdzQdMxDao qdmxDao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		if (StringUtils.isNotBlank(domain.getQdDjxh())) {
			domain.setTempFlag("N");
		}else {
			domain.setTempFlag("Y");
			domain.setQdDjxh(commonDao.selectSequence("SEQ_QD_DJXH"));
			// 根据下游序号检索下游名称
			domain.setXyMc(dao.queryXymcByXh(domain.getXyDjxh()));
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}
	
	public void queryQdMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseDomain;
		List<XyjsPcHwxxDomain> qdmxList = dao.queryQdMx(domain, userDomain);
		domain.setQdmxList(qdmxList);
	}
	
	public void queryJsxxMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseDomain;
		List<XyjsPcHwxxDomain> jsxxList = dao.queryJsxxMx(domain, userDomain);
		domain.setJsxxList(jsxxList);
	}
	
	public void saveJsmx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseDomain;
		List<String> ywDjxhs = domain.getYwDjxhs();
		XyjsSrdzQdMxDomain qdmxDomain = null;
		for (int i=0; i<ywDjxhs.size(); i++) {
			qdmxDomain = new XyjsSrdzQdMxDomain();
			qdmxDomain.setQdDjxh(domain.getQdDjxh());
			qdmxDomain.setYwDjxh(ywDjxhs.get(i));
			qdmxDomain.setYwMxXh(domain.getFylbDm());
			qdmxDomain.setYwLydm("1");
			
			if ("Y".equals(domain.getTempFlag())) {
				qdmxDao.saveDomainTemp(qdmxDomain, userDomain);
			}else {
				qdmxDao.saveDomain(qdmxDomain, userDomain);
			}
		}
	}
	
	public void deleteQdmx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseDomain;
		String ywMxXh = domain.getFylbDm();
		List<String> ywDjxhs = domain.getYwDjxhs();
		for (int i=0; i<ywDjxhs.size(); i++) {
			
			if ("Y".equals(domain.getTempFlag())) {
				qdmxDao.deleteDomainTemp(domain.getQdDjxh(), ywDjxhs.get(i), ywMxXh);
			}else {
				qdmxDao.deleteDomain(domain.getQdDjxh(), ywDjxhs.get(i), ywMxXh);
			}
		}
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		String tempFlag = domain.getTempFlag();
		dao.saveDomain(domain, userDomain);
		if ("Y".equals(tempFlag)) {
			saveQdmxTempToFormal(domain);
			deleteQdmxTempByQddjxh(domain.getQdDjxh());
		}
	}
	
	public void queryHjjeCalculate(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseDomain;
		XyjsSrdzQdDomain dom = dao.queryHjjeCalculate(domain, userDomain);
		if (dom != null) {
			domain.setHeJe(dom.getHeJe());
			domain.setDzJe(dom.getDzJe());
			domain.setDzcyJe(dom.getDzcyJe());
		}		
	}
	
	public void sendDzqd(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseDomain;
		dao.sendDzqd(domain, userDomain);
	}
	
	public void initconfirm(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		
	}
	
	public void querySydzqdConf(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.querySydzqdConf(domain,userDomain);
		domain.setDataList(dataList);
	}
	
	public void initSydzqdQrMx(BaseBusinessDomain baseBusinessDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}
	
	private void deleteQdmxTempByQddjxh(String qdDjxh) throws Exception {
		qdmxDao.deleteQdmxTempByQddjxh(qdDjxh);
	}
	
	private void saveQdmxTempToFormal(XyjsSrdzQdDomain domain) throws Exception {
		qdmxDao.saveQdmxTempToFormal(domain.getQdDjxh());
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain=(XyjsSrdzQdDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	public void saveSydzqdqr(BaseBusinessDomain baseBusinessDomain)
			throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		dao.updateSydzqdQr(domain);
		if("3".equals(domain.getZt())){
			dao.doCwInfo(domain);
		}
		
	}

	public void viewQrMx(BaseBusinessDomain baseBusinessDomain)
			throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		if (StringUtils.isNotBlank(domain.getQdDjxh())) {
			domain.setTempFlag("N");
		}else {
			domain.setTempFlag("Y");
			domain.setQdDjxh(commonDao.selectSequence("SEQ_QD_DJXH"));
		}
	}
	
}
