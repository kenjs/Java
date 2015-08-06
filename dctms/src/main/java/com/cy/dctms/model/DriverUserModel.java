package com.cy.dctms.model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dctms.entity.DriverUserInfoDomain;
import com.cy.dctms.orm.MybatisDaoSurrport;

@Service
public class DriverUserModel {

	@Autowired
	private MybatisDaoSurrport mybatisDaoSurrport;
	
	public DriverUserInfoDomain queryDriverUserInfo(Map<String,String> map) throws Exception{
		return (DriverUserInfoDomain) mybatisDaoSurrport.
				queryObject("com.cy.dctms.entity.DriverUserInfoDomain.iBatisSelectDriverUserInfo", map);
	}
}
