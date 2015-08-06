package com.cy.bggl.dao.imp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgGzlxDao;
import com.cy.bggl.domain.BgGzlxDomain;
import com.cy.common.bo.BgGzlx;
import com.cy.common.bo.BgGzlxFj;
import com.cy.common.bo.BgGzlxJsr;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DAOIMP for 办公-工作联系.
 * 
 * @author HJH
 */

@Repository
public class BgGzlxDaoImp implements BgGzlxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	//发件箱的查询
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", SysDateUtil.getIntervalDate(domain.getRqZ(), Calendar.DATE, 1));
		map.put("czyDjxh", domain.getCzyDjxh());

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgGzlxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxPage", map,start,pagSize);
		
		return dataList;
	}
	
	//收件箱的查询
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryListForSjx (BaseBusinessDomain baseDomain)  throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", SysDateUtil.getIntervalDate(domain.getRqZ(), Calendar.DATE, 1));
		map.put("czyDjxh", domain.getCzyDjxh());

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgGzlxRowCountForSjx", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxPageForSjx", map,start,pagSize);
		
		return dataList;
	}
	
	//草稿箱的查询
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryListForCgx (BaseBusinessDomain baseDomain)  throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", SysDateUtil.getIntervalDate(domain.getRqZ(), Calendar.DATE, 1));
		map.put("czyDjxh", domain.getCzyDjxh());

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgGzlxRowCountForCgx", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxPageForCgx", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", SysDateUtil.getIntervalDate(domain.getRqZ(), Calendar.DATE, 1));
		map.put("czyDjxh", domain.getCzyDjxh());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxAll", map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadListForSjx(BaseBusinessDomain baseDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", SysDateUtil.getIntervalDate(domain.getRqZ(), Calendar.DATE, 1));
		map.put("czyDjxh", domain.getCzyDjxh());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxAllForSjx", map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadListForCgx(BaseBusinessDomain baseDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", SysDateUtil.getIntervalDate(domain.getRqZ(), Calendar.DATE, 1));
		map.put("czyDjxh", domain.getCzyDjxh());

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxAllForCgx", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		BgGzlx bo = new BgGzlx();
		BgGzlxFj boFj = new BgGzlxFj();
		BgGzlxJsr boJsr = new BgGzlxJsr();
		
		int fjxh = 1;//附件序号
		
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		BgGzlxDomain dom = (BgGzlxDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			bo.setNr(domain.getNr());
			bo.setZt(domain.getZt());
			bo.setBcbzDm(domain.getBcbzDm());
			
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateBgGzlxByKey", bo);
			
			//修改操作时，先删除接收人
			this.deleteJsrByKey(domain);
			
			//修改操作时，判断是否需要添加附件，需要时则获取数据库中最大的附件序号
			if(domain.getUploadNameList().size() >0){
				fjxh = this.getMaxFjxh(domain);
			}
			
			
		}else{
			//保存工作联系表
			BeanUtils.copyProperties(bo, domain);
			bo.setNr(domain.getNr());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			
			businessSqlMapClientTemplate.insert("insertBgGzlx", bo);

		}
		
		
		//保存办公-工作联系-接收人
		if(StringUtils.isNotBlank(domain.getJsrs())){
			
			String[] jsrs = domain.getJsrs().split(",");
			String[] xtyhflDms = domain.getXtyhflDms().split(",");
			String jsr="";
			String xtyhflDm="";
			for (int i = 0; i < jsrs.length; i++) {
				jsr = jsrs[i];
				xtyhflDm = xtyhflDms[i];
				boJsr = new BgGzlxJsr();
				boJsr.setGzlxXh(bo.getGzlxXh());
				boJsr.setCzyDjxh(jsr);
				boJsr.setCkbz("N");
				boJsr.setCkrq(SysDateUtil.getCurrentDate());
				//XTYHFL_DM：如果是物流公司人员，则为“2”，如果是分包商人员，则为“6”
				boJsr.setXtyhflDm(new Long(xtyhflDm));
				
				businessSqlMapClientTemplate.insert("insertBgGzlxJsr", boJsr);	
			}
		}
		
			
		//保存办公-工作联系-附件
		for (int i = 0; i < domain.getUploadNameList().size(); i++) {
			boFj = new BgGzlxFj();
			boFj.setGzlxXh(bo.getGzlxXh());
			boFj.setXh(String.valueOf(fjxh+i));
			boFj.setFjmc(domain.getUploadNameList().get(i));
			boFj.setYxbz("Y");
			boFj.setFjnr(domain.getUploadValueList().get(i));
			businessSqlMapClientTemplate.insert("insertBgGzlxFj", boFj);
		}
		
		//置入工作联系序号
		domain.setGzlxXh(bo.getGzlxXh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("gzlxXh", domain.getGzlxXh());

		domain = (BgGzlxDomain)businessSqlMapClientTemplate.queryForObject("selectBgGzlxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("gzlxXh", domain.getGzlxXh());

		businessSqlMapClientTemplate.update("deleteBgGzlxByKey", map);
	}
	
	public void deleteJsrByKey(BgGzlxDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("gzlxXh", domain.getGzlxXh());

		businessSqlMapClientTemplate.update("deleteBgGzlxJsrByKey", map);
	}
	
	public void deleteFjDomain(BgGzlxDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("gzlxXh", domain.getGzlxXh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteBgGzlxFjByXh", map);
		
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgGzlxDomain domain = (BgGzlxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getGzlxXh())){
			BgGzlxDomain dom = (BgGzlxDomain) this.getDomainByKey(domain);
			List<BgGzlxDomain> fjList = this.getDomainFjByKey(domain);
			List<BgGzlxDomain> jsrList = this.getDomainJsrByKey(domain);
			
			String jsrMc = "";
			String jsr = "";
			String xtyhflDm="";
			
			for (Iterator iter = jsrList.iterator(); iter.hasNext();) {
				BgGzlxDomain element = (BgGzlxDomain) iter.next();
				jsrMc = element.getJsrMc()+","+jsrMc;
				jsr = element.getCzyDjxh()+","+jsr;
				xtyhflDm = element.getXtyhflDm()+","+xtyhflDm;
			}
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setFjList(fjList);
				if(StringUtils.isNotBlank(jsrMc)){
					domain.setJsrMcs(jsrMc.substring(0, jsrMc.length()-1));
				}
				if(StringUtils.isNotBlank(jsr)){
					domain.setJsrs(jsr.substring(0, jsr.length()-1));
				}
				if(StringUtils.isNotBlank(xtyhflDm)){
					domain.setXtyhflDms(xtyhflDm.substring(0, xtyhflDm.length()-1));
				}
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<BgGzlxDomain> getDomainFjByKey(BgGzlxDomain domain) throws Exception {
		List<BgGzlxDomain>  dataList = null;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("gzlxXh", domain.getGzlxXh());

		dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxFjByKey", map);		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BgGzlxDomain> getDomainJsrByKey(BgGzlxDomain domain) throws Exception {
		List<BgGzlxDomain>  dataList = null;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("gzlxXh", domain.getGzlxXh());

		dataList = businessSqlMapClientTemplate.queryForList("selectBgGzlxJsrByKey", map);		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public int getMaxFjxh(BgGzlxDomain domain) throws Exception {
		int fjxh = 0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		if(StringUtils.isNotBlank(domain.getGzlxXh())){
			map.put("gzlxXh", domain.getGzlxXh());
		}else{
			return 1;
		}
		
		fjxh = ((Integer) businessSqlMapClientTemplate.queryForObject("getMaxFjxhByGzlxXh", map)).intValue();		
		return fjxh+1;
	}

	public void getFjDomain(BgGzlxDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("gzlxXh", domain.getGzlxXh());
		map.put("xh", domain.getXh());
		BgGzlxDomain dom = (BgGzlxDomain)businessSqlMapClientTemplate.queryForObject("selectGzlxFjByXh", map);
		if(dom !=null){
			domain.setFjmc(dom.getFjmc());
			domain.setFjnr(dom.getFjnr());
		}
	}

	public void updateJsrDomain(BgGzlxDomain domain) throws Exception {
		BgGzlxJsr bo = new BgGzlxJsr();
		bo.setGzlxXh(domain.getGzlxXh());
		bo.setCzyDjxh(domain.getCzyDjxh());
		bo.setCkbz("Y");
		bo.setCkrq(SysDateUtil.getCurrentDate());
		businessSqlMapClientTemplate.update("updateBgGzlxJsrByKey", bo);
		
	}

}
