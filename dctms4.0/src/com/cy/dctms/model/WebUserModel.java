package com.cy.dctms.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dctms.entity.WebUserInfo;
import com.cy.dctms.orm.MybatisDaoSurrport;

@Service
public class WebUserModel {

	@Autowired
	private MybatisDaoSurrport mybatisDaoSurrport;
	
	public String addWebUserInfo(WebUserInfo bo) throws Exception{
		mybatisDaoSurrport.add("com.cy.dctms.entity.WebUserInfo.insertWebUserInfo", bo);
		return bo.getId();
	}
}
