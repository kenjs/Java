package com.cy.hygl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.HyHwqs;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyHwqsDao;
import com.cy.hygl.domain.HyHwqsDomain;

/**
 * The DAOIMP for 货运-货物签收.
 * 
 * @author HJH
 */

@Repository
public class HyHwqsDaoImp implements HyHwqsDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyHwqsDomain domain = (HyHwqsDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		if(StringUtils.isNotBlank(domain.getPcdh())){
			map.put("pcdh", "%"+domain.getPcdh()+"%");
		}
		if(StringUtils.isNotBlank(domain.getHdbh())){
			map.put("hdbh", "%"+domain.getHdbh()+"%");
		}
		if(StringUtils.isNotBlank(domain.getHwMc())){
			map.put("hwmc", "%"+domain.getHwMc()+"%");
		}
		if(StringUtils.isNotBlank(domain.getShrMc())){
			map.put("shrmc", "%"+domain.getShrMc()+"%");
		}
		
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyHwqsRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwqsPage", map,start,pagSize);
		List<BaseBusinessDomain>  list=new ArrayList<BaseBusinessDomain>();
		if(StringUtils.isNotBlank(domain.getZt())){
			if(domain.getZt().equals("1")){
				for (BaseBusinessDomain baseBusinessDomain : dataList) {
					HyHwqsDomain hyDomain=(HyHwqsDomain)baseBusinessDomain;
					if(StringUtils.isBlank(hyDomain.getHwqsDjxh())){
						list.add(baseBusinessDomain);
					}
				}
			}
			else{
				for (BaseBusinessDomain baseBusinessDomain : dataList) {
					HyHwqsDomain hyDomain=(HyHwqsDomain)baseBusinessDomain;
					if(StringUtils.isNotBlank(hyDomain.getHwqsDjxh())){
						list.add(baseBusinessDomain);
					}
				}
			}
			dataList.removeAll(list);
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyHwqsDomain domain = (HyHwqsDomain) baseDomain;
		//SysEncodeUtil.decodeURL(domain);
		//SysEncodeUtil.conGBK2ISO(domain);
		// 分页相关
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		if(StringUtils.isNotBlank(domain.getPcdh())){
			map.put("pcdh", "%"+domain.getPcdh()+"%");
		}
		if(StringUtils.isNotBlank(domain.getHdbh())){
			map.put("hdbh", "%"+domain.getHdbh()+"%");
		}
		if(StringUtils.isNotBlank(domain.getHwMc())){
			map.put("hwmc", "%"+domain.getHwMc()+"%");
		}
		if(StringUtils.isNotBlank(domain.getShrMc())){
			map.put("shrmc", "%"+domain.getShrMc()+"%");
		}
		
		//map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwqsAll", map);
		List<BaseBusinessDomain>  list=new ArrayList<BaseBusinessDomain>();
		if(StringUtils.isNotBlank(domain.getZt())){
			if(domain.getZt().equals("1")){
				for (BaseBusinessDomain baseBusinessDomain : dataList) {
					HyHwqsDomain hyDomain=(HyHwqsDomain)baseBusinessDomain;
					if(StringUtils.isBlank(hyDomain.getHwqsDjxh())){
						list.add(baseBusinessDomain);
					}
				}
			}
			else{
				for (BaseBusinessDomain baseBusinessDomain : dataList) {
					HyHwqsDomain hyDomain=(HyHwqsDomain)baseBusinessDomain;
					if(StringUtils.isNotBlank(hyDomain.getHwqsDjxh())){
						list.add(baseBusinessDomain);
					}
				}
			}
			dataList.removeAll(list);
		}
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyHwqsDomain domain = (HyHwqsDomain) baseDomain;
		HyHwqs bo = new HyHwqs();
		HyHwqsDomain dom=null;
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		if(StringUtils.isNotBlank(domain.getHwqsDjxh())){
			dom = (HyHwqsDomain) this.getDomainByKey(domain);
		}
		 

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setPcDjxh(domain.getPcDjxh());
			bo.setWfhdjxh(domain.getWfhdjxh());
			bo.setDdDjxh(domain.getDdDjxh());
			bo.setXh(domain.getXh());
			bo.setQsrq(domain.getQsrq());
			bo.setQsrmc(domain.getQsrmc());
			bo.setLxdh(domain.getLxdh());
			bo.setSfzh(domain.getSfzh());
			bo.setBz(domain.getBz());
			
			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());


			businessSqlMapClientTemplate.update("updateHyHwqsByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setCjr(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.insert("insertHyHwqs", bo);
			this.HyHwQsHxCl(domain,0);
			
		}
	}
	
	public void HyHwQsHxCl(HyHwqsDomain domain,int i){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("code", i);
		map.put("sm", "");
		 businessSqlMapClientTemplate.queryForObject("HyHwQsHxCl",map);
		 String sm=(String)map.get("sm");
		 if(StringUtils.isNotBlank(sm)){
			 throw new DiyServiceException(SysEncodeUtil.ISO2GBK(sm));
		 }
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyHwqsDomain domain = (HyHwqsDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hwqsDjxh", domain.getHwqsDjxh());

		domain = (HyHwqsDomain)businessSqlMapClientTemplate.queryForObject("selectHyHwqsByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyHwqsDomain domain = (HyHwqsDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hwqsDjxh", domain.getHwqsDjxh());

		businessSqlMapClientTemplate.update("deleteHyHwqsByKey", map);
		this.HyHwQsHxCl(domain,1);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyHwqsDomain domain = (HyHwqsDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHwqsDjxh())){
			HyHwqsDomain dom = (HyHwqsDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
		domain.setQsrq(SysDateUtil.getCurrentDate());

	}
}
