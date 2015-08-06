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
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.QyFydjxm;
import com.cy.zygl.dao.QyFydjxmDao;
import com.cy.zygl.domain.QyFydjxmDomain;

/**
 * The DAOIMP for 企业-费用登记项目维护.
 * 
 * @author HJH
 */

@Repository
public class QyFydjxmDaoImp implements QyFydjxmDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		if(StringUtils.isNotBlank(domain.getFydjXmmc())){
			map.put("fydjXmmc", "%"+domain.getFydjXmmc()+"%");
		}
		if(StringUtils.isNotBlank(domain.getClsxDm())){
			map.put("clsxDm", domain.getClsxDm());
		}
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyFydjxmRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFydjxmPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		if(StringUtils.isNotBlank(domain.getFydjXmmc())){
			map.put("fydjXmmc", "%"+domain.getFydjXmmc()+"%");
		}
		if(StringUtils.isNotBlank(domain.getClsxDm())){
			map.put("clsxDm", domain.getClsxDm());
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFydjxmAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain) baseDomain;
		domain.setSsJgbm(user.getZgsbm());
		QyFydjxm bo = new QyFydjxm();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyFydjxmDomain dom = (QyFydjxmDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setFydjXmmc(domain.getFydjXmmc());
			bo.setClsxDm(domain.getClsxDm());
			bo.setSm(domain.getSm());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateQyFydjxmByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyFydjxm", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("whXh", domain.getWhXh());


		domain = (QyFydjxmDomain)businessSqlMapClientTemplate.queryForObject("selectQyFydjxmByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("whXh", domain.getWhXh());

		businessSqlMapClientTemplate.update("deleteQyFydjxmByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyFydjxmDomain domain = (QyFydjxmDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWhXh())){
			QyFydjxmDomain dom = (QyFydjxmDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
