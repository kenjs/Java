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
import com.cy.framework.util.SysDateUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.XyjsSrdzQdMx;
import com.cy.hygl.dao.XyjsSrdzQdMxDao;
import com.cy.hygl.domain.XyjsSrdzQdMxDomain;

/**
 * The DAOIMP for ���ν���-�������-�嵥-��ϸ.
 * 
 * @author HJH
 */

@Repository
public class XyjsSrdzQdMxDaoImp implements XyjsSrdzQdMxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getXyjsSrdzQdMxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzQdMxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsSrdzQdMxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		XyjsSrdzQdMx bo = new XyjsSrdzQdMx();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		//XyjsSrdzQdMxDomain dom = (XyjsSrdzQdMxDomain) this.getDomainByKey(domain);

		BeanUtils.copyProperties(bo, domain);
		
		businessSqlMapClientTemplate.insert("insertXyjsSrdzQdMx", bo);
	}
	
	public void saveDomainTemp(XyjsSrdzQdMxDomain domain, UserDomain user) throws Exception {
		XyjsSrdzQdMx bo = new XyjsSrdzQdMx();

		BeanUtils.copyProperties(bo, domain);
		businessSqlMapClientTemplate.insert("insertXyjsSrdzQdMxTemp", bo);
	}
	
	public void saveQdmxTempToFormal(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("qdDjxh", qdDjxh);
		
		businessSqlMapClientTemplate.insert("saveQdmxTempToFormal", map);
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("ywMxXh", domain.getYwMxXh());

		domain = (XyjsSrdzQdMxDomain)businessSqlMapClientTemplate.queryForObject("selectXyjsSrdzQdMxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("qdDjxh", domain.getQdDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("ywMxXh", domain.getYwMxXh());

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdMxByKey", map);
	}
	
	public void deleteDomain(String qdDjxh, String ywDjxh, String ywMxXh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("qdDjxh", qdDjxh);
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdMxByKey", map);
	}

	public void deleteDomainTemp(String qdDjxh, String ywDjxh, String ywMxXh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("qdDjxh", qdDjxh);
		map.put("ywDjxh", ywDjxh);
		map.put("ywMxXh", ywMxXh);

		businessSqlMapClientTemplate.update("deleteXyjsSrdzQdMxTempByKey", map);
	}

	public void deleteQdmxTempByQddjxh(String qdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("qdDjxh", qdDjxh);
		businessSqlMapClientTemplate.delete("deleteQdmxTempByQddjxh", map);
	}
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsSrdzQdMxDomain domain = (XyjsSrdzQdMxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getQdDjxh())){
			XyjsSrdzQdMxDomain dom = (XyjsSrdzQdMxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
