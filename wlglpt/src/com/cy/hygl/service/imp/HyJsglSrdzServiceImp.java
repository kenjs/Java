package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.orm.ibatis.dialect.SysOracleDialect;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.hygl.dao.HyJsglSrdzDao;
import com.cy.hygl.domain.HyJsglSrdzDomain;
import com.cy.hygl.domain.JsDdHwxxDomain;
import com.cy.hygl.domain.JsSrdzDomain;
import com.cy.hygl.domain.JsSrdzDzcyDomain;
import com.cy.hygl.service.HyJsglSrdzService;

@Service
/**
 * The SERVICEIMP for 收入对账
 * 
 * @author HJH
 */
public class HyJsglSrdzServiceImp extends BaseBusinessServiceImp implements
		HyJsglSrdzService {
	@Autowired
	private HyJsglSrdzDao dao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		// 在此添加初始化相应代码
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		domain.setDefultRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
		domain.setDwDm(userDomain.gsbm);
		if (userDomain.getCs_20052().equals("Y")) {
			domain.setBmDm(userDomain.bmbm);
		}
		domain.setDzztDm("1");
		domain.setDzfsDm("1");// 默认按订单
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	public void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		if (SysToolsUtil.isNullOrEmpty(domain.getJsSrdzDomain().getDzDjxh())) {
			domain.setXgbz("N");
			// 初始化基本信息,根据jsDjxh
			this.initJbxxByJsDjxh(domain);
		} else {
			this.initJbxxByJsDjxh(domain);
			// 获取基本信息根据dzDjxh
			JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain.getJsSrdzDomain());
			if (null != dom) {
				domain.setXgbz("Y");
				// 判断发送按钮
				if (("0".equals(dom.getWsspztDm()) || "2".equals(dom.getWsspztDm())) && "Y".equals(dom.getSpbz())) {
					domain.setSendBz(true);
				}
				domain.setJsSrdzDomain(dom);
				// 检索当前维护的差异列表list（正式表取数）根据dzDjxh
				List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain.getJsSrdzDomain().getDzDjxh());
				if (null == dzcyList || dzcyList.isEmpty())
					return;

				domain.setJsSrdzcyList(dzcyList);
			}
		}
	}
	// 初始化基本信息,根据jsDjxh
	protected void initJbxxByJsDjxh(HyJsglSrdzDomain domain) throws Exception {
		HyJsglSrdzDomain jsdzDomain = null;
		
		//货物审批件的信息
		if(SysToolsUtil.isNullOrEmpty(domain.getJsSrdzDomain().getDzDjxh())){
			if(StringUtils.isNotBlank(domain.getJsSrdzDomain().getJsDjxh())){
				JsSrdzDomain dom2 = dao.getDzInfo(domain.getJsSrdzDomain().getJsDjxh());
				if(dom2 != null) {
					domain.setJsSrdzDomain(dom2);
					JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain.getJsSrdzDomain());
					if (null != dom) {					
						domain.setJsSrdzDomain(dom);
						// 检索当前维护的差异列表list（正式表取数）根据dzDjxh
						List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain.getJsSrdzDomain().getDzDjxh());
						if (null == dzcyList || dzcyList.isEmpty())
						return;
	
						domain.setJsSrdzcyList(dzcyList);
					}
				}
			}
		}
		
		jsdzDomain = (HyJsglSrdzDomain) dao.getJsSrdzByJsDjxh(domain.getJsSrdzDomain());
		if (jsdzDomain != null) {
			domain.getJsSrdzDomain().setSsJgbm(jsdzDomain.getDwDm());
			domain.setDzsr(jsdzDomain.getDzsr());
			domain.setDzyj(jsdzDomain.getDzyj());
			domain.setDzwj(jsdzDomain.getDzwj());
			domain.getJsSrdzDomain().setJsSr(jsdzDomain.getDzsr());// 默认结算收入合计为订单收入合计
			domain.getJsSrdzDomain().setJsYj(jsdzDomain.getDzyj());// 默认结算收入已结为订单收入已结
			domain.getJsSrdzDomain().setJsWj(jsdzDomain.getDzwj());// 默认结算收入未结为订单收入未结
			domain.setKhMc(jsdzDomain.getKhMc());
			domain.setDdbh(jsdzDomain.getDdbh());
			domain.setXdrq(jsdzDomain.getXdrq());
		}
		JsDdHwxxDomain dom1 = (JsDdHwxxDomain) dao.getJsDdHwxxDomain(domain.getJsSrdzDomain().getJsDjxh());
		if(dom1!=null){
			List<HyJsglSrdzDomain> list = dao.getWlssxl(dom1.getDdDjxh(), dom1.getXh());
			domain.setWlssXlList(list);
		}
	}

	protected void srDzCalculate(JsSrdzDomain jsdzDomain,
			List<JsSrdzDzcyDomain> dzcyList) {
		for (JsSrdzDzcyDomain temp : dzcyList) {
			if ("11".equals(temp.getDzcyClfsDm())) {// 调增收入,即结算收入=结算收入+当前差异金额
				jsdzDomain.setJsSr(jsdzDomain.getJsSr() + temp.getDzcyje());
				// 重新计算未结
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());
			}
			if ("12".equals(temp.getDzcyClfsDm())) {// 调减收入,即结算收入=结算收入-当前差异金额
				jsdzDomain.setJsSr(jsdzDomain.getJsSr() - temp.getDzcyje());
				// 重新计算未结
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());

			}
			if ("21".equals(temp.getDzcyClfsDm())) {// 调增已付,即已结=已结+当前差异金额
				jsdzDomain.setJsYj(jsdzDomain.getJsYj() + temp.getDzcyje());
				// 重新计算未结
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());
			}
			if ("22".equals(temp.getDzcyClfsDm())) {// 调减已付,即已结=已结-当前差异金额
				jsdzDomain.setJsYj(jsdzDomain.getJsYj() - temp.getDzcyje());
				// 重新计算未结
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());
			}
			if ("31".equals(temp.getDzcyClfsDm())) {// 做二次对账
				// jsdzDomain.setJsYj(jsdzDomain.getJsYj()-temp.getDzcyje());
				// 重新计算未结
			}
			if ("32".equals(temp.getDzcyClfsDm())) {// 做物流损失
				// jsdzDomain.setJsYj(jsdzDomain.getJsYj()-temp.getDzcyje());
				// 重新计算未结
			}
		}
		// 差异金额=结算未结-下单未结
		// jsdzDomain.setDzcyje(jsdzDomain.getJsWj()-jsdzDomain.getDzWj());
	}

	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		List<String> xhs = domain.getXhs();
		List<String> xhsAll = domain.getXhsAll();
		if (xhs.isEmpty())
			return;
		if (xhsAll.isEmpty())
			return;
		for(String xh:xhsAll){
			JsSrdzDzcyDomain temp = new JsSrdzDzcyDomain();
			temp.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
			temp.setXh(xh);
			JsSrdzDzcyDomain dzcyDomain = (JsSrdzDzcyDomain) dao.getDomainDzcyByKey(temp);
			if(StringUtils.isNotBlank(dzcyDomain.getXcJsDjxh())){
				if(dao.checkSrdz(dzcyDomain.getXcJsDjxh())){
					throw new DiyServiceException("二次对账已登记，不可修改！");
				}
			}
		}
		// 删除结算-收入对帐-对帐差异正式表
		if ("Y".equals(domain.getXgbz())) {
			for (String xh : xhs) {
				JsSrdzDzcyDomain temp = new JsSrdzDzcyDomain();
				temp.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
				temp.setXh(xh);
				JsSrdzDzcyDomain dzcyDomain = (JsSrdzDzcyDomain) dao.getDomainDzcyByKey(temp);
				if(StringUtils.isNotBlank(dzcyDomain.getXcJsDjxh())){
					if(dao.checkSrdz(dzcyDomain.getXcJsDjxh())){
						throw new DiyServiceException("二次对账已登记，不可修改！");
					}else{
						dao.deleteJsDdHwxxDomainByKey(dzcyDomain.getXcJsDjxh());
					}
				}
				dao.deleteDzcyByKey(domain.getJsSrdzDomain().getDzDjxh(), xh);
			}
			// 删除完时，判断对账差异标志，如果有对账差异记录着为Y，否则N
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain
					.getJsSrdzDomain().getDzDjxh());

			JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain
					.getJsSrdzDomain());
			if (null == dom)
				return;

			if (null == dzcyList || dzcyList.isEmpty()) {	
//				dom.setDzCybz("N");
//				dom.setDzcyje(Double.valueOf(0));
//				dom.setJsSr(domain.getDzsr());
//				dom.setJsYj(domain.getDzsr());
//				dom.setJsWj(0.0);
				
//				domain.setDzcyje("0");
			} else {
				double cyje=0;
				for(JsSrdzDzcyDomain e:dzcyList){
					if(e.getDzcyje()!=null){
						cyje=cyje+e.getDzcyje();
					}
				}
				dom.setDzCybz("Y");
				// dom.setDzcyje(cyje);
				// dom.setJsSr(dom.getDzSr());//默认结算收入为订单收入
				// dom.setJsYj(dom.getDzYj());//默认结算收入已结为订单收入已结
				// dom.setJsWj(dom.getDzWj());//默认结算收入未结为订单收入未结
				// this.srDzCalculate(dom, dzcyList);
				
				// domain.setDzcyje(cyje+"");
			}
			dao.saveDomain(dom, userDomain);
			// 调用后续处理
			domain.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
			
			dao.callHyglJsglSrdzDdhwHxcl(domain, userDomain);
			// dao.callHyglJsglSrdzDdHxcl(domain, userDomain);
		} else {// 删除结算-收入对帐-对帐差异-临时表根据主键
			for (String xh : xhs) {
				dao.deleteDzcyTempByKey(domain.getJsSrdzDomain().getDzDjxh(),xh);
			}
		}

	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		JsSrdzDomain dzDomain = domain.getJsSrdzDomain();
		if(dzDomain.getDzcyje()==null){
			dzDomain.setDzcyje(0.0);
		}
		if (dzDomain.getDzcyje() != 0.0) {
			dzDomain.setDzCybz("Y");
		}else {
			dzDomain.setDzCybz("N");
		}
		dao.saveDomain(dzDomain, userDomain);
		domain.setDzDjxh(dzDomain.getDzDjxh());
		
		// 保存对账差异明细表
		saveDzcyMx(domain, userDomain);
		
		// 调用货运管理-结算管理-收入对帐（订单货物）-后续处理（对帐保存后，再调本PROD）
		dao.callHyglJsglSrdzDdhwHxcl(domain, userDomain);
	}
	
	/**
	 *  保存对账差异明细表
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	private void saveDzcyMx(HyJsglSrdzDomain domain, UserDomain userDomain) throws Exception {
		//dao.deleteDzcyByDzDjxh(domain.getDzDjxh());
		
		List<Double> cyjes = domain.getCyjes();
		
		JsSrdzDzcyDomain dzcyDomain;
		for (int i=0; i<cyjes.size(); i++) {
			if (cyjes.get(i) == null || cyjes.get(i) == 0) {
				continue;
			}
			dzcyDomain = new JsSrdzDzcyDomain();
			if(StringUtils.isBlank(domain.getXhs().get(i))){
				Integer xh = dao.queryDzcyNextXh(domain.getDzDjxh());
				dzcyDomain.setXh(xh+"");
			}else{
				dzcyDomain.setXh(domain.getXhs().get(i));
			}
			dzcyDomain.setDzDjxh(domain.getDzDjxh());
			dzcyDomain.setDzcyje(cyjes.get(i));
			dzcyDomain.setDzcyyyDm(domain.getDzcyyyDm().get(i));
			dzcyDomain.setDzcyClfsDm(domain.getDzcyClfsDm().get(i));
			if("32".equals(dzcyDomain.getDzcyClfsDm())){
				dzcyDomain.setWlssDjxh(domain.getWlssDjxh().get(i));
				if(StringUtils.isNotBlank(domain.getXcJsDjxh().get(i))){
					//删二次对账产生的记录
					if(dao.checkSrdz(domain.getXcJsDjxh().get(i))){
						throw new DiyServiceException("二次对账已登记，不可修改！");
					}else{
						dao.deleteJsDdHwxxDomainByKey(domain.getXcJsDjxh().get(i));
					}
				}
				dzcyDomain.setXcJsDjxh("");
			}else{
				dzcyDomain.setWlssDjxh("");
				dzcyDomain.setXcJsDjxh(domain.getXcJsDjxh().get(i));
			}
			
			dzcyDomain.setBz(domain.getBzs().get(i));
			
			JsDdHwxxDomain dom1;
			if("31".equals(dzcyDomain.getDzcyClfsDm())){
				JsSrdzDomain dom = new JsSrdzDomain();
				dom.setDzDjxh(domain.getDzDjxh());
				dom = (JsSrdzDomain) dao.getDomainByKey(dom);
				
				if(dom!=null){
					if(StringUtils.isBlank(dzcyDomain.getXh())||StringUtils.isBlank(dzcyDomain.getXcJsDjxh())){
						//xh为空或xcjsDjxh为空，新增一个记录
						dom1 = (JsDdHwxxDomain) dao.getJsDdHwxxDomain(dom.getJsDjxh());
						dom1.setJsDjxh("");
					}else{
						//对原来已产生的 二次记录 修改
						if(dao.checkSrdz(dzcyDomain.getXcJsDjxh())){
							throw new DiyServiceException("二次对账已登记，不可修改！");
						}
						dom1 = (JsDdHwxxDomain) dao.getJsDdHwxxDomain(dzcyDomain.getXcJsDjxh());
						dom1.setJsDjxh(dzcyDomain.getXcJsDjxh());
					}
					if(dom1!=null){		
						dom1.setDzSr(dzcyDomain.getDzcyje());
						dom1.setDzYj(0.0);
						dom1.setDzWj(dzcyDomain.getDzcyje());
						dom1.setDcjsbz("Y");
						dom1.setQcDzDjxh(domain.getDzDjxh());
						dom1.setDzztDm("1");
						dom1.setDzDjxh("");
						
						dao.saveJsDdHwxxDomain(dom1, userDomain);
						dzcyDomain.setXcJsDjxh(dom1.getJsDjxh());
					}					
				}
			}
			dao.saveDzcyDomain(dzcyDomain, userDomain);
		}
	}

	/*@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		// 修改时，点击保存，只修改改结算-收入对帐-按订单即可
		if ("Y".equals(domain.getXgbz())) {
			// 判断对账差异标志，如果有对账差异记录着为Y，否则N
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain
					.getJsSrdzDomain().getDzDjxh());
			if (null == dzcyList || dzcyList.isEmpty())
				domain.getJsSrdzDomain().setDzCybz("N");
			else
				domain.getJsSrdzDomain().setDzCybz("Y");

			// 修改结算-收入对帐
			dao.saveDomain(domain.getJsSrdzDomain(), userDomain);
		} else {
			// 取临时表list根据dzDjxh
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyTempList(domain
					.getJsSrdzDomain().getDzDjxh());

			// 保存或修改结算-收入对帐-对帐差异（正式表）
			if (dzcyList != null || !dzcyList.isEmpty()) {
				domain.getJsSrdzDomain().setDzCybz("Y");// 判断对账差异标志，如果有对账差异记录着为Y，否则N
				for (JsSrdzDzcyDomain dom : dzcyList) {
					dao.saveDzcyDomain(dom, userDomain);
				}
			} else {
				domain.getJsSrdzDomain().setDzCybz("N");
			}

			// 保存结算-收入对帐
			dao.saveDomain(domain.getJsSrdzDomain(), userDomain);
			// 删除结算-收入对帐-对帐差异-临时表
			dao.deleteDzcyTempByDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
		}
		domain.setXgbz("Y");

		// 调用后续处理
		domain.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
		// dao.callHyglJsglSrdzDdHxcl(domain, userDomain);
	}*/

	@Override
	public void doMyQueryMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		dao.initDomainDzcyMx(domain.getDzcyDomain());
	}

	@Override
	public void doMySaveMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		// xgbz=Y时，直接操作正式表
		if ("Y".equals(domain.getXgbz())) {
			JsSrdzDzcyDomain dzcy = domain.getDzcyDomain();
			dzcy.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
			dao.saveDzcyDomain(dzcy, userDomain);

			// 添加时，判断对账差异标志，如果有对账差异记录着为Y，否则N
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain
					.getDzcyDomain().getDzDjxh());
			if (null == dzcyList || dzcyList.isEmpty())
				return;
			JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain
					.getJsSrdzDomain());
			if (null == dom)
				return;

			dom.setDzCybz("Y");
			// dom.setJsSr(dom.getDzSr());//默认结算收入为订单收入
			// dom.setJsYj(dom.getDzYj());//默认结算收入已结为订单收入已结
			// dom.setJsWj(dom.getDzWj());//默认结算收入未结为订单收入未结
			// this.srDzCalculate(dom, dzcyList);

			dao.saveDomain(dom, userDomain);
			// 调用后续处理
			domain.setDzDjxh(domain.getDzcyDomain().getDzDjxh());
			
			// dao.callHyglJsglSrdzDdHxcl(domain, userDomain);
		} else {// 操作临时表
			JsSrdzDzcyDomain dzcy = domain.getDzcyDomain();
			dzcy.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());

			dao.saveDzcyTempDomain(domain.getDzcyDomain(), userDomain);
		}

		this.initMx(baseBusinessDomain, userDomain);
	}
	
	//批量对账
	public void plDz(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		JsSrdzDomain dom = new JsSrdzDomain();
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		String pldzStr = domain.getPldzStr();
		if(pldzStr != null){
			String[] strs = pldzStr.split(",");
			for(int i=0;i < strs.length;i++) {
				String[] resStrs = strs[i].split("#");
				dom.setJsDjxh(resStrs[0]);
				dom.setDzDjxh(resStrs[1]);
				dom.setDzfsDm(resStrs[2]);
				
				domain = (HyJsglSrdzDomain) dao.getJsSrdzByJsDjxh(dom);
				if(domain != null) {
					dom.setJsSr(domain.getDzsr());
					dom.setSsJgbm(domain.getDwDm());
					dom.setJsYj(domain.getDzyj());
					dom.setJsWj(domain.getDzwj());
					dom.setDzcyje(0d);
					dom.setDzje(domain.getDzwj());
					dom.setDzCybz("N");
					
					dao.saveDomain(dom, userDomain);
					
					domain.setDzDjxh(dom.getDzDjxh());
					// 调用货运管理-结算管理-收入对帐（订单货物）-后续处理（对帐保存后，再调本PROD）
					dao.callHyglJsglSrdzDdhwHxcl(domain, userDomain);
				}
			}
		}
	}

}
