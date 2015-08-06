package com.cy.common.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.GnqxglCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseCommonService;

@Service
public class BaseCommonServiceImp implements BaseCommonService {

	@Autowired
	private GnqxglCommonDao commonDao;
	
	/**
	 * ���ݲ�������ȡ����Ӧ�Ĳ���ֵ
	 */
	public String getCszByCsbm(String csbm, UserDomain user) throws Exception{
		// �ȴ�USERDOMAIN��ȡ���������
		if (user.getXtcsMap() != null && user.getXtcsMap().containsKey(csbm))
			return user.getXtcsMap().get(csbm);
		
		String csz;
		csz = commonDao.getCszByCsbm(csbm, "", user.getCzyDjxh(), "", "");
		user.getXtcsMap().put(csbm, csz);
		return csz;
	}
	
}
