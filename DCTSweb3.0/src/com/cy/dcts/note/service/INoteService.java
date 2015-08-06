package com.cy.dcts.note.service;

import com.cy.dcts.common.bo.NoteLogInfo;

public interface INoteService {

	/**
	 * 新增发送短信信息
	 * @author nxj
	 * @param noteLogInfo
	 * @return
	 */
	public String addNoteLogInfo(NoteLogInfo noteLogInfo);
	
	/**
	 * HTTP协议短信接口
	 * @param mobilephone
	 * @param noteCode
	 * @return
	 */
	public String sendNoteSDKService(String mobilephone,String noteCode);
}
