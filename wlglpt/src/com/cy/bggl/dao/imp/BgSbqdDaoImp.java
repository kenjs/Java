package com.cy.bggl.dao.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.bggl.dao.BgSbqdDao;
import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.common.bo.BgQdqt;
import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * THE ACTION FOR 办公签到签退 上班签到
 * 
 * @author 闫伟
 * @date 2013.1.22
 */

@Repository
public class BgSbqdDaoImp extends ExtendDaoImp implements BgSbqdDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (domain.getStartTime() != null && !domain.getEndTime().equals("")) {
			map.put("startTime", domain.getStartTime());
		}
		if (domain.getEndTime() != null && !domain.getEndTime().equals("")) {
			map.put("endTime", domain.getEndTime());
		}
		map.put("djxh", user.getCzyDjxh());
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject("getBgSbQdRowCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgSbQdPage", map, start, pagSize);
		return dataList;
	}

	public List<BgGzsjDomain> getSbSj(String bm) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("bm", bm);
		return businessSqlMapClientTemplate.queryForList("getBgSbQdsj", map);
	}

	@Override
	public void saveDomain(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		DateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.sql.Timestamp time = new java.sql.Timestamp(new Date().getTime());
		String hours = String.valueOf(time.getHours());// 时
		String fen = String.valueOf(time.getMinutes());// 分
		String miao = String.valueOf(time.getSeconds());// 秒
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		BgQdqt bo = new BgQdqt();
		String riqi = SysDateUtil.getCurrentDate();
		String nowDate=riqi+" "+hours+":"+fen+":"+miao;
		String oldDate = riqi + " " + domain.getSbSj();
		Date d1 = dateFor.parse(nowDate);
		Date d2 = dateFor.parse(oldDate);
		long cha = d1.getTime() - d2.getTime();
		long day = cha / (1000 * 60 * 60 * 24);
		long hour = (cha / (60 * 60 * 1000) - day * 24);
		long min = ((cha / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// 如果大于0，代表已迟到，赋状态为Y
		if (min > 0) {
			bo.setCdztsj(Double.valueOf(hour * 60 + min));
			bo.setCdztbz("Y");
		} else {
			bo.setCdztsj(Double.valueOf(0));
			bo.setCdztbz("N");
		}
		bo.setYQdqtSj(oldDate);
		bo.setSjQdqtSj(nowDate);
		bo.setCzyDjxh(user.getCzyDjxh());
		bo.setRq(SysDateUtil.getCurrentDate());
		bo.setQdqtDm("1");
		businessSqlMapClientTemplate.insert("insertBgQdqt", bo);
	}

	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain, UserDomain user) throws Exception {
		BgQdqtDomain domain = (BgQdqtDomain) baseBusinessDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (domain.getStartTime() != null && !domain.getEndTime().equals("")) {
			map.put("startTime", domain.getStartTime());
		}
		if (domain.getEndTime() != null && !domain.getEndTime().equals("")) {
			map.put("endTime", domain.getEndTime());
		}
		map.put("czyDjxh", user.getCzyDjxh());
		// 检索数据
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate.queryForList("selectBgSbqdAll", map);
		return dataList;

	}
	
	public List<BgQdqtDomain> getSjQdByCzy(String czy) throws Exception{
		Map<String, String> map=new HashMap<String, String>();
		map.put("czy", czy);
		return businessSqlMapClientTemplate.queryForList("selectSjQdByCzy",map);
	}
}
