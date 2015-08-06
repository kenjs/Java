package com.cy.bggl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgRcapDao;
import com.cy.bggl.domain.BgRcapDomain;
import com.cy.common.bo.BgRcap;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DAOIMP for 办公-日程安排.
 * 
 * @author HJH
 */

@Repository
public class BgRcapDaoImp implements BgRcapDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgRcapDomain domain = (BgRcapDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgRcapRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgRcapPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgRcapAll", map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public void queryForDate(BgRcapDomain domain) throws Exception {
		String rqQ;
		String rqZ;
		if(StringUtils.isNotBlank(domain.getChangeDate())){
			 rqQ = domain.getChangeDate()+"-01";
		}else{
			rqQ = SysDateUtil.getCurrentDate().substring(0,7)+"-01";
		}
		
		rqZ = SysDateUtil.getLastDayOfMonth(rqQ);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("rqQ", rqQ);
		map.put("rqZ", rqZ);
		map.put("czyDjxh", domain.getCzyDjxh());
		
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgRcapByDate", map);
		domain.setDataList(dataList);
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgRcapDomain domain = (BgRcapDomain) baseDomain;
		BgRcap bo = new BgRcap();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		BgRcapDomain dom = (BgRcapDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setCzyDjxh(user.czyDjxh);
//			bo.setRq(domain.getRq());
			bo.setNr(domain.getNr());
			//bo.setDxFsxh(domain.getDxFsxh());
			//bo.setCkbz(domain.getCkbz());
			bo.setXgrCzyDjxh(user.czyDjxh);
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateBgRcapByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setCkbz("N");
			bo.setCzyDjxh(user.czyDjxh);
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertBgRcap", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		if(domain.getBgDjxh() != null){
			if(StringUtils.isBlank(domain.getBgDjxh())){
				return null;
			}else{
				map.put("bgDjxh", domain.getBgDjxh());
			}
		}else{
			map.put("czyDjxh", domain.getCzyDjxh());
			map.put("rq", domain.getCxrq());
		}
		
		domain = (BgRcapDomain)businessSqlMapClientTemplate.queryForObject("selectBgRcapByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("bgDjxh", domain.getBgDjxh());

		businessSqlMapClientTemplate.update("deleteBgRcapByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgRcapDomain domain = (BgRcapDomain) baseDomain;
		BgRcapDomain dom = (BgRcapDomain) this.getDomainByKey(domain);
		if(dom!=null){
			BeanUtils.copyProperties(domain, dom);
		}
	}

}
