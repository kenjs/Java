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
import com.cy.common.exception.DiyServiceException;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.common.bo.JsKpsq;
import com.cy.common.bo.JsKpsqMx;
import com.cy.hygl.dao.JsKpsqDao;
import com.cy.hygl.domain.JsKpsqDomain;

/**
 * The DAOIMP for 开票申请.
 * 
 * @author HJH
 */

@Repository
public class JsKpsqDaoImp implements JsKpsqDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
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
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getJsKpsqRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpsqPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpsqAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		JsKpsq bo = new JsKpsq();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		JsKpsqDomain dom = (JsKpsqDomain) this.getDomainByKey(domain);
		if(StringUtils.isNotBlank(domain.getYdrq())){
			String ydrq = domain.getYdrq().split("%")[0];
			domain.setYdrq(ydrq);
		}
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			//bo.setKpsqfsDm(domain.getKpsqfsDm());
			//bo.setKhDjxh(domain.getKhDjxh());
			bo.setSqKpjeHj(domain.getSqKpjeHj());
			bo.setSqKprq(domain.getSqKprq());
			bo.setBzsm(domain.getBzsm());
			bo.setShf(domain.getShf());
			bo.setShfSbh(domain.getShfSbh());
			bo.setFhf(domain.getFhf());
			bo.setFhfSbh(domain.getFhfSbh());
			bo.setYdrq(domain.getYdrq());
			bo.setKpDwJgMc(domain.getKpDwJgMc());
			bo.setDkf(domain.getDkf());
			bo.setDj(domain.getDj());
			bo.setMc(domain.getMc());
			bo.setSl(domain.getSl());

			businessSqlMapClientTemplate.update("updateJsKpsqByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setFpkjbz("N");
			bo.setDjrCzyDjxh(user.czyDjxh);
			bo.setDjrq(SysDateUtil.getCurrentDate());
			bo.setSpbz("N");
			bo.setKpDwJgbm(user.gsbm);
			bo.setYxbz("Y");

			businessSqlMapClientTemplate.insert("insertJsKpsq", bo);
		}
		domain.setKpsqDjxh(bo.getKpsqDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());

		domain = (JsKpsqDomain)businessSqlMapClientTemplate.queryForObject("selectJsKpsqByKey", map);
		if(domain != null&&StringUtils.isNotBlank(domain.getYdrq())) {
			String ydrq = domain.getYdrq().split(" ")[0];
			domain.setYdrq(ydrq);
		}
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());

		businessSqlMapClientTemplate.update("deleteJsKpsqByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getKpsqDjxh())){
			JsKpsqDomain dom = (JsKpsqDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryDzQdList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception {
		/*JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}*/
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectDzQdList");
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryJsKpsqMxList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception{
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		if(SysToolsUtil.isNullOrEmpty(domain.getKpsqDjxh())){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpsqMx",map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryJsKpsqMxTempList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception{
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		if(SysToolsUtil.isNullOrEmpty(domain.getKpsqDjxh())){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpsqMxTemp",map);
		return dataList;
	}
	
	public void deleteJsKpsqMxByKey(String kpsqmxDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpsqmxDjxh", kpsqmxDjxh);

		businessSqlMapClientTemplate.update("deleteJsKpsqMxByKey", map);
	}
	public void deleteJsKpsqMxTempByKey(String kpsqmxDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpsqmxDjxh", kpsqmxDjxh);

		businessSqlMapClientTemplate.update("deleteJsKpsqMxTempByKey", map);
	}
	
	public void saveJsKpsqMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		JsKpsqMx bo = new JsKpsqMx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		JsKpsqDomain dom = (JsKpsqDomain) this.getJsKpsqMxDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			//bo.setKpsqDjxh(domain.getKpsqDjxh());
			//bo.setQdDjxh(domain.getQdDjxh());
			if("Y".equals(domain.getDzQdXgbz())){
				bo.setSqKpje(domain.getSqKpJe());
			}else{
				if(null==dom.getSqKpJe())
					bo.setSqKpje(domain.getSqKpJe());
				else
					bo.setSqKpje(domain.getSqKpJe()+dom.getSqKpJe());
			}
			bo.setYwDjxh(dom.getQdDjxh());
			bo.setBzsm(domain.getBzsm());
			bo.setYxbz("Y");
			
			businessSqlMapClientTemplate.update("updateJsKpsqMxByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setKpsqMxflDm("1");//对帐清单
			bo.setYwDjxh(domain.getQdDjxh());
			bo.setSqKpje(domain.getSqKpJe());
			bo.setYxbz("Y");
			
			businessSqlMapClientTemplate.insert("insertJsKpsqMx", bo);
		}
	}

	public BaseBusinessDomain getJsKpsqMxDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpsqmxDjxh", domain.getKpsqmxDjxh());

		domain = (JsKpsqDomain)businessSqlMapClientTemplate.queryForObject("selectJsKpsqMxByKey", map);
		return domain;
	}
	
	public void saveJsKpsqMxTempDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		JsKpsqMx bo = new JsKpsqMx();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		JsKpsqDomain dom = (JsKpsqDomain) this.getJsKpsqMxTempDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			if("Y".equals(domain.getDzQdXgbz())){
				bo.setSqKpje(domain.getSqKpJe());
			}else{
				if(null==dom.getSqKpJe())
					bo.setSqKpje(domain.getSqKpJe());
				else
					bo.setSqKpje(domain.getSqKpJe()+dom.getSqKpJe());
			}
			bo.setYwDjxh(dom.getQdDjxh());
			bo.setBzsm(domain.getBzsm());
			bo.setYxbz("Y");
			bo.setCzrq(SysDateUtil.getCurrentDate());
			
			businessSqlMapClientTemplate.update("updateJsKpsqMxTempByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setCzrq(SysDateUtil.getCurrentDate());
			bo.setKpsqMxflDm("1");//对帐清单
			bo.setYwDjxh(domain.getQdDjxh());
			bo.setSqKpje(domain.getSqKpJe());
			bo.setYxbz("Y");
			
			businessSqlMapClientTemplate.insert("insertJsKpsqMxTemp", bo);
		}
	}

	public BaseBusinessDomain getJsKpsqMxTempDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpsqmxDjxh", domain.getKpsqmxDjxh());

		domain = (JsKpsqDomain)businessSqlMapClientTemplate.queryForObject("selectJsKpsqMxTempByKey", map);
		return domain;
	}
	
	//更新申请开票金额合计
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		JsKpsq bo = new JsKpsq();
		JsKpsqDomain dom = (JsKpsqDomain) this.getDomainByKey(domain);

		if(dom == null)
			return;
		BeanUtils.copyProperties(bo, dom);
		
		bo.setSqKpjeHj(domain.getSqKpjeHj());

		businessSqlMapClientTemplate.update("updateJsKpsqByKey", bo);
	}
	
	public void insertJsKpsqMxByTemp(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());

		businessSqlMapClientTemplate.insert("insertJsKpsqMxByTemp", map);
	}
	
	public void deleteJsKpsqMxTemp(BaseBusinessDomain baseDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());

		businessSqlMapClientTemplate.delete("deleteJsKpsqMxTemp", map);
	}
	
	public BaseBusinessDomain getJsKpsqMxDomainByXh(String kpsqDjxh,String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("kpsqDjxh", kpsqDjxh);
		map.put("qdDjxh", qdDjxh);

		JsKpsqDomain domain = (JsKpsqDomain)businessSqlMapClientTemplate.queryForObject("selectJsKpsqMxByXh", map);
		return domain;
	}
	public BaseBusinessDomain getJsKpsqMxTempDomainByXh(String kpsqDjxh,String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("kpsqDjxh", kpsqDjxh);
		map.put("qdDjxh", qdDjxh);

		JsKpsqDomain domain = (JsKpsqDomain)businessSqlMapClientTemplate.queryForObject("selectJsKpsqMxTempByXh", map);
		return domain;
	}
	
	public void callProKpsqHxcl(String kpsqDjxh, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kpsqDjxh", kpsqDjxh);
		map.put("bmbm", userDomain.getBmbm());
		map.put("czyDjxh", userDomain.getCzyDjxh());
		map.put("retCode", 0);
		map.put("retMsg", "");
		businessSqlMapClientTemplate.queryForObject("callProKpsqHxcl", map);
		if (map.get("retCode") != null && (Integer)map.get("retCode") != 0) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("retMsg")));
		}
	}

	
	public List<JsKpsqDomain> querySrKpMx(JsKpsqDomain domain) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("khDjxh", domain.getKhDjxh());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		List<JsKpsqDomain>  list=businessSqlMapClientTemplate.queryForList("querySrKpMxList",map);
		return list;
	}

	
	public void savaSrKpMxTemp(JsKpsqMx bo) throws Exception {
		businessSqlMapClientTemplate.insert("insertJsKpsqMxTemp", bo);
	}

	
	public List<JsKpsqDomain> querySrKpsqMxList(JsKpsqDomain domain,int i)
			throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		if(StringUtils.isNotBlank(domain.getKpsqDjxh())){
			map.put("kpSqDjxh", domain.getKpsqDjxh());
		}
		List<JsKpsqDomain>  list=null;
		if(i==1){
			 list=businessSqlMapClientTemplate.queryForList("querySrKpMxTempList",map);
		}
		else{
		     list=businessSqlMapClientTemplate.queryForList("querySrKpTempList",map);
		}
		return list;
	}

	
	public void deleteSqKpTemp(JsKpsqMx bo) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(bo.getYwDjxh())){
			map.put("ywDjxh", bo.getYwDjxh());
		}
		if(StringUtils.isNotBlank(bo.getKpsqDjxh())){
			map.put("kpSqDjxh", bo.getKpsqDjxh());
		}
		businessSqlMapClientTemplate.update("deleteSqKpTempByDjxh",map);
		
	}

	
	public void savaSrKpMx(JsKpsqMx bo) throws Exception {
		businessSqlMapClientTemplate.insert("insertJsKpsqMx", bo);
		
	}

	
	public int checkSrSpMx(JsKpsqMx bo) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("kpDjxh", bo.getKpsqDjxh());
		map.put("ywDjxh", bo.getYwDjxh());
		int count=(Integer)businessSqlMapClientTemplate.queryForObject("queryCountSrKpMx",map);
		return count;
	}

	
	public void deleteSrKpMx(JsKpsqMx bo) throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		map.put("kpDjxh", bo.getKpsqDjxh());
		map.put("ywDjxh", bo.getYwDjxh());
		businessSqlMapClientTemplate.update("deleteSrKpMx",map);
	}

	
	public void updateSrKpJeByDjxh(JsKpsqDomain domain,double zje) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(domain.getKpsqDjxh())){
			map.put("kpDjxh", domain.getKpsqDjxh());
			map.put("zje", zje);
			businessSqlMapClientTemplate.update("updateSrKpJeByDjxh",map);
		}
		
	}
	
}
