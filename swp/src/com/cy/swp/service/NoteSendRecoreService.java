package com.cy.swp.service;

import java.util.List;
import java.util.Map;

import com.cy.swp.bo.NoteSendRecord;

public interface NoteSendRecoreService {
	
	public List<NoteSendRecord> selectNoteSendRecore(Map<String, String> queryMap);

	public List<NoteSendRecord> selectNoteSendRecoreUseFor(Map<String, String> queryMap);
	
	public Integer insertIntoTemp(NoteSendRecord companyBo);
	
	public List<NoteSendRecord> queryNoteSendRecoreList();

}
