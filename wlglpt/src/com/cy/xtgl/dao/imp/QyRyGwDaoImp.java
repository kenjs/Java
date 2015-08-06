package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyRyGw;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.dao.QyRyGwDao;
import com.cy.xtgl.domain.QyRyGwDomain;
@Repository
/**
 * The DAOIMP for ��ҵ-��Ա-��λ.
 * 
 * @author HaoY
 */
public class QyRyGwDaoImp implements QyRyGwDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception {
		QyRyGwDomain qyRyGwDomain = (QyRyGwDomain) domain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("czyDjxh", qyRyGwDomain.getCzyDjxh());
		map.put("oldGwDjxh", qyRyGwDomain.getOldGwDjxh());
		
		businessSqlMapClientTemplate.update("deleteQyRyGwByKey", map);
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception {
		QyRyGwDomain qyRyGwDomain = (QyRyGwDomain) domain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank( qyRyGwDomain.getSjJgbm()))
			map.put("sjJgbm", qyRyGwDomain.getSjJgbm());
		if(StringUtils.isNotBlank( qyRyGwDomain.getSsJgbm()))
		map.put("jgbm", qyRyGwDomain.getSsJgbm());
		if(StringUtils.isNotBlank(qyRyGwDomain.getCzyDjxh()))
		map.put("czyDjxh", qyRyGwDomain.getCzyDjxh());
		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyRyGwAll", map);
		return dataList;
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("czyDjxh", domain.getCzyDjxh());
		map.put("oldGwDjxh", domain.getOldGwDjxh());
		domain = (QyRyGwDomain)businessSqlMapClientTemplate.queryForObject("selectQyRyGwByKey", map);
		return domain;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCzyDjxh())){
			QyRyGwDomain dom = (QyRyGwDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception {
		QyRyGwDomain qyRyGwDomain = (QyRyGwDomain) domain;
		//��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank( qyRyGwDomain.getSjJgbm()))
			map.put("sjJgbm", qyRyGwDomain.getSjJgbm());
		if(StringUtils.isNotBlank( qyRyGwDomain.getSsJgbm()))
		map.put("jgbm", qyRyGwDomain.getSsJgbm());
		if(StringUtils.isNotBlank(qyRyGwDomain.getCzyDjxh()))
		map.put("czyDjxh", qyRyGwDomain.getCzyDjxh());
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyRyGwRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyRyGwPage", map,start,pagSize);
		String sjMc=(String)businessSqlMapClientTemplate.queryForObject("getGwSjMcByJgbm",map);
		for (BaseBusinessDomain baseBusinessDomain : dataList) {
			QyRyGwDomain dom	=(QyRyGwDomain)baseBusinessDomain;
			dom.setSjMc(SysEncodeUtil.ISO2GBK(sjMc));
		}
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain) baseDomain;
		QyRyGw bo = new QyRyGw();
		
		if(domain != null && !"".equals(domain.getOldGwDjxh().trim())){
			//����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
			QyRyGwDomain dom = (QyRyGwDomain) this.getDomainByKey(domain);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("czyDjxh", domain.getCzyDjxh());
			map.put("gwDjxh", domain.getGwDjxh());
			map.put("ssJgbm", domain.getSsJgbm());
			map.put("zjbz", dom.getZjbz());
			map.put("qxJgbm", domain.getQxJgbm());
			map.put("czyDjxh", domain.getCzyDjxh());
			map.put("oldGwDjxh", domain.getOldGwDjxh());
			
			businessSqlMapClientTemplate.update("updateQyRyGwByKey", map);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setGwDjxh(domain.getGwDjxh());
			bo.setZjbz("N");
			businessSqlMapClientTemplate.insert("insertQyRyGw", bo);
		}
	}

	public int checkYhgw(BaseBusinessDomain baseDomain) throws Exception {
		QyRyGwDomain domain = (QyRyGwDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("czyDjxh", domain.getCzyDjxh());
		map.put("gwDjxh", domain.getGwDjxh());
		if(domain.getOldGwDjxh() != null) {
			map.put("oldGwDjxh", domain.getOldGwDjxh());
		}
		return (Integer)businessSqlMapClientTemplate.queryForObject("getQyRyGwYhGw", map);
	}

}
