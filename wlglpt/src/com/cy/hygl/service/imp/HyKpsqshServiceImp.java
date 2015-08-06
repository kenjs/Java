package com.cy.hygl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.dao.HyKpsqshDao;
import com.cy.hygl.domain.HyKpsqshDomain;
import com.cy.hygl.service.HyKpsqshService;
import com.cy.common.domain.UserDomain;

@Service
/**
 * The SERVICEIMP for 货运-开票申请审核.
 * 
 * @author LYY
 */
public class HyKpsqshServiceImp extends BaseBusinessServiceImp implements HyKpsqshService {
	@Autowired
	private HyKpsqshDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyKpsqshDomain domain = (HyKpsqshDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setShbz("N");
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyKpsqshDomain domain = (HyKpsqshDomain)baseBusinessDomain;
		//dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyKpsqshDomain domain = (HyKpsqshDomain)baseBusinessDomain;
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
		HyKpsqshDomain domain = (HyKpsqshDomain)baseBusinessDomain;
		//List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		//domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyKpsqshDomain domain = (HyKpsqshDomain)baseBusinessDomain;
		//dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyKpsqshDomain domain=(HyKpsqshDomain)baseBusinessDomain;
		//dao.deleteByKey(domain, userDomain);
	}
}
