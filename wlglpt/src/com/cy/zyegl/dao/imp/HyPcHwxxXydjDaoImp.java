package com.cy.zyegl.dao.imp;

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
import com.cy.common.bo.HyPcHwxxXydj;
import com.cy.zyegl.dao.HyPcHwxxXydjDao;
import com.cy.zyegl.domain.HyPcHwxxXydjDomain;

/**
 * The DAOIMP for ����-�ɳ�-������Ϣ-Э��Ǽ�.
 * 
 * @author HJH
 */

@Repository
public class HyPcHwxxXydjDaoImp implements HyPcHwxxXydjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxXydjDomain domain = (HyPcHwxxXydjDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxXydjRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxXydjPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxXydjDomain domain = (HyPcHwxxXydjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxXydjAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHwxxXydjDomain domain = (HyPcHwxxXydjDomain) baseDomain;
		HyPcHwxxXydj bo = new HyPcHwxxXydj();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		HyPcHwxxXydjDomain dom = (HyPcHwxxXydjDomain) this.getDomainByKey(domain);
		BeanUtils.copyProperties(bo, domain);
		if(dom != null){
			businessSqlMapClientTemplate.update("updateHyPcHwxxXydjByKey", bo);
		}else{
			businessSqlMapClientTemplate.insert("insertHyPcHwxxXydj", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxXydjDomain domain = (HyPcHwxxXydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());

		domain = (HyPcHwxxXydjDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxXydjByKey", map);
		return domain;
	}
	
	public HyPcHwxxXydjDomain initHwxxXydj(HyPcHwxxXydjDomain domain) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());

		domain = (HyPcHwxxXydjDomain)businessSqlMapClientTemplate.queryForObject("initHwxxXydj", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxXydjDomain domain = (HyPcHwxxXydjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcHwxxXydjByKey", map);
	}
	
	public void deleteHyPcHwxxXydjByPcDjxh(String pcDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("pcDjxh", pcDjxh);
		businessSqlMapClientTemplate.delete("deleteHyPcHwxxXydjByPcDjxh", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxXydjDomain domain = (HyPcHwxxXydjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			HyPcHwxxXydjDomain dom = (HyPcHwxxXydjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
