package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyFbsJsjg;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.zygl.dao.QyFbsJsjgDao;
import com.cy.zygl.domain.QyFbsJsjgDomain;

/**
 * The DAOIMP for 企业-分包商-结算价格.
 * 
 * @author HJH
 */

@Repository
public class QyFbsJsjgDaoImp implements QyFbsJsjgDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
//		if(StringUtils.isNotBlank(domain.getSsJgbm())){
//			map.put("ssJgbm", domain.getSsJgbm());
//		}
		if(StringUtils.isNotBlank(domain.getFbsDjxh())){
			map.put("fbsDjxh", domain.getFbsDjxh());
		}
		if(StringUtils.isNotBlank(domain.getLxDjxh())){
			map.put("lxDjxh", domain.getLxDjxh());
		}
		if("2".equals(domain.getYxqZ())){
			map.put("xsls", "2");
		}
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyFbsJsjgRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsJsjgPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
//		if(StringUtils.isNotBlank(domain.getSsJgbm())){
//			map.put("ssJgbm", domain.getSsJgbm());
//		}
		if(StringUtils.isNotBlank(domain.getFbsDjxh())){
			map.put("fbsDjxh", domain.getFbsDjxh());
		}
		if(StringUtils.isNotBlank(domain.getLxDjxh())){
			map.put("lxDjxh", domain.getLxDjxh());
		}
		if("2".equals(domain.getYxqZ())){
			map.put("xsls", "2");
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsJsjgAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) baseDomain;
		QyFbsJsjg bo = new QyFbsJsjg();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyFbsJsjgDomain dom = (QyFbsJsjgDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			//bo.setSsJgbm(domain.getSsJgbm());
			bo.setFbsDjxh(domain.getFbsDjxh());
			//bo.setLxDjxh(domain.getLxDjxh());
			//bo.setJsJldwDm(domain.getJsJldwDm());
			//bo.setDj(domain.getDj());
			bo.setYxqQ(domain.getYxqQ());
			bo.setYxqZ(domain.getYxqZ());
			//bo.setBz(domain.getBz());

			bo.setYxbz("Y");
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());

			businessSqlMapClientTemplate.update("updateQyFbsJsjgYxq1ByKey", bo);
			businessSqlMapClientTemplate.update("updateQyFbsJsjgYxq2ByKey", bo);
			businessSqlMapClientTemplate.update("updateQyFbsJsjgYxq3ByKey", bo);
			//bo.setBz(domain.getBz());
			businessSqlMapClientTemplate.update("updateQyFbsJsjgByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			//bo.setDjJgbm(user.getBmbm());
			//bo.setDjrCzyDjxh(user.getCzyDjxh());
			//bo.setDjrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyFbsJsjgYxq1ByKey", bo);
			businessSqlMapClientTemplate.update("updateQyFbsJsjgYxq2ByKey", bo);
			businessSqlMapClientTemplate.update("updateQyFbsJsjgYxq3ByKey", bo);
			//bo.setBz(domain.getBz());
			businessSqlMapClientTemplate.insert("insertQyFbsJsjg", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jsjgDjxh", domain.getJsjgDjxh());

		domain = (QyFbsJsjgDomain)businessSqlMapClientTemplate.queryForObject("selectQyFbsJsjgByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("jsjgDjxh", domain.getJsjgDjxh());

		businessSqlMapClientTemplate.update("deleteQyFbsJsjgByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getJsjgDjxh())){
			QyFbsJsjgDomain dom = (QyFbsJsjgDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	public int queryQyFbsJsjgCount(QyFbsJsjgDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("lxDjxh", domain.getLxDjxh());
		map.put("yxqQ", domain.getYxqQ());
		map.put("yxqZ", domain.getYxqZ());
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyFbsJsjgLsCount", map))).intValue();
		return count;
	}
}
