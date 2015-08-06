package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyJs;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.dao.QyJsDao;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAOIMP for 企业角色.
 * 
 * @author ylp
 * @since 2013-1-10 上午9:12:51
 * @version
 */

@Repository
public class QyJsDaoImp implements QyJsDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyJsDomain domain = (QyJsDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		if (StringUtils.isNotBlank(domain.getSsJgbm())) {
			String s = domain.getSsJgbm().trim();
			map.put("ssJgbm", s);
		}

		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer) (businessSqlMapClientTemplate
				.queryForObject("getQyJsRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyJsPage", map, start, pagSize);

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyJsDomain domain = (QyJsDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件

		if (StringUtils.isNotBlank(domain.getSsJgbm())) {
			String s = domain.getSsJgbm().trim();
			map.put("ssJgbm", s);
		}

		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyJsAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		QyJsDomain domain = (QyJsDomain) baseDomain;
		QyJs bo = new QyJs();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyJsDomain dom = (QyJsDomain) this.getDomainByKey(domain);

		if (dom != null) {
			BeanUtils.copyProperties(bo, dom);

			bo.setJsMc(domain.getJsMc());
			bo.setJsJc(domain.getJsJc());
			bo.setBzsm(domain.getBzsm());

			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyJsByKey", bo);
		} else {
			BeanUtils.copyProperties(bo, domain);
			bo.setLybz("N");
			bo.setQybz("Y");
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.insert("insertQyJs", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		QyJsDomain domain = (QyJsDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jsDjxh", domain.getJsDjxh());

		domain = (QyJsDomain) businessSqlMapClientTemplate.queryForObject(
				"selectQyJsByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		QyJsDomain domain = (QyJsDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("jsDjxh", domain.getJsDjxh());

		businessSqlMapClientTemplate.update("deleteQyJsByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyJsDomain domain = (QyJsDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getJsDjxh())) {
			QyJsDomain dom = (QyJsDomain) this.getDomainByKey(domain);
			if (dom != null) {
				BeanUtils.copyProperties(domain, dom);
			}
		}
		else{
			Map<String, String> map=new HashMap<String, String>();
			map.put("jgbm", domain.getSsJgbm());
			QyZzjgDomain qyDomain=(QyZzjgDomain)businessSqlMapClientTemplate.queryForObject("selectQyJsMcByJgbm",map);
			domain.setSjMc(qyDomain.getMc());
		}
	}

	// 检查角色名称是否重复
	public int checkJsMcRe(QyJsDomain qyjsdomain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotBlank(qyjsdomain.getSsJgbm())){
			map.put("ssJgbm", qyjsdomain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(qyjsdomain.getJsDjxh())){
			map.put("jsDjxh", qyjsdomain.getJsDjxh());
		}
		if(StringUtils.isNotBlank(qyjsdomain.getJsMc())){
			map.put("jsMc", SysEncodeUtil.UTF82ISO(qyjsdomain.getJsMc()));
		}
	int count  = (Integer) businessSqlMapClientTemplate.queryForObject("getQyJsJsdJxhReRowCount", map);
		

		return count;
	}

	// 删除角色前，判断该角色是否已在使用
	public int checkJsUsed(QyJsDomain qyjsdomain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("jsDjxh", qyjsdomain.getJsDjxh());
		int count = (Integer) businessSqlMapClientTemplate.queryForObject(
				"getQyJsUsedRowCount", map);
		return count;
	}

	public void startJs(QyJsDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(domain.getJsDjxh())) {
			map.put("jsDjxh", domain.getJsDjxh());
			businessSqlMapClientTemplate.update("startJs", map);
		}
	}

	public void stopJs(QyJsDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(domain.getJsDjxh())) {
			map.put("jsDjxh", domain.getJsDjxh());
			businessSqlMapClientTemplate.update("stopJs", map);
		}

	}

}
