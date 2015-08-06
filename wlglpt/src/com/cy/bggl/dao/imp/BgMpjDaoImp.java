package com.cy.bggl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgMpjDao;
import com.cy.bggl.domain.BgMpjDomain;
import com.cy.common.bo.BgMpj;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;

/**
 * THE ACTION FOR 办公管理 名片夹
 * 
 * @author 闫伟
 * @date 2013.1.22
 */

@Repository
public class BgMpjDaoImp extends ExtendDaoImp implements BgMpjDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain busDomain, UserDomain user) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) busDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (domain.getGs() != null && !"".equals(domain.getGs())) {
			String gs = SysEncodeUtil.UTF82ISO(domain.getGs());
			map.put("gs", "%" + gs + "%");
		}
		if (domain.getXm() != null && !"".equals(domain.getXm())) {
			String xm = SysEncodeUtil.UTF82ISO(domain.getXm());
			map.put("xm", "%" + xm + "%");
		}
		if (domain.getDz() != null && !"".equals(domain.getDz())) {
			String dz = SysEncodeUtil.UTF82ISO(domain.getDz());
			map.put("dz", "%" + dz + "%");
		}
		map.put("czyDjxh", user.getCzyDjxh());
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getBgMpjRowCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgMpjPage", map, start, pagSize);
		return dataList;
	}

	@Override
	public void initDomainMx(BaseBusinessDomain busDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) busDomain;
		if (StringUtils.isNotBlank(domain.getBgDjxh())) {
			System.out.println("修改操作");
			BaseBusinessDomain dom = this.getDomainByKey(domain);
			BeanUtils.copyProperties(domain, dom);
		}
		System.out.println("添加操作");
	}

	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain busDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) busDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("bgDjxh", domain.getBgDjxh());
		return (BgMpjDomain) businessSqlMapClientTemplate.queryForObject("selectBgMpjByKey", map);
	}

	@Override
	public void saveDomain(BaseBusinessDomain busDomain, UserDomain user) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) busDomain;
		BgMpjDomain bgDomain = null;
		BgMpj bo = new BgMpj();
		if (StringUtils.isNotBlank(domain.getBgDjxh())) {
			bgDomain = (BgMpjDomain) this.getDomainByKey(domain);
		}
		if (bgDomain != null) {
			bgDomain.setXm(domain.getXm());
			bgDomain.setGs(domain.getGs());
			bgDomain.setZw(domain.getZw());
			bgDomain.setDz(domain.getDz());
			bgDomain.setDh(domain.getDh());
			bgDomain.setSj(domain.getSj());
			bgDomain.setCz(domain.getCz());
			bgDomain.setWz(domain.getWz());
			bgDomain.setYb(domain.getYb());
			bgDomain.setDy(domain.getDy());
			bgDomain.setXgrCzyDjxh(user.getCzyDjxh());
			bgDomain.setXgrq(SysDateUtil.getCurrentDate());
			BeanUtils.copyProperties(bo, bgDomain);
			businessSqlMapClientTemplate.update("updateBgMpjByKey", bo);
		} else {
			domain.setYxbz("Y");
			domain.setCjrq(SysDateUtil.getCurrentDate());
			domain.setXgrq(SysDateUtil.getCurrentDate());
			domain.setCzyDjxh(user.getCzyDjxh());
			domain.setCjrCzyDjxh(user.getCzyDjxh());
			domain.setXgrCzyDjxh(user.getCzyDjxh());
			BeanUtils.copyProperties(bo, domain);
			businessSqlMapClientTemplate.insert("insertBgMpj", bo);
		}
	}

	@Override
	public void deleteByKey(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		Map<String, String> map = new HashMap<String, String>();
		map.put("bgDjxh", domain.getBgDjxh());
		businessSqlMapClientTemplate.delete("deleteBgMpjByKey", map);
	}

	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		BgMpjDomain domain = (BgMpjDomain) baseBusinessDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getGs() != null && !"".equals(domain.getGs())) {
			String gs = SysEncodeUtil.UTF82ISO(domain.getGs());
			map.put("gs", "%" + gs + "%");
		}
		if (domain.getXm() != null && !"".equals(domain.getXm())) {
			String xm = SysEncodeUtil.UTF82ISO(domain.getXm());
			map.put("xm", "%" + xm + "%");
		}
		if (domain.getDz() != null && !"".equals(domain.getDz())) {
			String dz = SysEncodeUtil.UTF82ISO(domain.getDz());
			map.put("dz", "%" + dz + "%");
		}
		map.put("czyDjxh", user.getCzyDjxh());
		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgMpjAll", map);
		return dataList;
	}
}
