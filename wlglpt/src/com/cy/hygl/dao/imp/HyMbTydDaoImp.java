package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.HyMbTyd;
import com.cy.hygl.dao.HyMbTydDao;
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyTydglDomain;

/**
 * The DAOIMP for ����-ģ��-���˵�.
 * 
 * @author HJH
 */

@Repository
public class HyMbTydDaoImp implements HyMbTydDao {
	
	//������Դ����-���˵�
	private static String DM_DDFL_TYD = "13";
	//����״̬-δ��
	private static String DM_HWZT_WF = "21";
	//����״̬-δ��
	private static String DM_HWZT_WT = "11";
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("gsbm", domain.getSsJgbm());
		}
		map.put("fhrDjxh", domain.getFhrDjxh());
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("fhrMc", SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
		}

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyMbTydRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyMbTydPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("gsbm", domain.getSsJgbm());
		}
		map.put("fhrDjxh", domain.getFhrDjxh());
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("fhrMc", SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
		}

		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyMbTydAll", map);
		
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain) baseDomain;
		HyMbTyd bo = new HyMbTyd();
		
		if (domain.getThflDm() != null && domain.getThflDm().longValue() == 1) {
			domain.setHwztDm(DM_HWZT_WT);
		}else {
			domain.setHwztDm(DM_HWZT_WF);
		}
		
		BeanUtils.copyProperties(domain, bo);
		//�ͻ�
		bo.setKhDjxh(domain.getFhrDjxh());
		bo.setKhmc(domain.getFhrMc());
		
		HyMbTydDomain dom = (HyMbTydDomain)this.getDomainByKey(baseDomain);
		
		if(dom != null){
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());

			businessSqlMapClientTemplate.update("updateHyMbTydByKey", bo);
//			//�е��޸ĵ�ʱ�򣬸��¶�Ӧ���е�������Ϣ��Ӧ�� �����ˡ��ջ��˵���Ϣ
//			businessSqlMapClientTemplate.update("updateTydHwmxFhShxx", domain);
			domain.setMbDjxh(bo.getMbDjxh());
		}else{
			bo.setDdflDm(DM_DDFL_TYD);
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate());
//			bo.setDjJgbm(user.getBmbm());
//			bo.setSsJgbm(user.getGsbm());
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate());
			businessSqlMapClientTemplate.insert("insertHyMbTyd", bo);
			domain.setMbDjxh(bo.getMbDjxh());
		}
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("mbDjxh", domain.getMbDjxh());

		domain = (HyMbTydDomain)businessSqlMapClientTemplate.queryForObject("selectHyMbTydByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("mbDjxh", domain.getMbDjxh());

		businessSqlMapClientTemplate.update("deleteHyMbTydByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyMbTydDomain domain = (HyMbTydDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getMbDjxh())){
			HyMbTydDomain dom = (HyMbTydDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom,domain);
			}
		}

	}
	
	public void saveAsTemplate(HyMbTydDomain domain, UserDomain userDomain) throws Exception {
		HyMbTyd bo = new HyMbTyd();
		BeanUtils.copyProperties(domain,bo);
		
		bo.setDjrCzyDjxh(userDomain.getCzyDjxh());
		bo.setDjJgbm(userDomain.getBmbm());
		bo.setSsJgbm(userDomain.gsbm);
		bo.setCjrCzyDjxh(userDomain.getCzyDjxh());
		bo.setXgrCzyDjxh(userDomain.getCzyDjxh());
		
		businessSqlMapClientTemplate.insert("insertHyMbTydFromTyd", bo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mbDjxh", bo.getMbDjxh());
		map.put("ddDjxh", bo.getDdDjxh());
		
		businessSqlMapClientTemplate.insert("insertHyMbTydHwmxFromTyd", map);
	}
	
	public void checkTemplateName(HyMbTydDomain domain, UserDomain userDomain) throws Exception {
		SysEncodeUtil.decodeURL(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mbmc", SysEncodeUtil.GBK2ISO(domain.getMbmc()));
		map.put("gsbm", userDomain.getGsbm());
		map.put("mbDjxh", domain.getMbDjxh());
		
		Long count = (Long)businessSqlMapClientTemplate.queryForObject("checkTemplateName", map);
		
		if (count != null && count.longValue() > 0) {
			domain.setMcUseable(false);
		}else {
			domain.setMcUseable(true);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryMb4Tydgl(HyMbTydDomain domain, UserDomain userDomain) throws Exception {
		SysEncodeUtil.decodeURL(domain);
		// ��ҳ���
		PageDomain page=domain.getPage();
		/*int start=page.getStart();
		int pagSize=page.getPageSize();*/
		Map<String, String>map=new HashMap<String, String>();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		
		if (StringUtils.isNotBlank(domain.getFhrDjxh())) {
			map.put("fhrDjxh", domain.getFhrDjxh());
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			map.put("fhrMc", SysEncodeUtil.GBK2ISO(domain.getFhrMc()));
		}
		if(StringUtils.isNotBlank(domain.getShrMc())){
			map.put("shrMc", SysEncodeUtil.GBK2ISO(domain.getShrMc()));
		}
		map.put("gsbm", userDomain.getGsbm());

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		/*int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("queryMb4TydglCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("queryMb4TydglPage", map,start,pagSize);*/
		
		//ȫ������������ҳ
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("queryMb4TydglPage", map);
		
		return dataList;
	}
	
}
