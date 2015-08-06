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
import com.cy.common.bo.HyPcHwxxHdqdMx;
import com.cy.hygl.dao.HyPcHwxxHdqdMxDao;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;

/**
 * The DAOIMP for ����-�ɳ�-������Ϣ-�ص��嵥-��ϸ.
 * 
 * @author HJH
 */

@Repository
public class HyPcHwxxHdqdMxDaoImp implements HyPcHwxxHdqdMxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxHdqdMxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdqdMxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdqdMxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		HyPcHwxxHdqdMx bo = new HyPcHwxxHdqdMx();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		HyPcHwxxHdqdDomain dom = (HyPcHwxxHdqdDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setHdqdDjxh(domain.getHdqdDjxh());
			bo.setHdDjxh(domain.getHdDjxh());
			bo.setDqztbz(domain.getDqztbz());

			
			businessSqlMapClientTemplate.update("updateHyPcHwxxHdqdMxByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			businessSqlMapClientTemplate.insert("insertHyPcHwxxHdqdMx", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("hdqdDjxh", domain.getHdqdDjxh());
		map.put("hdDjxh", domain.getHdDjxh());

		domain = (HyPcHwxxHdqdDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxHdqdMxByKey", map);
		return domain;
	}
	
	public void updateDqbzByqdDjxh(String hdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdDjxh", hdDjxh);

		businessSqlMapClientTemplate.update("updateDqbzByqdDjxh", map);
	}
	public void updateDqbzBykey(String hdDjxh,String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdDjxh", hdDjxh);
		map.put("hdqdDjxh", hdqdDjxh);

		businessSqlMapClientTemplate.update("updateDqbzBykey", map);
	}
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdqdDjxh", domain.getHdqdDjxh());

		//�����嵥���� ɾ����ϸ
		businessSqlMapClientTemplate.update("deleteHyPcHwxxHdqdMx", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHdqdDjxh())){
			HyPcHwxxHdqdDomain dom = (HyPcHwxxHdqdDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}	
}
