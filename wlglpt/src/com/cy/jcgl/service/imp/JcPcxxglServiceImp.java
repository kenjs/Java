package com.cy.jcgl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.dao.HyPcxxglDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.jcgl.dao.JcPcxxglDao;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.service.JcPcxxglService;


@Service
/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-5-4
 * @author LYY
 */
public class JcPcxxglServiceImp extends BaseBusinessServiceImp implements JcPcxxglService {
	@Autowired
	private JcPcxxglDao dao;
	@Autowired
	private HyPcxxglDao pcDao;
	@Autowired
	private HyPcHwxxDao pcHwxxDao;
	@Autowired
	private WlglptCommonDao commonDao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcPcxxglDomain domain=(JcPcxxglDomain)baseBusinessDomain;
		if(userDomain.getCs_20052().equals("Y")){
			domain.setPcJgbm(userDomain.getBmbm());
		}
	
		
		
		initPccdSfsp(domain, userDomain);
	}
	
	public void initPccdSfsp(JcPcxxglDomain domain, UserDomain userDomain) throws Exception {
		String xtcs20201 = commonDao.getFunXtXtcs("20201", userDomain.getGsbm(), "2");
		domain.setXtcs20201(xtcs20201);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcPcxxglDomain domain = (JcPcxxglDomain)baseBusinessDomain;
		//根据主键从数据库检索出相应信息
		if (StringUtils.isNotBlank(domain.getPcDjxh())) {
			HyPcxxglDomain dom = new HyPcxxglDomain();
			dom.setPcDjxh(domain.getPcDjxh());
			dom = (HyPcxxglDomain)pcDao.getDomainByKey(dom);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}
		List<DmbGgDomain> pcfsList = commonDao.queryPcfsRadioList();
		domain.setPcfsList(pcfsList);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcPcxxglDomain domain=(JcPcxxglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList=dao.queryList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JcPcxxglDomain domain = (JcPcxxglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
}
