package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysNumberUtil;
import com.cy.hygl.dao.HyTydHwmxDao;
import com.cy.hygl.dao.HyTydglDao;
import com.cy.hygl.domain.HyTydHwmxDomain;
import com.cy.hygl.domain.HyTydglDomain;
import com.cy.hygl.service.HyTydglService;

@Service
/**
 * The SERVICEIMP for 货运-托运单管理.
 * 
 * @author HJH
 */
public class HyTydglServiceImp extends BaseBusinessServiceImp implements HyTydglService {
	@Autowired
	private HyTydglDao dao;
	@Autowired
	private HyTydHwmxDao hwmxDao; 
	@Autowired
	private WlglptCommonDao commonDao;
	
	//余款结算方式
	private static String DM_YKJSFS_YJ = "1";
	private static String DM_YKJSFS_HF = "2";
	private static String DM_YKJSFS_YJHF="3";
	
	//顺序号分类编码-订单编号
	private static String DM_SXHFL_DDBH = "1";

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setSsJgbm4Query(userDomain.getGsbm());
		if(userDomain.getCs_20052().equals("Y")){
			domain.setDjJgbm4Query(userDomain.getBmbm());
		}
		
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain)baseBusinessDomain;
		String xh = domain.getHwmxDomain().getXh();
		domain.setSsJgbm(userDomain.getGsbm());
		dao.initDomainMx(domain);
		domain.getHwmxDomain().setXh(xh);
		
		List<HyTydHwmxDomain> hwList = hwmxDao.queryHwmxByTydXh(domain.getDdDjxh(), domain.getHwmxDomain().getTempFlag());
		domain.setHwList(hwList);
		
		domain.getHwmxDomain().setZlTjProportion(userDomain.getZlTjProportion());
		
		if (domain.getDdDjxh() == null || domain.getDdDjxh().longValue() <= 0) {
			domain.setXdrq(SysDateUtil.getSqlDate());
			domain.getHwmxDomain().setTempFlag("Y");
			domain.setDjJgbm(userDomain.getBmbm());
			
			if("1000000021".equals(userDomain.getZgsbm())){
				domain.setShrXzqhDm("110000");
				domain.setShrXzqhMc("北京");
			}
		}else {
			domain.getHwmxDomain().setTempFlag("N");
		}
		
		String xtcs20002 = commonDao.getFunXtXtcs("20002", userDomain.getGsbm(), "2");
		domain.setXtcs20002(xtcs20002);
		String xtcs20014 = commonDao.getFunXtXtcs("20014", userDomain.getGsbm(), "2");
		domain.setXtcs20014(xtcs20014);
		String xtcs20000 = commonDao.getFunXtXtcs("20000", userDomain.getGsbm(), "2");
		domain.setXtcs20000(xtcs20000);
		
		domain.setSsJgbm(userDomain.getGsbm());
		domain.setDjJgbm(userDomain.getBmbm());
		domain.setDjJgmc(userDomain.getBmjc());
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain = (HyTydglDomain)baseBusinessDomain;
		// 若订单编号为空，则系统生成一个订单编号
		if (StringUtils.isBlank(domain.getDdbh())) {
			domain.setDdbh(commonDao.getFunXtSxh(DM_SXHFL_DDBH, userDomain.getGsbm()));
		}
		initTydDomainBeforeSave(domain);
		dao.saveDomain(domain, userDomain);
		saveHwxxToFormalTable(domain, userDomain);
		
		callPHyglDdglTydglHxcl(domain.getDdDjxh(), null);
		
		int xh = dao.selectXh(domain);
		domain.setXh(xh+"");
	}
	
	private void initTydDomainBeforeSave(HyTydglDomain domain) throws Exception {
		domain.setYjjsfsDm("1");
		domain.setPsbz("N");
	}
	
	/**
	 * 保存货物临时表数据到正式表,并删除临时表数据
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	private void saveHwxxToFormalTable(HyTydglDomain domain, UserDomain userDomain) throws Exception {
		//只有新增或有复制或模板的时候才需要处理临时表
		if ("N".equals(domain.getHwmxDomain().getTempFlag())) {
			return;
		}
		hwmxDao.saveHwxxToFormal(domain);
		hwmxDao.deleteHyTydHwxxTempByDdDjxh(domain.getDdDjxh());
	}
	
	public void callPHyglDdglTydglHxcl(Long ddDjxh, String xh) throws Exception {
		dao.callPHyglDdglTydglHxcl(ddDjxh, xh,"0");
	}
	
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		dao.callPHyglDdglTydglDelete(domain.getDdDjxh(), userDomain.getCzyDjxh(),"0");
	}
	
	public void refreshHwList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		List<HyTydHwmxDomain> hwList = hwmxDao.queryHwmxByTydXh(domain.getDdDjxh(), domain.getHwmxDomain().getTempFlag());
		domain.setHwList(hwList);
	}
	
	private void autoSetHwJsJldwdm(HyTydHwmxDomain hwmxDomain) throws Exception {
		String jsJldwDm = "";
		Double jsSl = 0.0;
		if ("01".equals(hwmxDomain.getJsJldwFlDm())) {
			jsJldwDm = hwmxDomain.getHwSlJldwDm();
			jsSl = hwmxDomain.getHwSl();
		}else if ("02".equals(hwmxDomain.getJsJldwFlDm())) {
			jsJldwDm = hwmxDomain.getHwZlJldwDm();
			jsSl = hwmxDomain.getHwZl();
		}else if ("03".equals(hwmxDomain.getJsJldwFlDm())) {
			jsJldwDm = hwmxDomain.getHwTjJldwDm();
			jsSl = hwmxDomain.getHwTj();
		}
		hwmxDomain.setJsJldwDm(jsJldwDm);
		hwmxDomain.setJsSl(jsSl);
	}
	
	public void initHwMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		HyTydHwmxDomain hwmxDomain = domain.getHwmxDomain();
		
		String tempFlag = hwmxDomain.getTempFlag();
		hwmxDao.initDomainMx(hwmxDomain);
		hwmxDomain.setTempFlag(tempFlag);
		
		//hwmxDomain.setQyHwBzJldwDm(userDomain.getHwBzJldwDm());
		hwmxDomain.setQyHwSlJldwDm(userDomain.getHwSlJldwDm());
		hwmxDomain.setQyHwZlJldwDm(userDomain.getHwZlJldwDm());
		hwmxDomain.setQyHwTjJldwDm(userDomain.getHwTjJldwDm());
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveHwMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)
			throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		HyTydHwmxDomain hwmxDomain = domain.getHwmxDomain();
		if(StringUtils.isBlank(hwmxDomain.getJsJldwFlDm())){
			hwmxDomain.setJsJldwFlDm("01");
		}
		autoSetHwJsJldwdm(hwmxDomain);
		hwmxDomain.setYjjsfsDm("1");
		hwmxDomain.setPsbz("N");
		hwmxDao.saveDomain(hwmxDomain, userDomain);	
		
		//如果是修改正式表，则需要调用存储过程做后续处理
		if ("N".equals(hwmxDomain.getTempFlag())) {
			callPHyglDdglTydglHxcl(Long.parseLong(hwmxDomain.getDdDjxh()), hwmxDomain.getXh());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteHwMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		if (domain.getDdDjxh() != null && domain.getDdDjxh().longValue() > 0 && domain.getHwXhs().size() > 0) {
			hwmxDao.deleteHwxxByXhs(domain.getDdDjxh().toString(), domain.getHwXhs(), domain.getHwmxDomain().getTempFlag());
			
			//如果是删除正式表，则需要调用存储过程做后续处理
			if ("N".equals(domain.getHwmxDomain().getTempFlag())) {
				//如果是批量删除，则对所有货物重新处理，否则只处理对应那条货物
				String xh = domain.getHwXhs().size() > 1 ? null : domain.getHwXhs().get(0);
				callPHyglDdglTydglHxcl(domain.getDdDjxh(), xh);
			}
		}
	}

	public void initCopyMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		String fhrMc = SysEncodeUtil.ISO2GBK(domain.getFhrMc());
		domain.setFhrMc(fhrMc);
	}

	public void queryCopy(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> list=dao.queryCopy(domain, userDomain);
		domain.setCopyList(list);
	}

	public void initTydFromCopy(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		Long ddDjxh = domain.getDdDjxh();
		String ddbh = domain.getDdbh();
		String tempFlag = domain.getHwmxDomain().getTempFlag();
		
		domain.setDdDjxh(Long.parseLong(domain.getDdDjxhCopy()));
		//将复制托单货物信息拷贝到当前托单，若当前托运单还未生产订单号，则重新生成一个订单号并返回
		ddDjxh = hwmxDao.saveCopyOrMbHwxxToTemp(domain.getDdDjxhCopy(), ddDjxh, "COPY");
		//根据复制托单初始化当前托单domain
		dao.initDomainMx(domain);		
		
		domain.setDdDjxh(ddDjxh);
		domain.setDdbh(ddbh);
		domain.getHwmxDomain().setTempFlag(tempFlag);
		domain.setXdrq(SysDateUtil.getSqlDate());
		refreshHwList(domain, userDomain);
	}
	
	public void initTydFromTemplate(BaseBusinessDomain domain, UserDomain userDomain)throws Exception {
		
	}
	
	public void queryYpchwNumByDdDjxh(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		
		Integer ypcHwNum = dao.queryYpchwNumByDdDjxh(domain.getDdDjxh());
		domain.setYpcHwNum(ypcHwNum);
	}
	public void checkDdbh(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		if(StringUtils.isNotBlank(domain.getDdbh())){
			List<HyTydglDomain> list = dao.checkDdbh(domain.getDdbh(), domain.getSsJgbm());
			if(list.size()>0){
				domain.setDdbh("-1");
			}
		}
	}
	
	public void printView(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydglDomain domain=(HyTydglDomain)baseBusinessDomain;
		HyTydHwmxDomain hwmxDom = domain.getHwmxDomain();
		String tempFlag = hwmxDom.getTempFlag();
		/*hwmxDom.setDdDjxh(domain.getDdDjxh()+"");
		hwmxDom.setTempFlag("N");
		dao.initDomainMx(domain);
		
		hwmxDom = (HyTydHwmxDomain)hwmxDao.getDomainByKey(hwmxDom);
		hwmxDom.setDxSrHj(SysNumberUtil.getResult(hwmxDom.getSrHj()+""));
		domain.setHwmxDomain(hwmxDom);*/
		
		hwmxDom = hwmxDao.queryPrintInfo(hwmxDom);
		if (hwmxDom.getSrHj() != null && Math.abs(hwmxDom.getSrHj().doubleValue()) > 0) {
			hwmxDom.setSrhjDx(SysNumberUtil.getResult(hwmxDom.getSrHj().toString()));
			String qybm = userDomain.qybm;
			if("TS".equals(qybm)){
				if (hwmxDom.getSrBjf() != null) {
					hwmxDom.setSrYsf(hwmxDom.getSrHj() - hwmxDom.getSrBjf());
				}else {
					hwmxDom.setSrYsf(hwmxDom.getSrHj());
				}
			}
		}
		if (hwmxDom.getFyDshk() != null && Math.abs(hwmxDom.getFyDshk().doubleValue()) > 0) {
			hwmxDom.setFyDshkDx(SysNumberUtil.getResult(hwmxDom.getFyDshk().toString()));
		}
		if (hwmxDom.getSrHjMz() != null && Math.abs(hwmxDom.getSrHjMz().doubleValue()) > 0) {
			hwmxDom.setSrHjMzDx(SysNumberUtil.getResult(hwmxDom.getSrHjMz().toString()));
		}
		//hwmxDom.setBz(dealBz4Print(hwmxDom.getBz()));
		dealLxdh(hwmxDom);
		hwmxDom.setTempFlag(tempFlag);
		domain.setHwmxDomain(hwmxDom);
	}
	
	private void dealLxdh(HyTydHwmxDomain hwmxDom) {
		if (StringUtils.isNotBlank(hwmxDom.getFhrLxdh())) {
			String fhrLxdh = hwmxDom.getFhrLxdh();
			String[] dhs = fhrLxdh.split("/");
			if (dhs.length >= 1) {
				hwmxDom.setFhrLxdh(dhs[0]);
			}
			if (dhs.length >= 2) {
				hwmxDom.setFhrYddh(dhs[1]);
			}
		}
		
		if (StringUtils.isNotBlank(hwmxDom.getShrLxdh())) {
			String shrLxdh = hwmxDom.getShrLxdh();
			String[] dhs = shrLxdh.split("/");
			if (dhs.length >= 1) {
				hwmxDom.setShrLxdh(dhs[0]);
			}
			if (dhs.length >= 2) {
				hwmxDom.setShrYddh(dhs[1]);
			}
		}
	}
	
	private String dealBz4Print(String bz) {
		if (StringUtils.isBlank(bz)) {
			return "";
		}
		StringBuffer bzSb = new StringBuffer();
		bzSb.append("　　　　");
		int i = 0;
		int j = 9;
		while (i < bz.length()) {
			if ((bz.length() - i) <= j) {
				bzSb.append(bz.substring(i, bz.length()));
			}else {
				bzSb.append(bz.substring(i, i+j)).append("<br/>");
			}
			i += j;
			j = 14;
		}
		
		return bzSb.toString();
	}
}
