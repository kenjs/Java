package com.cy.dctms.dao;

import com.cy.dctms.common.bo.NoteSendRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("noteSendRecoreDao")
public interface NoteSendRecoreDao {
	
	public List<NoteSendRecord> selectNoteSendRecore(Map<String, String> queryMap);

	public List<NoteSendRecord> selectNoteSendRecoreUseFor(Map<String, String> queryMap);
	
	public Integer insertIntoTemp(NoteSendRecord companyBo);
	
	
	
	/**
	 * 查询当天已经发送三条短信的司机手机号码
	 * @return
	 * @author nxj
	 */
	public List<NoteSendRecord> queryNoteSendRecoreList();

}
