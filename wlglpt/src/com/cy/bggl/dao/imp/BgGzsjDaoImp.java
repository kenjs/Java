package com.cy.bggl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgGzsjDao;
import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.common.bo.BgGzsj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DAOIMP for 办公-工作时间.
 * 
 * @author HJH
 */

@Repository
public class BgGzsjDaoImp implements BgGzsjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getJgbm())){
			map.put("jgbm", domain.getJgbm());
		}
		if(StringUtils.isNotBlank(domain.getYxqZ())&&"1".equals(domain.getYxqZ())){
			map.put("yxqZ", "1");
		}
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgGzsjRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzsjPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getJgbm())){
			map.put("jgbm", domain.getJgbm());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgGzsjAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain) baseDomain;
		BgGzsj bo = new BgGzsj();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		BgGzsjDomain dom = (BgGzsjDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setJgbm(domain.getJgbm());
			bo.setYxqQ(domain.getYxqQ());
			bo.setYxqZ(StringUtils.isBlank(domain.getYxqZ())?"9999-12-31":domain.getYxqZ());
			bo.setAmSbsjS(domain.getAmSbsjS());
			bo.setAmSbsjF(domain.getAmSbsjF());
			bo.setPmSbsjS(domain.getPmSbsjS());
			bo.setPmSbsjF(domain.getPmSbsjF());

			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			
			businessSqlMapClientTemplate.update("updateBgGzsjYxq1ByKey", bo);
			businessSqlMapClientTemplate.update("updateBgGzsjYxq2ByKey", bo);
			businessSqlMapClientTemplate.delete("updateBgGzsjYxq3ByKey", bo);
			businessSqlMapClientTemplate.update("insertBgGzsj", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			if(StringUtils.isBlank(domain.getYxqZ())){
				bo.setYxqZ("9999-12-31");
			}
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateBgGzsjYxq1ByKey", bo);
			businessSqlMapClientTemplate.update("updateBgGzsjYxq2ByKey", bo);
			businessSqlMapClientTemplate.delete("updateBgGzsjYxq3ByKey", bo);
			businessSqlMapClientTemplate.insert("insertBgGzsj", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("jgbm", domain.getJgbm());
		map.put("yxqQ", domain.getYxqQ());

		domain = (BgGzsjDomain)businessSqlMapClientTemplate.queryForObject("selectBgGzsjByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("jgbm", domain.getJgbm());
		map.put("yxqQ", domain.getYxqQ());

		businessSqlMapClientTemplate.update("deleteBgGzsjByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgGzsjDomain domain = (BgGzsjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getJgbm())){
			BgGzsjDomain dom = (BgGzsjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}
	
	public int queryBgGzsjCount(BgGzsjDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("jgbm", domain.getJgbm());
		map.put("yxqQ", domain.getYxqQ());
		map.put("yxqZ", domain.getYxqZ());
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgGzsjLsCount", map))).intValue();
		return count;
	}
}
