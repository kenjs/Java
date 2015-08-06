package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.HyHwxxShfsbg;
import com.cy.hygl.dao.HyHwxxShfsbgDao;
import com.cy.hygl.domain.HyHwxxShfsbgDomain;

/**
 * The DAOIMP for 货运-货物信息-送货方式变更.
 * 
 * @author HJH
 */

@Repository
public class HyHwxxShfsbgDaoImp implements HyHwxxShfsbgDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyHwxxShfsbgRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwxxShfsbgPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwxxShfsbgAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		HyHwxxShfsbg bo = new HyHwxxShfsbg();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyHwxxShfsbgDomain dom = (HyHwxxShfsbgDomain) this.getDomainByKey(domain);
		//主表已有该订单货物 不重复插入
		if(dom == null){
			BeanUtils.copyProperties(bo, domain);
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertHyHwxxShfsbg", bo);
			
			domain.setShbgDjxh(bo.getShbgDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		//map.put("shbgDjxh", domain.getShbgDjxh());
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		
		domain = (HyHwxxShfsbgDomain)businessSqlMapClientTemplate.queryForObject("selectHyHwxxShfsbgByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		
		businessSqlMapClientTemplate.update("deleteHyHwxxShfsbgByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		HyHwxxShfsbgDomain dom = (HyHwxxShfsbgDomain) this.getDomainByKey(domain);
		if(dom!=null){
			BeanUtils.copyProperties(domain,dom);
		}
	}
	public int checkShfs(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", pcDjxh);

		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkShfsbg", map))).intValue();
		return totalRecords;
	}
	public int checkShfsbgZb(String ddDjxh,String xh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ddDjxh", ddDjxh);
		map.put("xh", xh);

		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkShfsbgZb", map))).intValue();
		return totalRecords;
	}
}
