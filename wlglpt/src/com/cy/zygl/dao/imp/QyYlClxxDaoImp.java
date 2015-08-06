package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyYlClxx;
import com.cy.common.bo.QyYlClxxSj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.dao.QyYlClxxDao;
import com.cy.zygl.domain.QyYlClxxDomain;

/**
 * The DAOIMP for 企业-运力-车辆信息.
 * 
 * @author Haoy
 */

@Repository
public class QyYlClxxDaoImp implements QyYlClxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("clsxDm", "1");
		if (StringUtils.isNotBlank(domain.getClhm())) {
			map.put("clhm", "%" + SysEncodeUtil.UTF82ISO(domain.getClhm()) + "%");
		}
		if (StringUtils.isNotBlank(domain.getCzXm())) {
			map.put("czXm", "%" + domain.getCzXm() + "%");
		}

		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer) (businessSqlMapClientTemplate
				.queryForObject("getQyYlClxxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyYlClxxPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("clsxDm", "1");
		if (StringUtils.isNotBlank(domain.getClhm())) {
			map.put("clhm", "%" + domain.getClhm() + "%");
		}
		if (StringUtils.isNotBlank(domain.getCzXm())) {
			map.put("czXm", "%" + domain.getCzXm() + "%");
		}

		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyYlClxxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseDomain;
		this.saveCl(domain, user);
		this.saveSj(domain);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("clDjxh", domain.getClDjxh());

		domain = (QyYlClxxDomain) businessSqlMapClientTemplate.queryForObject(
				"selectQyYlClxxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("clDjxh", domain.getClDjxh());

		businessSqlMapClientTemplate.update("deleteQyYlClxxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getClDjxh())) {
			QyYlClxxDomain dom = (QyYlClxxDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<QyYlClxxDomain> querySj(String clDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("clDjxh", clDjxh);
		return businessSqlMapClientTemplate.queryForList(
				"selectQyYlClxxSjByClDjxh", map);
	}

	public void deleteSj(String xh,String clDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("clDjxh", clDjxh);
		map.put("xh", xh);
		businessSqlMapClientTemplate.update("deleteQyYlClxxSjByKey", map);
	}

	public QyYlClxxDomain getSjxx(String clDjxh, String xh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("clDjxh", clDjxh);
		map.put("xh", xh);
		return (QyYlClxxDomain) businessSqlMapClientTemplate.queryForObject(
				"selectQyYlClxxSjByKey", map);
	}

	public void saveCl(QyYlClxxDomain domain, UserDomain user) throws Exception {
		QyYlClxx bo = new QyYlClxx();
		QyYlClxxDomain dom = (QyYlClxxDomain) this.getDomainByKey(domain);
		if (dom != null) {
			BeanUtils.copyProperties(bo, dom);

			bo.setCzXm(domain.getCzXm());
			bo.setXxgxfsDm(domain.getXxgxfsDm());
			bo.setCzZjlxDm(domain.getCzZjlxDm());
			bo.setCzZjhm(domain.getCzZjhm());
			bo.setCzLxdh(domain.getCzLxdh());
			bo.setClhm(domain.getClhm());
			bo.setThclbz(domain.getThclbz());
			bo.setYsclbz(domain.getYsclbz());
			bo.setPsclbz(domain.getPsclbz());
			bo.setBz(domain.getBz());
			bo.setSsJgbm(domain.getSsJgbm());
			
			bo.setClxhwhDjxh(domain.getClxhwhDjxh());
			bo.setGcbz(domain.getGcbz());
			
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateQyYlClxxByKey", bo);
		} else {
			BeanUtils.copyProperties(bo, domain);
			bo.setClsxDm("1");
			bo.setDjJgbm(user.getBmbm());
			bo.setYxbz("Y");

			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyYlClxx", bo);
		}
		domain.setClDjxh(bo.getClDjxh());
	}

	
	public int checkClhm(QyYlClxxDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		if (StringUtils.isNotBlank(domain.getClDjxh())) {
			map.put("clDjxh", domain.getClDjxh());
		}
		map.put("clhm", SysEncodeUtil.UTF82ISO(domain.getClhm()));
		return (Integer) businessSqlMapClientTemplate.queryForObject(
				"checkClhm", map);
	}

	public void saveSj(QyYlClxxDomain domain) throws Exception {
		QyYlClxxSj bo = new QyYlClxxSj();
		QyYlClxxDomain dom = (QyYlClxxDomain) this.getSjxx(domain
				.getClDjxh(), domain.getXh());
		if (dom != null) {
			BeanUtils.copyProperties(bo, dom);
			bo.setSjXm(domain.getSjXm());
			bo.setSjZjhm(domain.getSjZjhm());
			bo.setJszhm(domain.getJszhm());
			bo.setSjLxdh(domain.getSjLxdh());
			bo.setSjSjhm(domain.getSjSjhm());

			businessSqlMapClientTemplate
					.update("updateQyYlClxxSjByKey", bo);
		} else {
			int i = this.getXh(domain)+1;
			domain.setXh(i+"");
			BeanUtils.copyProperties(bo, domain);
			bo.setWhbz("H");
			bo.setYxbz("Y");

			businessSqlMapClientTemplate.insert("insertQyYlClxxSj2", bo);
		}
	}

	public int getXh(QyYlClxxDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("clDjxh", domain.getClDjxh());
		return (Integer) businessSqlMapClientTemplate.queryForObject("getMaxXh",map);
	}
	
	public String getCurrentXh(QyYlClxxDomain domain) throws Exception{		
		return (String) businessSqlMapClientTemplate.queryForObject("getCurrentXh");
	}
}
