package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyKhHwxh;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.dao.QyKhHwxhDao;
import com.cy.zygl.domain.QyKhHwxhDomain;

/**
 * The DAOIMP for 企业-客户-货物信息.
 * 
 * @author HJH
 */

@Repository
public class QyKhHwxhDaoImp implements QyKhHwxhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getHwDjxh())){
			map.put("hwDjxh", domain.getHwDjxh());
		}
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyKhHwxhRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhHwxhPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("hwDjxh", domain.getHwDjxh());
		}
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhHwxhAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseDomain;
		QyKhHwxh bo = new QyKhHwxh();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyKhHwxhDomain dom = (QyKhHwxhDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setXhmc(domain.getXhmc());
			bo.setXhjc(domain.getXhjc());
			bo.setXhqc(domain.getXhqc());
			
			String s=SysEncodeUtil.UTF82GBK(domain.getXhmc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			
			bo.setCdJldwDm(domain.getCdJldwDm());
			bo.setCd(domain.getCd());
			bo.setKd(domain.getKd());
			bo.setGd(domain.getGd());
			bo.setBzJldwDm(domain.getBzJldwDm());
//			bo.setBzJsHsbl(domain.getBzJsHsbl());
//			bo.setBzCbHsbl(domain.getBzCbHsbl());
			bo.setJsJldwDm(domain.getJsJldwDm());
			bo.setCbJldwDm(domain.getCbJldwDm());
			bo.setBz(domain.getBz());
			bo.setSlJldwDm(domain.getSlJldwDm());
			bo.setZlJldwDm(domain.getZlJldwDm());
			bo.setTjJldwDm(domain.getTjJldwDm());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());

			businessSqlMapClientTemplate.update("updateQyKhHwxhByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setQybz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setDjJgbm(user.getBmbm());
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate().toString());
			
			String s=SysEncodeUtil.UTF82GBK(domain.getXhmc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));

			businessSqlMapClientTemplate.insert("insertQyKhHwxh", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hwxhDjxh", domain.getHwxhDjxh());

		domain = (QyKhHwxhDomain)businessSqlMapClientTemplate.queryForObject("selectQyKhHwxhByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hwxhDjxh", domain.getHwxhDjxh());

		businessSqlMapClientTemplate.update("deleteQyKhHwxhByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyKhHwxhDomain domain = (QyKhHwxhDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHwxhDjxh())){
			QyKhHwxhDomain dom = (QyKhHwxhDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	public int checkHwxhmc(QyKhHwxhDomain qyKhHwxhdomain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hwxhDjxh",qyKhHwxhdomain.getHwxhDjxh());
		map.put("hwDjxh", qyKhHwxhdomain.getHwDjxh());
		map.put("xhmc", SysEncodeUtil.UTF82ISO(qyKhHwxhdomain.getXhmc()));
		int count =  (Integer)businessSqlMapClientTemplate.queryForObject("getQyKhHwxhMcCount", map);
		return count;
	}
}
