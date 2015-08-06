package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyFbsYh;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.security.MD5;
import com.cy.zygl.dao.QyFbsYhDao;
import com.cy.zygl.domain.QyFbsYhDomain;

/**
 * The DAOIMP for 分包商用户管理.
 * 
 * @author HJH
 */

@Repository
public class QyFbsYhDaoImp implements QyFbsYhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		if (StringUtils.isNotBlank(domain.getSsJgbm())) {
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if (StringUtils.isNotBlank(domain.getFbsDjxh())) {
			map.put("fbsDjxh", domain.getFbsDjxh());
		}

		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer) (businessSqlMapClientTemplate.queryForObject("getQyFbsYhRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsYhPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		if (StringUtils.isNotBlank(domain.getSsJgbm())) {
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if (StringUtils.isNotBlank(domain.getFbsDjxh())) {
			map.put("fbsDjxh", domain.getFbsDjxh());
		}

		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsYhAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseDomain;
		domain.setZh(domain.getZh().toUpperCase());
		QyFbsYh bo = new QyFbsYh();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyFbsYhDomain dom = (QyFbsYhDomain) this.getDomainByKey(domain);
		
		MD5 md = new MD5();
		if (dom != null) {
			BeanUtils.copyProperties(bo, dom);

			bo.setMc(domain.getMc());
			bo.setZh(domain.getZh());
			bo.setPwd(md.getMD5ofStr(domain.getPwd()));
			bo.setDlyzfsDm(domain.getDlyzfsDm());

			bo.setYxbz("Y");
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyFbsYhByKey", bo);
		} else {
			BeanUtils.copyProperties(bo, domain);
			bo.setPwd(md.getMD5ofStr(domain.getPwd()));
			bo.setQybz("Y");
			bo.setYxbz("Y");
			bo.setQybm(user.getQybm());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyFbsYh", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseDomain;
		if (domain.getYhDjxh() == null || "".equals(domain.getYhDjxh()))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("yhDjxh", domain.getYhDjxh());

		domain = (QyFbsYhDomain) businessSqlMapClientTemplate.queryForObject("selectQyFbsYhByKey", map);
		if (domain != null)
			domain.setPwd1(domain.getPwd());
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("yhDjxh", domain.getYhDjxh());

		businessSqlMapClientTemplate.update("deleteQyFbsYhByKey", map);
	}

	public void saveEnable(QyFbsYhDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("yhDjxh", domain.getYhDjxh());

		businessSqlMapClientTemplate.update("startUseQyFbsYhByKey", map);
	}

	public void saveDisable(QyFbsYhDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("yhDjxh", domain.getYhDjxh());

		businessSqlMapClientTemplate.update("stopUseQyFbsYhByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsYhDomain domain = (QyFbsYhDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getYhDjxh())) {
			QyFbsYhDomain dom = (QyFbsYhDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}

	public int checkYhzh(QyFbsYhDomain domain, UserDomain user) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("qybm", user.getQybm());
		map.put("zh", domain.getZh().toUpperCase());
		map.put("yhDjxh", domain.getYhDjxh());
		int count = (Integer) businessSqlMapClientTemplate.queryForObject("checkQyFbsYhmcRepeat", map);
		return count;
	}
}
