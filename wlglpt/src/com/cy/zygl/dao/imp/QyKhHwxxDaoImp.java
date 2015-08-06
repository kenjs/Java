package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.QyKhHwxx;
import com.cy.zygl.dao.QyKhHwxxDao;
import com.cy.zygl.domain.QyKhHwxxDomain;

/**
 * The DAOIMP for 企业-客户-货物信息.
 * 
 * @author HJH
 */

@Repository
public class QyKhHwxxDaoImp implements QyKhHwxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		if(domain.getFhrMc()!=null&&!domain.getFhrMc().equals("")){
			map.put("fhrMc", "%"+SysEncodeUtil.UTF82ISO(domain.getFhrMc())+"%");
			List<QyKhHwxxDomain>	list=(List<QyKhHwxxDomain>)businessSqlMapClientTemplate.queryForList("selectQyKhDjxhByKhMc",map);
			String strKhDjxh="";
			if(list!=null){
				for (QyKhHwxxDomain qyKhHwxxDomain : list) {
					strKhDjxh+=qyKhHwxxDomain.getKhDjxh()+",";
				}
			}
			System.out.println(strKhDjxh);
			strKhDjxh=strKhDjxh.substring(0,strKhDjxh.length()-1);
			System.out.println(strKhDjxh);
			map.put("djxh", strKhDjxh);
		}
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyKhHwxxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhHwxxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhHwxxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseDomain;
		QyKhHwxx bo = new QyKhHwxx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyKhHwxxDomain dom = (QyKhHwxxDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setHwmc(domain.getHwmc());
			bo.setHwjc(domain.getHwjc());
			
			String s=SysEncodeUtil.UTF82GBK(domain.getHwmc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			
			bo.setCdJldwDm(domain.getCdJldwDm());
			bo.setCd(domain.getCd());
			bo.setKd(domain.getKd());
			bo.setGd(domain.getGd());
			bo.setBzJldwDm(domain.getBzJldwDm());
			bo.setBzJsHsbl(domain.getBzJsHsbl());
			bo.setBzCbHsbl(domain.getBzCbHsbl());
			bo.setJsJldwDm(domain.getJsJldwDm());
			//bo.setCbJldwDm(domain.getCbJldwDm());
			bo.setBz(domain.getBz());
			bo.setSlJldwDm(domain.getSlJldwDm());
			bo.setZlJldwDm(domain.getZlJldwDm());
			bo.setTjJldwDm(domain.getTjJldwDm());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());

			businessSqlMapClientTemplate.update("updateQyKhHwxxByKey", bo);
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
			
			String s=SysEncodeUtil.UTF82GBK(domain.getHwmc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));

			businessSqlMapClientTemplate.insert("insertQyKhHwxx", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hwDjxh", domain.getHwDjxh());

		domain = (QyKhHwxxDomain)businessSqlMapClientTemplate.queryForObject("selectQyKhHwxxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hwDjxh", domain.getHwDjxh());

		businessSqlMapClientTemplate.update("deleteQyKhHwxxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyKhHwxxDomain domain = (QyKhHwxxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHwDjxh())){
			QyKhHwxxDomain dom = (QyKhHwxxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}else{
			if(StringUtils.isNotBlank(domain.getKhDjxh())){
				String khjc = getKhjc(domain.getKhDjxh());
				domain.setKhjc(SysEncodeUtil.ISO2GBK(khjc));
			}
		}

	}

	public int checkHwmc(QyKhHwxxDomain qyKhHwxxdomain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("khDjxh",qyKhHwxxdomain.getKhDjxh());
		map.put("hwDjxh", qyKhHwxxdomain.getHwDjxh());
		map.put("hwmc", SysEncodeUtil.UTF82ISO(qyKhHwxxdomain.getHwmc()));
		int count =  (Integer)businessSqlMapClientTemplate.queryForObject("getQyKhHwxxMcCount", map);
		return count;
	}
	
	private String getKhjc(String khDjxh) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("khDjxh", khDjxh);
		return (String) businessSqlMapClientTemplate.queryForObject("getKhjcByKhDjxh", map);
	}
}
