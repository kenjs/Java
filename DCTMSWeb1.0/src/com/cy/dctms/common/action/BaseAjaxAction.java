package com.cy.dctms.common.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.constants.Constants;


/**
 * ����AJAX����action
 * @author hayden
 */
public abstract class BaseAjaxAction extends BaseAction {

	private static final long serialVersionUID = -1559876334712310043L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	protected abstract String execMethod() throws Exception;

	@Override
	public String exec() {
		try {
			return execMethod();
		} catch (Exception e) {
			logger.warn("error:", e);
			try {
				this.sendBusinessErrorResponse(null);
			} catch (Exception e1) {
			}
			return null;
		}
	}
	
	/**
	 * Ajax���󷵻�ҵ�������
	 * */
	public String sendBusinessErrorResponse(String errorMessage) throws IOException, Exception{
		if(StringUtils.isEmpty(errorMessage)){
			errorMessage = "����ʧ�ܣ�ҵ�������";
		}
		return this.sendErrorResponse(Constants.EXCEPTION_CODE_BUSINESS_COMMON, errorMessage);	
	}
	
	/**
	 * Ajax���󷵻����ݿ����
	 * */
	public String sendDataBaseErrorResponse(String errorMessage) throws IOException, Exception{
		if(StringUtils.isEmpty(errorMessage)){
			errorMessage = "����ʧ�ܣ����ݿ����";
		}
		return this.sendErrorResponse(Constants.EXCEPTION_CODE_DATABASE_COMMON, errorMessage);	
	}
	
	/**
	 * Ajax���󷵻�SESSION����Ϊ�մ���
	 * */
	public String sendSessionEmptyErrorResponse(String errorMessage) throws IOException, Exception{
		if(StringUtils.isEmpty(errorMessage)){
			errorMessage = "ֻ�е�¼�û����ܽ��в��������ȵ�¼��";
		}
		return this.sendErrorResponse(Constants.EXCEPTION_CODE_SESSION_EMPTY_COMMON, errorMessage);	
	}
	
	/**
	 * Ajax���󷵻������������
	 * */
	public String sendParameterErrorResponse(String errorMessage) throws IOException, Exception{
		if(StringUtils.isEmpty(errorMessage)){
			errorMessage = "����ʧ�ܣ������������!";
		}
		return this.sendErrorResponse(Constants.EXCEPTION_CODE_PARAMETER_COMMON, errorMessage);	
	}
	
	/**
	 * Ajax���󷵻��Զ������
	 * */
	public String sendErrorResponse(String errorCode, String errorMessage) throws IOException, Exception{
		if (isAjaxRequest()) {
			response.setContentType("text/plain;charset=GBK");
			response.setStatus(500);
			PrintWriter out = response.getWriter();
			out.write("������룺" + errorCode + "<br> ������Ϣ��" + errorMessage);
			out.flush();
			out.close();
			return "������룺" + errorCode + "<br> ������Ϣ��" + errorMessage;
		}
		logger.info("send response fail! no ajax request.");
		return "no ajax request.";
	}
	
	/**
	 * @Description:�ж��Ƿ���ajax������
	 */
	private boolean isAjaxRequest() {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	
}
