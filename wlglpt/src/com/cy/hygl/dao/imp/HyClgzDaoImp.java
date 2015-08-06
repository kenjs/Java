package com.cy.hygl.dao.imp;


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

import com.cy.common.bo.HyClgz;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyClgzDao;
import com.cy.hygl.domain.HyClgzDomain;
/**
 * The DAOIMP for 车辆跟踪
 * 
 * @author HCM
 */

@Repository
public class HyClgzDaoImp implements HyClgzDao {

	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)
			throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;
		if( domain.getDwbmBz4Query().equals("B")){
			map.put("pcJgbm4Query", domain.getDjJgbm4Query());//B   部门不为空   传部门
		}else if(domain.getDwbmBz4Query().equals("D")){
			map.put("pcJgbm4Query", domain.getPcJgbm4Query());//D  部门空    传单位
		}
		
		map.put("dwbmBz4Query", domain.getDwbmBz4Query());
		map.put("pcrCzyDjxh4Query", domain.getPcrCzyDjxh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		map.put("fhrMc4Query", domain.getFhrMc());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("clhm4Query", domain.getClhm4Query());
		map.put("sjxm4Query", domain.getSjxm4Query());
		map.put("ddbh4Query", domain.getDdbh4Query());
		map.put("pcdh4Query", domain.getPcdh4Query());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyClgzPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		for(BaseBusinessDomain e:dataList){
			HyClgzDomain element = (HyClgzDomain)e;
			Date d = SysDateUtil.parse(element.getPcrq(), "yyyy-MM-dd HH:mm:ss");
			String dateStr = SysDateUtil.format(d, SysDateUtil.DATEFORMAT);
			element.setPcrq(dateStr);
		}
		
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		List<BaseBusinessDomain>  dataList = null;
		if( domain.getDwbmBz4Query().equals("B")){
			map.put("pcJgbm4Query", domain.getDjJgbm4Query());//B   部门不为空   传部门
		}else if(domain.getDwbmBz4Query().equals("D")){
			map.put("pcJgbm4Query", domain.getPcJgbm4Query());//D  部门空    传单位
		}
		
		map.put("dwbmBz4Query", domain.getDwbmBz4Query());
		map.put("pcrCzyDjxh4Query", domain.getPcrCzyDjxh4Query());
		map.put("fhrDjxh4Query", domain.getFhrDjxh());
		//String a = domain.getFhrDjxh();
		map.put("fhrMc4Query", domain.getFhrMc());
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		map.put("clhm4Query", domain.getClhm4Query());
		map.put("sjxm4Query", domain.getSjxm4Query());
		map.put("ddbh4Query", domain.getDdbh4Query());
		map.put("pcdh4Query", domain.getPcdh4Query());
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", 99999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyClgzPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		
		for(BaseBusinessDomain e:dataList){
			HyClgzDomain element = (HyClgzDomain)e;
			Date d = SysDateUtil.parse(element.getPcrq(), "yyyy-MM-dd HH:mm:ss");
			String dateStr = SysDateUtil.format(d, SysDateUtil.DATEFORMAT);
			element.setPcrq(dateStr);
		}
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseDomain;	
		HyClgz bo = new HyClgz();
		
			BeanUtils.copyProperties(bo,domain);
			
			SysEncodeUtil.decodeURL(domain);

			
			Date d = SysDateUtil.parse(domain.getRq(), "yyyy年MM月dd日 HH时mm分ss秒");
			String dateStr = SysDateUtil.format(d, SysDateUtil.TIME_DATETIME_FORMAT);
			bo.setRq(dateStr);
			
			bo.setLlbz("Y");
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			
			businessSqlMapClientTemplate.insert("insertHyClgz", bo);
		
	}



	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyClgzDomain domain = (HyClgzDomain)baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyClgzDomain dom = (HyClgzDomain) getHyClgzPcxx(domain.getPcDjxh());
			if(dom != null){
				BeanUtils.copyProperties(domain, dom);
			}
			List<BaseBusinessDomain> list = this.getHyClgzList(domain.getPcDjxh());

			domain.setDataList(list);
		}
	}

	public BaseBusinessDomain getHyClgzPcxx(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("pcDjxh", pcDjxh);
		HyClgzDomain domain = (HyClgzDomain) businessSqlMapClientTemplate.queryForObject("getHyClgzPcxx", map);
		return domain;
	}


	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> getHyClgzList(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("pcDjxh", pcDjxh);
		List<BaseBusinessDomain> list = businessSqlMapClientTemplate.queryForList("getHyClgzList", map);
		return list;
	}
	
	public void deleteClgzByKey(String clgzDjxh)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("clgzDjxh", clgzDjxh);
		businessSqlMapClientTemplate.update("deleteHyClgzByKey", map);
	}
	public HyClgzDomain getClgzByKey(String clgzDjxh)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("clgzDjxh", clgzDjxh);
		return (HyClgzDomain)businessSqlMapClientTemplate.queryForObject("getHyClgz", map);
	}
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)throws Exception {

		return null;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception {

	}

}
