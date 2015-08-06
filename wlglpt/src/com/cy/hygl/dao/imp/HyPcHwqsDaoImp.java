package com.cy.hygl.dao.imp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.Hwzt;
import com.cy.common.bo.HyPcHwqs;
import com.cy.common.bo.XyPsfLr;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHwqsDao;
import com.cy.hygl.domain.HyPcHwqsDomain;
import com.cy.hygl.domain.HyWlssdjDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The DAOIMP for 货运-派车-货物签收.
 * 
 * @author HJH
 */

@Repository
public class HyPcHwqsDaoImp extends ExtendDaoImp implements HyPcHwqsDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain, UserDomain userDomain)  throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("sfJs", domain.getSfJs());
		map.put("zrJgbm", userDomain.getGsbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcHwqsPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain,UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("sfJs", domain.getSfJs());
		map.put("zrJgbm", userDomain.getGsbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("pageNum", 1);
		map.put("pageSize", 999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcHwqsPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList1(BaseBusinessDomain baseDomain,UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		PageDomain page=domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		map.put("sfJs", domain.getSfJs());
		map.put("zrJgbm", userDomain.getGsbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("pageNum", 1);
		map.put("pageSize", 999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcHwqsPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}

	@Override
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		HyPcHwqs bo = new HyPcHwqs();
		
		BeanUtils.copyProperties(bo, domain);
		if(StringUtils.isNotBlank(domain.getHwqsDjxh())){
			bo.setXgrq(SysDateUtil.getSqlDate());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			businessSqlMapClientTemplate.update("updateHyPcHwqsByKey", bo);
		}else{
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());
			bo.setDjJgbm(user.getBmbm());
			bo.setNewDdDjxh("0");

			businessSqlMapClientTemplate.insert("insertHyPcHwqs", bo);
			domain.setHwqsDjxh(bo.getHwqsDjxh());
		}

	}
	

	public Long callPPcHwqsHxcl(String hwqsDjxh, String pcDjxh, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hwqsDjxh", hwqsDjxh);
		map.put("pcDjxh", pcDjxh);
		map.put("ssJgbm", userDomain.gsbm);
		map.put("bmbm", userDomain.bmbm);
		map.put("czyDjxh", userDomain.czyDjxh);
		map.put("newDdDjxh", 0);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPPcHwqsHxcl", map);
		
		if (map.get("rtnCode") != null && ((Integer)map.get("rtnCode")) != 0) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("rtnMess")));
		}
		
		return (Long) map.get("newDdDjxh");
	}

	public void callPcHwQsDel(String hwqsDjxh,String pcDjxh, String wlssDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hwqsDjxh",hwqsDjxh);
		map.put("pcDjxh", pcDjxh);
		map.put("wlssDjxh", wlssDjxh);
		map.put("newDdDjxh", 0);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPcHwQsDel", map);
		
		if (map.get("rtnCode") != null && ((Integer)map.get("rtnCode")) != 0) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("rtnMess")));
		}
	}
	
	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hwqsDjxh", domain.getHwqsDjxh());

		domain = (HyPcHwqsDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwqsByKey", map);
		return domain;
	}

	@Override
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hwqsDjxh", domain.getHwqsDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcHwqsByKey", map);
	}

	@Override
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;

		String wfhDjxh = domain.getWfhDjxh();
		String ddDjxh = domain.getDdDjxh();
		String xh = domain.getXh();
		String shfsDm = domain.getShfsDm();
		
		if(StringUtils.isNotBlank(domain.getHwqsDjxh())){
			HyPcHwqsDomain dom = (HyPcHwqsDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
		domain.setWfhDjxh(wfhDjxh);
		domain.setDdbh(ddDjxh);
		domain.setXh(xh);
		domain.setShfsDm(shfsDm);
	}
	
	public void initPsfMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseDomain;
		String pcDjxh = domain.getPcDjxh();
		String wfhDjxh = domain.getWfhDjxh();
		Map<String,String> map = new HashMap<String, String>();
		// 设置条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		HyPcHwqsDomain dom = (HyPcHwqsDomain)businessSqlMapClientTemplate.queryForObject("queryPsfInfo", map);
		if(dom!=null){
			BeanUtils.copyProperties(domain, dom);
			domain.setPcDjxh(pcDjxh);
			domain.setWfhDjxh(wfhDjxh);
		}
	}

	public void savePsf(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseBusinessDomain;
		XyPsfLr bo = new XyPsfLr();
		Map<String,String> map = new HashMap<String, String>();
		// 设置条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		HyPcHwqsDomain dom = (HyPcHwqsDomain)businessSqlMapClientTemplate.queryForObject("queryPsfInfo", map);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dom.getPsfy()!=null&&dom.getPsfy()!=0){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setPsfy(domain.getPsfy());
			bo.setBz(domain.getBz());
			bo.setXgrCzyDjxh(userDomain.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			bo.setCjrq(sdf.format(dom.getCjrq()));
			businessSqlMapClientTemplate.update("updateXyPsfLrByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
						
			bo.setCjrCzyDjxh(userDomain.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(userDomain.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			bo.setSfqr("N");
			
			businessSqlMapClientTemplate.insert("insertXyPsfLr", bo);
		}
		businessSqlMapClientTemplate.update("updateXyjsPcHwxXZcPsf", bo);
	}
	
	public HyPcHwqsDomain checkWlssSfDj(HyPcHwqsDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置条件
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		return (HyPcHwqsDomain) businessSqlMapClientTemplate.queryForObject("checkWlssSfDj", map);
	}
	
	public void insertHwZtInfo(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyPcHwqsDomain domain = (HyPcHwqsDomain) baseBusinessDomain;
		Hwzt bo = new Hwzt();
		bo.setWfhDjxh(domain.getWfhDjxh());
		bo.setDdDjxh(domain.getDdDjxh());
		bo.setXh(domain.getXh());
		
		bo.setThbz("N");
		bo.setYxbz("Y");
		bo.setCjrCzyDjxh(userDomain.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getCurrentDate().toString());
		bo.setXgrCzyDjxh(userDomain.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getCurrentDate().toString());
		
		businessSqlMapClientTemplate.insert("insertHyHwzt", bo);
	}
	
}
