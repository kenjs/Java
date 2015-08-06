package com.cy.dzgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QySpwsXmfl;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.dzgl.dao.QySpwsXmflDao;
import com.cy.dzgl.domain.QySpwsXmflDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 企业-审批文书-项目分类.
 * 
 * @date 2013.1.29
 * @author 闫伟
 */
@Repository
public class QySpwsXmflDaoImp extends ExtendDaoImp implements QySpwsXmflDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain busDomain, UserDomain user) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (domain.getWsDm() != null && !domain.getWsDm().equals("")) {
			map.put("wsDm", domain.getWsDm());
		}
		map.put("jgbm", user.getZgsbm());
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getQySpwsXmflRowCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQySpwsXmflPage", map, start, pagSize);
		return dataList;
	}

	@Override
	public void initDomainMx(BaseBusinessDomain busDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		if (StringUtils.isNotBlank(domain.getXmflDjxh())) {
			BaseBusinessDomain dom = this.getDomainByKey(domain);
			BeanUtils.copyProperties(domain, dom);
		}
	}

	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain busDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("XmflDjxh", domain.getXmflDjxh());
		return (QySpwsXmflDomain) businessSqlMapClientTemplate.queryForObject("selectQySpwsXmflByKey", map);
	}

	public int checkQySpwsflMc(BaseBusinessDomain busDomain, UserDomain user) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		QySpwsXmfl bo = new QySpwsXmfl();
		if (domain.getWsDm() != null && !domain.getWsDm().equals("")) {
			bo.setWsDm(domain.getWsDm());
		}
		if (domain.getXmflDjxh() != null && !domain.getXmflDjxh().equals("")) {
			String djxh = SysEncodeUtil.UTF82ISO(domain.getXmflDjxh());
			bo.setXmflDjxh(djxh);
		}
		if (domain.getXmflmc() != null && !domain.getXmflmc().equals("")) {
			String mc = SysEncodeUtil.UTF82ISO(domain.getXmflmc());
			bo.setXmflmc(mc);
		}
		bo.setJgbm(user.getGsbm());
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("checkQySpwsflMc", bo))).intValue();
		return count;
	}

	@Override
	public void saveDomain(BaseBusinessDomain busDomain, UserDomain user) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		QySpwsXmflDomain wsDomain = null;
		QySpwsXmfl bo = new QySpwsXmfl();
		if (StringUtils.isNotBlank(domain.getXmflDjxh())) {
			wsDomain = (QySpwsXmflDomain) this.getDomainByKey(domain);
		}
		if (wsDomain != null) {
			wsDomain.setWsDm(domain.getWsDm());
			wsDomain.setXmflmc(domain.getXmflmc());
			wsDomain.setXgrCzyDjxh(user.getCzyDjxh());
			wsDomain.setXgrq(SysDateUtil.getCurrentDate());
			BeanUtils.copyProperties(bo, wsDomain);
			businessSqlMapClientTemplate.update("updateQySpwsXmflByKey", bo);
		} else {
			domain.setYxbz("Y");
			domain.setJgbm(user.getZgsbm());
			domain.setCjrCzyDjxh(user.getCzyDjxh());
			domain.setCjrq(SysDateUtil.getCurrentDate());
			domain.setXgrCzyDjxh(user.getCzyDjxh());
			domain.setXgrq(SysDateUtil.getCurrentDate());
			BeanUtils.copyProperties(bo, domain);
			businessSqlMapClientTemplate.insert("insertQySpwsXmfl", bo);
		}
	}

	@Override
	public void deleteByKey(BaseBusinessDomain busDomain, UserDomain userDomain) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getXmflDjxh() != null && !domain.getXmflDjxh().equals("")) {
			map.put("xmflDjxh", domain.getXmflDjxh());
		}
		businessSqlMapClientTemplate.delete("deleteQySpwsXmflByKey", map);
	}

	public List<BaseBusinessDomain> downloadLIst(BaseBusinessDomain busDomain, UserDomain user) throws Exception {
		QySpwsXmflDomain domain = (QySpwsXmflDomain) busDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getWsDm() != null && !domain.getWsDm().equals("")) {
			map.put("wsDm", domain.getWsDm());
		}
		map.put("jgbm", user.getZgsbm());
		return businessSqlMapClientTemplate.queryForList("selectQySpwsXmflAll", map);
	}
}
