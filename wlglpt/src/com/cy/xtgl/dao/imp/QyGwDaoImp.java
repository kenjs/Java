package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyGw;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.xtgl.dao.QyGwDao;
import com.cy.xtgl.domain.QyGwDomain;

/**
 * The DAOIMP for 企业-岗位.
 * 
 * @author HaoY
 */

@Repository
public class QyGwDaoImp implements QyGwDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm().toString());
		}

		// 检索数据
		int totalRecords = (Integer)((businessSqlMapClientTemplate.queryForObject("selectAllQyGw", map)));
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyGwPage", map,start,pagSize);
		
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm().toString());
		}else{
			map.put("ssJgbm", domain.getDwDjxh().toString());
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyGwAll", map);
		return dataList;
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("gwDjxh", domain.getGwDjxh());
		return (QyGwDomain)businessSqlMapClientTemplate.queryForObject("selectQyGwByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getGwDjxh())){
			QyGwDomain dom = (QyGwDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
				throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("gwDjxh", domain.getGwDjxh());

		businessSqlMapClientTemplate.update("deleteQyGwByKey", map);
	}
	
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		QyGw bo = new QyGw();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyGwDomain dom = (QyGwDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setGwMc(domain.getGwMc());
			bo.setGwJc(domain.getGwJc());
			bo.setBzsm(domain.getBzsm());
			bo.setGwDm(null);
			
			businessSqlMapClientTemplate.update("updateQyGwByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setLybz("N");
			bo.setYxbz("Y");
			bo.setQybz("Y");
			bo.setGwDm(null);
			bo.setCjrCzyDjxh(new Long(user.getCzyDjxh()));
			bo.setCjrq(SysDateUtil.getCurrentDate());
			bo.setXgrCzyDjxh(new Long(user.getCzyDjxh()));
			bo.setXgrq(SysDateUtil.getCurrentDate());
			
			businessSqlMapClientTemplate.insert("insertQyGw", bo);
		}
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryXtGw(BaseBusinessDomain baseDomain)
			throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm().toString());
		return businessSqlMapClientTemplate.queryForList("selectXtGw", map);
	}

	public void saveXtGw(BaseBusinessDomain baseDomain,UserDomain user) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		QyGw bo = new QyGw();
		
		if(domain != null){
			BeanUtils.copyProperties(bo, domain);
			bo.setLybz("Y");
			bo.setYxbz("Y");
			bo.setQybz("Y");
			bo.setCjrCzyDjxh(new Long(user.getCzyDjxh()));
			bo.setCjrq(SysDateUtil.getCurrentDate());
			bo.setXgrCzyDjxh(new Long(user.getCzyDjxh()));
			bo.setXgrq(SysDateUtil.getCurrentDate());
			
			businessSqlMapClientTemplate.insert("insertQyGw", bo);
		}
	}

	public int checkGw(BaseBusinessDomain baseDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm().toString());
		map.put("gwDm", domain.getGwDm());
		return (Integer) businessSqlMapClientTemplate.queryForObject("checkGwSfCf",map);
	}

	public String getBmMc(String jgbm) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("jgbm", jgbm);
		return (String) businessSqlMapClientTemplate.queryForObject("getBmMc",map);
	}

	public String getYxbz(BaseBusinessDomain baseDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm().toString());
		map.put("gwDm", domain.getGwDm());
		String yxbz= (String) businessSqlMapClientTemplate.queryForObject("checkGwSfCz",map);
		return yxbz;
	}

	public void updateXtgw(BaseBusinessDomain baseDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm().toString());
		map.put("gwDm", domain.getGwDm());
		map.put("cjrCzyDjxh", domain.getCjrCzyDjxh());
		map.put("cjrq", domain.getCjrq());
		map.put("xgrCzyDjxh", domain.getXgrCzyDjxh());
		map.put("xgrq", domain.getXgrq());
		businessSqlMapClientTemplate.update("updateQyGwByGwDmAndSsJgbm", map);
	}

	public void saveEnable(BaseBusinessDomain baseDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("gwDjxh", domain.getGwDjxh());
		map.put("qybz", domain.getQybz());
		
		businessSqlMapClientTemplate.update("stopOrStartUse", map);
	}

	public void saveDisable(BaseBusinessDomain baseDomain) throws Exception {
		QyGwDomain domain = (QyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("gwDjxh", domain.getGwDjxh());
		map.put("qybz", domain.getQybz());
		
		businessSqlMapClientTemplate.update("stopOrStartUse", map);
	}

}
