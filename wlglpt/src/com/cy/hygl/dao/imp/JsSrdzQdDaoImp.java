package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.JsKpsq;
import com.cy.common.bo.JsKpsqMx;
import com.cy.common.bo.JsSrdzQd;
import com.cy.common.bo.JsSrdzQdMx;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.JsSrdzQdDao;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.domain.JsSrdzQdDomain;

/**
 * The DAOIMP for 结算-收入对帐-清单.
 * 
 * @author HJH
 */

@Repository
public class JsSrdzQdDaoImp implements JsSrdzQdDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
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
		if(StringUtils.isNotBlank(domain.getDjrq())){
			map.put("rqQ", domain.getDjrq());
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqZ", domain.getRqZ());
		}
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getJsSrdzQdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsSrdzQdPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
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
		if(StringUtils.isNotBlank(domain.getDjrq())){
			map.put("rqQ", domain.getDjrq());
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqZ", domain.getRqZ());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsSrdzQdAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		JsSrdzQd bo = new JsSrdzQd();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		JsSrdzQdDomain dom = (JsSrdzQdDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setKhDjxh(domain.getKhDjxh());
			bo.setQdmc(domain.getQdmc());
			bo.setDzqdHzfsDm(domain.getDzqdHzfsDm());
			bo.setHeJe(domain.getHeJe());
			bo.setYfJe(Double.valueOf(0));
			bo.setWfJe(domain.getHeJe());
			bo.setWsqKpJe(domain.getHeJe());
			//bo.setDjJgbm(domain.getDjJgbm());
			//bo.setSsJgbm(domain.getSsJgbm());
			bo.setSfKpBz(domain.getSfKpBz());
			bo.setYxbz("Y");

			businessSqlMapClientTemplate.update("updateJsSrdzQdByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setDzqdHzfsDm("1"); // 暂时默认为1：按订单
			bo.setYfJe(Double.valueOf(0));
			bo.setWfJe(domain.getHeJe());
			bo.setWsqKpJe(domain.getHeJe());
			bo.setDjrCzyDjxh(user.czyDjxh);
			bo.setDjrq(SysDateUtil.getCurrentDate());
			bo.setYxbz("Y");

			businessSqlMapClientTemplate.insert("insertJsSrdzQd", bo);
			
			domain.setQdDjxh(bo.getQdDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("qdDjxh", domain.getQdDjxh());

		domain = (JsSrdzQdDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzQdByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", domain.getQdDjxh());

		businessSqlMapClientTemplate.update("deleteJsSrdzQdByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getQdDjxh())){
			JsSrdzQdDomain dom = (JsSrdzQdDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public void saveDzQdMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		JsSrdzQdMx bo = new JsSrdzQdMx();

		JsSrdzQdDomain dom = (JsSrdzQdDomain) this.getDzQdMxDomainByKey(domain);

		if(dom != null){
			return;
		}else{
			bo.setQdDjxh(domain.getQdDjxh());
			bo.setYwDjxh(domain.getYwDjxh());
			bo.setYwMxXh(domain.getYwMxXh());
			bo.setYwLydm(domain.getYwLydm());

			businessSqlMapClientTemplate.insert("insertJsSrdzQdMx", bo);
		}
	}
	public void saveDzQdMxTempDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		JsSrdzQdMx bo = new JsSrdzQdMx();

		JsSrdzQdDomain dom = (JsSrdzQdDomain) this.getDzQdMxTempDomainByKey(domain);

		if(dom != null){
			return;
		}else{
			bo.setQdDjxh(domain.getQdDjxh());
			bo.setYwDjxh(domain.getYwDjxh());
			bo.setYwMxXh(domain.getYwMxXh());
			bo.setYwLydm(domain.getYwLydm());
			bo.setCzRq(SysDateUtil.getCurrentDate());
			businessSqlMapClientTemplate.insert("insertJsSrdzQdMxTemp", bo);
		}
	}
	public BaseBusinessDomain getDzQdMxDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("ywMxXh", domain.getYwMxXh());

		domain = (JsSrdzQdDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzQdMxByKey", map);
		return domain;
	}
	public BaseBusinessDomain getDzQdMxTempDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("ywMxXh", domain.getYwMxXh());

		domain = (JsSrdzQdDomain)businessSqlMapClientTemplate.queryForObject("selectJsSrdzQdMxTempByKey", map);
		return domain;
	}

	public void deleteDzQdMx(String qdDjxh,String ywDjxh, String ywMxXh, UserDomain userDomain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);

		businessSqlMapClientTemplate.update("deleteJsSrdzQdMxByKey", map);
	}
	public void deleteDzQdMxTemp(String qdDjxh,String ywDjxh, String ywMxXh, UserDomain userDomain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);

		businessSqlMapClientTemplate.update("deleteJsSrdzQdMxTempByKey", map);
	}
	//对账清单明细
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryDzQdMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("dzqdHzfsDm", domain.getDzqdHzfsDm());
		
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsSrdzQdMx", map);
		return dataList;
	}
	//对账清单明细temp 临时表数据
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryDzQdMxTemp(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("dzqdHzfsDm", domain.getDzqdHzfsDm());
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsSrdzQdMxTemp", map);
		return dataList;
	}
	//已对账的
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryYdz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		//dwbmbz,dwbmDm
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("dwbmbz", "B");
			map.put("dwbmDm", domain.getDjJgbm());
		}
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("dwbmbz", "D");
			map.put("dwbmDm", domain.getSsJgbm());
		}
		
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		
		if(StringUtils.isNotBlank(domain.getDdbh())){
			map.put("ddbh", domain.getDdbh());
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectYdzList", map);
		return dataList;
	}
	
	public void saveQdMxDomainByTemp(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);

		businessSqlMapClientTemplate.insert("saveQdMxDomainByTemp", map);
	}
	public void deleteDzQdMxTempByQdDjxh(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);

		businessSqlMapClientTemplate.delete("deleteDzQdMxTempByQdDjxh", map);
	}
	
	public void updateJsSrDzDd(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);

		businessSqlMapClientTemplate.update("updateJsSrDzDd", map);
	}
	public void deleteDzQdMxUpdateSrDzDdOfIsNull(String ywDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ywDjxh", ywDjxh);

		businessSqlMapClientTemplate.update("deleteDzQdMxUpdateSrDzDdOfIsNull", map);
	}
	//更新合计金额
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		JsSrdzQd bo = new JsSrdzQd();

		JsSrdzQdDomain dom = (JsSrdzQdDomain) this.getDomainByKey(domain);

		if(dom == null)
			return;
		BeanUtils.copyProperties(bo, dom);
		bo.setHeJe(domain.getHeJe());
		//bo.setYfJe(Double.valueOf(0));
		//bo.setWfJe(domain.getHeJe());

		businessSqlMapClientTemplate.update("updateJsSrdzQdByKey", bo);
	}
	
	@SuppressWarnings("unchecked")
	public List<HyZyglFydjDomain> queryFydjList(JsSrdzQdDomain domain, UserDomain userDomain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("djJgbm", domain.getDjJgbm());
		//map.put("ddbh", domain.getDdbh());
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		
		List<HyZyglFydjDomain> fydjList = businessSqlMapClientTemplate.queryForList("queryFydjList", map);
		return fydjList;
	}
	
	public void updateQdHeJeByKey(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		businessSqlMapClientTemplate.update("updateQdHeJeByKey", map);
	}
	
	public void updateJsSrdzQdxhToNullByQdDjxh(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		businessSqlMapClientTemplate.update("updateJsSrdzQdxhToNullByQdDjxh", map);
	}
	
	public Double calQdHjJe(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", qdDjxh);
		Object value = businessSqlMapClientTemplate.queryForObject("calQdHjJe", map);
		
		if (value != null) {
			return (Double)value;
		}
		return 0.0;
	}
	public List<JsSrdzQdDomain> selectSrdzQdMxWhenWlss(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("qdDjxh", domain.getQdDjxh());
		
		List<JsSrdzQdDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectSrdzQdMxWhenWlss", map);
		
		return dataList;
	}
	
	public void cwYsfSrdz(BaseBusinessDomain baseDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("qdmc", SysEncodeUtil.GBK2ISO("清单名称："));
		map.put("kh", SysEncodeUtil.GBK2ISO("，客户："));
		map.put("ykp", SysEncodeUtil.GBK2ISO("，已申请开票金额："));
		map.put("wkp", SysEncodeUtil.GBK2ISO("，未申请开票金额："));
		businessSqlMapClientTemplate.insert("cwYsfSrdz",map);
	}
	
	public void saveKp(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain) baseDomain;
		JsKpsq bo = new JsKpsq();
		JsKpsqMx boMx = new JsKpsqMx();
		
		bo.setKpsqDjxh(domain.getKpsqDjxh());
		bo.setKpsqfsDm("1");
		bo.setSqKpjeHj(domain.getHeJe());
		bo.setSqKprq(SysDateUtil.getCurrentDate());
		bo.setDjJgbm(domain.getDjJgbm());
		bo.setSsJgbm(domain.getSsJgbm());
		bo.setFpkjJe(0d);		
		
		bo.setFpkjbz("N");
		bo.setDjrCzyDjxh(user.czyDjxh);
		bo.setDjrq(SysDateUtil.getCurrentDate());
		bo.setSpbz("N");
		bo.setKpDwJgMc(domain.getJsdw());				//开票单位
		bo.setKpDwJgbm(user.getGsbm());
		bo.setYxbz("Y");

		businessSqlMapClientTemplate.insert("insertJsKpsq", bo);
		
		boMx.setKpsqMxflDm("1");//对帐清单		
		boMx.setYwDjxh(domain.getQdDjxh());
		boMx.setSqKpje(domain.getHeJe());
		boMx.setYxbz("Y");
		boMx.setKpsqDjxh(domain.getKpsqDjxh());
		
		businessSqlMapClientTemplate.insert("insertJsKpsqMx", boMx);
	}
	
	public int checkQdDel(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("qdDjxh", qdDjxh);
		int res = (Integer) businessSqlMapClientTemplate.queryForObject("checkQdDelete", map);
		return res;
	}
	
	public int checkQDCwInfo(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("qdDjxh", qdDjxh);
		Integer res = (Integer) businessSqlMapClientTemplate.queryForObject("checkQdCwInfo", map);
		
		return res == null ? 0 : res;
	}
	
	public void deleCwInfo(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("qdDjxh", qdDjxh);
		
		businessSqlMapClientTemplate.update("deleCwInfo", map);
	}
}
