package com.cy.hygl.dao.imp;

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
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.HyPcHwxxHdqd;
import com.cy.hygl.dao.HyPcHwxxHdqdDao;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;

/**
 * The DAOIMP for 货运-派车-货物信息-回单清单.
 * 
 * @author HJH
 */

@Repository
public class HyPcHwxxHdqdDaoImp implements HyPcHwxxHdqdDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxHdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryQdList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("qdCxBz", domain.getQdCxBz());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxHdqdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdqdPage", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryHdByQd(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = 999999;

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("hdqdDjxh", domain.getHdqdDjxh());
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("queryHdByQd", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdqdAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		HyPcHwxxHdqd bo = new HyPcHwxxHdqd();

		SysEncodeUtil.decodeURL(domain);
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		HyPcHwxxHdqdDomain dom = (HyPcHwxxHdqdDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setQdmc(domain.getQdmc());
			bo.setJszt(domain.getJszt());
			bo.setFsGsbm(domain.getFsGsbm());
			bo.setJsGsbm(domain.getJsGsbm());
			bo.setBz(domain.getBz());
			bo.setSsJgbm(domain.getSsJgbm());
			bo.setDjJgbm(domain.getDjJgbm());


			bo.setYxbz("Y");

			businessSqlMapClientTemplate.update("updateHyPcHwxxHdqdByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSsJgbm(user.getGsbm());
			bo.setDjJgbm(user.getBmbm());
			bo.setYxbz("Y");
			bo.setDbrCzyDjxh(user.getCzyDjxh());
			bo.setDbrq(SysDateUtil.getSqlDate()+"");

			businessSqlMapClientTemplate.insert("insertHyPcHwxxHdqd", bo);
		}
		domain.setHdqdDjxh(bo.getHdqdDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hdqdDjxh", domain.getHdqdDjxh());

		domain = (HyPcHwxxHdqdDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxHdqdByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hdqdDjxh", domain.getHdqdDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcHwxxHdqdByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHdqdDjxh())){
			HyPcHwxxHdqdDomain dom = (HyPcHwxxHdqdDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public void updatejsztWhenJs(String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hdqdDjxh", hdqdDjxh);

		businessSqlMapClientTemplate.update("updatejsztWhenJs", map);
	}
	public int checkTh(String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hdqdDjxh", hdqdDjxh);
		int count = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkTh", map))).intValue();
		return count;
	}
	public void updatejsztWhenTh(String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hdqdDjxh", hdqdDjxh);
		businessSqlMapClientTemplate.update("updatejsztWhenTh", map);
	}
	@SuppressWarnings("unchecked")
	public List<HyPcHwxxHdqdDomain> hdDjxhList(String hdqdDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("hdqdDjxh", hdqdDjxh);

		// 检索数据
		List<HyPcHwxxHdqdDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHdByHdqdDjxh", map);
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public String selectQdDjxh(String hdDjxh,String ssJgbm) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("hdDjxh", hdDjxh);
		map.put("ssJgbm", ssJgbm);

		// 检索数据
		HyPcHwxxHdqdDomain  dom = (HyPcHwxxHdqdDomain) businessSqlMapClientTemplate.queryForObject("selectQdByHdDjxhWhenDelete", map);
		return dom.getHdqdDjxh();
	}
}
