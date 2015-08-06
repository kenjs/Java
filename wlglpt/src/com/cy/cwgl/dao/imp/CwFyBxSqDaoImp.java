package com.cy.cwgl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwFybxsq;
import com.cy.common.bo.CwFybxsqMx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwFyBxSqDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.domain.CwFyBxSqMxDomain;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * The DAOIMP for 财务-发票开票登记
 * 
 * @author LYY
 */

@Repository
public class CwFyBxSqDaoImp implements CwFyBxSqDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryAllList(BaseBusinessDomain baseDomain,UserDomain user)  throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if(StringUtils.isNotBlank(domain.getSqrq())){
			map.put("sqrq", domain.getSqrq());
		}
		if(StringUtils.isNotBlank(domain.getRqz())){
			map.put("sqrz", domain.getRqz());
		}
		map.put("djxh", user.getCzyDjxh());
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwFybxsqRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectCwFybxsqPage", map,start,pagSize);
		for (BaseBusinessDomain baseBusinessDomain2 : dataList) {
			String bzStr="";
			CwFybxsqDomain cwDomain=(CwFybxsqDomain)baseBusinessDomain2;
			map.put("cwDjxh", cwDomain.getCwDjxh());
			List<CwFyBxSqMxDomain> mxList=businessSqlMapClientTemplate.queryForList("selectCwFybxsqMxByKey",map);
			for (CwFyBxSqMxDomain cwFyBxSqMxDomain : mxList) {
				if(cwFyBxSqMxDomain.getBz()!=null&&!cwFyBxSqMxDomain.getBz().equals("null")){
					bzStr+=cwFyBxSqMxDomain.getBz()+",";
				}
			}
			if(!bzStr.equals("")){
				bzStr=bzStr.substring(0,bzStr.length()-1);
			}
			cwDomain.setMxBz(bzStr);
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSqrq())){
			map.put("sqrq", domain.getSqrq());
		}
		map.put("djxh", domain.getSsJgbm());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwFybxsqAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain) baseDomain;
		CwFybxsq bo = new CwFybxsq();
		CwFybxsqDomain dom=null;
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			 dom = (CwFybxsqDomain) this.getDomainByKey(domain);
		}
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			bo.setSqrCzyDjxh(user.getCzyDjxh());
			bo.setFyjzDwDjxh(domain.getFyjzDwDjxh());
			bo.setFyzfDwDjxh(domain.getFyzfDwDjxh());
			bo.setFybxje(domain.getFybxje());
			bo.setBz(domain.getBz());
			bo.setSqrq(domain.getSqrq());
			bo.setXmflDjxh(domain.getXmflDjxh());
			bo.setBxdh(domain.getBxdh());
			//bo.setWsspztDm(domain.getWsspztDm());
			//bo.setWsSpxh(domain.getWsSpxh());
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			
			businessSqlMapClientTemplate.update("updateCwFybxsqByKey", bo);
		}else{
			domain.setSqrCzyDjxh(user.getCzyDjxh());
			domain.setSqrq(domain.getSqrq());
			domain.setSqDwDjxh(user.getGsbm());
			domain.setSqBmDjxh(user.getBmbm());
			domain.setSpbz("N");
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertCwFybxsq", bo);
			domain.setCwDjxh(bo.getCwDjxh());
		}
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwFybxsqDomain)businessSqlMapClientTemplate.queryForObject("selectCwFybxsqByKey", map);
		return domain;
	}


	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			CwFybxsqDomain dom = (CwFybxsqDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public int queryKhmcCount(CwFpKpdjDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());
		map.put("kpdwJgbm", domain.getKpdwJgbm());
		map.put("khmc", SysEncodeUtil.UTF82ISO(domain.getKhmc()));
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("queryKhmcCount", map))).intValue();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<CwFpKpdjDomain> queryXzqhList(CwFpKpdjDomain domain) throws Exception{
		//Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		List<CwFpKpdjDomain>  data = businessSqlMapClientTemplate.queryForList("queryXzqhList", null);
		return data;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain=(CwFybxsqDomain)baseDomain;
		Map<String , String> map=new HashMap<String, String>();
		map.put("cwDjxh", domain.getCwDjxh());
		businessSqlMapClientTemplate.update("deleteCwFybxsqByKey",map);
		this.deleteHxCl(baseDomain, userDomain);
	}
	
	public void deleteHxCl(BaseBusinessDomain baseDomain, UserDomain userDomain){
		CwFybxsqDomain domain=(CwFybxsqDomain)baseDomain;
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("cwDjxh", domain.getCwDjxh());
		map.put("czyDjxh", userDomain.getCzyDjxh());
		map.put("jzDw", domain.getFyjzDwDjxh());
		map.put("count", 0);
		map.put("error", "");
		businessSqlMapClientTemplate.update("deleteFxSqHxCl",map);
		if(map.get("error")!=null&&(Integer)map.get("count")!=0){
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("error")));
		}
	}

	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<BaseBusinessDomain> queryMxList(BaseBusinessDomain baseDomain,
			UserDomain user) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain) baseDomain;
		Map<String, String> map=new HashMap<String, String>();
		map.put("cwDjxh", domain.getCwDjxh());
		List<BaseBusinessDomain> dataList =businessSqlMapClientTemplate.queryForList("selectCwFybxsqMxByKey",map);
		return dataList;
	}

	
	public void saveMx(BaseBusinessDomain baseDomain,UserDomain user,CwFybxsqDomain cwDomain)
		throws Exception {
		CwFyBxSqMxDomain domain=(CwFyBxSqMxDomain)baseDomain;
		CwFybxsqMx bo=new CwFybxsqMx();
		bo.setYxbz("Y");
		bo.setCwDjxh(domain.getCwDjxh());
		bo.setFylbCwDjxh(domain.getFylbDjxh());
		bo.setFyxmCwDjxh(domain.getFyxmDjxh());
		bo.setFyje(Double.valueOf(domain.getFyJe()));
		bo.setBxje(Double.valueOf(domain.getBxJe()));
		bo.setBz(domain.getBz());
		businessSqlMapClientTemplate.insert("insertCwFybxsqMx",bo);
		this.deleteHxCl(cwDomain, user);
	}

	
	public void updateMx(BaseBusinessDomain baseDomain,UserDomain user,CwFybxsqDomain cwDomain)
			throws Exception {
		CwFyBxSqMxDomain domain=(CwFyBxSqMxDomain)baseDomain;
		CwFybxsqMx bo=new CwFybxsqMx();
		bo.setYxbz("Y");
		bo.setXh(domain.getMxDjxh());
		bo.setCwDjxh(domain.getCwDjxh());
		bo.setFylbCwDjxh(domain.getFylbDjxh());
		bo.setFyxmCwDjxh(domain.getFyxmDjxh());
		bo.setFyje(Double.valueOf(domain.getFyJe()));
		bo.setBxje(Double.valueOf(domain.getBxJe()));
		bo.setBz(domain.getBz());
		businessSqlMapClientTemplate.update("updateCwFybxsqMxByKey",bo);
		this.deleteHxCl(cwDomain, user);
	}

	
	public void deleteMx(BaseBusinessDomain baseDomain,UserDomain user,CwFybxsqDomain cwDomain) throws Exception {
		CwFyBxSqMxDomain domain=(CwFyBxSqMxDomain)baseDomain;
		Map<String, String> map=new HashMap<String, String>();
		map.put("cwDjxh", domain.getCwDjxh());
		map.put("djxh", domain.getMxDjxh());
		businessSqlMapClientTemplate.update("deleteFyBxSqMx",map);
		cwDomain.setCwDjxh(domain.getCwDjxh());
		cwDomain.setFyjzDwDjxh(domain.getYxbz());
		this.deleteHxCl(cwDomain, user);
	}

	
	public String checkXmfl(BaseBusinessDomain baseDomain) throws Exception {
		CwFyBxSqMxDomain domain=(CwFyBxSqMxDomain)baseDomain;
		Map<String, String> map=new HashMap<String, String>();
		map.put("djxh", domain.getCwDjxh());
		String djxh=(String)businessSqlMapClientTemplate.queryForObject("queryXmFlDjxh",map);
		return djxh;
	}
	
	public String getMcByJgbm(String jgbm){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("jgbm", jgbm);
		String mc=(String)businessSqlMapClientTemplate.queryForObject("getDwMcByJgbm",map);
		return mc;
	}

}
