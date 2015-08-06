package com.cy.bggl.dao.imp;

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
import com.cy.common.bo.BgZlgx;
import com.cy.common.bo.BgZlgxFj;
import com.cy.bggl.dao.BgZlgxDao;
import com.cy.bggl.domain.BgZlgxDomain;

/**
 * The DAOIMP for �칫-���Ϲ���.
 * 
 * @author HJH
 */

@Repository
public class BgZlgxDaoImp implements BgZlgxDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		
		if(StringUtils.isNotBlank(domain.getCjrCzyDjxh())){
			map.put("cjrCzyDjxh", domain.getCjrCzyDjxh());
		}
		if(StringUtils.isNotBlank(domain.getZlmc())){
			String s = domain.getZlmc().trim();
			map.put("zlmc","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}

		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getBgZlgxRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgZlgxPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		
		if(StringUtils.isNotBlank(domain.getCjrCzyDjxh())){
			map.put("cjrCzyDjxh", domain.getCjrCzyDjxh());
		}
		if(StringUtils.isNotBlank(domain.getZlmc())){
			String s = domain.getZlmc().trim();
			map.put("zlmc","%"+SysEncodeUtil.UTF82ISO(s)+"%");
		}
		if(StringUtils.isNotBlank(domain.getRqQ())){
			map.put("rqQ", domain.getRqQ());
		}
		if(StringUtils.isNotBlank(domain.getRqZ())){
			map.put("rqZ", domain.getRqZ());
		}
		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectBgZlgxAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain) baseDomain;
		BgZlgx bo = new BgZlgx();
		int fjxh = 1;//�������
		BgZlgxFj boFj=null;

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		BgZlgxDomain dom = (BgZlgxDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setJgbm(domain.getJgbm());
			bo.setFbrq(SysDateUtil.getCurrentDate());
			bo.setBcztDm(domain.getBcztDm());
			bo.setLy(domain.getLy());
			bo.setZlmc(domain.getZlmc());
			bo.setSm(domain.getSm());
			if(!"Y".equals(domain.getXjgxbz()))
				bo.setXjgxbz("N");
			else
				bo.setXjgxbz(domain.getXjgxbz());

			bo.setYxbz("Y");
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.update("updateBgZlgxByKey", bo);
			
			//�޸Ĳ���ʱ���ж��Ƿ���Ҫ��Ӹ�������Ҫʱ���ȡ���ݿ������ĸ������
			if(domain.getUploadNameList().size() >0){
				fjxh = this.getMaxFjxh(domain);
			}
		}else{
			BeanUtils.copyProperties(bo, domain);
			bo.setFbrq(SysDateUtil.getCurrentDate());
			if(!"Y".equals(domain.getXjgxbz()))
				bo.setXjgxbz("N");
			
			bo.setYxbz("Y");
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());

			businessSqlMapClientTemplate.insert("insertBgZlgx", bo);
			domain.setZlgxDjxh(bo.getZlgxDjxh());
		}
		
		//���渽��
		for (int i = 0; i < domain.getUploadNameList().size(); i++) {
			boFj=new BgZlgxFj();
			boFj.setZlgxDjxh(domain.getZlgxDjxh());
			boFj.setXh(String.valueOf(fjxh+i));
			boFj.setFjmc(domain.getUploadNameList().get(i));
			boFj.setYxbz("Y");
			boFj.setFjnr(domain.getUploadValueList().get(i));
			businessSqlMapClientTemplate.insert("insertBgZlgxFj", boFj);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("zlgxDjxh", domain.getZlgxDjxh());

		domain = (BgZlgxDomain)businessSqlMapClientTemplate.queryForObject("selectBgZlgxByKey", map);
		return domain;
	}

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("zlgxDjxh", domain.getZlgxDjxh());

		businessSqlMapClientTemplate.update("deleteBgZlgxByKey", map);
	}
	
	/**
	 * ɾ������
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void deleteFj(BgZlgxDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("zlgxDjxh", domain.getZlgxDjxh());
		map.put("xh", domain.getXh());

		businessSqlMapClientTemplate.update("deleteBgZlgxFjByKey", map);
	}
	/**
	 * ��ѯ����
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void queryFjByKey(BgZlgxDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		map.put("zlgxDjxh", domain.getZlgxDjxh());
		map.put("xh", domain.getXh());
		BgZlgxDomain dom = (BgZlgxDomain)businessSqlMapClientTemplate.queryForObject("selectBgZlgxFjByKey", map);
		if(dom !=null){
			domain.setFjmc(dom.getFjmc());
			domain.setFjnr(dom.getFjnr());
		}
	}
	
	/**
	 * ��ѯ�����б�
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BgZlgxDomain> queryFj(BgZlgxDomain domain,UserDomain user)throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("zlgxDjxh", domain.getZlgxDjxh());

		List<BgZlgxDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgZlgxFjList", map);		
		return dataList;
	}
	
	
	@SuppressWarnings("unchecked")
	public int getMaxFjxh(BgZlgxDomain domain) throws Exception {
		int fjxh = 0;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		if(StringUtils.isNotBlank(domain.getZlgxDjxh())){
			map.put("zlgxDjxh", domain.getZlgxDjxh());
		}else{
			return 1;
		}
		
		fjxh = ((Integer) businessSqlMapClientTemplate.queryForObject("getMaxFjxhByZlgxDjXh", map)).intValue();		
		return fjxh+1;
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		BgZlgxDomain domain = (BgZlgxDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getZlgxDjxh())){
			BgZlgxDomain dom = (BgZlgxDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
