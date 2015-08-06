package com.cy.zygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyClxhwh;
import com.cy.common.bo.WlSsYy;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.dao.QyWlSsYyDao;
import com.cy.zygl.domain.QyClxhwhDomain;
import com.cy.zygl.domain.WlSsYyDomain;

/**
 * The DAOIMP for ��ҵ-�����ͺ�ά��.
 * 
 * @author HJH
 */

@Repository
public class QyWlSsYyDaoImp implements QyWlSsYyDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		String mc=SysEncodeUtil.UTF82ISO(domain.getSsyy());
		map.put("ssyy","%"+mc+"%" );
		map.put("ssJgbm",domain.getSsJgbm());
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyWlssyyRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyWlssyyPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		String mc=SysEncodeUtil.UTF82ISO(domain.getSsyy());
		map.put("ssyy","%"+mc+"%" );
		map.put("ssJgbm",domain.getSsJgbm());
		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyWlssyyAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain) baseDomain;
		WlSsYy bo = new WlSsYy();
		WlSsYyDomain dom=null;
		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		if(StringUtils.isNotBlank(domain.getWhXh())){
			dom = (WlSsYyDomain) this.getDomainByKey(domain);
		}
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			bo.setSm(domain.getSm());
			bo.setSsyy(domain.getSsyy());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateQyWlssyyByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setSsJgbm(user.getZgsbm());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertQyWlssyy", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("whXh", domain.getWhXh());

		domain = (WlSsYyDomain)businessSqlMapClientTemplate.queryForObject("selectQyWlssyyByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("whXh", domain.getWhXh());

		businessSqlMapClientTemplate.update("deleteQyWlssyyByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		WlSsYyDomain domain = (WlSsYyDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getWhXh())){
			WlSsYyDomain dom = (WlSsYyDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
