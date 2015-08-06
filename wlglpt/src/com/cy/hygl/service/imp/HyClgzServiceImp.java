package com.cy.hygl.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyClgzDao;
import com.cy.hygl.domain.HyClgzDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.domain.JsKpDzqdDomain;
import com.cy.hygl.service.HyClgzService;
import com.cy.common.domain.UserDomain;


@Service
/**
 * The SERVICEIMP for 车辆跟踪
 * 
 * @author HCM
 */
public class HyClgzServiceImp extends BaseBusinessServiceImp implements HyClgzService {
	@Autowired
	private HyClgzDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseBusinessDomain;
	}

	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
		UserDomain userDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseBusinessDomain;
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			domain.setFhrMc(SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		}
		//部门
		if(StringUtils.isNotBlank(domain.getDjJgbm4Query())){
			domain.setDwbmBz4Query("B");
		}else{
			domain.setDwbmBz4Query("D");
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseBusinessDomain;
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			domain.setFhrMc(SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		}
		//部门
		if(StringUtils.isNotBlank(domain.getDjJgbm4Query())){
			domain.setDwbmBz4Query("B");
		}else{
			domain.setDwbmBz4Query("D");
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");			
		String dateStr = sdf.format(d);
		domain.setRq(dateStr);
	}
	
	public void getGzxx(BaseBusinessDomain baseBusinessDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseBusinessDomain;
		HyClgzDomain dom = dao.getClgzByKey(domain.getClgzDjxh());
		domain.setSm(dom.getSm());
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyClgzDomain domain=(HyClgzDomain)baseBusinessDomain;
		List<String> xhs=domain.getClgzDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		
			//删除结算-开票申请-对帐清单
	    for (String xh : xhs) {
				dao.deleteClgzByKey(xh);
			}
   }
}
