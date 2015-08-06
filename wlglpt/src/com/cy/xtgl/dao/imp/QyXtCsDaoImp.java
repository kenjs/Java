package com.cy.xtgl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.DmXzxm;
import com.cy.common.bo.QyXtCs;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.dao.QyXtCsDao;
import com.cy.xtgl.domain.DmXzxmDomain;
import com.cy.xtgl.domain.QyXtCsDomain;
import com.cy.xtgl.domain.QyZzjgDomain;
/**
 * The DAO for 企业-系统-参数信息.
 * 
 * @author HCM
 */
@Repository
public class QyXtCsDaoImp implements QyXtCsDao{
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		map.put("xtgg", SysEncodeUtil.UTF82ISO("系统公共"));//sql中有字符串，防止乱码

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getQyXtCsRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyXtCsPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		map.put("xtgg", SysEncodeUtil.UTF82ISO("系统公共"));//sql中有字符串，防止乱码

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectQyXtCsAll", map);
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain) baseDomain;
		String ssJgdm = domain.getSsJgbm();
		Map<String,String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("csxh", domain.getCsxh());
		map.put("ssJgbm", domain.getSsJgbm());
		map.put("xtgg", SysEncodeUtil.UTF82ISO("系统公共"));//sql中有字符串，防止乱码
		
		//判断符合条件数据条数，取出需要的一条
		List<QyXtCsDomain> list = businessSqlMapClientTemplate.queryForList("selectQyXtCsByProperty", map);
		if(list.size()>1){
			for(QyXtCsDomain element:list){
				if(!element.getJgbm().equals("0")){
					domain = element;
				}
			}
		}else{
			domain = list.get(0);
		}
		domain.setSsJgbm(ssJgdm);
		
		//查询机构名称
		QyZzjgDomain dom = new QyZzjgDomain();
		dom.setJgbm(ssJgdm);
		dom = (QyZzjgDomain)businessSqlMapClientTemplate.queryForObject("selectQyZzjgByKey",dom);
		domain.setSsJgmc(dom.getMc());
		
		return domain;
	}
	
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getCsxh())){
			QyXtCsDomain dom = (QyXtCsDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}
		
		if(StringUtils.isNotEmpty(domain.getCsz())){
			String[]str = domain.getCsz().split(" ");
			if(str.length>1){
				domain.setXzxmValueDm(str[0]);
				domain.setXzxmValueMc(str[1]);
				//取出选择项目list
				List<DmXzxm> list = this.selectDmXzxm(domain.getXzxmDm());
				List<DmXzxmDomain> xzxmLsit = new ArrayList<DmXzxmDomain>();
				for(DmXzxm element:list){
					DmXzxmDomain dom = new DmXzxmDomain();
					BeanUtils.copyProperties(dom,element);
					dom.setCsz(element.getXzxmValueDm()+" "+element.getXzxmValueMc());
					xzxmLsit.add(dom);
				}
				domain.setDmXzxmList(xzxmLsit);
				System.out.println(domain.getDmXzxmList());
			}
		}
		
		
	}
	/**
	 * 根据选择项目代码取出对应list
	 * @param xzxmDm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DmXzxm> selectDmXzxm(String xzxmDm) {
		Map<String,String> map = new HashMap<String, String>();
		// 设置查询条件
		map.put("xzxmDm", xzxmDm);
		List<DmXzxm> list = businessSqlMapClientTemplate.queryForList("selectDmXzxmByXzxmDm", map);

		return list;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user)
			throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain)baseDomain;	
		QyXtCs bo = new QyXtCs();
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		QyXtCs boTemp = (QyXtCs)businessSqlMapClientTemplate.queryForObject("selectQyXtCsByKey", domain);
		
		if(boTemp != null){
			BeanUtils.copyProperties(bo,boTemp);
			if(StringUtils.isNotEmpty(domain.getXzxmDm())){
				bo.setCsz(domain.getXzxmValueDm());
			}else {
				bo.setCsz(domain.getCsz());
			}
			
			businessSqlMapClientTemplate.update("updateQyXtCs", bo);
		}else{
			bo.setSzdw(domain.getSsJgbm());
			bo.setCsxh(domain.getCsxh());
			bo.setCslbDm(domain.getCslbDm());
			if(StringUtils.isNotEmpty(domain.getXzxmDm())){
				bo.setCsz(domain.getXzxmValueDm());
			}else {
				bo.setCsz(domain.getCsz());
			}
			
			bo.setCjrCzyDjxh(user.getCzyDjxh());
			bo.setCjrq(SysDateUtil.getSqlDate().toString());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			
			businessSqlMapClientTemplate.insert("insertQyXtCs", bo);
		}
	}
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain)
			throws Exception {
		QyXtCsDomain domain = (QyXtCsDomain)baseDomain;
		System.out.println(domain);
		businessSqlMapClientTemplate.update("deleteQyXtCsByKey",domain);
		
	}


}
