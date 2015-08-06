package com.cy.dctms.sendMessagerLog.service;


public interface INoteService {


	/**
	 * HTTP协议短信接口
	 * @param mobilephone
	 * @param noteCode
	 * @return
	 */
	public String sendNoteSDKService(String mobilephone,String noteCode);
}
