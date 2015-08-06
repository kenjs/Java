package com.cy.zyegl.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zyegl.dao.HyFydjshDao;
import com.cy.zyegl.domain.HyFydjshDomain;
import com.cy.zyegl.service.HyFydjshService;

@Service
/**
 * The SERVICEIMP for 货运-费用登记审核.
 * 
 * @author HJH
 */
public class HyFydjshServiceImp extends BaseBusinessServiceImp implements HyFydjshService {
	@Autowired
	private HyFydjshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyFydjshDomain domain = (HyFydjshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setShbz("N");
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyFydjshDomain domain = (HyFydjshDomain)baseBusinessDomain;
		//dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyFydjshDomain domain = (HyFydjshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain,userDomain);
		/*Map<String,List<HyFydjshDomain>> map=new HashMap<String, List<HyFydjshDomain>>();
		List<HyFydjshDomain> shList=null;
		for (BaseBusinessDomain base : dataList) {
			HyFydjshDomain dom=(HyFydjshDomain)base;
			if(map.containsKey(dom.getXh())){
				map.get(dom.getXh()).add(dom);
			}else{
				shList=new ArrayList<HyFydjshDomain>();
				shList.add(dom);
				map.put(dom.getXh(), shList);
			}
		}*/
		domain.setDataList(dataList);
		//domain.setMap(map);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyFydjshDomain domain = (HyFydjshDomain)baseBusinessDomain;
		//List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		//domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyFydjshDomain domain = (HyFydjshDomain)baseBusinessDomain;
		//dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyFydjshDomain domain=(HyFydjshDomain)baseBusinessDomain;
		//dao.deleteByKey(domain, userDomain);
	}
}
