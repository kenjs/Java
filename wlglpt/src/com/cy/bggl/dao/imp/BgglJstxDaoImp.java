package com.cy.bggl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgglJstxDao;
import com.cy.bggl.domain.BgglJstxDomain;
import com.cy.common.bo.BgGzlx2;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;

/**
 * The DAOIMP for 办公-即时通讯.
 * 
 * @author anq
 */

@Repository
public class BgglJstxDaoImp extends ExtendDaoImp implements BgglJstxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("fsrCzyDjxh", domain.getFsrCzyDjxh());
		map.put("lxrDjxh", domain.getLxrDjxh());
		if (StringUtils.isNotBlank(domain.getJsrqQ())) {
			map.put("jsrqQ", domain.getJsrqQ());
		}

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgGzlx2RowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlx2Page", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain) baseDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("fsrCzyDjxh", domain.getFsrCzyDjxh());
		map.put("lxrDjxh", domain.getLxrDjxh());
		if (StringUtils.isNotBlank(domain.getJsrqQ())) {
			map.put("jsrqQ", domain.getJsrqQ());
		}
		
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlx2All", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain) baseDomain;
		BgGzlx2 bo = new BgGzlx2();


		if(domain.getJstxXh() != null && domain.getJstxXh().longValue() > 0){
			BgglJstxDomain dom = (BgglJstxDomain)this.getDomainByKey(domain);
			BeanUtils.copyProperties(bo, dom);
			
			bo.setFsrCzyDjxh(domain.getFsrCzyDjxh());
			bo.setFsrq(domain.getFsrq());
			bo.setCzyDjxh(domain.getCzyDjxh());
			bo.setXtyhflDm(domain.getXtyhflDm());
			bo.setJsbz(domain.getJsbz());
			bo.setJsrq(domain.getJsrq());

			
			businessSqlMapClientTemplate.update("updateBgGzlx2ByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setFsrCzyDjxh(user.getCzyDjxh());
			bo.setFsrq(new java.util.Date());
			bo.setJsbz("N");
			bo.setXtyhflDm(user.getXtyhflDm());
			
			businessSqlMapClientTemplate.insert("insertBgGzlx2", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键查询条件
		map.put("jstxXh", domain.getJstxXh());

		domain = (BgglJstxDomain)businessSqlMapClientTemplate.queryForObject("selectBgGzlx2ByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置主键条件
		map.put("jstxXh", domain.getJstxXh());

		businessSqlMapClientTemplate.update("deleteBgGzlx2ByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgglJstxDomain domain = (BgglJstxDomain) baseDomain;
		if(domain.getJstxXh() != null && domain.getJstxXh().longValue() > 0){
			BgglJstxDomain dom = (BgglJstxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
