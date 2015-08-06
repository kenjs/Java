package com.cy.cwgl.dao.imp;



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

import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.bo.CwKhysgl;
import com.cy.common.bo.CwYsyf;
import com.cy.common.constants.XtglConstants;
import com.cy.cwgl.dao.CwKhysglDao;
import com.cy.cwgl.domain.CwKhysglDomain;
import com.cy.cwgl.domain.CwKhysglMxDomain;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DAOIMP for 财务-客户预收收入.
 * 
 * @author HJH
 */

@Repository
public class CwKhysglDaoImp implements CwKhysglDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("jgbm", domain.getDjJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		else{
			map.put("fhr", "%"+SysEncodeUtil.UTF82ISO(domain.getFhrMc())+"%");
		}
		if(StringUtils.isNotBlank(domain.getRqq())){
			map.put("rqq", domain.getRqq());
		}
		if(StringUtils.isNotBlank(domain.getRqz())){
			map.put("rqz", domain.getRqz());
		}
		
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwKhysglRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwKhysglPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSsJgbm4Query())){
			map.put("jgbm", domain.getSsJgbm4Query());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		else{
			map.put("fhr", "%"+SysEncodeUtil.UTF82ISO(domain.getFhrMc())+"%");
		}
		if(StringUtils.isNotBlank(domain.getRqq())){
			map.put("rqq", domain.getRqq());
		}
		if(StringUtils.isNotBlank(domain.getRqz())){
			map.put("rqz", domain.getRqz());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwKhysglAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain) baseDomain;
		CwKhysgl bo = new CwKhysgl();
		CwKhysglDomain dom=null;
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		if(StringUtils.isNotBlank(domain.getSrDjxh())){
			 dom = (CwKhysglDomain) this.getDomainByKey(domain);
		}
		

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			//bo.setKhDjxh(domain.getKhDjxh());
			bo.setJe(domain.getJe());
			bo.setYxbz("Y");
			bo.setXgrDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateCwKhysglByKey", bo);
			this.addCwYsMx(domain,user);
		}else{
			bo.setKhMc(domain.getKhMc());
			bo.setJe(domain.getJe());
			bo.setKhDjxh(domain.getKhDjxh());
			bo.setYxbz("Y");
			bo.setDjrDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getCurrentDate());
			bo.setDjJgbm(user.getBmbm());
			bo.setSsSsjg(user.getGsbm());
			bo.setXgrDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.insert("insertCwKhysgl", bo);
			domain.setSrDjxh(bo.getSrDjxh());
			this.addCwYsMx(domain,user);
		}
	}
	
	public void addCwYsMx(CwKhysglDomain domain, UserDomain user) throws Exception{
		CwKhysglMxDomain mxDomain=new CwKhysglMxDomain();
		mxDomain.setSrDjxh(domain.getSrDjxh());
		mxDomain.setJe(domain.getCzJe());
		mxDomain.setKhmc(domain.getKhMc());
		mxDomain.setZffsDm(domain.getZffs());
		mxDomain.setZcflDm(domain.getZcfl());
		mxDomain.setYhCshDjxh(domain.getYhCshDjxh());
		mxDomain.setYhhdh(domain.getYhhdh());
		mxDomain.setJbrCzyDjxh(domain.getJbrCzyDjxh());
		mxDomain.setJbrq(domain.getJbRq());
		mxDomain.setBz(domain.getBz());
		mxDomain.setYxbz("Y");
		mxDomain.setCzxfBz("1");
		businessSqlMapClientTemplate.insert("insertCwKhYsGlMx",mxDomain);
		CwYsyf ysBo=new CwYsyf();
		ysBo.setYfjsfDm("21");
		ysBo.setYfjsfDjxh(domain.getKhDjxh());
		ysBo.setKmdlDm("3");
		ysBo.setKmxlDm("312");
		ysBo.setZdyKmzlDm("");
		ysBo.setYsyflyDm("34");
		ysBo.setYwDjxh(mxDomain.getMxXh());
		ysBo.setCsrq(SysDateUtil.getSqlDate().toString());
		ysBo.setYsyfztDm("21");
		ysBo.setYsfJe(domain.getCzJe());
		ysBo.setYisfJe("0");
		ysBo.setWsfJe(domain.getCzJe());
		ysBo.setSm("客户:"+domain.getKhMc()+",充值金额:"+domain.getCzJe());
		ysBo.setYxbz("Y");
		ysBo.setDjJgbm(user.getBmbm());
		ysBo.setSsJgbm(user.getGsbm());
		businessSqlMapClientTemplate.insert("insertCwYsyf",ysBo);
	}
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("khDjxh", domain.getKhDjxh());
		map.put("jgbm", domain.getSsJgbm());
		domain = (CwKhysglDomain)businessSqlMapClientTemplate.queryForObject("selectCwKhysglByKey", map);
		return domain;
	}

	public List<CwKhysglMxDomain> getKhYsMx(BaseBusinessDomain baseDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("srDjxh", domain.getSrDjxh());
		List<CwKhysglMxDomain> list=(List<CwKhysglMxDomain>)businessSqlMapClientTemplate.queryForList("queryCwYsGlMx",map);
		String mc=(String)businessSqlMapClientTemplate.queryForObject("selectKhMcBySrDjxh",map);
		String je=(String)businessSqlMapClientTemplate.queryForObject("selectKhJeByKhDjxh",map);
		domain.setKhMc(SysEncodeUtil.ISO2GBK(mc));
		domain.setJe(je);
		return list;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		String jgbm=domain.getSsJgbm();
		map.put("khDjxh", domain.getKhDjxh());
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			CwKhysglDomain dom = (CwKhysglDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setSsJgbm(jgbm);
			}
			else{
				String mc=(String)businessSqlMapClientTemplate.queryForObject("selectKhMcByKhDjxh",map);
				domain.setKhMc(SysEncodeUtil.ISO2GBK(mc));
				domain.setJe(0+"");
			}
		}
		

	}

	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteMx(CwKhysglMxDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("xh", domain.getMxXh());
		map.put("srDjxh", domain.getSrDjxh());
		map.put("je", domain.getJe());
		businessSqlMapClientTemplate.update("deleteCwKhysglMxByKey",map);
		businessSqlMapClientTemplate.update("updateKhSrJe",map);
		businessSqlMapClientTemplate.delete("deleteCwYsYfByMxDjxh",map);
	}
	
	public String getInItJe(String djxh){
		Map<String, String> map=new HashMap<String, String>();
		map.put("srDjxh", djxh);
		String je=(String)businessSqlMapClientTemplate.queryForObject("selectKhJeByKhDjxh",map);
		return je;
	}

	
	public  void checkZcFl(CwKhysglDomain domain) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<String, String>();
		if(domain.getZcfl()!=null&&!domain.getZcfl().equals("")){
			map.put("zcfl", domain.getZcfl());
			map.put("jgbm", domain.getSsJgbm());
			String bz=(String)businessSqlMapClientTemplate.queryForObject("checkZcFlByJgbm",map);
			if(bz.equals("Y")){
				domain.setTager("1");
			}
			else{
				domain.setTager("2");
			}
		}
	}

	
	public int checkYsYfDj(CwKhysglMxDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("mxDjxh", domain.getMxXh());
		int count=(Integer)businessSqlMapClientTemplate.queryForObject("selectCountCwYsYfByMxDjxh",map);
		return count;
	}
}
