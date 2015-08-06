package com.cy.common.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cy.common.dao.RyxzCommonDao;
import com.cy.common.domain.RyxzCommonDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;


@Repository
public class RyxzCommonDaoImp implements RyxzCommonDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	/**
	 * ��֯����
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RyxzCommonDomain> queryQyjgList(String qyzcXh) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyzcXh", qyzcXh);
		List<RyxzCommonDomain> dataList=businessSqlMapClientTemplate.queryForList("queryQyjgList", map);
		return dataList;
	}
	
	/**
	 * ��ҵ�ڲ���Ա
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RyxzCommonDomain> queryQyryList(String qyzcXh) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyzcXh", qyzcXh);
		List<RyxzCommonDomain> dataList=businessSqlMapClientTemplate.queryForList("queryQyryList", map);
		return dataList;
	}
	
	/**
	 * ��ҵ�ڲ���Ա����֯����
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RyxzCommonDomain> queryQyryAndZzjgList(String qyzcXh) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyzcXh", qyzcXh);
		List<RyxzCommonDomain> dataList=businessSqlMapClientTemplate.queryForList("queryQyryAndZzjgList", map);
		return dataList;
	}
	
	/**
	 * �ְ�����֯����
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RyxzCommonDomain> queryFbsZzjgList(String qyzcXh) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyzcXh", qyzcXh);
		List<RyxzCommonDomain> dataList=businessSqlMapClientTemplate.queryForList("queryFbsZzjgList", map);
		return dataList;
	}
	
	/**
	 * �ְ��̵���Ա
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RyxzCommonDomain> queryFbsryList(String qyzcXh) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyzcXh", qyzcXh);
		List<RyxzCommonDomain> dataList=businessSqlMapClientTemplate.queryForList("queryFbsryList", map);
		return dataList;
	}


}
