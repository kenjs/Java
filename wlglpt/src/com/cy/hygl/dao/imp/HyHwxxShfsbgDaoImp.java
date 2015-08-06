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
import com.cy.common.bo.HyHwxxShfsbg;
import com.cy.hygl.dao.HyHwxxShfsbgDao;
import com.cy.hygl.domain.HyHwxxShfsbgDomain;

/**
 * The DAOIMP for ����-������Ϣ-�ͻ���ʽ���.
 * 
 * @author HJH
 */

@Repository
public class HyHwxxShfsbgDaoImp implements HyHwxxShfsbgDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyHwxxShfsbgRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwxxShfsbgPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwxxShfsbgAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		HyHwxxShfsbg bo = new HyHwxxShfsbg();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		HyHwxxShfsbgDomain dom = (HyHwxxShfsbgDomain) this.getDomainByKey(domain);
		//�������иö������� ���ظ�����
		if(dom == null){
			BeanUtils.copyProperties(bo, domain);
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertHyHwxxShfsbg", bo);
			
			domain.setShbgDjxh(bo.getShbgDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		//map.put("shbgDjxh", domain.getShbgDjxh());
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		
		domain = (HyHwxxShfsbgDomain)businessSqlMapClientTemplate.queryForObject("selectHyHwxxShfsbgByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		
		businessSqlMapClientTemplate.update("deleteHyHwxxShfsbgByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		HyHwxxShfsbgDomain dom = (HyHwxxShfsbgDomain) this.getDomainByKey(domain);
		if(dom!=null){
			BeanUtils.copyProperties(domain,dom);
		}
	}
	public int checkShfs(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("pcDjxh", pcDjxh);

		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkShfsbg", map))).intValue();
		return totalRecords;
	}
	public int checkShfsbgZb(String ddDjxh,String xh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("ddDjxh", ddDjxh);
		map.put("xh", xh);

		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkShfsbgZb", map))).intValue();
		return totalRecords;
	}
}
