package com.cy.bggl.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.bggl.dao.BgXbqtDao;
import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.bggl.service.BgXbqtService;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * THE ACTION FOR °ì¹«Ç©µ½Ç©ÍË ÏÂ°àÇ©ÍË
 * 
 * @author ãÆÎ°
 * @date 2013.1.22
 */

@Service
public class BgXbqtServiceImp extends BaseBusinessServiceImp implements BgXbqtService {
	@Autowired
	private BgXbqtDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(cal.DATE, 1);
		domain.setOldDate(sim.format(cal.getTime()));
		domain.setNewDate(SysDateUtil.getCurrentDate());
		List<BgGzsjDomain> sjList = dao.getXbSj(userDomain.getBmbm());
		if (sjList.size() > 0) {
			BgGzsjDomain sjDomain = sjList.get(0);
			domain.setXbSj(sjDomain.getPmSbsjS() + ":" + sjDomain.getPmSbsjF()+":"+"00");
		} else {
			domain.setXbSj("17:30:00");
		}
		List<BgQdqtDomain> bgList=dao.getSjXdByCzy(userDomain.getCzyDjxh());
		BgQdqtDomain bgDomain=null;
		System.out.println(bgList.size());
		if(bgList.size()>0){
			bgDomain=bgList.get(0);
			String ary[]=bgDomain.getSjQdqtSj().split(" ");
			domain.setSjXbSj(ary[1]);
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		List<BaseBusinessDomain> list = dao.selectAll(domain, userDomain);
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
