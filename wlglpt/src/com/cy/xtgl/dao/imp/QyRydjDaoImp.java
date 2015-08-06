package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyRyGw;
import com.cy.common.bo.QyRydj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.security.MD5;
import com.cy.xtgl.dao.QyRydjDao;
import com.cy.xtgl.domain.QyRyGwDomain;
import com.cy.xtgl.domain.QyRydjDomain;

/**
 * The DAOIMP for 用户维护
 * 
 * @author yu huan
 * @Date 2013-1-9
 */

@Repository
public class QyRydjDaoImp extends ExtendDaoImp implements QyRydjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			map.put("jgbm", String.valueOf(domain.getJgbm()));
		}
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		// 检索数据
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getQyRydjRowCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyRydjYhwhPage", map,
				start, pagSize);
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyZcxh", domain.getQyZcxh());
		// 设置查询条件
		if (StringUtils.isNotBlank(domain.getJbdm())) {
			map.put("jbdm", domain.getJbdm());
		}
		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyRydjAll", map);
		return dataList;
	}

	@Override
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseDomain;
		QyRydj rydjbo = new QyRydj();
		// 定义企业人员岗位表，用于添加修改
		QyRyGw rygwbo = new QyRyGw();
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyRydjDomain dom = (QyRydjDomain) this.getDomainByKey(domain);
		if (dom != null) {
			BeanUtils.copyProperties(rydjbo, dom);
			rydjbo.setZh(domain.getZh().toUpperCase());			
			MD5 md = new MD5();
			String pwd= md.getMD5ofStr(domain.getPwd());
			if(!"".equals(domain.getPwd().trim())){
				rydjbo.setPwd(pwd);
			}	
			rydjbo.setSjhm(domain.getSjhm());
			rydjbo.setSjdh(domain.getSjdh());
			rydjbo.setBgdh(domain.getBgdh());
			rydjbo.setBgdhao(domain.getBgdhao());
			rydjbo.setJtdh(domain.getJtdh());
			rydjbo.setQq(domain.getQq());
			rydjbo.setMsn(domain.getMsn());
			rydjbo.setEmail(domain.getEmail());
			rydjbo.setRylbDm(domain.getRylbDm());
			rydjbo.setDlyzfsDm(domain.getDlyzfsDm());
			rydjbo.setQxJgbm(domain.getQxJgbm());
			rydjbo.setXgrCzyDjxh(user.getCzyDjxh());
			rydjbo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateQyRydjByKey", rydjbo);
			// 修改企业人员岗位表
			Map<String, String> map = new HashMap<String, String>();
			map.put("czyDjxh", rydjbo.getCzyDjxh());
			map.put("gwDjxh", domain.getGwDjxh());
			map.put("oldGwDjxh", domain.getOldGwdjxh());
			map.put("ssJgbm", domain.getSsJgbm());
			map.put("zjbz", "Y");
			map.put("qxJgbm", domain.getQxJgbm());
			businessSqlMapClientTemplate.update("updateQyRyGwByKey", map);
		} else {
			BeanUtils.copyProperties(rydjbo, domain);

			rydjbo.setQyZcxh(user.getQyZcxh());
			rydjbo.setQybm(user.getQybm());
			rydjbo.setZh(domain.getZh().toUpperCase());
			MD5 md = new MD5();
			String pwd = md.getMD5ofStr(domain.getPwd());
			rydjbo.setPwd(pwd);
			
			rydjbo.setXtglybz("N");
			rydjbo.setQybz("Y");
			rydjbo.setYxbz("Y");
			rydjbo.setCjrCzyDjxh(user.getCzyDjxh());
			rydjbo.setXgrCzyDjxh(user.getCzyDjxh());
			rydjbo.setCjrq(SysDateUtil.getCurrentDate().toString());
			rydjbo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.insert("insertQyRydj", rydjbo);
			rygwbo.setQxJgbm(domain.getQxJgbm());
			rygwbo.setCzyDjxh(rydjbo.getCzyDjxh());
			rygwbo.setGwDjxh(domain.getGwDjxh());
			rygwbo.setSsJgbm(domain.getSsJgbm());
			rygwbo.setZjbz("Y");
			businessSqlMapClientTemplate.insert("insertQyRyGw", rygwbo);
		}

	}

	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseDomain;
		if (domain.getCzyDjxh() == null || "".equals(domain.getCzyDjxh().trim()))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("czyDjxh", domain.getCzyDjxh());
		domain = (QyRydjDomain) businessSqlMapClientTemplate.queryForObject("selectQyRydjByKey", map);
		return domain;
	}

	@Override
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件删除
		map.put("czyDjxh", domain.getCzyDjxh());
		businessSqlMapClientTemplate.update("deleteQyRydjByKey", map);
	}

	@Override
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyRydjDomain domain = (QyRydjDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getCzyDjxh())) {
			QyRydjDomain dom = (QyRydjDomain) this.getDomainByKey(domain);
			if (dom != null) {
				// 取得用户的主部门、主岗位、权限机构等信息（从QY_RY_GW表中获取ZJBZ='Y'的记录）
				QyRyGwDomain qyDom = new QyRyGwDomain();
				qyDom.setCzyDjxh(dom.getCzyDjxh());
				qyDom = (QyRyGwDomain) businessSqlMapClientTemplate.queryForObject("selectZgwByCzyDjxh", qyDom);
				
				if (qyDom != null) {
					dom.setGwDjxh(qyDom.getGwDjxh());
					dom.setSsJgbm("" + qyDom.getSsJgbm());
					dom.setQxJgbm("" + qyDom.getQxJgbm());
					dom.setZjbz(qyDom.getZjbz());
					BeanUtils.copyProperties(domain, dom);
				}
			}
		}
	}

	// 验证账号不能重复
	public int checkQyzzYhwhMc(QyRydjDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("czyDjxh", "" + domain.getCzyDjxh());
		map.put("zh",domain.getZh().toUpperCase());
		map.put("qyzcxh", domain.getQyZcxh());
		int czyDjxh = ((Integer) (businessSqlMapClientTemplate.queryForObject("checkQyRydjMcRepeat", map))).intValue();
		return czyDjxh;
	}

	// 启用
	public void startUse(QyRydjDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件删除
		map.put("czyDjxh", domain.getCzyDjxh());
		businessSqlMapClientTemplate.update("startQyzzYhwhUse", map);
	}

	// 停用
	public void stopUse(QyRydjDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置主键条件删除
		map.put("czyDjxh", domain.getCzyDjxh());
		businessSqlMapClientTemplate.update("stopQyzzYhwhUse", map);
	}

}
