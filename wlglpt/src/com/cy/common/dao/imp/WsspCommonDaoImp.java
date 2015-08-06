package com.cy.common.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cy.common.dao.WsspCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.common.domain.WssplzDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;


@Repository
public class WsspCommonDaoImp implements WsspCommonDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	//���������ж�
	public void queryWszsJudge(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wsspxh", domain.getWsspxh());
		map.put("spxh", domain.getSpxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("queryWszsJudge", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		//rtnCode=0,=1,0 ��������1 ����������, -1 ��������
		if (rtnCode != 0 && rtnCode!=1 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
		if(rtnCode==0)
			domain.setJudgeBz(true);//��������
		if(rtnCode==1)
			domain.setJudgeBz(false);//��������
	}
	//��������
	public void saveJudge(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wsspxh", domain.getWsspxh());
		map.put("spxh", domain.getSpxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("saveJudge", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	//�����˻�
	public void saveBack(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wsspxh", domain.getWsspxh());
		map.put("spxh", domain.getSpxh());
		map.put("czyDjxh", user.czyDjxh);
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("saveBack", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	//����������ת��ѯ
	@SuppressWarnings("unchecked")
	public List<WssplzDomain> querySplzcx(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<WssplzDomain> dataList=new ArrayList<WssplzDomain>();
		map.put("wsspxh", domain.getWsspxh());
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("querySplzcx","dataList",map);
		dataList=(List<WssplzDomain>)map.get("dataList");
		return dataList;
	}
	//�����б�
	@SuppressWarnings("unchecked")
	public WsspCommonDomain queryFsSelect(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<WsspCommonDomain> dataList=new ArrayList<WsspCommonDomain>();
		WsspCommonDomain dom=null;
		map.put("wsspxh", domain.getWsspxh());
		map.put("spxh", domain.getSpxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryFsSelect","dataList",map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		
		if(domain.isNextWssp()){
			if(2==rtnCode){
				domain.setRtnCode(String.valueOf(rtnCode));
				return null;
			}
			
			if (rtnCode != 0 && rtnCode != 2 && StringUtils.isNotBlank(rtnMess)) {
				throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
			}
			
		}else{
			if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
				throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
			}
		}
		
		
		dataList=(List<WsspCommonDomain>)map.get("dataList");
		
		if(null==dataList || dataList.isEmpty())
			return dom;
		dom=(WsspCommonDomain)dataList.get(0);
		return dom;
	}
	//����
	public void saveSend(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wsspxh", domain.getWsspxh());
		map.put("spxh", domain.getSpxh());
		map.put("sprJdxh", domain.getSprJdxh());
		map.put("sprCzyDjxh", domain.getSprCzyDjxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("saveSend", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	//��ȡ������ͨ�������˻���
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> querySprListBySpJgbm(String spJgbm) throws Exception{
		List<DmbGgDomain> dataList=new ArrayList<DmbGgDomain>();
		if(SysToolsUtil.isNullOrEmpty(spJgbm))
			return dataList;
		Map<String, String> map = new HashMap<String, String>();
		map.put("ssJgbm", spJgbm);
		dataList=businessSqlMapClientTemplate.queryForList("queryWssprList", map);
		return dataList;
	}
	//��ȡ������ͨ����λ�Ǽ����
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> querySprListByGwDjxh(String gwDjxh) throws Exception{
		List<DmbGgDomain> dataList=new ArrayList<DmbGgDomain>();
		if(SysToolsUtil.isNullOrEmpty(gwDjxh))
			return dataList;
		Map<String, String> map = new HashMap<String, String>();
		map.put("gwDjxh", gwDjxh);
		dataList=businessSqlMapClientTemplate.queryForList("queryWssprList", map);
		return dataList;
	}
	
	//��ȡ������ͨ������λ
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryWssprByBdwList(String bdwDm) throws Exception{
		List<DmbGgDomain> dataList=new ArrayList<DmbGgDomain>();
		if(SysToolsUtil.isNullOrEmpty(bdwDm))
			return dataList;
		Map<String, String> map = new HashMap<String, String>();
		map.put("ssJgbm", bdwDm);
		dataList=businessSqlMapClientTemplate.queryForList("queryWssprByBdwList", map);
		return dataList;
	}
	
	//��ȡ������ͨ���ܹ�˾
	@SuppressWarnings("unchecked")
	public List<DmbGgDomain> queryWssprByZgsList(String zgsDm) throws Exception{
		List<DmbGgDomain> dataList=new ArrayList<DmbGgDomain>();
		if(SysToolsUtil.isNullOrEmpty(zgsDm))
			return dataList;
		Map<String, String> map = new HashMap<String, String>();
		map.put("ssJgbm", zgsDm);
		dataList=businessSqlMapClientTemplate.queryForList("queryWssprByZgsList", map);
		return dataList;
	}
	
	public String queryWsspjUrl(String wsspxh) throws Exception{
		if(SysToolsUtil.isNullOrEmpty(wsspxh))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("wsspxh", wsspxh);
		String spjUrl=(String) businessSqlMapClientTemplate.queryForObject("queryWsspjUrl", map);
		return spjUrl;
	}

	//�״η����б�
	@SuppressWarnings("unchecked")
	public WsspCommonDomain queryScFsSelect(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<WsspCommonDomain> dataList=new ArrayList<WsspCommonDomain>();
		WsspCommonDomain dom=null;
		map.put("wsDm", domain.getWsDm());
		map.put("wsXmflDjxh", domain.getWsXmflDjxh());
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			map.put("jgbm", domain.getJgbm());
		}else {
			map.put("jgbm", user.bmbm);
		}		
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		map.put("dataList", dataList);
		
		businessSqlMapClientTemplate.queryForObjectByCurr("queryScFsSelect","dataList",map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		
		if (rtnCode != 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
		
		
		dataList=(List<WsspCommonDomain>)map.get("dataList");
		
		if(null==dataList || dataList.isEmpty())
			return dom;
		dom=(WsspCommonDomain)dataList.get(0);
		return dom;
	}
	
	//�״η���
	public void saveScSend(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wsDm", domain.getWsDm());
		map.put("wsXmflDjxh", domain.getWsXmflDjxh());
		map.put("ywDjxh", domain.getYwDjxh());
		map.put("fsCzyDjxh", user.czyDjxh);
		map.put("wssplzszxh", domain.getWssplzszxh());
		map.put("sprCzyDjxh", domain.getSprCzyDjxh());
		map.put("oldWsspxh", domain.getOldWsspxh());
		map.put("rtnCode", 0);
		map.put("rtnMess", "");
		
		businessSqlMapClientTemplate.queryForObject("saveScSend", map);
		Integer rtnCode = (Integer)map.get("rtnCode");
		String rtnMess = (String)map.get("rtnMess");
		if (rtnCode <= 0 && StringUtils.isNotBlank(rtnMess)) {
			throw new DiyServiceException(SysEncodeUtil.ISO2GBK(rtnMess));
		}
	}
	//��ȡ���� ���������������
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wsDm", domain.getWsDm());
		map.put("jgbm", user.bmbm);
		map.put("wsXmflDjxh",domain.getWsXmflDjxh());
		
		
		businessSqlMapClientTemplate.queryForObject("queryWssplcszxh", map);
		Long wssplzszxh = (Long)map.get("wssplzszxh");
		
		if (wssplzszxh <= 0) {
			throw new DiyServiceException("������δ�����������̣����ܷ������������ȶ�����������");
		}
		
		return String.valueOf(wssplzszxh);
	}

}
