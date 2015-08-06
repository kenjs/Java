package com.cy.bggl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgGzrDao;
import com.cy.bggl.domain.BgGzrDomain;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysEncodeUtil;

/**
 * THE ACTION FOR 办公管理 工作日
 * 
 * @author 闫伟
 * @date 2013.1.24
 */
  
@Repository
public class BgGzrDaoImp extends ExtendDaoImp implements BgGzrDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@Override
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain busDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) busDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (domain.getStartTime() != null && !domain.getStartTime().equals("")) {
			map.put("startTime", domain.getStartTime());
		}
		if (domain.getEndTime() != null && !domain.getEndTime().equals("")) {
			map.put("endTime", domain.getEndTime());
		}
		map.put("jgbm", domain.getJgbm());
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getBgGzrRowCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgGzrPage", map, start, pagSize);
		return dataList;
	}

	public void updateGzrByJgbm(BaseBusinessDomain busDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) busDomain;
		String gzrDm = SysEncodeUtil.UTF82GBK(domain.getGzrDm());
		if (gzrDm.equals("工作日")) {
			Map<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotBlank(domain.getJgbm())) {
				map.put("jgbm", domain.getJgbm());
			}
			if (StringUtils.isNotBlank(domain.getRq())) {
				map.put("rq", domain.getRq());
			}
			businessSqlMapClientTemplate.update("updateGzrByJgbm", map);
		} else {
			Map<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotBlank(domain.getJgbm())) {
				map.put("jgbm", domain.getJgbm());
			}
			if (StringUtils.isNotBlank(domain.getRq())) {
				map.put("rq", domain.getRq());
			}
			businessSqlMapClientTemplate.update("updateJjrqByJgbm", map);
		}

	}

	@Override
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain busDomain) throws Exception {
		BgGzrDomain domain = (BgGzrDomain) busDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getStartTime() != null && !domain.getStartTime().equals("")) {
			map.put("startTime", domain.getStartTime());
		}
		if (domain.getEndTime() != null && !domain.getEndTime().equals("")) {
			map.put("endTime", domain.getEndTime());
		}
		map.put("jgbm", domain.getJgbm());
		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgGzrAll", map);
		return dataList;
	}
}
