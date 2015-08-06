package com.cy.zyegl.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.zyegl.dao.HyXydjbdshDao;
import com.cy.zyegl.domain.HyXydjbdshDomain;
import com.cy.zyegl.service.HyXydjbdshService;
import com.cy.common.domain.UserDomain;

@Service
/**
 * The SERVICEIMP for 货运-费用登记审核.
 * 
 * @author HJH
 */
public class HyXydjbdshServiceImp extends BaseBusinessServiceImp implements HyXydjbdshService {
	@Autowired
	private HyXydjbdshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyXydjbdshDomain domain = (HyXydjbdshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setShbz("N");
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyXydjbdshDomain domain = (HyXydjbdshDomain)baseBusinessDomain;
		//dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyXydjbdshDomain domain = (HyXydjbdshDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain,userDomain);
		/*Map<String,List<HyXydjbdshDomain>> map=new HashMap<String, List<HyXydjbdshDomain>>();
		List<HyXydjbdshDomain> shList=null;
		for (BaseBusinessDomain base : dataList) {
			HyXydjbdshDomain dom=(HyXydjbdshDomain)base;
			if(map.containsKey(dom.getXh())){
				map.get(dom.getXh()).add(dom);
			}else{
				shList=new ArrayList<HyXydjbdshDomain>();
				shList.add(dom);
				map.put(dom.getXh(), shList);
			}
		}*/
		domain.setDataList(dataList);
		//domain.setMap(map);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyXydjbdshDomain domain = (HyXydjbdshDomain)baseBusinessDomain;
		//List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		//domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyXydjbdshDomain domain = (HyXydjbdshDomain)baseBusinessDomain;
		//dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyXydjbdshDomain domain=(HyXydjbdshDomain)baseBusinessDomain;
		//dao.deleteByKey(domain, userDomain);
	}
}
