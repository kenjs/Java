package com.cy.zygl.dao.imp;

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
import com.cy.common.bo.QyThShdz;
import com.cy.xtgl.domain.QyZzjgDomain;
import com.cy.zygl.dao.QyThShdzDao;
import com.cy.zygl.domain.QyThShdzDomain;

/**
 * The DAOIMP for 企业-提货收货地址维护.
 * 
 * @author HJH
 */

@Repository
public class QyThShdzDaoImp implements QyThShdzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyThShdzRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyThShdzPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyThShdzAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain) baseDomain;
		QyThShdz bo = new QyThShdz();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyThShdzDomain dom = (QyThShdzDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDz(domain.getDz());
			bo.setDh(domain.getDh());
			bo.setXzqhDm(domain.getXzqhDm());
			bo.setFzr(domain.getFzr());
			
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.update("updateQyThShdzByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyThShdz", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("whXh", domain.getWhXh());

		domain = (QyThShdzDomain)businessSqlMapClientTemplate.queryForObject("selectQyThShdzByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("whXh", domain.getWhXh());

		businessSqlMapClientTemplate.update("deleteQyThShdzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyThShdzDomain domain = (QyThShdzDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWhXh())){
			QyThShdzDomain dom = (QyThShdzDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}else{
			Map<String,String> map = new HashMap<String, String>();
			map.put("jgbm", domain.getSsJgbm());
			QyZzjgDomain zzJgDomain = (QyZzjgDomain) businessSqlMapClientTemplate.queryForObject("selectQyzzjgFgsMcByJgbm", map);
			domain.setSsJgmc(zzJgDomain.getMc());
		}

	}
}
