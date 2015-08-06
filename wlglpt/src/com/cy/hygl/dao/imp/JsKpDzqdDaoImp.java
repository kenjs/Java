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
import com.cy.common.bo.JsKpsqMx;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.JsKpDzqdDao;
import com.cy.hygl.domain.JsKpDzqdDomain;


/**
 * The DAOIMP for 开票申请.
 * 
 * @author HCM
 */

@Repository
public class JsKpDzqdDaoImp implements JsKpDzqdDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		SysEncodeUtil.decodeURL(domain);
		SysEncodeUtil.conGBK2ISO(domain);
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
		if(StringUtils.isNotBlank(domain.getKhmc())){
			map.put("khmc", domain.getKhmc());
		}
		if(StringUtils.isNotBlank(domain.getDjrqQ())){
			map.put("djrqQ", domain.getDjrqQ());
		}
		if(StringUtils.isNotBlank(domain.getDjrqZ())){
			map.put("djrqZ", domain.getDjrqZ());
		}

		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getJsKpDzqdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpDzqdPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;
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
		if(StringUtils.isNotBlank(domain.getDjrqQ())){
			map.put("djrqQ", domain.getDjrqQ());
		}
		if(StringUtils.isNotBlank(domain.getDjrqZ())){
			map.put("djrqZ", domain.getDjrqZ());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpDzqdAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;
        JsKpsqMx bo = new JsKpsqMx();
     	Map<String, String> map = new HashMap<String, String>();
     	map.put("kpsqDjxh", domain.getKpsqDjxh());
     	map.put("qdDjxh", domain.getQdDjxh());
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
     	JsKpDzqdDomain dom = (JsKpDzqdDomain)businessSqlMapClientTemplate.queryForObject("selectJsKpsqMx1ByKey",map);
     	
     	if(dom != null){
     		BeanUtils.copyProperties(bo, dom);
     		bo.setBzsm(domain.getBzsm());
     		bo.setSqKpje(domain.getSqKpje());
     		bo.setYwDjxh(domain.getQdDjxh());
     		
     		businessSqlMapClientTemplate.update("updateJsKpsqMx1ByKey",bo);
     		domain.setKpsqmxDjxh(bo.getKpsqmxDjxh());
     	}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setKpsqMxflDm("1");
			bo.setYwDjxh(domain.getQdDjxh());
			businessSqlMapClientTemplate.insert("insertJsKpsqMx1", bo);
			domain.setKpsqmxDjxh(bo.getKpsqmxDjxh());
     	}

		
	}



	@SuppressWarnings("unchecked")
	public void initHxMx(BaseBusinessDomain baseDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
     	map.put("kpsqDjxh", domain.getKpsqDjxh());
     	map.put("qdDjxh", domain.getQdDjxh());
     	
     	JsKpDzqdDomain dom = (JsKpDzqdDomain)businessSqlMapClientTemplate.queryForObject("findJsKpDzqdByKey",map);

     	if(dom!=null){
         	domain.setBzsm(dom.getBzsm());
         	domain.setSqKpje(dom.getSqKpje());
         	domain.setQdmc(dom.getQdmc());
        	domain.setHeJe(dom.getHeJe());
        	domain.setWsqKpJe(dom.getWsqKpJe());
        	domain.setYsqKpJe(dom.getYsqKpJe());
        	domain.setYkpQdhx(dom.getYkpQdhx());
     	}  
     	Map<String, String> map1 = new HashMap<String, String>();
		// 设置查询条件
		map1.put("ssJgbm", domain.getSsJgbm());
		
		map1.put("khDjxh", domain.getKhDjxh());
        // 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpDzqdList", map1);
		domain.setDataList(dataList);
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> getYhxList(BaseBusinessDomain baseDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("kpsqDjxh", domain.getKpsqDjxh());			

		// 检索数据
		List<BaseBusinessDomain>  yhxList = businessSqlMapClientTemplate.queryForList("selectYhxList", map);

		return yhxList;
	}


	@SuppressWarnings("unchecked")
	public void initAddHxMx(BaseBusinessDomain baseDomain) throws Exception {
		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getKpsqDjxh()) && StringUtils.isNotBlank(domain.getSsJgbm()) && StringUtils.isNotBlank(domain.getKhDjxh())){

			Map<String, String> map = new HashMap<String, String>();
			// 设置查询条件
			map.put("ssJgbm", domain.getSsJgbm());
			
			map.put("khDjxh", domain.getKhDjxh());
			

			// 检索数据
			List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectJsKpDzqdList", map);
			domain.setDataList(dataList);
			List<BaseBusinessDomain>  yhxList = this.getYhxList(domain);			
			if(StringUtils.isBlank(domain.getQdDjxh())&&dataList.size()>0){
				JsKpDzqdDomain dom = (JsKpDzqdDomain)dataList.get(0);
				domain.setQdDjxh(dom.getQdDjxh());
			}	
			if(yhxList.size()>0){
					
				domain.setYhxList(yhxList);
				String str = "";
				for(BaseBusinessDomain element:yhxList){
					JsKpDzqdDomain dom = (JsKpDzqdDomain) element;
					if(str.equals("")){
						str = str+dom.getQdDjxh();
					}else{
						str = str +","+dom.getQdDjxh();
					}				
				}
				domain.setXhStr(str);
			}
		}  	
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {

		JsKpDzqdDomain domain = (JsKpDzqdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getKpsqDjxh())){

			Map<String, String> map = new HashMap<String, String>();
			// 设置查询条件			
			map.put("kpsqDjxh", domain.getKpsqDjxh());
			

			// 检索数据
			JsKpDzqdDomain dom = (JsKpDzqdDomain)businessSqlMapClientTemplate.queryForObject("findJsKpSqByKey", map);			
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);	
			}
			List<BaseBusinessDomain>  yhxList = this.getYhxList(domain);
			if(yhxList.size()>0){
				
				domain.setYhxList(yhxList);
				String str = "";
				for(BaseBusinessDomain element:yhxList){
					JsKpDzqdDomain dom1 = (JsKpDzqdDomain) element;
					if(str.equals("")){
						str = str+dom1.getQdDjxh();
					}else{
						str = str +","+dom1.getQdDjxh();
					}				
				}
				domain.setXhStr(str);
			}
		}
	}
	public void deleteJsKpsqMxByKey(String kpsqmxDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("kpsqmxDjxh", kpsqmxDjxh);

		businessSqlMapClientTemplate.update("deleteJsKpsqMx1ByKey", map);
	}
	/**
	 * 核销，修改，删除 后续处理
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void callPHyglJsglYkphx(String kpsqmxDjxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kpsqmxDjxh", kpsqmxDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("callPHyglJsglYkphx", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
