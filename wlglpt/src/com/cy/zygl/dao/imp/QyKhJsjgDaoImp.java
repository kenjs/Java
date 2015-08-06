package com.cy.zygl.dao.imp;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bsh.StringUtil;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.bo.QyKhJsjg;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.dao.QyKhJsjgDao;
import com.cy.zygl.domain.QyKhJsjgDomain;


/**
 * The DAOIMP for 企业-客户-结算价格.
 * 
 * @author HJH
 */

@Repository
public class QyKhJsjgDaoImp implements QyKhJsjgDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("jgbm", domain.getSsJgbm());
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
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyKhJsjgRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhJsjgPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		String jgbm=domain.getSsJgbm();
		if(StringUtils.isNotBlank(domain.getFhrDjxh())){
			map.put("khDjxh", domain.getFhrDjxh());
		}
		if(StringUtils.isNotBlank(domain.getHwDjxh())){
			map.put("hwDjxh", domain.getHwDjxh());
		}
		if(domain.getBmDjxh()!=null&&!domain.getBmDjxh().equals("")){
			jgbm=domain.getBmDjxh();
		}
		map.put("jgbm", jgbm);
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhJsjgAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain) baseDomain;
		QyKhJsjg bo = new QyKhJsjg();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyKhJsjgDomain dom = (QyKhJsjgDomain) this.getDomainByKey(domain);

		if(dom != null){
BeanUtils.copyProperties(bo, dom);
			
			bo.setSsJgbm(domain.getSsJgbm());
			bo.setKhDjxh(domain.getKhDjxh());
			bo.setSfdXzqhDm(domain.getFhrXzqhDm());
			bo.setMddXzqhDm(domain.getShrXzqhDm());
			bo.setLcs(domain.getLcs());
			bo.setDdts(domain.getDdts());
			bo.setSyfw(domain.getSyfw());
			bo.setHwDjxh(domain.getHwDjxh());
			bo.setHwxhDjxh(domain.getHwxhDjxh());
			bo.setJldwFlDm(domain.getJldwFlDm());
			bo.setJldwDm(domain.getJldwDm());
			bo.setJgjsgs(domain.getJgjsgs());
			bo.setXtgs(domain.getXtgs());
			bo.setJgsm(domain.getJgsm());
			bo.setYxqQ(domain.getYxqQ());
			bo.setYxqZ(domain.getYxqZ());
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyKhJsjgByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSfdXzqhDm(domain.getFhrXzqhDm());
			bo.setMddXzqhDm(domain.getShrXzqhDm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyKhJsjg", bo);
		}
	}

	
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jsjgDjxh", domain.getJsjgDjxh());

		domain = (QyKhJsjgDomain)businessSqlMapClientTemplate.queryForObject("selectQyKhJsjgByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("jsjgDjxh", domain.getJsjgDjxh());

		businessSqlMapClientTemplate.update("deleteQyKhJsjgByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyKhJsjgDomain domain = (QyKhJsjgDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getJsjgDjxh())){
			QyKhJsjgDomain dom = (QyKhJsjgDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setFhrXzqhDm(domain.getSfdXzqhDm());
				domain.setShrXzqhDm(domain.getMddXzqhDm());
			}
		}
		else{
			String mc=new String(domain.getKhMc().getBytes("ISO-8859-1"),"gb2312");
			domain.setKhMc(mc);
			domain.setYxqQ(SysDateUtil.getCurrentDate());
			Map<String, String> map=new HashMap<String, String>();
			map.put("jgbm", domain.getSsJgbm());
			QyKhJsjgDomain qyDomain=(QyKhJsjgDomain)businessSqlMapClientTemplate.queryForObject("getqyjsjgSjMcByJgbm",map);
			domain.setSjMc(qyDomain.getSjMc());
		}

	}

	
	public int saveCheck(QyKhJsjgDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("khDjxh", domain.getKhDjxh());
		map.put("hwDjxh", domain.getHwDjxh());
		map.put("hwxhDjxh", domain.getHwxhDjxh());
		map.put("sfdXzqhDm", domain.getFhrXzqhDm());
		map.put("mddXzqhDm", domain.getShrXzqhDm());
		map.put("yxqq", domain.getYxqQ());
		map.put("yxqz", domain.getYxqZ());
		map.put("djxh", domain.getJsjgDjxh());
		int count=(Integer)businessSqlMapClientTemplate.queryForObject("saveQyKhJSJgCheck",map);
		return count;
	}
}
