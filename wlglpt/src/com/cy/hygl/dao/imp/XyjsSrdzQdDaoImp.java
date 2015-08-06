package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwYsyf;
import com.cy.common.bo.XyjsSrdzQd;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.XyjsSrdzQdDao;
import com.cy.hygl.domain.XyjsPcHwxxDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;

/**
 * The DAOIMP for 下游结算-收入对帐-清单.
 * 
 * @author HJH
 */

@Repository
public class XyjsSrdzQdDaoImp implements XyjsSrdzQdDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		map.put("xyDjxh", domain.getXyDjxh());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		map.put("fylbDm", domain.getFylbDm());
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getXyjsSrdzQdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzQdPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) baseDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		map.put("xyDjxh", domain.getXyDjxh());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		map.put("fylbDm", domain.getFylbDm());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzQdAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) baseDomain;
		XyjsSrdzQd bo = new XyjsSrdzQd();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		XyjsSrdzQdDomain dom = (XyjsSrdzQdDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setXyDjxh(domain.getXyDjxh());
			bo.setQdmc(domain.getQdmc());
			bo.setHeJe(domain.getHeJe());
			bo.setDzJe(domain.getDzJe());
			bo.setDzcyJe(domain.getDzcyJe());

			businessSqlMapClientTemplate.update("updateXyjsSrdzQdByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setYfJe(0.0);
			bo.setWfJe(0.0);
			bo.setYsqKpje(0.0);
			bo.setWsqKpje(0.0);
			bo.setDjrCzyDjxh(user.czyDjxh);
			bo.setDjJgbm(user.bmbm);
			bo.setDjrq(SysDateUtil.getSqlDate());
			bo.setSsJgbm(user.gsbm);
			bo.setSpbz("N");
			bo.setZt("1");

			businessSqlMapClientTemplate.insert("insertXyjsSrdzQd", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("qdDjxh", domain.getQdDjxh());

		domain = (XyjsSrdzQdDomain)businessSqlMapClientTemplate.queryForObject("selectXyjsSrdzQdByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", domain.getQdDjxh());

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdDomain domain = (XyjsSrdzQdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getQdDjxh())){
			XyjsSrdzQdDomain dom = (XyjsSrdzQdDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<XyjsPcHwxxDomain> queryJsxxMx(XyjsSrdzQdDomain domain, UserDomain user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ssJgbm", user.gsbm);
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("xyDjxh", domain.getXyDjxh());
		map.put("tempFlag", domain.getTempFlag());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("xdrqQ", domain.getXdrqQ());
		map.put("xdrqZ", domain.getXdrqZ());
		map.put("pcdh", domain.getPcdh());
		map.put("ddbh", domain.getDdbh());
		map.put("hwSl", domain.getHwSl());
		map.put("hdbh", domain.getHdbh());
		map.put("fylbDm", domain.getFylbDm());
		
		List<XyjsPcHwxxDomain> jsxxList = businessSqlMapClientTemplate.queryForList("queryJsxxMx", map);
		
		return jsxxList;
	}
	
	@SuppressWarnings("unchecked")
	public List<XyjsPcHwxxDomain> queryQdMx(XyjsSrdzQdDomain domain, UserDomain user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("fylbDm", domain.getFylbDm());
		map.put("tempFlag", domain.getTempFlag());
		
		List<XyjsPcHwxxDomain> qdmxList = businessSqlMapClientTemplate.queryForList("queryQdMx", map);
		
		return qdmxList;
	}
	
	public String queryXymcByXh(String xyDjxh) throws Exception {
		if (StringUtils.isBlank(xyDjxh)) {
			return "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xyDjxh", xyDjxh);
		
		Object name = businessSqlMapClientTemplate.queryForObject("queryXymcByXh", map);
		if (name != null) {
			return SysEncodeUtil.ISO2GBK(name.toString());
		}
		return "";
	}
	
	public XyjsSrdzQdDomain queryHjjeCalculate(XyjsSrdzQdDomain domain, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("fylbDm", domain.getFylbDm());
		map.put("tempFlag", domain.getTempFlag());
		
		XyjsSrdzQdDomain dom = (XyjsSrdzQdDomain)businessSqlMapClientTemplate.queryForObject("queryHjjeCalculate", map);
		return dom;
	}
	
	public void sendDzqd(XyjsSrdzQdDomain domain, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qdDjxh", domain.getQdDjxh());
		businessSqlMapClientTemplate.update("sendDzqd", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> querySydzqdConf(XyjsSrdzQdDomain domain,UserDomain userDomain) throws Exception {
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		map.put("fylbDm", domain.getFylbDm());
		map.put("xyDjxh", userDomain.getGsbm());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getSydzqdQrRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectSydzqdQrPage", map,start,pagSize);
		
		return dataList;
	}
	
	public void updateSydzqdQr(XyjsSrdzQdDomain domain) throws Exception {
		XyjsSrdzQd bo = new XyjsSrdzQd();
		bo.setQdDjxh(domain.getQdDjxh());
		bo.setZt(domain.getZt());
		bo.setBz(domain.getBz());
		
		businessSqlMapClientTemplate.update("updateSyqddzqeByKey", bo);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadQdQr(XyjsSrdzQdDomain domain,UserDomain userDomain) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		map.put("fylbDm", domain.getFylbDm());
		map.put("xyDjxh", userDomain.getGsbm());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectSydzqdQrAll", map);
		return dataList;
	}
	
	public void doCwInfo(XyjsSrdzQdDomain domain) throws Exception {
		XyjsSrdzQdDomain dom = (XyjsSrdzQdDomain) this.getDomainByKey(domain);
		Map<String,String> map = new HashMap<String, String>();
		if(StringUtils.isNotBlank(dom.getQdDjxh())){
			map.put("qdDjxh", dom.getQdDjxh());
			map.put("qdmc", SysEncodeUtil.GBK2ISO("清单名称："));
			map.put("lb", SysEncodeUtil.GBK2ISO("，对账费用类别："));
			map.put("psf", SysEncodeUtil.GBK2ISO("配送费"));
			map.put("dfk", SysEncodeUtil.GBK2ISO("到付款"));
			map.put("dshk", SysEncodeUtil.GBK2ISO("代收货款"));
			map.put("ykp", SysEncodeUtil.GBK2ISO("，已申请开票金额："));
			map.put("wkp", SysEncodeUtil.GBK2ISO("，未申请开票金额："));
			
			businessSqlMapClientTemplate.insert("qdqrCwInfor",map);
		}
	}
}
