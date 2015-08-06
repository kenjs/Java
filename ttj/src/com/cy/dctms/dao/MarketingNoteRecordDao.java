package com.cy.dctms.dao;

import com.cy.dctms.common.bo.MarketingNoteRecordInfo;
import com.cy.dctms.common.bo.PushSendRecord;
import org.springframework.stereotype.Repository;

@Repository("marketingNoteRecordDao")
public interface MarketingNoteRecordDao {
	
	
	
	/**
	 * 插入营销短信发送记录
	 * @param marketingNoteRecordInfo
	 * @return
	 */
	public Integer addMarketingNoteRecordInfo(MarketingNoteRecordInfo marketingNoteRecordInfo);


	/**
	 * 插入营销推送记录
	 * @param pushSendRecord
	 * @return
	 */
	public Integer addPushSendRecord(PushSendRecord pushSendRecord);
	
	

}
