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
import com.cy.common.bo.QyXlwh;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.dao.QyXlwhDao;
import com.cy.zygl.domain.QyXlwhDomain;


/**
 * The DAOIMP for 企业-线路维护.
 * 
 * @author HJH
 */

@Repository
public class QyXlwhDaoImp implements QyXlwhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getSsJgbm());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyXlwhRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyXlwhPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		map.put("jgbm", domain.getSjJgbm());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyXlwhAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		QyXlwh bo = new QyXlwh();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		domain.setSfdXzqhDm(domain.getFhrXzqhDm());
		domain.setMddXzqhDm(domain.getShrXzqhDm());
		QyXlwhDomain dom = (QyXlwhDomain) this.getDomainByKey(domain);
		
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setLcs(domain.getLcs());
			bo.setDdts(domain.getDdts());
			bo.setFhrXzqhDm(dom.getSfdXzqhDm());
			bo.setShrXzqhDm(dom.getMddXzqhDm());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateQyXlwhByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSfdXzqhDm(bo.getFhrXzqhDm());
			bo.setMddXzqhDm(bo.getShrXzqhDm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyXlwh", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("sfdXzqhDm", domain.getSfdXzqhDm());
		map.put("mddXzqhDm", domain.getMddXzqhDm());

		domain = (QyXlwhDomain)businessSqlMapClientTemplate.queryForObject("selectQyXlwhByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("sfdXzqhDm", domain.getSfdXzqhDm());
		map.put("mddXzqhDm", domain.getMddXzqhDm());

		businessSqlMapClientTemplate.update("deleteQyXlwhByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getSsJgbm())&&StringUtils.isNotBlank(domain.getSfdXzqhDm())&&StringUtils.isNotBlank(domain.getMddXzqhDm())){
			QyXlwhDomain dom = (QyXlwhDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setFhrXzqhDm(domain.getSfdXzqhDm());
				domain.setShrXzqhDm(domain.getMddXzqhDm());
			}
		}
		else {
			Map<String, String> map=new HashMap<String, String>();
			map.put("jgbm", domain.getSsJgbm());
			QyXlwhDomain xlDomain=(QyXlwhDomain)businessSqlMapClientTemplate.queryForObject("getXlWhSjMcByJgbm",map);
			domain.setSjMc(xlDomain.getSjMc());
		}

	}
}
