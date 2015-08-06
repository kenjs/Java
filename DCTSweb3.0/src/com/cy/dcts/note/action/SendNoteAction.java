package com.cy.dcts.note.action;

import java.util.HashMap;

import java.util.Map;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;

import com.cy.dcts.common.util.XmlParseUtil;
import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.NoteLogInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.ipUrlStr.service.IIpUrlStrService;
import com.cy.dcts.note.service.INoteService;



import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
  
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element; 
/**
 * 用户注册、修改密码、找回密码、解绑更换手机号发送验证码 
 * 
 * @author nxj
 *
 */
public class SendNoteAction extends BaseJsonAction {

	private static final long serialVersionUID = 297624853598043847L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private INoteService noteService;
	private IIpUrlStrService ipUrlStrService;
	
	
	
	protected void execMethod() throws Exception {
 		String mobilephone = this.request.getParameter("mobilephone");
		String noteCode = this.request.getParameter("noteCode");
		String type = this.request.getParameter("type");
		String noteId = "";
		String resultes;
		String message;
		StringBuffer content = new StringBuffer();
		//注册
		if("1".equals(type)) {
			content.append("【快到网】感谢您注册,验证码：");
		}
		//找回密码
		if("2".equals(type)) {
			content.append("【快到网】您申请找回密码,验证码：");
		}
		//修改密码
		if("3".equals(type)) {
			content.append("【快到网】您申请修改密码,验证码：");
		}
		//解绑更换手机号
		if("4".equals(type)) {
			content.append("【快到网】您申请解绑更换手机号,验证码：");
		}
		content.append(noteCode);
		content.append("（5分钟内有效）,如非本人操作请忽略,谢谢合作。");
		try {
			NoteLogInfo noteLogInfo = new NoteLogInfo();
			noteLogInfo.setMobilephone(mobilephone);
			noteLogInfo.setNoteCode(noteCode);
			noteLogInfo.setVisitIp(ipUrlStrService.getIpAddr(request));
			//发送短信
			String sdk = noteService.sendNoteSDKService(mobilephone, content.toString());
			String returnStatus = XmlParseUtil.getXmlStr(sdk, "status");
			if("0".equals(returnStatus)) {
				noteLogInfo.setSendStart(String.valueOf(Constants.NOTE_SEND_START_SUCCESS));
				resultes = "0";
				message = "用户注册发送验证码成功";
				logger.debug("测试短信接口发送成功！");
			}else {
				noteLogInfo.setSendStart(String.valueOf(Constants.NOTE_SEND_START_ERROR));
				resultes = "2";
			    message = "用户注册发送验证码失败";
				logger.debug("测试短信接口发送失败！");
			}
			noteLogInfo.setReturnedValue(XmlParseUtil.getXmlStr(sdk, "taskid"));
			//添加发送短信的日志
			noteId = noteService.addNoteLogInfo(noteLogInfo);
			String result = this.sendResponseToJson(resultes,message);
			logger.warn("query user code success. noteId=[{}], mobilephone=[{}], noteCode=[{}], json=[{}]",new Object[] {noteId, mobilephone, noteCode, result });
		}catch (Exception e) {
			logger.error("save note error! noteId=[{}], mobilephone=[{}], noteCode=[{}]",new Object[] { noteId, mobilephone, noteCode });
			throw new RuntimeException();
		}
//		System.out.println(content.toString());
	}
	
//	@Override
//	protected void execMethod() throws Exception {
// 		String mobilephone = this.request.getParameter("mobilephone");
//		String noteCode = this.request.getParameter("noteCode");
//		String type = this.request.getParameter("type");
//		String noteId = "";
//		String resultes;
//		String message;
//		StringBuffer content = new StringBuffer();
//		//注册
//		if("1".equals(type)) {
//			content.append("感谢您注册快到网,验证码：");
//		}
//		//找回密码
//		if("2".equals(type)) {
//			content.append("您申请快到网找回密码,验证码：");
//		}
//		//修改密码
//		if("3".equals(type)) {
//			content.append("您申请快到网修改密码,验证码：");
//		}
//		//解绑更换手机号
//		if("4".equals(type)) {
//			content.append("您申请快到网解绑更换手机号,验证码：");
//		}
//			content.append(noteCode);
//			content.append("（5分钟内有效）,如非本人操作请忽略,谢谢合作。【快到网】");
//		
//		
//		try {
//			NoteLogInfo noteLogInfo = new NoteLogInfo();
//			noteLogInfo.setMobilephone(mobilephone);
//			noteLogInfo.setNoteCode(noteCode);
//			noteLogInfo.setVisitIp(ipUrlStrService.getIpAddr(request));
//			//发送短信
//			String sdk = noteService.sendNoteSDKService(mobilephone, content.toString());
//			Map map = readStringXmlOut(sdk);
//			if("Success".equals(map.get("returnstatus").toString())) {
//				noteLogInfo.setSendStart(String.valueOf(Constants.NOTE_SEND_START_SUCCESS));
//				resultes = "0";
//				message = "用户注册发送验证码成功";
//				logger.debug("测试短信接口发送成功！");
//			}else {
//				noteLogInfo.setSendStart(String.valueOf(Constants.NOTE_SEND_START_ERROR));
//				resultes = "2";
//			    message = "用户注册发送验证码失败";
//				logger.debug("测试短信接口发送失败！");
//			}
//			noteLogInfo.setReturnedValue(map.get("taskID").toString());
//			//添加发送短信的日志
//			noteId = noteService.addNoteLogInfo(noteLogInfo);
//			String result = this.sendResponseToJson(resultes,message);
//			logger.warn("query user code success. noteId=[{}], mobilephone=[{}], noteCode=[{}], json=[{}]",new Object[] {noteId, mobilephone, noteCode, result });
//		}catch (Exception e) {
//			logger.error("save note error! noteId=[{}], mobilephone=[{}], noteCode=[{}]",new Object[] { noteId, mobilephone, noteCode });
//			throw new RuntimeException();
//		}
//	}
	

//    public static Map readStringXmlOut(String xml) {  
//        Map map = new HashMap();  
//        Document doc = null;  
//        try {  
//            doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
//            Element rootElt = doc.getRootElement(); // 获取根节点  
//            String returnstatus = rootElt.elementTextTrim("returnstatus"); // 拿到returnsms节点下的子节点title值  
//            String message = rootElt.elementTextTrim("message"); // 拿到returnsms节点下的子节点title值 
//            String remainpoint = rootElt.elementTextTrim("remainpoint"); // 拿到returnsms节点下的子节点title值 
//            String taskID = rootElt.elementTextTrim("taskID"); // 拿到returnsms节点下的子节点title值  
//            String successCounts = rootElt.elementTextTrim("successCounts"); // 拿到returnsms节点下的子节点title值  
//            map.put("returnstatus", returnstatus);
//            map.put("message", message);
//            map.put("remainpoint", remainpoint);
//            map.put("taskID", taskID);
//            map.put("successCounts", successCounts);
//        } catch (DocumentException e) {  
//            e.printStackTrace();  
//  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return map;  
//    }  

	
	public void setNoteService(INoteService noteService) {
		this.noteService = noteService;
	}


	public void setIpUrlStrService(IIpUrlStrService ipUrlStrService) {
		this.ipUrlStrService = ipUrlStrService;
	}

}
