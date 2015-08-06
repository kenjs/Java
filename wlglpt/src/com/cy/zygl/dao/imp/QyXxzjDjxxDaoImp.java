package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyXxzjDjxx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.dao.QyXxzjDjxxDao;
import com.cy.zygl.domain.QyXxzjDjxxDomain;

/**
 * The DAO for 企业-信息中介-登记信息
 * 
 * @author yw
 * @since 2013-2-20 下午13:31:00
 * @version
 */

@Repository
public class QyXxzjDjxxDaoImp extends ExtendDaoImp implements QyXxzjDjxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@Override
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (domain.getSsJgbm() != null && !domain.getSsJgbm().equals("")) {
			map.put("jgbm", domain.getSsJgbm());
		}
		if (domain.getXxzjmc() != null && !domain.getXxzjmc().equals("")) {
			String s = SysEncodeUtil.UTF82ISO(domain.getXxzjmc());
			map.put("mc", "%" + s + "%");
		}
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getQyXxzjDjxxRowCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyXxzjDjxxPage", map, start, pagSize);
		return dataList;
	}

	@Override
	public void initDomainMx(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		if (domain.getXxzjDjxh() != null && !domain.getXxzjDjxh().equals("")) {
			BaseBusinessDomain xxzjDomain = this.getDomainByKey(domain);
			BeanUtils.copyProperties(domain, xxzjDomain);
		}
	}

	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("xxzjDjxh", domain.getXxzjDjxh());
		return (QyXxzjDjxxDomain) businessSqlMapClientTemplate.queryForObject("selectQyXxzjDjxxByKey", map);
	}

	@Override
	public void saveDomain(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		QyXxzjDjxxDomain xxzjDomain = null;
		QyXxzjDjxx bo = new QyXxzjDjxx();
		if (domain.getXxzjDjxh() != null && !domain.getXxzjDjxh().equals("")) {
			xxzjDomain = (QyXxzjDjxxDomain) this.getDomainByKey(domain);
		}
		String s = SysEncodeUtil.UTF82GBK(domain.getXxzjmc());
		if (xxzjDomain != null) {
			xxzjDomain.setXxzjDjxh(domain.getXxzjDjxh());
			xxzjDomain.setSsJgbm(domain.getSsJgbm());
			xxzjDomain.setXxzjmc(domain.getXxzjmc());
			xxzjDomain.setXxzjjc(domain.getXxzjjc());
			xxzjDomain.setDz(domain.getDz());
			xxzjDomain.setDh(domain.getDh());
			xxzjDomain.setYb(domain.getYb());
			xxzjDomain.setFzr(domain.getFzr());
			xxzjDomain.setXzqhDm(domain.getXzqhDm());
			xxzjDomain.setXxzjQybm(domain.getXxzjQybm());
			xxzjDomain.setBz(domain.getBz());
			xxzjDomain.setPyqc(CnToSpellUtil.getFullSpell(s));
			xxzjDomain.setPyjc(CnToSpellUtil.getFirstSpell(s));
			xxzjDomain.setDjJgbm(user.getBmbm());
			xxzjDomain.setDjrCzyDjxh(user.getCzyDjxh());
			xxzjDomain.setDjrq(SysDateUtil.getCurrentDate());
			xxzjDomain.setXgrCzyDjxh(user.getCzyDjxh());
			xxzjDomain.setXgrq(SysDateUtil.getCurrentDate());
			BeanUtils.copyProperties(bo, xxzjDomain);
			businessSqlMapClientTemplate.update("updateQyXxzjDjxxByKey", bo);
		} else {
			domain.setPyqc(CnToSpellUtil.getFullSpell(s));
			domain.setPyjc(CnToSpellUtil.getFirstSpell(s));
			domain.setDjJgbm(user.getBmbm());
			domain.setDjrCzyDjxh(user.getCzyDjxh());
			domain.setDjrq(SysDateUtil.getCurrentDate());
			domain.setCjrCzyDjxh(user.getCzyDjxh());
			domain.setCjrq(SysDateUtil.getCurrentDate());
			domain.setXgrCzyDjxh(user.getCzyDjxh());
			domain.setXgrq(SysDateUtil.getCurrentDate());
			domain.setQybz("Y");
			domain.setYxbz("Y");
			BeanUtils.copyProperties(bo, domain);
			businessSqlMapClientTemplate.insert("insertQyXxzjDjxx", bo);
		}
	}

	@Override
	public void deleteByKey(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getXxzjDjxh() != null && !domain.getXxzjDjxh().equals("")) {
			map.put("xxzjDjxh", domain.getXxzjDjxh());
			businessSqlMapClientTemplate.delete("deleteQyXxzjDjxxByKey", map);
		}
	}

	public int checkQyXxZjMc(QyXxzjDjxxDomain domain) throws Exception {
		QyXxzjDjxx bo = new QyXxzjDjxx();
		if (domain.getSsJgbm() != null && !domain.getSsJgbm().equals("")) {
			bo.setSsJgbm(domain.getSsJgbm());
		}
		if (domain.getXxzjmc() != null && !domain.getXxzjmc().equals("")) {
			String s = SysEncodeUtil.UTF82ISO(domain.getXxzjmc());
			bo.setXxzjmc(s);
		}
		if (domain.getXxzjDjxh() != null && !domain.getXxzjDjxh().equals("")) {
			bo.setXxzjDjxh(domain.getXxzjDjxh());
		}
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("checkQyXxZjMc", bo))).intValue();
		return count;
	}

	@Override
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyXxzjDjxxDomain domain = (QyXxzjDjxxDomain) baseBusinessDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getSsJgbm() != null && !domain.getSsJgbm().equals("")) {
			map.put("jgbm", domain.getSsJgbm());
		}
		if (domain.getXxzjmc() != null && !domain.getXxzjmc().equals("")) {
			String s = SysEncodeUtil.UTF82ISO(domain.getXxzjmc());
			map.put("mc", "%" + s + "%");
		}
		return businessSqlMapClientTemplate.queryForList("selectQyXxzjDjxxAll", map);
	}
}
