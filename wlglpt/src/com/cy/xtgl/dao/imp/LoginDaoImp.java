package com.cy.xtgl.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QySwdnDsh;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.XtGnmkDomain;
import com.cy.common.domain.XtXtmlDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.security.MD5;
import com.cy.xtgl.dao.LoginDao;
import com.cy.xtgl.domain.QyRyGwDomain;
import com.cy.xtgl.domain.QySwdnDshDomain;


/**
 * 
* @Descriptoin 登录dao 
* @Note
* @author anq
* @since 2012-12-18 下午05:22:51 
* @version
 */
@Repository
public class LoginDaoImp extends ExtendDaoImp implements LoginDao{
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	
	public void checkUserInfo(UserDomain domain) throws Exception {
		Map<String, Object> parMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(domain.getQybm())) {
			parMap.put("qybm", domain.getQybm().toUpperCase());
		}
		if (StringUtils.isNotBlank(domain.getDlzh())) {
			parMap.put("dlzh", domain.getDlzh().toUpperCase());
		}
		
		MD5 md = new MD5();
		parMap.put("dlmm", md.getMD5ofStr(domain.getDlmm()));
		parMap.put("macAddr", domain.getMacAddr());
		parMap.put("rtnCode", null);
		parMap.put("rtnMess", null);
		parMap.put("czyDjxh", null);
		
		businessSqlMapClientTemplate.queryForObject("checkUserInfoPro", parMap);
		Integer rtnCode = (Integer)parMap.get("rtnCode");
		String rtnMess = (String)parMap.get("rtnMess");
		String czyDjxh = (String)parMap.get("czyDjxh");
		
		domain.setCzyDjxh(czyDjxh);
		domain.setRtnCode(rtnCode);
		domain.setRtnMess(SysEncodeUtil.ISO2GBK(rtnMess));
	}
	
	@SuppressWarnings("unchecked")
	public UserDomain getUserInfo(UserDomain domain) throws Exception {
		Map<String, Object> parMap = new HashMap<String, Object>();
		List<UserDomain> list = null;
		parMap.put("czyDjxh", domain.getCzyDjxh());
		parMap.put("gwDjxh", domain.getGwDjxh());
		parMap.put("userInfoList", list);
		businessSqlMapClientTemplate.queryForObjectByCurr("getUserInfo", "userInfoList", parMap);
		
		list = (List<UserDomain>)parMap.get("userInfoList");
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 账号检测成功，获取用户所属菜单栏
	 */
	@SuppressWarnings("unchecked")
	public List<XtGnmkDomain> getUserMenu(UserDomain domain, UserDomain userDomain) throws Exception{
		List<XtGnmkDomain> returnList = new ArrayList<XtGnmkDomain>();
		Map<String,Object> parameterMap = new HashMap<String,Object>();   
		if(userDomain != null){			
			parameterMap.put("czyDjxh", userDomain.getCzyDjxh());			
			if (StringUtils.isBlank(domain.getXtmlXh())) {
				String xtmlXh = queryLatestOprXtml(userDomain.getCzyDjxh());
				if (StringUtils.isBlank(xtmlXh)) {
					xtmlXh = "1009";
				}
				domain.setXtmlXh(xtmlXh);
			}
			parameterMap.put("xtmlXh", domain.getXtmlXh());
			parameterMap.put("curMemuList",null);
			businessSqlMapClientTemplate.queryForObjectByCurr("getCurMemuList","curMemuList",parameterMap); 
			returnList = (List<XtGnmkDomain>)parameterMap.get("curMemuList");	
		}	
		
		if (returnList == null || returnList.size() == 0)
			returnList = new ArrayList<XtGnmkDomain>();
		return returnList;
	}
	
	public String queryLatestOprXtml(String czyDjxh) throws Exception {
		Map<String,Object> parameterMap = new HashMap<String,Object>();   
		parameterMap.put("czyDjxh", czyDjxh);
		Object xh = businessSqlMapClientTemplate.queryForObject("queryLatestOprXtml", parameterMap);
		if (xh != null) {
			return xh.toString();
		}else {
			return null;
		}
	}
	
	//添加上网电脑待审批信息 2013-10-12 update by xiay
	public void saveSwdnDsh(QySwdnDsh qySwdnDsh) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		QySwdnDsh bo = new QySwdnDsh();
		// 判断mac地址与用户是否存在没审核的记录
		map.put("mac", qySwdnDsh.getMac());
		map.put("czyDjxh", qySwdnDsh.getCzyDjxh());
		
		QySwdnDshDomain swsh = (QySwdnDshDomain) businessSqlMapClientTemplate.queryForObject("selectByMac",map);
		if(swsh==null){
			businessSqlMapClientTemplate.insert("insertSwdnDsh", qySwdnDsh);
		}else{
			//已经有未审核记录只修改申请时间
			BeanUtils.copyProperties(bo, swsh);
			bo.setShrq(SysDateUtil.getCurrentDate());//申请日期
			businessSqlMapClientTemplate.update("updateSwdnDshByKey",bo);
		}
	}
	
	public List<XtXtmlDomain> getTopMenu(UserDomain userDomain) throws Exception {
		List<XtXtmlDomain> topMenuList = new ArrayList<XtXtmlDomain>();
		Map<String,Object> parameterMap = new HashMap<String,Object>();  
		
		parameterMap.put("czyDjxh",userDomain.getCzyDjxh());
		parameterMap.put("topMemuList",null);
		businessSqlMapClientTemplate.queryForObjectByCurr("getTopMemuList","topMemuList",parameterMap); 
		topMenuList = (List<XtXtmlDomain>)parameterMap.get("topMemuList");
		
		return topMenuList;
	}
	
	public List<XtGnmkDomain> queryLatestOprMenu(UserDomain domain, UserDomain userDomain) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("czyDjxh", userDomain.getCzyDjxh());
		
		List<XtGnmkDomain> latestOprMenuList = businessSqlMapClientTemplate.queryForList("queryLatestOprMenu", paramMap);
		return latestOprMenuList;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryGw(String czyDjxh) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("czyDjxh", czyDjxh);
		List<BaseBusinessDomain> list = businessSqlMapClientTemplate.queryForList("queryGwqhList", map);
		QyRyGwDomain a = (QyRyGwDomain) list.get(0);
		System.out.println(a);
		return list;
	}
	
}
