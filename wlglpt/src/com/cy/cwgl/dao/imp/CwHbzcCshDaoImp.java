package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwHbzcCsh;
import com.cy.common.bo.CwHbzcye;
import com.cy.common.bo.CwHbzcye2;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwHbzcCshDao;
import com.cy.cwgl.domain.CwHbzcCshDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 财务-货币资产初始化.
 * 
 * @author HJH
 */

@Repository
public class CwHbzcCshDaoImp implements CwHbzcCshDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("jgbm", domain.getSsJgbm());
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwHbzcCshRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcCshPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		map.put("jgbm", domain.getSsJgbm());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcCshAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		CwHbzcCsh bo = new CwHbzcCsh();
		CwHbzcCshDomain dom=null;
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		if(StringUtils.isNotBlank(domain.getCshDjxh())){
			 dom = (CwHbzcCshDomain) this.getDomainByKey(domain);
		}
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setSsJgbm(domain.getSsJgbm());
			bo.setZcflDm(domain.getZcflDm());
			bo.setYhmc(domain.getYhmc());
			bo.setHm(domain.getHm());
			bo.setZh(domain.getZh());
			bo.setCsje(domain.getCsje());
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateCwHbzcCshByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setQybz("Y");
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.insert("insertCwHbzcCsh", bo);
			CwHbzcye hbBo=new CwHbzcye();
			hbBo.setSsJgbm(domain.getSsJgbm());
			hbBo.setZcflDm(domain.getZcflDm());
			hbBo.setYhCshDjxh(bo.getCshDjxh());
			hbBo.setJe(domain.getCsje());
			hbBo.setYxbz("Y");
			businessSqlMapClientTemplate.insert("insertCwHbzcye",hbBo);
			CwHbzcye2 zcBo=new CwHbzcye2();
			zcBo.setSsJgbm(domain.getSsJgbm());
			zcBo.setZcflDm(domain.getZcflDm());
			zcBo.setYhCshDjxh(bo.getCshDjxh());
			zcBo.setJe(domain.getCsje());
			zcBo.setSm("初始化");
			zcBo.setRq(SysDateUtil.getCurrentDate());
			zcBo.setJbrCzyDjxh(user.getCzyDjxh());
			zcBo.setDjJgbm(user.getBmbm());
			zcBo.setBz("1");
			zcBo.setYwxh(bo.getCshDjxh());
			zcBo.setYxbz("Y");
			businessSqlMapClientTemplate.insert("insertCwHbzcye2",zcBo);
		}
	}
	
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cshDjxh", domain.getCshDjxh());

		domain = (CwHbzcCshDomain)businessSqlMapClientTemplate.queryForObject("selectCwHbzcCshByKey", map);
		return domain;
	}

	public String getYhCshDjxhWhenXj(String ssJgbm) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("ssJgbm", ssJgbm);

		int yhCshDjxh = ((Integer)(businessSqlMapClientTemplate.queryForObject("getYhCshDjxhWhenXj", map))).intValue();
		return yhCshDjxh+"";
	}
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		Map<String, String> map=new HashMap<String, String>();
		map.put("cshDjxh", domain.getCshDjxh());
		businessSqlMapClientTemplate.delete("deleteCwHbzcCshByKey",map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCshDjxh())){
			CwHbzcCshDomain dom = (CwHbzcCshDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
		else{
			Map<String, String> map=new HashMap<String, String>();
			if(StringUtils.isNotBlank(domain.getSsJgbm())){
				map.put("jgbm", domain.getSsJgbm());
				CwHbzcCshDomain cwDomain=(CwHbzcCshDomain)businessSqlMapClientTemplate.queryForObject("selectCshsjMcByJgbm", map);
				domain.setSjMc(cwDomain.getSjMc());
			}
			
		}

	}

	public void stopUser(CwHbzcCshDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(domain.getCshDjxh())){
			map.put("djxh", domain.getCshDjxh());
		}
		businessSqlMapClientTemplate.update("stopCwglHbByDjxh",map);
	}

	public void startUser(CwHbzcCshDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(domain.getCshDjxh())){
			map.put("djxh", domain.getCshDjxh());
		}
		businessSqlMapClientTemplate.update("startCwglHbByDjxh",map);
		
	}

	public void checkYhMc(CwHbzcCshDomain domain) throws Exception {
		int count=0;
		String mc=SysEncodeUtil.UTF82GBK(domain.getFlMc());
		Map<String, String> map=new HashMap<String, String>();
		if(mc.equals("银行存款")){
			String yhmc=SysEncodeUtil.UTF82ISO(domain.getYhmc());
			String zh=SysEncodeUtil.UTF82ISO(domain.getZh());
			map.put("mc", yhmc);
			map.put("zh", zh);
			map.put("jgbm", domain.getSsJgbm());
			count=(Integer)businessSqlMapClientTemplate.queryForObject("checkCwYhZh",map);
			if(count>0){
				domain.setYhTree("1");
			}
			
		}
		else {
			map.put("flMc", domain.getZcflDm());
			map.put("jgbm", domain.getSsJgbm());
			count=(Integer)businessSqlMapClientTemplate.queryForObject("checkCwFlMc",map);
			if(count>0){
				domain.setFlTree("1");
			}
		}
		
		
	}

	
	public void deleteHxcl(CwHbzcCshDomain baseDomain) throws Exception {
		CwHbzcCshDomain domain = (CwHbzcCshDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		Map<String,Object> mm = new HashMap<String, Object>();
		// 设置主键条件
		mm.put("gnDm", domain.getGnmkDm());
		mm.put("djXh", domain.getCshDjxh());
		mm.put("djXh1", "");
		mm.put("djXh2", "");
		mm.put("count", 0);
		mm.put("error", "");
		businessSqlMapClientTemplate.queryForObject("checkDeleteBefore",mm);
		if(mm.get("error")!=null&&(Integer)mm.get("count")!=0){
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("error")));
		}
		
	}
}
