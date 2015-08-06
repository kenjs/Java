package com.cy.bggl.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgSbqdDao;
import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.bggl.service.BgSbqdService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * THE ACTION FOR 办公签到签退 上班签到
 * 
 * @author 闫伟
 * @date 2013.1.22
 */

@Service
public class BgSbqdServiceImp extends BaseBusinessServiceImp implements BgSbqdService {
	@Autowired
	private BgSbqdDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(cal.DATE, 1);
		domain.setOldDate(sim.format(cal.getTime()));
		domain.setNewDate(SysDateUtil.getCurrentDate());
		List<BgGzsjDomain> sjList = dao.getSbSj(userDomain.getBmbm());
		if (sjList.size() > 0) {
			BgGzsjDomain sjDomain = sjList.get(0);
			domain.setSbSj(sjDomain.getAmSbsjS() + ":" + sjDomain.getAmSbsjF()+":"+"00");
		} else {
			domain.setSbSj("8:30:00");
		}
		List<BgQdqtDomain> bgList=dao.getSjQdByCzy(userDomain.getCzyDjxh());
		BgQdqtDomain bgDomain=null;
		if(bgList.size()>0){
			bgDomain=bgList.get(0);
			String ary[]=bgDomain.getSjQdqtSj().split(" ");
			domain.setSjSbSj(ary[1]);
		}
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.selectAll(domain, userDomain);
		System.out.println(list.size());
		domain.setDataList(list);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);

	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain, userDomain);
		domain.setDataList(dataList);
	}

}
