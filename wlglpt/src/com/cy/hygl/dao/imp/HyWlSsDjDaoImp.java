package com.cy.hygl.dao.imp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyWlssdj;
import com.cy.common.bo.HyWlssdjMx;
import com.cy.common.bo.HyWlssdjZp;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyWlSsDjDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyWlssdjDomain;
import com.cy.hygl.domain.HyWlssdjMxDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The DAOIMP for 物流损失登记.
 * 
 * @author HJH
 */

@Repository
public class HyWlSsDjDaoImp implements HyWlSsDjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = 0;
		int pagSize = 99999999;

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("ssJgbm", domain.getSsJgbm());
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyWlssCkRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyWlssCkPage",map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();


		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("ssJgbm", domain.getSsJgbm());
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			map.put("khDjxh", domain.getKhDjxh());
		}
		map.put("pcrqQ", domain.getPcrqQ());
		map.put("pcrqZ", domain.getPcrqZ());
		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyWlssCkAll", map);
		return dataList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<HyZyglFydjDomain> selectSjList(String ddDjxh,String xh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		map.put("ddDjxh", ddDjxh);
		map.put("xh", xh);
		// 检索数据
		List<HyZyglFydjDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectSjForWlss", map);
		return dataList;
	}
	
	@Transactional
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		HyWlssdjDomain hyDomain=null;
		HyWlssdj bo = new HyWlssdj();
		if(domain.getWlssDjxh()!=null&&!"".equals(domain.getWlssDjxh())){
			hyDomain=(HyWlssdjDomain)this.getDomainByKey(domain);
		}
		if(hyDomain!=null){
			BeanUtils.copyProperties(bo, hyDomain);
			bo.setZl(domain.getSsZl());
			bo.setTj(domain.getSsTj());
			bo.setSl(domain.getSsSl());
			bo.setJsSl(domain.getJsSl());
			bo.setDdDjxh(domain.getDdDjxh());
			bo.setHwmxxh(domain.getHwmxxh());
			bo.setWlssyyWhXh(domain.getWlssyyWhXh());
			//bo.setWlssclfsDm(domain.getWlssclfsDm());
			bo.setHjSr(Double.valueOf(domain.getHjSr()));
			bo.setKhDjxh(domain.getKhDjxh());
			bo.setPcDjxh(domain.getPcDjxh());
			bo.setWfhDjxh(domain.getWfhDjxh());
			bo.setBz(domain.getBz());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setWlssLybz(domain.getWlssLybz());
			bo.setSpbcbz(domain.getSpbcbz());	
			
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateHyWlssdjByKey",bo);
		}
		else{
			bo.setWlssyyWhXh(domain.getWlssyyWhXh());
			bo.setHjSr(Double.valueOf(domain.getHjSr()));
			bo.setKhDjxh(domain.getKhDjxh());
			bo.setPcDjxh(domain.getPcDjxh());
			bo.setWfhDjxh(domain.getWfhDjxh());
			bo.setZl(domain.getSsZl());
			bo.setTj(domain.getSsTj());
			bo.setSl(domain.getSsSl());
			bo.setDdDjxh(domain.getDdDjxh());
			bo.setHwmxxh(domain.getHwmxxh());
			bo.setJsSl(domain.getJsSl());
			bo.setDdDjxh(domain.getDdDjxh());
			bo.setHwmxxh(domain.getHwmxxh());
			bo.setWlssLybz(domain.getWlssLybz());
			bo.setSpbcbz(domain.getSpbcbz());
			
			bo.setBz(domain.getBz());
			bo.setPcygCzyDjxh("");
			bo.setSpbz("N");
			bo.setWsspztDm(String.valueOf(0));
			bo.setWsSpxh("");
			bo.setSsJgbm(user.getGsbm());
			bo.setDjJgbm(user.getBmbm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.insert("insertHyWlssdj",bo);
		}
			
			domain.setWlssDjxh(bo.getWlssDjxh());
	}

	@SuppressWarnings("unchecked")
	public List<HyWlssdjMxDomain> getHyWlssdjMxList(String wlssDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("wlssDjxh", wlssDjxh);
		
		List<HyWlssdjMxDomain> hyWlssdjMxList = businessSqlMapClientTemplate.queryForList("getHyWlssdjMxList", map);
		return hyWlssdjMxList;
		
	}

	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wlssDjxh", domain.getWlssDjxh());

		HyWlssdjDomain main = (HyWlssdjDomain)businessSqlMapClientTemplate.queryForObject("selectHyWlssdjByKey", map);
		return main;
	}
	public BaseBusinessDomain selectHyPcHwxxWhenWlss(BaseBusinessDomain baseDomain) throws Exception{
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		if(domain.getPcDjxh()==null||"".equals(domain.getPcDjxh())){
			HyWlssdjDomain dom = (HyWlssdjDomain)this.getDomainByKey(domain);
			map.put("pcDjxh", dom.getPcDjxh());
			map.put("wfhDjxh", dom.getWfhDjxh());
		}else{
			map.put("pcDjxh", domain.getPcDjxh());
			map.put("wfhDjxh", domain.getWfhDjxh());
		}

		
		HyZyglFydjDomain dom = (HyZyglFydjDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxWhenWlss",map);
		return dom;
	}
	public BaseBusinessDomain getHyPcAllByDjxh(String id) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("pcDjxh", id);

		HyPcxxglDomain doMain = (HyPcxxglDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcAllByDjxh", map);
		return doMain;
	}
	
	public HyPcxxglDomain selectHyPcAllByWlssDjxh(String wlssDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("wlssDjxh", wlssDjxh);

		HyPcxxglDomain doMain = (HyPcxxglDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcAllByWlssDjxh", map);
		return doMain;
	}
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain) baseDomain;
		HyPcxxglDomain dom = null;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			dom = (HyPcxxglDomain) this.getHyPcAllByDjxh(domain.getPcDjxh());
		}else if (StringUtils.isNotBlank(domain.getWlssDjxh())){
			dom = this.selectHyPcAllByWlssDjxh(domain.getWlssDjxh());
		}
		HyZyglFydjDomain hwDom = (HyZyglFydjDomain) this.selectHyPcHwxxWhenWlss(domain);
		if(dom!=null){
			domain.setPcdh(dom.getPcdh());
			domain.setClhm4Query(dom.getCyrClhm());
			domain.setCyrGchm(dom.getCyrGchm());
			domain.setCyrSjxm(dom.getCyrSjxm());
			domain.setPcrMc(dom.getPcrMc());	
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");	
			domain.setPcrq(sim.format(dom.getPcrq()));	
			domain.setPcJgmc(dom.getPcJgbmMc());	
			domain.setSsJgmc(dom.getSsJgbmMc());
			
			domain.setDdDjxh(hwDom.getDdDjxh());
			domain.setHwmxxh(hwDom.getXh());
			domain.setWfhDjxh(hwDom.getWfhDjxh());
			domain.setHwmc(hwDom.getHwmc());
			domain.setKhDjxh(hwDom.getFhrDjxh());
			domain.setKhmc(hwDom.getFhrMc());
			domain.setSl(hwDom.getSl());
			domain.setTj(hwDom.getTj());
			domain.setZl(hwDom.getZl());
			domain.setJsSl(hwDom.getJsSl());
		}
		
		if(StringUtils.isNotBlank(domain.getWlssDjxh())){
			HyWlssdjDomain wlDomain=(HyWlssdjDomain)this.getDomainByKey(domain);
			if(wlDomain!=null){
				domain.setPcDjxh(wlDomain.getPcDjxh());
				domain.setWlssyyWhXh(wlDomain.getWlssyyWhXh());
				//domain.setWlssclfsDm(wlDomain.getWlssclfsDm());
				domain.setHjSr(String.valueOf(wlDomain.getHjSr()));
				domain.setKhDjxh(wlDomain.getKhDjxh());
				domain.setWfhDjxh(wlDomain.getWfhDjxh());
				domain.setDdDjxh(wlDomain.getDdDjxh());
				domain.setHwmxxh(wlDomain.getHwmxxh());
				domain.setBz(wlDomain.getBz());
				domain.setSsZl(wlDomain.getZl());
				domain.setSsSl(wlDomain.getSl());
				domain.setSsTj(wlDomain.getTj());
				domain.setJsSl(wlDomain.getJsSl());
				domain.setSpbz(wlDomain.getSpbz());
				domain.setWsSpxh(wlDomain.getWsSpxh());
				domain.setWsspztDm(wlDomain.getWsspztDm());
				domain.setWlssLybz(wlDomain.getWlssLybz());
				domain.setSpbcbz(wlDomain.getSpbcbz());
				
				domain.setSl(hwDom.getSl());
				domain.setTj(hwDom.getTj());
				domain.setZl(hwDom.getZl());
				domain.setJsSl(hwDom.getJsSl());
				
				
				List<HyWlssdjMxDomain> hyWlssdjMxList = this.getHyWlssdjMxList(domain.getWlssDjxh());
				
				domain.setWlssMxList(hyWlssdjMxList);
			}
		}	
		
	}

	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		HyZyglFydjDomain hyDomain=(HyZyglFydjDomain)domain;
		Map<String, String> map=new HashMap<String, String>();
		map.put("wlssDjxh", hyDomain.getWlssDjxh());
		businessSqlMapClientTemplate.delete("deleteHyWlssdjByKey",map);
	}

	
	public List<HyZyglFydjDomain> getHw(HyZyglFydjDomain domain,UserDomain userDomain) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			map.put("pcDjxh", domain.getPcDjxh());
		}
		if(String.valueOf(domain.getFhrDjxh())!=null&&!"null".equals(domain.getFhrDjxh())&&!"".equals(domain.getFhrDjxh())){
			map.put("fhDjxh", domain.getFhrDjxh());
		}
		map.put("mcContainDmBz", domain.getConDm());
		List<HyZyglFydjDomain> dataList =businessSqlMapClientTemplate.queryForList("getPcHwByDjxh",map);
		return dataList;
	}
	

	
	public int checkWlDj(HyZyglFydjDomain domain) throws Exception {
		Map<String , String> map=new HashMap<String, String>();
		map.put("djxh", domain.getPcDjxh());
		int count=(Integer)businessSqlMapClientTemplate.queryForObject("checkWlSsDjBefore",map);
		return count;
	}
	public int getXhOfWlssdjMx(String wlssDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("wlssDjxh", wlssDjxh);
		return (Integer) businessSqlMapClientTemplate.queryForObject("getMaxXhOfWlssdjMx",map);
	}
	public void saveHyWlssdjMx(HyWlssdjMx bo) throws Exception{
		bo.setYxbz("Y");
		if(StringUtils.isBlank(bo.getXh())){
			int xh = this.getXhOfWlssdjMx(bo.getWlssDjxh());
			bo.setXh((xh+1)+"");
			businessSqlMapClientTemplate.update("insertHyWlssdjMx",bo);
		}else {
			businessSqlMapClientTemplate.insert("updateHyWlssdjMxByKey",bo);
		}
	}
	public void deleteWlssdjMx(String wlssDjxh,String xh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("wlssDjxh", wlssDjxh);
		map.put("xh", xh);
		businessSqlMapClientTemplate.delete("deleteHyWlssdjMxByKey",map);
	}
	public double getWlssdjJe(String wlssDjxh) throws Exception {
		Map<String , String> map=new HashMap<String, String>();
		map.put("wlssDjxh", wlssDjxh);
		double je;
		String jeStr=(String) businessSqlMapClientTemplate.queryForObject("getWlssdjJe",map);
		if(String.valueOf(jeStr)==null||String.valueOf(jeStr).equals("null")){
			je=0;
		}else{
			je=Double.valueOf(jeStr);
		}
		return je;
	}
	
	public void updateWlssdjJe(String wlssDjxh,String je) throws Exception{
		Map<String , String> map=new HashMap<String, String>();
		map.put("wlssDjxh", wlssDjxh);
		map.put("je", je);
		businessSqlMapClientTemplate.update("updateHyWlssdjJeByKey",map);
	}
	
	public void wlssDjHxcl(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseDomain;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("wlDjxh", domain.getWlssDjxh());
		map.put("bm", user.getBmbm());
		map.put("czyDjxh", user.getCzyDjxh());
		map.put("count", 0);
		map.put("error", "");
		businessSqlMapClientTemplate.queryForObject("checkSaveWlssdj",map);
		if(StringUtils.isNotBlank((String)map.get("error"))&&(Integer)map.get("count")!=0){
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK((String)map.get("error")));
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> getSsList(HyZyglFydjDomain domain,UserDomain userDomain) throws Exception {
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		map.put("str0",SysEncodeUtil.GBK2ISO("直接登记"));
		map.put("str1",SysEncodeUtil.GBK2ISO("回单登记时"));
		map.put("str2",SysEncodeUtil.GBK2ISO("订单接收时"));
		
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("selectWlssListRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectWlssList", map,start,pagSize);
		
		return dataList;
	}
	
	@Transactional
	public void saveFileDomain(HyZyglFydjDomain domain,UserDomain user)
			throws Exception {
		wlssDjHxcl(domain, user);//后续处理审核之后不可修改
		HyWlssdjZp bo=new HyWlssdjZp();
		bo.setWlssDjXh(domain.getWlssDjxh());
		bo.setXgsj(domain.getXgsj());
		bo.setZpdz(domain.getZpdz());
		bo.setZpmc(domain.getZpmc());
		businessSqlMapClientTemplate.update("insertHyWlssDjZp", bo);
		
		
	}
	@SuppressWarnings("unchecked")
	public List<HyWlssdjZp> queryPhoto(String wlssdjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("wlssDjXh", wlssdjxh);
		List<HyWlssdjZp> list=businessSqlMapClientTemplate.queryForList("queryPhotoes",map);
		
		return list;
	}
	@Transactional
	public String deletePhoto(String zpscxh,HyZyglFydjDomain domain,UserDomain user) throws Exception {
		wlssDjHxcl(domain, user);//后续处理审核之后不可修改
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("zpscxh", zpscxh);
		String zpdz=(String)businessSqlMapClientTemplate.queryForObject("queryPhoto",map);
		businessSqlMapClientTemplate.delete("deletePhoto", map);
		
		return zpdz;
	}
	@Transactional
	public List<String> deletePhotoes(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception {
		wlssDjHxcl(domain, userDomain);//后续处理审核之后不可修改
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("wlssDjXh", domain.getWlssDjxh());
		List<String> list=businessSqlMapClientTemplate.queryForList("queryZpdz",map);
		businessSqlMapClientTemplate.delete("deletePhotoes", map);
		return list;
	}
	
	
}
