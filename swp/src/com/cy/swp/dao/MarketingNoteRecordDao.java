package com.cy.swp.dao;

import com.cy.swp.bo.PushSendRecord;
import org.springframework.stereotype.Repository;

import com.cy.swp.bo.MarketingNoteRecordInfo;

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
