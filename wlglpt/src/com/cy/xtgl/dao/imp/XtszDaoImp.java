package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.XtglXtsz;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.security.MD5;
import com.cy.xtgl.dao.XtszDao;
import com.cy.xtgl.domain.XtszDomain;

@Repository
public class XtszDaoImp implements XtszDao {

	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	public boolean checkCzypwd(XtszDomain domain, UserDomain userDomain) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("czyDjxh", userDomain.getCzyDjxh());
		MD5 md = new MD5();
		map.put("pwd", md.getMD5ofStr(domain.getPwd()));
		
		int count = (Integer) businessSqlMapClientTemplate.queryForObject("getXtszBypwd",map);
		if(count == 0){
			return false;
		}
		return true;
	}
	
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XtszDomain domain = (XtszDomain) baseDomain;
		XtglXtsz bo = new XtglXtsz();
		
		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		XtszDomain dom = (XtszDomain) this.getDomainByKey(domain,user);
		BeanUtils.copyProperties(bo, dom);
		
		if("1".equals(domain.getFlag())){
			MD5 md = new MD5();
			bo.setPwd(md.getMD5ofStr(domain.getPwdNew()));
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateXtszByKey", bo);
		}else if("2".equals(domain.getFlag())){
			bo.setSjdh(domain.getSjdh());
			bo.setSjhm(domain.getSjhm());
			bo.setBgdh(domain.getBgdh());
			bo.setBgdhao(domain.getBgdhao());
			bo.setJtdh(domain.getJtdh());
			bo.setQq(domain.getQq());
			bo.setMsn(domain.getMsn());
			bo.setEmail(domain.getEmail());
			bo.setXgrCzyDjxh(user.getCzyDjxh());
			bo.setXgrq(SysDateUtil.getSqlDate().toString());
			businessSqlMapClientTemplate.update("updateXtszByKey", bo);
		}else if("3".equals(domain.getFlag())){
			String[] csxhs = domain.getCsxhs().split(",");
			String[] csmrz = domain.getCsmrzs().split(",");
			for (int i = 0; i < csxhs.length; i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("csxh", csxhs[i]);
				map.put("csmrz", SysEncodeUtil.UTF82ISO(csmrz[i]));
				businessSqlMapClientTemplate.update("updateXtszXtcs", map);
			}
		}
	}
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XtszDomain domain = (XtszDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("czyDjxh", user.getCzyDjxh());

		domain = (XtszDomain)businessSqlMapClientTemplate.queryForObject("selectXtszByKey", map);
		return domain;
	}

	@SuppressWarnings("unchecked")
	public void initDomainMx(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XtszDomain domain = (XtszDomain) baseDomain;
		if(StringUtils.isNotBlank(user.getCzyDjxh())){
			XtszDomain dom = (XtszDomain) this.getDomainByKey(domain,user);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
				domain.setPwd("");
			}
		}
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectXtXtcsYx");
		String csxhs = "";
		for (Iterator iter = dataList.iterator(); iter.hasNext();) {
			XtszDomain element = (XtszDomain) iter.next();
			if("".equals(csxhs)){
				csxhs = element.getCsxh();
			}else{
				csxhs = csxhs +","+ element.getCsxh();
			}
		}
		domain.setDataList(dataList);
		domain.setCsxhs(csxhs);
	}
	
	@SuppressWarnings("unchecked")
	public List<XtszDomain> queryXzxmList() throws Exception {
		List<XtszDomain> dataList = businessSqlMapClientTemplate.queryForList("selectXtDmXzxm");
		return dataList;
	}
}
