package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyKhZhdz;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.dao.QyKhZhdzDao;
import com.cy.zygl.domain.QyKhZhdzDomain;

/**
 * The DAOIMP for 企业-客户-装货地址.
 * 
 * @author ylp
 * @since 2013-1-15 上午8:40:00
 * @version
 */

@Repository
public class QyKhZhdzDaoImp implements QyKhZhdzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseDomain;
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

		int totalRecords = ((Integer) (businessSqlMapClientTemplate
				.queryForObject("getQyKhZhdzRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyKhZhdzPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();

		if (StringUtils.isNotBlank(domain.getKhDjxh())) {
			String kh = domain.getKhDjxh().trim();
			map.put("khDjxh", kh);
		}
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyKhZhdzAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseDomain;
		QyKhZhdz bo = new QyKhZhdz();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyKhZhdzDomain dom = (QyKhZhdzDomain) this.getDomainByKey(domain);
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
			bo.setBz(domain.getBz());
			bo.setDjJgbm(user.bmbm);
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate().toString());
			bo.setQybz("Y");
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyKhZhdzByKey", bo);
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

			businessSqlMapClientTemplate.insert("insertQyKhZhdz", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhdzDjxh", domain.getZhdzDjxh());

		domain = (QyKhZhdzDomain) businessSqlMapClientTemplate.queryForObject(
				"selectQyKhZhdzByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhdzDjxh", domain.getZhdzDjxh());

		businessSqlMapClientTemplate.update("deleteQyKhZhdzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyKhZhdzDomain domain = (QyKhZhdzDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getZhdzDjxh())) {
			QyKhZhdzDomain dom = (QyKhZhdzDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	public int checkXxdzRe(QyKhZhdzDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("khDjxh", domain.getKhDjxh());
		map.put("zhdzDjxh", domain.getZhdzDjxh());
		map.put("xxDz", SysEncodeUtil.UTF82ISO(domain.getXxdz()));
		int count = (Integer) businessSqlMapClientTemplate.queryForObject(
				"CheckXxdzRe", map);
		return count;

	}
}
