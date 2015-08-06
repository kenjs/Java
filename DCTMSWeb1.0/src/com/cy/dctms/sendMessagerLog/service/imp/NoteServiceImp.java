package com.cy.dctms.sendMessagerLog.service.imp;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.util.HttpPostUtil;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.sendMessagerLog.service.INoteService;

public class NoteServiceImp implements INoteService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String sendNoteSDK;//短信接口访问地址
	private String sendNoteUSERID;//短信接口USERID
	private String sendNotePwdSDK;//短信接口密码
	private String sendNoteTERMID;//扩展号
	private String sendNoteTIME;//发送时间 1为立即发送,定时发送 格式为: yyyyMMddHHmmss
	
	
	
	
	public String sendNoteSDKService(String mobilephone, String noteCode) {
		StringBuffer posturl = new StringBuffer(sendNoteSDK);
		
		String md5 = MD5Util.MD5(sendNoteUSERID+"||"+mobilephone+"||"+sendNotePwdSDK);
		
		try {
			noteCode = URLEncoder.encode(noteCode,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Map<String, String> params = new  HashMap<String, String>();
		params.put("userid", sendNoteUSERID);
		params.put("smstype", "0");
		params.put("phones", mobilephone);
		params.put("content", noteCode);
		params.put("sendtermid", sendNoteTERMID);
		params.put("sendtime", sendNoteTIME); 
		params.put("md5", md5.toLowerCase());
		
		return HttpPostUtil.postXml(posturl.toString(), params);
		
	}
	
	
	public void setSendNoteSDK(String sendNoteSDK) {
		this.sendNoteSDK = sendNoteSDK;
	}

	public void setSendNoteUSERID(String sendNoteUSERID) {
		this.sendNoteUSERID = sendNoteUSERID;
	}

	public void setSendNotePwdSDK(String sendNotePwdSDK) {
		this.sendNotePwdSDK = sendNotePwdSDK;
	}


	public void setSendNoteTERMID(String sendNoteTERMID) {
		this.sendNoteTERMID = sendNoteTERMID;
	}


	public void setSendNoteTIME(String sendNoteTIME) {
		this.sendNoteTIME = sendNoteTIME;
	}
	
	
}
