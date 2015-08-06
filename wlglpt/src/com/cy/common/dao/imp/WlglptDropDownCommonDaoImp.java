package com.cy.common.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.dao.WlglptDropDownCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptDropDownCommonDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.hygl.domain.QyZrbmThShdzDomain;
import com.cy.zygl.domain.DmXzqhDomain;
import com.cy.zygl.domain.QyYlClxxDomain;
/**
 * WlglptDropDownCommonDao
 * @author Administrator
 *
 */
@Repository
public class WlglptDropDownCommonDaoImp implements WlglptDropDownCommonDao{
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<DmXzqhDomain> queryXzqhInputSelList() throws Exception {
		List<DmXzqhDomain> list = businessSqlMapClientTemplate.queryForList("queryXzqhInputSelList");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<DmXzqhDomain> queryXzdqList() throws Exception {
		List<DmXzqhDomain> list = businessSqlMapClientTemplate.queryForList("queryXzdqList");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<WlglptDropDownCommonDomain> queryHykhListByBmbm(String bmbm)
			throws Exception {
		//设置查询条件
		Map<String,String> map = new HashMap<String, String>();
		map.put("bmbm", bmbm);
		List<WlglptDropDownCommonDomain> data = businessSqlMapClientTemplate.queryForList("getHykhBySsbmbm",map);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public List<WlglptDropDownCommonDomain> queryHykhListBySsjgbm(String ssJgbm) throws Exception {
		//设置查询条件
		Map<String,String> map = new HashMap<String, String>();
		map.put("ssJgbm", ssJgbm);
		List<WlglptDropDownCommonDomain> data = businessSqlMapClientTemplate.queryForList("getHykhBySsjgbm",map);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public List<WlglptDropDownCommonDomain> queryHyhwList(String khDjxh, String CS20001)
			throws Exception {
		//设置查询条件
		Map<String,String> map = new HashMap<String, String>();
		map.put("khDjxh", khDjxh);
		map.put("CS20001", CS20001);
		
		List<WlglptDropDownCommonDomain> data = businessSqlMapClientTemplate.queryForList("getQyKhHwxxByKhDJxh",map);
		return data;
	}

	@SuppressWarnings("unchecked")
	public List<WlglptDropDownCommonDomain> queryHyShdzList(String khDjxh)
			throws Exception {
		//设置查询条件
		Map<String,String> map = new HashMap<String, String>();
		map.put("khDjxh", khDjxh);
		List<WlglptDropDownCommonDomain> data = businessSqlMapClientTemplate.queryForList("getHyShdzByKhDJxh",map);
		return data;
	}

	@SuppressWarnings("unchecked")
	public List<WlglptDropDownCommonDomain> queryHyShrDzList(String khDjxh)
			throws Exception {
		//设置查询条件
		Map<String,String> map = new HashMap<String, String>();
		map.put("khDjxh", khDjxh);
		List<WlglptDropDownCommonDomain> data = businessSqlMapClientTemplate.queryForList("getHyShrDzByKhDJxh",map);
		return data;
	}

	@SuppressWarnings("unchecked")
	public List<WlglptDropDownCommonDomain> queryHyShdwList(String khDjxh)
			throws Exception {
		//设置查询条件
		Map<String,String> map = new HashMap<String, String>();
		map.put("khDjxh", khDjxh);
		List<WlglptDropDownCommonDomain> data = businessSqlMapClientTemplate.queryForList("getShdwByKhDjxh",map);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public List<QyYlClxxDomain> queryQyClxxBySsbmbm(String bmbm, QyYlClxxDomain clxxDomain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("bmbm", bmbm);
		map.put("clsxDm", clxxDomain.getClsxDm());
		map.put("pcfsDm", clxxDomain.getPcfsDm());
		List<QyYlClxxDomain> list = businessSqlMapClientTemplate.queryForList("queryQyClxxBySsbmbm",map);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<QyYlClxxDomain> queryQyGcxxBySsbmbm(String bmbm, QyYlClxxDomain clxxDomain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("bmbm", bmbm);
		map.put("clsxDm", clxxDomain.getClsxDm());
		map.put("pcfsDm", clxxDomain.getPcfsDm());
		List<QyYlClxxDomain> list = businessSqlMapClientTemplate.queryForList("queryQyGcxxBySsbmbm",map);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<QyZrbmThShdzDomain> queryZrbmThShdz(QyZrbmThShdzDomain domain, UserDomain userDomain) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("zrbmDjxh", domain.getZrbmDjxh());
		if ("QY_TH_SHDZ".equals(domain.getTableName())) {
			map.put("zrbmDjxh", userDomain.getGsbm());
		}
		map.put("tableName", domain.getTableName());
		
		List<QyZrbmThShdzDomain> list = businessSqlMapClientTemplate.queryForList("queryZrbmThShdz", map);
		return list;
	}
	
}
