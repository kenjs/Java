package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.QyZzjg;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;



import com.cy.xtgl.dao.QyZzjgFgsDao;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * THE ACTION FOR 企业-组织机构 分公司
 * @author 闫伟
 * @date 2013.1.7
*/ 	

@Repository
public class QyZzjgFgsDaoImp extends ExtendDaoImp implements QyZzjgFgsDao {
	
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (StringUtils.isNotBlank(domain.getSjJgbm())) {
			map.put("sj", String.valueOf(domain.getSjJgbm()));
		}
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getQyZzjgFgsCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyZzjgFgsPage", map, start, pagSize);
		return dataList;
	};

	@Override
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			BaseBusinessDomain dom = this.getDomainByKey(domain);
			BeanUtils.copyProperties(domain, dom);
		}
		else{
			Map<String, String> map=new HashMap<String, String>();
			map.put("jgbm", domain.getSjJgbm());
			QyZzjgDomain qyDomain=(QyZzjgDomain)businessSqlMapClientTemplate.queryForObject("selectQyzzjgFgsMcByJgbm",map);
			domain.setSjMc(qyDomain.getMc());
		}
	}

	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseDomain;
		return (QyZzjgDomain) businessSqlMapClientTemplate.queryForObject("selectQyZzjgByKey", domain);
	}

	@Override
	public void deleteByKey(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		businessSqlMapClientTemplate.delete("deleteQyZzFgsByKey", domain);
	}

	@Override
	public void saveDomain(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		QyZzjgDomain dom = null;
		if (domain.getJgbm() != null && !"".equals(domain.getJgbm().trim())) {
			dom = (QyZzjgDomain) this.getDomainByKey(domain);
		}
		String s = SysEncodeUtil.UTF82GBK(domain.getMc());
		if (dom != null) {
			dom.setMc(domain.getMc());
			dom.setJc(domain.getJc());
			dom.setDz(domain.getDz());
			dom.setFzr(domain.getFzr());
			dom.setYb(domain.getYb());
			dom.setDh(domain.getDh());
			dom.setXgrCzyDjxh(user.getCzyDjxh());
			dom.setXgrq(SysDateUtil.getCurrentDate());
			dom.setPyjx(CnToSpellUtil.getFirstSpell(s));
			dom.setPyqp(CnToSpellUtil.getFullSpell(s));
			dom.setQybz(domain.getQybz());
			dom.setFhrXzqhDm(domain.getFhrXzqhDm());
			dom.setByj(domain.getByj());
			businessSqlMapClientTemplate.update("updateQyZzjgByKey", dom);
		} else {
			QyZzjg bo=new QyZzjg();
			QyZzjgDomain dd = new QyZzjgDomain();// 获取一个Domian对象
			dd.setJgbm(String.valueOf(domain.getSjJgbm()));// 取到上级单位的机构编码
			QyZzjgDomain sjgs = (QyZzjgDomain) this.getDomainByKey(dd);// 然后根据上级单位的机构编码取到级次编码
			Long jici = new Long(sjgs.getJcbm());
			domain.setJcbm(String.valueOf(jici + 1));// 把上级级次编号加1
			BeanUtils.copyProperties(bo, domain);
			String max = this.getMaxJbdm(bo);// 取上级单位下面最大的JBDM
			if (max.equals("null")) {// 如果为空的话，表示为null
				String gb = sjgs.getJbdm();
				domain.setJbdm(gb + "0001");
			} else {
				String qian = max.substring(0, max.length() - 4);// 取前面几位数
				String hou = max.substring(max.length() - 4);// 取后面4位数
				int ii = Integer.parseInt(hou);
				int gbbm = ii + 1;
				String str="000"+gbbm;
				domain.setJbdm(qian + str.substring(str.length()-4));
			}
			//domain.setQybz("Y");// 启用标志
			domain.setYxbz("Y");// 有效标志
			domain.setJglbDm(String.valueOf(2));// 设置为2 表示为分公司
			domain.setCjrCzyDjxh(user.getCzyDjxh());// 创建人
			domain.setXgrCzyDjxh(user.getCzyDjxh());// 修改人
			domain.setCjrq(SysDateUtil.getCurrentDate());// 当前创建日期
			domain.setXgrq(SysDateUtil.getCurrentDate());// 当前修改日期
			domain.setPyjx(CnToSpellUtil.getFirstSpell(s));
			domain.setPyqp(CnToSpellUtil.getFullSpell(s));
			businessSqlMapClientTemplate.insert("insertQyZzjg", domain);
		}
	}

	public void startUser(QyZzjgDomain domain) throws Exception {
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			businessSqlMapClientTemplate.update("startQyzzFgsUser", domain);
		}
	}

	public void stopUser(QyZzjgDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			map.put("jgbm", domain.getJgbm());
			businessSqlMapClientTemplate.update("stopQyzzFgsUser", map);
		}
	}

	public String getMaxJbdm(QyZzjg bo) throws Exception {
		String jbdm = String.valueOf(businessSqlMapClientTemplate.queryForObject("getQyzzFgsMaxJbdm", bo));
		return jbdm;
	}

	public int checkQyzzFgsMc(QyZzjgDomain domain) throws Exception {
		String mc = SysEncodeUtil.UTF82ISO(domain.getMc());
		QyZzjg bo=new QyZzjg();
		BeanUtils.copyProperties(bo, domain);
		bo.setMc(mc);
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("checkQyzzFgsMc", bo))).intValue();
		return count;
	}

	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(domain.getSjJgbm())) {
			map.put("sjJgbm", domain.getSjJgbm());
		}
		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectQyZzjgFgsAll", map);
		return dataList;
	}

}
