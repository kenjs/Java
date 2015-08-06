package com.cy.common.dao.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.cy.common.dao.GnqxglCommonDao;


@Repository
public class GnqxglCommonDaoImp implements GnqxglCommonDao {
	@Autowired
	private SqlMapClientTemplate frmSqlMapClientTemplate;
	
	/**
	 * 根据参数编码取得相应的参数值
	 */
	public String getCszByCsbm(String csbm, String nsrsbh, String czryDm, String dwNsrsbh, String nsrSwjgDm) throws Exception{
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("csbm", csbm);
		parameterMap.put("nsrsbh", nsrsbh);
		parameterMap.put("czryDm", czryDm);
		parameterMap.put("dwNsrsbh", dwNsrsbh);
		parameterMap.put("nsrSwjgDm", nsrSwjgDm);
		parameterMap.put("csz", "");
		try{
			frmSqlMapClientTemplate.queryForObject("getXtcsCsz",parameterMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  parameterMap.get("csz").toString().trim();  
	}

}
