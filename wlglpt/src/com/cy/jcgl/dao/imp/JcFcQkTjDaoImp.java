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
import com.cy.jcgl.dao.JcFcQkTjDao;
import com.cy.jcgl.dao.JcKhShMxDao;
import com.cy.jcgl.dao.JcPcxxglDao;
import com.cy.jcgl.dao.JcShQkTjDao;
import com.cy.jcgl.domain.JcFcQkMxDomain;
import com.cy.jcgl.domain.JcFcQkTjDomain;
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
public class JcFcQkTjDaoImp extends ExtendDaoImp implements JcFcQkTjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
		JcFcQkTjDomain domain=(JcFcQkTjDomain)baseDomain;
		//SysEncodeUtil.decodeURL(domain);
		//SysEncodeUtil.conGBK2ISO(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		
		map.put("jgbm", domain.getSjJgbm());
		map.put("rqq", domain.getRqq());
		map.put("rqz", domain.getRqz());
		map.put("dataList", dataList);
		
		//businessSqlMapClientTemplate.queryForObjectByCurr("queryJcFcQkTjList", "dataList", map);
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		JcPcxxglDomain domain=(JcPcxxglDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		if (StringUtils.isNotBlank(domain.getPcJgbm())) {
			map.put("pcJgbm", domain.getPcJgbm());
			map.put("dwbmBz", "B");
		}else {
			map.put("pcJgbm", domain.getSsJgbm());
			map.put("dwbmBz", "D");
		}
		map.put("pcrCzyDjxh", domain.getPcrCzyDjxh());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("cyrClhm", domain.getCyrClhm());
		map.put("cyrSjxm", domain.getCyrSjxm());
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("dingdan", domain.getDingdan());
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJcPcxxglPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
	}

	
}
