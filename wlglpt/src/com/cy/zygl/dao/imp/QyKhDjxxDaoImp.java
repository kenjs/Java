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
import com.cy.common.bo.QyKhDjxx;
import com.cy.zygl.dao.QyKhDjxxDao;
import com.cy.zygl.domain.QyKhDjxxDomain;

/**
 * The DAOIMP for ��ҵ-�ͻ�-�Ǽ���Ϣ.
 * 
 * @author HJH
 */

@Repository
public class QyKhDjxxDaoImp implements QyKhDjxxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getDjJgbm())){
			map.put("djJgbm", domain.getDjJgbm());
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			String s = domain.getFhrMc().trim();
			map.put("khmc","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyKhDjxxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhDjxxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			String s = domain.getFhrMc().trim();
			map.put("khmc","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyKhDjxxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain) baseDomain;
		QyKhDjxx bo = new QyKhDjxx();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		QyKhDjxxDomain dom = (QyKhDjxxDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			//bo.setKhDjxh(domain.getKhDjxh());
			bo.setKhmc(domain.getKhmc());
			bo.setKhjc(domain.getKhjc());
			
			String s=SysEncodeUtil.UTF82GBK(domain.getKhmc());
			bo.setPyqc(CnToSpellUtil.getFullSpell(s));
			bo.setPyjc(CnToSpellUtil.getFirstSpell(s));
			
			bo.setXzqhDm(domain.getXzqhDm());
			bo.setDz(domain.getDz());
			bo.setDh(domain.getDh());
			bo.setYb(domain.getYb());
			bo.setKhbm(domain.getKhbm());
			bo.setFzr(domain.getFzr());
			bo.setBz(domain.getBz());
			bo.setKhlxDm(domain.getKhlxDm());
			bo.setYkjsfsDm(domain.getYkjsfsDm());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			bo.setXxgxfsDm(domain.getXxgxfsDm());

			businessSqlMapClientTemplate.update("updateQyKhDjxxByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);
			
			String s=SysEncodeUtil.UTF82GBK(domain.getKhmc());
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

			businessSqlMapClientTemplate.insert("insertQyKhDjxx", bo);
			domain.setKhDjxh(bo.getKhDjxh());
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("khDjxh", domain.getKhDjxh());

		domain = (QyKhDjxxDomain)businessSqlMapClientTemplate.queryForObject("selectQyKhDjxxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("khDjxh", domain.getKhDjxh());

		businessSqlMapClientTemplate.update("deleteQyKhDjxxByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyKhDjxxDomain domain = (QyKhDjxxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getKhDjxh())){
			QyKhDjxxDomain dom = (QyKhDjxxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
	
	
	/**
	 * ��ǰ�ͻ��ĵǼ���Ų�����ʱ����ѯͬ�������Ƿ�����ͬ���Ŀͻ�����
	 * ��ǰ�ͻ��ĵǼ���Ŵ���ʱ����ѯ�Ƿ����������Ŀͻ�����
	 * @param domain
	 * @throws Exception
	 */
	public int queryKhmcCount(QyKhDjxxDomain domain) throws Exception{
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("khDjxh", domain.getKhDjxh());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("khmc", SysEncodeUtil.UTF82ISO(domain.getKhmc()));
		
		count = ((Integer)(businessSqlMapClientTemplate.queryForObject("queryKhmcCount", map))).intValue();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<QyKhDjxxDomain> queryXzqhList(QyKhDjxxDomain domain) throws Exception{
		//Map<String,String> map = new HashMap<String, String>();
		// ������������
		List<QyKhDjxxDomain>  data = businessSqlMapClientTemplate.queryForList("queryXzqhList", null);
		return data;
	}
}
