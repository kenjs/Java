package com.cy.zyegl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyPcXydj;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zyegl.dao.HyPcXydjDao;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;
import com.cy.zyegl.domain.HyPcXydjDomain;

/**
 * The DAOIMP for 货运-派车-协议登记.
 * 
 * @author HJH
 */

@Repository
public class HyPcXydjDaoImp implements HyPcXydjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
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
		map.put("fhrMc", domain.getFhrMc());
		map.put("fhrDjxh", domain.getFhrDjxh());
		map.put("pcdh", domain.getPcdh());
		map.put("zt", domain.getZt());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyPcXydj", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcXydjAll", map);
		return dataList;
	}
	
	public HyPcXydjDomain initXydj(String pcDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", pcDjxh);
		return (HyPcXydjDomain)businessSqlMapClientTemplate.queryForObject("initXydj", map);
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		HyPcXydj bo = new HyPcXydj();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyPcXydjDomain dom = (HyPcXydjDomain) this.getDomainByKey(domain);

		BeanUtils.copyProperties(bo, domain);
		
		if(dom != null){
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(new java.util.Date());

			businessSqlMapClientTemplate.update("updateHyPcXydjByKey", bo);
		}else{
			bo.setSpbz("N");
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(new java.util.Date());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(new java.util.Date());

			businessSqlMapClientTemplate.insert("insertHyPcXydj", bo);
		}
	}
	
	public void callProXydjHxcl(String pcDjxh, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pcDjxh", pcDjxh);
		map.put("bmbm", userDomain.getBmbm());
		map.put("czyDjxh", userDomain.getCzyDjxh());
		map.put("retCode", 0);
		map.put("retMsg", "");
		businessSqlMapClientTemplate.queryForObject("callProXydjHxcl", map);
		if (map.get("retCode") != null && (Integer)map.get("retCode") != 0) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("retMsg")));
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", domain.getPcDjxh());

		domain = (HyPcXydjDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcXydjByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("pcDjxh", domain.getPcDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcXydjByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcXydjDomain domain = (HyPcXydjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcXydjDomain dom = (HyPcXydjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryHyPcXybdList(String pcDjxh)  throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("hyPcXybdList", map);
		return dataList;
	}
	public void updatePcXydjSlbgbz(HyPcHwxxXydjDomain hyPcHwxxXydjDomain) throws Exception {
		businessSqlMapClientTemplate.update("updatePcXydjSlbgbz", hyPcHwxxXydjDomain);
	}
	public int checkXydj(String pcDjxh)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcDjxh", pcDjxh);
		int count = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkXydj", map))).intValue();
		if(count<1){
			throw new DiyServiceException("请先协议登记！");
		}
		return count;
	}
}
