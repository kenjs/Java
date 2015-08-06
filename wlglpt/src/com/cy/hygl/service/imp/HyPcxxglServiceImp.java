package com.cy.hygl.service.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyQingDanDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.hygl.service.HyPcxxglService;


@Service
/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-3-4
 * @author yw
 */
public class HyPcxxglServiceImp extends BaseBusinessServiceImp implements HyPcxxglService {
	@Autowired
	private HyPcxxglDao dao;
	@Autowired
	private HyPcHwxxDao pcHwxxDao;
	@Autowired
	private WlglptCommonDao commonDao;
	
	//顺序号分类编码-派车单编号
	private static String DM_SXHFL_PCDH = "2";
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseBusinessDomain;
		if(userDomain.getCs_20052().equals("Y")){
			domain.setPcJgbm(userDomain.getBmbm());
		}
		
		
		initPccdSfsp(domain, userDomain);
	}
	
	public void initPccdSfsp(HyPcxxglDomain domain, UserDomain userDomain) throws Exception {
		String xtcs20201 = commonDao.getFunXtXtcs("20201", userDomain.getGsbm(), "2");
		domain.setXtcs20201(xtcs20201);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseBusinessDomain;
		//根据主键从数据库检索出相应信息
		if (StringUtils.isNotBlank(domain.getPcDjxh())) {
			HyPcxxglDomain dom = (HyPcxxglDomain)dao.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
				domain.setZrbmDmShow(dom.getZrbmDm());
				domain.setZrbmDjxhShow(dom.getZrbmDjxh());
			}
		}else {
			domain.setPcrq(new Date());
		}
		
		if (StringUtils.isBlank(domain.getClsxDm())) {
			//若无自营车辆，则页面默认选中社会车辆
			Integer zyclsl = dao.queryQyClxxCountBySsbmbm(userDomain.getBmbm(), "1");
			if (zyclsl <= 0) {
				domain.setClsxDm("2");
			}else {
				domain.setClsxDm("1");
			}
		}
		
		String xtcs20004 = commonDao.getFunXtXtcs("20004", userDomain.getGsbm(), "2");
		domain.setXtcs20004(xtcs20004);
		String xtcs20030 = commonDao.getFunXtXtcs("20030", userDomain.getGsbm(), "2");
		domain.setXtcs20030(xtcs20030);
		String xtcs20000 = commonDao.getFunXtXtcs("20000", userDomain.getGsbm(), "2");
		domain.setXtcs20000(xtcs20000);
		
		domain.setDw4Query(userDomain.gsbm);
		List<DmbGgDomain> pchwClfsList = dao.queryPchwClfsdmList(domain.getPcfsDm());
		domain.setPchwClfsList(pchwClfsList);
	}
	
	public void queryWfhxx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain) baseDomain;
		List<HyTydWfhxxDomain> wfhList = dao.queryWfhxx(domain);
		domain.setWfhList(wfhList);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList=dao.queryList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		if(domain.getSpbz()!=null&&domain.getSpbz().equals("qingdan")){
			List<HyQingDanDomain> dataList = dao.qingdan(domain);
			domain.setQdList(dataList);
			
		}	
		else{
			List<BaseBusinessDomain> dataList = dao.downloadList(domain);
			domain.setDataList(dataList);
		}
			
		
		
	}
	
	public void saveWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseDomain;
		//配送费累加
		String xtcs20030 = commonDao.getFunXtXtcs("20030", userDomain.getGsbm(), "2");
		double zPsf = 0.0;
		if("3".equals(domain.getPcfsDm())){
			if("Y".equals(xtcs20030)){
				String[]wfhXhs = domain.getWfhXhs().split(",");
				for(String wfhXh:wfhXhs){
					Double psf = dao.getQrPsf(wfhXh);
					if(psf == null){
						throw new DiyServiceException("必须配送费确认后，才可配送该货物！");
					}else{
						zPsf=zPsf+(double)psf;
					}
				}
				domain.setQrPsf(zPsf+"");
			}
		}
		dao.saveWfhxx4Pc(domain);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain)baseBusinessDomain;
		if (StringUtils.isBlank(domain.getPcdh())) {
			domain.setPcdh(commonDao.getFunXtSxh(DM_SXHFL_PCDH, userDomain.getGsbm()));
		}
		dao.saveDomain(domain, userDomain);
		
		savePcHwxx(domain, userDomain);
		dao.callPHyglDdglPcxxHxcl(domain);
		dao.deletePcHwxxTmpByLsXh(domain.getPchwLsxh());
	}
	
	private void savePcHwxx(HyPcxxglDomain domain, UserDomain user) throws Exception {
		pcHwxxDao.savePcHwxxToFormalTab(domain);
		
		/*List<String> wfhDjxhs = domain.getWfhDjxhs();
		Integer existWfh = dao.queryExistWfhxxCount(wfhDjxhs);
		if (existWfh < wfhDjxhs.size()) {
			throw new DiyServiceException("派车不成功，货物信息已发生变化！请删除货物后重新选择货物。");
		}
		HyPcHwxxDomain pcHwxxDomain;
		for (int i=0; i<wfhDjxhs.size(); i++) {
			pcHwxxDomain = new HyPcHwxxDomain();
			pcHwxxDomain.setPchwLsxh(domain.getPchwLsxh());
			pcHwxxDomain.setPcDjxh(domain.getPcDjxh());
			pcHwxxDomain.setWfhDjxh(wfhDjxhs.get(i));
			pcHwxxDomain.setJsSl(domain.getJssls().get(i));
			pcHwxxDomain.setHwSl(domain.getHwSls().get(i));
			pcHwxxDomain.setHwZl(domain.getHwZls().get(i));
			pcHwxxDomain.setHwTj(domain.getHwTjs().get(i));
			pcHwxxDomain.setBbh(domain.getBbhs().get(i));
			pcHwxxDomain.setZrbmDm(domain.getZrbmDm());
			pcHwxxDomain.setZrbmDjxh(domain.getZrbmDjxh());
			pcHwxxDomain.setZrbmDz(domain.getZrbmDz());
			pcHwxxDomain.setZrbmLxdh(domain.getZrbmLxdh());
			pcHwxxDomain.setZrbmLxr(domain.getZrbmLxr());
			pcHwxxDomain.setZrbmXzqhDm(domain.getZrbmXzqhDm());
			pcHwxxDomain.setPchwClfsDm("22");
			pcHwxxDomain.setYxbz("Y");
			
			pcHwxxDao.saveDomain(pcHwxxDomain, user);
		}*/
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		dao.callPHyglDdglPcxxHxcl(domain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modify(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		dao.callPHyglDdglPcxxDelete(domain, userDomain);
		dao.bakPcHwxx2Tmp(domain);
	}
	
	public void initWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		HyPcHwxxDomain pcHwDomain = domain.getPcHwxxDomain();
		
		String xtcs20000 = commonDao.getFunXtXtcs("20000", userDomain.getGsbm(), "2");
		domain.setXtcs20000(xtcs20000);
		
		HyPcHwxxDomain dom = null;
		if (StringUtils.isNotBlank(pcHwDomain.getPcDjxh())) {
			dom = (HyPcHwxxDomain) pcHwxxDao.getDomainByKey(pcHwDomain);
		}else if (StringUtils.isNotBlank(pcHwDomain.getPchwLsxh())) {
			dom = (HyPcHwxxDomain) pcHwxxDao.getPcHwDomainFromLsbByKey(pcHwDomain);
		}
		
		if (dom != null) {
			BeanUtils.copyProperties(pcHwDomain, dom);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		HyPcHwxxDomain pcHwDomain = domain.getPcHwxxDomain();
		
		if (StringUtils.isNotBlank(pcHwDomain.getPcDjxh())) {
			pcHwxxDao.updatePcHwxx(pcHwDomain);
		}else if (StringUtils.isNotBlank(pcHwDomain.getPchwLsxh())) {
			pcHwxxDao.updatePcHwxxTmp(pcHwDomain);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteWfhxx4Pc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		
		int tempNum = 0;
		int formalNum = 0;
		List<String> hwXh4PcDel = domain.getHwXh4PcDel();
		for (int i=0; i<hwXh4PcDel.size(); i++) {
			String tempBz = domain.getTempBz().get(i);
			if ("N".equals(tempBz)) {
				formalNum ++;
				dao.deleteHyPcHwxxByKey(domain.getPcDjxh(), hwXh4PcDel.get(i), domain.getBbhs().get(i));
			}else {
				tempNum++;
			}			
		}
		
		if (formalNum > 0) {
			//TODO 调用存储过程
			dao.callPHyglDdglPcxxHxcl(domain);
		}
		
		if (tempNum > 0) {
			dao.deleteWfhxxTmp4Pc(domain);
		}
	}
	
	public void queryPcHwxx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		if ("Y".equals(domain.getRefreshBbhFlag())) {
			dao.updatePchwxxBbh(domain);
		}
		
		List<HyTydWfhxxDomain> pcHwxxList = dao.queryPcHwxxByPcXh(domain);
		domain.setPcHwxxList(pcHwxxList);
	}
	
	public void queryPcxxSjsInitVal(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception { 
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		dao.queryPcxxSjsInitVal(domain);
	}

	
	public void qingdan(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		List<HyQingDanDomain> list=dao.qingdan(domain);
		for (HyQingDanDomain hyQingDanDomain : list) {
			if(hyQingDanDomain.getSjxm()!=null&&!hyQingDanDomain.getSjxm().equals("null"))
				domain.getQingDan().setSjxm(hyQingDanDomain.getSjxm());
			
			if(hyQingDanDomain.getPcdh()!=null&&!hyQingDanDomain.getPcdh().equals("null"))
			domain.getQingDan().setPcdh(hyQingDanDomain.getPcdh());
			
			if(hyQingDanDomain.getClhm()!=null&&!hyQingDanDomain.getClhm().equals("null"))
			domain.getQingDan().setClhm(hyQingDanDomain.getClhm());
			
			if(hyQingDanDomain.getPcrq()!=null&&!hyQingDanDomain.getPcrq().equals("null"))
			domain.getQingDan().setPcrq(hyQingDanDomain.getPcrq());
			
			if(hyQingDanDomain.getLxdh()!=null&&!hyQingDanDomain.getLxdh().equals("null"))
			domain.getQingDan().setLxdh(hyQingDanDomain.getLxdh());
			
			if(hyQingDanDomain.getSfd()!=null&&!hyQingDanDomain.getSfd().equals("null"))
			domain.getQingDan().setSfd(hyQingDanDomain.getSfd());
			
			if(hyQingDanDomain.getMdd()!=null&&!hyQingDanDomain.getMdd().equals("null"))
			domain.getQingDan().setMdd(hyQingDanDomain.getMdd());
			
			if(hyQingDanDomain.getPcBz()!=null&&!hyQingDanDomain.getPcBz().equals("null"))
				domain.getQingDan().setPcBz(hyQingDanDomain.getPcBz());
		}
		
		String mc=dao.queryZgs(userDomain);
		domain.getQingDan().setGsMc(SysEncodeUtil.ISO2GBK(mc));
		domain.setQdList(list);
	}
	
}
