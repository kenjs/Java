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
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.QyFbsLxdj;
import com.cy.zygl.dao.QyFbsLxdjDao;
import com.cy.zygl.domain.QyFbsLxdjDomain;

/**
 * The DAOIMP for ��ҵ-�ְ���-·�ߵǼ�.
 * 
 * @author HJH
 */

@Repository
public class QyFbsLxdjDaoImp implements QyFbsLxdjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("fbsDjxh", domain.getFbsDjxh());

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyFbsLxdjRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsLxdjPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("fbsDjxh", domain.getFbsDjxh());

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsLxdjAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) baseDomain;
		QyFbsLxdj bo = new QyFbsLxdj();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		QyFbsLxdjDomain dom = (QyFbsLxdjDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setLxmc(domain.getLxmc());
			bo.setLxjc(domain.getLxjc());
			bo.setPyqc(domain.getPyqc());
			bo.setPyjc(domain.getPyjc());
			bo.setSfdXzqhDm(domain.getSfdXzqhDm());
			bo.setMddXzqhDm(domain.getMddXzqhDm());
			bo.setBz(domain.getBz());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.czyDjxh);

			businessSqlMapClientTemplate.update("updateQyFbsLxdjByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setQybz("Y");
			bo.setDjJgbm(user.bmbm);
			bo.setDjrq(SysDateUtil.getCurrentDate());
			bo.setDjrCzyDjxh(user.czyDjxh);
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyFbsLxdj", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("lxDjxh", domain.getLxDjxh());

		domain = (QyFbsLxdjDomain)businessSqlMapClientTemplate.queryForObject("selectQyFbsLxdjByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("lxDjxh", domain.getLxDjxh());

		businessSqlMapClientTemplate.update("deleteQyFbsLxdjByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsLxdjDomain domain = (QyFbsLxdjDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getLxDjxh())){
			QyFbsLxdjDomain dom = (QyFbsLxdjDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}

	public int checkLxmc(QyFbsLxdjDomain qyFbsLxdjDomain) throws Exception {
		int lxMcConut = 0;
		Map<String,String> map = new HashMap<String, String>();
		map.put("ssJgbm", qyFbsLxdjDomain.getSsJgbm());
		map.put("fbsDjxh", qyFbsLxdjDomain.getFbsDjxh());
		map.put("lxmc", SysEncodeUtil.UTF82ISO(qyFbsLxdjDomain.getLxmc()));
		map.put("lxDjxh", qyFbsLxdjDomain.getLxDjxh());
		lxMcConut = ((Integer)businessSqlMapClientTemplate.queryForObject("getQyFbsLxdjLxmc", map)).intValue();
		
		return lxMcConut;
	}
}
