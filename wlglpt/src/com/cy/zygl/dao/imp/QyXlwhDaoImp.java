package com.cy.zygl.dao.imp;



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
import com.cy.common.bo.QyXlwh;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.dao.QyXlwhDao;
import com.cy.zygl.domain.QyXlwhDomain;


/**
 * The DAOIMP for ��ҵ-��·ά��.
 * 
 * @author HJH
 */

@Repository
public class QyXlwhDaoImp implements QyXlwhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("jgbm", domain.getSsJgbm());
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyXlwhRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyXlwhPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		map.put("jgbm", domain.getSjJgbm());
		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyXlwhAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		QyXlwh bo = new QyXlwh();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		domain.setSfdXzqhDm(domain.getFhrXzqhDm());
		domain.setMddXzqhDm(domain.getShrXzqhDm());
		QyXlwhDomain dom = (QyXlwhDomain) this.getDomainByKey(domain);
		
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setLcs(domain.getLcs());
			bo.setDdts(domain.getDdts());
			bo.setFhrXzqhDm(dom.getSfdXzqhDm());
			bo.setShrXzqhDm(dom.getMddXzqhDm());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateQyXlwhByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSfdXzqhDm(bo.getFhrXzqhDm());
			bo.setMddXzqhDm(bo.getShrXzqhDm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyXlwh", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("sfdXzqhDm", domain.getSfdXzqhDm());
		map.put("mddXzqhDm", domain.getMddXzqhDm());

		domain = (QyXlwhDomain)businessSqlMapClientTemplate.queryForObject("selectQyXlwhByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("sfdXzqhDm", domain.getSfdXzqhDm());
		map.put("mddXzqhDm", domain.getMddXzqhDm());

		businessSqlMapClientTemplate.update("deleteQyXlwhByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyXlwhDomain domain = (QyXlwhDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getSsJgbm())&&StringUtils.isNotBlank(domain.getSfdXzqhDm())&&StringUtils.isNotBlank(domain.getMddXzqhDm())){
			QyXlwhDomain dom = (QyXlwhDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setFhrXzqhDm(domain.getSfdXzqhDm());
				domain.setShrXzqhDm(domain.getMddXzqhDm());
			}
		}
		else {
			Map<String, String> map=new HashMap<String, String>();
			map.put("jgbm", domain.getSsJgbm());
			QyXlwhDomain xlDomain=(QyXlwhDomain)businessSqlMapClientTemplate.queryForObject("getXlWhSjMcByJgbm",map);
			domain.setSjMc(xlDomain.getSjMc());
		}

	}
}
