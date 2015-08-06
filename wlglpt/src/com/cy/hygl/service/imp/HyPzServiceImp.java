package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.WlglptDropDownCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPzDao;
import com.cy.hygl.dao.HyPzHwxxDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyPzDomain;
import com.cy.hygl.domain.HyPzHwxxDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.hygl.domain.QyZrbmThShdzDomain;
import com.cy.hygl.service.HyPzService;

@Service
/**
 * The SERVICEIMP for 货运-配载.
 * 
 * @author HJH
 */
public class HyPzServiceImp extends BaseBusinessServiceImp implements HyPzService {
	@Autowired
	private HyPzDao dao;
	@Autowired
	private HyPzHwxxDao pzHwxxDao;
	@Autowired
	private WlglptDropDownCommonDao dropDownDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setSsJgbm(userDomain.gsbm);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseBusinessDomain;
		if (StringUtils.isNotBlank(domain.getPzDjxh())) {
			dao.initDomainMx(domain);
		}else {
			HyPzDomain dom = new HyPzDomain();
			BeanUtils.copyProperties(domain, dom);
		}
		domain.setDw4Query(userDomain.gsbm);
	}
	
	public void saveWfhxx4Pz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseDomain;
		dao.saveWfhxx4Pz(domain);
		updateWfhxx4Pz(baseDomain, userDomain);
	}
	
	/**
	 * @description 更新配载选择的货物对应的数量、重量、体积
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	private void updateWfhxx4Pz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseDomain;
		List<String> wfhDjxhs = domain.getWfhDjxhs();
		
		HyPzHwxxDomain pzHwxxDomain;
		for (int i=0; i<wfhDjxhs.size(); i++) {
			if (wfhDjxhs.size() == domain.getTempBz().size() && !"Y".equals(domain.getTempBz().get(i))) {
				continue;
			}
			pzHwxxDomain = new HyPzHwxxDomain();
			pzHwxxDomain.setPchwLsxh(domain.getPchwLsxh());
			pzHwxxDomain.setWfhDjxh(wfhDjxhs.get(i));
			pzHwxxDomain.setHwSl(domain.getHwSls().get(i));
			pzHwxxDomain.setHwZl(domain.getHwZls().get(i));
			pzHwxxDomain.setHwTj(domain.getHwTjs().get(i));
			
			pzHwxxDao.updateWfhxx4Pz(pzHwxxDomain);
		}
	}
	
	public void queryPzHwxx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain=(HyPzDomain)baseDomain;
		List<HyTydWfhxxDomain> pzHwxxList = dao.queryPzHwxxByPzXh(domain);
		domain.setPzHwxxList(pzHwxxList);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		savePzHwxx(domain, userDomain);
	}
	
	private void savePzHwxx(HyPzDomain domain, UserDomain user) throws Exception {
		List<String> wfhDjxhs = domain.getWfhDjxhs();
		
		HyPzHwxxDomain pzHwxxDomain;
		for (int i=0; i<wfhDjxhs.size(); i++) {
			pzHwxxDomain = new HyPzHwxxDomain();
			pzHwxxDomain.setPchwLsxh(domain.getPchwLsxh());
			pzHwxxDomain.setPzDjxh(domain.getPzDjxh());
			pzHwxxDomain.setWfhDjxh(wfhDjxhs.get(i));
			pzHwxxDomain.setHwSl(domain.getHwSls().get(i));
			pzHwxxDomain.setHwZl(domain.getHwZls().get(i));
			pzHwxxDomain.setHwTj(domain.getHwTjs().get(i));
			
			pzHwxxDao.saveDomain(pzHwxxDomain, user);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteWfhxx4Pz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain=(HyPzDomain)baseDomain;
		
		int tempNum = 0;
		int formalNum = 0;
		List<String> hwXh4PcDel = domain.getHwXh4PcDel();
		for (int i=0; i<hwXh4PcDel.size(); i++) {
			String tempBz = domain.getTempBz().get(i);
			if ("N".equals(tempBz)) {
				formalNum ++;
				pzHwxxDao.deleteByKey(domain.getPzDjxh(), hwXh4PcDel.get(i));
			}else {
				tempNum++;
			}			
		}
		
		if (tempNum > 0) {
			pzHwxxDao.deleteWfhxxTmp4Pz(domain);
		}
		
		updateWfhxx4Pz(baseDomain, userDomain);
	}
	
	/**
	 * 配载转派车
	 */
	public void initPcxxFromPz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain = (HyPzDomain) baseDomain;
		String pchwLsxh = dao.selectPchwLsxh();
		domain.setPchwLsxh(pchwLsxh);
		
		if ("Y".equals(domain.getListPc())) {
			savePcHwxxTmpFromSavedPz(domain, userDomain);
		}else {
			savePcHwxxTmpFromPz(domain, userDomain);
		}
	}
	
	private void savePcHwxxTmpFromSavedPz(HyPzDomain domain, UserDomain user) throws Exception {
		HyPzDomain dom = (HyPzDomain)dao.getDomainByKey(domain);
		
		QyZrbmThShdzDomain zrbmxxDomain = getZrbmxx(dom.getHzJgbm(), user);
		
		HyPzHwxxDomain pzHwxxDomain = new HyPzHwxxDomain();
		pzHwxxDomain.setPzDjxh(domain.getPzDjxh());
		if (zrbmxxDomain != null) {
			pzHwxxDomain.setPchwLsxh(domain.getPchwLsxh());
			pzHwxxDomain.setHzJgbm(dom.getHzJgbm());
			pzHwxxDomain.setZrbmDjxh(dom.getHzJgbm());
			pzHwxxDomain.setZrbmDm("2");
			pzHwxxDomain.setZrbmDz(zrbmxxDomain.getZrbmDz());
			pzHwxxDomain.setZrbmLxdh(zrbmxxDomain.getZrbmLxdh());
			pzHwxxDomain.setZrbmLxr(zrbmxxDomain.getZrbmLxr());
			pzHwxxDomain.setZrbmXzqhDm(zrbmxxDomain.getZrbmXzqhDm());
		}
		
		pzHwxxDao.savePcHwxxTmpFromSavedPz(pzHwxxDomain);
	}
	
	/**
	 * @description 配载转派车：将配载选择的货物以及数量及货站保存到临时表，用于派车
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	private void savePcHwxxTmpFromPz(HyPzDomain domain, UserDomain user) throws Exception {
		List<String> wfhDjxhs = domain.getWfhDjxhs();
		
		QyZrbmThShdzDomain zrbmxxDomain = getZrbmxx(domain.getHzJgbm(), user);
		
		HyPzHwxxDomain pzHwxxDomain;
		for (int i=0; i<wfhDjxhs.size(); i++) {
			pzHwxxDomain = new HyPzHwxxDomain();
			pzHwxxDomain.setPchwLsxh(domain.getPchwLsxh());
			pzHwxxDomain.setWfhDjxh(wfhDjxhs.get(i));
			pzHwxxDomain.setHzJgbm(domain.getHzJgbm());
			pzHwxxDomain.setHwSl(domain.getHwSls().get(i));
			pzHwxxDomain.setHwZl(domain.getHwZls().get(i));
			pzHwxxDomain.setHwTj(domain.getHwTjs().get(i));
			pzHwxxDomain.setZrbmDjxh(domain.getHzJgbm());
			pzHwxxDomain.setZrbmDm("2");
			pzHwxxDomain.setZrbmDz(zrbmxxDomain.getZrbmDz());
			pzHwxxDomain.setZrbmLxdh(zrbmxxDomain.getZrbmLxdh());
			pzHwxxDomain.setZrbmLxr(zrbmxxDomain.getZrbmLxr());
			pzHwxxDomain.setZrbmXzqhDm(zrbmxxDomain.getZrbmXzqhDm());
			
			pzHwxxDao.savePcHwxxTmp(pzHwxxDomain, user);
		}
	}
	
	/**
	 * @description 根据货站编码取对应的地址、电话等详细信息
	 * @param hzJgbm
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	private QyZrbmThShdzDomain getZrbmxx(String hzJgbm, UserDomain userDomain) throws Exception {
		if (StringUtils.isBlank(hzJgbm)) {
			return new QyZrbmThShdzDomain();
		}
		QyZrbmThShdzDomain qyZrbmThShdzDomain = new QyZrbmThShdzDomain();
		qyZrbmThShdzDomain.setZrbmDjxh(hzJgbm);
		qyZrbmThShdzDomain.setTableName("QY_ZZJG");
		List<QyZrbmThShdzDomain> dataList=dropDownDao.queryZrbmThShdz(qyZrbmThShdzDomain, userDomain);
		if (dataList != null && dataList.size() > 0) {
			return dataList.get(0);
		}
		return new QyZrbmThShdzDomain();
	}
	
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPzDomain domain=(HyPzDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	
	public void onQingdan(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		HyPzDomain domain=(HyPzDomain)baseDomain;
		List<BaseBusinessDomain> list=dao.onQingdan(domain);
		
		String mc=dao.queryZgsMc(userDomain);
		if(mc!=null&&!mc.equals("")){
			domain.getQingDan().setGsMc(SysEncodeUtil.ISO2GBK(mc));
		}
		domain.setPzList(list);
		
		
	}
	
	/**
	 * 配载预览
	 */
	public void viewPz(BaseBusinessDomain baseDomain) throws Exception {
		HyPzDomain domain=(HyPzDomain)baseDomain;
		
		String hzmc = dao.getHzmcByHzJgbm(domain.getHzJgbm());
		String clxh = dao.getClxhByClxhWhxh(domain.getClxhwhDjxh());
		
		if(StringUtils.isNotBlank(hzmc)){
			domain.setHzmc(SysEncodeUtil.ISO2GBK(hzmc));
		}
		domain.setClxh(clxh);
	}
	
	/**
	 * 检索配载货物
	 * @param baseDomain
	 * @throws Exception
	 */
	public void queryPzHw4View(BaseBusinessDomain baseDomain) throws Exception {
		HyPzDomain domain=(HyPzDomain)baseDomain;
		List<HyTydWfhxxDomain> pzHwxxList = dao.viewPz(domain);
		
		domain.setPzHwxxList(pzHwxxList);
	}
}
