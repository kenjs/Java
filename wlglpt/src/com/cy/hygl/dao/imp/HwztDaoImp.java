package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.Hwzt;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HwztDao;
import com.cy.hygl.domain.HwztDomain;

/**
 * The DAOIMP for 货物自提.
 * 
 * @author LYY
 */

@Repository
public class HwztDaoImp implements HwztDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<HwztDomain> queryList(HwztDomain domain, UserDomain userDomain)  throws Exception {
		// 分页相关
		PageDomain page = domain.getPage();
		int curPageNo = page.getCurPageNo();
		int pagSize = page.getPageSize();
		Map<String, Object> map = new HashMap<String, Object>();
		List<HwztDomain> dataList = null;
		// 设置查询条件
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		
		// 根据页面上的排序条件设置排序
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("thrqQ", domain.getThrqQ());
		map.put("thrqZ", domain.getThrqZ());
		map.put("shrMc", SysEncodeUtil.UTF82ISO(domain.getShrMc()));
		map.put("thbz", domain.getThbz());
		
		map.put("start", new Integer(curPageNo));
		map.put("pagSize", new Integer(pagSize));
		map.put("pageCount", new Integer(0));
		map.put("pageCurcount", new Integer(0));
		map.put("dataList", dataList);
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("hyglZyglHwztCx", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<HwztDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<HwztDomain> downloadList(HwztDomain domain) throws Exception {
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		List<HwztDomain> dataList = null;
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("thrqQ", domain.getThrqQ());
		map.put("thrqZ", domain.getThrqZ());
		map.put("shrMc", domain.getShrMc());
		map.put("thbz", domain.getThbz());
		
		map.put("start", page.getCurPageNo());
		map.put("pagSize", Integer.MAX_VALUE);
		map.put("pageCount", new Integer(0));
		map.put("pageCurcount", new Integer(0));
		map.put("dataList", dataList);
		
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("hyglZyglHwztCx", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<HwztDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("pageCurcount"));
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HwztDomain domain = (HwztDomain) baseDomain ;
		Hwzt bo = new Hwzt();
		bo.setJbrCzyDjxh(domain.getJbrCzyDjxh());
		bo.setThrq(domain.getThrq());
		bo.setBzsm(domain.getBzsm());
		bo.setXgrCzyDjxh(user.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getSqlDate().toString());
		bo.setThbz("Y");
		bo.setHwztDjxh(domain.getHwztDjxh());
		bo.setThrMc(domain.getThrMc());
		bo.setThrLxdh(domain.getThrLxdh());
		bo.setThrSfzh(domain.getThrSfzh());
		Map<String, String> map=new HashMap<String, String>();
		map.put("ZT_DJXH", domain.getHwztDjxh());
		int count=(Integer)businessSqlMapClientTemplate.queryForObject("checkHwZtDjByDjxh",map);
		if(count>0){
			domain.setTager("1");
		}
		else {
			businessSqlMapClientTemplate.update("updateThbzToY", bo);
			domain.setTager("2");
		}
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		return null ;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
//		HwztDomain domain = (HwztDomain) baseDomain;

	}

	public void register(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HwztDomain domain = (HwztDomain) baseDomain ;
		businessSqlMapClientTemplate.update("updateThbzToY", domain);
		//callHyglZyglHwztHxcl(domain.getHwztDjxh());
	}

	public void delete(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HwztDomain domain = (HwztDomain) baseDomain ;
		domain.setJbrCzyDjxh("");
		domain.setThrq("");
		domain.setBzsm("");
		domain.setXgrCzyDjxh(user.getCzyDjxh());
		domain.setXgrq(SysDateUtil.getSqlDate().toString());
		domain.setThbz("N");
		domain.setThrMc("");
		domain.setThrLxdh("");
		domain.setThrSfzh("");
		businessSqlMapClientTemplate.update("updateThbzToN", domain);
		callHyglZyglHwztHxcl(domain.getHwztDjxh());
	}
	
	public void callHyglZyglHwztHxcl(String hwztDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hwztDjxh", hwztDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callHyglZyglHwztHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
}
