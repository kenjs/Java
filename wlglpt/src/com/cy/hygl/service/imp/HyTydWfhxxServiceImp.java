package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.dao.HyTydWfhxxDao;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.hygl.service.HyTydWfhxxService;

@Service
/**
 * The SERVICEIMP for 货运-托运单-未发货(提货)信息.
 * 
 * @author HJH
 */
public class HyTydWfhxxServiceImp extends BaseBusinessServiceImp implements HyTydWfhxxService {
	@Autowired
	private HyTydWfhxxDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		if(userDomain.getCs_20052().equals("Y")){
			domain.setDjJgbm4Query(userDomain.getBmbm());
		}
		
		if ("Y".equals(domain.getPcOpenFlag())) {
			domain.setDw4Query(userDomain.gsbm);
			List<DmbGgDomain> pchwClfsList = dao.queryPchwClfsdmList(domain.getPcfsDm());
			domain.setPchwClfsList(pchwClfsList);
		}
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyTydWfhxxDomain domain=(HyTydWfhxxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	public void doUpdate(BaseBusinessDomain baseBusinessDomain,UserDomain user) throws Exception {
		HyTydWfhxxDomain domain=(HyTydWfhxxDomain)baseBusinessDomain;
		HyTydWfhxxDomain dom = (HyTydWfhxxDomain) dao.getDomainByKey(domain);
		String hwzt = "";
		String hwZt = "";
		if(StringUtils.isNotBlank(dom.getHwztDm())){
			if("11".equals(dom.getHwztDm())){
				hwzt = "21";
				hwZt = "未提改未发";
			}else if("21".equals(dom.getHwztDm())){
				hwzt = "11";
				hwZt = "未发改未提";
			}
			
		}
		dao.updateWfhhwZt(dom.getWfhDjxh(), hwzt);
		domain.setMswz("货物状态修改：若状态列为“未提”，则操作列值为“改运输”，当点击“改运输”时，" +
		"该行货物状态改为“未发”；若为“未发”，则为“改提货”当点击“改提货”时，该行货物状态改为“未提”。此次操作为："+hwZt);
		domain.setLx("HY_DD_WFHXX");
		domain.setMkmc("货运管理-调度管理-未发货信息");
		dao.saveXgRz(domain, user);
	}
}
