package com.cy.cwgl.dao.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.cy.common.bo.CwSrdj;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwYsyfSrdjDao;
import com.cy.cwgl.domain.CwKhysglDomain;
import com.cy.cwgl.domain.CwKhysglMxDomain;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;


/**
 * The DAO for 财务-收入登记
 * 
 * @author HCM
 */
@Repository
public class CwYsyfSrdjDaoImp implements CwYsyfSrdjDao{
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;

		
		//根据页面上的排序条件设置排序
		map.put("zgsbm", domain.getZgsbm());
		map.put("jgbm", domain.getSsJgbm());
		map.put("csrqQ", domain.getRqQ());
		map.put("csrqZ", domain.getRqZ());
		map.put("yfjsfDm", domain.getYfjsfDm());
		map.put("yfjsfDjxh", domain.getYfjsfDjxh());
		if(StringUtils.isNotBlank(domain.getYfjsfDjmc())){
			map.put("yfjsfDjmc", SysEncodeUtil.GBK2ISO(domain.getYfjsfDjmc()));
		}
		map.put("zt", domain.getZt());
		map.put("ddbh", SysEncodeUtil.GBK2ISO(domain.getDdbh()));
		map.put("ysyflyDm", domain.getYsyflyDm());
		map.put("kmxlDm", domain.getKmxlDm());
		
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);

		map.put("dataList", dataList);
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryCwYsyfSrdjPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));

		return dataList;
	}
	

	public BaseBusinessDomain getYsyfSrdj(String ysyfDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ysyfDjxh", ysyfDjxh);
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain) businessSqlMapClientTemplate.queryForObject("getYsyfSrdj", map);
		return domain;
	}
	//其他收入
	public BaseBusinessDomain getYsyfSrdjByYwDjxh(String ywDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ywDjxh", ywDjxh);
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain) businessSqlMapClientTemplate.queryForObject("getYsyfSrdjByYwDjxh", map);
		return domain;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> getSrdjList(String ysyfDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ysyfDjxh", ysyfDjxh);
		List<BaseBusinessDomain> list = businessSqlMapClientTemplate.queryForList("getSrdjList", map);
		return list;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseDomain;
		if(StringUtils.isNotBlank(domain.getYsyfDjxh())){
			CwYsyfSrdjDomain dom = (CwYsyfSrdjDomain) getYsyfSrdj(domain.getYsyfDjxh());
			if(dom != null){
				BeanUtils.copyProperties(domain, dom);
			}
			List<BaseBusinessDomain> list = this.getSrdjList(domain.getYsyfDjxh());
			domain.setDataList(list);
		}
	}

	public  void deleteSrdj(String srDjxh)throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("srDjxh", srDjxh);

		businessSqlMapClientTemplate.update("deleteCwSrdjByKey", map);
	}
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)	throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseDomain;	
		CwSrdj bo = new CwSrdj();
		
		BeanUtils.copyProperties(bo,domain);
		
		bo.setYxbz("Y");
		bo.setDjrCzyDjxh(user.getCzyDjxh());
		bo.setDjrq(SysDateUtil.getSqlDate().toString());
		
		businessSqlMapClientTemplate.insert("insertCwSrdj", bo);
		if(bo.getZffsDm().equals("4")){
			//根据yfjsfDjxh取CW_YSLRGL查找SR_DJXH和客户名称
			CwKhysglMxDomain mxDomain=new CwKhysglMxDomain();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("khDjxh", bo.getYfjsfDjxh());
			map.put("jgbm",user.getGsbm());
			CwKhysglDomain cwDomain=(CwKhysglDomain)businessSqlMapClientTemplate.queryForObject("selectCwKhysglByKey",map);
			
			mxDomain.setSrDjxh(cwDomain.getSrDjxh());
			mxDomain.setJe("-"+domain.getJe());
			mxDomain.setKhmc(cwDomain.getKhMc());
			mxDomain.setZffsDm(domain.getZffsDm());
			mxDomain.setZcflDm(domain.getZcflDm());
			mxDomain.setYhCshDjxh(domain.getYhCshDjxh());
			mxDomain.setYhhdh(domain.getYhhdh());
			mxDomain.setJbrCzyDjxh(domain.getJbrCzyDjxh());
			mxDomain.setJbrq(domain.getRq());
			mxDomain.setBz("");
			mxDomain.setYxbz("Y");
			mxDomain.setCzxfBz("2");
			mxDomain.setYsyfDjxh(domain.getYsyfDjxh());
			businessSqlMapClientTemplate.insert("insertCwKhYsGlMx",mxDomain);
			//添加之后，要相应用CW_KHYSLV中的金额减去客户充值金额
			map.put("srDjxh", cwDomain.getSrDjxh());
			map.put("je", domain.getJe());
			businessSqlMapClientTemplate.update("updateKhSrJe",map);
		}
		domain.setSrDjxh(bo.getSrDjxh());
		
	}
	
	
	public void callPHyglCwglSrdjHxcl(String srDjxh, String bz) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("srDjxh", srDjxh);
		map.put("bz", bz);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPHyglCwglSrdjHxcl", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	@SuppressWarnings("unchecked")
	public List<CwYsyfSrdjDomain> getLbList() throws Exception {
		List<CwYsyfSrdjDomain> list = businessSqlMapClientTemplate.queryForList("getLbList");
		CwYsyfSrdjDomain dom = new CwYsyfSrdjDomain();
		dom.setYfjsfDm("");
		dom.setLbStr("-- 请选择 --");
		list.add(0, dom);
		return list;
	}
	//已不用
	@SuppressWarnings("unchecked")
	public List<CwYsyfSrdjDomain> getMcList(String ssJgbm,String yfjsfDm) throws Exception{
		List<CwYsyfSrdjDomain> list = new ArrayList<CwYsyfSrdjDomain>();
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ssJgbm", ssJgbm);
		
		if(StringUtils.isNotBlank(yfjsfDm)){
			int i  =Integer.parseInt(yfjsfDm);
			switch (i) {
			case 11:
				list= businessSqlMapClientTemplate.queryForList("getZjMcList",map);
				break;
			case 12:
				list= businessSqlMapClientTemplate.queryForList("getSjMcList",map);
				break;
			case 21:
				list= businessSqlMapClientTemplate.queryForList("getKhMcList",map);
				break;
			case 22:
				list= businessSqlMapClientTemplate.queryForList("getFgsMcList",map);
				break;
			case 23:
				list= businessSqlMapClientTemplate.queryForList("getFbsMcList",map);
				break;
			default:
				break;
			}
		}		
		CwYsyfSrdjDomain dom = new CwYsyfSrdjDomain();
		dom.setYfjsfDjxh("");
		dom.setMcStr("-- 请选择 --");
		list.add(0, dom);
		return list;
	}
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)	throws Exception {	
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("srDjxh", domain.getSrDjxh());

		domain = (CwYsyfSrdjDomain)businessSqlMapClientTemplate.queryForObject("selectCwSrdjByKey", map);
		return domain;
	}
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;

		
		//根据页面上的排序条件设置排序
		map.put("zgsbm", domain.getZgsbm());
		map.put("jgbm", domain.getSsJgbm());
		map.put("csrqQ", domain.getRqQ());
		map.put("csrqZ", domain.getRqZ());
		map.put("yfjsfDm", domain.getYfjsfDm());
		map.put("yfjsfDjxh", domain.getYfjsfDjxh());
		if(StringUtils.isNotBlank(domain.getYfjsfDjmc())){
			map.put("yfjsfDjmc", SysEncodeUtil.GBK2ISO(domain.getYfjsfDjmc()));
		}
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", 99999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);

		map.put("dataList", dataList);
		// 检索数据
		businessSqlMapClientTemplate.queryForObjectByCurr("queryCwYsyfSrdjPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		for(BaseBusinessDomain e:dataList){
			CwYsyfSrdjDomain element = (CwYsyfSrdjDomain)e;
			Date d = SysDateUtil.parse(element.getCsrq(), "yyyy-MM-dd HH:mm:ss");
			String dateStr = SysDateUtil.format(d, SysDateUtil.DATEFORMAT);
			element.setCsrq(dateStr);
		}
		return dataList;
	}

	public void doCancle(BaseBusinessDomain baseDomain) throws Exception {
		CwYsyfSrdjDomain domain = (CwYsyfSrdjDomain) baseDomain;
		businessSqlMapClientTemplate.delete("deleteCwSrdjByYsyfDjxh", domain);
	}


    public void checkDelete(String xh,int bz)throws Exception {
    	CwYsyfSrdjDomain dom = new CwYsyfSrdjDomain();
    	if(bz==0){
    		dom.setSrDjxh(xh);
    		dom = (CwYsyfSrdjDomain)this.getDomainByKey(dom);
    		if("4".equals(dom.getZffsDm())){
    			Map<String, String> map=new HashMap<String, String>();
        		if(StringUtils.isNotBlank(dom.getSsJgbm())){
        			map.put("jgbm", dom.getSsJgbm());
        		}
        		if(StringUtils.isNotBlank(dom.getYfjsfDjxh())){
        			map.put("djxh", dom.getYfjsfDjxh());
        		}
        		Double je=(Double)businessSqlMapClientTemplate.queryForObject("selectKhYsJeByKhDjxh",map);
        		if(je==null||je.equals("null")){
        			je=0.0;
        		}
        		if((double)dom.getJe()>(double)je){
        			throw new DiyServiceException("撤销后，该客户充值金额为负！不可撤销！");
        		}
    		}
    	}else{
    		dom = (CwYsyfSrdjDomain)getYsyfSrdj(xh);
    		if("312".equals(dom.getKmxlDm())){
        		Map<String, String> map=new HashMap<String, String>();
        		if(StringUtils.isNotBlank(dom.getSsJgbm())){
        			map.put("jgbm", dom.getSsJgbm());
        		}
        		if(StringUtils.isNotBlank(dom.getYfjsfDjxh())){
        			map.put("djxh", dom.getYfjsfDjxh());
        		}
        		Double je=(Double)businessSqlMapClientTemplate.queryForObject("selectKhYsJeByKhDjxh",map);
        		if(je==null||je.equals("null")){
        			je=0.0;
        		}
        		if((double)dom.getYsfJe()>(double)je){
        			throw new DiyServiceException("撤销后，该客户充值金额为负！不可撤销！");
        		}
    		}
    	}
    }
	public void checkZfFs(CwYsyfSrdjDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("jgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getYfjsfDjxh())){
			map.put("djxh", domain.getYfjsfDjxh());
		}
		Double je=(Double)businessSqlMapClientTemplate.queryForObject("selectKhYsJeByKhDjxh",map);
		Double wsJe=(Double)businessSqlMapClientTemplate.queryForObject("selectKhWsJeByKhDjxh",map);
		if(je==null||je.equals("null")){
			je=0.0;
		}
		domain.setJe(je);
		domain.setWsJe(wsJe);
	}
	public int checkSrdj(String yfjsfDm,String yfjsfDjxh) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
	

		map.put("yfjsfDm", yfjsfDm);
		map.put("yfjsfDjxh", yfjsfDjxh);
		
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkSrdj", map))).intValue();
		
		return totalRecords;
	}


	public void initCwYsJe(CwYsyfSrdjDomain domain,UserDomain userDomain) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(domain.getYfjsfDjxh())){
			map.put("khDjxh", domain.getYfjsfDjxh());
		}
		if(StringUtils.isNotBlank(userDomain.getGsbm())){
			map.put("jgbm", userDomain.getGsbm());
		}
		if(StringUtils.isNotBlank(domain.getYsyfDjxh())){
			map.put("ysyfDjxh", domain.getYsyfDjxh());
		}
		CwKhysglDomain cwDomain=(CwKhysglDomain)businessSqlMapClientTemplate.queryForObject("selectCwKhysglByKey",map);
		if(cwDomain!=null){
			map.put("srDjxh", cwDomain.getSrDjxh());
			List<CwKhysglDomain> cwList=(List<CwKhysglDomain>)businessSqlMapClientTemplate.queryForList("getCwYsLrMxJeBySrDjxh",map);
			double je=0;
			if(cwList!=null){
				for (CwKhysglDomain cwKhysglDomain : cwList) {
					 je+=Double.valueOf(cwKhysglDomain.getJe());
				}
			}
			
			if(!String.valueOf(je).equals("null")&&!String.valueOf(je).equals("0")){
				map.put("je", je);
				businessSqlMapClientTemplate.update("updateKhSrJe",map);
				businessSqlMapClientTemplate.update("deleteCwKhysglMxBySrDjxh",map);
			}
		}
	}
	
	public  void deleteYsyfXx(String ywDjxh)throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ywDjxh", ywDjxh);

		businessSqlMapClientTemplate.delete("deleteYsyfXx", map);
	}
	
	public  void updateYsyfXx(String ywDjxh)throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ywDjxh", ywDjxh);

		businessSqlMapClientTemplate.update("updateYsyfXx", map);
	}
	
}
