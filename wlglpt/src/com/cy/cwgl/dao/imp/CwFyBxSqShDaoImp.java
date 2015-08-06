package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.CwFybxsq;
import com.cy.common.bo.CwFybxsqMx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwFyBxSqShDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.domain.CwFyBxSqMxDomain;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.cwgl.domain.CwFybxsqShDomain;
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
public class CwFyBxSqShDaoImp implements CwFyBxSqShDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<CwFybxsqShDomain> queryAllList(BaseBusinessDomain baseDomain,UserDomain user)  throws Exception {
		CwFybxsqShDomain domain = (CwFybxsqShDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		List<CwFybxsqShDomain> dataList=null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("djxh", user.getCzyDjxh());
		map.put("shbz", domain.getShbz());
		map.put("rqq", domain.getRqq());
		map.put("rqz", domain.getRqz());
		map.put("dataList", dataList);
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryListFyBxSqSh", "dataList", map);
		dataList=(List<CwFybxsqShDomain>)map.get("dataList");
		int i=0;
		for (CwFybxsqShDomain cwFybxsqShDomain : dataList) {
			String bzStr="";
			
			map.put("cwDjxh", cwFybxsqShDomain.getCwDjxh());
			List<CwFyBxSqMxDomain> mxList=businessSqlMapClientTemplate.queryForList("selectCwFybxsqMxByKey",map);
			for (CwFyBxSqMxDomain cwFyBxSqMxDomain : mxList) {
				if(cwFyBxSqMxDomain.getBz()!=null&&!cwFyBxSqMxDomain.getBz().equals("null")){
					bzStr+=cwFyBxSqMxDomain.getBz()+",";
				}
			}
			if(!bzStr.equals("")){
				bzStr=bzStr.substring(0,bzStr.length()-1);
			}
			cwFybxsqShDomain.setMxBz(bzStr);
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwFpKpdjDomain domain = (CwFpKpdjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getKpdwJgbm())){
			map.put("kpdwJgbm", domain.getKpdwJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwFpKpdjAll", map);
		return dataList;
	}

	@SuppressWarnings("unchecked")
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
			bo.setSqrq(SysDateUtil.getCurrentDate());
			bo.setFyjzDwDjxh(domain.getFyjzDwDjxh());
			bo.setFyzfDwDjxh(domain.getFyzfDwDjxh());
			bo.setFybxje(domain.getFybxje());
			bo.setBz(domain.getBz());
			bo.setXmflDjxh(domain.getXmflDjxh());
			bo.setSpbz("N");
			bo.setWsspztDm(domain.getWsspztDm());
			bo.setWsSpxh(domain.getWsSpxh());
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			
			businessSqlMapClientTemplate.update("updateCwFybxsqByKey", bo);
		}else{
			domain.setSqrCzyDjxh(user.getCzyDjxh());
			domain.setSqrq(SysDateUtil.getCurrentDate());
			domain.setSqDwDjxh(user.getZgsbm());
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
			this.deleteHxCl(domain, user);
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

	


	
	

}
