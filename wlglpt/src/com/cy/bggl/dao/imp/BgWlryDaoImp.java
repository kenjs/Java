package com.cy.bggl.dao.imp;

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
import com.cy.common.bo.BgWlry;
import com.cy.bggl.dao.BgWlryDao;
import com.cy.bggl.domain.BgWlryDomain;

/**
 * The DAOIMP for 办公-外联人员.
 * 
 * @author HaoY
 */

@Repository
public class BgWlryDaoImp implements BgWlryDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgWlryDomain domain = (BgWlryDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("wlryFlxh", domain.getWlryFlxh());
		map.put("xm", domain.getXm());
		map.put("dz", domain.getDz());
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgWlryRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgWlryPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("wlryFlxh", domain.getWlryFlxh());
		map.put("xm", domain.getXm());
		map.put("dz", domain.getDz());
		
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgWlryAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgWlryDomain domain = (BgWlryDomain) baseDomain;
		BgWlry bo = new BgWlry();
		//如果wlryDjxh为空则执行insert，否则执行update
		if(domain != null && !"".equals(domain.getWlryDjxh().trim())){
			//根据主键查询对象
			BgWlryDomain dom = (BgWlryDomain) this.getDomainByKey(domain);
			BeanUtils.copyProperties(bo, dom);
			
			bo.setWlryFlxh(domain.getWlryFlxh());
			bo.setXm(domain.getXm());
			bo.setDz(domain.getDz());
			bo.setDh(domain.getDh());
			bo.setCz(domain.getCz());
			bo.setSj(domain.getSj());
			bo.setWz(domain.getWz());
			bo.setYb(domain.getYb());
			bo.setDy(domain.getDy());
			bo.setBz(domain.getBz());

			businessSqlMapClientTemplate.update("updateBgWlryByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertBgWlry", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wlryDjxh", domain.getWlryDjxh());

		domain = (BgWlryDomain)businessSqlMapClientTemplate.queryForObject("selectBgWlryByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("wlryDjxh", domain.getWlryDjxh());

		businessSqlMapClientTemplate.update("deleteBgWlryByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgWlryDomain domain = (BgWlryDomain) baseDomain;
		String jgbm = domain.getJgbm();
		if(StringUtils.isNotBlank(domain.getWlryDjxh())){
			BgWlryDomain dom = (BgWlryDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setJgbm(jgbm);
			}
		}

	}
}
