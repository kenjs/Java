package com.cy.bggl.dao.imp;

import java.util.Date;
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
import com.cy.framework.util.SysToolsUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.BgTzgg;
import com.cy.common.bo.BgTzggFj;
import com.cy.common.bo.BgTzggYyjl;
import com.cy.bggl.dao.BgTzggDao;
import com.cy.bggl.domain.BgGzlxDomain;
import com.cy.bggl.domain.BgTzggDomain;
import com.cy.bggl.domain.BgTzggYyjlDomain;

/**
 * The DAOIMP for 办公-通知公告.
 * 
 * @author HJH
 */

@Repository
public class BgTzggDaoImp implements BgTzggDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	public static final String TIME_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getCjrCzyDjxh())){
			map.put("cjrCzyDjxh", domain.getCjrCzyDjxh());
		}
		if(StringUtils.isNotBlank(domain.getZt())){
			String s = domain.getZt().trim();
			map.put("zt","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgTzggRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgTzggPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getCjrCzyDjxh())){
			map.put("cjrCzyDjxh", domain.getCjrCzyDjxh());
		}
		if(StringUtils.isNotBlank(domain.getZt())){
			String s = domain.getZt().trim();
			map.put("zt","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgTzggAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		BgTzgg bo = new BgTzgg();
		int fjxh = 1;//附件序号
		BgTzggFj boFj=null;

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		BgTzggDomain dom = (BgTzggDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setJgbm(domain.getJgbm());
			bo.setFbrq(SysDateUtil.getCurrentDate());
			bo.setBcztDm(domain.getBcztDm());
			bo.setZt(domain.getZt());
			bo.setNr(domain.getNr());
			if(!"Y".equals(domain.getXjgxbz()))
				bo.setXjgxbz("N");
			else
				bo.setXjgxbz(domain.getXjgxbz());

			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateBgTzggByKey", bo);
			
			//修改操作时，判断是否需要添加附件，需要时则获取数据库中最大的附件序号
			if(domain.getUploadNameList().size() >0){
				fjxh = this.getMaxFjxh(domain);
			}
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setFbrq(SysDateUtil.getCurrentDate());
			if(!"Y".equals(domain.getXjgxbz()))
				bo.setXjgxbz("N");
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertBgTzgg", bo);
			
			domain.setTzggXh(bo.getTzggXh());
		}
		
		//保存附件
		for (int i = 0; i < domain.getUploadNameList().size(); i++) {
			boFj=new BgTzggFj();
			boFj.setTzggXh(domain.getTzggXh());
			boFj.setXh(String.valueOf(fjxh+i));
			boFj.setFjmc(domain.getUploadNameList().get(i));
			boFj.setYxbz("Y");
			boFj.setFjnr(domain.getUploadValueList().get(i));
			businessSqlMapClientTemplate.insert("insertBgTzggFj", boFj);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("tzggXh", domain.getTzggXh());

		domain = (BgTzggDomain)businessSqlMapClientTemplate.queryForObject("selectBgTzggByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("tzggXh", domain.getTzggXh());

		businessSqlMapClientTemplate.update("deleteBgTzggByKey", map);
	}
	
	/**
	 * 删除附件
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void deleteFj(BgTzggDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("tzggXh", domain.getTzggXh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteBgTzggFjByKey", map);
	}
	/**
	 * 查询附件
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void queryFjByKey(BgTzggDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("tzggXh", domain.getTzggXh());
		map.put("xh", domain.getXh());
		BgTzggDomain dom = (BgTzggDomain)businessSqlMapClientTemplate.queryForObject("selectBgTzggFjByKey", map);
		if(dom !=null){
			domain.setFjmc(dom.getFjmc());
			domain.setFjnr(dom.getFjnr());
		}
	}
	
	/**
	 * 查询附件列表
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgTzggDomain> queryFj(BgTzggDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("tzggXh", domain.getTzggXh());

		List<BgTzggDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgTzggFjList", map);		
		return dataList;
	}
	
	public void saveMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		BgTzggYyjl bo = new BgTzggYyjl();
		BgTzggYyjlDomain dom=(BgTzggYyjlDomain) this.getYyjlDomainByKey(domain.getTzggXh(),user.czyDjxh);
		if(null!=dom){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setTzggXh(domain.getTzggXh());
			bo.setCzyDjxh(user.getCzyDjxh());
			bo.setCkrq(SysDateUtil.format(new Date(), TIME_DATETIME_FORMAT));

			
			businessSqlMapClientTemplate.update("updateBgTzggYyjlByKey", bo);
		}else{
			bo.setTzggXh(domain.getTzggXh());
			bo.setCzyDjxh(user.getCzyDjxh());
			bo.setCkrq(SysDateUtil.format(new Date(), TIME_DATETIME_FORMAT));
			businessSqlMapClientTemplate.insert("insertBgTzggYyjl", bo);
		}
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgTzggDomain domain = (BgTzggDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getTzggXh())){
			BgTzggDomain dom = (BgTzggDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}
	@SuppressWarnings("unchecked")
	public int getMaxFjxh(BgTzggDomain domain) throws Exception {
		int fjxh = 0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		if(StringUtils.isNotBlank(domain.getTzggXh())){
			map.put("tzggXh", domain.getTzggXh());
		}else{
			return 1;
		}
		
		fjxh = ((Integer) businessSqlMapClientTemplate.queryForObject("getMaxFjxhByTzggXh", map)).intValue();		
		return fjxh+1;
	}
	
	//办公-通知公告-已阅记录
	public BaseBusinessDomain getYyjlDomainByKey(String tzggXh,String czyDjXh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("tzggXh", tzggXh);
		map.put("czyDjxh",czyDjXh);

		BgTzggYyjlDomain domain = (BgTzggYyjlDomain)businessSqlMapClientTemplate.queryForObject("selectBgTzggYyjlByKey", map);
		return domain;
	}

}
