package com.cy.dctms.service;

import com.cy.dctms.common.bo.NoteSendRecord;

import java.util.List;
import java.util.Map;

public interface NoteSendRecoreService {
	
	public List<NoteSendRecord> selectNoteSendRecore(Map<String, String> queryMap);

	public List<NoteSendRecord> selectNoteSendRecoreUseFor(Map<String, String> queryMap);
	
	public Integer insertIntoTemp(NoteSendRecord companyBo);
	
	public List<NoteSendRecord> queryNoteSendRecoreList();

}
