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
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.QyClxhwh;
import com.cy.zygl.dao.QyClxhwhDao;
import com.cy.zygl.domain.QyClxhwhDomain;

/**
 * The DAOIMP for ��ҵ-�����ͺ�ά��.
 * 
 * @author HJH
 */

@Repository
public class QyClxhwhDaoImp implements QyClxhwhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("ssJgbm", domain.getSsJgbm());
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyClxhwhRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyClxhwhPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("ssJgbm", domain.getSsJgbm());
		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyClxhwhAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain) baseDomain;
		domain.setSsJgbm(user.getZgsbm());
		QyClxhwh bo = new QyClxhwh();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		QyClxhwhDomain dom = (QyClxhwhDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setClxh(domain.getClxh());
			bo.setBzsm(domain.getBzsm());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			bo.setCz(domain.getCz());
			bo.setTj(domain.getTj());
			bo.setCd(domain.getCd());
			bo.setKd(domain.getKd());
			bo.setGd(domain.getGd());
			businessSqlMapClientTemplate.update("updateQyClxhwhByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyClxhwh", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("clxhwhDjxh", domain.getClxhwhDjxh());
		map.put("ssJgbm", domain.getSsJgbm());

		domain = (QyClxhwhDomain)businessSqlMapClientTemplate.queryForObject("selectQyClxhwhByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("clxhwhDjxh", domain.getClxhwhDjxh());
		map.put("ssJgbm", userDomain.getZgsbm());

		businessSqlMapClientTemplate.update("deleteQyClxhwhByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyClxhwhDomain domain = (QyClxhwhDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getClxhwhDjxh())){
			QyClxhwhDomain dom = (QyClxhwhDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
