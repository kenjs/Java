package com.cy.swp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.swp.bo.NoteSendRecord;
import com.cy.swp.dao.NoteSendRecoreDao;
import com.cy.swp.service.NoteSendRecoreService;

@Service("noteSendRecoreService")
public class NoteSendRecoreServiceImpl implements NoteSendRecoreService {

	@Resource
	private NoteSendRecoreDao noteSendRecoreDao;
	
	public List<NoteSendRecord> selectNoteSendRecore(
			Map<String, String> queryMap) {
		return noteSendRecoreDao.selectNoteSendRecore(queryMap);
	}

	public List<NoteSendRecord> selectNoteSendRecoreUseFor(
			Map<String, String> queryMap) {
		return noteSendRecoreDao.selectNoteSendRecoreUseFor(queryMap);
	}

	public Integer insertIntoTemp(NoteSendRecord companyBo) {
		return noteSendRecoreDao.insertIntoTemp(companyBo);
	}

	public List<NoteSendRecord> queryNoteSendRecoreList() {
		return noteSendRecoreDao.queryNoteSendRecoreList();
	}

}
