package com.cy.dctms.sendMessagerLog.service;


public interface INoteService {


	/**
	 * HTTPЭ����Žӿ�
	 * @param mobilephone
	 * @param noteCode
	 * @return
	 */
	public String sendNoteSDKService(String mobilephone,String noteCode);
}
