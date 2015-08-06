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
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.bo.CwYsyf;
import com.cy.common.bo.XyjsZbYjdz;
import com.cy.hygl.dao.XyjsZbYjdzDao;
import com.cy.hygl.domain.XyjsZbYjdzDomain;

/**
 * The DAOIMP for 下游结算-转包-月结对账.
 * 
 * @author XIAY
 */

@Repository
public class XyjsZbYjdzDaoImp implements XyjsZbYjdzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		// 分页相关
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDm())){
			map.put("zrbmDm", domain.getZrbmDm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDjxh())){
			map.put("zrbmDjxh", domain.getZrbmDjxh());
		}
		if(StringUtils.isNotBlank(domain.getPcrqQ())){
			map.put("pcrqQ", domain.getPcrqQ());
		}
		if(StringUtils.isNotBlank(domain.getPcrqZ())){
			map.put("pcrqZ", domain.getPcrqZ());
		}
		if(StringUtils.isNotBlank(domain.getDzbz())){
			map.put("dzbz", domain.getDzbz());
		}
		
		// 根据页面上的排序条件设置排序
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// 检索数据
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getXyjsZbYjdzRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsZbYjdzPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// 设置查询条件
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDm())){
			map.put("zrbmDm", domain.getZrbmDm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDjxh())){
			map.put("zrbmDjxh", domain.getZrbmDjxh());
		}
		if(StringUtils.isNotBlank(domain.getPcrqQ())){
			map.put("pcrqQ", domain.getPcrqQ());
		}
		if(StringUtils.isNotBlank(domain.getPcrqZ())){
			map.put("pcrqZ", domain.getPcrqZ());
		}
		if(StringUtils.isNotBlank(domain.getDzbz())){
			map.put("dzbz", domain.getDzbz());
		}

		// 检索数据
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsZbYjdzAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		XyjsZbYjdz bo = new XyjsZbYjdz();

		// 根据主键查询对象 如果对象存在那么为修改 不存在则为添加
		XyjsZbYjdzDomain dom = (XyjsZbYjdzDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDzje(domain.getDzje());   //对账金额
			bo.setDzrCzyDjxh(user.czyDjxh); //对账人
			bo.setDzrq(SysDateUtil.getCurrentDate());//对账日期
			bo.setDzJgbm(user.bmbm);	//对账部门
			bo.setDzCybz(domain.getDzCybz());//对账差异标志
			bo.setDzcyje(domain.getDzcyje());//对账差异金额
			bo.setDzbz("Y");   //对账标志
			
			businessSqlMapClientTemplate.update("updateXyjsZbYjdzByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);

			businessSqlMapClientTemplate.insert("insertXyjsZbYjdz", bo);
		}
		//系统参数设定为: 不需要审批
		if(domain.getSfsp().equals("N")){
			bo.setSpbz("Y");
			bo.setWsspztDm("4");//不需要审批时对账状态
			businessSqlMapClientTemplate.update("updateXyjsZbYjdzByKey", bo);
			
			//添加财务应付信息
			CwYsyf sf = new CwYsyf();
			
			//运费结算方代码)
			if(dom.getZrbmDm().equals("1")){
				sf.setYfjsfDm("21");//客户
			}else if(dom.getZrbmDm().equals("2")){
				sf.setYfjsfDm("22");//分公司
			}else if(dom.getZrbmDm().equals("3")){
				sf.setYfjsfDm("23");//分包商
			}
			sf.setYfjsfDjxh(dom.getZrbmDjxh());	//运费结算方登记序号
			sf.setKmdlDm("2");		//应付账款
			sf.setKmxlDm("107");	//转包月结
			sf.setYsyflyDm("36");	//应收应付来源代码
			sf.setYwDjxh(dom.getDzDjxh());		//业务登记序号(视具体业务对应不同表)
			sf.setCsrq(SysDateUtil.getCurrentDate());	//产生日期
			sf.setYsyfztDm("11");		//未付
			sf.setYsfJe(domain.getDzje()+"");//应收付金额
			sf.setYisfJe("0.0");			//已收付金额
			sf.setWsfJe(domain.getDzje()+"");//未收付金额
			sf.setSm("派车单号："+dom.getPcdh()+"派车日期："+SysDateUtil.format(dom.getPcrq())+"货物名称："
					+dom.getHwmc()+"对账支出："+domain.getDzje()); //说明
			sf.setYxbz("Y");			//有效标志(Y/N)
			sf.setDjJgbm(user.bmbm);		//登记部门
			sf.setSsJgbm(dom.getSsJgbm());	//所属机构
			
			businessSqlMapClientTemplate.insert("insertCwYsyf",sf);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键查询条件
		map.put("dzDjxh", domain.getDzDjxh());

		domain = (XyjsZbYjdzDomain)businessSqlMapClientTemplate.queryForObject("selectXyjsZbYjdzByKey", map);
		return domain;
	}
	

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// 设置主键条件
		map.put("dzDjxh", domain.getDzDjxh());

		businessSqlMapClientTemplate.update("deleteXyjsZbYjdzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getDzDjxh())){
			XyjsZbYjdzDomain dom = (XyjsZbYjdzDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
