package com.cy.driver.user.action;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.NoteLogInfo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.ValidateUtil;
import com.cy.common.util.XmlParseUtil;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;
import com.cy.driver.user.service.LoginUserInfoService;

/**
 * 发送短信
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class NoteSendAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3046914909199738758L;
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private LoginUserInfoService loginUserInfoService;
	private OperationLogService operationLogService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private int codeExistTime;
	
	protected void execMethod() throws Exception {
	
	}
	
	public String exec() {
		try {
			String driverId = "";
			String resulte = "0";
			String message = "";
			
			StringBuilder content = new StringBuilder();
			content.append("【快到网】");
			String code = getRandomStr(6);//随机生成6位验证码
			
			String phoneNumber = request.getParameter("mobilephone");
			if(StringUtils.isBlank(phoneNumber)){
				log.info("用户没有输入手机号码. ");
				sendResponseToJson("-8", "请输入手机号码");
				return ERROR;
			}
			boolean match = ValidateUtil.validateTelePhone(phoneNumber);//手机号码格式验证
			if(!match){
				log.info("手机号码格式不正确. ");
				sendResponseToJson("-8", "手机号码格式不正确");
				return ERROR;
			}
			boolean exist = false;
			String flag = request.getParameter("differenceFlag");//Register or Login ? 0-> Register,1 -> Login
			if(StringUtils.isNotBlank(flag)) {
				if("0".equals(flag)) {
					content.append("感谢您注册，验证码：");
					content.append(code);
					content.append("（5分钟内有效），如非本人操作请忽略，谢谢合作。");
					exist = loginUserInfoService.checkUserAccountExist(phoneNumber);
					if(exist) {
						resulte = "0";
						message = "账号已注册, 请重新输入. ";
						sendResponseToJson(resulte, message);
						return ERROR;
					}
				} else if("1".equals(flag)) {
					exist = loginUserInfoService.checkUserAccountExist(phoneNumber);
					if(!exist) {
						resulte = "0";
						message = "该号码不存在, 请重新输入. ";
						sendResponseToJson(resulte, message);
						return ERROR;
					}
				}
			} else {
				driverId = request.getParameter("driverId");
				if(StringUtils.isBlank(driverId)) {
					sendResponseToJson("-9", "司机不存在");
					return ERROR;
				}
				int accFlag = operationLogService.checkUser(driverId);
				if(accFlag == 1) {
					log.info("该用户不存在或已被删除");
					sendResponseToJson("-9","该用户不存在或已被删除");
					return ERROR;
				} else if(accFlag == 11) {
					log.info("该用户已被冻结");
					sendResponseToJson("-9","该用户已被冻结");
					return ERROR;
				}
				
				content.append("感谢您进行手机认证，验证码：");
				content.append(code);
				content.append("（5分钟内有效），如非本人操作请忽略，谢谢合作。");
			}			
//			String code = "123456";
			log.info("验证手机号码: " + phoneNumber + "; 验证码: " + code);
			String sdk = driverUserCargoInfoService.noteSend(phoneNumber, content.toString());
			String returnStatus = XmlParseUtil.getXmlStr(sdk, "status");
			if("0".equals(returnStatus)) {
				resulte = "1";
				message = "用户注册发送验证码成功";
			}else {
				resulte = "0";
				message = "用户注册发送验证码失败";
			}

			log2Db(driverId);
			sendResponseToJson(resulte, message,code);

			NoteLogInfo noteLogInfo = new NoteLogInfo();
			noteLogInfo.setMobilephone(phoneNumber);
			noteLogInfo.setNoteCode(code);
			noteLogInfo.setReturnedValue(XmlParseUtil.getXmlStr(sdk, "taskid"));
			noteLogInfo.setVisitIp(request.getRemoteAddr());
			operationLogService.insertNoteLogInfo(noteLogInfo);
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public void setLoginUserInfoService(LoginUserInfoService loginUserInfoService) {
		this.loginUserInfoService = loginUserInfoService;
	}

	private String getRandomStr(int length) {
		if(length <= 0)
			return null;
		
		StringBuffer sb = new StringBuffer(length);
		int num ;
		Random random = new Random();
		for(int i = 0;i < length;i ++)
		{
			num = random.nextInt(10);
			sb.append(num);
		}
		return sb.toString();
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("noteSendAction");
		bo.setOperationType(18);
		bo.setRemark("手机验证码");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public int getCodeExistTime() {
		return codeExistTime;
	}

	public void setCodeExistTime(int codeExistTime) {
		this.codeExistTime = codeExistTime;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
	
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
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return map;  
//    }  

}
