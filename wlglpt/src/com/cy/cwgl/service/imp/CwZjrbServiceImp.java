package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwHbzcLsjlDao;
import com.cy.cwgl.dao.CwQtsrDao;
import com.cy.cwgl.dao.CwYsyfSrdjDao;
import com.cy.cwgl.dao.CwZjrbDao;
import com.cy.cwgl.domain.CwHbzcLsjlDomain;
import com.cy.cwgl.domain.CwQtsrDomain;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.cwgl.domain.CwZjrbDomain;
import com.cy.cwgl.domain.CwZjrbSzmxDomain;
import com.cy.cwgl.service.CwZjrbService;

@Service
/**
 * The SERVICEIMP for 财务-资金日报.
 * 
 * @author HJH
 */
public class CwZjrbServiceImp extends BaseBusinessServiceImp implements CwZjrbService {
	@Autowired
	private CwZjrbDao dao;
	@Autowired
	private CwHbzcLsjlDao lsjlDao;
	@Autowired
	private CwQtsrDao qtsrDao;
	@Autowired
	private CwYsyfSrdjDao srdjDao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;		
		
		//取日流水记录所需参数
		CwHbzcLsjlDomain dom = new CwHbzcLsjlDomain();
		
		dom.setPage(domain.getPage());
		dom.setSsJgbm(domain.getSsJgbm());
		dom.setRq(domain.getRq());
		dom.setBz(domain.getBz());
		
		List<BaseBusinessDomain> dataList = lsjlDao.queryListForZjrb(dom);
		domain.setDataList(dataList);

	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain=(CwZjrbDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	public void cxTjMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		dao.cxTjMx(domain);
	}
	public void qtsrMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = qtsrDao.queryList(domain.getQtsrDomain());
		domain.getQtsrDomain().setDataList(dataList);
	}
	public void querySzMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		List<CwZjrbSzmxDomain> szmxList = dao.querySzmxList(domain);
		domain.setSzMxList(szmxList);
	}
	public void doMySaveQtsr(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		
		List<String> cwDjxhs=domain.getQtsrDomain().getCwDjxhs();
		List<String> xmmcs=domain.getQtsrDomain().getXmmcs();
		List<Double> jes = domain.getQtsrDomain().getJes();
		List<String> yhCshDjxhs=domain.getQtsrDomain().getYhCshDjxhs();
		List<String> zcflDms = domain.getQtsrDomain().getZcflDms();
		List<String> bzs = domain.getQtsrDomain().getBzs();
		List<String> fkfs = domain.getQtsrDomain().getFkfs();
		int i = 0;
		for (String cwDjxh : cwDjxhs) {
			CwQtsrDomain dom = new CwQtsrDomain();
			dom.setCwDjxh(cwDjxh);
			CwQtsrDomain temp = (CwQtsrDomain) qtsrDao.getDomainByKey(dom);
			if(temp!=null){
				if(temp.getXmmc().equals(SysEncodeUtil.UTF82GBK(xmmcs.get(i)))&&temp.getFkf().equals(SysEncodeUtil.UTF82GBK(fkfs.get(i)))
						&&temp.getZcflDm().equals(zcflDms.get(i))&&temp.getYhCshDjxh().equals(yhCshDjxhs.get(i))){
					if(((temp.getBz()==null||"".equals(temp.getBz()))&&"".equals(bzs.get(i)))||temp.getBz().equals(bzs.get(i))){
						if(temp.getJe().doubleValue()==jes.get(i).doubleValue()){
							i++;
							continue;
						}
					}
				}
			}
			this.saveQtsrOne(domain, userDomain, i);
			i++;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveQtsrOne(CwZjrbDomain domain,UserDomain userDomain,int i) throws Exception{
		String ssJgbm = domain.getQtsrDomain().getSsJgbm();
		String rq = domain.getQtsrDomain().getRq();
		String czyDjxh = userDomain.getCzyDjxh();
		String djJgbm = userDomain.getBmbm();
		
		
		List<String> xmmcs=domain.getQtsrDomain().getXmmcs();
		List<Double> jes = domain.getQtsrDomain().getJes();
		List<String> yhCshDjxhs=domain.getQtsrDomain().getYhCshDjxhs();
		List<String> zcflDms = domain.getQtsrDomain().getZcflDms();
		List<String> bzs = domain.getQtsrDomain().getBzs();
		List<String> fkfs = domain.getQtsrDomain().getFkfs();
		
		CwQtsrDomain dom = new CwQtsrDomain();
		
		dom.setSsJgbm(ssJgbm);
		dom.setDjJgbm(djJgbm);
		dom.setRq(rq);
		dom.setXmmc(SysEncodeUtil.GBK2ISO(xmmcs.get(i)));
		dom.setJe(jes.get(i));
		dom.setYhCshDjxh(yhCshDjxhs.get(i));
		dom.setZcflDm(zcflDms.get(i));
		dom.setBz(bzs.get(i));
		dom.setFkf(SysEncodeUtil.GBK2ISO(fkfs.get(i)));
		
		qtsrDao.saveDomain(dom, userDomain);
		
		qtsrDao.callPCwglQtsrHxcl(dom.getCwDjxh(), ssJgbm, czyDjxh);
		
		
		CwYsyfSrdjDomain srdjDmain = (CwYsyfSrdjDomain) srdjDao.getYsyfSrdjByYwDjxh(dom.getCwDjxh());
		CwYsyfSrdjDomain srdjDom = new CwYsyfSrdjDomain();
		
		srdjDom.setYsyfDjxh(srdjDmain.getYsyfDjxh());
		srdjDom.setYfjsfDm(srdjDmain.getYfjsfDm());
		srdjDom.setFkfmc(dom.getFkf());
		srdjDom.setRq(rq);
		srdjDom.setZcflDm(dom.getZcflDm());
		if("11".equals(dom.getZcflDm())){
			srdjDom.setZffsDm("1");
		}else if("12".equals(dom.getZcflDm())){
			srdjDom.setZffsDm("2");
		}else {
			srdjDom.setZffsDm("3");
		}
		srdjDom.setJe(dom.getJe());
		srdjDom.setYhCshDjxh(dom.getYhCshDjxh());
		srdjDom.setJbrCzyDjxh(czyDjxh);
		srdjDom.setDjJgbm(djJgbm);
		srdjDom.setSsJgbm(ssJgbm);
		
		srdjDao.saveDomain(srdjDom, userDomain);
		srdjDao.callPHyglCwglSrdjHxcl(srdjDom.getSrDjxh(), "0");
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteQtsr(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		CwZjrbDomain domain = (CwZjrbDomain)baseBusinessDomain;
		String ssJgbm = domain.getQtsrDomain().getSsJgbm();
		String czyDjxh = userDomain.getCzyDjxh();
		
		List<String> cwDjxhs=domain.getQtsrDomain().getCwDjxhs();
		if(null==cwDjxhs|| cwDjxhs.isEmpty())
			return;
		
		for (String cwDjxh : cwDjxhs) {
			if(StringUtils.isNotBlank(cwDjxh)){
				qtsrDao.deleteByKey(cwDjxh);
				
				qtsrDao.callPCwglQtsrHxcl(cwDjxh, ssJgbm, czyDjxh);
			}
		}
	}
}
