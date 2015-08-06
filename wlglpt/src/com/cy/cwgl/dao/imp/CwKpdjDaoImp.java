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
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.CwKpdj;
import com.cy.cwgl.dao.CwKpdjDao;
import com.cy.cwgl.domain.CwKpdjDomain;

/**
 * The DAOIMP for 财务-开票登记.
 * 
 * @author HJH
 */

@Repository
public class CwKpdjDaoImp implements CwKpdjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain) baseDomain;		
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("khDjxh", domain.getKhDjxh());
		map.put("fpdm", domain.getFpdm());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		if(StringUtils.isNotBlank(domain.getKhmc())){
			map.put("khmc", SysEncodeUtil.GBK2ISO(domain.getKhmc()));
		}
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwKpdjRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwKpdjPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("fpdm", domain.getFpdm());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		if(StringUtils.isNotBlank(domain.getFhrDjxh())){
			map.put("khDjxh", domain.getFhrDjxh());
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("khmc", SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		}
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwKpdjAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain) baseDomain;
		CwKpdj bo = new CwKpdj();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		CwKpdjDomain dom = (CwKpdjDomain) this.getDomainByKey(domain);

		if(dom != null){
			bo.setKpsqDjxh(dom.getKpsqDjxh());
			bo.setKhDjxh(dom.getKhDjxh());
			bo.setFpdm(dom.getFpdm());
			bo.setFphm(dom.getFphm());
			bo.setKprCzyDjxh(user.getCzyDjxh());
			bo.setKprq(dom.getKprq());
			bo.setKpje(0-dom.getKpje());
			bo.setSl(dom.getSl());
			bo.setSe(0-dom.getSe());
			bo.setZfbz("N");
			bo.setHzbz("Y");
			bo.setLzFpdm(dom.getFpdm());
			bo.setLzFphm(dom.getFphm());
			bo.setDjJgbm(user.getBmbm());
			bo.setSsJgbm(user.getGsbm());
			
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertCwKpdj", bo);
		}
		domain.setKpDjxh(bo.getKpDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpDjxh", domain.getKpDjxh());

		domain = (CwKpdjDomain)businessSqlMapClientTemplate.queryForObject("selectCwKpdjByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpDjxh", domain.getKpDjxh());
		map.put("zfrCzyDjxh", userDomain.getCzyDjxh());
		map.put("zfRq", SysDateUtil.getSqlDate().toString());
		businessSqlMapClientTemplate.update("deleteCwKpdjByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwKpdjDomain domain = (CwKpdjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getKpDjxh())){
			CwKpdjDomain dom = (CwKpdjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	public void cwKpdjZfhxcl(String kpDjxh)
			throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("kpDjxh", kpDjxh);
		map.put("rtnCode", 0);
		map.put("errMesge", "");
		businessSqlMapClientTemplate.queryForObject("cwKpdjZfhxcl", map);
		
		Integer rtnCode = (Integer) map.get("rtnCode");
		String errMesge = (String) map.get("errMesge");
		if (rtnCode != 0 && StringUtils.isNotBlank(errMesge)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(errMesge));
		}
	}
}
