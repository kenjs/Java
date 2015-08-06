package com.cy.swp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.NoteSendRecord;

@Repository("noteSendRecoreDao")
public interface NoteSendRecoreDao {
	
	public List<NoteSendRecord> selectNoteSendRecore(Map<String, String> queryMap);

	public List<NoteSendRecord> selectNoteSendRecoreUseFor(Map<String, String> queryMap);
	
	public Integer insertIntoTemp(NoteSendRecord companyBo);
	
	
	
	/**
	 * 查询当天已经发送三条短信的司机手机号码
	 * @param queryMap
	 * @return
	 * @author nxj
	 */
	public List<NoteSendRecord> queryNoteSendRecoreList();

}
