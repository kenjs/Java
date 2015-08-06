package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyKhShdz;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.dao.QyKhShdzDao;
import com.cy.zygl.domain.QyKhShdzDomain;

/**
 * The DAOIMP for 企业-客户-收货地址.
 * 
 * @author ylp
 * @since 2013-1-16 上午10:40:00
 * @version
 */

@Repository
public class QyKhShdzDaoImp implements QyKhShdzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyKhShdzDomain domain = (QyKhShdzDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getKhDjxh())) {
			String kh = domain.getKhDjxh().trim();
			map.put("khDjxh", kh);
		}
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer) (businessSqlMapClientTemplate
				.queryForObject("getQyKhShdzRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyKhShdzPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyKhShdzDomain domain = (QyKhShdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getKhDjxh())) {
			String kh = domain.getKhDjxh().trim();
			map.put("khDjxh", kh);
		}
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());

		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyKhShdzAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		QyKhShdzDomain domain = (QyKhShdzDomain) baseDomain;
		QyKhShdz bo = new QyKhShdz();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyKhShdzDomain dom = (QyKhShdzDomain) this.getDomainByKey(domain);
		String s = SysEncodeUtil.ISO2GBK(domain.getXxdz());
		if (dom != null) {
			BeanUtils.copyProperties(bo, dom);

			bo.setXzqhDm(domain.getXzqhDm());
			bo.setXxdz(s);
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			bo.setYb(domain.getYb());
			bo.setLxr(SysEncodeUtil.UTF82GBK(domain.getLxr()));
			bo.setLxrYddh(domain.getLxrYddh());
			bo.setLxrGddh(domain.getLxrGddh());
			bo.setQtlxdh(domain.getQtlxdh());
			bo.setShdwMc(domain.getShdwMc());
			bo.setBz(domain.getBz());
			bo.setDjJgbm(user.bmbm);
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate().toString());
			bo.setYxbz("Y");
			bo.setQybz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyKhShdzByKey", bo);
		} else {
			BeanUtils.copyProperties(bo, domain);
			bo.setXxdz(s);
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			bo.setYxbz("Y");
			bo.setQybz("Y");
			bo.setDjJgbm(user.bmbm);
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate().toString());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyKhShdz", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		QyKhShdzDomain domain = (QyKhShdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("shdzDjxh", domain.getShdzDjxh());

		domain = (QyKhShdzDomain) businessSqlMapClientTemplate.queryForObject(
				"selectQyKhShdzByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		QyKhShdzDomain domain = (QyKhShdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("shdzDjxh", domain.getShdzDjxh());

		businessSqlMapClientTemplate.update("deleteQyKhShdzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyKhShdzDomain domain = (QyKhShdzDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getShdzDjxh())) {
			QyKhShdzDomain dom = (QyKhShdzDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	public int checkShXxdzRe(QyKhShdzDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("khDjxh", domain.getKhDjxh());
		map.put("shdzDjxh", domain.getShdzDjxh());
		map.put("xxDz", SysEncodeUtil.UTF82ISO(domain.getXxdz()));
		int count = (Integer) businessSqlMapClientTemplate.queryForObject(
				"CheckShXxdzRe", map);
		return count;
	}
}
