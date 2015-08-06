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

import com.cy.common.bo.HyFydj;
import com.cy.common.bo.HyFydjMx;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyFydjglDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
/**
 * The DAOImp for 费用登记管理.
 * 
 * @author hy
 */
@Repository
public class HyFydjglDaoImp implements HyFydjglDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;
		if (StringUtils.isNotBlank(domain.getDjJgbm4Query())) {
			map.put("pcJgbm4Query", domain.getDjJgbm4Query());
			map.put("dwbmBz4Query", "B");
		}else {
			map.put("pcJgbm4Query", domain.getPcJgbm4Query());
			map.put("dwbmBz4Query", "D");
		}
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		map.put("fhrMc4Query", domain.getFhrMc());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("clhm4Query", domain.getClhm4Query());
		map.put("pcdh4Query", domain.getPcdh4Query());
		map.put("zt", domain.getZt());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyZyglFydjPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}

	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		return null;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		HyFydjMx bo = new HyFydjMx();
		if(StringUtils.isNotBlank(domain.getSelOrUpd())){
			bo.setFyDjxh(domain.getFyDjxh());
			bo.setSrje(new Double(domain.getSrje()));
			bo.setZfje(new Double(domain.getZfje()));
			bo.setFydjxmWhXh(domain.getFydjxmWhXh());
			bo.setSkfCzyDjxh(domain.getSkfCzyDjxh());
			
			businessSqlMapClientTemplate.update("updateHyFydjMxByKey2", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setHwmxxh(domain.getXh());
			bo.setSpbz("N");
			bo.setYxbz("Y");
			
			bo.setSsJgbm(user.getGsbm());
			bo.setDjJgbm(user.getBmbm());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			
			businessSqlMapClientTemplate.insert("insertHyFydjCy", bo);
			domain.setFyDjxh(bo.getFyDjxh());
		}
		domain.setSelOrUpd(null);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("fyDjxh", domain.getFyDjxh());
		
		HyZyglFydjDomain dom = (HyZyglFydjDomain)businessSqlMapClientTemplate.queryForObject("queryHyFydjByKey", map);
		return dom;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("fyDjxh", domain.getFyDjxh());
		businessSqlMapClientTemplate.delete("deleteHyFydjMxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		String khDjxh = domain.getKhDjxh();
		String khmc = getKhMcByKey(khDjxh);
		HyZyglFydjDomain dom2 = getKhHwXxBy2Xh(domain.getPcDjxh(),domain.getWfhDjxh());
		
		HyZyglFydjDomain dom = null;
		if (StringUtils.isNotBlank(domain.getFyDjxh())) {
			dom = (HyZyglFydjDomain) queryPcxxByFyDjxh(domain.getFyDjxh());
		}else if(StringUtils.isNotBlank(domain.getPcDjxh())){
			dom = (HyZyglFydjDomain) getHyPcxxByPcDjxh(domain.getPcDjxh());
		}
		if(dom != null){
			BeanUtils.copyProperties(domain, dom);
			domain.setFhrDjxh(dom.getFkfCzyDjxh());
		}
		if(StringUtils.isNotBlank(khmc)){
			khmc = SysEncodeUtil.ISO2GBK(khmc);
		}
		domain.setKhmc(khmc);
		domain.setFkfCzyDjxh(khDjxh);
		domain.setKhDjxh(khDjxh);
		domain.setHwmc(dom2.getHwmc());
		domain.setWfhDjxh(dom2.getWfhDjxh());
	}

	public String getMaxXh(String fyDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fyDjxh", fyDjxh);
		return (String)businessSqlMapClientTemplate.queryForObject("getMaxHyFyDJMxXh", map);
	}

	@SuppressWarnings("unchecked")
	public List<HyZyglFydjDomain> queryHyFydjMxList(
			BaseBusinessDomain baseDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		List<HyZyglFydjDomain> fyDjMxList = null ;
		fyDjMxList = businessSqlMapClientTemplate.queryForList("selectHyFydjMxList", map);
		return fyDjMxList;
	}
	
	public void saveHyFyDj(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		
	}

	public void saveHyFyDjMx(BaseBusinessDomain baseDomain, UserDomain user)throws Exception {
		
	}
	
	@SuppressWarnings("unchecked")
	public List<HyZyglFydjDomain> getKhHwxx(HyZyglFydjDomain domain)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcDjxh", domain.getPcDjxh());
		if(StringUtils.isNotBlank(domain.getKhDjxh()) && !"null".equals(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		return businessSqlMapClientTemplate.queryForList("getKhHwXxBy2Xh", map);
	}

	public void deleteFyDj(String fyDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fyDjxh", fyDjxh);
		businessSqlMapClientTemplate.delete("deleteHyFydjMxByKey", map);
	}
	
	
	public void fydjHxcl(HyZyglFydjDomain domain, UserDomain user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fyDjxh", domain.getFyDjxh());
		map.put("xh", domain.getXh());
		map.put("ssJgbm", user.getBmbm());
		map.put("pcrCzyDjxh", user.getCzyDjxh());
		map.put("rtnErrCode", 0);
		map.put("rtnErrMess", "");
		businessSqlMapClientTemplate.queryForObject("fydjHxclProce", map);
		
		int rtnCode = (Integer) map.get("rtnErrCode");
		String errMesge = (String) map.get("rtnErrMess");
		if (rtnCode!= 0 && StringUtils.isNotBlank(errMesge)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(errMesge));
		}
		
	}

	public BaseBusinessDomain getHyPcxxByPcDjxh(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("pcDjxh", pcDjxh);
		HyZyglFydjDomain domain = (HyZyglFydjDomain) businessSqlMapClientTemplate.queryForObject("getHyPcXx", map);
		return domain;
	}
	
	public BaseBusinessDomain queryPcxxByFyDjxh(String fyDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("fyDjxh", fyDjxh);
		HyZyglFydjDomain domain = (HyZyglFydjDomain) businessSqlMapClientTemplate.queryForObject("queryPcxxByFyDjxh", map);
		return domain;
	}

	
	public int checkFydjMcByFydjxh(String djxh) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("djxh", djxh);
		 int count=(Integer)businessSqlMapClientTemplate.queryForObject("checkFydjMcByFydj",map);
		return count;
	}

	public HyPcxxglDomain getInsertInfor(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("pcDjxh", pcDjxh);
		return (HyPcxxglDomain) businessSqlMapClientTemplate.queryForObject("selectHyPcByKey",map);
	}

	public void insertCwYsYf(CwYsyfSrdjDomain domain) throws Exception {
		
	}

	public List<HyZyglFydjDomain> querySkfList(String ddDjxh, String xh,
			UserDomain user) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("ddDjxh", ddDjxh);
		map.put("xh", xh);
		map.put("ssJgbm", user.getZgsbm());
		return businessSqlMapClientTemplate.queryForList("getSkfList", map);
	}
	
	private String getKhMcByKey(String khDjxh)throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("khDjxh", khDjxh);
		
		return (String) businessSqlMapClientTemplate.queryForObject("getKhMcByKey", map);
	}
	
	private HyZyglFydjDomain getKhHwXxBy2Xh(String pcDjxh,String wfhDjxh) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("pcDjxh", pcDjxh);
		map.put("wfhDjxh", wfhDjxh);
		return (HyZyglFydjDomain) businessSqlMapClientTemplate.queryForObject("getKhHwXxBy2Xh", map);
	}

}
