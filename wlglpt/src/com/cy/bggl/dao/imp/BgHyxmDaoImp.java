package com.cy.bggl.dao.imp;

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
import com.cy.common.bo.BgHyxm;
import com.cy.common.bo.BgHyxmFj;
import com.cy.bggl.dao.BgHyxmDao;
import com.cy.bggl.domain.BgHyxmDomain;

/**
 * The DAOIMP for 办公-行业新闻.
 * 
 * @author HJH
 */

@Repository
public class BgHyxmDaoImp implements BgHyxmDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgHyxmDomain domain = (BgHyxmDomain) baseDomain;
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
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgHyxmRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgHyxmPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgHyxmDomain domain = (BgHyxmDomain) baseDomain;
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
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgHyxmAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgHyxmDomain domain = (BgHyxmDomain) baseDomain;
		BgHyxm bo = new BgHyxm();
		int fjxh = 1;//附件序号
		BgHyxmFj boFj=null;

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		BgHyxmDomain dom = (BgHyxmDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setJgbm(domain.getJgbm());
			bo.setFbrq(SysDateUtil.getCurrentDate());
			bo.setBcztDm(domain.getBcztDm());
			bo.setLy(domain.getLy());
			bo.setZt(domain.getZt());
			bo.setNr(domain.getNr());
			if(!"Y".equals(domain.getXjgxbz()))
				bo.setXjgxbz("N");
			else
				bo.setXjgxbz(domain.getXjgxbz());

			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateBgHyxmByKey", bo);
			
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

			businessSqlMapClientTemplate.insert("insertBgHyxm", bo);
			domain.setHyxmDjxh(bo.getHyxmDjxh());
		}
		//保存附件
		for (int i = 0; i < domain.getUploadNameList().size(); i++) {
			boFj=new BgHyxmFj();
			boFj.setHyxmDjxh(domain.getHyxmDjxh());
			boFj.setXh(String.valueOf(fjxh+i));
			boFj.setFjmc(domain.getUploadNameList().get(i));
			boFj.setYxbz("Y");
			boFj.setFjnr(domain.getUploadValueList().get(i));
			businessSqlMapClientTemplate.insert("insertBgHyxmFj", boFj);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgHyxmDomain domain = (BgHyxmDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hyxmDjxh", domain.getHyxmDjxh());

		domain = (BgHyxmDomain)businessSqlMapClientTemplate.queryForObject("selectBgHyxmByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgHyxmDomain domain = (BgHyxmDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("hyxmDjxh", domain.getHyxmDjxh());

		businessSqlMapClientTemplate.update("deleteBgHyxmByKey", map);
	}
	/**
	 * 删除附件
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void deleteFj(BgHyxmDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("hyxmDjxh", domain.getHyxmDjxh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteBgHyxmFjByKey", map);
	}
	/**
	 * 查询附件
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void queryFjByKey(BgHyxmDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("hyxmDjxh", domain.getHyxmDjxh());
		map.put("xh", domain.getXh());
		BgHyxmDomain dom = (BgHyxmDomain)businessSqlMapClientTemplate.queryForObject("selectBgHyxmFjByKey", map);
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
	public List<BgHyxmDomain> queryFj(BgHyxmDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("hyxmDjxh", domain.getHyxmDjxh());

		List<BgHyxmDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgHyxmFjList", map);		
		return dataList;
	}
	
	
	@SuppressWarnings("unchecked")
	public int getMaxFjxh(BgHyxmDomain domain) throws Exception {
		int fjxh = 0;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		if(StringUtils.isNotBlank(domain.getHyxmDjxh())){
			map.put("hyxmDjxh", domain.getHyxmDjxh());
		}else{
			return 1;
		}
		
		fjxh = ((Integer) businessSqlMapClientTemplate.queryForObject("getMaxFjxhByHyxmDjXh", map)).intValue();		
		return fjxh+1;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgHyxmDomain domain = (BgHyxmDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHyxmDjxh())){
			BgHyxmDomain dom = (BgHyxmDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
