package com.cy.hygl.dao.imp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyPcZpaj;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyZpajDao;
import com.cy.hygl.domain.HyZpajDomain;

/**
 * The DAOIMP for ªı‘À-’’∆¨∞≤ºÏ
 * 
 * @date 2013.3.5
 * @author yw
 */
@Repository
public class HyZpajDaoImp extends ExtendDaoImp implements HyZpajDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@Override
	@Transactional
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain busdomain) throws Exception {
		HyZpajDomain domain = (HyZpajDomain) busdomain;
		PageDomain page = domain.getPage();
		Map<String, Object> map = new HashMap<String, Object>();

		List<BaseBusinessDomain> dataList = null;
		map.put("pcJgbm", domain.getPcJgbm());
		map.put("bz", domain.getBz());
		map.put("pcrCzyDjxh", domain.getPcrCzyDjxh());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("clHm", domain.getClHm());
		map.put("sjXm", domain.getSjXm());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("pcDh", domain.getPcDh());
		if(StringUtils.isNotBlank(domain.getClHm())){
			String clHm=SysEncodeUtil.UTF82ISO(domain.getClHm());
			map.put("clHm", clHm);
		}
		if(StringUtils.isNotBlank(domain.getSjXm())){
			String sjXm=SysEncodeUtil.UTF82ISO(domain.getSjXm());
			map.put("sjXm", sjXm);
		}
		if(StringUtils.isNotBlank(domain.getPcDh())){
			String pcdh=SysEncodeUtil.UTF82ISO(domain.getPcDh());
			map.put("pcDh", pcdh);
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			domain.setFhrMc(SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
			map.put("khmc", domain.getFhrMc());
		}
		map.put("pageNum", page.getCurPageNo());
		map.put("pageSize", page.getPageSize());
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);

		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyZpajPage", "dataList", map);
		page.setTotalRecords((Integer) map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>) map.get("dataList");
		page.setReccount((Integer) map.get("reccount"));
		return dataList;
	}
	
	@Override
	@Transactional
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain userdomain) throws Exception {
		HyZpajDomain domain=(HyZpajDomain)userdomain;
		List<BaseBusinessDomain> dataList = null;
		PageDomain page = domain.getPage();
		Map<String, Object> map=new HashMap<String, Object>();
		String jgbm=domain.getSsJgbm();
		String bz="D";
		if(domain.getPcJgbm()!=null&&!domain.getPcJgbm().equals("")){
			jgbm=domain.getPcJgbm();
			bz="B";
		}
		map.put("pcJgbm", jgbm);
		map.put("bz", bz);
		map.put("pcrCzyDjxh", domain.getPcrCzyDjxh());
		map.put("pcrqq", domain.getPcrqq());
		map.put("pcrqz", domain.getPcrqz());
		map.put("clHm", domain.getClhm4Query());
		map.put("sjXm", domain.getSjXm());
		map.put("khDjxh", domain.getFhrDjxh());
		map.put("khmc", domain.getFhrMc());
		map.put("pcDh", domain.getPcDh());
		if(StringUtils.isNotBlank(domain.getClHm())){
			String clHm=SysEncodeUtil.UTF82ISO(domain.getClHm());
			map.put("clHm", clHm);
		}
		if(StringUtils.isNotBlank(domain.getSjXm())){
			String sjXm=SysEncodeUtil.UTF82ISO(domain.getSjXm());
			map.put("sjXm", sjXm);
		}
		if(StringUtils.isNotBlank(domain.getFhrMc())){
			domain.setFhrMc(SysEncodeUtil.UTF82ISO(domain.getFhrMc()));
			map.put("khmc", domain.getFhrMc());
		}
		if(StringUtils.isNotBlank(domain.getPcDh())){
			String pcdh=SysEncodeUtil.UTF82ISO(domain.getPcDh());
			map.put("pcDh", pcdh);
		}
		map.put("pageNum", 1);
		map.put("pageSize", 999999999);
		map.put("pageCount", 0);
		map.put("reccount", 0);
		map.put("dataList", dataList);
		businessSqlMapClientTemplate.queryForObjectByCurr("queryHyZpajPage", "dataList", map);
		page.setTotalRecords((Integer)map.get("pageCount"));
		dataList = (List<BaseBusinessDomain>)map.get("dataList");
		page.setReccount((Integer)map.get("reccount"));
		return dataList;
	}
	
	@Override
	public void saveDomain(BaseBusinessDomain busdomain, UserDomain user) throws Exception {
		HyZpajDomain domain = (HyZpajDomain) busdomain;
		HyPcZpaj bo = new HyPcZpaj();
		domain.setYxbz("Y");
		domain.setCjrCzyDjxh(user.getCzyDjxh());
		domain.setCjrq(SysDateUtil.getCurrentDate());
		domain.setXgrCzyDjxh(user.getCzyDjxh());
		domain.setXgrq(SysDateUtil.getCurrentDate());
		BeanUtils.copyProperties(bo, domain);
		businessSqlMapClientTemplate.insert("insertHyPcZpaj", bo);
//		if (StringUtils.isNotBlank(domain.getFjmcSave())) {
//			putBlobTypeCol(bo.getAjDjxh(), domain.getFj());
//		}

	}
	

	private void putBlobTypeCol(String xh, byte[] fjnr) throws Exception {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		String sql = "select A.AJZP from HY_PC_ZPAJ A WHERE A.AJ_DJXH = " + xh + " FOR UPDATE";
		try {
			conn = businessSqlMapClientTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			pre = conn.prepareStatement(sql.toString());
			rs = pre.executeQuery();
			if (rs.next()) {
				// Blob file = rs.getBlob(1);
				oracle.sql.BLOB file = (oracle.sql.BLOB) rs.getBlob(1);
				// if(null != file){
				BufferedOutputStream out = new BufferedOutputStream(file.getBinaryOutputStream());
				ByteArrayInputStream in = new ByteArrayInputStream(fjnr);
				int c;
				while ((c = in.read()) != -1) {
					out.write(c);
				}
				in.close();
				out.close();
				pre.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
			pre.close();
			conn.close();
		}
	}
	
	
	
	@Override
	public void initDomainMx(BaseBusinessDomain userdomain) throws Exception {
		HyZpajDomain domain=(HyZpajDomain)userdomain;
		if(domain.getPcDjxh()!=null&&!domain.getPcDjxh().equals("")){
			BaseBusinessDomain ajDomain=this.getDomainByKey(domain);
			if(ajDomain!=null){
				BeanUtils.copyProperties(domain, ajDomain);
			}
		}
	}
	
	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain userdomain) throws Exception {
		Map<String, String>map=new HashMap<String, String>();
		HyZpajDomain domain=(HyZpajDomain)userdomain;
		map.put("pcDjxh", domain.getPcDjxh());
		return (BaseBusinessDomain)businessSqlMapClientTemplate.queryForObject("selectHyPcByPcDjxh",map);
	}

}
