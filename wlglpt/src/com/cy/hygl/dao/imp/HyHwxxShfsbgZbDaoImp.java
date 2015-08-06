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
import com.cy.common.bo.HyHwxxShfsbgZb;
import com.cy.hygl.dao.HyHwxxShfsbgZbDao;
import com.cy.hygl.domain.HyHwxxShfsbgDomain;
import com.cy.hygl.domain.HyHwxxShfsbgZbDomain;

/**
 * The DAOIMP for ����-������Ϣ-�ͻ���ʽ���-�ӱ�.
 * 
 * @author HJH
 */

@Repository
public class HyHwxxShfsbgZbDaoImp implements HyHwxxShfsbgZbDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyHwxxShfsbgZbDomain domain = (HyHwxxShfsbgZbDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyHwxxShfsbgZbRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwxxShfsbgZbPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgZbDomain domain = (HyHwxxShfsbgZbDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyHwxxShfsbgZbAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyHwxxShfsbgZbDomain domain = (HyHwxxShfsbgZbDomain) baseDomain;
		HyHwxxShfsbgZb bo = new HyHwxxShfsbgZb();

		BeanUtils.copyProperties(bo, domain);
		bo.setCjrCzyDjxh(user.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getSqlDate().toString());
		bo.setXgrCzyDjxh(user.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getSqlDate().toString());

		businessSqlMapClientTemplate.insert("insertHyHwxxShfsbgZb", bo);

	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("ddDjxh", domain.getDdDjxh());
		map.put("xh", domain.getXh());
		map.put("pcDjxh", domain.getPcDjxh());
		map.put("wfhDjxh", domain.getWfhDjxh());
		
		domain = (HyHwxxShfsbgDomain)businessSqlMapClientTemplate.queryForObject("selectHyHwxxShfsbgZbByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		//����domain ���ӱ�
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("wfhDjxh", domain.getWfhDjxh());
		map.put("pcDjxh", domain.getPcDjxh());

		businessSqlMapClientTemplate.update("deleteHyHwxxShfsbgZbByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		//����domain ���ӱ� ��ȫ�����ӱ���Ϣ
		HyHwxxShfsbgDomain domain = (HyHwxxShfsbgDomain) baseDomain;
		HyHwxxShfsbgDomain dom = (HyHwxxShfsbgDomain) this.getDomainByKey(domain);
		if(dom!=null){
			domain.setShbgDjxh(dom.getShbgDjxh());
			domain.setBspsf(dom.getBspsf());
			domain.setBz(dom.getBz());
			domain.setCjrMc(dom.getCjrMc());
			domain.setCjrq(dom.getCjrq());
		}
	}

}
