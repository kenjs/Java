package com.cy.dcts.note.service.imp;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.util.HttpPostUtil;
import com.cy.dcts.common.bo.NoteLogInfo;
import com.cy.dcts.common.util.MD5Util;
import com.cy.dcts.note.dao.INoteDao;
import com.cy.dcts.note.service.INoteService;

public class NoteServiceImp implements INoteService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String sendNoteSDK;//短信接口访问地址
	private String sendNoteUSERID;//短信接口USERID
	private String sendNotePwdSDK;//短信接口密码
	private String sendNoteTERMID;//扩展号
	private String sendNoteTIME;//发送时间 1为立即发送,定时发送 格式为: yyyyMMddHHmmss
	
	private INoteDao noteDao; 
	
	
	public String addNoteLogInfo(NoteLogInfo noteLogInfo) {
		return noteDao.addNoteLogInfo(noteLogInfo);
	}
	
	
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
	
	
//	public String sendNoteSDKService(String mobilephone, String noteCode) {
//		String urlStr = sendNoteSDK+mobilephone+"&content="+noteCode;
//		URL url = null;
//		@SuppressWarnings("unused")
//		HttpURLConnection httpConn = null;
//		BufferedReader in = null;
//		StringBuffer sb = new StringBuffer();
//		try {
//			url = new URL(urlStr);
//			in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
//			String str = null;
//			while ((str = in.readLine()) != null) {
//				sb.append(str);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			logger.error("HTTP协议接口发送短信接口：", ex);
//		} finally {
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		String result = sb.toString();
//		return result;
//	}
	
	public INoteDao getNoteDao() {
		return noteDao;
	}

	public void setNoteDao(INoteDao noteDao) {
		this.noteDao = noteDao;
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
