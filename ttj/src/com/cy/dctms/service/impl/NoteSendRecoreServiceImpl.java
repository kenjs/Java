package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.NoteSendRecord;
import com.cy.dctms.dao.NoteSendRecoreDao;
import com.cy.dctms.service.NoteSendRecoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
