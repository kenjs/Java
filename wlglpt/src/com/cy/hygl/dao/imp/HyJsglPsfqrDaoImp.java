package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.XyPsfLr;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyJsglPsfqrDao;
import com.cy.hygl.domain.HyPcxxglDomain;


/**
 
 */
@Repository
public class HyJsglPsfqrDaoImp extends ExtendDaoImp implements HyJsglPsfqrDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("pcJgbm", user.getGsbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("pcdh", domain.getPcdh());
		
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJsPsfqrPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain,UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain=(HyPcxxglDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("pcJgbm", userDomain.getGsbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("pcdh", domain.getPcdh());
		
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryJsPsfqrPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyPsfLr bo = new XyPsfLr();
		bo.setSfqr("Y");
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPsfDjxh())){
			bo.setPsfDjxh(domain.getPsfDjxh());
			bo.setQrjg(domain.getQrJg());
			bo.setQrsm(domain.getBz());

			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			
			businessSqlMapClientTemplate.update("updatePsrQrXx", bo);
		}	
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {		
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("psfDjxh", domain.getPsfDjxh());
		return (BaseBusinessDomain) businessSqlMapClientTemplate.queryForObject("getPsfDomainByKey", map);
	}
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("psfDjxh", domain.getPsfDjxh());
		
		businessSqlMapClientTemplate.delete("psfOnBack", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		HyPcxxglDomain dom = (HyPcxxglDomain) getDomainByKey(domain);
		if(dom != null){
			BeanUtils.copyProperties(domain, dom);
		}
	}

	public String getPsfQrBz(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("psfDjxh", domain.getPsfDjxh());
		
		return (String) businessSqlMapClientTemplate.queryForObject("getPsfQrBz", map);
	}
	
	public void viewMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcxxglDomain domain = (HyPcxxglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("psfDjxh", domain.getPsfDjxh());
		
		HyPcxxglDomain dom = (HyPcxxglDomain)businessSqlMapClientTemplate.queryForObject("viewPsfQrMx", map);
		
		if(dom != null) {
			BeanUtils.copyProperties(domain, dom);
		}
		
	}
	
	public int checkPsfSfTh(String psfDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("psfDjxh", psfDjxh);
		
		return (Integer)businessSqlMapClientTemplate.queryForObject("checkPsfCanBack", map);
		
	}

}
