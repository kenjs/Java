package com.cy.xtgl.dao.imp;

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
import com.cy.common.bo.QyWsdysz;
import com.cy.xtgl.dao.QyWsdyszDao;
import com.cy.xtgl.domain.QyWsdyszDomain;

/**
 * The DAOIMP for ��ҵ-�����ӡ����.
 * 
 * @author HJH
 */

@Repository
public class QyWsdyszDaoImp implements QyWsdyszDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyWsdyszRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyWsdyszPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyWsdyszAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		QyWsdysz bo = new QyWsdysz();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		QyWsdyszDomain dom = (QyWsdyszDomain) this.getDomainByWsdm(domain);
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setWsDm(domain.getWsDm());
			bo.setLeftMargin(domain.getLeftMargin());
			bo.setTopMargin(domain.getTopMargin());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQyWsdyszByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSsJgbm(user.getGsbm());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyWsdysz", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("whXh", domain.getWhXh());

		domain = (QyWsdyszDomain)businessSqlMapClientTemplate.queryForObject("selectQyWsdyszByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("whXh", domain.getWhXh());

		businessSqlMapClientTemplate.update("deleteQyWsdyszByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWhXh())){
			QyWsdyszDomain dom = (QyWsdyszDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
	}
	public BaseBusinessDomain getDomainByWsdm(BaseBusinessDomain baseDomain) throws Exception {
		QyWsdyszDomain domain = (QyWsdyszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("wsDm", domain.getWsDm());
		map.put("ssJgbm", domain.getSsJgbm());
		domain = (QyWsdyszDomain)businessSqlMapClientTemplate.queryForObject("getDomainByWsdm", map);
		return domain;
	}
}
