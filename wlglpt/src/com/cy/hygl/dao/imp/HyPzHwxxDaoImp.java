package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.HyPzHwxx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysSqlInUtil;
import com.cy.hygl.dao.HyPzHwxxDao;
import com.cy.hygl.domain.HyPzDomain;
import com.cy.hygl.domain.HyPzHwxxDomain;

/**
 * The DAOIMP for 货运-配载-货物信息.
 * 
 * @author HJH
 */

@Repository
public class HyPzHwxxDaoImp extends ExtendDaoImp implements HyPzHwxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPzHwxxDomain domain = (HyPzHwxxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPzHwxxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPzHwxxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPzHwxxDomain domain = (HyPzHwxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPzHwxxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPzHwxxDomain domain = (HyPzHwxxDomain) baseDomain;
		HyPzHwxx bo = new HyPzHwxx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyPzHwxxDomain dom = (HyPzHwxxDomain) this.getDomainByKey(domain);

		BeanUtils.copyProperties(bo, domain);
		
		if(dom != null){
			businessSqlMapClientTemplate.update("updateHyPzHwxxByKey", bo);
		}else{
			businessSqlMapClientTemplate.insert("insertHyPzHwxx", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPzHwxxDomain domain = (HyPzHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pzDjxh", domain.getPzDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());

		domain = (HyPzHwxxDomain)businessSqlMapClientTemplate.queryForObject("selectHyPzHwxxByKey", map);
		return domain;
	}

	public void deleteByKey(String pzDjxh, String wfhDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("pzDjxh", pzDjxh);
		map.put("wfhDjxh", wfhDjxh);

		businessSqlMapClientTemplate.update("deleteHyPzHwxxByKey", map);
	}
	
	public void deleteWfhxxTmp4Pz(HyPzDomain domain) throws Exception {
		List<String> wfhXhs = domain.getHwXh4PcDel();
		String[] xhs = new String[wfhXhs.size()];
		System.arraycopy(wfhXhs.toArray(), 0, xhs, 0, xhs.length);
		String xhsIn = SysSqlInUtil.getParameterArray(xhs, "TMP.WFH_DJXH ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xhsIn", xhsIn);
		map.put("lsxh", domain.getPchwLsxh());
		businessSqlMapClientTemplate.delete("deleteWfhxxTmp4Pz", map);
	}
	
	public void updateWfhxx4Pz(HyPzHwxxDomain domain) throws Exception {
		businessSqlMapClientTemplate.update("updateWfhxx4Pz", domain);
	}
	
	public void savePcHwxxTmp(HyPzHwxxDomain domain, UserDomain user) throws Exception {
		businessSqlMapClientTemplate.insert("savePcHwxxTmp", domain);
	}
	
	public void savePcHwxxTmpFromSavedPz(HyPzHwxxDomain pzHwxxDomain) throws Exception {
		businessSqlMapClientTemplate.insert("savePcHwxxTmpFromSavedPz", pzHwxxDomain);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPzHwxxDomain domain = (HyPzHwxxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPzDjxh())){
			HyPzHwxxDomain dom = (HyPzHwxxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
