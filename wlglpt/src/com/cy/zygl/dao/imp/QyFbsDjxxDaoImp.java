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
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.bo.QyFbsDjxx;
import com.cy.zygl.dao.QyFbsDjxxDao;
import com.cy.zygl.domain.QyFbsDjxxDomain;

/**
 * The DAOIMP for ��ҵ-�ְ���-�Ǽ���Ϣ.
 * 
 * @author HJH
 */

@Repository
public class QyFbsDjxxDaoImp implements QyFbsDjxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getFbsmc())){
			String s = domain.getFbsmc().trim();
			map.put("fbsmc","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyFbsDjxxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsDjxxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getFbsmc())){
			String s = domain.getFbsmc().trim();
			map.put("fbsmc","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyFbsDjxxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) baseDomain;
		QyFbsDjxx bo = new QyFbsDjxx();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		QyFbsDjxxDomain dom = (QyFbsDjxxDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			//bo.setFbsDjxh(domain.getFbsDjxh());
			bo.setFbsmc(domain.getFbsmc());
			bo.setFbsjc(domain.getFbsjc());
			String s=SysEncodeUtil.UTF82GBK(domain.getFbsjc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			
			bo.setXzqhDm(domain.getXzqhDm());
			bo.setDz(domain.getDz());
			bo.setDh(domain.getDh());
			bo.setYb(domain.getYb());
			bo.setFzr(domain.getFzr());
			bo.setFbsQybm(domain.getFbsQybm());
			bo.setBz(domain.getBz());
			
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());

			businessSqlMapClientTemplate.update("updateQyFbsDjxxByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			String s=SysEncodeUtil.UTF82GBK(domain.getFbsjc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			
			bo.setDjJgbm(user.getBmbm());
			bo.setDjrCzyDjxh(user.getCzyDjxh());
			bo.setDjrq(SysDateUtil.getSqlDate().toString());
			bo.setQybz("Y");
			bo.setYxbz("Y");
			
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQyFbsDjxx", bo);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("fbsDjxh", domain.getFbsDjxh());

		domain = (QyFbsDjxxDomain)businessSqlMapClientTemplate.queryForObject("selectQyFbsDjxxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("fbsDjxh", domain.getFbsDjxh());

		businessSqlMapClientTemplate.update("deleteQyFbsDjxxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyFbsDjxxDomain domain = (QyFbsDjxxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getFbsDjxh())){
			QyFbsDjxxDomain dom = (QyFbsDjxxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	/**
	 * ��ǰ�ְ��̵ĵǼ���Ų�����ʱ����ѯͬ�������Ƿ�����ͬ���ķְ�������
	 * ��ǰ�ְ��̵ĵǼ���Ŵ���ʱ����ѯ�Ƿ����������ķְ�������
	 * @param domain
	 * @throws Exception
	 */
	public int queryFbsmcCount(QyFbsDjxxDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("fbsDjxh", domain.getFbsDjxh());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("fbsmc", SysEncodeUtil.UTF82ISO(domain.getFbsmc()));
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("queryFbsmcCount", map))).intValue();
		return count;
	}
}
