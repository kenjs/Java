package com.cy.jcgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.jcgl.dao.JcFcQkMxDao;
import com.cy.jcgl.dao.JcKhShMxDao;
import com.cy.jcgl.dao.JcPcxxglDao;
import com.cy.jcgl.dao.JcShQkTjDao;
import com.cy.jcgl.domain.JcFcQkMxDomain;
import com.cy.jcgl.domain.JcKhShMxDomain;
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcShQkTjDomain;


/**
 * The DAOIMP for 货运-派车信息管理
 * 
 * @date 2013.1.29
 * @author 闫伟
 */
@Repository
public class JcFcQkMxDaoImp extends ExtendDaoImp implements JcFcQkMxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception {
		JcFcQkMxDomain domain=(JcFcQkMxDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		
		PageDomain page = domain.getPage();
		List<BaseBusinessDomain> dataList = null;
		
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jgbm", user.getGsbm());
		map.put("hwmc", domain.getHwmc());
		map.put("sl", domain.getSl());
		map.put("pcdh", domain.getPcdh());
		map.put("tydh", domain.getTydh());
		map.put("hdh", domain.getHdh());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("fhrMc", domain.getFhrMc());
		map.put("sfd", domain.getSfd());
		map.put("mdd", domain.getMdd());
		map.put("clhm", domain.getClhm());
		map.put("rqq", domain.getRqq());
		map.put("rqz", domain.getRqz());
		map.put("pcfs", domain.getPcfs());
		
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount",0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryFcMxPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception {
		JcFcQkMxDomain domain=(JcFcQkMxDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("jgbm", user.getGsbm());
		map.put("hwmc", domain.getHwmc());
		map.put("sl", domain.getSl());
		map.put("pcdh", domain.getPcdh());
		map.put("tydh", domain.getTydh());
		map.put("hdh", domain.getHdh());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("fhrMc", domain.getFhrMc());
		map.put("sfd", domain.getSfd());
		map.put("mdd", domain.getMdd());
		map.put("clhm", domain.getClhm());
		map.put("rqq", domain.getRqq());
		map.put("rqz", domain.getRqz());
		map.put("pcfs", domain.getPcfs());
		
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryFcMxPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
	}

	
}
