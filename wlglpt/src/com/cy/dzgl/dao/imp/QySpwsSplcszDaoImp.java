package com.cy.dzgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QySpwsSplcsz;
import com.cy.common.bo.QySpwsSplcszZb;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.dzgl.dao.QySpwsSplcszDao;
import com.cy.dzgl.domain.QySpwsSplcszDomain;
import com.cy.dzgl.domain.QySpwsSplcszZbDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;

/**
 * The DAOIMP for ��ҵ-��������-������������.
 * 
 * @author anq
 */

@Repository
public class QySpwsSplcszDaoImp implements QySpwsSplcszDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		//�ܹ�˾
		map.put("zgsbm",domain.getZgsbm());
		//jgbmΪ���Ų����ڣ�Ϊ��λ
		if(StringUtils.isBlank(domain.getSsJgbm())){
			if(StringUtils.isNotBlank(domain.getDwDm())){
				map.put("jgbm",domain.getDwDm());
			}
		}else{
			map.put("jgbm",domain.getSsJgbm());
		}
		//����
		if(StringUtils.isNotBlank(domain.getWsDm())){
			map.put("wsDm", SysEncodeUtil.UTF82ISO(domain.getWsDm()));
		}
		//��Ŀ����
		if(StringUtils.isNotBlank(domain.getXmflDjxh())){
			map.put("xmflDjxh", domain.getXmflDjxh());
		}

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQySpwsSplcszRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQySpwsSplcszPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		//�ܹ�˾
		map.put("zgsbm",domain.getZgsbm());
		//jgbmΪ���Ų����ڣ�Ϊ��λ
		if(StringUtils.isBlank(domain.getSsJgbm())){
			if(StringUtils.isNotBlank(domain.getDwDm())){
				map.put("jgbm",domain.getDwDm());
			}
		}else{
			map.put("jgbm",domain.getSsJgbm());
		}
		//����
		if(StringUtils.isNotBlank(domain.getWsDm())){
			map.put("wsDm", SysEncodeUtil.UTF82ISO(domain.getWsDm()));
		}
		//��Ŀ����
		if(StringUtils.isNotBlank(domain.getXmflDjxh())){
			map.put("xmflDjxh", domain.getXmflDjxh());
		}

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQySpwsSplcszAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		QySpwsSplcsz bo = new QySpwsSplcsz();
		
		QySpwsSplcszDomain dom=(QySpwsSplcszDomain)this.getDomainByKey(domain);

		if (dom!=null) {
			BeanUtils.copyProperties(dom,bo);
			
			bo.setZssx(domain.getZssx());
			bo.setGzrbz(domain.getGzrbz());
			bo.setQzxsbz(domain.getQzxsbz());
			
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateQySpwsSplcszByKey", bo);
		}else{ //����
			BeanUtils.copyProperties(domain,bo);
			
			//�������Ϊ�գ���ȡ��λ
			if(SysToolsUtil.isNullOrEmpty(domain.getSsJgbm())){
				bo.setSsJgbm(domain.getDwDm());
			}else{
				bo.setSsJgbm(domain.getSsJgbm());
			}
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertQySpwsSplcsz", bo);
		}
		domain.setSplcSzxh(bo.getSplcSzxh());
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// ����������ѯ����
		map.put("splcSzxh", domain.getSplcSzxh());

		domain = (QySpwsSplcszDomain)businessSqlMapClientTemplate.queryForObject("selectQySpwsSplcszByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// ������������
		map.put("splcSzxh", domain.getSplcSzxh());

		businessSqlMapClientTemplate.update("deleteQySpwsSplcszByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		if(domain.getSplcSzxh() != null && domain.getSplcSzxh().longValue() > 0){
			QySpwsSplcszDomain dom = (QySpwsSplcszDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom, domain);
			}
		}

	}
	
	public BaseBusinessDomain getSpjcDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsSplcszZbDomain domain = (QySpwsSplcszZbDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// ����������ѯ����
		map.put("splcSzxh", domain.getSplcSzxh());
		map.put("jdxh", domain.getJdxh());

		domain = (QySpwsSplcszZbDomain)businessSqlMapClientTemplate.queryForObject("selectQySpwsSplcszZbByKey", map);
		return domain;
	}

	public void deleteSpjcBySplcSzxh(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		Map<String,Object> map = new HashMap<String, Object>();
		// ������������
		map.put("splcSzxh", domain.getSplcSzxh());

		businessSqlMapClientTemplate.update("deleteSpjcBySplcSzxh", map);
	}
	
	private Long queryNextJdxh(Long splcSzxh) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("splcSzxh", splcSzxh);
		Long jdxh = (Long)businessSqlMapClientTemplate.queryForObject("queryNextJdxh", map);
		return jdxh;
	}

	public void initSpjcDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QySpwsSplcszDomain spDomain = (QySpwsSplcszDomain) baseDomain;
		QySpwsSplcszZbDomain domain = spDomain.getSpjcDomain();
		if(domain.getSplcSzxh() != null && domain.getSplcSzxh().longValue() > 0 
				&& StringUtils.isNotBlank(domain.getJdxh())){
			QySpwsSplcszZbDomain dom = (QySpwsSplcszZbDomain) this.getSpjcDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(dom, domain);
				spDomain.setExistsBz("Y");
			}
		}else {
			Long jdxh = this.queryNextJdxh(domain.getSplcSzxh());
			domain.setJdxh(jdxh.toString());
		}

	}
	
	public void saveSpjc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszZb spzb = new QySpwsSplcszZb();
		QySpwsSplcszZbDomain domain = ((QySpwsSplcszDomain) baseDomain).getSpjcDomain();
		
		QySpwsSplcszZbDomain dom = (QySpwsSplcszZbDomain) this.getSpjcDomainByKey(domain);
		if(domain.getQzxs()==0)
			domain.setQzxs(null);
		//�޸�
		if(dom!=null){
			BeanUtils.copyProperties(dom, spzb);
			spzb.setSpjdsm(domain.getSpjdsm());
			spzb.setSpJgbm(domain.getSpJgbm());
			spzb.setGwDjxh(domain.getGwDjxh());
			spzb.setSphjsm(domain.getSphjsm());
			spzb.setSpyjl(domain.getSpyjl());
			spzb.setSpqm(domain.getSpqm());
			spzb.setQzxs(domain.getQzxs());
			spzb.setZstj(domain.getZstj());
			spzb.setYxzsBz(domain.getYxzsBz());
			spzb.setSpsx(domain.getSpsx());
			spzb.setGzrbz(domain.getGzrbz());
			spzb.setFsbz(domain.getFsbz());
			
			spzb.setXgrCzyDjxh(userDomain.getCzyDjxh());
			spzb.setXgrq(SysDateUtil.getSqlDate());
			spzb.setYxbz("Y");
			businessSqlMapClientTemplate.update("updateQySpwsSplcszZbByKey", spzb);
		}else {  //����
			BeanUtils.copyProperties(domain, spzb);
			spzb.setCjrCzyDjxh(userDomain.getCzyDjxh());
			spzb.setCjrq(SysDateUtil.getSqlDate());
			spzb.setXgrCzyDjxh(userDomain.getCzyDjxh());
			spzb.setXgrq(SysDateUtil.getSqlDate());
			spzb.setYxbz("Y");
			businessSqlMapClientTemplate.insert("insertQySpwsSplcszZb", spzb);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<QySpwsSplcszZbDomain> querySplcszZbList(Long splcSzxh) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("splcSzxh", splcSzxh);
		
		List<QySpwsSplcszZbDomain> zbList = businessSqlMapClientTemplate.queryForList("querySplcszZbList", map);
		
		return zbList;
	}
	
	public void deleteSplcZb(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("splcSzxh", domain.getSplcSzxh());
		
		businessSqlMapClientTemplate.delete("deleteSplcZb", map);
	}
	
	public String queryQySpwsXmflbzByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) baseDomain;
		SysEncodeUtil.decodeURL(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jgbm", userDomain.getQyZcxh());
		map.put("wsDm", domain.getWsDm());
		
		String xmflbz = (String)businessSqlMapClientTemplate.queryForObject("queryQySpwsXmflbzByKey", map);
		domain.setXmflbz(xmflbz);
		return xmflbz;
	}
	
	public void queryWsxx(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		// ����������ѯ����
		map.put("zgsbm", domain.getZgsbm());
		if(!SysToolsUtil.isNullOrEmpty(domain.getWsDm()))
			map.put("wsDm", domain.getWsDm());
		if(!SysToolsUtil.isNullOrEmpty(domain.getXmflDjxh()) && !"0".equals(domain.getXmflDjxh()))
			map.put("xmflDjxh", domain.getXmflDjxh());

		QySpwsSplcszDomain dom = (QySpwsSplcszDomain)businessSqlMapClientTemplate.queryForObject("selectWsxx", map);
		if(null==dom)
			return;
		domain.setWsDm(dom.getWsDm());
		domain.setWsMc(dom.getWsMc());
		domain.setWsJc(dom.getWsJc());
		domain.setSm(dom.getSm());
		domain.setXmflDjxh(dom.getXmflDjxh());
		domain.setXmflmc(dom.getXmflmc());
		domain.setWsspmsDm(dom.getWsspmsDm());
		domain.setWsspmsMc(dom.getWsspmsMc());
		domain.setYwflMc(dom.getYwflMc());
		domain.setYwhjMc(dom.getYwhjMc());
		domain.setQzxsbz(dom.getQzxsbz());
	}
	
	public void queryWssplcsz(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception{
		if(domain.getSplcSzxh() == null || domain.getSplcSzxh().longValue() <= 0)
			return;
			
		Map<String,Object> map = new HashMap<String, Object>();
		// ����������ѯ����
		map.put("splcSzxh", domain.getSplcSzxh());

		QySpwsSplcszDomain dom = (QySpwsSplcszDomain)businessSqlMapClientTemplate.queryForObject("selectWssplcszByKey", map);
		if(null==dom){
			domain.setZssx(null);
			domain.setGzrbz("1");
			return;
		}
		domain.setExistsBz("Y");//�ô��ڱ�־
		domain.setSsJgbm(dom.getSsJgbm());
		domain.setDwMc(dom.getDwMc());
		domain.setSsJgmc(dom.getSsJgmc());
		domain.setZssx(dom.getZssx());
		domain.setGzrbz(dom.getGzrbz());
		domain.setQzxsbz(dom.getQzxsbz());
	}
	
	public void saveCxszSplc(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception{
		QySpwsSplcszDomain dom = (QySpwsSplcszDomain) this.getDomainByKey(domain);
		if(null==dom)
			return;
		QySpwsSplcsz bo = new QySpwsSplcsz();
		
		//��ȡ�ɵ����������������
		Long splcSzXh=domain.getSplcSzxh();

	    //��������
		BeanUtils.copyProperties(dom,bo);
		//�ÿվɵ����������������
		bo.setSplcSzxh(null);
		//�õ�ǰ��λ���߲���
		bo.setSsJgbm(domain.getCurDwbm());
		
		bo.setCjrCzyDjxh(userDomain.getCzyDjxh());
		bo.setCjrq(SysDateUtil.getSqlDate().toString());
		bo.setXgrCzyDjxh(userDomain.getCzyDjxh());
		bo.setXgrq(SysDateUtil.getSqlDate().toString());

		businessSqlMapClientTemplate.insert("insertQySpwsSplcsz", bo);
		domain.setSplcSzxh(bo.getSplcSzxh());
		//�����ӱ�
		//���ݾɵ���������������Ų��Ҿɵ��ӱ���Ϣ
		List<QySpwsSplcszZbDomain> zbList=this.querySplcszZbList(splcSzXh);
		if(null ==zbList || zbList.isEmpty())
			return;
		QySpwsSplcszZb zbBo=null;
		for (QySpwsSplcszZbDomain zb : zbList) {
			zbBo=new QySpwsSplcszZb();
			BeanUtils.copyProperties(zb,zbBo);
			//���µ����������������
			zbBo.setSplcSzxh(domain.getSplcSzxh());
			
			zbBo.setCjrCzyDjxh(userDomain.getCzyDjxh());
			zbBo.setCjrq(SysDateUtil.getSqlDate());
			zbBo.setXgrCzyDjxh(userDomain.getCzyDjxh());
			zbBo.setXgrq(SysDateUtil.getSqlDate());
			zbBo.setYxbz("Y");
			
			businessSqlMapClientTemplate.insert("insertQySpwsSplcszZb", zbBo);
			zbBo=null;
		}
		
	}
	
	public void deleteQxszSplc(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception{
		//ɾ������
		this.deleteByKey(domain, userDomain);
		//ɾ���ӱ�
		this.deleteSpjcBySplcSzxh(domain, userDomain);
	}

}
