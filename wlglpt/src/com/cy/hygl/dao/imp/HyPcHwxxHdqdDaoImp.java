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
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.HyPcHwxxHdqd;
import com.cy.hygl.dao.HyPcHwxxHdqdDao;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;

/**
 * The DAOIMP for ����-�ɳ�-������Ϣ-�ص��嵥.
 * 
 * @author HJH
 */

@Repository
public class HyPcHwxxHdqdDaoImp implements HyPcHwxxHdqdDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxHdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryQdList(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("qdCxBz", domain.getQdCxBz());
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getHyPcHwxxHdqdRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdqdPage", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryHdByQd(BaseBusinessDomain baseDomain)  throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = 999999;

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("hdqdDjxh", domain.getHdqdDjxh());
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("queryHdByQd", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHyPcHwxxHdqdAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		HyPcHwxxHdqd bo = new HyPcHwxxHdqd();

		SysEncodeUtil.decodeURL(domain);
		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		HyPcHwxxHdqdDomain dom = (HyPcHwxxHdqdDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setQdmc(domain.getQdmc());
			bo.setJszt(domain.getJszt());
			bo.setFsGsbm(domain.getFsGsbm());
			bo.setJsGsbm(domain.getJsGsbm());
			bo.setBz(domain.getBz());
			bo.setSsJgbm(domain.getSsJgbm());
			bo.setDjJgbm(domain.getDjJgbm());


			bo.setYxbz("Y");

			businessSqlMapClientTemplate.update("updateHyPcHwxxHdqdByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setSsJgbm(user.getGsbm());
			bo.setDjJgbm(user.getBmbm());
			bo.setYxbz("Y");
			bo.setDbrCzyDjxh(user.getCzyDjxh());
			bo.setDbrq(SysDateUtil.getSqlDate()+"");

			businessSqlMapClientTemplate.insert("insertHyPcHwxxHdqd", bo);
		}
		domain.setHdqdDjxh(bo.getHdqdDjxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("hdqdDjxh", domain.getHdqdDjxh());

		domain = (HyPcHwxxHdqdDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcHwxxHdqdByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdqdDjxh", domain.getHdqdDjxh());

		businessSqlMapClientTemplate.update("deleteHyPcHwxxHdqdByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getHdqdDjxh())){
			HyPcHwxxHdqdDomain dom = (HyPcHwxxHdqdDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	public void updatejsztWhenJs(String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdqdDjxh", hdqdDjxh);

		businessSqlMapClientTemplate.update("updatejsztWhenJs", map);
	}
	public int checkTh(String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdqdDjxh", hdqdDjxh);
		int count = ((Integer)(businessSqlMapClientTemplate.queryForObject("checkTh", map))).intValue();
		return count;
	}
	public void updatejsztWhenTh(String hdqdDjxh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("hdqdDjxh", hdqdDjxh);
		businessSqlMapClientTemplate.update("updatejsztWhenTh", map);
	}
	@SuppressWarnings("unchecked")
	public List<HyPcHwxxHdqdDomain> hdDjxhList(String hdqdDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("hdqdDjxh", hdqdDjxh);

		// ��������
		List<HyPcHwxxHdqdDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectHdByHdqdDjxh", map);
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public String selectQdDjxh(String hdDjxh,String ssJgbm) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("hdDjxh", hdDjxh);
		map.put("ssJgbm", ssJgbm);

		// ��������
		HyPcHwxxHdqdDomain  dom = (HyPcHwxxHdqdDomain) businessSqlMapClientTemplate.queryForObject("selectQdByHdDjxhWhenDelete", map);
		return dom.getHdqdDjxh();
	}
}
