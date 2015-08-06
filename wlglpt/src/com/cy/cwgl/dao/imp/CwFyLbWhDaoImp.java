package com.cy.cwgl.dao.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.CwFpKpdj;
import com.cy.common.bo.CwFylb;
import com.cy.common.bo.CwKpdj;
import com.cy.common.bo.WlSsYy;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.dao.CwFyLbWhDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.domain.CwFylbDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.zygl.domain.WlSsYyDomain;

/**
 * The DAOIMP for ����-��Ʊ��Ʊ�Ǽ�
 * 
 * @author LYY
 */

@Repository
public class CwFyLbWhDaoImp implements CwFyLbWhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryListById(CwFylbDomain domain,UserDomain user)  throws Exception {
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if(StringUtils.isNotBlank(domain.getFylbMc())){
			String lb=SysEncodeUtil.UTF82ISO(domain.getFylbMc());
			map.put("lbmc", "%"+lb+"%");
		}
		map.put("zgs", user.getZgsbm());
		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwFylbRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectCwFylbPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank(domain.getFylbMc())){
			String lb=SysEncodeUtil.UTF82ISO(domain.getFylbMc());
			map.put("lbmc", "%"+lb+"%");
		}
		map.put("zgs",domain.getSsJgbm());
		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwFylbAll", map);
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		CwFylbDomain domain=(CwFylbDomain)baseDomain;
		CwFylb bo = new CwFylb();
		CwFylbDomain dom=null;
		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			dom = (CwFylbDomain) this.getDomainByKey(domain);
		}
		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setFylbMc(domain.getFylbMc());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());
			businessSqlMapClientTemplate.update("updateCwFylbByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setYxbz("Y");
			bo.setSsJgbm(user.getZgsbm());
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getCurrentDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getCurrentDate().toString());

			businessSqlMapClientTemplate.insert("insertCwFylb", bo);
		}
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("cwDjxh", domain.getCwDjxh());

		domain = (CwFylbDomain)businessSqlMapClientTemplate.queryForObject("selectCwFylbByKey", map);
		return domain;
	}


	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCwDjxh())){
			CwFylbDomain dom = (CwFylbDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public int queryKhmcCount(CwFpKpdjDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("kpsqDjxh", domain.getKpsqDjxh());
		map.put("kpdwJgbm", domain.getKpdwJgbm());
		map.put("khmc", SysEncodeUtil.UTF82ISO(domain.getKhmc()));
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("queryKhmcCount", map))).intValue();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<CwFpKpdjDomain> queryXzqhList(CwFpKpdjDomain domain) throws Exception{
		//Map<String,String> map = new HashMap<String, String>();
		// ������������
		List<CwFpKpdjDomain>  data = businessSqlMapClientTemplate.queryForList("queryXzqhList", null);
		return data;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		CwFylbDomain domain = (CwFylbDomain) baseDomain;
		Map<String, String> map=new HashMap<String, String>();
		map.put("cwDjxh", domain.getCwDjxh());
		businessSqlMapClientTemplate.delete("deleteCwFylbByKey",map);
	}


	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
