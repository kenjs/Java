package com.cy.cwgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwHbzcLsjlDao;
import com.cy.cwgl.domain.CwHbzcLsjlDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;

/**
 * The DAO for ����-�����ʲ���ˮ��¼
 * 
 * @author HCM
 */
@Repository
public class CwHbzcLsjlDaoImp implements CwHbzcLsjlDao{
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("yhCshDjxh", domain.getYhCshDjxh());
		map.put("jbrCzyDjxh", domain.getJbrCzyDjxh());
		map.put("djJgbm", domain.getDjJgbm());
		map.put("zcflDm", domain.getZcflDm());
		if(StringUtils.isNotBlank(domain.getZh())){
			map.put("zh", "%" + domain.getZh() + "%");
		}
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());
		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwHbzcLsjlRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcLsjlPage", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryListForZjrb(BaseBusinessDomain baseDomain)  throws Exception {
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("rq", domain.getRq());
		map.put("bz", domain.getBz());
		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getCwHbzcLsjlRowCountForZjrb", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcLsjlForZjrb", map,start,pagSize);
		
		return dataList;
	}
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		CwHbzcLsjlDomain domain = (CwHbzcLsjlDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("yhCshDjxh", domain.getYhCshDjxh());
		map.put("jbrCzyDjxh", domain.getJbrCzyDjxh());
		map.put("djJgbm", domain.getDjJgbm());
		map.put("zcflDm", domain.getZcflDm());
		map.put("zh", "%" + domain.getZh() + "%");
		map.put("rqQ", domain.getRqQ());
		map.put("rqZ", domain.getRqZ());

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectCwHbzcLsjlAll", map);
		return dataList;
	}

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void saveDomain(BaseBusinessDomain domain, UserDomain user)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
